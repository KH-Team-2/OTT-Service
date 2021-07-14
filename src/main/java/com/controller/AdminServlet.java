package com.controller;

import com.biz.admin.AdminBizImpl;
import com.dto.DecrationDto;
import com.dto.FBWDto;
import com.dto.Paging;
import com.dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
		switch (command) {
			case "adminlist": {
				int pages = Integer.parseInt(request.getParameter("pages"));
				request.setAttribute("pages", pages);
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/adminmypage.jsp");
				dispatch.forward(request, response);
				break;
			}
			case "User_Info": {
				int page = 1;
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page"));
				}
				Paging paging = new Paging();
				paging.setPage(page);
				paging.setTotalCount(biz.UserCount());
				List<UserDto> list = biz.AdminUserView();
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/User_Info.jsp");
				dispatch.forward(request, response);
				break;
			}
			case "adminupdate": {
				int UserNum = Integer.parseInt(request.getParameter("UserNum"));
				UserDto dto = biz.UserSelect(UserNum);
				request.setAttribute("dto", dto);
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/adminUpdate.jsp");
				dispatch.forward(request, response);
				break;
			}
			
			case "DecrationList": {
				int page = 1;
				if(request.getParameter("page")!=null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				Paging paging = new Paging();
				paging.setPage(page);
				paging.setTotalCount(biz.DecrationCount());
				List<DecrationDto> list = biz.AdminDeclarationView();
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/Decration.jsp");
				dispatch.forward(request, response);
				break;
			}
			case "FBWList": {
				int page = 1;
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page"));
				}
				Paging paging = new Paging();
				paging.setPage(page);
				paging.setTotalCount(biz.FBWCount());
				List<FBWDto> list = biz.AdminFBWView(page);
				request.setAttribute("list", list);
				request.setAttribute("paging", paging);
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/Ban_Word.jsp");
				dispatch.forward(request, response);
				break;
			}
			case "userSecession" : {
				int usernum = Integer.parseInt(request.getParameter("usernum"));
				boolean result = biz.UserSecession(usernum);
				if(result) {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('회원이 추방되었습니다'); location.href='"+"admin.do?command=User_Info';"+"</script>");
				}else {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('다시 시도해주세요'); location.href='"+"admin.do?command=User_Info';"+"</script>");
				}
				break;
			}
			case "FBWorddelete" : {
				String fbwords = request.getParameter("FBWords");
				boolean result = biz.DeleteFBW(fbwords);
				if(result) {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('금지어가 삭제되었습니다'); location.href='"+"admin.do?command=FBWList';"+"</script>");
				}else {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('다시 시도해주세요'); location.href='"+"admin.do?command=FBWList';"+"</script>");
				}
				break;
			}
			case "adminusersearch" : {
				String info = request.getParameter("info");
				List<UserDto> list = biz.AdminUserSearch(info);
				request.setAttribute("list", list);
				RequestDispatcher dispatch = request.getRequestDispatcher("admin/User_Info.jsp");
				dispatch.forward(request, response);
				break;
			}
			case "addFBWord" : {
				String FBWords = request.getParameter("FBWords");
				String reason = request.getParameter("reason");
//				FBWDto dto = new FBWDto(FBWords,reason);
//				boolean result = biz.AddFBW(dto);
//				if(result) {
//					PrintWriter writer = response.getWriter();
//					writer.println("<script>alert('금지어가 추가되었습니다'); location.href='"+"admin.do?command=FBWList';"+"</script>");
//				}else {
//					PrintWriter writer = response.getWriter();
//					writer.println("<script>alert('다시 시도해주세요'); location.href='"+"admin.do?command=FBWList';"+"</script>");
//				}
				break;
			}
			case "deleteDecration" : {
				int reviewnum = Integer.parseInt(request.getParameter("reviewnum"));
				boolean result = biz.DeleteDeclaration(reviewnum);
				if(result) {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('확인완료'); location.href='"+"admin.do?command=DecrationList';"+"</script>");
				}else {
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('확인실패'); location.href='"+"admin.do?command=DecrationList';"+"</script>");
				}
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
