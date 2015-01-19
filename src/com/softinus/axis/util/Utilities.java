package com.softinus.axis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Utilities
{
	public static void setGlobalFont(View view) 
	{
        if (view != null) {
            if (view instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) view;
                int len = vg.getChildCount();
                for (int i = 0; i < len; i++) {
                    View v = vg.getChildAt(i);
                    if (v instanceof TextView) {
                        ((TextView) v).setTypeface(Typeface.MONOSPACE);
                    }
                    setGlobalFont(v);
                }
            }
        } else {
            Log.e("setGlobalFont", "This is null  ");
        }
 
    }
	
	public static void WriteAxisLog(Context context, String strFileName, String strAct, int nCode)
	{
		{	// 로그 파일 쓰기 부분
			long now = System.currentTimeMillis();						// 현재 시간을 msec으로 구한다.	
			Date date = new Date(now);									// 현재 시간을 저장 한다.	
			SimpleDateFormat SDF_Format = new SimpleDateFormat("yyyy:MM:dd:HH:mm");						// 시간 포맷 지정

			// 지정된 포맷으로 String 타입 리턴 
			String strTemp = SDF_Format.format(date);
			strTemp += " "+strAct+" ";
			strTemp += " "+nCode+"\n";
			
			WriteTextFile(context, "history.txt", strTemp);
		}
	}
	
	  public static boolean WriteTextFile(Context context, String strFileName, String strBuf) {
	        try {
	            File file = new File( Environment.getExternalStorageDirectory().getPath() + "/"+ context.getPackageName(), strFileName );
	            
	            if( !file.exists() )
	            {
	            	file.getParentFile().mkdirs();
	            	//file.mkdirs();
	            }
	            
	            FileOutputStream fos = new FileOutputStream(file, true);
	            Writer out = new OutputStreamWriter(fos, "UTF-8");
	            out.write(strBuf);
	            out.close();
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	 
	        return true;
	    }
	 



		public static String ReadTextFile(Context context, String strFileName)
		{
	        String text = null;
	        try {
	            File file = new File( Environment.getExternalStorageDirectory().getPath() + "/"+ context.getPackageName(), strFileName );
	           	            
	            FileInputStream fis = new FileInputStream(file);
	            Reader in = new InputStreamReader(fis);
	            int size = fis.available();
	            char[] buffer = new char[size];
	            in.read(buffer);
	            in.close();
	 
	            text = new String(buffer);
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	 
	        return text;
	    }
}
