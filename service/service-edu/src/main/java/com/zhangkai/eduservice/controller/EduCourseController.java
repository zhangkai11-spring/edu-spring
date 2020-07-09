package com.zhangkai.eduservice.controller;


import com.zhangkai.base.handler.CommonException;
import com.zhangkai.base.result.R;
import com.zhangkai.eduservice.entity.Vo.CourseinfoVo;
import com.zhangkai.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/eduservice/educourse")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("step1")
    public R savestep1(@RequestBody CourseinfoVo courseinfoVo) throws CommonException {
        String courseid = eduCourseService.savecourse(courseinfoVo);
        return R.ok().data("courseid",courseid);
    }

}

