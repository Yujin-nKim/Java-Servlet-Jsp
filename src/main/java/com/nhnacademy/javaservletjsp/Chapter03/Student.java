package com.nhnacademy.javaservletjsp.Chapter03;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt;

    // ... java beans 특징을 고려하여 작성합니다.
    public Student(String id, String name, Gender gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
