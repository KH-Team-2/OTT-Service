package com.crawling;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.dto.TvingDto;

import common.JDBCTemplate;

public class TvingCrawling extends JDBCTemplate{
	
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:\\Users\\Moon\\Desktop\\opt\\chromedriver.exe"; //드라이버 경로
    
    public void Tving(){
    	Connection connection = getConnection();
    	int count = 0;
    	List<TvingDto> list = new ArrayList<>();
    	TvingDto dto = null;
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
        
        String url = "https://www.tving.com/movie/home";
        
        driver.get(url);
        //브라우저 이동시 생기는 로드시간을 기다린다.
        //HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        
//      영상을 여러 개 불러오기 위해 스크롤을 미리 i번 내린다.
      for (int i = 0; i < 1; i++) {
          jse.executeScript("window.scrollBy(0,2000)", "");
          try {
//              시간 딜레이(3초)
              Thread.sleep(2000);
          } catch (InterruptedException e) {
          }
      }
//      스크롤을 내린 후에 맨 위로 다시 올린다.
      jse.executeScript("window.scrollBy(0, 2000)", "");
      
      try {
//        시간 딜레이(3초)
    Thread.sleep(2000);
      } catch (InterruptedException e) {
      }
        
      List<WebElement> mouseImg = driver.findElements(By.className("image-cover"));
      try {
//              시간 딜레이(1초)
          Thread.sleep(1000);
      } catch (InterruptedException e) {
      }
      actions.moveToElement(mouseImg.get(0)).build().perform();
    	
//    영상 i개를 가져온다.

    for (int i = 0; i < 10; i++) {
        try {
//            시간 딜레이(3초)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    	
        
        WebElement titleimg = null;
        
        
        actions.moveToElement(mouseImg.get(i)).build().perform();
        
        count++;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        try {
//            마우스를 올리면 아래 화살표 버튼을 눌러야 영상에 대한 내용이 나온다. 그걸 클릭하기 위함이다.
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
//      img주소를 가져오기 위한 div태그이다.
      WebElement imgel = driver.findElement(By.className("thumb-summary"));
//      img주소를 가지고있는 span태그이다.
      WebElement imgurlel = mouseImg.get(i).findElement(By.className("thumb-summary"));
//      영상에 대한 정보를 가지고있는 div 태그이다.
      WebElement movieContents = driver.findElement(By.className("movie-detail-title"));

//      제목을 가지고 있는 엘리먼트이다.
      WebElement title = movieContents.findElement(By.className("movie-detail__player-title"));

      
      
      WebElement summary = movieContents.findElement(By.className("css-1yoak30"));
      List<WebElement> contents = movieContents.findElements(By.className("css-1yaqkod"));
      String imgurl = imgurlel.getAttribute("style");
      
//    style값에서 필요없는 부분을 잘라서 영상의 주소 부분만 가져온다.
    imgurl = imgurl.substring(23, imgurl.length() - 3);

    String gener = null;
    String year = null;
    String director = null;
    String actor = null;
    
    } 
    }
    

}
