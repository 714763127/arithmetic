package linearlist;

/*
 * 双相链表
 * */
public class DoubleLink<T> {
    private class Node<T> {
        //节点值
        private T value;
        //前一个节点
        private Node prev;
        //后一个节点
        private Node next;


        public Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }


    }

    //链表长度
    private int size;
    //头节点
    private Node<T> head;
    //尾节点
    private Node<T> tial;

    public DoubleLink() {
/**
 * 头结点不存储值 并且头结点初始化时 就一个头结点。
 * 所以头结点的前后节点都是自己
 * 并且这个链表的长度为0；
 */
        head = new Node<>(null, null, null);
        tial = new Node<>(null, null, null);
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmplty() {
        return size == 0;
    }

    /*
     * 判断索引是否超出范围
     * */
    public void checIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出索引范围");
        }
    }

    /*
     * 通过索引获取链表当中的节点
     * */
    public Node<T> getNode(int index) {
        checIndex(index);
        //当索引的值小于该链表长度一半时，那么从链表的头结点开始查找是最快的
        if (index < size / 2) {
            Node<T> cur = head.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        } else {
            Node<T> cur = tial.prev;
            int newIndex = size - (index + 1);
            for (int i = 0; i < newIndex; i++) {
                cur = cur.prev;
            }
            return cur;
        }
    }

    /*
     * 获取节点当中的值
     * */
    public T getValue(Node<T> cur) {
        return cur.value;
    }

    /*
     * 通过索引获取链表的值
     * */
    public T get(int index) {
        return getNode(index).value;
    }

    /**
     * 获取第一个节点的值
     */
    public T getFirst() {
        return getValue(getNode(0));
    }

    /**
     * 获取最后一个节点的值
     */
    public T getLast() {
        return getValue(getNode(size - 1));
    }

    /*
     * 插入节点
     * */
    public void inesert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引超出线性表范围");
        }
        //如果是空链表
        if (size == 0) {
            add(value);
        } else {
            //当index为0，即在链表头处插入
            if (index == 0) {
                addAtHead(value);
            } else {
                Node cur = getNode(index);//获取当前结点
                Node prev = getNode(index - 1);//获取前一个结点
                Node newNode = new Node(value, prev, cur);
                cur.prev = newNode;
                prev.next = newNode;
                size++;
            }

        }
    }

    //在尾部插入元素
    public void add(T value) {
        if (size == 0) {//空链表
            Node newNoed = new Node(value, head, tial);
            head.next = newNoed;
            tial.prev = newNoed;

        } else {//不是空连表
            //获取最后一个节点
            Node last = getNode(size - 1);
            Node newNoed = new Node(value, last, tial);
            last.next = newNoed;
            tial.prev = newNoed;

        }
        size++;
    }

    //在头部插入元素
    public void addAtHead(T value) {
        Node first = getNode(0);//获取第一个节点
        Node newNode = new Node(value, head, first);
        head.next = newNode;
    }

    //在按索引删除元素
    public T delete(int index) {
        checIndex(index);
        Node<T> del = null;
        //若要删除的是头节点
        if (index == 0) {
            del = head.next;
            Node second = del.next;
            head.next = second;
            second.prev = head;
            del.next = null;
        } else {
            del = getNode(index);//获取待删除节点前的结点

            Node prev = del.prev;
            Node next = del.next;

            prev.next = next;
            next.prev = prev;
            del.prev = null;
            del.next = null;//将被删除的next引用置 为空

        }
        size--;
        return del.value;
    }

    //删除最后一个元素
    public T remove() {
        return delete(size - 1);
    }

    //清空线性表
    public void clear() {
        head.next = null;
        tial.prev = null;
        size = 0;
    }

    public String toString() {
        if (isEmplty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = head.next; current != null; current = current.next) {
                sb.append(current.value.toString() + ",");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}
