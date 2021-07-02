
import java.util.Date;

public class MyBoardDto {
	private int myno;
	private String myname;
	private String mytitle;
	private String mycontent;
	private Date mydate;
	
	public MyBoardDto() {}
	
	//insert
	public MyBoardDto(String myname, String mytitle, String mycontent) {
		super();
		this.myname = myname;
		this.mytitle = mytitle;
		this.mycontent = mycontent;
	}
	
	
	
}
