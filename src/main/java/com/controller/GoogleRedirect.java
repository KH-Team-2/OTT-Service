package com.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.dto.GoogleToken;

import javax.net.ssl.HttpsURLConnection;

@WebServlet("/GoogleRedirect.do")
public class GoogleRedirect extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public GoogleRedirect() { super(); }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			
			System.out.println(i + " : " + info[i]);	
		}
		
		info[7] = info[7] + info[8];
		
		HttpSession session = request.getSession();

		session.setAttribute("googlepw", info[1]);
		session.setAttribute("googleid", info[3]);
		session.setAttribute("googleimg", info[7]);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("user/socialregi.jsp");
		dispatch.forward(request, response);
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
