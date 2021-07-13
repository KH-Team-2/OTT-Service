package com.controller;

import com.biz.viewlist.ViewListBiz;
import com.biz.viewlist.ViewListBizImpl;
import com.dto.Paging;
import com.dto.WHDto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewListServlet")
public class ViewListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ViewListServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ViewListBiz biz = new ViewListBizImpl();

        String command = request.getParameter("command");
        System.out.println("command=" + command);
        switch (command) {
            case "viewlist": {
                int page = Integer.parseInt(request.getParameter("pages"));
                int usernum = Integer.parseInt(request.getParameter("usernum"));
                request.setAttribute("pages", page);
                request.setAttribute("usernum", usernum);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user/mypage.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "viewhistory": {
                int page = Integer.parseInt(request.getParameter("page"));
                int usernum = Integer.parseInt(request.getParameter("usernum"));
                int count = 0;
                count = biz.ViewListCount(usernum);

                Paging paging = new Paging();
                paging.setPage(page);
                paging.setTotalCount(count);

//                List<WHDto> list = biz.ViewListLoading(usernum);
                List<WHDto> list = biz.ViewListPaging(usernum, page);

                request.setAttribute("list", list);
                request.setAttribute("usernum", usernum);
                request.setAttribute("paging", paging);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user/View_History.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "viewlistadd": {
                int movienum = Integer.parseInt(request.getParameter("movienum"));
                int usernum = Integer.parseInt(request.getParameter("usernum"));
                boolean res = biz.ViewListAdd(movienum, usernum);
                if (res) {
                    System.out.println("성공");
                } else {
                    System.out.println("실패");
                }
                break;
            }
            case "viewdel": {
                int historynum = Integer.parseInt(request.getParameter("historynum"));

                biz.ViewListDelete(historynum);

                break;
            }
            case "chkdel": {
                String num = request.getParameter("num");
                String[] nums = num.split(",");
                for (int i = 0; i < nums.length; i++) {
                    biz.ViewListDelete(Integer.parseInt(nums[i]));
                }
                break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
