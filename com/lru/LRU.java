package lru;

/**
 * @ClassName LRU
 * @Description
 * @Author boy
 * @Date 2019/6/20 8:19 PM
 */
public class LRU{
    //链表的头结点
    Node head;
    //链表的尾结点
    Node tail;
    //长度
    int length;

    public LRU(int length){
        this.length = length;
    }

    public void addNode(String value){
        Node node = new Node();
        node.value = value;
        if (null == head){
            head = node;
            tail = node;
            return;
        }
        if (length<=size()){
            delTailNode();
        }
        head.prev = node;
        node.next = head;
        head = node;

    }

    public Node getNode(String value){
        Node node = head;
        while (node!=null){
            if (value.equals(node.value)){
                Node preNode = node.prev;
                preNode.next = node.next;
                node.next.prev = preNode;
                node.next = head;
                head.prev = node;
                head = node;
                break;
            }else {
                node = node.next;
            }
        }
        return node;
    }

    public void delTailNode(){
        tail = tail.prev;
        tail.next = null;
    }

    public void delNode(String value){
        Node node = head;
        while (node!=null){
            if (value.equals(node.value)){
                Node comNode = node.prev;
                comNode.next = node.next;
                node.next.prev = comNode;
                break;
            }else {
                node = node.next;
            }
        }
    }

    public int size(){
       int length = 0;
        Node node = head;
        while (node!=null){
            length++;
            node = node.next;
        }
        return length;
    }
}

class Node{
    //节点值
    String value;
    //指向上一个节点的引用
    Node prev;
    //指向下一个节点的引用
    Node next;
}