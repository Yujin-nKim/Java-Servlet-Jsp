package com.nhnacademy.javaservletjsp.Chapter03;

import java.util.List;

public interface StudentRepository {
    // 학생-등록
    void save(Student student);
    // 학생-수정
    void update(Student student);
    // 학생-삭제
    void deleteById(String id);
    // 학생-조회 by id
    String getStudentById(String id);
    // 학생-리스트
    List<Student> getStudents();
    // 학생-아이디 존재여부
    boolean existsById(String id);
}