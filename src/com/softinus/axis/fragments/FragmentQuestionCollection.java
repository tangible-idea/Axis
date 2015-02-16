package com.softinus.axis.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softinus.axis.activities.R;
import com.softinus.axis.data.QuestionData;
import com.softinus.axis.data.QuestionStorage;
import com.softinus.axis.util.Global;
import com.softinus.axis.util.SPUtil;
import com.softinus.axis.util.Utilities;

public class FragmentQuestionCollection extends Fragment
{
	private TextView TXT_question_count= null;
	private TextView TXT_question= null;
	private EditText EDT_answer= null;
	private Button BTN_submit= null;
	
	private int nMax= Global.s_arrCollectionList.size();
	private int nCount= 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView= inflater.inflate(R.layout.fragment_question_collection, container, false);
		
		TXT_question_count= (TextView)rootView.findViewById(R.id.txt_question_count);
		TXT_question= (TextView)rootView.findViewById(R.id.txt_question);
		EDT_answer= (EditText)rootView.findViewById(R.id.edt_answer);
		BTN_submit= (Button)rootView.findViewById(R.id.btn_submit);
		
		
		if(Global.s_arrCollectionList.isEmpty())
		{
			ShowAlertDialog("문제 부족", "문제 모음을 풀기 위해서 문제를 구매하셔야 합니다.", "확인");
			return rootView;
		}
		
		final QuestionData currQ = FindNextQuestion(Global.s_arrCollectionList.get(nCount));
		TXT_question.setText(currQ.strText);
		
		BTN_submit.setOnClickListener(new OnClickListener()
		{			
			@Override
			public void onClick(View v)
			{
				
				
				if( EDT_answer.getText().toString().equals( currQ.strAnswer ) ) 
				{
					Utilities.WriteAxisLog(getActivity(), "history.txt", "solved", currQ.nCode);
					
					ShowAlertDialog("문제 제출", "맞았습니다.", "다음 문제");
					SPUtil.putBoolean(getActivity(), Global.s_strPrefix_Solved + currQ.nCode, true);
				}
				else
				{
					ShowAlertDialog("문제 제출", "틀렸습니다.", "다음 문제");
				}
				
				if(nCount < nMax-1)
				{
					FindNextQuestion(Global.s_arrCollectionList.get(++nCount));
				}
				else
				{
					ShowAlertDialog("문제모음 결과", "끝.", "확인");
					
					Fragment fr;
					fr= new FragmentSelectCollection();
					FragmentManager fm = getFragmentManager();
					FragmentTransaction fragmentTransaction = fm.beginTransaction();
					fragmentTransaction.replace(R.id.fragment_place, fr);
					fragmentTransaction.commit();
				}
				
				
				
			}
		});
	
		return rootView;
	}


	private QuestionData FindNextQuestion(int nQuestionCount)
	{
		
		
		QuestionData tempQ= null;		
		for(QuestionData QD : QuestionStorage.arrQList)
		{ 
			if(QD.nCode == nQuestionCount)	// 돌면서 현재 코드번호 맞는 문제를 찾는다.
				tempQ = QD;
		}
		final QuestionData currQ =  tempQ;
		
		TXT_question_count.setText("문제 수 : "+nCount+"/"+nMax);
		EDT_answer.setText("");
		TXT_question.setText(currQ.strQuestion);
		
		return currQ;
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
