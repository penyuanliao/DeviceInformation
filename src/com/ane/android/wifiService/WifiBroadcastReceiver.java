package com.ane.android.wifiService;

import com.ane.android.DeviceExtensionContext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Describes the state of any Wifi connection that is active or is in the process of being set up.
 * @author Benson.liao
 * @version 1.0.0
 * @category android.net.wifi.WifiInfo
 * **/
public class WifiBroadcastReceiver extends BroadcastReceiver 
{
	/** AIR Extension **/
	protected DeviceExtensionContext _context;  
	/** 訊號強度 **/
	protected int strength = 0;
	
	protected String BSSID = "";
	
	protected boolean hiddenSSID = false;
	
	protected int ipAddress = 0;
	
	protected int linkSpeed = 0;
	
	protected String macAddress = "";
	
	protected int networkID = 0;
	
	protected int rssi = 0;
	
	protected String SSID = "";
	
	protected String supplicantState = "";
	/** init **/
	public WifiBroadcastReceiver(DeviceExtensionContext context)
	{
		_context = context;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		WifiManager wifiManager = (WifiManager)_context.getActivity().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		
		
		
		
		
		this.BSSID = wifiInfo.getBSSID();
		this.hiddenSSID = wifiInfo.getHiddenSSID();
		this.ipAddress = wifiInfo.getIpAddress();
		this.linkSpeed = wifiInfo.getLinkSpeed();//連接速度
		this.macAddress = wifiInfo.getMacAddress();
		this.networkID = wifiInfo.getNetworkId();
		this.rssi = wifiInfo.getRssi();
		this.SSID = wifiInfo.getSSID();
		this.supplicantState = wifiInfo.getSupplicantState().toString();
		this.strength = WifiManager.calculateSignalLevel(rssi, 5);
		
		if (_context != null)
		{
			try
			{
				StringBuilder wifiValues = new StringBuilder(String.valueOf("strength="));
				wifiValues.append(this.strength);
				wifiValues.append("&hiddenSSID=").append(this.hiddenSSID);
				wifiValues.append("&ipAddress=").append(this.ipAddress);
				wifiValues.append("&linkSpeed=").append(this.linkSpeed);
				wifiValues.append("&macAddress=").append(this.macAddress);
				wifiValues.append("&networkID=").append(this.networkID);
				wifiValues.append("&rssi=").append(this.rssi);
				wifiValues.append("&SSID=").append(this.SSID);
				wifiValues.append("&supplicantState=").append(this.supplicantState);
				wifiValues.append("&BSSID=").append(this.BSSID);
				_context.dispatchStatusEventAsync(WifiInfoStatus.RSSI_CHANGED_ACTION, wifiValues.toString());
				
			}catch (Exception e)
			{
				_context.dispatchStatusEventAsync("EROOR", e.toString());
			}

			
		}
		
	}

}
