package com.softinus.axis.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.softinus.axis.util.SPUtil;

public class FragmentMarket extends Fragment
{
    // 리스트뷰를 구성하는 리스트뷰와 어댑터 변수
    private MarketAdapter m_adapter = null;
    private ListView m_list = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		View rootview= inflater.inflate(R.layout.fragment_history, container, false);
		
		// ExamData 객체를 관리하는 ArrayList 객체를 생성한다.
        ArrayList<QuestionData> data_list = new ArrayList<QuestionData>();
        // 사용자 정의 어댑터 객체를 생성한다.
        m_adapter = new MarketAdapter(data_list);
        
        // 리스트를 얻어서 어댑터를 설정한다.
        m_list= (ListView) rootview.findViewById(R.id.list_history);
        m_list.setAdapter(m_adapter);
    
        for(int i=0; i<QuestionStorage.arrQList.size(); ++i)
        {
        	QuestionData QD= QuestionStorage.arrQList.get(i);
        	
        	if( SPUtil.getBoolean(getActivity(), Integer.toString(i)) == false )
        		m_adapter.add(QD);
        }
	
		return rootview;
	}
	
	
	

	// BaseAdapter 를 상속하여 어댑터 클래스를 재정의한다.
    private class MarketAdapter extends BaseAdapter 
    {
        private LayoutInflater m_inflater = null;
        // ExamData 객체를 관리하는 ArrayList
        private ArrayList<QuestionData> m_data_list;
        
        public MarketAdapter(ArrayList<QuestionData> items)
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
            	view = m_inflater.inflate(R.layout.market_listview_question , null); 
            } else {
                view = convertView;
            }
            
            QuestionData data= m_data_list.get(position);
            
            if(data != null)
            {
            	TextView TXT_content= (TextView) view.findViewById(R.id.txt_question_content);
            	TextView TXT_exp= (TextView) view.findViewById(R.id.txt_exp);
            	ImageView IMG_difficult= (ImageView) view.findViewById(R.id.img_difficult);
            	Button BTN_buy= (Button) view.findViewById(R.id.btn_buy);
            	
            	final TextView TXT_state= (TextView) view.findViewById(R.id.txt_state);
            	final View V_state= view.findViewById(R.id.bg_check);
            	
            	if(SPUtil.getBoolean(getActivity(), String.valueOf(position))==true)
            	{
            		TXT_state.setVisibility(View.VISIBLE);
	            	V_state.setVisibility(View.VISIBLE);
            	}
            	else
            	{
            		TXT_state.setVisibility(View.INVISIBLE);
	            	V_state.setVisibility(View.INVISIBLE);
            	}
            		
            	
            	BTN_buy.setOnClickListener(new OnClickListener()
            	{
					
					@Override
					public void onClick(View v)
					{
						new AlertDialog.Builder(getActivity())
				        .setTitle("문제 구매")
				        .setMessage( position+"번 문제를 구매하시겠습니까?"
				        		)
				        .setPositiveButton("YES", new DialogInterface.OnClickListener()
				        {
				            public void onClick(DialogInterface dialog, int whichButton)
				            {
				            	SPUtil.putBoolean(getActivity(), String.valueOf(position), true);
				            	TXT_state.setVisibility(View.VISIBLE);
				            	V_state.setVisibility(View.VISIBLE);
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
            	TXT_exp.setText("exp : "+String.valueOf(data.nExp));
            	
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
            
//            // 요청하는 항목에 해당하는 데이터 객체를 얻는다.
//            AxisHistory data = m_data_list.get(position);
//            
//            // 데이터가 존재하는 경우
//            if(data != null)
//            {
//            	if((pos==0) && (type==0))	// month
//            	{
//            		TextView TXT_content= (TextView) view.findViewById(R.id.hexagon_month_content);
//            		TXT_content.setText(data.strData);
//            	}
//            	else if(type == 1)	// date
//            	{
//            		TextView TXT_content= (TextView) view.findViewById(R.id.txt_content);
//            		if(pos == 1)	// left
//            		{
//                		TXT_content.setText(data.strData);
//            		}
//            		else	// right
//            		{
//            			TXT_content.setText(data.strData);
//            		}
//            	}
//            	else if(type == 2)	// content
//            	{
//            		TextView TXT_content= (TextView) view.findViewById(R.id.txt_content);
//            		if(pos == 1)	// left
//            		{
//            			TXT_content.setText(data.strData);
//            		}
//            		else	// right
//            		{
//            			TXT_content.setText(data.strData);
//            		}
//            	}
//            }
            return view;            
        }
    }
}
