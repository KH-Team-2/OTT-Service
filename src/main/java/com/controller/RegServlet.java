package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.dto.UserDto;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ATTACHES_DIR = "C:\\Temp";
       
    public RegServlet() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat ("yyyy-MM-dd");
		Date time = new Date();
		String contentType = request.getContentType();
		UserBiz biz = new UserBizImpl();
		
		String fileName = null;
		
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			
			Collection<Part> parts = request.getParts();

			String id = null;
			String pw = null;
			String email = null;
			String phone = null;
			String name = null;
			String birth = null;
			String gender = null;
			String nick = null;
			String imgname = null;

            for (Part part : parts) {
 
                if (part.getHeader("Content-Disposition").contains("filename=")) {
                    fileName =  extractFileName(part.getHeader("Content-Disposition"));
                    
                    fileName = format.format(time) + fileName;
                    fileName = fileName.replaceAll(" ", "");
                    fileName = fileName.replaceAll("-", "");
                    fileName = fileName.replaceAll(":", "");

                    if (part.getSize() > 0) {
                        part.write(ATTACHES_DIR + File.separator + fileName );
                        part.delete();
                    }

                    fileName = fileName + File.separator;
                }
                else {
                	if(part.getName().contentEquals("UserID")) { id=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("PW")) { pw=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("Email")) { email=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("Phone")) { phone=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("Name")) { name=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("Birth")) { birth=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("Gender")) { gender=request.getParameter(part.getName()); }
                	if(part.getName().contentEquals("NickName")) { nick=request.getParameter(part.getName()); }
                }
           }
            
            try {
            	
            	java.sql.Date d = java.sql.Date.valueOf(birth);
            	if(gender.contentEquals("남")) { gender="M"; }
     			else { gender="W"; }

            	UserDto dto = new UserDto();
            	
     			dto.setID(id);
     			dto.setPW(pw);
     			dto.setEmail(email);
     			dto.setPhone(phone);
     			dto.setName(name);
     			dto.setGender(gender);
     			dto.setNickName(nick);
     			dto.setImgURL(fileName);
     			dto.setBirth(d);
     			
     			boolean res = biz.CreateAccount(dto);
     			
     			if(res) { jsResponse("회원가입 성공", "index/index.jsp", response); }
				else { jsResponse("회원가입 실패", "index/index.jsp", response); }
     			
            } catch ( Exception e ) { e.printStackTrace(); }
        }
		else {
			String command = request.getParameter("command");
			
			System.out.println("= = = = = = = = = = = =");
			System.out.println("Command : " + command);
			System.out.println("= = = = = = = = = = = =");
			
			if(command.contentEquals("idcheck")) {
				String idcheck = request.getParameter("id");
				
				boolean res = biz.IDCheck(idcheck);
				
				JSONObject obj = new JSONObject();
				obj.put("check", res);
				
				PrintWriter out = response.getWriter();
				out.println(obj.toJSONString());
			}
			else if(command.contentEquals("googlereg")) {
				
				UserDto dto = new UserDto();
				HttpSession session = request.getSession();

				dto.setID((String)session.getAttribute("googleid"));
     			dto.setPW((String)session.getAttribute("googlepw"));
     			dto.setEmail((String)session.getAttribute("googleid"));
     			dto.setPhone(request.getParameter("Phone"));
     			dto.setName(request.getParameter("Name"));
     			dto.setNickName(request.getParameter("NickName"));
     			dto.setImgURL((String)session.getAttribute("googleimg"));
     			
     			String gender = request.getParameter("Gender");
            	if(gender.contentEquals("남")) { gender="M"; } else { gender="W"; }
     			dto.setGender(gender);
     			
     			java.sql.Date userbirth = java.sql.Date.valueOf(request.getParameter("Birth"));
     			dto.setBirth(userbirth);

     			boolean res = biz.CreateAccount(dto);
     			
     			if(res) { jsResponse("회원가입 성공", "index/index.jsp", response); }
				else { jsResponse("회원가입 실패", "index/index.jsp", response); }
			}
		}
	}        

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String extractFileName(String partHeader) {
        for (String cd : partHeader.split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf("=") +  1).trim().replace("\"", "");;
                int index = fileName.lastIndexOf(File.separator);
                return fileName.substring(index + 1);
            }
        }
        return null;
    }
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script> alert('" + msg + "'); location.href='" + url + "'; </script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
					
	}
}