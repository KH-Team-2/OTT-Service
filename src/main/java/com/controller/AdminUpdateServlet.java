package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

import com.biz.admin.AdminBiz;
import com.biz.admin.AdminBizImpl;
import com.dto.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/AdminUpdateServlet")
public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminUpdateServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AdminBiz biz = new AdminBizImpl();
		int usernum = 0;
		Date userdate = null;
		String id = null;
		String pw = null;
		String name = null;
		Date birth = null;
		String phone = null;
		String email = null;
		String gender = null;
		String nickname= null;
		String imgurl = null;
		try {
			
		String uploadPath = "C:\\Temp";
		System.out.println(uploadPath);
			
		MultipartRequest multi = new MultipartRequest(
				
				request,uploadPath,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy()
				);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		usernum = Integer.parseInt(multi.getParameter("usernum"));
		id = multi.getParameter("id");
		pw = multi.getParameter("pw");
		name = multi.getParameter("name");
		phone = multi.getParameter("phone");
		email = multi.getParameter("email");
		gender = multi.getParameter("sex");
		nickname = multi.getParameter("nickname");
		String strbirth = multi.getParameter("birth");
		System.out.println(strbirth);
		birth = java.sql.Date.valueOf(strbirth);
		String struserdate = multi.getParameter("userdate");
		userdate = java.sql.Date.valueOf(struserdate);
		imgurl = multi.getFilesystemName("profile");
		UserDto dto = new UserDto();
		dto.setUserNum(usernum);
		dto.setID(id);
		dto.setPW(pw);
		dto.setEmail(email);
		dto.setPhone(phone);
		dto.setName(name);
		dto.setBirth(birth);
		dto.setGender(gender);
		dto.setNickName(nickname);
		dto.setImgURL(imgurl);
		
		boolean res = biz.AdminUpdateInfo(dto);
		if(res) {
			jsResponse("성공", "admin.do?command=User_Info", response);
		}else {
			jsResponse("실패", "admin.co?command=User_Info", response);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}        


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script> alert('" + msg + "'); location.href='" + url + "'; </script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
					
	}

}