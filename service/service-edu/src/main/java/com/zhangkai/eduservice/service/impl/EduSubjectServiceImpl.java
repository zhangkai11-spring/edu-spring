package com.zhangkai.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangkai.eduservice.entity.EduSubject;
import com.zhangkai.eduservice.entity.execel.EduSub;
import com.zhangkai.eduservice.entity.tree.subject.OneSubject;
import com.zhangkai.eduservice.entity.tree.subject.Twosubject;
import com.zhangkai.eduservice.listener.SubWatch;
import com.zhangkai.eduservice.mapper.EduSubjectMapper;
import com.zhangkai.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-07
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {



    @Override
    public void addExcel(MultipartFile file,EduSubjectService eduSubjectService) {


        try {
            EasyExcel.read(file.getInputStream(), EduSub.class,new SubWatch(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> tree() {
        //一级分类列表
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<EduSubject> Onesubjectlist = baseMapper.selectList(oneWrapper);
        //二级分类列表
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id","0");
        List<EduSubject> Twosubjectlist = baseMapper.selectList(twoWrapper);

        //大列表
        List<OneSubject> oneSubjects = new ArrayList<>();

        for (EduSubject Subject1:Onesubjectlist) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(Subject1,oneSubject);
            List<Twosubject> twosubjects = new ArrayList<Twosubject>();
            for (EduSubject Subject2: Twosubjectlist) {
                if(Subject2.getParentId().equals(Subject1.getId())){
                    Twosubject twosubject = new Twosubject();
                    BeanUtils.copyProperties(Subject2,twosubject);
                    twosubjects.add(twosubject);
                }
            }
            oneSubject.setList(twosubjects);
            oneSubjects.add(oneSubject);
        }

        return oneSubjects;
    }
}
