package com.ane.android.functions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class GetDeviceCPUInfo implements FREFunction 
{

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) 
	{
		FREObject result;
		RandomAccessFile reader;
		try {
			
			reader = new RandomAccessFile("/proc/stat", "r");
			
			
			
			String load = reader.readLine();
			
			String[] logs = load.split(" ");
			
			long idle1 = Long.parseLong(logs[4]);
			long cpu1 = Long.parseLong(logs[2]) + Long.parseLong(logs[3]) + Long.parseLong(logs[5]) 
					+ Long.parseLong(logs[6]) + Long.parseLong(logs[7]) + Long.parseLong(logs[8]);
			try 
			{
				Thread.sleep(360); //停止360豪秒
			}catch (Exception e)
			{
				context.dispatchStatusEventAsync("CPUProcesses.Error",e.toString());
			}
			reader.seek(0);//回到第一行
			load = reader.readLine();
			reader.close();
			
			logs = load.split(" ");
			
			long idle2 = Long.parseLong(logs[4]);
			long cpu2 = Long.parseLong(logs[2]) + Long.parseLong(logs[3]) + Long.parseLong(logs[5])
		            + Long.parseLong(logs[6]) + Long.parseLong(logs[7]) + Long.parseLong(logs[8]);
			float cpu_usage = (float)(cpu2 - cpu1)/ ((cpu2 + idle2) - (cpu1 + idle1));
			
			result = FREObject.newObject(cpu_usage);
			context.dispatchStatusEventAsync("CPUProcesses.Read.Usage", String.format("CPU: %.2f",  cpu_usage));
			
			return result;
			
		} catch (FileNotFoundException e) 
		{
			context.dispatchStatusEventAsync("CPUProcesses.Error",e.toString());
			e.printStackTrace();
		} catch (IOException e) 
		{
			context.dispatchStatusEventAsync("CPUProcesses.Error",e.toString());
			e.printStackTrace();
		} catch (FREWrongThreadException e) 
		{
			context.dispatchStatusEventAsync("CPUProcesses.Error",e.toString());
			e.printStackTrace();
		}
		
		return null;
	}

}
