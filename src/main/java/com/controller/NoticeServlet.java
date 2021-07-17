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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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
            case "watch": {
                int noticenum = Integer.parseInt(request.getParameter("noticenum"));
                int usernum = Integer.parseInt(request.getParameter("usernum"));

                NoticeDto dto = biz.NoticeSelectOne(noticenum);

                UserDto userDto = biz.UserSelect(usernum);

                request.setAttribute("noticedto", dto);
                request.setAttribute("userdto", userDto);

                RequestDispatcher dispatcher = request.getRequestDispatcher("admin/NoticeSelect.jsp");
                dispatcher.forward(request, response);

                break;
            }
            case "update": {
                int noticenum = Integer.parseInt(request.getParameter("noticenum"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                NoticeDto dto = new NoticeDto();
                dto.setTitle(title);
                dto.setContent(content);
                dto.setNum(noticenum);

                HttpSession session = request.getSession();

                UserDto userDto = (UserDto) session.getAttribute("dto");

                boolean res = biz.NoticeUpdate(dto);

                response.sendRedirect("notice.do?command=watch&noticenum=" + noticenum + "&usernum=" + userDto.getUserNum());

                break;
            }

            case "write": {
                int usernum = Integer.parseInt(request.getParameter("usernum"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                String nickname = request.getParameter("nickname");
                NoticeDto dto = new NoticeDto();
                dto.setUsernum(usernum);
                dto.setNickname(nickname);
                dto.setTitle(title);
                dto.setContent(content);

                boolean res = biz.NoticeWrite(dto);

                if (res) {
                    jsResponse("작성 완료", "notice.do?command=list&page=1&usernum=" + usernum, response);
                } else {
                    jsResponse("작성 실패", "notice.do?command=list&page=1&usernum=" + usernum, response);
                }


                break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
        String s = "<script> alert('" + msg + "'); location.href='" + url + "'; </script>";

        PrintWriter out = response.getWriter();
        out.print(s);
    }
}
