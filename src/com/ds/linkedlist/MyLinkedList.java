package com.ds.linkedlist;

class SingleList {
    /**
     * 定义一个成员内部类Node，即结点。该类也可以写在外面
     * value - 存放的是结点的值
     * next - 存放的是下一结点的地址值
     */
    class Node {
        int value;
        Node next;
        public Node() {} // 建议显式地把无参的构造函数写出来，即使用不到
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
        head = new Node(0); // 初始化一个头节点，其没有索引下标。头节点值域为0，指针域为null
        size = 0; // 链表结点数量默认为0，即没有数据
    }

    /**
     * 在首元结点位置插入新结点，即插入位置为头结点的下一个结点处
     * @param val 欲插入的值
     */
    public void addAtHead(int val) {
        Node node = new Node(val); // 新建一个结点，该结点要插入到首元结点位置
        node.next = head.next; // 将头节点的指针域赋值给新结点的指针域
        head.next = node; // 头结点的指针域指向新结点
        size++; // 链表结点数+1
    }

    /**
     * 插入结点在尾部需要从头结点开始遍历到尾部才能插入
     * @param val 欲插入的值
     */
    public void addAtTail(int val) {
        Node tempNode = head; // 让tempNode指向头结点
        while (tempNode.next != null) { // 只要tempNode没到链表尾部，tempNode.next就不为null
            tempNode = tempNode.next; // 让tempNode一步步往后移动，直到移动到尾部为止
        }
        Node newNode = new Node(val); // 生成新结点，结点数据域到值为待插入待值
        newNode.next = null; // 虽然不赋null值也会通过new Node(val)赋为null，但还是建议加上这句
        tempNode.next = newNode; // 此时待tempNode处于链表尾部，让其next域指向新结点，完成插入动作
        size++; // 链表结点数+1
    }

    /**
     * 在任意位置前插入结点
     * @param index 插入结点位置的下标
     * @param val 插入结点的值
     */
    public void addAnywhere(int index, int val) {
        if (index<0 | index>size) { // 插入的位置为负或超出链表大小，即为错误
            return; // 返回值为void的方法用"return;"语句退出该方法，下面的语句不再执行
        }
        Node tempNode = head; //让tempNode指向头结点
        for (int i = 0; i < index; i++) { //i应该循环到index的前一个位置，i=index时不执行方法体内语句
            tempNode = tempNode.next; // tempNode一直移动到index位置到前一位置
        }
        Node newNode = new Node(val); // 生成一个新结点newNode
        newNode.next = tempNode.next; // 新结点到next指针域指向index位置(tempNode.next)的结点
        tempNode.next = newNode; // index结点的前一结点指向新结点，这时新结点即在index位置
        size++; // 链表结点数+1
    }

    /**
     * 删除index处的结点
     * @param index 待删除结点的下标
     */
    public void deleteAnywhere(int index) {
        if (index<0 | index>size-1) { // 待删除位置下标必须不小于0与比size小一个
            return; // 返回值为void的方法用"return;"语句退出该方法，下面的语句不再执行
        }
        if (index == 0) { //如果要删除的是首元结点。注意：头节点没有索引下标，'0'是首元结点的位置
            if (size == 1) { // 如果只有首元结点一个结点
                head.next = null; //头结点指针域置空
                size--; // 链表结点数-1
                return; // 返回值为void的方法用"return;"语句退出该方法，下面的语句不再执行
            } else { // 如果不止首元结点一个结点
                head.next = head.next.next; // 让头节点指针域指向首元结点的下一结点
                size--; // 链表结点数-1
                return; // 返回值为void的方法用"return;"语句退出该方法，下面的语句不再执行
            }
        }
        Node tempNode = head; //让tempNode指针指向头节点
        for (int i = 0; i < index; i++) { //i应该循环到index的前一个位置，i=index时不执行方法体内语句
            tempNode = tempNode.next; //tempNode一直移动到index位置到前一位置
        }
        Node willDeleteNode = tempNode.next; // tempNode.next为待删结点处，让willDeleteNode指向该位置
        tempNode.next = willDeleteNode.next; // 待删结点前一结点的指针域指向待删结点的下一结点
        size--; // 链表结点数-1
    }

    /**
     * 根据索引下标index返回链表数据域的值
     * @param index 索引下标，index比size小一个
     * @return 对应索引结点的数据域值
     */
    public int get(int index) {
        if (index < 0 | index > size-1 | head.next == null) {
            return -1; // 给出的下标不在链表范围内以及头节点指针域为空说明没有结点，则统统返回-1
        }
        Node tempNode = head; // 让tempNode指向头结点
        for (int i=0; i<= index; i++) { //让i循环到index位置处
            tempNode = tempNode.next; // tempNode最终指向至index位置处
        }
        return tempNode.value; // 执行完for循环，tempNode所指的就是要取的那个结点
    }
}

public class MyLinkedList {
    public static void main(String[] args) {
        SingleList sl = new SingleList();
        sl.addAtHead(1);
        sl.addAtTail(2);
        sl.addAnywhere(2,3);
        sl.addAnywhere(3,4); // 1234
        sl.deleteAnywhere(3); // 123
        System.out.println(sl.get(2)); // 3
    }
}
