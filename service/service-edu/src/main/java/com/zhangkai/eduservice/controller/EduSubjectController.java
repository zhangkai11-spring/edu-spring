package com.zhangkai.eduservice.controller;


import com.zhangkai.base.result.R;
import com.zhangkai.eduservice.entity.EduSubject;
import com.zhangkai.eduservice.entity.tree.subject.OneSubject;
import com.zhangkai.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-07
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping(value = "addExcel")
    public R addEexcel(@RequestParam(name = "file")MultipartFile file){
        eduSubjectService.addExcel(file,eduSubjectService);
        return R.ok();
    }

    @GetMapping(value = "all")
    public R findall(){
        List<OneSubject> tree = eduSubjectService.tree();
        return R.ok().data("tree",tree);
    }

}

