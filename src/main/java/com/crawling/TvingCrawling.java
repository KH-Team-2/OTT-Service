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
import com.dto.WatchaDto;

import common.JDBCTemplate;

public class TvingCrawling extends JDBCTemplate{
   
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:\\Users\\Moon\\Desktop\\chromedriver.exe"; //드라이버 경로
    
    
    public void Crwaling() {
       Connection con = getConnection();
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
        
        
        WebDriver driver = new ChromeDriver(options);
        Actions actions = new Actions(driver);
        
        String url = "https://www.tving.com/movie/genre";
       
        
        driver.get(url);
        
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
      jse.executeScript("window.scrollBy(0, 0)", "");


      try {
//              시간 딜레이(3초)
          Thread.sleep(2000);
      } catch (InterruptedException e) {
      }
      
      List<WebElement> mouseImg = driver.findElements(By.className("thumb-summary"));
      try {
//        시간 딜레이(1초)
    Thread.sleep(1000);
} catch (InterruptedException e) {
}
      
//    마우스를 맨 처음 div태그로 올린다.
    actions.moveToElement(mouseImg.get(0)).build().perform();
//  영상 i개를 가져온다.

  for (int i = 0; i < 1; i++) {
      try {
//          시간 딜레이(3초)
          Thread.sleep(1000);
      } catch (InterruptedException e) {
      }

      WebElement titleimg = null;

//      마우스 hover처리
      actions.moveToElement(mouseImg.get(i)).build().perform();
    
      count++;
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
      }
      try {
//          마우스를 올리면 아래 화살표 버튼을 눌러야 영상에 대한 내용이 나온다. 그걸 클릭하기 위함이다.
          WebElement mouseClick = driver.findElement(By.className("thumb-summary"));
          mouseClick.click();
      } catch (Exception e) {
//
      }
      
      WebElement imgurlel = mouseImg.get(i).findElement(By.className("image-cover lazy loaded"));
      
      WebElement movieContents = driver.findElement(By.className("movie-detail-title"));
      
      WebElement title = movieContents.findElement(By.className("movie-detail__player-title"));
      
//    영상의 줄거리를 가져온다.
    WebElement summary = movieContents.findElement(By.className("summary"));
//    영상의 장르, 연도, 감독, 배우를 가져온다.
    List<WebElement> contents = movieContents.findElements(By.className("info"));
    
    String gener = null;
    String year = null;
    String director = null;
    String actor = null;
    
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {
    }
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
    }
}
    
    
    }
}