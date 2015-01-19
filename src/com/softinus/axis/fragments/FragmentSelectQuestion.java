package com.softinus.axis.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.softinus.axis.activities.R;
import com.softinus.axis.data.QuestionData;
import com.softinus.axis.data.QuestionStorage;
import com.softinus.axis.util.Global;
import com.softinus.axis.util.SPUtil;

public class FragmentSelectQuestion extends Fragment
{
    // 리스트뷰를 구성하는 리스트뷰와 어댑터 변수
    private QuestionSelAdapter m_adapter = null;
    private ListView m_list = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
	
		View rootview= inflater.inflate(R.layout.fragment_select_question, container, false);
		
		// ExamData 객체를 관리하는 ArrayList 객체를 생성한다.
        ArrayList<QuestionData> data_list = new ArrayList<QuestionData>();
        // 사용자 정의 어댑터 객체를 생성한다.
        m_adapter = new QuestionSelAdapter(data_list);
        
        // 리스트를 얻어서 어댑터를 설정한다.
        m_list= (ListView) rootview.findViewById(R.id.list_selectquestion);
        m_list.setAdapter(m_adapter);
    
        for(int i=0; i<QuestionStorage.arrQList.size(); ++i)	// 전체 문제 목록 돌면서
        {
        	QuestionData QD= QuestionStorage.arrQList.get(i);
        	
        	if( SPUtil.getBoolean(getActivity(), Global.s_strPrefix_Buy+Integer.toString(i)) == true )
        		m_adapter.add(QD);
        }
	
		return rootview;
	}
	

	// BaseAdapter 를 상속하여 어댑터 클래스를 재정의한다.
    private class QuestionSelAdapter extends BaseAdapter 
    {
        private LayoutInflater m_inflater = null;
        // ExamData 객체를 관리하는 ArrayList
        private ArrayList<QuestionData> m_data_list;
        
        public QuestionSelAdapter(ArrayList<QuestionData> items)
        {
            m_data_list = items;    
            // 인플레이터를 얻는다.
            m_inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        
        // ArrayList 에 ExamData 객체를 추가하는 메서드
        public void add(QuestionData parm_data)
        {
            m_data_list.add(parm_data);
            // 데이터가 변화됨을 알려준다.
            notifyDataSetChanged();
        }

        // 어댑터에서 참조하는 ArrayList 가 가진 데이터의 개수를 반환하는 함수
        @Override
        public int getCount() 
        {
            return m_data_list.size();
        }
        
        // 인자로 넘어온 값에 해당하는 데이터를 반환하는 함수
        @Override
        public QuestionData getItem(int position) 
        {
            return m_data_list.get(position);
        }
        
        // 인자로 넘어온 값에 해당하는 행 ID 를 반환하는 메서드
        @Override
        public long getItemId(int position) 
        {
            return position;
        }
        

        // 각 항목에 출력될 뷰를 구성하여 반환하는 메서드
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = null;
            
            // convertView 뷰는 어댑터가 현재 가지고 있는 해당 항목의 뷰객체이다.
            // null 이 넘어오는 경우에만 새로 생성하고, 그렇지않은 경우에는 그대로 사용한다.
            if(convertView == null)
            {
            	view = m_inflater.inflate(R.layout.question_listview_select , null); 
            } else {
                view = convertView;
            }
            
            final QuestionData data= m_data_list.get(position);
            
            if(data != null)
            {
            	TextView TXT_content= (TextView) view.findViewById(R.id.txt_question_content);
            	TextView TXT_solved= (TextView) view.findViewById(R.id.txt_solved);
            	ImageView IMG_difficult= (ImageView) view.findViewById(R.id.img_difficult);
            	Button BTN_play= (Button) view.findViewById(R.id.btn_play);
            	
            	final TextView TXT_state= (TextView) view.findViewById(R.id.txt_state);
            	final View V_state= view.findViewById(R.id.bg_check);
            	
            	if(SPUtil.getBoolean(getActivity(), String.valueOf( data.nCode ))==true)
            	{
            		TXT_state.setVisibility(View.VISIBLE);
	            	V_state.setVisibility(View.VISIBLE);
            	}
            	else
            	{
            		TXT_state.setVisibility(View.INVISIBLE);
	            	V_state.setVisibility(View.INVISIBLE);
            	}
            		
            	
            	BTN_play.setOnClickListener(new OnClickListener()
            	{
					@Override
					public void onClick(View v)
					{
						new AlertDialog.Builder(getActivity())
				        .setTitle("문제 풀기")
				        .setMessage( data.nCode+"번 문제를 푸시겠습니까?"
				        		)
				        .setPositiveButton("YES", new DialogInterface.OnClickListener()
				        {
				            public void onClick(DialogInterface dialog, int whichButton)
				            {
				            	Global.s_nCurrQuestion= data.nCode;	 // 현재 풀고 있는 번호 넘기고 문제 푸는 페이지로 넘김.
				            	
				    			Fragment fr;
				    			fr= new FragmentQuestionSingle();
				    			FragmentManager fm = getFragmentManager();
				    			FragmentTransaction fragmentTransaction = fm.beginTransaction();
				    			fragmentTransaction.replace(R.id.fragment_place, fr);
				    			fragmentTransaction.commit();
				            }
				        })
				        .setNegativeButton("NO", new DialogInterface.OnClickListener()
				        {
				        	public void onClick(DialogInterface dialog, int whichButton)
				            {
				            }
				        })
				        .show();
					}
				});
            	
            	TXT_content.setText(data.strQuestion);
            	
            	Log.d("LOG:::::::::::::::Load listview", Global.s_strPrefix_Solved+ data.nCode );
            	// 해당 문제가 풀렸으면...
            	if( SPUtil.getBoolean(getActivity(), Global.s_strPrefix_Solved+ data.nCode ) == true )
            	{
            		TXT_solved.setText("완료");
            	}
            	else
            	{
            		TXT_solved.setText("미완료");	
            	}
            	
            	
            	switch(data.nDifficult)
            	{
            	case 1:
            		IMG_difficult.setImageResource(R.drawable.lv1);
            		break;
            	case 2:
            		IMG_difficult.setImageResource(R.drawable.lv2);
            		break;
            	case 3:
            		IMG_difficult.setImageResource(R.drawable.lv3);
            		break;
            	case 4:
            		IMG_difficult.setImageResource(R.drawable.lv4);
            		break;
            	case 5:
            		IMG_difficult.setImageResource(R.drawable.lv5);
            		break;
            	case 6:
            		IMG_difficult.setImageResource(R.drawable.lv6);
            		break;
            	case 7:
            		IMG_difficult.setImageResource(R.drawable.lv7);
            		break;
            	}
            }
            return view;            
        }
    }
}
