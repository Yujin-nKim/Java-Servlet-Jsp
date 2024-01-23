package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter;

import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Member;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.Request;

public class OrderPageFilter implements Filter{
    @Override
    public void doFilter(Request request, FilterChain filterChain) {
        if(request.getPath().equals("/order")) {
            Member member = (Member) request.get("member");
            if(member.hasRole(Member.Role.NONE)) {
                System.out.println("path: " + request.getPath() + " member role has USER, MANAGER, ADMIN : false");
            }else {
                System.out.println("path: " + request.getPath() + " member role has USER or MANAGER or ADMIN : true");
                filterChain.doFilter(request);
            }
        }else {
            System.out.println("OrderPageFilter : 다음 필터로 넘김!");
            filterChain.doFilter(request);
        }
    }
}
