package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request;

import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter.AdminPageFilter;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter.FilterChain;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter.MyPageFilter;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter.OrderPageFilter;

public class HttpRequest {
    private final FilterChain filterChain = new FilterChain();

    public HttpRequest(){
        initFilter();
    }

    public void doRequest(Request request){
        filterChain.doFilter(request);
    }

    private void initFilter(){
        filterChain.addFilter(new MyPageFilter());
        filterChain.addFilter(new AdminPageFilter());
        filterChain.addFilter(new OrderPageFilter());
    }
}