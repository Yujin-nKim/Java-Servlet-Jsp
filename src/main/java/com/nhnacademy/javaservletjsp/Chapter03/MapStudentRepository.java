package com.nhnacademy.javaservletjsp.Chapter03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// StudentRepository 구현체
// Student를 저장할 수 있는 HashMap 기반의 저장소
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }

    @Override
    public String getStudentById(String id) {
        return null;
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
