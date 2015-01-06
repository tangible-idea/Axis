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

import com.softinus.axis.fragments.FragmentMarket;
import com.softinus.axis.fragments.FragmentStudy;

public class MainActivity2 extends BaseAxisAcivity implements OnClickListener
{
	private Button BTN_more, BTN_market, BTN_record, BTN_study;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);
		
		BTN_more= (Button) findViewById(R.id.btn_more);
		BTN_market= (Button) findViewById(R.id.btn_market);
		BTN_record= (Button) findViewById(R.id.btn_record);
		BTN_study= (Button) findViewById(R.id.btn_study);
		
		BTN_more.setOnClickListener(this);
		BTN_market.setOnClickListener(this);
		BTN_record.setOnClickListener(this);
		BTN_study.setOnClickListener(this);
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
			Fragment fr;
			fr= new FragmentMarket();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();

		}
		else if(v.getId() == R.id.btn_study)
		{
			Fragment fr;
			fr= new FragmentStudy();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction fragmentTransaction = fm.beginTransaction();
			fragmentTransaction.replace(R.id.fragment_place, fr);
			fragmentTransaction.commit();
		}
		else if(v.getId() == R.id.btn_record)
		{
			
		}
		
	}
}
