package com.example.demo.generic;

public class GenericSimpleStack<T> {

    public T[] stack;
    private int pos;

    public GenericSimpleStack(int size) {
        stack = (T[]) new Object[size];
        pos = 0;
    }

    public void push(T o){
        stack[pos++] = o;
    }

    public T pop() {
        return stack[--pos];
    }


}
