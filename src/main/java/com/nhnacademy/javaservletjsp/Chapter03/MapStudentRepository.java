package com.nhnacademy.javaservletjsp.Chapter03;

import java.util.concurrent.ConcurrentHashMap;

// StudentRepository 구현체
// Student를 저장할 수 있는 HashMap 기반의 저장소
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();
}
