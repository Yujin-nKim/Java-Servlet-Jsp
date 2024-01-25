package com.nhnacademy.javaservletjsp.Chapter02.n3Listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ServletContextListener implements javax.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // 웹 애플리케이션 초기화 시 방문수 저장한 파일에서 counter 값을 읽어옴
        // 경로 : /WEB-INF/classes/counter.dat2

        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName2");
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;
        String realFilePath = servletContext.getRealPath(counterFilePath);
        log.error("path:{}", realFilePath);

        File target = new File(realFilePath);

        if(target.exists()) {
            try (
                // file 입출력 - BufferedReader, BufferedWriter 사용함
                // charset = UTF-8
                FileInputStream fileInputStream = new FileInputStream(target);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(inputStreamReader);

            ) {
                long c = Long.parseLong(br.readLine());
                // 읽어온 counter 값은 ServletContext에 속성 값으로 저장
                servletContext.setAttribute("counter", c);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        log.error("init counter : {}", servletContext.getAttribute("counter"));
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        // 웹 애플리케이션 종료 시에 counter 값을 방문수 저장 파일에 기록

        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName2");
        String counterFilePath = "/WEB-INF/classes/" + counterFileName;
        String realFilePath = servletContext.getRealPath(counterFilePath);

        try(
                FileOutputStream fileOutputStream = new FileOutputStream(realFilePath);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,StandardCharsets.UTF_8);
                BufferedWriter fw = new BufferedWriter(outputStreamWriter);
        ){
            fw.write(String.valueOf(servletContext.getAttribute("counter")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("distory-counter:" + servletContext.getAttribute("counter"));

    }
}
