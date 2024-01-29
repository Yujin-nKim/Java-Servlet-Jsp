package com.nhnacademy.javaservletjsp.Chapter03;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// StudentRepository 구현체
// Student를 저장할 수 있는 HashMap 기반의 저장소
@Slf4j
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(), student);
        System.out.println("save 실행");
    }

    @Override
    public void update(Student student) {
//        String id = student.getId();
//        Student targetStudent = getStudentById(id);
        deleteById(student.getId());
        save(student);
        System.out.println("update 실행");
    }

    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        Student student = studentsMap.get(id);
        return student;
    }

    @Override
    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();

        Iterator<String> itor = studentsMap.keySet().iterator();
        while(itor.hasNext()) {
            String key = itor.next();
            Student student = studentsMap.get(key);
            studentList.add(student);
        }
        return studentList;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }
}
