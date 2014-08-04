package com.ane.android.functions;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.ane.android.DeviceExtensionContext;
import com.ane.android.wifiService.WifiBroadcastReceiver;

public class registerWifiInfo implements FREFunction
{

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) 
	{
		DeviceExtensionContext _context = (DeviceExtensionContext) context;
		
		Activity activity = _context.getActivity();
		
		_context.wifiBroadcastReceiver = new WifiBroadcastReceiver(_context);
		
		activity.registerReceiver(_context.wifiBroadcastReceiver, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
		
		return null;
	}

}
