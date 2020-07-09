package com.zhangkai.eduservice.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class Elisenter extends AnalysisEventListener<Stu> {
    @Override
    public void invoke(Stu stu, AnalysisContext analysisContext) {
        System.out.println("***"+stu);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
