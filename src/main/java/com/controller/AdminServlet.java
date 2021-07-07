package com.controller;

import com.biz.admin.AdminBizImpl;
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
		AdminBizImpl biz = new AdminBizImpl();
		if(command.equals("userlist")) {
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/adminmypage.jsp");
			dispatch.forward(request, response);
		} else if (command.equals("User_Info")) {
			List<UserDto> list = biz.AdminUserView();
			request.setAttribute("list", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/User_Info.jsp");
			dispatch.forward(request, response);
		} else if(command.equals("adminupdate")) {
			int UserNum = Integer.parseInt(request.getParameter("UserNum"));
			UserDto dto = biz.UserSelect(UserNum);
			request.setAttribute("dto", dto);
			RequestDispatcher dispatch = request.getRequestDispatcher("admin/adminUpdate.jsp");
			dispatch.forward(request, response);
		} else if(command.equals("userUpdateform")) {
			int userNum = Integer.parseInt(request.getParameter("usernum"));
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String gender = request.getParameter("sex");
			String nickname = request.getParameter("nickname");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
