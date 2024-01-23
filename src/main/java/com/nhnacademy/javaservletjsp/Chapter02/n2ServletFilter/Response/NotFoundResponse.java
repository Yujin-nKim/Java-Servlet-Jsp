package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Response;

import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Member;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.Request;

public class NotFoundResponse implements Response {
    @Override
    public void doResponse(Request request) {
        System.out.println("존재하지 않는 페이지!");
    }
}
