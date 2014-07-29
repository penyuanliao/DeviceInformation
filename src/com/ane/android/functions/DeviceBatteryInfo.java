package com.ane.android.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;


import com.ane.android.DeviceExtensionContext;
import com.ane.android.battery.BatteryBroadcastReceiver;

public class DeviceBatteryInfo implements FREFunction 
{
	
	@Override
	public FREObject call(FREContext context, FREObject[] args) 
	{
		
		DeviceExtensionContext _context = (DeviceExtensionContext) context;
		
		Activity activity = _context.getActivity();
		
		_context.batteryBroadcastReceiver = new BatteryBroadcastReceiver(_context);
		
		activity.registerReceiver(_context.batteryBroadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		
		return null;
	}

}
