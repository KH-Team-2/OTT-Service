package com.controller;

import com.biz.user.UserBiz;
import com.biz.user.UserBizImpl;
import com.dto.UserDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/NaverServlet")
public class NaverServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public NaverServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String clientId = "VVxEIrwhlmXUmc7eYn3S";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "7EsJSRv2JA";//애플리케이션 클라이언트 시크릿값";
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String redirectURI = URLEncoder.encode("http://khproject.kr/OTT_Service/user/login.jsp", "UTF-8");
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        System.out.println("apiURL=" + apiURL);
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.print("responseCode=" + responseCode);
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                System.out.println(res.toString());
                JSONParser parsing = new JSONParser();
                Object obj = parsing.parse(res.toString());
                JSONObject jsonObj = (JSONObject)obj;

                access_token = (String)jsonObj.get("access_token");
                refresh_token = (String)jsonObj.get("refresh_token");


            }

        } catch (Exception e) {
            System.out.println(e);
        }
        String token = access_token; // 네이버 로그인 접근 토큰;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가


        String apiURL2 = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);

        String responseBody = get(apiURL2,requestHeaders);

        JSONParser jsonParser = new JSONParser();
        Object obj = null;
        try {
            obj = jsonParser.parse(responseBody);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject object = (JSONObject) jsonObject.get("response");
        String pw = (String) object.get("id");
        String id = (String) object.get("email");
        String gender = (String) object.get("gender");
        if(gender.equals("F")) {gender = "W";}
        String phone = (String) object.get("mobile");
        String name = (String) object.get("name");
        String birth = (String) object.get("birthday");
        String birthyear = (String) object.get("birthyear");
        String birthday = birthyear + "-" + birth;
        String pfimg = (String) object.get("profile_image");
        int idx = id.indexOf("@");
        String nickName = id.substring(0, idx);
        java.sql.Date userbirth = java.sql.Date.valueOf(birthday);
        UserDto dto = new UserDto();
        dto.setBirth(userbirth);
        dto.setID(id);
        dto.setGender(gender);
        dto.setEmail(id);
        dto.setImgURL(pfimg);
        dto.setNickName(nickName);
        dto.setName(name);
        dto.setPhone(phone);
        dto.setPW(pw);
        UserBiz biz = new UserBizImpl();
        UserDto dto2 = biz.Login(id, pw);

        HttpSession session = request.getSession();

        if(dto2.getID() != null){
            session.setAttribute("dto", dto2);
            session.setMaxInactiveInterval(60*60);

            response.sendRedirect("search.do?command=main");
            return;
        }
        boolean res = biz.CreateAccount(dto);
        if (res) {
            session.setAttribute("dto", dto);
            jsResponse("회원가입 성공", "user/login.jsp", response);
        } else {
            jsResponse("회원가입 실패", "user/login.jsp", response);
        }


    }

    private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
        String s = "<script> alert('" + msg + "'); location.href='" + url + "'; </script>";

        PrintWriter out = response.getWriter();
        out.print(s);
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
