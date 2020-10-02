package lru;

import java.util.Hashtable;

/**
 * @ClassName LRUHashTable
 * @Description hashtable + 链表 实现lru算法
 * @Author boy
 * @Date 2019/6/22 11:38 AM
 */
public class LRUHashTable {
    //链表的头结点
    NodeCache head;
    //链表的尾结点
    NodeCache tail;
    //容器
    Hashtable hashtable;
    //长度
    int length;
    //实际长度
    int currentLength;

    public LRUHashTable(int length) {
        this.length = length;
        hashtable = new Hashtable(length);
    }

    /*
     * @Author boy
     * @Description 插入新值
     * @Date 2019/6/24 9:18 PM
     * @Param [key, value]
     * @return void
     */
    public void put(Object key, Object value) {
        NodeCache nodeCache = (NodeCache) hashtable.get(key);
        if (nodeCache == null) {
            if (currentLength >= length) {
                delTail();
            }
            nodeCache = new NodeCache();
            currentLength++;
        }
        nodeCache.key = key;
        nodeCache.value = value;
        hashtable.put(key, nodeCache);
        moveToHead(nodeCache);

    }

    /*
     * @Author boy
     * @Description 获取值
     * @Date 2019/6/24 9:19 PM
     * @Param [key]
     * @return java.lang.Object
     */
    public Object get(Object key) {
        NodeCache nodeCache = (NodeCache) hashtable.get(key);
        if (nodeCache == null) {
            return null;
        } else {
            moveToHead(nodeCache);
        }

        return nodeCache.value;
    }

    /*
     * @Author boy
     * @Description 删除尾部节点
     * @Date 2019/6/24 9:19 PM
     * @Param []
     * @return void
     */
    public void delTail() {
        hashtable.remove(tail.key);
        tail = tail.prev;
        tail.next = null;
        currentLength--;
    }

    /*
     * @Author boy
     * @Description 节点移到表头
     * @Date 2019/6/24 9:19 PM
     * @Param [nodeCache]
     * @return void
     */
    public void moveToHead(NodeCache nodeCache) {
        if (nodeCache == head) {
            return;
        }
        if (nodeCache.prev != null) {
            nodeCache.prev.next = nodeCache.next;
        }
        if (nodeCache.next != null) {
            nodeCache.next.prev = nodeCache.prev;
        }
        if (nodeCache == tail) {
            tail = nodeCache.prev;
        }
        if (head != null) {
            head.prev = nodeCache;
            nodeCache.next = head;
            nodeCache.prev = null;
        }
        head = nodeCache;
        if (tail == null) {
            tail = head;
        }

    }
}

/*
 * @Author boy
 * @Description 链表节点
 * @Date 2019/6/24 6:05 PM
 * @Param
 * @return
 */
class NodeCache {
    //键
    Object key;
    //值
    Object value;
    //指向上一个节点的引用
    NodeCache prev;
    //指向下一个节点的引用
    NodeCache next;
}

