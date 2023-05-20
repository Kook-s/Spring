package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVO {
	private int boNo;			//일반 게시글 번호
	private String boTitle;		//일반 게시글 제목
	private String boWriter;	//일반 게시글 작성자
	private String boContent;	//일반 게시글 내용
	private String boDate;		//일반 게시글 작성일
	private int boHit;			//일반 게시글 조회수
	
}
