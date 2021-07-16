package com.crawling;

import com.dto.ContentsDto;
import com.dto.WatchaDto;
import common.JDBCTemplate;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class WavveCrawling extends JDBCTemplate {

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "C:/Users/LazRuby/Desktop/Files/chromedriver.exe";

    public void Crawling() {
    	
    	try { System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH); }
        catch (Exception e) { e.printStackTrace(); }

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        String url = "https://www.wavve.com/member/login?referer=%2Findex.html";
        driver.get(url);
        
        //////////////////////////////////////////////////////////////////

    	Connection connection = getConnection();
        List<ContentsDto> list = new ArrayList<>();
        Actions actions = new Actions(driver);
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        wait(1000);
        
        //////////////////////////////////////////////////////////////////
        
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div[1]/form/ul[1]/li[1]/input")).sendKeys("khsemi");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div[1]/form/ul[1]/li[2]/input")).sendKeys("!khproject123");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[3]/div/div[1]/form/div/a")).click();
        
        wait(2000);
        
        driver.findElement(By.xpath("//*[@id=\"notice-popup-container\"]/div[2]/a/img")).click();
        
        wait(1500);
           
        for(int j=1; j<11; j++) {
        	
        	url = "https://www.wavve.com/list/VN4?api=apis.wavve.com%252Fcf%252Fvod%252Fpopularcontents%253Forderby%253Dviewtime%2526contenttype%253Dvod%2526genre%253D01%2526WeekDay%253Dall%2526uitype%253DVN4%2526uiparent%253DGN56-VN4%2526uirank%253D2%2526broadcastid%253D126147%2526offset%253D0%2526limit%253D20%2526uicode%253DVN4&came=BandViewGnbCode&page=" + j;
            driver.get(url);
            
            wait(1500);
            
            System.out.println();

        	for(int i=1; i<21; i++) {
        		   
            	java.sql.Date temp_day=  java.sql.Date .valueOf("2020-02-2");
            	ContentsDto dto = new ContentsDto(0, "미제공", "미제공", "미제공", "미제공", 5.0, "미제공", "미제공", "미제공", temp_day, "미제공", "미제공" );
        		
            	String imgurl = driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[2]/div[" + i + "]/a/div[1]/img")).getAttribute("src");
            	driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[2]/div[" + i + "]/a/div[2]")).click();
            	
            	wait(1500);
            	
            	try {
            		if( driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/button[2]")).getText().length() >= 1 ) {
            			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/button[2]")).click();
            		}
            	} catch ( Exception e ) {}
            	
            	wait(1500);
            	
            	driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/button ")).click();
            	
            	wait(500);
            	
            	dto.setTitle( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/h2/span")  );

            	System.out.println(dto.getTitle());
            	
            	WebElement detail_p = driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p"));
            	
            	for ( int z=1; z < detail_p.length(); z++ ) {
            		
            	}
            		
            	System.out.println( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/p") );
            	System.out.println( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div") );
            	System.out.println(imgurl);
            	System.out.println(driver.getCurrentUrl());
            	System.out.println("= = = = = = = = = = = = = = = = = =");
            	System.out.println();
            	
            	wait(1000);
            	
            	driver.navigate().back();
            	wait(1500);
            }
        	
        }

      //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[1]
      //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[1]/span[1]
      //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[1]/span[2]
        
      //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[2]
      //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[2]/span[1]
      //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[2]/span[2]

        
        //try { Thread.sleep(1000); } catch (InterruptedException e) {}
        
        
        //actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div/div[2]/div/ul[1]/li[2]/div[1]/div/ul/li[1]/a/div/span")));
        //driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div/div[2]/div/ul[1]/li[2]/div[1]/div/ul/li[1]/a/div/span")).click();
       
        //List<WebElement> a_loc = driver.findElements(By.tagName("a"));
      //*[@id="app"]/div[1]/div[2]/div/div[2]/div/ul[1]/li[2]/div[1]/div/ul/li[2]/a/div/span
     
        /*
//        영상 i개를 가져온다.

        for (int i = 0; i < 10; i++) {
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
            WebElement imgurlel = mouseImg.get(i).findElement(By.className("e1q5rx9q1"));
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
                    System.out.println("이미지주소 : " + imgurl);
                }
            }
            System.out.println(list.get(i).getTitle());

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
            commit(connection);
            System.out.println("Contents에 저장");
//            영화 정보가 정상적으로 저장 됐을 때 플랫폼 테이블에도 저장한다.
            boolean res = insertPlatform(connection);
            System.out.println(res);
            if (res) {
                System.out.println("Platform 저장");
                commit(connection);
            } else {
                rollback(connection);
            }
        } else {
            rollback(connection);
        }
//        크롤링을 해서 가져오기 때문에 중북되는 값이 있을 수 있다.
//        그 중복되는 값들을 모두 삭제한다.
        boolean delres = overlap(connection);
        System.out.println(delres);
        if (delres) {
            System.out.println("중복 제거 완료");
            commit(connection);
        } else {
            rollback(connection);
        }
        close(connection);
        System.out.println(count);
        */

        //////////////////////////////////////////////////////////////////////
        
        //try { if (driver != null) { driver.close(); driver.quit(); } }
        //catch (Exception e) { throw new RuntimeException(e.getMessage()); }    
    }
    
    static public void wait(int count) { try { Thread.sleep(count); } catch (InterruptedException e) {} }
    
    static public String getText(WebDriver driver, String url) { 
    	
    	String text = "";
    	
    	try { text = driver.findElement(By.xpath(url)).getText(); } catch ( Exception e ) {}
    	
    	return text;
    }
    
    static public ContentsDto setDto(WebDriver driver, ContentsDto Dto) {
    	
    	try {
    		
    	} catch ( Exception e ) {}
    	
    	return Dto;
    }
    
    //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[1]
    //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[1]/span[1]
    //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[1]/span[2]
      
    //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[2]
    //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[2]/span[1]
    //*[@id="g-contents"]/div[1]/div/div[2]/div[3]/div/p[2]/span[2]
}