package com.ane.android.battery;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREReadOnlyException;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
import com.ane.android.*;
import com.ane.android.battery.event.BatteryStatus;

public class BatteryBroadcastReceiver extends BroadcastReceiver {

	private final int defaultValue = 0;
	
	protected DeviceExtensionContext _context; 
	/** 有沒有電池 **/
	protected boolean present = false;
	/** 電源狀態 **/
	protected int status = 0;
	/** 目前電源 **/
	protected int level = 0;
	/** 總電量 **/
	protected int scale = 0;
	/** 溫度 **/
	protected int temperature = 0;
	/** 電壓 **/
	protected int voltage = 0;
	/** 電池健康狀態 **/
	protected int health = 0;
	/** 充電來源 **/
	protected int plugged = 0;
	/** 電池種類 **/
	protected String technology = "";
	
	public BatteryBroadcastReceiver(DeviceExtensionContext context)
	{
		_context = context;
	}
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
		level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, defaultValue);
		scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, defaultValue);
		voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, defaultValue);
		status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, defaultValue);
		health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, defaultValue);
		plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, defaultValue);
		temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, defaultValue);
		technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
		
		if (_context != null)
		{
			try
			{
				StringBuilder batteryValues = new StringBuilder("present=");
				batteryValues.append(String.valueOf(present));
				batteryValues.append("&level=").append(String.valueOf(level));
				batteryValues.append("&scale=").append(String.valueOf(scale));
				batteryValues.append("&temperature=").append(String.valueOf(temperature));
				batteryValues.append("&voltage=").append(String.valueOf(voltage));
				batteryValues.append("&status=").append(String.valueOf(status));
				batteryValues.append("&health=").append(String.valueOf(health));
				batteryValues.append("&plugged=").append(String.valueOf(plugged));
				batteryValues.append("&technology=").append(String.valueOf(technology));
				_context.dispatchStatusEventAsync(BatteryStatus.BATTERY_CHANGE, batteryValues.toString());
			}catch (Exception e)
			{
				_context.dispatchStatusEventAsync(BatteryStatus.BATTERY_ERROR, e.toString());
			}

		}
		
	}

	public void setValues(DeviceExtensionContext context, FREObject obj)
	{
		context.dispatchStatusEventAsync("setValue", "setValue");
		try
		{
			obj.setProperty("present", FREObject.newObject(present));
			obj.setProperty("status", FREObject.newObject(present));
			obj.setProperty("level", FREObject.newObject(level));
			obj.setProperty("scale", FREObject.newObject(scale));
			obj.setProperty("temperature", FREObject.newObject(temperature));
			obj.setProperty("voltage", FREObject.newObject(voltage));
			obj.setProperty("health", FREObject.newObject(health));
			obj.setProperty("plugged", FREObject.newObject(plugged));
			obj.setProperty("technology", FREObject.newObject(technology));
			
			
		}catch (IllegalStateException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (FREASErrorException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (FRENoSuchNameException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (FREReadOnlyException e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			context.dispatchStatusEventAsync("exception_error", e.toString());
		}
	}
	
}
