package com.controller;

import com.biz.search.SearchBiz;
import com.biz.search.SearchBizImpl;
import com.dto.ContentsDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public SearchServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        SearchBiz biz = new SearchBizImpl();
        String command = request.getParameter("command");
        System.out.println("command=" + command);
        switch (command) {
            case "main":{
                RequestDispatcher dispatch = request.getRequestDispatcher("index/index.jsp");
                dispatch.forward(request, response);
                break;
            }

            case "search": {
                String searchBar = request.getParameter("searchBar");
                String startdate = request.getParameter("startdate");
                searchBar = searchBar.trim();
//                startdate = startdate.substring(0, 4);
                String enddate = request.getParameter("enddate");
//                enddate = enddate.substring(0, 4);
                double startgrade = Double.parseDouble(request.getParameter("startgrade"));
                double endgrade = Double.parseDouble(request.getParameter("endgrade"));
                String genre = request.getParameter("genre");
                request.setAttribute("searchBar", searchBar);
                /*if (genre.equals("none")) {
                    genre = "";
                }*/
                List<ContentsDto> list = biz.SearchList(searchBar, startdate, enddate, startgrade, endgrade, genre);
                System.out.println(list.get(0).getPfimgurl());
                request.setAttribute("list", list);
                RequestDispatcher dispatch = request.getRequestDispatcher("search/Search.jsp");
                dispatch.forward(request, response);

                break;
            }
            case "detail":{
                String title = request.getParameter("title");
                ContentsDto dto = biz.SearchDetail(title);

                request.setAttribute("dto", dto);
                RequestDispatcher dispatch = request.getRequestDispatcher("search/SearchDetail.jsp");
                dispatch.forward(request, response);

            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}