package cn.codethink.xiaoming.common;

import cn.codethink.xiaoming.api.APIFactory;

/**
 * <h1>字符串标识符</h1>
 *
 * @author Chuanwise
 */
public interface StringId
    extends Id, Comparable<StringId> {
    
    /**
     * 获取空字符串标识符
     *
     * @return 空字符串标识符
     */
    static StringId empty() {
        return APIFactory.getInstance().getEmptyStringId();
    }
    
    /**
     * 构造字符串标识符
     *
     * @param value 字符串
     * @return 字符串标识符
     */
    static StringId of(String value) {
        return APIFactory.getInstance().getStringId(value);
    }
}
