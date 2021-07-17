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

import com.dto.ContentsDto;
import com.dto.TvingDto;

import common.JDBCTemplate;

public class TvingCrwaling2 extends JDBCTemplate{
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
    public static final String WEB_DRIVER_PATH = "C:/Users/LazRuby/Desktop/Files/chromedriver.exe"; //드라이버 경로

    public static void main(String[] args) { TvingCrwaling2 c = new TvingCrwaling2(); c.Crawling();}
    
    public void Crawling() {
    	
    	try { System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH); }
        catch (Exception e) { e.printStackTrace(); }
    	
    	Connection con = getConnection();
    	ChromeOptions options = new ChromeOptions();
    	WebDriver driver = new ChromeDriver(options);
    	Actions actions = new Actions(driver);
    	JavascriptExecutor jse = (JavascriptExecutor) driver;
    	
    	///////////////////////////////////////////////////////////////////////
    	
    	String url = "https://www.tving.com/movie/genre";
    	
    	driver.get(url);
    	
        wait(1000);
        
	    for (int i = 0; i < 5; i++) { jse.executeScript("window.scrollBy(0,5000)", ""); wait(2000); }
	    
	    System.out.println(driver.findElement(By.xpath("//*[@id=\"movieList\"]/div[119]")).getText());
	    System.out.println("=========================================");
	    System.out.println(driver.findElement(By.xpath("//*[@id=\"movieList\"]/div[119]")).getText());
	  
	    
	    /*
	    
	    for(int i=1; i<101; i++) {
	    	
	    	driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/button ")).click();
 		   
        	java.sql.Date temp_day=  java.sql.Date .valueOf("2020-02-2");
        	String imgurl = driver.findElement(By.xpath("//*[@id=\"movie-detail-title\"]/div[5]/div[1]/img")).getAttribute("src");
        	
        	ContentsDto dto = new ContentsDto(0, "미제공", "미제공", "미제공", "미제공", 5.0, "#드라마", "미제공", "미제공", temp_day, imgurl, "TV" );
    		
        	try {
        		if( driver.findElement(By.xpath("//*[@id=\"wrap\"]/main/div/div/section/div")).getText().length() >= 1 ) {
        			driver.navigate().back();
        			continue;
        		}
        	} catch ( Exception e ) {}
        	
        	wait(2000);
        	
        	//////////////////////////////////////////////////////////////////
        	     	         	        	
        	dto.setTitle( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/h2/span")  );
        	dto.setMovieImg(imgurl);
        	dto.setMovieAddr(driver.getCurrentUrl());
        	dto.setRate( rannum() );
        	dto = setDto( driver, dto );
        	
        	WavveCrawling selfclass = new WavveCrawling();
        	selfclass.insert(con, dto);
        	System.out.println(dto);
        	
        	wait(1500);
        	
        	driver.navigate().back();
        	
        	wait(4000);
        } 	*/
      
	    ///////////////////////////////////////////////////////////////////////
        
    }
    
    static public void wait(int count) { try { Thread.sleep(count); } catch (InterruptedException e) { } } 
    
    static public String getText(WebDriver driver, String url) {  	
    	String text = "";  	
    	try { text = driver.findElement(By.xpath(url)).getText(); } catch ( Exception e ) { }	
    	return text;
    }
   
    static public double rannum() {
    	double num = (Math.random() * 10);
        num = Math.round(num*100);
        num = num / 100.0;
        return num;
    }
}
