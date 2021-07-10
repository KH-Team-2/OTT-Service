package com.controller;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UserServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		UserBiz biz = new UserBizImpl();

		String command = request.getParameter("command");

		switch (command) {
			case "login":{
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				UserDto dto = biz.Login(id, pw);
				System.out.println(dto.getUserNum());
				HttpSession session = request.getSession();
				session.setAttribute("dto", dto);

				response.sendRedirect("../search.do?command=main");
			}

		}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
