package com.ane.android.functions;

import android.content.Context;
import android.telephony.TelephonyManager;

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
		TelephonyManager tmanager = (TelephonyManager)context.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		String DeviceID = tmanager.getDeviceId();
		
		try 
		{
			result = FREObject.newObject(DeviceID);
			
		} catch (FREWrongThreadException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
