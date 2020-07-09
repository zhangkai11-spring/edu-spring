package com.zhangkai.service_oss.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.zhangkai.service_oss.utils.AliOssData;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
public class OssService {

    public  String  upload(MultipartFile file) {
        String endpoint=AliOssData.END_POINT;
        String keyiid=AliOssData.KEY_ID;
        String keySecret=AliOssData.KEY_SECRET;
        String bucketname=AliOssData.BUCKETNAME;
        OSS ossClient=null;
        InputStream inputStream=null;

        try {
            //获取OSS实例
            ossClient = new OSSClientBuilder().build(endpoint, keyiid, keySecret);
            //获取文件流与名字
            inputStream = file.getInputStream();
            String filename=file.getOriginalFilename();
            //生成新的文件名
            String uuid= UUID.randomUUID().toString().replace("-","");
            String datepath=new DateTime().toString("yyyyMMdd");
            filename=datepath+"/"+uuid+"/"+filename;
            ossClient.putObject(bucketname, filename, inputStream);
            String url="https://"+bucketname+"."+endpoint+"/"+filename;

            return url;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 关闭OSSClient。
            if(ossClient !=null){
                ossClient.shutdown();
            }
        }
        return  null;
    }
}
