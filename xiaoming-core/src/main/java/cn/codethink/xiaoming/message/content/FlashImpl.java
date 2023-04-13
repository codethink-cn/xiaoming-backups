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

import com.google.common.base.Preconditions;

import java.util.Objects;

public class FlashImpl
    extends AbstractMessageContent
    implements Flash {
    
    private static final String DEFAULT_SUMMARY = "[闪照]";
    
    private final Image image;
    
    private Integer hashCodeCache;
    
    public FlashImpl(Image image) {
        Preconditions.checkNotNull(image, "Image is null!");
        
        this.image = image;
    }
    
    @Override
    public String summarize() {
        return DEFAULT_SUMMARY;
    }
    
    @Override
    public Image getImage() {
        return image;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FlashImpl flash = (FlashImpl) o;
        return Objects.equals(image, flash.image);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(image);
        }
        return hashCodeCache;
    }
}
