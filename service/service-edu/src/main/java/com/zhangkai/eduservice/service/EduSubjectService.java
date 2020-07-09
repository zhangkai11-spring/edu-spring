package com.zhangkai.eduservice.service;

import com.zhangkai.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangkai.eduservice.entity.tree.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-07-07
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addExcel(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> tree();
}
