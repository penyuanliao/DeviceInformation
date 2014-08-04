package com.ane.android.functions;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class GetWifiMacAddress implements FREFunction 
{

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) 
	{
		FREObject result = arg1[0];
		WifiManager wifiManager = (WifiManager)context.getActivity().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		/** 取得wifi硬體位址 **/
		String macAddress = wifiInfo.getMacAddress();
		
		try 
		{
			result = FREObject.newObject(macAddress);
			
		} catch (FREWrongThreadException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
