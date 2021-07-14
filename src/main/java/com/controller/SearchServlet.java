package com.controller;

import com.biz.review.ReviewBiz;
import com.biz.review.ReviewBizImpl;
import com.biz.search.SearchBiz;
import com.biz.search.SearchBizImpl;
import com.dto.ContentsDto;
import com.dto.FBWDto;
import com.dto.Paging;
import com.dto.ReviewDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
        ReviewBiz biz1 = new ReviewBizImpl();
        String command = request.getParameter("command");
        System.out.println("command=" + command);
        switch (command) {
            case "main": {
                List<ContentsDto> newList = biz.SearchNewList();
                List<ContentsDto> popList = biz.SearchPopList();

                request.setAttribute("newList", newList);
                request.setAttribute("popList", popList);


                RequestDispatcher dispatch = request.getRequestDispatcher("index/index.jsp");
                dispatch.forward(request, response);
                break;
            }

            case "search": {
                String searchBar = request.getParameter("searchBar");
                if (searchBar.equals("") || searchBar == null) {
                    searchBar = "asd";
                }
                String startdate = request.getParameter("startdate");
                searchBar = searchBar.trim();
                String enddate = request.getParameter("enddate");
                double startgrade = Double.parseDouble(request.getParameter("startgrade"));
                double endgrade = Double.parseDouble(request.getParameter("endgrade"));
                String genre = request.getParameter("genre");
                List<FBWDto> fbwDtos = biz.SearchFBW(searchBar);
//                System.out.println(fbwDtos.get(0).getFBWords());
                if (!fbwDtos.isEmpty()) {
                    PrintWriter writer = response.getWriter();
                    writer.println("<script>alert('금지어가 입력되었습니다.'); location.href='" + "search.do?command=main" + "';</script>");
                    writer.close();

                } else {
                    List<ContentsDto> list = biz.SearchList(searchBar, startdate, enddate, startgrade, endgrade, genre);
                    searchBar = "";
                    request.setAttribute("list", list);
                    request.setAttribute("searchBar", searchBar);
                    RequestDispatcher dispatch = request.getRequestDispatcher("search/Search.jsp");
                    dispatch.forward(request, response);
                }
                break;
            }
            case "detail": {
                int page = 1;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                String title = request.getParameter("title");

                ContentsDto dto = biz.SearchDetail(title);
                int movienum = dto.getMovieNum();

                int count = biz1.RiviewCount(movienum);
                Paging paging = new Paging();
                paging.setPage(page);
                paging.setTotalCount(count);
                List<ReviewDto> list = biz1.ReviewPagingList(movienum, page);

                request.setAttribute("dto", dto);
                request.setAttribute("list", list);
                request.setAttribute("paging", paging);
                RequestDispatcher dispatch = request.getRequestDispatcher("search/SearchDetail.jsp");
                dispatch.forward(request, response);

                break;
            }

            case "logout": {
                HttpSession session = request.getSession();
                session.removeAttribute("dto");

                response.sendRedirect("../user/login.jsp");
                break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
