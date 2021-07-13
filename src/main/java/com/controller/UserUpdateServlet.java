package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.admin.AdminBiz;
import com.biz.admin.AdminBizImpl;
import com.dto.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AdminBiz biz = new AdminBizImpl();
		String contentType = request.getContentType();
		java.util.Date time = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {
			String path = "C:\\Temp";
			int size = 1024*1024*10;
			String fileName = "";
			String originalFileName="";
			try {
				MultipartRequest multi = new MultipartRequest(request, path,size,"utf-8",new DefaultFileRenamePolicy());
				
				//전송한 전체 파일 이름들을 가져온다.
				Enumeration files = multi.getFileNames();
				String str = (String)files.nextElement();
				//파일명 중복이 발생했을 때 정책에 의해 뒤에 1,2,3 처럼 숫자가 붙어 고유 파일명을 생성한다.
		        // 이때 생성된 이름을 FilesystemName이라고 하여 그 이름 정보를 가져온다. (중복 처리)
				fileName = multi.getFilesystemName(str);
				
				//실제 파일이름 가져옴
				originalFileName = multi.getOriginalFileName(str);
				
				int usernum = Integer.parseInt(multi.getParameter("usernum"));
				UserDto dto = new UserDto();
				dto.setUserNum(usernum);
				dto.setImgURL(path+originalFileName);
				boolean res = biz.UserImgUdate(dto);
				
				if(res) {
					jsResponse("이미지 변경성공", "user.do?command=updateuser&usernum="+dto.getUserNum(), response);
				}else {
					jsResponse("이미지 변경실패", "user.do?command=updateuser&usernum="+dto.getUserNum(), response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			int usernum = Integer.parseInt(request.getParameter("usernum"));
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String nickname = request.getParameter("nickname");
			Date birth = java.sql.Date.valueOf(request.getParameter("birth"));
			
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
			
			boolean res = biz.AdminUpdateInfo(dto);
			
			if(res) {
				jsResponse("성공", "user.do?command=updateuser&usernum="+dto.getUserNum(), response);
			}else {
				jsResponse("실패", "user.do?command=updateuser&usernum="+dto.getUserNum(), response);
			}
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
