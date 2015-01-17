package com.softinus.axis.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.softinus.axis.fragments.FragmentMarket;
import com.softinus.axis.fragments.FragmentStudy;
import com.softinus.axis.fragments.QuestionCollectionFragment;
import com.softinus.axis.fragments.QuestionSingleFragment;

public class MainActivity2 extends BaseAxisAcivity implements OnClickListener
{
	private Button BTN_more, BTN_market, BTN_record, BTN_study;
	private ToggleButton BTN_study_sub1, BTN_study_sub2, BTN_study_sub3;
	private ToggleButton BTN_market_sub1, BTN_market_sub2;
	
	private ImageView IMG_sub;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);
		
		BTN_more= (Button) findViewById(R.id.btn_more);
		BTN_market= (Button) findViewById(R.id.btn_market);
		BTN_record= (Button) findViewById(R.id.btn_record);
		BTN_study= (Button) findViewById(R.id.btn_study);
		
		BTN_study_sub1= (ToggleButton) findViewById(R.id.btn_study_sub1);
		BTN_study_sub2= (ToggleButton) findViewById(R.id.btn_study_sub2);
		BTN_study_sub3= (ToggleButton) findViewById(R.id.btn_study_sub3);
		
		BTN_market_sub1= (ToggleButton) findViewById(R.id.btn_market_sub1);
		BTN_market_sub2= (ToggleButton) findViewById(R.id.btn_market_sub2);
		
		
		
		BTN_more.setOnClickListener(this);
		BTN_market.setOnClickListener(this);
		BTN_record.setOnClickListener(this);
		BTN_study.setOnClickListener(this);
		
		BTN_study_sub1.setOnClickListener(this);
		BTN_study_sub2.setOnClickListener(this);
		BTN_study_sub3.setOnClickListener(this);
		
		BTN_market_sub1.setOnClickListener(this);
		BTN_market_sub2.setOnClickListener(this);
	}

	@Override
	protected void onStart()
	{
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		if(v.getId() == R.id.btn_more)
		{
			Intent intent= new Intent(MainActivity2.this, MoreActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.btn_market)
		{
			BTN_study_sub1.setVisibility(View.GONE);
			BTN_study_sub2.setVisibility(View.GONE);
			BTN_study_sub3.setVisibility(View.GONE);
			
			BTN_market_sub1.setVisibility(View.VISIBLE);
			BTN_market_sub2.setVisibility(View.VISIBLE);
			
			Fragment fr;
			fr= new FragmentMarket();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();

		}
		else if(v.getId() == R.id.btn_study)
		{
			BTN_study_sub1.setVisibility(View.VISIBLE);
			BTN_study_sub2.setVisibility(View.VISIBLE);
			BTN_study_sub3.setVisibility(View.VISIBLE);
			
			BTN_market_sub1.setVisibility(View.GONE);
			BTN_market_sub2.setVisibility(View.GONE);
			
			BTN_study_sub1.setChecked(true);
			BTN_study_sub2.setChecked(false);
			BTN_study_sub3.setChecked(false);
			
			Fragment fr;
			fr= new QuestionSingleFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
			

		}
		else if(v.getId() == R.id.btn_record)
		{
			BTN_study_sub1.setVisibility(View.GONE);
			BTN_study_sub2.setVisibility(View.GONE);
			BTN_study_sub3.setVisibility(View.GONE);
			
			BTN_market_sub1.setVisibility(View.GONE);
			BTN_market_sub2.setVisibility(View.GONE);
		}
		
		else if(v.getId() == R.id.btn_study_sub1)
		{
			BTN_study_sub1.setChecked(true);
			BTN_study_sub2.setChecked(false);
			BTN_study_sub3.setChecked(false);
			
			Fragment fr;
			fr= new QuestionSingleFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
		}
		else if(v.getId() == R.id.btn_study_sub2)
		{
			BTN_study_sub1.setChecked(false);
			BTN_study_sub2.setChecked(true);
			BTN_study_sub3.setChecked(false);
			
			Fragment fr;
			fr= new QuestionCollectionFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
		}
		else if(v.getId() == R.id.btn_study_sub3)
		{
			BTN_study_sub1.setChecked(false);
			BTN_study_sub2.setChecked(false);
			BTN_study_sub3.setChecked(true);
		}
		
		else if(v.getId() == R.id.btn_market_sub1)
		{
			BTN_market_sub1.setChecked(true);
			BTN_market_sub2.setChecked(false);
		}
		else if(v.getId() == R.id.btn_market_sub2)
		{
			BTN_market_sub1.setChecked(false);
			BTN_market_sub2.setChecked(true);
		}
	}
}
