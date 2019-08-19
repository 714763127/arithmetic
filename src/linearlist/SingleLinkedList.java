package linearlist;

import sun.util.resources.cldr.en.CalendarData_en_US_POSIX;

/*
 * 单链表
 * */
public class SingleLinkedList<T> {

    //定义一个内部Node，代表链表节点

    private class Node {
        private T data;//保存数据
        private Node next;//指向下一个节点的引用

        public Node() {

        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node header;//头结点
    private Node tail;//保存伟结点
    private int size;//保存已含有的节点数据

    public SingleLinkedList() {
        header = null;
        tail = null;
    }

    public SingleLinkedList(T element) {
        header = new Node(element, null);
        tail = header;
        size++;
    }

    //返回链表长度
    public int length() {
        return size;
    }

    //获取指定索引处的元素

    public T get(int index) {
        return this.getNodeByIndex(index).data;
    }

    //获取指定位置的节点

    private Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        Node current = header;//从头开始遍历
        int i = 0;
        while (true) {
            if (i == index) {
                return current;
            }
            current = header.next;
            i++;
        }
    }

    //按值查找所在位置
    public int locate(T element) {
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    //指定位置插入元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        //如果是空链表
        if (header == null) {
            add(element);
        } else {
            //当index为0，即在链表头处插入
            if (index == 0) {
                addAtHead(element);
            } else {
                Node prev = getNodeByIndex(index - 1);//获取前一个结点
                //让prev的next指向新节点，新节点的next指向原来prev的下一个节点
                prev.next = new Node(element, prev.next);
                size++;
            }

        }

    }

    //在尾部插入元素
    public void add(T element) {
        //如果链表是空的
        if (header == null) {
            header = new Node(element, null);
            //只有一个节点，header,tail都应指向该节点
            tail = header;
        } else {
            Node newNoed = new Node(element, null);
            tail.next = newNoed;

            tail = newNoed;
        }
        size++;
    }

    //头部插入
    public void addAtHead(T element) {
        //创建新的节点，让新的节点的next指向header
        //并以新的节点为新的header
        Node newNode = new Node(element, null);
        newNode.next = header;
        header = newNode;
        //若插入前是空表
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    //删除指定索引处的元素
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        Node del = null;
        //若要删除的是头节点
        if (index == 0) {
            del = header;
            header = header.next;

        } else {
            Node prev = getNodeByIndex(index - 1);//获取待删除节点前的结点
            del = prev.next;//获取待删除节点
            prev.next = del.next;
            del.next = null;//将被删除的next引用置 为空

        }
        size--;
        return del.data;
    }

    //删除最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    //判断线性表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //清空线性表
    public void clear() {
        header = null;
        tail = null;
        size = 0;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next) {
                sb.append(current.data.toString() + ",");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

}
