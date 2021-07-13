package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.alarm.AlarmBizImpl;

public class AlarmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String commandString = request.getParameter("command");
	System.out.println("command="+command);
	
	AlarmBizImpl biz = new AlarmBizImpl();
	switch (command) {
	case "alarmswitch":{
		int page = Integer.parseInt(request.getParameter("page"));
		request.setAttribute("page", page);
		RequestDispatcher dispatch = request.getRequestDispatcher();
	}
	}
	}
	

}
