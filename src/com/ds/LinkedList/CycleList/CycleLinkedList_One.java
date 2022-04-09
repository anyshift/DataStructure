package com.ds.LinkedList.CycleList;

/**
 * https://leetcode-cn.com/leetbook/read/linked-list/jbex5/
 */

class ListNode {
    int value;
    ListNode next;
    public ListNode() {}
    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

/**
 * 解题思路：fastNode每次走两步，slowNode每次走一步，只要有环，尾结点的next域就不为空
 *         那么fastNode和slowNode就可以一直移动下去，直到他们相遇。
 *         只要他们相遇了，那么他们的引用地址就是一致的。
 */
public class CycleLinkedList_One {
    public boolean hasCycle(ListNode head) {

        /**
         * head头指针可能为null（数据域和指针域都不存在），因此不能单单判断head.next是否为空，不然可能NullPointerException
         */
        if (head == null || head.next == null) { // `head==null` 不能丢，不然会NullPointerException
            return false; // 头节点不存在或头节点next域为空，则不存在成环条件，返回false
        }

        ListNode fastNode = head;
        ListNode slowNode = head;

        /**
         * while循环里需要加上`fastNode != null`因为没环的情况下，fastNode一次走两步可能会走超出链表，那么fastNode就是为null
         * 调用`fastNode.next`就会触发NullPointerException异常。所以要加上`fastNode != null`
         */
        while (fastNode != null && fastNode.next != null) { // 不仅仅判断fastNode.next不为空
            slowNode = slowNode.next; // 慢结点，每次走一步
            fastNode = fastNode.next.next; // 快结点，每次走两步
            if (fastNode == slowNode) { // 之所以不比较value是怕结点内有多个相同的值，因此直接比较引用地址
                return true; // 如果地址相同，则说明快慢结点相遇了，能相遇则说明有环
            }
        }
        return false; // 没环返回false
    }
}
