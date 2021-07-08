package com.controller;

import com.biz.review.ReviewBiz;
import com.biz.review.ReviewBizImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ReviewServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ReviewBiz biz = new ReviewBizImpl();
        String command = request.getParameter("command");

        switch (command) {
            case "write":{
                int movienum = Integer.parseInt(request.getParameter("movienum"));
                String reviewinfo = request.getParameter("reviewinfo");



//                biz.ReviewWrite();

                break;
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
