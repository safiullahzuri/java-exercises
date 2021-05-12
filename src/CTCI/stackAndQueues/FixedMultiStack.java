package CTCI.stackAndQueues;

import java.util.Stack;

public class FixedMultiStack {
    private int numberOfStacks = 3;
    private int stackCapacity;
    private int[] values;
    private int[] sizes;

    public FixedMultiStack(int stackSize){
        stackCapacity = stackSize;
        values = new int[numberOfStacks*stackSize];
        sizes = new int[stackSize];
    }

    void sort(Stack<Integer> A, boolean a){
        Stack<Integer> B = new Stack<>();
        while (!A.isEmpty()){
            int temp = A.pop();
            while (!B.isEmpty() && B.peek() > temp){
                A.push(B.pop());
            }
            B.push(temp);
        }

        while (!B.isEmpty()){
            A.push(B.pop());
        }

    }

    public void sort(Stack<Integer> stack){
        Stack<Integer> sorted = new Stack<>();
        while (!stack.isEmpty()){
            int temp = stack.pop();
            while (!sorted.isEmpty() && sorted.peek() > temp){
                stack.push(sorted.pop());
            }
            sorted.push(temp);
        }
        while (!sorted.isEmpty()) stack.push(sorted.pop());
    }

    public int peek(int stackNum){
        if (isEmpty(stackNum)){
            return Integer.MIN_VALUE;
        }
        return values[indexOfTop(stackNum)];
    }

    public void push(int stackNum, int value){
        if (isFull(stackNum)){
            return;
        }
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public boolean isEmpty(int stackNum){
        return sizes[stackNum] == 0;
    }

    public boolean isFull(int stackNum){
        return sizes[stackNum] == stackCapacity;
    }

    private int indexOfTop(int stackNum){
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset+size-1;
    }



}
