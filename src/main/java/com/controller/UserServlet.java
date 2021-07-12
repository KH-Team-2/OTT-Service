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
				
				if(dto.getID() != null){
					HttpSession session = request.getSession();
					
					session.setAttribute("dto", dto);
					session.setMaxInactiveInterval(60*60);
					
					response.sendRedirect("search.do?command=main");
					
				} else {
					jsResponse("로그인 실패", "search.do?command=main", response);
				}
				
				break;
			}
			
			case "searchID": {
				String name = request.getParameter("Name");
				String email = request.getParameter("Email");
				String phone = request.getParameter("Phone");
				
				String ID = biz.SearchID(name, email, phone);

				System.out.println(ID);
				
				if( ID != null) {
					jsResponse("아이디: "+ID, "user/login.jsp", response);
				} else {
					jsResponse("일치하는 아이디가 존재하지 않습니다.", "user/IDPW.jsp",response);
				}
				
				break;
			}
		}
    }
    
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				 + "alert('"+msg+"');"
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
