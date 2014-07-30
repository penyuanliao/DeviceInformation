package com.ane.android.functions;


import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREReadOnlyException;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;
//extends Activity
public class DeviceInfo  implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] arg1)
	{
		FREObject result = arg1[0];
		/** 設備名稱 **/
		String device  = android.os.Build.DEVICE;
		/** 模組號碼 **/
		String model   = android.os.Build.MODEL;
		/** 產品名稱 **/
		String product = android.os.Build.PRODUCT;
		/** Serial Number **/
		String serial = android.os.Build.SERIAL; // API Need Level 9
		/** 主機版名稱 **/
		String board = android.os.Build.BOARD;
		/** 品牌名稱 **/
		String brand = android.os.Build.BRAND;
		/** CPU + ABI **/
		String cpu_abi = android.os.Build.CPU_ABI;
		/** CPU + ABI **/
		String cpu_abi2 = android.os.Build.CPU_ABI2;
		/** 版本號碼 **/
		String display = android.os.Build.DISPLAY;
		/** 設備識別碼 **/
		String fingerprint = android.os.Build.FINGERPRINT;
		/** HOST **/
		String host = android.os.Build.HOST;
		/** 版本編號 **/
		String id = android.os.Build.ID;
		/** 設備描述 **/
		String tags = android.os.Build.TAGS;
		/** 設備種類 **/
		String type = android.os.Build.TYPE;
		/** USER **/
		String user = android.os.Build.USER;
		/** 製造商 **/
		String manufacturer = android.os.Build.MANUFACTURER;
		
		String osVersion = System.getProperty("os.version");
		
//		WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
//		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//		/** 取得wifi硬體位址 **/
//		String macAddress = wifiInfo.getMacAddress();
//		
//		String androidID = Secure.getString(this.getBaseContext().getContentResolver(), Secure.ANDROID_ID);
		
		try {
			
			result.setProperty("device", FREObject.newObject(device));
			result.setProperty("model", FREObject.newObject(model));
			result.setProperty("product", FREObject.newObject(product));
			result.setProperty("serial", FREObject.newObject(serial));
			result.setProperty("board", FREObject.newObject(board));
			result.setProperty("brand", FREObject.newObject(brand));
			result.setProperty("cpu_abi", FREObject.newObject(cpu_abi));
			result.setProperty("cpu_abi2", FREObject.newObject(cpu_abi2));
			result.setProperty("display", FREObject.newObject(display));
			result.setProperty("fingerprint", FREObject.newObject(fingerprint));
			result.setProperty("host", FREObject.newObject(host));
			result.setProperty("id", FREObject.newObject(id));
			result.setProperty("tags", FREObject.newObject(tags));
			result.setProperty("type", FREObject.newObject(type));
			result.setProperty("user", FREObject.newObject(user));
			result.setProperty("manufacturer", FREObject.newObject(manufacturer));
			result.setProperty("osVersion", FREObject.newObject(osVersion));
			
			WifiManager wifiManager = (WifiManager)context.getActivity().getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			/** 取得wifi硬體位址 **/
			String macAddress = wifiInfo.getMacAddress();
			
			String androidID = Secure.getString(context.getActivity().getContentResolver(), Secure.ANDROID_ID);
			
			TelephonyManager tmanager = (TelephonyManager)context.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
			String deviceID = tmanager.getDeviceId();
			
			result.setProperty("macAddress", FREObject.newObject(macAddress));
			result.setProperty("androidID", FREObject.newObject(androidID));
			result.setProperty("deviceID", FREObject.newObject(deviceID));
			//context.dispatchStatusEventAsync("DeviceID_EVENT", deviceID);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRETypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREInvalidObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREASErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FRENoSuchNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			
		}
		return result;
	}

}
