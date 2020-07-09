package com.zhangkai.eduservice.entity.tree.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneSubject {
    private String id;
    private String title;
    private List<Twosubject> list=new ArrayList<>();
}
