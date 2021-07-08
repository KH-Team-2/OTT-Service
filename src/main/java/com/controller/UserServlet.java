package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.dto.UserDto;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		UserBiz biz = new UserBizImpl();
		
		
		switch (command) {	
			case "login" : {
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				UserDto dto = biz.Login(id, pw);
				if( dto.getID() != null ) {
					HttpSession session = request.getSession();
					session.setAttribute("userdto",dto);
					session.setMaxInactiveInterval(60*60);
					dispatch("index/index.jsp", request, response);
				} else {
					jsResponse("로그인 실패. 아이디/비밀번호를 확인하세요.", "user/login.jsp", response);
				}
				break;
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response ) throws IOException {
		String s = "<script type='text/javascript'> alert('"+msg+"');"
				 + "location.href='"+url+"';"
				 + "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
