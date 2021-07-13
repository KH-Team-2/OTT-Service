package com.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/KakaoServlet")
public class KakaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KakaoServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String code = request.getParameter("code");
		System.out.println("code: "+code);
		
		//token가져오기
		String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=528930d4a41bf7c7d8a4a5c8306eabec");
            sb.append("&redirect_uri=http://localhost:8989/kakao.do");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            accessToken = element.getAsJsonObject().get("access_token").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("accessToken : " + accessToken);
            System.out.println("refreshToken : " + refreshToken);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
        //유저 정보 받아오기
        //      요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
  	    HashMap<String, Object> userInfo = new HashMap<>();
  	    String URL = "https://kapi.kakao.com/v2/user/me";
  	    try {
  	        URL url = new URL(URL);
  	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
  	        conn.setRequestMethod("POST");
  	        
  	        //    요청에 필요한 Header에 포함될 내용
  	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
  	        
  	        int responseCode = conn.getResponseCode();
  	        System.out.println("responseCode : " + responseCode);
  	        
  	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
  	        
  	        String line = "";
  	        String result = "";
  	        
  	        while ((line = br.readLine()) != null) {
  	            result += line;
  	        }
  	        System.out.println("response body : " + result);
  	        
  	        JsonParser parser = new JsonParser();
  	        JsonElement element = parser.parse(result);
  	        
  	        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
  	        JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
  	        
  	        String nickname = properties.getAsJsonObject().get("nickname").getAsString();
  	        String email = kakao_account.getAsJsonObject().get("email").getAsString();
  	        
  	        userInfo.put("nickname", nickname);
  	        userInfo.put("email", email);
  	    } catch (IOException e) {
  	        e.printStackTrace();
  	    }
  	    
  	    System.out.println("userInfo: "+userInfo);
        
	    if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("accessToken", accessToken);
	    }
        dispatch("user/socialregi.jsp", request, response);
        
        
        
	}

	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
