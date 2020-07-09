package com.zhangkai.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangkai.base.handler.CommonException;
import com.zhangkai.base.result.ResultCode1;
import com.zhangkai.eduservice.entity.EduSubject;
import com.zhangkai.eduservice.entity.execel.EduSub;
import com.zhangkai.eduservice.service.EduSubjectService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SubWatch extends AnalysisEventListener<EduSub> {
    private EduSubjectService eduSubjectService;
    //一条一条保存数据，从第一列开始保存，之后第二列，以此类推。。。
    @Override
    public void invoke(EduSub subjectData, AnalysisContext analysisContext) {

        //判断一级分类是否已存在
        EduSubject oneEduSubject = existOneSubject(eduSubjectService,subjectData.getOnesubject());
        //如果不存在，则保持一级分类
        if(oneEduSubject == null){
            oneEduSubject=new EduSubject();
            oneEduSubject.setParentId("0");
            oneEduSubject.setTitle(subjectData.getOnesubject());
            eduSubjectService.save(oneEduSubject);
        }
        //一级分类的id就是二级分类的父id
        String pid=oneEduSubject.getId();
        //查询是否存在二级分类
        EduSubject twoEduSubject = existTwoSubject(eduSubjectService, subjectData.getTwosubject(), pid);
        //如果不存在，则保存二级分类
        if(twoEduSubject == null){
            twoEduSubject=new EduSubject();
            twoEduSubject.setParentId(pid);
            twoEduSubject.setTitle(subjectData.getTwosubject());
            eduSubjectService.save(twoEduSubject);
        }

    }
    //判断数据库是否已存在一级分类
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name).eq("parent_id","0");
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
    //判断数据库是否已存在二级分类
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name).eq("parent_id",pid);
        EduSubject eduSubject = eduSubjectService.getOne(wrapper);
        return eduSubject;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
