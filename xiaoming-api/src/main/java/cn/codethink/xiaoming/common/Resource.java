/*
 * Copyright 2023 CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.api.APIFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * <h1>资源</h1>
 *
 * <p>资源是一种可通过流访问的数据，如外部文件、</p>
 *
 * @author Chuanwise
 */
@FunctionalInterface
public interface Resource {
    
    /**
     * 将文件作为资源
     *
     * @param file 文件
     * @return 资源
     */
    static Resource of(File file) {
        return APIFactory.getInstance().getFileResource(file);
    }
    
    /**
     * 将字节数组作为资源
     *
     * @param bytes 字节数组
     * @return 资源
     */
    static Resource of(byte[] bytes) {
        return APIFactory.getInstance().getBytesResource(bytes);
    }
    
    /**
     * 将 URL 数据作为资源
     *
     * @param url URL
     * @return 资源
     */
    static Resource of(URL url) {
        return APIFactory.getInstance().getURLResource(url);
    }
    
    /**
     * 将指定路径的静态资源作为资源
     *
     * @param path 静态资源路径
     * @return 资源
     */
    static Resource of(String path) {
        return APIFactory.getInstance().getStaticResource(path);
    }
    
    /**
     * 将指定路径的静态资源作为资源
     *
     * @param classLoader 类加载器
     * @param path        静态资源路径
     * @return 资源
     */
    static Resource of(ClassLoader classLoader, String path) {
        return APIFactory.getInstance().getStaticResource(classLoader, path);
    }
    
    /**
     * 将输入流作为资源
     *
     * @param inputStream 输入流
     * @return 资源
     */
    static Resource of(InputStream inputStream) {
        return APIFactory.getInstance().getInputStreamResource(inputStream);
    }
    
    /**
     * 打开资源
     *
     * @return 输入流
     * @throws IOException 打开资源过程中的异常
     */
    InputStream open() throws IOException;
}

