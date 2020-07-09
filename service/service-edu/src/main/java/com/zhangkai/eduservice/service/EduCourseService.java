package com.zhangkai.eduservice.service;

import com.zhangkai.base.handler.CommonException;
import com.zhangkai.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangkai.eduservice.entity.Vo.CourseinfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-08
 */
public interface EduCourseService extends IService<EduCourse> {

    String savecourse(CourseinfoVo courseinfoVo) throws CommonException;
}
