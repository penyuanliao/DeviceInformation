package com.ane.android.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ane.android.DeviceExtensionContext;

public class GetDeviceBatteryInfo implements FREFunction 
{
	@Override
	public FREObject call(FREContext context, FREObject[] args) 
	{
		FREObject Result = args[0];
		
		DeviceExtensionContext _context = (DeviceExtensionContext) context;
		
		_context.batteryBroadcastReceiver.setValues(_context, Result);
		
		
		
		return null;
	}

}
