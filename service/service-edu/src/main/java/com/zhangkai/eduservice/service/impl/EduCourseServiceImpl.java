package com.zhangkai.eduservice.service.impl;

import com.zhangkai.base.handler.CommonException;
import com.zhangkai.base.result.ResultCode1;
import com.zhangkai.eduservice.entity.EduCourse;
import com.zhangkai.eduservice.entity.EduCourseDescription;
import com.zhangkai.eduservice.entity.Vo.CourseinfoVo;
import com.zhangkai.eduservice.mapper.EduCourseMapper;
import com.zhangkai.eduservice.service.EduCourseDescriptionService;
import com.zhangkai.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-07-08
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    @Transactional
    public String savecourse(CourseinfoVo courseinfoVo) throws CommonException {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseinfoVo,eduCourse);
        baseMapper.insert(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseinfoVo,eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
       /* if(save==false){
            throw new CommonException(ResultCode1.FAIL);
        }*/
       return eduCourse.getId();
    }
}
