package com.zhangkai.eduservice.entity.execel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EduSub {
    @ExcelProperty(index = 0)
    private String onesubject;
    @ExcelProperty(index = 1)
    private String twosubject;

}
