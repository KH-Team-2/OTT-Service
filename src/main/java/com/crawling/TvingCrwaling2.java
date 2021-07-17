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

public class TvingCrwaling2 extends JDBCTemplate{
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:\\Users\\Moon\\Desktop\\chromedriver.exe"; //드라이버 경로


    public static void main(String[] args) {
    	TvingCrwaling2 c = new TvingCrwaling2();
    	c.Crawling();
    }
    
    
    public void Crawling() {
    	Connection con = getConnection();
    	int count = 0;
    	List<TvingDto> list = new ArrayList<>();
    	TvingDto dto = null;

    	
    	System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
    	//크롬 설정 객체 생성
    	ChromeOptions options = new ChromeOptions();
    	
    	WebDriver driver = new ChromeDriver(options);
    	Actions actions = new Actions(driver);
    	
    	//url
    	String url = "https://www.tving.com/movie/genre";
    	
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
          jse.executeScript("window.scrollBy(0,5000)", "");
          try {
//              시간 딜레이(3초)
              Thread.sleep(2000);
          } catch (InterruptedException e) {
          }
      }
      //스크롤을 내린 후에 맨 위로 다시 올린다.
      jse.executeScript("window.scrollBy(0, 0)", "");
      
      try {
//        시간 딜레이(3초)
    Thread.sleep(2000);
} catch (InterruptedException e) {
}
      
      
      //섹션 찾고
      List<WebElement> sections = driver.findElements(By.className("thumb-summary"));
      //콘텐츠들 url 담을 list
      List<String> conurlList = new ArrayList<String>();
      //imgurl 담을 list
      List<String> imgurlList = new ArrayList<String>();
      
    //섹션안에 이미지태그들
      List<WebElement> imgtags = sections.get(0).findElements(By.tagName("img"));
      //섹션안에 콘텐츠url들
      List<WebElement> urltags = sections.get(0).findElements(By.className("thumb-summary"));
      
      
      
      
    }
}
