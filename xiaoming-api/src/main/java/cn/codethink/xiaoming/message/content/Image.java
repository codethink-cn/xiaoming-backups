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

package cn.codethink.xiaoming.message.content;

import cn.codethink.xiaoming.api.APIFactory;
import cn.codethink.xiaoming.common.Resource;
import cn.codethink.xiaoming.message.Message;

/**
 * <h1>图像</h1>
 *
 * @author Chuanwise
 */
public interface Image
    extends MessageContent {
    
    /**
     * 构造图像
     *
     * @param resource 资源
     * @return 图像
     */
    static Image of(Resource resource) {
        return APIFactory.getInstance().getImage(resource);
    }
    
    /**
     * 构造图像
     *
     * @param resource  资源
     * @param width     宽度
     * @param height    高度
     * @param size      字节数
     * @param imageType 图像类型
     * @return 图像
     */
    static Image of(Resource resource, int width, int height, int size, ImageType imageType) {
        return APIFactory.getInstance().getImage(resource, width, height, size, imageType);
    }
    
    /**
     * 获取图像资源
     *
     * @return 图像资源
     */
    Resource getResource();
    
    /**
     * 获取图片宽度
     *
     * @return 图片宽度像素数或 0
     */
    int getWidth();
    
    /**
     * 获取图片高度
     *
     * @return 图片高度像素数或 0
     */
    int getHeight();
    
    /**
     * 获取图片大小
     *
     * @return 图片大小字节数或 0
     */
    int getSize();
    
    /**
     * 获取图片类型
     *
     * @return 图片类型
     */
    ImageType getImageType();
}
