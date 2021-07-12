package com.controller;

import com.biz.review.ReviewBiz;
import com.biz.review.ReviewBizImpl;
import com.biz.search.SearchBiz;
import com.biz.search.SearchBizImpl;
import com.dto.ContentsDto;
import com.dto.ReviewDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ReviewServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        SearchBiz biz = new SearchBizImpl();

        ReviewBiz biz1 = new ReviewBizImpl();
        String command = request.getParameter("command");


        switch (command) {
            case "write": {
                int movienum = Integer.parseInt(request.getParameter("movienum"));
                String reviewinfo = request.getParameter("reviewinfo");
                int usernum = Integer.parseInt(request.getParameter("usernum"));

                biz1.ReviewWrite(usernum, movienum, reviewinfo);

                break;
            }
            case "detail": {
                String title = request.getParameter("title");
//                int movienum = Integer.parseInt(request.getParameter("movienum"));
                ContentsDto dto = biz.SearchDetail(title);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                int movienum = dto.getMovieNum();

                List<ReviewDto> dtos = biz1.ReviewList(movienum);

                JSONObject object = new JSONObject();
                JSONArray array = new JSONArray();
                for (int i = 0; i < dtos.size(); i++) {
                    String date = dateFormat.format(dtos.get(i).getDate());
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("reviewnum", dtos.get(i).getReviewNum());
                    jsonObject.put("usernum", dtos.get(i).getUserNum());
                    jsonObject.put("movienum", dtos.get(i).getMovieNum());
                    jsonObject.put("nickname", dtos.get(i).getNickName());
                    jsonObject.put("reviewinfo", dtos.get(i).getReviewInfo());
                    jsonObject.put("count", dtos.get(i).getCount());
                    jsonObject.put("date", date);
                    array.add(i, jsonObject);
                }
                System.out.println(array.toJSONString());
                object.put("reviewlist", array);


                PrintWriter out = response.getWriter();
                System.out.println(object.toJSONString());
                out.println(object.toJSONString());
                break;
            }
            case "updatereview": {
                String reviewinfo = request.getParameter("reviewinfo");
                int reviewnum = Integer.parseInt(request.getParameter("reviewnum"));

                ReviewDto dto = new ReviewDto(reviewnum, reviewinfo);
                System.out.println(reviewinfo + reviewnum);
                biz1.ReviewUpdate(dto);
                break;
            }
            case "deletereview": {
                int reviewnum = Integer.parseInt(request.getParameter("reviewnum"));

                biz1.ReviewDelete(reviewnum);
                break;
            }
            case "reportreview": {
                int reviewnum = Integer.parseInt(request.getParameter("reviewnum"));
                biz1.ReviewReport(reviewnum);
                break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
