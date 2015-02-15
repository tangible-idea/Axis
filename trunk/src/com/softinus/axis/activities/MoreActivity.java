package com.softinus.axis.activities;

import com.softinus.axis.util.Global;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MoreActivity extends BaseAxisAcivity implements OnClickListener
{
	ImageView IMG_p1, IMG_p2, IMG_p3, IMG_p4, IMG_p5;
	Button BTN_close;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		
		BTN_close= (Button)findViewById(R.id.btn_more);
		BTN_close.setOnClickListener(this);
		
		IMG_p1= (ImageView)findViewById(R.id.p1);
		IMG_p2= (ImageView)findViewById(R.id.p2);
		IMG_p3= (ImageView)findViewById(R.id.p3);
		IMG_p4= (ImageView)findViewById(R.id.p4);
		IMG_p5= (ImageView)findViewById(R.id.p5);
		
		switch(Global.s_nPencilCount)
		{
		case 1:
			IMG_p1.setEnabled(true);
			IMG_p2.setEnabled(false);
			IMG_p3.setEnabled(false);
			IMG_p4.setEnabled(false);
			IMG_p5.setEnabled(false);
			break;
		case 2:
			IMG_p1.setEnabled(true);
			IMG_p2.setEnabled(true);
			IMG_p3.setEnabled(false);
			IMG_p4.setEnabled(false);
			IMG_p5.setEnabled(false);
			break;
		case 3:
			IMG_p1.setEnabled(true);
			IMG_p2.setEnabled(true);
			IMG_p3.setEnabled(true);
			IMG_p4.setEnabled(false);
			IMG_p5.setEnabled(false);
			break;
		case 4:
			IMG_p1.setEnabled(true);
			IMG_p2.setEnabled(true);
			IMG_p3.setEnabled(true);
			IMG_p4.setEnabled(true);
			IMG_p5.setEnabled(false);
			break;
		case 5:
			IMG_p1.setEnabled(true);
			IMG_p2.setEnabled(true);
			IMG_p3.setEnabled(true);
			IMG_p4.setEnabled(true);
			IMG_p5.setEnabled(true);
			break;
		}
	}

	@Override
	public void onClick(View v)
	{
		finish();
	}
	
	
}
