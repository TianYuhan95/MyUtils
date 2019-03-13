package com.tian.test.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @BelongProject:test
 * @BelongPackage:com.tian.test.utils
 * @Author:田宇寒
 * @CreateTime:2019-03-13
 * @Description: 文件上传工具类
 */
public class UploadTool {
    public static void uploadFile(MultipartFile multipartFile) throws IOException {
        //获取用户提交的文件名字
        String orignalFilename = multipartFile.getOriginalFilename();
        //动态生成文件名--->UUID(全球唯一标识：网卡号+时间戳)
        String randomUUID = UUID.randomUUID().toString();
        //通过orignalFilename获取文件类型
        int index = orignalFilename.lastIndexOf(".");
        String exet = orignalFilename.substring(index);
        //根据当前的系统时间生成对应的文件夹：格式为yyyyMMdd\HH\mm\ss
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd/HH/mm/ss");
        String dateStr = simpleDateFormat.format(date);
        String filePath = "/"+dateStr;
        System.out.println(filePath);
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        filePath += "/"+randomUUID+exet;
        multipartFile.transferTo(new File(filePath));
    }
}
