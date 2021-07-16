package com.dto;

import java.util.Date;

public class NoticeDto {
    private int num;
    private int usernum;
    private String title;
    private String content;
    private Date date;
    private int reference;
    private String nickname;

    public NoticeDto() {
    }

    public NoticeDto(int num, int usernum, String title, String content, Date date, int reference, String nickname) {
        this.num = num;
        this.usernum = usernum;
        this.title = title;
        this.content = content;
        this.date = date;
        this.reference = reference;
        this.nickname = nickname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
