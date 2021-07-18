package com.controller;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.biz.viewlist.ViewListBiz;
import com.biz.viewlist.ViewListBizImpl;
import com.biz.wish.WishBiz;
import com.biz.wish.WishBizImpl;
import com.dto.Paging;
import com.dto.UserDto;
import com.dto.WHDto;
import com.dto.WishDto;

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
					jsResponse("로그인 실패", "user/login.jsp", response);
				}
				
				break;
			}
			
			case "searchID": {
				String name = request.getParameter("Name");
				String email_1 = request.getParameter("Email_1");
				String email_2 = request.getParameter("Email_5");
				if (email_2 == null) {
					email_2 = request.getParameter("selectEmail");
				}
				String phone_1 = request.getParameter("Phone_1");
				String phone_2 = request.getParameter("Phone_2");
				String phone_3 = request.getParameter("Phone_3");

				System.out.println(email_2);


				String email = null;
				String phone = null;

				phone = phone_1 + "-" + phone_2 + "-" + phone_3;
				email = email_1 + "@" + email_2;

				System.out.println(phone);
				System.out.println(email);
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
				int pages = Integer.parseInt(request.getParameter("pages"));
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				request.setAttribute("pages", pages);
				request.setAttribute("usernum", usernum);
				dispatch("user/mypage.jsp",request,response);
				break;
			}
			case "wishlist" :{
				int page = 1;
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page"));
				}
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				Paging paging = new Paging();
				paging.setPage(page);
				paging.setTotalCount(wishbiz.WishCount(usernum));
				System.out.println(paging.getTotalCount());
				List<WishDto> list = wishbiz.WishList(usernum,page);
				request.setAttribute("usernum",usernum);
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
				dispatch("user/WishListViewPage.jsp",request,response);
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
					session.invalidate();
					writer.println("<script>alert('탈퇴 성공'); window.parent.location.href='user/login.jsp'"+"</script>");
				}else {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('다시 시도해주세요'); location.href='user.do?command=updateuser&usernum='"+usernum+"</script>");
				}
				break;
			}
			case "wishadd":{
				int movienum = Integer.parseInt(request.getParameter("movienum"));
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				
				boolean res = wishbiz.WishAdd(usernum, movienum);
				PrintWriter writer = response.getWriter();
				if(res) {
					writer.print("성공");
				}else {
					writer.print("실패");
				}
				break;
			}
			case "wishdel":{
				int movienum = Integer.parseInt(request.getParameter("movienum"));
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				
				boolean res = wishbiz.WishDelete(usernum, movienum);
				PrintWriter writer = response.getWriter();
				if(res) {
					writer.print("성공");
				}else {
					writer.print("실패");
				}
				break;
			}
			case "wishnumdel":{
				int wishnum = Integer.parseInt(request.getParameter("wishnum"));
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				System.out.println(wishnum);
				wishbiz.WishNumDel(wishnum);
				
				break;
			}
			case "wishmuldel": {
				String usernum = request.getParameter("usernum");
            	String[] wishnum = request.getParameterValues("checkArr");

            	boolean res = wishbiz.WishMulDel(usernum, wishnum);
            	
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
