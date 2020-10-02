package lru;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @ClassName LRUTest
 * @Description TODO
 * @Author boy
 * @Date 2019/6/20 9:13 PM
 */
public class LRUTest {
    public static void main(String args[]) {
//        LRU lru = new LRU(4);
//        lru.addNode("1");
//        lru.addNode("2");
//        lru.addNode("3");
//        lru.addNode("4");
//        lru.addNode("5");
//
//        lru.getNode("3");
//
//
//        Node node = lru.head;
//        while(node!=null){
//            System.out.println(node.value);
//            node = node.next;
//            if (null == node){
//                break;
//            }
//        }

//        Hashtable hashtable = new Hashtable();
//        hashtable.put("1","1");
//        hashtable.put("1","2");
//        System.out.println(hashtable.get("1"));


        LRUCache lruCache1 = new LRUCache(1);
        lruCache1.put("1", "1");

        LRUHashTable lruCache = new LRUHashTable(4);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        lruCache.put("4", "4");
        lruCache.put("5", "5");
        lruCache.put("5", "6");

        System.out.println(lruCache.get("1"));

        NodeCache nodeCache = lruCache.head;
        while (nodeCache != null) {
            System.out.println(nodeCache.value);
            nodeCache = nodeCache.next;
        }

        HashMap hashMap = new HashMap(1);
        hashMap.put("1", "1");
        hashMap.put("2", "2");
        System.out.println(hashMap.get("1"));
        System.out.println(hashMap.get("2"));
    }
}
