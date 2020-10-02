package lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName LRU
 * @Description 基于LinkedHashMap的LRU算法
 * @Author boy
 * @Date 2019/6/13 10:27 AM
 */
public class LRULinkedHashMap extends LinkedHashMap {

    //缓存长度
    int length;

    public LRULinkedHashMap(int length) {
        super(length, 0.75f, true);
        this.length = length;
    }

    /*
     * @Author boy
     * @Description LRU算法的关键方法，超过最大长度就移除尾部元素
     * @Date 2019/6/13 12:24 PM
     * @Param [map]
     * @return boolean
     */
    @Override
    public boolean removeEldestEntry(Map.Entry map) {
        return size() > length;
    }
}
