package com.softinus.axis.activities;

import java.util.Date;

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
import android.widget.TextView;
import android.widget.ToggleButton;

import com.softinus.axis.data.QuestionData;
import com.softinus.axis.data.QuestionStorage;
import com.softinus.axis.fragments.FragmentHistory;
import com.softinus.axis.fragments.FragmentMarket;
import com.softinus.axis.fragments.FragmentQuestionCollection;
import com.softinus.axis.fragments.FragmentSelectCollection;
import com.softinus.axis.fragments.FragmentSelectQuestion;

public class MainActivity2 extends BaseAxisAcivity implements OnClickListener
{
	private Button BTN_more, BTN_market, BTN_record, BTN_study;
	private ToggleButton BTN_study_sub1, BTN_study_sub2, BTN_study_sub3;
	private ToggleButton BTN_market_sub1, BTN_market_sub2;
	
	private Button BTN_yearup, BTN_yeardown;
	private TextView TXT_year;
	
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
		
		BTN_yearup= (Button) findViewById(R.id.btn_year_up);
		BTN_yeardown= (Button) findViewById(R.id.btn_year_down);
		TXT_year= (TextView) findViewById(R.id.txt_history_year);
		
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
		
		{
	        QuestionStorage.arrQList.add(new QuestionData(1, new Date(), "hello", 1, 100, "1+1", "1+1", "2", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(2, new Date(), "hello", 1, 100, "1+2", "1+2", "3", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(3, new Date(), "hello", 1, 100, "2+2", "2+2", "4", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(4, new Date(), "hello", 1, 100, "3+3", "3+3", "6", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(5, new Date(), "hello", 1, 100, "4+4", "4+4", "8", "", ""));

	        QuestionStorage.arrQList.add(new QuestionData(6, new Date(), "hello", 2, 110, "1-1", "1-1", "0", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(7, new Date(), "hello", 2, 110, "2-2", "2-2", "0", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(8, new Date(), "hello", 2, 110, "2-2", "2-2", "0", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(9, new Date(), "hello", 2, 110, "3-3", "3-3", "0", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(10, new Date(), "hello", 2, 110, "4-4", "4-4", "0", "", ""));
	       
	        QuestionStorage.arrQList.add(new QuestionData(11, new Date(), "hello", 3, 120, "1x1", "1x1", "1", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(12, new Date(), "hello", 3, 120, "1x2", "1x2", "2", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(13, new Date(), "hello", 3, 120, "2x2", "2x2", "4", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(14, new Date(), "hello", 3, 120, "3x3", "3x3", "9", "", ""));
	        QuestionStorage.arrQList.add(new QuestionData(15, new Date(), "hello", 3, 120, "4x4", "4x4", "16", "", ""));
	        
			BTN_market.setBackgroundResource(R.drawable.market_off);
			BTN_study.setBackgroundResource(R.drawable.study_on);
			BTN_record.setBackgroundResource(R.drawable.recode_off);
			
			BTN_study_sub1.setVisibility(View.VISIBLE);
			BTN_study_sub2.setVisibility(View.VISIBLE);
			BTN_study_sub3.setVisibility(View.VISIBLE);
			
			BTN_market_sub1.setVisibility(View.GONE);
			BTN_market_sub2.setVisibility(View.GONE);
			
			BTN_yearup.setVisibility(View.GONE);
			BTN_yeardown.setVisibility(View.GONE);
			TXT_year.setVisibility(View.GONE);
			
			BTN_study_sub1.setChecked(true);
			BTN_study_sub2.setChecked(false);
			BTN_study_sub3.setChecked(false);
			
			Fragment fr;
			fr= new FragmentSelectQuestion();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
		}
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
			BTN_market.setBackgroundResource(R.drawable.market_on);
			BTN_study.setBackgroundResource(R.drawable.study_off);
			BTN_record.setBackgroundResource(R.drawable.recode_off);
			
			BTN_study_sub1.setVisibility(View.GONE);
			BTN_study_sub2.setVisibility(View.GONE);
			BTN_study_sub3.setVisibility(View.GONE);
			
			BTN_market_sub1.setVisibility(View.VISIBLE);
			BTN_market_sub2.setVisibility(View.VISIBLE);
			
			BTN_yearup.setVisibility(View.GONE);
			BTN_yeardown.setVisibility(View.GONE);
			TXT_year.setVisibility(View.GONE);
			
			Fragment fr;
			fr= new FragmentMarket();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();

		}
		else if(v.getId() == R.id.btn_study)
		{
			BTN_market.setBackgroundResource(R.drawable.market_off);
			BTN_study.setBackgroundResource(R.drawable.study_on);
			BTN_record.setBackgroundResource(R.drawable.recode_off);
			
			BTN_study_sub1.setVisibility(View.VISIBLE);
			BTN_study_sub2.setVisibility(View.VISIBLE);
			BTN_study_sub3.setVisibility(View.VISIBLE);
			
			BTN_market_sub1.setVisibility(View.GONE);
			BTN_market_sub2.setVisibility(View.GONE);
			
			BTN_yearup.setVisibility(View.GONE);
			BTN_yeardown.setVisibility(View.GONE);
			TXT_year.setVisibility(View.GONE);
			
			BTN_study_sub1.setChecked(true);
			BTN_study_sub2.setChecked(false);
			BTN_study_sub3.setChecked(false);
			
			Fragment fr;
			fr= new FragmentSelectQuestion();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
			

		}
		else if(v.getId() == R.id.btn_record)
		{
			BTN_market.setBackgroundResource(R.drawable.market_off);
			BTN_study.setBackgroundResource(R.drawable.study_off);
			BTN_record.setBackgroundResource(R.drawable.recode_on);
			
			BTN_study_sub1.setVisibility(View.GONE);
			BTN_study_sub2.setVisibility(View.GONE);
			BTN_study_sub3.setVisibility(View.GONE);
			
			BTN_market_sub1.setVisibility(View.GONE);
			BTN_market_sub2.setVisibility(View.GONE);
			
			BTN_yearup.setVisibility(View.VISIBLE);
			BTN_yeardown.setVisibility(View.VISIBLE);
			TXT_year.setVisibility(View.VISIBLE);
			
			Fragment fr;
			fr= new FragmentHistory();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
		}
		
		else if(v.getId() == R.id.btn_study_sub1)
		{
			BTN_study_sub1.setChecked(true);
			BTN_study_sub2.setChecked(false);
			BTN_study_sub3.setChecked(false);
			
			Fragment fr;
			fr= new FragmentSelectQuestion();
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
			fr= new FragmentSelectCollection();
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
