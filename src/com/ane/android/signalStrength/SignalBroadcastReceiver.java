package com.ane.android.signalStrength;

import com.ane.android.DeviceExtensionContext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/** Contains phone signal strength related information. **/
public class SignalBroadcastReceiver extends BroadcastReceiver 
{
	/** AIR Extension **/
	protected DeviceExtensionContext _context;  
	
	protected int cdmaDbm = 0;//展頻3G 信號強度
	
	protected int cdmaEcio = 0;//展頻3G 載干比
	protected int evdoDbm = 0;//3G 信號強度
	protected int evdoEcio = 0;
	protected int evdoSnr = 0;
	protected int gsmBitErrorRate = 0; //2G 誤碼率
	protected int gsmSignalStrength = 0; //2G 信號強度 
	
	protected boolean isGsm = false;//是否GSM信號 2G or 3G
	
	
	/** init **/
	public SignalBroadcastReceiver(DeviceExtensionContext context)
	{
		_context = context;
	}
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub

	}
	

}
