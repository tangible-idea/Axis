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

import com.softinus.axis.activities.R;
import com.softinus.axis.data.QuestionData;
import com.softinus.axis.data.QuestionStorage;
import com.softinus.axis.util.Global;
import com.softinus.axis.util.SPUtil;

public class FragmentSelectCollection extends Fragment implements OnClickListener
{
	Button BTN_start_colleciton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
	
		View rootview= inflater.inflate(R.layout.fragment_select_collection, container, false);
		
		
		BTN_start_colleciton= (Button)rootview.findViewById(R.id.btn_start_collection);
		BTN_start_colleciton.setOnClickListener(this);
	
		return rootview;
	}

	@Override
	public void onClick(View v)
	{
		Global.s_arrCollectionList.clear();
		for(int i=0; i<QuestionStorage.arrQList.size(); ++i)	// 전체 문제 목록 돌면서
        {
        	QuestionData QD= QuestionStorage.arrQList.get(i);
        	
        	if( SPUtil.getBoolean(getActivity(), Global.s_strPrefix_Buy+Integer.toString(i)) == true )
        	{
        		Global.s_arrCollectionList.add(QD.nCode);
        	}
        }
		
		if(Global.s_nPencilCount == 0)
		{
			ShowAlertDialog("연필부족", "연필이 부족합니다.", "확인");
		}
		else if(Global.s_arrCollectionList.size() == 0)
		{
			ShowAlertDialog("문제부족", "구매한 문제가 없습니다.", "확인");
		}
		
		//Global.s_nCurrQuestion= QD.nCode;	 // 현재 풀고 있는 번호 넘기고 문제 푸는 페이지로 넘김.
    	Global.s_nPencilCount--;	// 연필 하나 깍음.
    	
		Fragment fr;
		fr= new FragmentQuestionCollection();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_place, fr);
		fragmentTransaction.commit();
		
		
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
