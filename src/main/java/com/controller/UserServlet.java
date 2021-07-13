package com.controller;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.biz.viewlist.ViewListBiz;
import com.biz.viewlist.ViewListBizImpl;
import com.biz.wish.WishBiz;
import com.biz.wish.WishBizImpl;
import com.dto.UserDto;
import com.dto.WHDto;
import com.dto.WishListDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
			case "userlist":{
				int page = Integer.parseInt(request.getParameter("pages"));
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				request.setAttribute("pages", page);
				request.setAttribute("usernum", usernum);
				dispatch("user/mypage.jsp",request,response);
			}
			case "wishlist" :{
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				List<WishListDto> list = wishbiz.WishList(usernum);
				request.setAttribute("usernum",usernum);
				request.setAttribute("list", list);
				dispatch("user/WishListViewPage.jsp",request,response);
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
				
				break;
			}
			case "updateuser":{
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				UserDto dto = biz.selectOne(usernum);
				request.setAttribute("dto", dto);
				dispatch("user/Update.jsp",request,response);
				break;
			}
			case "View_History" : {
				UserDto dto = (UserDto)session.getAttribute("dto");
				List<WHDto> list = viewbiz.ViewListLoading(dto.getUserNum());
				request.setAttribute("list", list);
				dispatch("user/View_History.jsp",request, response);
				
			}
			case "userSecession" : {
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				boolean result = biz.UserDel(usernum);
				System.out.println(result);
				if(result) {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('탈퇴 성공'); window.parent.location.href='user/login.jsp'"+"</script>");
				}else {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('다시 시도해주세요'); window.parent.location.href='user/login.jsp'"+"</script>");
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
