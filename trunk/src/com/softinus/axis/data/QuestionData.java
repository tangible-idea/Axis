package com.softinus.axis.data;

import java.util.Date;

public class QuestionData
{
	public int nCode= 0; // 일련 번호
	public Date dateRecorded= null;	// 문제 기록된 날짜
	public String strAuthor;	// 문제 기록한 ID.
	public int nDifficult= 0;	// 문제 난이도
	public int nExp= 0;		// 문제를 풀었을 때 경험치
	public String strQuestion="";	// 문제 수식
	public String strText="";	// Text 데이터
	public String strAnswer="";	// 문제 답변
	public String strImgData="";	// 문제 이미지
	public String strExplain="";	// 문제 설명
	
	public QuestionData(int nCode, Date dateRecorded, String strAuthor,
			int nDifficult, int nExp, String strQuestion, String strText,
			String strAnswer, String strImgData, String strExplain)
	{
		super();
		this.nCode = nCode;
		this.dateRecorded = dateRecorded;
		this.strAuthor = strAuthor;
		this.nDifficult = nDifficult;
		this.nExp = nExp;
		this.strQuestion = strQuestion;
		this.strText = strText;
		this.strAnswer = strAnswer;
		this.strImgData = strImgData;
		this.strExplain = strExplain;
	}
	
}
