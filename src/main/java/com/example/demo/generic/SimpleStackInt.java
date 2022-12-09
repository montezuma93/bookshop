package com.example.demo.generic;

public class SimpleStackInt {

    private Integer[] stack;
    private int pos;

    public SimpleStackInt(int size) {
        stack = new Integer[size];
        pos = 0;
    }

    public void push(Integer o) {
        stack[pos++] = o;
    }

    public Integer pop() {
        return stack[--pos];
    }
}


