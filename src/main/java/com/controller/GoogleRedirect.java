package com.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.dto.GoogleToken;
import com.dto.UserDto;

import javax.net.ssl.HttpsURLConnection;

@WebServlet("/GoogleRedirect.do")
public class GoogleRedirect extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public GoogleRedirect() { super(); }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String code = request.getParameter("code");
		String query = "code=" + code;
		
		query += "&client_id=" + "348827184821-njga1dt2kpens8d8kvj6u5kcn3h5omi2.apps.googleusercontent.com";
		query += "&client_secret=" + "hYAvQ_rof2v3Mpk19ZTl_9E9";
		query += "&redirect_uri=" + "http://localhost:8381/OTT-Service/GoogleRedirect.do";
		query += "&grant_type=authorization_code";
		
		String tokenJson = getHttpConnection("https://accounts.google.com/o/oauth2/token", query);
		
		Gson gson = new Gson();
		GoogleToken token = gson.fromJson(tokenJson, GoogleToken.class);
		
		String ret = getHttpConnection("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token.getAccess_token());
		
		String info[] = ret.split(",|:");
		
		System.out.println(ret);
		
		for(int i=0; i<info.length; i++) {
			info[i] = info[i].replace("\"","");
			info[i] = info[i].replace(" ","");
			info[i] = info[i].replace(",","");
			info[i] = info[i].replace("{","");
			info[i] = info[i].replace("}","");
			info[i] = info[i].replaceAll("(\r\n|\r|\n|\n\r)", "");	
		}
		
		info[7] = info[7] + ":" + info[8];
		
		UserBiz biz = new UserBizImpl();	
		UserDto dto = biz.Login(info[3], info[1]);
		
		HttpSession session = request.getSession();
		
		if(dto.getID() != null){
			session.setAttribute("dto", dto);
			session.setMaxInactiveInterval(60*60);
			
			response.sendRedirect("search.do?command=main");
			return;
		}

		session.setAttribute("googlepw", info[1]);
		session.setAttribute("googleid", info[3]);
		session.setAttribute("googleimg", info[7]);
		session.setAttribute("googleemail", info[3]);
		
		PrintWriter pagemove = response.getWriter();
		pagemove.println( "<script>" + "location.href='user/SocialRegi.jsp';" + "</script>" );
	}
	
	private String getHttpConnection(String uri) throws ServletException, IOException {
	
		URL url = new URL(uri);
		
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		int responseCode = conn.getResponseCode();
		
		String line;
		StringBuffer buffer = new StringBuffer();
		
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) { e.printStackTrace(); }
		return buffer.toString();
	}
	
	private String getHttpConnection(String uri, String param) throws ServletException, IOException {
		
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		try (OutputStream stream = conn.getOutputStream()) {
			try (BufferedWriter wd = new BufferedWriter(new OutputStreamWriter(stream))) {
				wd.write(param);
			}
		}
		
		int responseCode = conn.getResponseCode();
		
		String line;
		StringBuffer buffer = new StringBuffer();
		
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) { e.printStackTrace(); }
		return buffer.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
