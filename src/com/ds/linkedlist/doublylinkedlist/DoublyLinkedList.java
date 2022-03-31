package com.ds.linkedlist.doublylinkedlist;

class Node {
    int value;
    Node prev, next; // prev: 前驱结点。  next: 后继结点。
    public Node() {}
    public Node(int value) {
        this.value = value;
        prev = null;
        next = null;
    }
}

public class DoublyLinkedList {

    Node head; // 定义头节点，头节点无索引下标，为了方便对链表操作才定义头节点。头结点的下一结点首元结点，首元结点的索引下标为0
    int size; // 用于记录双链表结点数

    public DoublyLinkedList() {
        head = new Node(0); // 头节点数据域值为0，prev、next值初始化为null
        size = 0; // 双链表结点数初始化为0
    }

    public int get(int index) {
        if (index<0 | index>=size | head.next == null) {
            return -1;
        }
        Node tempNode = head; // tempNode指向头节点，代替头节点进行操作，总不能直接让头节点移动位置吧...
        for (int i = 0; i <= index; i++) { // 循环，一直循环到待查的index处
            tempNode = tempNode.next; // tempNode移动到下一结点
        }
        return tempNode.value; // 循环完毕即查找到结点，返回其数据域的值
    }

    public void addAtHead(int val) {
        Node newNode = new Node(val);
        if (head.next == null) { // 如果只有头结点
            newNode.prev = head; // 先让新结点的前驱或后继有所指向，此处是新结点的前驱，后继初始化的时候默认是null
            head.next = newNode; // 再让新结点的前后结点指向新结点
        } else {  // 如果除了头结点还有其他结点
            newNode.next = head.next; // 先让新结点的前驱或后继有所指向，此处是新结点的后继
            newNode.prev = head; // 先让新结点的前驱或后继有所指向，此处是新结点的前驱
            head.next.prev = newNode; // 再让新结点的后一结点指向新结点
            head.next = newNode; // 再让新结点的前一结点指向新结点
        }
        size++; // 插入完毕，结点数+1
    }

    public void addAtTail(int val) {
        Node tempNode = head; // tempNode指向头节点
        for (int i = 0; i < size; i++) {
            tempNode = tempNode.next; // tempNode往前移动
        }
        Node newNode = new Node(val); // 新建结点，该结点为待插入结点，该结点将是链表的最后一个结点，该结点的next域已初始化为null
        newNode.prev = tempNode; // 新结点的前驱指向原链表最后一个结点
        tempNode.next = newNode; // 原链表最后一个结点的next域指向新结点，完成双向互指
        size++; // 双链表结点数+1
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 | index > size) return; // 可插入位置(index)为: 0 ～ size-1
        Node tempNode = head; // tempNode指向头结点
        for (int i = 0; i < index; i++) { // 循环到index前一个结点，i=index时不指向循环体语句
            tempNode = tempNode.next; // tempNode往前移动
        }
        Node newNode = new Node(val); // 新建结点，该结点为待插入结点
        newNode.prev = tempNode; // 新结点的前驱指向tempNode(index的前一结点)
        newNode.next = tempNode.next; // 新结点的后继指向tempNode的下一结点，即index位置的结点
        tempNode.next = newNode; // tempNode结点的next域指向新结点
        tempNode.next.prev = newNode; // index位置的结点的前驱指向新结点。完成后，原有index位置的结点将被新结点顶替，新结点即在index索引处
        size++; // 双链表结点数+1
    }

    public void deleteAtIndex(int index) {
        if (index<0 | index>size-1) { // 能删除结点的位置为: 0 ～ size-1
            return; // 返回值类型为void的用这个结束，下面的语句不再执行
        }
        if (index == 0) { // 如果删除的索引下标为0，即首元结点
            if (size == 1) { // 并且双链表结点数只有一个，那么说明只有首元结点这一结点
                head.next.prev = null; // 将首元结点前驱置为null
                head.next = null; // 将头节点的next域置为null
            } else { // 如果不止首元结点一个结点，即包含多个结点
                head.next.next.prev = head; // 待删结点的下一结点的前驱指向头节点
                head.next = head.next.next; // 头结点的next域指向待删结点的下一结点
            }
            size--; // 以上if-else语句执行后，都要使双链表结点数-1
            return; // 删除成功，终止，下面的语句不再执行
        }
        Node tempNode = head; // tempNode指向头节点
        for (int i = 0; i < index; i++) { // 循环到index索引的前一结点
            tempNode = tempNode.next; // tempNode往前移动
        }
        Node willDeleteNode = tempNode.next; // willDeleteNode指向待删结点，可以理解为willDeleteNode为待删结点
        if (willDeleteNode.next == null) { // 如果待删结点的next域为null，说明这个结点是双链表最后一个结点
            willDeleteNode.prev = null; // 将最后这个结点的前驱置null
            tempNode.next = null; // 待删结点的前一结点的next域置null
        } else { // 如果待删结点不是最后一个结点
            tempNode.next = willDeleteNode.next; // tempNode结点的next域指向待删结点的下一结点
            willDeleteNode.next.prev = tempNode; // 待删结点的下一结点的前驱指向待删结点的前一结点
        }
        size--; // 双链表结点数-1
    }

    public static void main(String[] args) {
        DoublyLinkedList obj = new DoublyLinkedList();
        obj.addAtHead(1); // 链表：1
        obj.addAtTail(3); // 链表：13
        obj.addAtIndex(1,2); // 链表：123
        System.out.println(obj.get(1)); // 2
        obj.deleteAtIndex(0); // 链表：23
        System.out.println(obj.get(1)); // 3
    }
}