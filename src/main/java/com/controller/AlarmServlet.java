package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.alarm.AlarmBiz;
import com.biz.alarm.AlarmBizImpl;

@WebServlet("/AlarmServlet")
public class AlarmServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    public AlarmServlet() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command=" + command);
		
		AlarmBiz biz = new AlarmBizImpl();
		
		switch (command) {
		
			case "alarmswitch":{
				
				int usernum = request.getParameter("usernum");
				int movienum = request.getParameter("movienum");
				
				boolean res = biz.AlarmSwitch(usernum, movienum);	
				
				if(res == false) { String s = "<script> alert('알람 등록에 실패하였습니다.'); </script>"; }
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				 + "alert('"+msg+"');"
				 + "location.href='"+url+"';"
				 + "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
		
	}

}
