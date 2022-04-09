package com.ds.LinkedList.ReverseLinkedList;

/**
 * https://leetcode-cn.com/leetbook/read/linked-list/f58sg/
 */

class ListNode {
    int value;
    ListNode next;
    public ListNode() {}
    public ListNode(int value) {
        this.value = value;
    }
    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }
}

public class ReverseLinkedList {
    /**
     * 方法一：双链表，一条链表从头节点开始删，删除的结点加入另一条初始为空的链表，循环执行
     * @param head 不带头节点的链表
     * @return 逆序后的链表
     */
    public ListNode reverseList_1(ListNode head) {
        ListNode newList = null; // 新建一个空结点
        while (head != null) { //只有判断head不为空就行，head.next可以为空
            ListNode temp = head.next; // temp结点指向首元结点的下一结点，首元结点待会要指向新链表处
            head.next = newList; // 首元结点的next域指向首元结点头部
            newList = head; // 新链表的头指针移动到链表头部，使newList保持头结点的位置
            head = temp; // 原链表的头节点为temp所指向的那个结点
        }
        return newList; // while循环完毕后，newList能够组建成一条逆排序后的新链表，返回newList就行
    }

    /**
     * 方法二：同方法一，不过该方法是带头节点的方法
     * @param head 带头节点的链表
     * @return 逆序后的链表
     */
    public ListNode reverseList_2(ListNode head) {
        ListNode newList = null; // 新建一个空节点
        while (head != null && head.next != null) {
            if (head.next.next != null) { // 如果不是最后一个结点
                ListNode temp = head.next.next; // temp结点指向首元结点的下一结点，首元结点待会要指向新链表处
                head.next.next = newList; // 头结点的下一结点的next域指向新链表的头部
                newList = head.next; // 新链表的头结点移动到新链表头部，使newList保持头结点的位置
                head.next = temp; // 原链表的头结点的next域指向temp（head.next.next）
            } else { // 如果原链表仅剩最后一个结点
                head.next.next = newList; // 最后一个结点的next域指向新链表newList的头结点
                newList = head.next; // 新链表的头结点移动到新链表头部，使newList保持头结点的位置
                head.next = null; // 原链表没有元素了，只剩头结点，置空其头结点的next域
            }
        }
        return newList; // while循环完毕后，newList能够组建成一条逆排序后的新链表，返回newList就行
    }

    public static void main(String[] args) {
        ReverseLinkedList rll = new ReverseLinkedList();
        ListNode L5 = new ListNode(5);
        ListNode L4 = new ListNode(4,L5);
        ListNode L3 = new ListNode(3,L4);
        ListNode L2 = new ListNode(2,L3);
        ListNode L1 = new ListNode(1,L2);
        ListNode L0 = new ListNode(0,L1); // 用于带头结点测试

        /* 用于输出ListNode未逆排序的数值
            int[] notReversedList = new int[5];
            for (int i = 0; i < 5; i++) {
                notReversedList[i] = L1.value;
                if (L1.next != null) {
                    L1 = L1.next;
                }
            }
            for (int i = 0; i < 5; i++) {
                System.out.print(notReversedList[i]);
            }
            System.out.println();
        */

        ListNode listNode_Head = rll.reverseList_2(L0); // 带头结点
        ListNode listNode_noHead = rll.reverseList_1(L1); // 不带头结点

        int[] reversedList = new int[5];
        for (int i = 0; i < 5; i++) {
            reversedList[i] = listNode_Head.value;
            if (listNode_Head.next != null) {
                listNode_Head = listNode_Head.next;
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(reversedList[i]);
        }
    }
}
