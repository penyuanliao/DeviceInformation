package com.ane.android.functions;

import android.provider.Settings.Secure;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class GetAndroidID implements FREFunction 
{

	@Override
	public FREObject call(FREContext context, FREObject[] arg1)
	{
		FREObject deviceId = arg1[0];
		try 
		{
			String id = Secure.getString(context.getActivity().getContentResolver(), Secure.ANDROID_ID);
			deviceId = FREObject.newObject(id);
			context.dispatchStatusEventAsync("DeviceID_EVENT", id);
		} catch (FREWrongThreadException e)
		{
			e.printStackTrace();
		}
		return deviceId;
	}

}
