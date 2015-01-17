package com.softinus.axis.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.softinus.axis.activities.R;

public class FragmentMarket extends ListFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		 View rootView = inflater.inflate(R.layout.fragment_market, container,
		            false);

		    String[] values = new String[] { "문제1 구매", "문제2 구매", "문제3 구매",
		    		"문제4 구매", "문제5 구매", "문제6 구매",
		    		"문제7 구매", "문제8 구매", "문제9 구매"};
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, values);
		    setListAdapter(adapter);
		    return rootView;
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
	}

	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState)
//	{
//		// TODO Auto-generated method stub
//		
//	
//		return inflater.inflate(R.layout.fragment_market, container, false);
//	}
	
}
