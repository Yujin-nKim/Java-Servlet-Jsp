package com.nhnacademy.javaservletjsp.Chapter03;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.Random;

@Slf4j
@WebListener
public class WebApplicationListener implements ServletContextListener {
    // application 구동시 student 1 ~ 10 학생 등록
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        String[][] dummy_data = {
                {"남예준", "M"},
                {"한노아", "M"},
                {"채봉구", "M"},
                {"도은호", "M"},
                {"유하민", "M"},
                {"김유진", "F"},
                {"채밤비", "F"},
                {"김냠밤", "F"},
                {"홍길동", "F"},
                {"김도도", "F"}
        };

        for(int i = 1; i <= 10; i++) {
            Random random = new Random();
            Student student = new Student("student"+i, dummy_data[i-1][0], Gender.valueOf(dummy_data[i-1][1]), random.nextInt(20)+10);
            studentRepository.save(student);
        }

        // application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
        log.info("Listener | StudentRepository 초기화 완료");
    }
}
