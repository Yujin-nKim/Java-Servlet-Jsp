package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Response;

import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Member;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.Request;

public class OrderPageResponse implements Response {
    @Override
    public void doResponse(Request request) {
        System.out.println("###### response:OrderPageResponse #####");
        Member member = (Member) request.get("member");
        System.out.println("아이디:" + member.getId());
        System.out.println("이름:" + member.getName());
        System.out.println("등급:" + Member.Role.USER);
        System.out.println("do something ... ORDER ...");
    }
}
