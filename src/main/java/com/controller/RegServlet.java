package com.controller;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.dto.UserDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;

@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
   // private static final String ATTACHES_DIR = request.getRealPath("pfimg");

    public RegServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        String contentType = request.getContentType();
        UserBiz biz = new UserBizImpl();
        
        String fileName = null;
        
        if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {

            Collection<Part> parts = request.getParts();

            String id = null;
            String pw = null;
            String email = null;
            String email_1 = null;
            String email_2 = null;
            String phone = null;
            String phone_1 = null;
            String phone_2 = null;
            String phone_3= null;
            String name = null;
            String birth = null;
            String gender = null;
            String nick = null;
            String imgname = null;
            
            for (Part part : parts) {

                if (part.getHeader("Content-Disposition").contains("filename=")) {
                	            	
                    fileName = extractFileName(part.getHeader("Content-Disposition"));
                    String path = request.getRealPath("pfimg");

                    fileName = format.format(time) + fileName;
                    fileName = fileName.replaceAll(" ", "");
                    fileName = fileName.replaceAll("-", "");
                    fileName = fileName.replaceAll(":", "");
                    fileName = fileName.replaceAll("\\\\", "");                   
            		
                    if (part.getSize() > 0) {
                        part.write(path + "/" + fileName);
                        part.delete();
                    }

                    
                } else {
                    if (part.getName().contentEquals("UserID")) {
                        id = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("PW")) {
                        pw = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Email_1")) {
                        email_1 = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Email_2")) {
                        email_2 = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Phone_1")) {
                        phone_1 = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Phone_2")) {
                        phone_2 = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Phone_3")) {
                        phone_3 = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Name")) {
                        name = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Birth")) {
                        birth = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("Gender")) {
                        gender = request.getParameter(part.getName());
                    }
                    if (part.getName().contentEquals("NickName")) {
                        nick = request.getParameter(part.getName());
                    }
                }
            }

            try {
            	
                java.sql.Date d = java.sql.Date.valueOf(birth);
                if (gender.contentEquals("???")) {
                    gender = "M";
                } else {
                    gender = "W";
                }

                UserDto dto = new UserDto();
                phone = phone_1 + "-" + phone_2 + "-" + phone_3;
                email = email_1 + "@" + email_2;
 
                dto.setID(id);
                dto.setPW(pw);
                dto.setEmail(email_1 + "@" + email_2);
                dto.setPhone(phone);
                dto.setName(name);
                dto.setGender(gender);
                dto.setNickName(nick);
                dto.setImgURL("http://www.khproject.kr/OTT_Service/pfimg/" + fileName);
                dto.setBirth(d);
                
                boolean res = biz.CreateAccount(response, dto);

                if (res) {
                    jsResponse("???????????? ??????", "user/login.jsp", response);
                } else {
                    jsResponse("???????????? ??????", "user/login.jsp", response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String command = request.getParameter("command");

            System.out.println("= = = = = = = = = = = =");
            System.out.println("Command : " + command);
            System.out.println("= = = = = = = = = = = =");

            if (command.contentEquals("idcheck")) {
                String idcheck = request.getParameter("id");

                boolean res = biz.IDCheck(idcheck);

                JSONObject obj = new JSONObject();
                obj.put("check", res);

                PrintWriter out = response.getWriter();
                out.println(obj.toJSONString());
            }
            
            else if (command.contentEquals("googlereg")) {

                UserDto dto = new UserDto();
                HttpSession session = request.getSession();
                
                dto.setID((String) session.getAttribute("googleid"));
                dto.setPW((String) session.getAttribute("googlepw"));
                dto.setEmail((String) session.getAttribute("googleid"));
                dto.setName(request.getParameter("Name"));
                dto.setNickName(request.getParameter("NickName"));
                dto.setImgURL((String) session.getAttribute("googleimg"));
                
                String Phone = request.getParameter("Phone_1") + "-" + request.getParameter("Phone_2") + "-" + request.getParameter("Phone_3");
                dto.setPhone(Phone);

                String gender = request.getParameter("Gender");
                if (gender.contentEquals("???")) { gender = "M"; }
                else { gender = "W"; }              
                dto.setGender(gender);

                java.sql.Date userbirth = java.sql.Date.valueOf(request.getParameter("Birth"));
                dto.setBirth(userbirth);
                
                boolean res = biz.CreateAccount(response, dto);

                if (res) {
                    session.setAttribute("dto", dto);
                    jsResponse("???????????? ??????", "http://www.khproject.kr/OTT_Service/user/login.jsp", response);
                } else {
                   jsResponse("???????????? ??????", "http://www.khproject.kr/OTT_Service/user/login.jsp", response);
               }
            }
            
            else if (command.contentEquals("kakaoreg")) {

                UserDto dto = new UserDto();
                HttpSession session = request.getSession();

                dto.setID((String) session.getAttribute("kakaoid"));
                dto.setPW((String) session.getAttribute("kakaopw"));
                dto.setEmail((String) session.getAttribute("kakaoid"));
                dto.setName(request.getParameter("Name"));
                dto.setNickName((String) session.getAttribute("kakaonickname"));
                dto.setImgURL("http://www.khproject.kr/OTT_Service/img/default_profile.png");
                
                String Phone = request.getParameter("Phone_1") + "-" + request.getParameter("Phone_2") + "-" + request.getParameter("Phone_3");
                dto.setPhone(Phone);

                String gender = request.getParameter("Gender");
                if (gender.contentEquals("???")) {
                    gender = "M";
                } else {
                    gender = "W";
                }
                dto.setGender(gender);

                java.sql.Date userbirth = java.sql.Date.valueOf(request.getParameter("Birth"));
                dto.setBirth(userbirth);

                boolean res = biz.CreateAccount(response,dto);

                if (res) {
                    session.setAttribute("dto", dto);
                    jsResponse("???????????? ??????", "user/login.jsp", response);
                } else {
                    jsResponse("???????????? ??????", "user/login.jsp", response);
                }


            } else if (command.equals("naverreg")) {
                UserDto dto = new UserDto();
                dto.setID((String) request.getAttribute("id"));
                dto.setPW((String) request.getAttribute("pw"));
                dto.setEmail((String) request.getAttribute("id"));
                dto.setPhone(request.getParameter("phone"));
                dto.setName(request.getParameter("name"));
                dto.setNickName((String) request.getAttribute("nickname"));
                dto.setImgURL(request.getParameter("pfimg"));
                dto.setGender(request.getParameter("gender"));
                System.out.println(request.getParameter("birthday"));
                System.out.println(request.getParameter("name"));
                java.sql.Date userbirth = java.sql.Date.valueOf(request.getParameter("birthday"));
                dto.setBirth(userbirth);
                boolean res = biz.CreateAccount(response,dto);

                if (res) {
                    jsResponse("???????????? ??????", "http://www.khproject.kr/OTT_Service/user/login.jsp", response);
               } else {
                    jsResponse("???????????? ??????", "http://www.khproject.kr/OTT_Service/user/login.jsp", response);
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String extractFileName(String partHeader) {
        for (String cd : partHeader.split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                ;
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