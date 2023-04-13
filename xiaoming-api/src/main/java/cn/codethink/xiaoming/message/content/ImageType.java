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

import java.util.Set;

/**
 * <h1>图片类型</h1>
 *
 * @author Chuanwise
 */
public interface ImageType {
    
    /**
     * PNG
     */
    ImageType PNG = of("PNG");
    
    /**
     * BMP
     */
    ImageType BMP = of("BMP");
    
    /**
     * JPG
     */
    ImageType JPG = of("JPG");
    
    /**
     * GIF
     */
    ImageType GIF = of("GIF");
    
    /**
     * WEBP
     */
    ImageType WEBP = of("WEBP");
    
    /**
     * 获取图片类型
     *
     * @param imageType 图片类型
     * @return 图片类型
     */
    static ImageType of(String imageType) {
        return APIFactory.getInstance().getImageType(imageType);
    }
    
    /**
     * 获取支持的所有图片类型
     *
     * @return 图片类型
     */
    static Set<ImageType> getInstances() {
        return APIFactory.getInstance().getImageTypes();
    }
}
