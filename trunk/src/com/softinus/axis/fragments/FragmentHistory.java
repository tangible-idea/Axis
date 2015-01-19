package com.softinus.axis.fragments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.softinus.axis.activities.R;
import com.softinus.axis.data.AxisHistory;
import com.softinus.axis.util.Utilities;

public class FragmentHistory extends Fragment
{
	
    // 리스트뷰를 구성하는 리스트뷰와 어댑터 변수
    private HistoryAdapter m_adapter = null;
    private ListView m_list = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{	
		View rootview= inflater.inflate(R.layout.fragment_history, container, false);
		
		// ExamData 객체를 관리하는 ArrayList 객체를 생성한다.
        ArrayList<AxisHistory> data_list = new ArrayList<AxisHistory>();
        // 사용자 정의 어댑터 객체를 생성한다.
        m_adapter = new HistoryAdapter(data_list);
        
        // 리스트를 얻어서 어댑터를 설정한다.
        m_list= (ListView) rootview.findViewById(R.id.list_history);
        m_list.setAdapter(m_adapter);
        
        
        
        
        String txt= Utilities.ReadTextFile(getActivity(), "history.txt");
        StringTokenizer ST_line = new StringTokenizer(txt, "\r\n");
        
        int nCurrMonth= -1;
        int nCurrDate= -1;
        
        while(ST_line.hasMoreTokens())
        {
        	String strLine= ST_line.nextToken();
        	
        	StringTokenizer ST_blank = new StringTokenizer(strLine, " ");
        	String strDate= ST_blank.nextToken();
        	String strAction= ST_blank.nextToken();
        	String strNote= ST_blank.nextToken();
        	
			SimpleDateFormat SDF_Format = new SimpleDateFormat("yyyy:MM:dd:HH:mm");						// 시간 포맷 지정
			StringTokenizer ST_date= new StringTokenizer(strDate, ":");
			String strYear= ST_date.nextToken();
			String strMonth= ST_date.nextToken();
			String strDay= ST_date.nextToken();
			String strHours= ST_date.nextToken();
			String strMinutes= ST_date.nextToken();
			try
			{
				Date parsed= SDF_Format.parse(strDate);
				
				if(nCurrMonth != parsed.getMonth())	// 월이 바뀌엇으면
				{
					m_adapter.add(new AxisHistory(0, 0, 1+parsed.getMonth()+"월"));
					nCurrMonth = parsed.getMonth();
				}
				
				if(nCurrDate != parsed.getDate())	// 일이 바뀌엇으면
				{
					if(parsed.getDate() % 2 == 0) // 짝수일이면
					{
						m_adapter.add(new AxisHistory(1, 1, parsed.getDate()+"일"));
						m_adapter.add(new AxisHistory(2, 1, "시스템 : 접속["+ strHours +":"+strMinutes+"]"));
					}
					else
					{
						m_adapter.add(new AxisHistory(1, 2, parsed.getDate()+"일"));
						m_adapter.add(new AxisHistory(2, 2, "시스템 : 접속["+ strHours +":"+strMinutes+"]"));
					}
					
					nCurrDate= parsed.getDate();
				}
				
				String strBuf= "";
				if(strAction.equals("solved"))
				{
					strBuf += "학습 : 문제풀이";
				}
				else if(strAction.equals("buy"))
				{
					strBuf += "마켓 : 문제구입";
				}
				else if(strAction.equals("online"))
				{
					strBuf += "시스템 : 접속 (" + strHours + strMinutes +")";
				}
				else if(strAction.equals("join"))
				{
					strBuf += "시스템 : 회원가입";
				}
				// 노트 적기
				if(parsed.getDate() % 2 == 0) // 짝수일이면
					 m_adapter.add(new AxisHistory(2, 1, strBuf));
				else
					 m_adapter.add(new AxisHistory(2, 2, strBuf));
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        
       
//       m_adapter.add(new AxisHistory(1, 1, "1일"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       
//       m_adapter.add(new AxisHistory(1, 2, "2일"));
//       m_adapter.add(new AxisHistory(2, 2, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 2, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 2, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 2, "우와왐ㄴ어ㅣㅁㅇ"));
//       
//       m_adapter.add(new AxisHistory(1, 1, "3일"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 1, "우와왐ㄴ어ㅣㅁㅇ"));
//       
//       m_adapter.add(new AxisHistory(1, 2, "4일"));
//       m_adapter.add(new AxisHistory(2, 2, "우와왐ㄴ어ㅣㅁㅇ"));
//       m_adapter.add(new AxisHistory(2, 2, "우와왐ㄴ어ㅣㅁㅇ"));
       
       
       
		
		return rootview;
	}
	
	// BaseAdapter 를 상속하여 어댑터 클래스를 재정의한다.
    private class HistoryAdapter extends BaseAdapter 
    {
        private LayoutInflater m_inflater = null;
        // ExamData 객체를 관리하는 ArrayList
        private ArrayList<AxisHistory> m_data_list;
        
        public HistoryAdapter(ArrayList<AxisHistory> items)
        {
            m_data_list = items;    
            // 인플레이터를 얻는다.
            m_inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        
        // ArrayList 에 ExamData 객체를 추가하는 메서드
        public void add(AxisHistory parm_data)
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
        public AxisHistory getItem(int position) 
        {
            return m_data_list.get(position);
        }
        
        // 인자로 넘어온 값에 해당하는 행 ID 를 반환하는 메서드
        @Override
        public long getItemId(int position) 
        {
            return position;
        }
        
        // 인자로 넘어온 값에 해당하는 뷰의 타입을 반환하는 메서드
        @Override
        public int getItemViewType(int position)
        {
            return m_data_list.get(position).nType;
        }
        
        public int getItemViewPosition(int position)
        {
        	return m_data_list.get(position).nPosition;
        }
        
        // getView 메서드로 생성될 수 있는 뷰의 수를 반환하는 메서드
        @Override
        public int getViewTypeCount()
        {
            return 5;
        }

        // 각 항목에 출력될 뷰를 구성하여 반환하는 메서드
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = null;
            // 해당 항목의 뷰 타입을 얻는다.
            int type = getItemViewType(position);
            int pos = getItemViewPosition(position);
            
            // convertView 뷰는 어댑터가 현재 가지고 있는 해당 항목의 뷰객체이다.
            // null 이 넘어오는 경우에만 새로 생성하고, 그렇지않은 경우에는 그대로 사용한다.
            if(convertView == null) {
                // 타입에 따라 각기 다른 XML 리소스로 뷰를 생성한다.
            	if((pos==0) && (type==0))	// month
            	{
            		view = m_inflater.inflate(R.layout.history_viewtype_month , null); 
            	}
            	else if(type == 1)	// date
            	{
            		if(pos == 1)	// left
            		{
            			view = m_inflater.inflate(R.layout.history_viewtype_date_left , null); 
            		}
            		else	// right
            		{
            			view = m_inflater.inflate(R.layout.history_viewtype_date_right , null); 
            		}
            	}
            	else if(type == 2)	// content
            	{
            		if(pos == 1)	// left
            		{
            			view = m_inflater.inflate(R.layout.history_viewtype_content_left , null); 
            		}
            		else	// right
            		{
            			view = m_inflater.inflate(R.layout.history_viewtype_content_right , null); 
            		}
            	}
            } else {
                view = convertView;
            }
            
            // 요청하는 항목에 해당하는 데이터 객체를 얻는다.
            AxisHistory data = m_data_list.get(position);
            
            // 데이터가 존재하는 경우
            if(data != null)
            {
            	if((pos==0) && (type==0))	// month
            	{
            		TextView TXT_content= (TextView) view.findViewById(R.id.hexagon_month_content);
            		TXT_content.setText(data.strData);
            	}
            	else if(type == 1)	// date
            	{
            		TextView TXT_content= (TextView) view.findViewById(R.id.txt_content);
            		if(pos == 1)	// left
            		{
                		TXT_content.setText(data.strData);
            		}
            		else	// right
            		{
            			TXT_content.setText(data.strData);
            		}
            	}
            	else if(type == 2)	// content
            	{
            		TextView TXT_content= (TextView) view.findViewById(R.id.txt_content);
            		if(pos == 1)	// left
            		{
            			TXT_content.setText(data.strData);
            		}
            		else	// right
            		{
            			TXT_content.setText(data.strData);
            		}
            	}
            }
            return view;            
        }
    }
	
}
