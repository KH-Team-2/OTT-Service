package com.dto;

public class Paging {
	private int page = 1; //현재 페이지(get)
	private int totalCount; //row 전체의 수(get)
	private int beginPage; //출력시작
	private int endPage;	//출력끝
	private int displayRow=10; //한페이지에 몇개의 로우(선택 set)
	private int displayPage=10; //한페이지에 몇개의 페이지(선택 set)
	boolean prev; //prev버튼이 보일건지 안보일건지
	boolean next; //next 버튼이 보일건지 안보일건지
	
	
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		//setTotalCount() 를 호출해야 paging이 되기 때문에
		//paging() 함수를 setTotalCount()를 호출했을때 자동으로 호출되게 한다.
		this.totalCount = totalCount;
		paging();
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrew(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	private void paging() {
		// 1/10=0.1(올림)1
		endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
		System.out.println("endpage : " + endPage);
		
		beginPage = endPage - (displayPage-1);
		System.out.println("beginpage : " + beginPage);
		
		//글 32개
		//32/10 = 3.2 (올림) 4페이지
		//2=? 2/10
		int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
		
		if(totalPage<endPage) {
			endPage = totalPage;
			next = false;
		}else {
			next = true;
		}
		prev = (beginPage==1)?false:true; //page가 11이상에서만 나온다.
		System.out.println("endpage : "+endPage);
		System.out.println("totalPage : " +totalPage);
	}
}
