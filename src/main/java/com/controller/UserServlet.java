package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.biz.viewlist.ViewListBiz;
import com.biz.viewlist.ViewListBizImpl;
import com.dto.UserDto;
import com.dto.WHDto;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UserServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		UserBiz biz = new UserBizImpl();
		ViewListBiz viewlistbiz = new ViewListBizImpl();
		String command = request.getParameter("command");
		HttpSession session = request.getSession();

		switch (command) {
			case "login":{
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				
				UserDto dto = biz.Login(id, pw);
				
				System.out.println(dto.getUserNum());
				
				if(dto.getID() != null){
					session.setAttribute("dto", dto);
					session.setMaxInactiveInterval(60*60);
					
					response.sendRedirect("../search.do?command=main");
					
				} else {
					jsResponse("로그인 실패", "../search.do?command=main", response);
				}
				
				
			}
			case "userlist": {
				int page = Integer.parseInt(request.getParameter("pages"));
				
				request.setAttribute("page", page);
				
				dispatch("user/mypage.jsp",request, response);
				
				break;
			}
			case "View_History":{
				UserDto dto = (UserDto)session.getAttribute("dto");
				List<WHDto> list = viewlistbiz.ViewListLoading(dto.getUserNum());
				request.setAttribute("list", list);
				dispatch("user/View_History.jsp", request, response);
				break;
			}
			case "Update":{
				UserDto dto = (UserDto)session.getAttribute("dto");
				request.setAttribute("dto", dto);
				dispatch("user/Update.jsp", request, response);
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
