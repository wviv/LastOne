package com.wa.last.java2.reflect2.T1;

public class TestOb {

    private String a;
    private int b;

    public TestOb() {
    }

    public TestOb(String a, int b) {
        this.a = a;
        this.b = b;
    }


    public void dd() {
        System.out.println("dddd");
    }

    @Deprecated
    @Override
    public String toString() {
        return super.toString();
    }
}
