package com.ane.android;

import java.util.Map;
import java.util.HashMap;

import android.app.ActivityManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.ane.android.functions.*;
import com.ane.android.battery.BatteryBroadcastReceiver;

public class DeviceExtensionContext extends FREContext {
	
	public BatteryBroadcastReceiver batteryBroadcastReceiver;
	
	@Override
	public void dispose() 
	{
		getActivity().unregisterReceiver(batteryBroadcastReceiver);
		
		batteryBroadcastReceiver = null;
		
	}

	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Map<String, FREFunction> funcs = new HashMap<String, FREFunction>();
		
		funcs.put("isSupported", 		new IsSupported());
		funcs.put("getVersion", 		new DeviceVersion());
		funcs.put("getDeviceInfo", 		new DeviceInfo());
		funcs.put("startBatteryInfo", 	new DeviceBatteryInfo());
		funcs.put("getBatteryInfo", 	new GetDeviceBatteryInfo());
		funcs.put("getWifiMacAddress", 	new GetWifiMacAddress());
		funcs.put("GetAndroidID", 		new GetAndroidID());
		funcs.put("GetDeviceID", 		new GetDeviceID());
		funcs.put("GetMemoryInfo",		new GetMemoryInfo());
		return funcs;
	}

}
