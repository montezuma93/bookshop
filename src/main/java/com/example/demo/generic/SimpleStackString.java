package com.example.demo.generic;

public class SimpleStackString {

    private String[] stack;
    private int pos;

    public SimpleStackString(int size) {
        stack = new String[size];
        pos = 0;
    }

    public void push(String o) {
        stack[pos++] = o;
    }

    public String pop() {
        return stack[--pos];
    }
}
