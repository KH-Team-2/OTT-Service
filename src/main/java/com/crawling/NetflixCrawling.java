package com.crawling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.dto.NetflixDto;

import common.JDBCTemplate;

public class NetflixCrawling extends JDBCTemplate{
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:/Users/82102/Desktop/chromedriver_win32/chromedriver.exe"; //드라이버 경로
    
    public static void main(String[] args) {
    	NetflixCrawling c = new NetflixCrawling();
    	c.Crawling();
    }
    public void Crawling() {
    	Connection con = getConnection();
    	int count = 0;
    	List<NetflixDto> list = new ArrayList<>();
    	NetflixDto dto = null;
    	String[] urls = {"1365","7424","783","10659","31574","6548","7627","6839","5763","26835","7462","5977","8711","7077","1701","8883","1492","4370","8933","83"};
    	//드라이버 설정
    	System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
    	
    	//크롬 설정 객체 생성
    	ChromeOptions options = new ChromeOptions();
    	
    	WebDriver driver = new ChromeDriver(options);
    	Actions actions = new Actions(driver);
    	//url
    		String url = "https://www.netflix.com/kr/browse/genre/5763";
    		//i 번째 url 로 이동
    		driver.get(url);
    		
    		//브라우저 이동시 생기는 로드시간을 기다린다.
            //HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //섹션 찾고
            List<WebElement> sections = driver.findElements(By.className("nm-content-horizontal-row-item-container"));
            //콘텐츠들 url 담을 list
            List<String> conurlList = new ArrayList<String>();
            //imgurl 담을 list
            List<String> imgurlList = new ArrayList<String>();
            
            	//섹션안에 이미지태그들
	            List<WebElement> imgtags = sections.get(0).findElements(By.tagName("img"));
	            //섹션안에 콘텐츠url들
	            List<WebElement> urltags = sections.get(0).findElements(By.className("nm-collections-link"));
	            for(int i=0;i<imgtags.size();i++) {
	            	if(i==4) {
	                    WebElement mouseClick = driver.findElement(By.className("nm-content-horizontal-row-nav-icon"));
	                    mouseClick.click();
	                    try {
	                        Thread.sleep(4000);
	                    } catch (InterruptedException e) {
	        
	                    }
	
	            	}else {
	            		if(i%4==0) {
	            			List<WebElement> nextbtn = driver.findElements(By.className("nm-content-horizontal-row-nav-icon"));
	            			nextbtn.get(1).click();
	            			try {
	                            Thread.sleep(4000);
	                        } catch (InterruptedException e) {
	            
	                        }
	            		}
	            	}
	            		imgurlList.add(imgtags.get(i).getAttribute("src"));
	            		conurlList.add(urltags.get(i).getAttribute("href"));
	            }
	            
	            
            
            
            for(int i=0; i<conurlList.size();i++) {
            	dto = new NetflixDto();
            	String conurl = conurlList.get(i);
            	String MovieImg = imgurlList.get(i);
            	driver.get(conurl);
            	String title = driver.findElement(By.className("title-title")).getText();
            	String year = driver.findElement(By.className("item-year")).getText();
            	String director = dto.getDirector();
            	String actor = driver.findElement(By.className("title-info-talent")).getText();
            	String subactor = actor.substring(3);
            	double rate = Math.round(dto.getRate()*10)/10.0;
            	String genre = driver.findElement(By.cssSelector(".more-details-item-container a")).getText();
            	String summary = driver.findElement(By.className("title-info-synopsis")).getText();
            	System.out.println(conurl);
            	System.out.println(MovieImg);
            	System.out.println(title);
            	System.out.println(year);
            	System.out.println(director);
            	System.out.println(subactor);
            	System.out.println(rate);
            	System.out.println(genre);
            	System.out.println(summary);
            	dto.setTitle(title);
            	dto.setYear(year);
            	dto.setDirector(director);
            	dto.setActor(subactor);
            	dto.setRate(rate);
            	dto.setGenre(genre);
            	dto.setSummary(summary);
            	dto.setContenturl(conurl);
            	dto.setMovieImg(MovieImg);
            	try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            	
            	int result = insertMovie(con, dto);
            	if(result>0) {
            		commit(con);
            	}else {
            		rollback(con);
            	}
            }
            	close(con);
            
            
            
//           int result = insertMovie(con,list);
//           if(result > 0) {
//        	   commit(con);
//        	   System.out.println("contents에 저장했음");
//        	   
//        	   boolean res = insertPlatform(con);
//        	   System.out.println(res);
//        	   if(res) {
//        		   System.out.println("platform에 저장");
//        		   commit(con);
//        	   }else {
//        		   rollback(con);
//        	   }
//           }else {
//        	   rollback(con);
//           }
           
//           boolean delres = overlap(con);
//           
//           if(delres) {
//        	   System.out.println("중복제거완료");
//        	   commit(con);
//           }else {
//        	   rollback(con);
//           }
           
           
           
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
    private int insertMovie(Connection con, NetflixDto dto) {
    	PreparedStatement pstm = null;
    	String sql = "INSERT INTO CONTENTS VALUES"+
    					"(MOVIE_SQ.NEXTVAL,?,?,?,?,?,?,?,?,SYSDATE,?)";
    	int res = 0;
    	try {
    		pstm = con.prepareStatement(sql);
    		pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getYear());
			pstm.setString(3, dto.getDirector());
			pstm.setString(4, dto.getActor());
			pstm.setDouble(5, dto.getRate());
			pstm.setString(6, dto.getGenre());
			pstm.setString(7, dto.getSummary());
			pstm.setString(8, dto.getContenturl());
			pstm.setString(9, dto.getMovieImg());
			
			res = pstm.executeUpdate();
			
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally {
    		close(pstm);
    	}
    	
    	return res;
    }
    
    private boolean insertPlatform(Connection connection) {
        PreparedStatement preparedStatement = null;
        int res = 0;

        String platformsql = " DECLARE\n" +
                "    NUM1 NUMBER;\n" +
                "    NUM2 NUMBER;\n" +
                "BEGIN\n" +
                "    SELECT MAX(MOVIENUM) INTO NUM1 FROM CONTENTS;\n" +
                "    SELECT MAX(MOVIENUM)+1 INTO NUM2 FROM PLATFORM;\n" +
                "    IF NUM2 IS NULL THEN\n" +
                "        SELECT MIN(MOVIENUM) INTO NUM2 FROM CONTENTS;\n" +
                "    end if;\n" +
                "    for i in NUM2 .. NUM1\n" +
                "        loop\n" +
                "            insert into PLATFORM VALUES ('NF', i);\n" +
                "        end loop;\n" +
                "end; ";



        try {
            preparedStatement = connection.prepareStatement(platformsql);

            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
        }
        return res>0;
    }
    
    private boolean overlap(Connection connection) {
        PreparedStatement preparedStatement = null;
        int result = 0;

        String delplatformsql = " DELETE\n" +
                "   FROM PlatForm C\n" +
                "   WHERE C.MovieNum IN  (SELECT MovieNum\n" +
                "       FROM Contents A\n" +
                "       WHERE ROWID >\n" +
                "             (SELECT MIN(ROWID)\n" +
                "              FROM Contents B\n" +
                "              WHERE b.SUMMARY = a.SUMMARY)) ";

        String delcontentssql = " DELETE\n" +
                "   FROM Contents A\n" +
                "   WHERE ROWID > (SELECT MIN(ROWID)\n" +
                "               FROM Contents B\n" +
                "               WHERE b.SUMMARY = a.SUMMARY) ";

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
