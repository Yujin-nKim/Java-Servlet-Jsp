package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Filter;

import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.Request;

public interface Filter {
    void doFilter(Request request, FilterChain filterChain);
}