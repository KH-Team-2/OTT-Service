package com.crawling;

import com.dto.WatchaDto;
import common.JDBCTemplate;
import oracle.jdbc.OracleCallableStatement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatChaCrawling extends JDBCTemplate {

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "/opt/chromedriver"; //드라이버 경로

    public void Crawling() {
        Connection connection = getConnection();
        int count = 0;
        List<WatchaDto> list = new ArrayList<>();
        WatchaDto dto = null;
        String outline = null;
        String[] array = null;


        //드라이버 설정
        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        //브라우저가 눈에 보이지 않고 내부적으로 돈다.
        //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
//        options.addArguments("headless");

        //위에서 설정한 옵션은 담은 드라이버 객체 생성
        //옵션을 설정하지 않았을 때에는 생략 가능하다.
        //WebDriver객체가 곧 하나의 브라우저 창이라 생각한다.
        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);

        //이동을 원하는 url
        String url = "https://watcha.com/explore";

//        신작 url
        String newurl = "https://watcha.com/arrivals/latest";

        //WebDriver을 해당 url로 이동한다.
        driver.get(url);

        //브라우저 이동시 생기는 로드시간을 기다린다.
        //HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        JavascriptExecutor jse = (JavascriptExecutor) driver;

//        영상을 여러 개 불러오기 위해 스크롤을 미리 i번 내린다.
        for (int i = 0; i < 3; i++) {
            jse.executeScript("window.scrollBy(0,2000)", "");
            try {
//                시간 딜레이(3초)
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
//        스크롤을 내린 후에 맨 위로 다시 올린다.
        jse.executeScript("window.scrollBy(0, 2000)", "");


        try {
//                시간 딜레이(3초)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

//        큰 div태그의 class이름을 가져와서 List에 담는다
//        List에 담는 이유는 같은 클래스 이름을 여러개 담기 위함이다
//        즉 css-bh4lon이라는 클래스는 왓챠의 이미지쪽 클래스이다.
//        여러 영상을 가져와야 하기 때문에 List에 모든 css-bh4lon을 담는다.
        List<WebElement> mouseImg = driver.findElements(By.className("e1q5rx9q0"));
        try {
//                시간 딜레이(1초)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
//        마우스를 맨 처음 div태그로 올린다.
        actions.moveToElement(mouseImg.get(0)).build().perform();


//        영상 i개를 가져온다.

        for (int i = 0; i < 5; i++) {
            try {
//                시간 딜레이(3초)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            WebElement titleimg = null;

//            마우스 hover처리
            actions.moveToElement(mouseImg.get(i)).build().perform();


            count++;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            try {
//                마우스를 올리면 아래 화살표 버튼을 눌러야 영상에 대한 내용이 나온다. 그걸 클릭하기 위함이다.
                WebElement mouseClick = driver.findElement(By.className("css-g373u1-StyledEmbedButton"));
                mouseClick.click();
            } catch (Exception e) {
//
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
//
            }
//            img주소를 가져오기 위한 div태그이다.
            WebElement imgel = driver.findElement(By.className("e1q5rx9q0"));
//            img주소를 가지고있는 span태그이다.
            WebElement imgurlel = imgel.findElement(By.className("e1q5rx9q1"));
//            영상에 대한 정보를 가지고있는 div 태그이다.
            WebElement movieContents = driver.findElement(By.className("e1la0pie8"));

//            제목을 가지고 있는 엘리먼트이다.
            WebElement title = movieContents.findElement(By.className("e1la0pie12"));
            try {
//                제목을 가지는게 h1태그가 있고 이미지파일이 있다. 이미지 파일일 경우 다르게 불러와야 하기 때문에 추가한다.
//                그러나 둘 중 하나가 없을 경우도 있기 때문에 try로 처리한다.
                titleimg = movieContents.findElement(By.className("e1la0pie13"));
            } catch (Exception e) {
//
            }
//            영상의 줄거리를 가져온다.
            WebElement summary = movieContents.findElement(By.className("css-1yoak30"));
//            영상의 장르, 연도, 감독, 배우를 가져온다.
            List<WebElement> contents = movieContents.findElements(By.className("css-1yaqkod"));
//            영상의 평점을 가져온다.
            WebElement rate = movieContents.findElement(By.className("css-le4yy9"));
//            영상 이미지의 주소를 가져온다.
//            sytle에 영상이 있기 때문에 그 영상의 sytle값을 가져온다.
            String imgurl = imgurlel.getAttribute("style");

//            style값에서 필요없는 부분을 잘라서 영상의 주소 부분만 가져온다.
            imgurl = imgurl.substring(23, imgurl.length() - 3);

            String gener = null;
            String year = null;
            String director = null;
            String actor = null;

//            감독이 있을때
            if (contents.size() == 3) {
//                감독을 가져온다.
                director = contents.get(0).getText().replace(" ", "").replace("|", " ");
//                배우를 가져온다.
                actor = contents.get(1).getText().replace(" ", "").replace("|", " ");
//                개요를 가져온다.
                outline = contents.get(2).getText().replace(" ", "").replace("|", " ");
                array = outline.split(" ");

                if (array.length == 4) {
                    gener = array[0] + array[1];
                    year = array[3];
                } else {
                    gener = array[0];
                    year = array[2];
                }

//                타이틀이 이미지가 아닐때
                if (titleimg == null) {
                    dto = new WatchaDto(1, title.getText(), year, director, actor, Double.parseDouble(rate.getText()), gener,
                            summary.getText(), imgurl);
                    list.add(dto);
//                    타이틀이 이미지일때
                } else {
                    dto = new WatchaDto(1, titleimg.getAttribute("alt"), year, director, actor, Double.parseDouble(rate.getText()), gener,
                            summary.getText(), imgurl);
                    list.add(dto);
                }
//                감독 없을 때
            } else {
                director = "  ";
                actor = contents.get(0).getText().replace(" ", "").replace("|", " ");
                outline = contents.get(1).getText().replace(" ", "").replace("|", " ");
                array = outline.split(" ");
                if (array.length == 4) {
                    gener = array[0] + array[1];
                    year = array[3];
                } else {
                    gener = array[0];
                    year = array[2];
                }
                if (titleimg == null) {
                    dto = new WatchaDto(1, title.getText(), year, "없음", actor, Double.parseDouble(rate.getText()), gener,
                            summary.getText(), imgurl);
                    list.add(dto);

                } else {
                    dto = new WatchaDto(1, titleimg.getAttribute("alt"), year, "없음", actor, Double.parseDouble(rate.getText()), gener,
                            summary.getText(), imgurl);
                    list.add(dto);
                    /*System.out.println("제목 : " + titleimg.getAttribute("alt"));
                    System.out.println("줄거리 : " + summary.getText());
                    System.out.println("감독 : " + director);
                    System.out.println("배우 : " + actor);
                    System.out.println("별점 : " + rate.getText());
                    System.out.println("장르 : " + gener);
                    System.out.println("연도 : " + year);
                    System.out.println("이미지주소 : " + imgurl);*/
                }
            }

            //1초 대기
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

//        영화 정보를 집어 넣는다.
        int result = insertMovie(connection, list);
        if (result > 0) {
            System.out.println("Contents에 저장");
//            영화 정보가 정상적으로 저장 됐을 때 플랫폼 테이블에도 저장한다.
            boolean res = insertPlatform(connection);
            if (res) {
                System.out.println("Platform 저장");
                commit(connection);
            } else {
                rollback(connection);
            }
        }
//        크롤링을 해서 가져오기 때문에 중북되는 값이 있을 수 있다.
//        그 중복되는 값들을 모두 삭제한다.
        boolean delres = overlap(connection);
        if (delres) {
            System.out.println("중복 제거 완료");
            commit(connection);
        } else {
            rollback(connection);
        }
        close(connection);
        System.out.println(count);
        

        try {
            //드라이버가 null이 아니라면
            if (driver != null) {
                //드라이버 연결 종료
                driver.close(); //드라이버 연결 해제

                //프로세스 종료
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private int insertMovie(Connection connection, List<WatchaDto> list) {
        PreparedStatement preparedStatement = null;
        String insertSql = " INSERT INTO SP_CONTENTS VALUES" +
                " (SAMPLE_MOVIE_SQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ? ) ";


        int res = 0;

        try {
            preparedStatement = connection.prepareStatement(insertSql);

            int cnt = 0;
            for (int i = 0; i < list.size(); i++) {
                WatchaDto dto = list.get(i);
                preparedStatement.setString(1, dto.getTitle());
                preparedStatement.setString(2, dto.getYear());
                preparedStatement.setString(3, dto.getDirector());
                preparedStatement.setString(4, dto.getActor());
                preparedStatement.setDouble(5, dto.getRate());
                preparedStatement.setString(6, dto.getGenre());
                preparedStatement.setString(7, dto.getSummary());
                preparedStatement.setString(8, "https://watcha.com/search?q=" + dto.getTitle());
                preparedStatement.setString(9, dto.getMovieImg());

                preparedStatement.addBatch();

                cnt++;
            }

            int[] result = preparedStatement.executeBatch();

            for (int i :
                    result) {
                if (i == -2) {
                    res++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        if (res == list.size()) {
            return res;
        } else {
            return 0;
        }

    }

    private boolean insertPlatform(Connection connection) {
        OracleCallableStatement callableStatement = null;
        boolean res = false;

        String platformsql = " DECLARE\n" +
                "    NUM1 NUMBER;\n" +
                "    NUM2 NUMBER;\n" +
                "BEGIN\n" +
                "    SELECT MAX(MOVIENUM) INTO NUM1 FROM SP_Contents;\n" +
                "    SELECT MAX(MOVIENUM) INTO NUM2 FROM SP_PLATFORM;\n" +
                "    IF NUM2 IS NULL THEN\n" +
                "    SELECT MIN(MOVIENUM) INTO NUM2 FROM SP_CONTENTS;\n\n" +
                "    end if;\n" +
                "\n" +
                "    for i in NUM2 .. NUM1\n" +
                "        loop\n" +
                "            insert into SP_PLATFORM VALUES ('WC', i);\n" +
                "        end loop;\n" +
                "end; ";



        try {
            callableStatement = (OracleCallableStatement) connection.prepareCall(platformsql);

            res = callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(callableStatement);
        }
        return res;
    }

    private boolean overlap(Connection connection) {
        PreparedStatement preparedStatement = null;
        int result = 0;

        String delplatformsql = " DELETE\n" +
                "FROM SP_PlatForm C\n" +
                "WHERE C.MovieNum IN  (SELECT MovieNum\n" +
                "       FROM SP_Contents A\n" +
                "       WHERE ROWID >\n" +
                "             (SELECT MIN(ROWID)\n" +
                "              FROM SP_Contents B\n" +
                "              WHERE b.TITLE = a.TITLE)); ";

        String delcontentssql = " DELETE\n" +
                "FROM SP_Contents A\n" +
                "WHERE ROWID > (SELECT MIN(ROWID)\n" +
                "               FROM SP_Contents B\n" +
                "               WHERE b.TITLE = a.TITLE); ";

        try {
            preparedStatement = connection.prepareStatement(delplatformsql);

            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(delcontentssql);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result > 0;
    }

    
}