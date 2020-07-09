package com.zhangkai.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zhangkai.base.handler.CommonException;
import com.zhangkai.base.result.R;


import com.zhangkai.eduservice.entity.EduTeacher;


import com.zhangkai.eduservice.entity.Vo.TeaPageConditon;
import com.zhangkai.eduservice.mapper.TeaInfomapper;
import com.zhangkai.eduservice.service.EduTeacherService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "eduservice/teacher")
@Slf4j
@CrossOrigin
public class EduTeaController {

    @Autowired
    private EduTeacherService teaInfoService;

    @Autowired
    private TeaInfomapper teaInfomapper;

    @PostMapping ("/tea/login")
    public R login()
    {
        return  R.ok().data("token","admin");
    }

    @GetMapping("/tea/info")
    public R getinfo()
    {
        return  R.ok().data("name","zhangkai").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @Cacheable(cacheNames = {"Teainfo"},key = "#id")
    @GetMapping("/tea/{id}")
    public R getTeaById(@PathVariable("id")  Long id)
    {
        EduTeacher teaInfo = teaInfoService.getById(id);
        if(teaInfo!=null){return R.ok().data("data",teaInfo);}
        return  R.error();
    }

    @Cacheable(cacheNames = {"Teainfo"},key = "#page+'page'+#size+'size'")
    @GetMapping("/tea/page/{page}/{size}")
    public R querypage(@PathVariable("page") int page, @PathVariable("size") int size)
    {
        Page<EduTeacher> teaInfoPage = new Page<>(page, size);
        QueryWrapper<EduTeacher> teaInfoQueryWrapper = new QueryWrapper<>();

        teaInfoQueryWrapper.orderByDesc("gmt_create");

        IPage<EduTeacher> querypage = teaInfoService.page(teaInfoPage,teaInfoQueryWrapper);
        if(!StringUtils.isEmpty(querypage)){
            return R.ok().data("list",querypage.getRecords()).data("total",querypage.getTotal());
        }
        return R.error();
    }
    //分页查询

    @PostMapping ("/tea/pagecondition/{page}/{size}")
    public R querypagecondtion(@PathVariable("page") int page,@PathVariable("size") int size
            ,@RequestBody(required = false) TeaPageConditon teaPageConditon)
    {

        Page<EduTeacher> teaInfoPage = new Page<>(page, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teaPageConditon.getName();
        Integer level = teaPageConditon.getLevel();
        String begin = teaPageConditon.getBegin();
        String end = teaPageConditon.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(level!= null){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        wrapper.orderByDesc("gmt_create");
        IPage<EduTeacher> querypage = teaInfoService.page(teaInfoPage,wrapper);
        System.out.println(1);
        return R.ok().data("list",querypage.getRecords()).data("total",querypage.getTotal());

    }

    //查询全部
    @Cacheable(cacheNames = {"Teainfo"},key = "'all'")
    @GetMapping("/tea/all")
    public R findall()
    {
        List<EduTeacher> findall = teaInfoService.list(null);
        if(findall != null)
        {return R.ok().data("全部",findall);}
        return R.error();
    }

    @CacheEvict(cacheNames = {"Teainfo"},allEntries = true)
    @PutMapping("/tea")
    public R updateTeaById(@RequestBody EduTeacher teaInfo) throws CommonException {
        teaInfoService.updateById(teaInfo);
        return R.ok().data("data",teaInfo);
    }

    @CacheEvict(cacheNames = {"Teainfo"},allEntries = true)
    @PostMapping ("/tea")
    public R saveTeaById(@RequestBody EduTeacher teaInfo)
    {
        teaInfoService.save(teaInfo);
        EduTeacher byId = teaInfoService.getById(teaInfo.getId());
        return R.ok().data("data",byId).data("id",teaInfo.getId());
    }

    @CacheEvict(cacheNames = {"Teainfo"},allEntries = true)
    @DeleteMapping ("/tea/{id}")
    public R deleteTeaById(@PathVariable("id")  String id)
    {
        System.out.println(1);
        boolean b = teaInfoService.removeById(id);
        if(b){
            return R.ok();
        }
        return R.error();
    }
}
