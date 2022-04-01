package com.ds.linkedlist.cyclelist;

/**
 * https://leetcode-cn.com/leetbook/read/linked-list/jjhf6/
 */

class List_Node {
    int value;
    List_Node next;
    public List_Node() {}
    public List_Node(int val) {
        this.value = val;
        this.next = null;
    }
}

/**
 * 解题思路：快结点一次走两步，慢结点一次走一步，如果有环，那么快慢结点终将相遇。
 *         第一次相遇后，让快结点或慢结点任一结点指向头节点，此解题过程以慢结点指向头节点为例，然后快慢结点只有引用地址不相等，就每次各自移动一步
 *         直到他们的引用地址相同，也就是第二次相遇时，相遇的那个位置就是环的第一个结点。
 */

public class CycleLinkedList_Two {
    public List_Node detectCycle(List_Node head) {

        /**
         * head头指针可能为null（数据域和指针域都不存在），因此不能单单判断head.next是否为空，不然可能NullPointerException
         */
        if (head == null || head.next == null) {
            return null;
        }

        List_Node fastNode = head;
        List_Node slowNode = head;

        /**
         * 只要fastNode结点有地址非空，就让快慢结点都往下走。如果fastNode走的位置使得fastNode为null了，那么就不走了，说明没环
         */
        while (fastNode != null && fastNode.next != null) {
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode) {
                break; // 第一次相遇后退出循环，不再往下走
            }
        }

        /**
         * if判断需要加上`fastNode != null`因为没环的情况下，fastNode一次走两步可能会走超出链表，那么fastNode就是为null
         * 调用`fastNode.next`就会触发NullPointerException异常。所以要加上`fastNode != null`
         */
        if (fastNode == null || fastNode.next == null) {
            return null; // 如果while循环完毕后fastNode结点为null，说明没有环（也就是fastNode超出了链表，不满足while条件，需要在此处处理）
        }

        slowNode = head; // 让慢结点指向头节点
        while (fastNode != slowNode) { // 快慢结点重新往前走，只要再次相遇就不往前走
            fastNode = fastNode.next; // 走一步
            slowNode = slowNode.next; // 走一步
        }
        return fastNode; // 返回快慢结点中的任一个，因为此时快慢结点都指向同一位置，这个位置就是入环的第一个结点
    }
}
