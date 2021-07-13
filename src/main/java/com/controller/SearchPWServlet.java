package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;


@WebServlet("/SearchPWServlet")
public class SearchPWServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public SearchPWServlet() {super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//////////////////////////////////////////////////////////////////
		
		String command = request.getParameter("command");
		
		System.out.println("= = = = = = = = = = = =");
		System.out.println("Command : " + command);
		System.out.println("= = = = = = = = = = = =");
		
		//////////////////////////////////////////////////////////////////
		
		if(command.contentEquals("SendCode")) {
		
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			System.out.println(id + "/" +  name + "/" + email);
			
			UserBiz biz = new UserBizImpl();
			
			boolean res = biz.SendEmailCode(id, name, email);

			if(res == false) {return;}
			
			PrintWriter out = response.getWriter();
			out.println(res);
			
			//////////////////////////////////////////////////////////////////
			
			String user = "fkwldktm@naver.com";
			String password = "!semi123";
	
			String to_email = email;
	
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", 465);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.naver.com");
			
			/////////////////////////////////////////////////////////////////
	
			StringBuffer temp = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < 10; i++) {
				int rIndex = rnd.nextInt(3);
				switch (rIndex) {
				case 0:
					temp.append((char) ((int) (rnd.nextInt(26)) + 97));
					break;
				case 1:
					temp.append((char) ((int) (rnd.nextInt(26)) + 65));
					break;
				case 2:
					temp.append((rnd.nextInt(10)));
					break;
				}
			}
			String AHKey = temp.toString();
			System.out.println("비밀번호 찾기 인증 키 : " + AHKey);
			
			////////////////////////////////////////////////////////////////
	
			Session emailsession = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			
			////////////////////////////////////////////////////////////////
	
			try {
				MimeMessage msg = new MimeMessage(emailsession);
				msg.setFrom(new InternetAddress(user));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
	
				msg.setSubject("안녕하세요. 인증 메일입니다.");
				msg.setText("비밀번호 찾기 인증 번호는 : [ " + temp + " ] 입니다.");
	
				Transport.send(msg);
				System.out.println("이메일 전송 완료");
	
			} catch (Exception e) { e.printStackTrace(); }
			
			////////////////////////////////////////////////////////////////
			
			HttpSession searchpwsec = request.getSession();	
			searchpwsec.setAttribute("AHKey", AHKey);
		}
		
		if(command.contentEquals("CodeCheck")) {
			
			String code = request.getParameter("code");
			
			HttpSession searchpwsec = request.getSession();	
			String key = (String)searchpwsec.getAttribute("AHKey");
			
			boolean res = code.equals(key)?true:false;
			
			PrintWriter out3 = response.getWriter();
			out3.println(res);
		}
		
		if(command.contentEquals("ResetPW")) {
			
			UserBiz biz = new UserBizImpl();
				
			String id = request.getParameter("UserID3");
			String email = request.getParameter("Email");
			String name = request.getParameter("Name");
			String code = request.getParameter("Code");
		
			//////////////////////////////////////////////////////////////////
			
			String user = "fkwldktm@naver.com";
			String password = "!semi123";
	
			String to_email = email;
	
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.naver.com");
			prop.put("mail.smtp.port", 465);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.ssl.enable", "true");
			prop.put("mail.smtp.ssl.trust", "smtp.naver.com");		
			
			////////////////////////////////////////////////////////////////
			
			Session emailsession = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});
			
			/////////////////////////////////////////////////////////////////
						
			StringBuffer temp = new StringBuffer();
			
			Random rnd = new Random();
			
			for (int i = 0; i < 10; i++) {	
				int rIndex = rnd.nextInt(3);		
				switch (rIndex) {
					case 0:
						temp.append((char) ((int) (rnd.nextInt(26)) + 97));
						break;
					case 1:
						temp.append((char) ((int) (rnd.nextInt(26)) + 65));
						break;
					case 2:
						temp.append((rnd.nextInt(10)));
						break;
				}
			}
			
			String NewPW = temp.toString();
			
			////////////////////////////////////////////////////////////////
	
			try {
				MimeMessage msg = new MimeMessage(emailsession);
				msg.setFrom(new InternetAddress(user));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
	
				msg.setSubject("안녕하세요. 새로운 비밀번호입니다.");
				msg.setText("새로운 비밀번호는 : [ " + NewPW + " ] 입니다. \n반드시 비밀번호를 변경해주세요.");
	
				Transport.send(msg);
	
			} catch (Exception e) { e.printStackTrace(); }	
			
			////////////////////////////////////////////////////////////////
			
			boolean lastres = biz.ChangePW(id, NewPW);
			
			if(lastres == true) {
				PrintWriter writer = response.getWriter();
				writer.println("<script>location.href='index/index.jsp'</script>");				
			} else { 
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('알 수 없는 오류가 발생했습니다.');</script>");		
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }

}
