package com.softinus.axis.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.softinus.axis.activities.R;
import com.softinus.axis.data.QuestionData;
import com.softinus.axis.data.QuestionStorage;
import com.softinus.axis.util.Global;
import com.softinus.axis.util.SPUtil;
import com.softinus.axis.util.Utilities;

public class FragmentQuestionSingle extends Fragment
{
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
	
		View rootView= inflater.inflate(R.layout.fragment_question_single, container, false);
		
		TextView TXT_question= (TextView)rootView.findViewById(R.id.txt_question);
		final TextView EDT_answer= (TextView)rootView.findViewById(R.id.edt_answer);
		TextView TXT_earnEXP= (TextView)rootView.findViewById(R.id.txt_earn_exp);
		Button BTN_submit= (Button)rootView.findViewById(R.id.btn_submit);
		
		QuestionData tempQ= null;		
		for(QuestionData QD : QuestionStorage.arrQList)
		{
			if(QD.nCode == Global.s_nCurrQuestion)	// 돌면서 현재 코드번호 맞는 문제를 찾는다.
				tempQ = QD;
		}
		final QuestionData currQ =  tempQ;
		
		
		Log.d("LOG:::::::Global.s_nCurrQuestion", ""+Global.s_nCurrQuestion );
		
		TXT_question.setText(currQ.strText);
		TXT_earnEXP.setText( "exp : "+currQ.nExp);
		
		BTN_submit.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				if( EDT_answer.getText().toString().equals( currQ.strAnswer ) ) 
				{
					
//					{	// 로그 파일 쓰기 부분
//						long now = System.currentTimeMillis();						// 현재 시간을 msec으로 구한다.	
//						Date date = new Date(now);									// 현재 시간을 저장 한다.	
//						SimpleDateFormat SDF_Format = new SimpleDateFormat("yyyy:MM:dd:HH:mm");						// 시간 포맷 지정
//	
//						// 지정된 포맷으로 String 타입 리턴 
//						String strBuf = SDF_Format.format(date);
//						strBuf += " solved ";
//						strBuf += " "+currQ.nCode+"\n";
//						
//						Utilities.WriteTextFile(getActivity(), "history.txt", strBuf);
//					}
					Utilities.WriteAxisLog(getActivity(), "history.txt", "solved", currQ.nCode);
					
					ShowAlertDialog("문제 제출", "맞았습니다.", "확인");
					Global.s_nCurrQuestion= -1;
					SPUtil.putBoolean(getActivity(), Global.s_strPrefix_Solved + currQ.nCode, true);
					Log.d("LOG:::::::Solved", Global.s_strPrefix_Solved+ currQ.nCode );
					
	    			Fragment fr;
	    			fr= new FragmentSelectQuestion();
	    			FragmentManager fm = getFragmentManager();
	    			FragmentTransaction fragmentTransaction = fm.beginTransaction();
	    			fragmentTransaction.replace(R.id.fragment_place, fr);
	    			fragmentTransaction.commit();
				}
				else
				{
					ShowAlertDialog("문제 제출", "틀렸습니다.", "확인");
					
				}
			}
		});
		
		
		return rootView;
	}
	
	private void ShowAlertDialog(String strTitle, String strContent, String strButton)
	{
		new AlertDialog.Builder(getActivity())
		.setTitle( strTitle )
		.setMessage( strContent )
		.setPositiveButton( strButton , null)
		.setCancelable(false)
		.create()
		.show();
	}
}


