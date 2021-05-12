package chapter24;

public class Test {

    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();

        linkedList.add(2);
        linkedList.add(4);
        linkedList.add(25);
        linkedList.add(65);
        linkedList.add(25);

        //System.out.println(linkedList.contains(66));
        //System.out.println(linkedList.indexOf(65));

        TwoWayLinkedList<Integer> twoWayLinkedList = new TwoWayLinkedList<>();
        twoWayLinkedList.add(0, 21);
        twoWayLinkedList.add(1, 32);
        twoWayLinkedList.add(2,11);
        twoWayLinkedList.add(3, 28);

        System.out.println(twoWayLinkedList.toString());
    }
}
