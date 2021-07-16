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

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    	Connection con = getConnection();
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
        
        //////////////////////////////////////////////////////////////////
        
        int countt = 0;
           
        for(int j=1; j<11; j++) {
        	
        	//url = "https://www.wavve.com/list/VN4?api=apis.wavve.com%252Fcf%252Fvod%252Fpopularcontents%253Forderby%253Dviewtime%2526contenttype%253Dvod%2526genre%253D01%2526WeekDay%253Dall%2526uitype%253DVN4%2526uiparent%253DGN56-VN4%2526uirank%253D2%2526broadcastid%253D126147%2526offset%253D0%2526limit%253D20%2526uicode%253DVN4&came=BandViewGnbCode&page=" + j;
            //url = "https://www.wavve.com/list/VN3?api=apis.wavve.com%252Fcf%252Fvod%252Fpopularcontents%253Forderby%253Dviewtime%2526contenttype%253Dvod%2526genre%253D02%2526WeekDay%253Dall%2526uitype%253DVN3%2526uiparent%253DGN57-VN3%2526uirank%253D2%2526broadcastid%253D164058%2526offset%253D0%2526limit%253D20%2526uicode%253DVN3&came=BandViewGnbCode&page=" + j;
        	url = "https://www.wavve.com/list/MN85?api=apis.wavve.com%252Fcf%252Fmovie%252Fcontents%253Fsptheme%253Dsvod%2526price%253Dall%2526orderby%253Dviewtime%2526contenttype%253Dmovie%2526genre%253Dall%2526WeekDay%253Dall%2526uitype%253DMN85%2526uiparent%253DGN59-MN85%2526uirank%253D4%2526broadcastid%253D176159%2526offset%253D0%2526limit%253D20%2526uicode%253DMN85%2526mtype%253Dsvod&came=BandViewGnbCode&page=" + j;
            driver.get(url);
            
            wait(1500);  System.out.println();

        	for(int i=1; i<21; i++) {
        		   
            	java.sql.Date temp_day=  java.sql.Date .valueOf("2020-02-2");
            	ContentsDto dto = new ContentsDto(0, "미제공", "미제공", "미제공", "미제공", 5.0, "#드라마", "미제공", "미제공", temp_day, "미제공", "WV" );
        		
            	String imgurl = driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[2]/div[" + i + "]/a/div[1]/img")).getAttribute("src");
            	driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[2]/div[" + i + "]/a/div[2]")).click();
            	
            	wait(1500);
            	
            	//////////////////////////////////////////////////////////////////
            	
            	try {
            		if( driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/button[2]")).getText().length() >= 1 ) {
            			driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[2]/div/button[2]")).click();
            		}
            	} catch ( Exception e ) {}
            	
            	wait(1500);
            	
            	//////////////////////////////////////////////////////////////////
            	
            	driver.findElement(By.xpath("//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/button ")).click();
            	
            	wait(500);
            	         	        	
            	dto.setTitle( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/h2/span")  );
            	dto.setMovieImg(imgurl);
            	dto.setMovieAddr(driver.getCurrentUrl());
            	dto.setRate( rannum() );
            	dto = setDto( driver, dto );
            	
            	WavveCrawling selfclass = new WavveCrawling();
            	selfclass.insert(con, dto);
            	
            	wait(1000);
            	
            	driver.navigate().back();
            	
            	wait(1500); countt++; System.out.println(countt);
            } 	
        }
      
        try { if (driver != null) { driver.close(); driver.quit(); } }
        catch (Exception e) { throw new RuntimeException(e.getMessage()); }    
    }
    
    private boolean insert(Connection con, ContentsDto dto) {
       
    	PreparedStatement pstm = null;
		int rs = 0;

		try {
		
			String SQL = " INSERT INTO CONTENTS VALUES (MOVIE_SQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ? ) ";	
			pstm = con.prepareStatement(SQL);

			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getOpenYear());
			pstm.setString(3, dto.getDirector());
			pstm.setString(4, dto.getActor());
			pstm.setDouble(5, dto.getRate());
			pstm.setString(6, dto.getGenre());
			pstm.setString(7, dto.getSummary());
			pstm.setString(8, dto.getMovieAddr());
			pstm.setString(9, dto.getMovieImg());
	
			rs = pstm.executeUpdate();
			
			if(rs>=1) { con.commit(); }		
	
		} catch (SQLException e) { e.printStackTrace(); }
		finally { close(pstm); }
		
		return rs>=1?true:false;
    }
    
    static public void wait(int count) { try { Thread.sleep(count); } catch (InterruptedException e) {} }
    
    static public String getText(WebDriver driver, String url) { 
    	
    	String text = "";  	
    	try { text = driver.findElement(By.xpath(url)).getText(); } catch ( Exception e ) {}	
    	return text;
    }
    
    static public ContentsDto setDto(WebDriver driver, ContentsDto dto) {
    	
    	for(int i=1; i<=4; i++) {
    		
    		try { if( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").length() < 1) { continue; } } catch ( Exception e ) {}
        		
    		if ( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").contains("장르") ) {
    			String text = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			text = text.replaceAll("장르", "");
    			text = text.replaceAll(", ,", "");
    			dto.setGenre("#드라마, " + text);
    			continue;
    		}
    		else if ( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").contains("출연") ) {
    			String text = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			text = text.replaceAll("출연", "");
    			text = text.replaceAll("#", "");
    			text = text.replaceAll(", ,", "");
    			dto.setActor(text);
    			continue;
    		}
    		else if ( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").contains("줄거리") ) {
    			String text = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			dto.setSummary(text.replaceAll("줄거리", ""));
    			continue;
    		}
    		else if ( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").contains("개요") ) {
    			
    			String text = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			String text2 = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			
    			text = text.replaceAll("년", "");
    			text = text.replaceAll("[0-9]", "");
    			text = text.replaceAll("개요", "");
    			text = text.replaceAll(", ,", "");
    			dto.setDirector(text);
    			
    			text2 = text2.replaceAll("1TV|2TV", "");
    			text2 = text2.replaceAll("[^0-9]", "");
    			dto.setOpenYear(text2+"년");
    			continue;
    		}
    		else if ( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").contains("감독") ) {
    			String text = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			dto.setDirector(text.replaceAll("감독", ""));
    			continue;
    		}
    		else if ( getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]").contains("개봉") ) {
    			String text = getText(driver, "//*[@id=\"g-contents\"]/div[1]/div/div[2]/div[3]/div/p[" + i + "]");
    			dto.setDirector(text.replaceAll("개봉", ""));
    			continue;
    		}
    	}

    	return dto;
    }
    
    static public double rannum() {
    	double num = (Math.random() * 10);
        num = Math.round(num*100);
        num = num / 100.0;
        return num;
    }
}