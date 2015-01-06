package com.softinus.axis.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.softinus.axis.activities.R;

public class FragmentStudy extends Fragment
{
	ListView view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
	
		return inflater.inflate(R.layout.fragment_study, container, false);
	}
	
}
