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
import com.biz.wish.WishBiz;
import com.biz.wish.WishBizImpl;
import com.dto.UserDto;
import com.dto.WHDto;
import com.dto.WishListDto;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public UserServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		UserBiz biz = new UserBizImpl();
		WishBiz wishbiz = new WishBizImpl();
		ViewListBiz viewbiz = new ViewListBizImpl();
		String command = request.getParameter("command");

		switch (command) {
		
			case "loginpage":{
				
				response.sendRedirect("user/login.jsp");
				break;
			}
		
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
				int page = Integer.parseInt(request.getParameter("page"));
				
				request.setAttribute("page", page);
				
				dispatch("user/mypage.jsp",request, response);
				
				break;
			}
			case "WishListViewPage" :{
				UserDto dto = (UserDto)session.getAttribute("dto");
				List<WishListDto> list = wishbiz.WishList(dto.getUserNum());
				request.setAttribute("list", list);
				dispatch("user/WishListViewPage.jsp",request, response);
				break;
			}
			case "Update": {
				
				int userNum = Integer.parseInt(request.getParameter("userNum"));
				
				UserDto dto = biz.selectOne(userNum);
				
				request.setAttribute("dto", dto);
				dispatch("user/Update.jsp",request,response);
				break;
			}
			case "View_History" : {
				UserDto dto = (UserDto)session.getAttribute("dto");
				List<WHDto> list = viewbiz.ViewListLoading(dto.getUserNum());
				request.setAttribute("list", list);
				dispatch("user/View_History.jsp",request, response);
				
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
