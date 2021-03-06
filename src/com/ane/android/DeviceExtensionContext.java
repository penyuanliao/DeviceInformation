package com.ane.android;

import java.util.Map;
import java.util.HashMap;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.ane.android.functions.*;
import com.ane.android.wifiService.WifiBroadcastReceiver;
import com.ane.android.functions.unregisterWifiInfo;
import com.ane.android.battery.BatteryBroadcastReceiver;

public class DeviceExtensionContext extends FREContext {
	
	public BatteryBroadcastReceiver batteryBroadcastReceiver;
	
	public WifiBroadcastReceiver wifiBroadcastReceiver;
	
	@Override
	public void dispose() 
	{
		getActivity().unregisterReceiver(batteryBroadcastReceiver);
		
		batteryBroadcastReceiver = null;
		
		getActivity().unregisterReceiver(wifiBroadcastReceiver);
		
		wifiBroadcastReceiver = null;
	}

	@Override
	public Map<String, FREFunction> getFunctions() 
	{
		Map<String, FREFunction> funcs = new HashMap<String, FREFunction>();
		
		funcs.put("isSupported", 		new IsSupported());
		funcs.put("getVersion", 		new DeviceVersion());
		funcs.put("getDeviceInfo", 		new DeviceInfo());
		funcs.put("registerBatteryInfo",new DeviceBatteryInfo());
		funcs.put("getBatteryInfo", 	new GetDeviceBatteryInfo());
		funcs.put("getWifiMacAddress", 	new GetWifiMacAddress());
		funcs.put("getAndroidID", 		new GetAndroidID());
		funcs.put("getDeviceID", 		new GetDeviceID());
		funcs.put("getMemoryInfo",		new GetMemoryInfo());
		funcs.put("getCPUProcessInfo", 	new GetDeviceCPUInfo());
		funcs.put("registerWifiInfo", 	new registerWifiInfo());
		funcs.put("unregisterWifiInfo", new unregisterWifiInfo());
		
		return funcs;
	}

}
