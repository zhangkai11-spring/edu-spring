package com.zhangkai.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangkai.eduservice.entity.EduTeacher;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

@Component
@MapperScan
public interface TeaInfomapper extends BaseMapper<EduTeacher> {
}
