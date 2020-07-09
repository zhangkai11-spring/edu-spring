package com.zhangkai.service_oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliOssData implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyid;
    @Value("${aliyun.oss.file.keysecret}")
    private String keysecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    public static String END_POINT;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        KEY_ID=keyid;
        KEY_SECRET=keysecret;
        BUCKETNAME=bucketname;
    }
}
