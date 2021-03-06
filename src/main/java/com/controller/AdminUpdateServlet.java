package com.controller;

import com.biz.admin.AdminBiz;
import com.biz.admin.AdminBizImpl;
import com.dto.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

@WebServlet("/AdminUpdateServlet")
public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminUpdateServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    PrintWriter out = response.getWriter();
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		AdminBiz biz = new AdminBizImpl();
		String contentType = request.getContentType();
		java.util.Date time = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

		
		if (contentType != null &&  contentType.toLowerCase().startsWith("multipart/")) {

			String path = request.getRealPath("pfimg");
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
				dto.setImgURL("http://www.khproject.kr/OTT_Service/pfimg/"+originalFileName);
				boolean res = biz.UserImgUdate(dto);
				
				if(res) {
					jsResponse("이미지 변경성공", "admin.do?command=User_Info", response);
				}else {
					jsResponse("이미지 변경실패", "admin.do?command=User_Info", response);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			int usernum = Integer.parseInt(request.getParameter("usernum"));
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String nickname = request.getParameter("nickname");
			String grade = request.getParameter("grade");
			Date birth = java.sql.Date.valueOf(request.getParameter("birth"));

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
			dto.setGrade(grade);

			boolean res = biz.AdminUpdateInfo(dto);
			
			if(res) {
				jsResponse("성공", "admin.do?command=User_Info&page=1", response);
			}else {
				jsResponse("실패", "admin.do?command=User_Info&page=1", response);
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