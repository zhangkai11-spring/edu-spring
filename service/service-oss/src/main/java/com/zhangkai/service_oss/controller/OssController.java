package com.zhangkai.service_oss.controller;

import com.zhangkai.base.result.R;
import com.zhangkai.service_oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("ossService")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadoss(@RequestParam(name = "file") MultipartFile file){
        String upload = ossService.upload(file);
        return R.ok().data("url",upload);
    }
}
