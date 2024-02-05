package com.nhnacademy.javaservletjsp.Chapter04.StopWatch;

public class ArrayListTestMain {
    public static void main(String[] args) {
//        ArrayListTest arrayListTest = new ArrayListTest();
//        arrayListTest.test();
        PerformanceTestable performanceTestable = new ArrayListTest();
        ArrayListTestProxy arrayListTestProxy = new ArrayListTestProxy(performanceTestable);
        arrayListTestProxy.test();
    }
}
