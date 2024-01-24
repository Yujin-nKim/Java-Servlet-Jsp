package com.nhnacademy.javaservletjsp.Chapter02.n4html_form;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Slf4j
public class FileDownloadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "/Users/yujinkimvv/Documents/NHNAcademy/lecture/WEEK3/Java-Servlet-Jsp/src/main/java/com/nhnacademy/javaservletjsp/Chapter02/n4html_form";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = req.getParameter("fileName");

        if(Objects.isNull(fileName) || fileName.isEmpty()) {
            resp.sendError(400, "fileName paramter is needed");
            return;
        }

        String filePath = UPLOAD_DIR + File.separator + fileName;
        Path path = Path.of(filePath);

        if(!Files.exists(path)) {
            log.error("File not found : " + fileName);
            resp.sendError(404, "File not found : " + fileName);
            return;
        }

        resp.setContentType("applicatoin/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try(InputStream is = Files.newInputStream(path);
            OutputStream os = resp.getOutputStream();
        ) {
            byte[] buffer = new byte[4096];

            int n;
            while(-1 != (n = is.read(buffer))) {
                os.write(buffer, 0, n);
            }
        }catch (IOException ex) {
            log.error("", ex);
        }

    }
}
