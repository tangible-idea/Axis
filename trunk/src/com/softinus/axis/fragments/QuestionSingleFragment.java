package com.softinus.axis.fragments;

import com.softinus.axis.activities.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class QuestionSingleFragment extends Fragment
{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
	
		return inflater.inflate(R.layout.fragment_question_single, container, false);
	}
}
