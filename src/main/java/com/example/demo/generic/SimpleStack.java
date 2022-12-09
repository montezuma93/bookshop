package com.example.demo.generic;

public class SimpleStack {
    private Object[] stack;
    private int pos;

    public SimpleStack(int size) {
        stack = new Object[size];
        pos = 0;
    }

    public void push(Object o) {
        stack[pos++] = o;
    }

    public Object pop() {
        return stack[--pos];
    }


}
