package com.lagou.io;

import java.io.InputStream;

/**
 * @ClassName Resources
 * @Description TODO
 * @Author 智弘
 * @Date 2020/11/22 19:14
 * @Version 1.0
 */
public class Resources {
    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     */
    public static InputStream getResourceAsSteam(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
