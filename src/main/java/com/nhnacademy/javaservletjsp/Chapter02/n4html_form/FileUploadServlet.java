package com.nhnacademy.javaservletjsp.Chapter02.n4html_form;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig(
        // 파일을 메모리에 저장할지 디스크에 저장할지 결정하는 임계값 설정함
        // 파일 크기가 이 값보다 작으면 메모리에 저장되고, 이 값을 초과하면 디스크에 저장됨
        // 기본값은 0이며, 모든 파일이 디스크에 저장됨
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        // 업로드 파일의 최대 크기 지정
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        // 요청의 최대 크기 지정
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        // 업로드 파일이 지정되는 디렉토리 지정
        location = "/Users/yujinkimvv/Documents/NHNAcademy/lecture/WEEK3/Java-Servlet-Jsp/src/main/java/com/nhnacademy/javaservletjsp/Chapter02/n4html_form/"
)
@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/Users/yujinkimvv/Documents/NHNAcademy/lecture/WEEK3/Java-Servlet-Jsp/src/main/java/com/nhnacademy/javaservletjsp/Chapter02/n4html_form/";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for(Part part : req.getParts()){
            String contentDisposition = part.getHeader(CONTENT_DISPOSITION);

            if (contentDisposition.contains("filename=")) {
                String fileName = extractFileName(contentDisposition);

                if (part.getSize() > 0) {
                    part.write(UPLOAD_DIR + File.separator + fileName);
                    part.delete();
                }
            } else {
                String formValue = req.getParameter(part.getName());
                log.error("{}={}", part.getName(), formValue);
            }
        }
        resp.sendRedirect("/");
    }

    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}",contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}
