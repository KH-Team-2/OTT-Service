package com.controller;

import com.biz.admin.AdminBiz;
import com.dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("command="+command);
		AdminBiz biz = new AdminBiz();
		if(command.equals("userlist")) {
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/adminmypage.jsp");
			dispatch.forward(request, response);
		} else if (command.equals("User_Info")) {
			List<UserDto> list = biz.AdminUserView();
			request.setAttribute("list", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/User_Info.jsp");
			dispatch.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
