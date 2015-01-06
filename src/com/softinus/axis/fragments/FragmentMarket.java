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

		    String[] values = new String[] { "Message1", "Message2", "Message3"};
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
