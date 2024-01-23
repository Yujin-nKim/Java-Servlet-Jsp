package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter;

import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Member;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.Request;

public class MyPageFilter implements Filter {

    @Override
    public void doFilter(Request request, FilterChain filterChain) {
        if(request.getPath().equals("/mypage")){
            Member member = (Member) request.get("member");
            if(member.hasRole(Member.Role.USER)){
                System.out.println("path:" + request.getPath() + " member role has USER : true");
                filterChain.doFilter(request);
            }else{
                System.out.println("path:" + request.getPath() + " member role has USER : false");
            }
        }else{
            System.out.println("MyPageCheckFilter : 다음 필터로 넘김! ");
            // 서블릿을 호출하기 전에 처리해야하는 작업들

            //다음 filter로 넘김
            filterChain.doFilter(request);

            // 서블릿을 실행하고 나서 후 처리할때 여기다가


        }
    }
}