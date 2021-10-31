package com.springbook.biz.board;

import java.sql.Date;

public class BoardDTO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;

	public int getSeq() {
		return seq;
	}

	public String getTitle() {
		return title;
	}

	public String getWriter() {
		return writer;
	}

	public String getContent() {
		return content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + ", cnt=" + cnt + "]";
	}

}
