package com.softinus.axis.data;

public class AxisHistory
{
	public int nType=0;	// 0: 월, 1: 일, 2: 기록
	public int nPosition=0; 	// 0: 중간, 1: 왼쪽, 2: 오른쪽
	public String strData="";	// 데이터
	
	public AxisHistory(int nType, int nPosition, String strData)
	{
		super();
		this.nType = nType;
		this.nPosition = nPosition;
		this.strData = strData;
	}
	
	
}
