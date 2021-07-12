package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.admin.AdminBizImpl;
import com.biz.wish.WishBiz;
import com.biz.wish.WishBizImpl;
import com.dto.Paging;
import com.dto.UserDto;

@WebServlet("/WishServlet")
public class WishServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public WishServlet() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println(" [ Command ] = " + command);
		
		WishBiz biz = new WishBizImpl();
		
		switch (command) {
			
			case "list": {
				int page = Integer.parseInt(request.getParameter("page"));
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page"));
				}
				Paging paging = new Paging();
				paging.setPage(page);
				paging.setTotalCount(biz.WishCount(dto.Usernum));
				List<UserDto> list = biz.WishList(dto.Usernum);
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
				RequestDispatcher dispatch = request.getRequestDispatcher("user/WishListViewPage.jsp");
				dispatch.forward(request, response);
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
