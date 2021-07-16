package com.controller;

import com.biz.admin.AdminBizImpl;
import com.dto.NoticeDto;
import com.dto.Paging;
import com.dto.UserDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public NoticeServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String command = request.getParameter("command");
        System.out.println("command=" + command);
        AdminBizImpl biz = new AdminBizImpl();

        switch (command) {
            case "list": {
                int page = 1;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }

                int count = biz.CountNotice();

                Paging paging = new Paging();
                paging.setPage(page);
                paging.setTotalCount(count);



                int usernum = Integer.parseInt(request.getParameter("usernum"));

                UserDto dto = biz.UserSelect(usernum);


                List<NoticeDto> list = biz.NoticePagingList(page);

                request.setAttribute("list", list);
                request.setAttribute("usernum", usernum);
                request.setAttribute("dto", dto);
                request.setAttribute("paging", paging);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin/Notice.jsp");
                dispatcher.forward(request, response);
                break;
            }
//            case
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
