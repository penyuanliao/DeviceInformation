package com.ane.android.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ane.android.DeviceExtensionContext;

public class unregisterWifiInfo implements FREFunction 
{

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) 
	{
		DeviceExtensionContext context = (DeviceExtensionContext) arg0;
		
		context.getActivity().unregisterReceiver(context.wifiBroadcastReceiver);
		
		context.wifiBroadcastReceiver = null;
		
		return null;
	}

}
