package CTCI.arrays;

import java.util.HashSet;
import java.util.Stack;

public class LinkedLists {


    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList(7);
        linkedList.next = new LinkedList(1);
        linkedList.next.next = new LinkedList(6);

        linkedList.next.next.next  = new LinkedList(2);

        //LinkedList result = addLists(linkedList, l2, 0);

        //result.printMe();
        linkedList.printMe();
        System.out.println();
        LinkedList r = reverse(linkedList);
        r.printMe();

        Result result = getTailAndSize(linkedList);
        System.out.println();
        System.out.println(result.size);

    }

    static LinkedList findBeginning(LinkedList head){
        LinkedList slow = head;
        LinkedList fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){
                break;
            }
        }

        if (fast == null || fast.next == null) return null;

        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    static LinkedList findIntersection2(LinkedList list1, LinkedList list2){

        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);

        if (result1.tail != result2.tail)return null;

        LinkedList shorter = result1.size< result2.size ? list1: list2;
        LinkedList longer = result2.size < result1.size ? list1: list2;

        //advance the longer node
        longer = getKthNode(longer, Math.abs(result1.size- result2.size));

        while (shorter != longer){
            shorter = shorter.next;
            longer = longer.next;
        }
        return longer;
    }


    static boolean isPalindrome(LinkedList head){
        LinkedList fast = head;
        LinkedList slow = head;

        Stack<Integer> stack = new Stack<>();

        while (fast != null && fast.next != null){
            stack.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null){
            slow = slow.next;
        }

        while (slow != null){
            int top = stack.pop();
            if (top != slow.value) return false;
            slow = slow.next;
        }
        return true;
    }

    static LinkedList findIntersection(LinkedList list1, LinkedList list2){
        if (list1 == null || list2 == null) return null;
        Result result1 = getTailAndSize(list1);
        Result result2 = getTailAndSize(list2);

        if (result1.tail != result2.tail){
            return null;
        }

        LinkedList shorter = result1.size< result2.size?list1:list2;
        LinkedList longer = result1.size< result2.size?list2:list1;

        longer = getKthNode(longer, Math.abs(result1.size-result2.size));

        while (shorter != longer){
            shorter = shorter.next;
            longer = longer.next;
        }
        return longer;
    }

    static Result getTailAndSize(LinkedList list){
        if (list == null) return null;
        int size = 1;

        LinkedList current = list;
        while (current.next != null){
            size++;
            current = current.next;
        }
        return new Result(current, size);
    }

    static LinkedList getKthNode(LinkedList head, int k){
        LinkedList current = head;
        while (k>0 && current != null){
            current = current.next;
            k--;
        }
        return current;
    }




    static class Result{
        public LinkedList tail;
        public int size;

        public Result(LinkedList tail, int size){
            this.tail = tail;
            this.size = size;
        }
    }

    static LinkedList reverse(LinkedList list){
        LinkedList newHead = null;

        while (list != null){
            LinkedList newNode = new LinkedList(list.value);
            newNode.next = newHead;
            newHead = newNode;
            list = list.next;
        }
        return newHead;
    }



    static void pad(LinkedList list, int padding){
        for (int i=0; i<padding; i++){
            list.insertBefore2(list, 0);
        }
    }

    static int printKthToLast(LinkedList head, int k){
        if (head == null) return 0;
        int idx = printKthToLast(head.next, k) + 1;

        if (idx == k){
            System.out.println(k+"th node is "+head.value);
        }
        return idx;
    }


    static LinkedList addLists(LinkedList l1, LinkedList l2, int carry){
        if (l1 == null && l2 == null && carry == 0){
            return null;
        }
        int value = 0;
        if (l1!=null) value+=l1.value;
        if (l2!=null) value += l2.value;

        value+=carry;
        LinkedList newNode = new LinkedList();
        newNode.value = value%10;

        if (l1 != null || l2 != null){

            LinkedList l1Next = null;
            LinkedList l2Next =null;
            if (l1 != null){
                if (l1.next != null) l1Next =l1.next;
            }

            if (l2!=null){
                if (l2.next!=null) l2Next = l2.next;
            }

            LinkedList next = addLists(l1Next, l2Next, value/10);

            newNode.next = next;
        }
        return newNode;
    }

    static boolean deleteNode(LinkedList node){
        if (node == null || node.next == null) return false;

        node.value = node.next.value;
        node.next = node.next.next;

        return true;
    }

    static LinkedList print(LinkedList head, int k){
        LinkedList p1 = head;
        LinkedList p2 = head;

        for (int i=0; i<k; i++){
            p1 = p1.next;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
return p2;
    }







    static void deleteDups(LinkedList head){
        LinkedList current = head;

        while (current != null){
            LinkedList runner = current;

            while (runner.next != null){
                if (runner.next.value == current.value) {
                    runner.next = runner.next.next;
                }else{
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }


    static void dd3(LinkedList head){
        LinkedList current = head;

        while (current != null){
            LinkedList runner = current;

            while (runner.next != null){
                if (runner.next.value == current.value){
                    runner.next = runner.next.next;
                }else {
                    runner = runner.next;
                }
            }
            current= current.next;
        }
    }







    static void deleteDuplicates2(LinkedList list){

        HashSet<Integer> set = new HashSet<>();
        LinkedList prev = null;

        while (list != null){
            if (set.contains(list.value)){
                prev.next = list.next;
            }else{
                set.add(list.value);
            }
            prev = list;
            list = list.next;
        }

    }

    static void deleteDuplicates(LinkedList list){

        HashSet<Integer> set = new HashSet<>();
        LinkedList prev = null;
        while (list != null){
            if (set.contains(list.value)){
                prev.next = prev.next.next;
            }else{
                set.add(list.value);
            }
            prev = list;
            list = list.next;
        }

    }





    static class LinkedList{
        LinkedList next;
        int value;

        public LinkedList(){}
        public LinkedList(int value){
            this.value = value;
        }

        public LinkedList insertBefore(LinkedList list, int value){
            //
            LinkedList newNode = new LinkedList(value);
            newNode.next = list;
            //list.next = newNode;
            return newNode;
        }

        public LinkedList insertBefore2(LinkedList list, int data){
            LinkedList newNode = new LinkedList(data);
            if (list != null) newNode.next = list;
            return newNode;
        }

        void printMe(){
            LinkedList current = this;
            while (current != null){
                System.out.print(current.value+" -> ");
                current = current.next;
            }
            System.out.print("null");
        }
    }
}
