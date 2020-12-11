package com.bobo.hello_spboot;


import org.junit.Test;

public class SimpleTest {

    @Test
    public void f1() {
    }

    @Test
    public void f2() {
        int count = Runtime.getRuntime().availableProcessors();     // 本机 i7 9700  8c-8t
        System.out.println("count: " + count);      // 8
    }

    @Test
    public void f3() {

    }

    @Test
    public void f4() {

    }

}
