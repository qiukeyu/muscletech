package com.example.demo.utils;


import cn.hutool.core.codec.Base64;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadImgBed {

    public static final String ACCESS_TOKEN = "af1840e63d4748bd001d64dd2dc01c3d";
    public static final String OWNER = "seeking-ke-yu";
    public static final String REPO = "picture-bed";
    public static final String PATH = "/img/";

    // 用于提交描述
    public static final String ADD_MESSAGE = "add img";
    public static final String DEL_MESSAGE = "del img";

    public static final String API_CREATE_POST = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";


    /**
     * 生成创建(获取、删除)的指定文件路径
     *
     * @param type             advertisement(0), userAvatar(1), staffAvatar(2)
     * @param originalFilename
     * @return
     */
    public static String createUploadFileUrl(Integer type, String originalFilename) {
        //获取文件后缀
        String suffix = originalFilename.contains(".") ? originalFilename.substring(originalFilename.indexOf('.')) : null;
        //分类存储
        String path;
        switch (type) {
            case 0 -> path = "advertisement";
            case 1 -> path = "userAvatar";
            case 2 -> path = "staffAvatar";
            default -> path = "others";
        }
        //拼接存储的图片名称
        String fileName = "/" + System.currentTimeMillis() + " " + UUID.randomUUID() + suffix;
        //填充请求路径
        String url = String.format(UploadImgBed.API_CREATE_POST,
                UploadImgBed.OWNER,
                UploadImgBed.REPO,
                UploadImgBed.PATH + path + fileName);
        return url;
    }

    /**
     * 获取创建文件的请求体map集合：access_token、message、content
     *
     * @param multipartFile 文件字节数组
     * @return 封装成map的请求体集合
     */
    public static Map<String, Object> getUploadBodyMap(byte[] multipartFile) {
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("access_token", UploadImgBed.ACCESS_TOKEN);
        bodyMap.put("message", UploadImgBed.ADD_MESSAGE);
        bodyMap.put("content", Base64.encode(multipartFile));
        return bodyMap;
    }
}
