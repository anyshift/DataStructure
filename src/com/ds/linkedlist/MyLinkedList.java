package com.ds.linkedlist;

class SingleList {
    /**
     * 定义一个成员内部类Node，即结点
     * value 存放的是结点的值
     * next 存放的是下一结点的地址值
     */
    class Node {
        int value;
        Node next;
        public Node() {}
        public Node(int value) { // 定义一个带参的构造函数，用于创建value值为参数值的新结点
            this.value = value;
        }
    }

    Node head; // 定义一个头结点，该头结点是虚拟的
    int size; // size用于存放链表的结点数量

    /**
     * 默认构造函数，用于初始化链表
     */
    public SingleList() {
        head = new Node(0); // 初始化一个头节点，值域为0，指针域为null
        size = 0; // 链表结点数量默认为0，即没有数据
    }

    /**
     * 新插入结点在首元结点位置
     * @param val 欲插入的值
     */
    public void addAtHead(int val) {
        Node node = new Node(val); // 新建一个结点，该结点要插入到首元结点位置
        node.next = head.next; // 将头节点的指针域赋值给新结点的指针域
        head.next = node; // 头结点的指针域指向新结点
        size++; // 链表结点数量+1
    }

    /**
     * 根据索引下标index返回链表数据域的值
     * @param index 索引下标，index比size小一个
     * @return 对应索引结点的数据域值
     */
    public int get(int index) {
        // 给出的下标不在链表范围内以及头节点指针域为空说明没有结点，则统统返回-1
        if (index < 0 | index > size-1 | head.next == null) {
            return -1;
        }

        Node tempNode = head; // 创建一个临时结点，将其指向头结点
        for (int i=0; i<= index; i++) {
            tempNode = tempNode.next;
        }

        return tempNode.value;
    }
}

public class MyLinkedList {
    public static void main(String[] args) {
        SingleList sl = new SingleList();
        sl.addAtHead(1);
        sl.addAtHead(3);
        System.out.println(sl.get(0));
    }
}
