package com.zhangkai.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class Stu {
    @ExcelProperty(index = 0)
    private Integer sno;
    @ExcelProperty(index = 1)
    private String sname;
}
