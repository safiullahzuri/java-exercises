package algoexpert;

import java.util.*;

class Program {
    // This is an input class. Do not edit.
    public static class LinkedList {
        public int value;
        public LinkedList next;

        public LinkedList(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
        // Write your code here.
        LinkedList result = new LinkedList(0);
        int carry = 0;

        LinkedList nodeOne = linkedListOne;
        LinkedList nodeTwo = linkedListTwo;

        while(nodeOne != null || nodeTwo != null){
            int sum = 0;
            if(nodeOne != null) sum += nodeOne.value;
            if(nodeTwo != null) sum += nodeTwo.value;

            sum += carry;
            LinkedList newNode = new LinkedList(sum%10);
            carry = sum/10;

            result.next = newNode;
            result = newNode;

            //linkedListOne.next != null ? linkedListOne=linkedListOne.next: linkedListOne=null;
            //linkedListTwo.next != null ? linkedListTwo=linkedListTwo.next: linkedListTwo=null;

            nodeOne = (nodeOne != null) ? nodeOne.next : null;
            nodeTwo = (nodeTwo != null) ? nodeTwo.next : null;

        }

        if(carry > 0){
            result.next = new LinkedList(carry);
        }
        return result.next;
    }
}