package com.ane.android.functions;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREReadOnlyException;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

public class DeviceVersion implements FREFunction {

	@Override
	public FREObject call(FREContext _context, FREObject[] arg1) 
	{
		FREObject result = arg1[0];
		String CODENAME = android.os.Build.VERSION.CODENAME;
		int SDK = android.os.Build.VERSION.SDK_INT;
		String RELEASE = android.os.Build.VERSION.RELEASE;
		String INCREMENTAL = android.os.Build.VERSION.INCREMENTAL;
		
		try {
			
			//result = FREObject.newObject("com_ane_android_os_build_Version", null); //原因不明建立新的回傳null	
			result.setProperty("codeName", FREObject.newObject(CODENAME));
			result.setProperty("sdk", FREObject.newObject((int)SDK));
			result.setProperty("release", FREObject.newObject(RELEASE));
			result.setProperty("incremental", FREObject.newObject(INCREMENTAL));
			
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
			_context.dispatchStatusEventAsync("exception_error", e.toString());
		}
		return result;
	}

}
