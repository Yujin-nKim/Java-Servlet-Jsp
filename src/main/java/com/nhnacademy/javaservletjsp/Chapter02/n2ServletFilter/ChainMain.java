package com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter;


import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.HttpRequest;
import com.nhnacademy.javaservletjsp.Chapter02.n2ServletFilter.Request.Request;

public class ChainMain {
    public static void main(String[] args) {

        Request myPageRequest = new Request("/mypage");
        myPageRequest.put("member",Member.createUser("marco","마르코","1234"));

        Request adminPageRequest = new Request("/admin");
        adminPageRequest.put("member",Member.createAdmin("admin","관리자","1234"));

        System.out.println("############## /mypage 요청 ############## ");
        HttpRequest httpRequest1 = new HttpRequest();
        httpRequest1.doRequest(myPageRequest);

        System.out.println("############## /admin 요청 ############## ");
        HttpRequest httpRequest2 = new HttpRequest();
        httpRequest2.doRequest(adminPageRequest);

        // 연습문제 1
        Request orderPageRequest_admin = new Request("/order");
        orderPageRequest_admin.put("member", Member.createAdmin("admin", "관리자", "1234"));

        Request orderPageRequest_user = new Request("/order");
        orderPageRequest_user.put("member", Member.createUser("user", "유저", "1234"));

        Request orderPageRequest_manager = new Request("/order");
        orderPageRequest_manager.put("member", Member.createManager("manager", "매니저", "1234"));

        Request orderPageRequest_none = new Request("/order");
        orderPageRequest_none.put("member", Member.createUncertifiedMember("none", "NONE", "1234"));

        System.out.println("############## admin의 /order 요청 결과 ############## ");
        HttpRequest httpRequest_admin = new HttpRequest();
        httpRequest_admin.doRequest(orderPageRequest_admin);

        System.out.println("############## user의 /order 요청 결과 ############## ");
        HttpRequest httpRequest_user = new HttpRequest();
        httpRequest_user.doRequest(orderPageRequest_user);

        System.out.println("############## manager의 /order 요청 결과 ############## ");
        HttpRequest httpRequest_manager = new HttpRequest();
        httpRequest_manager.doRequest(orderPageRequest_manager);

        System.out.println("############## none의 /order 요청 결과 ############## ");
        HttpRequest httpRequest_none = new HttpRequest();
        httpRequest_none.doRequest(orderPageRequest_none);

        Request mainPageRequest = new Request("/main");
        mainPageRequest.put("member", Member.createUser("user", "USER", "1234"));

        System.out.println("############## user의 /main 요청 결과 ############## ");
        HttpRequest httpRequest_user_tomain = new HttpRequest();
        httpRequest_user_tomain.doRequest(mainPageRequest);

    }
}