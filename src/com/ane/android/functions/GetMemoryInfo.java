package com.ane.android.functions;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

import com.adobe.fre.FREASErrorException;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FRENoSuchNameException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREReadOnlyException;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

public class GetMemoryInfo implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) 
	{
		FREObject result = arg1[0];
		
		// Device memory data
		ActivityManager activityManager = (ActivityManager) context.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
		
		Runtime runtime = Runtime.getRuntime();
		// Runtime data memory numbers are in bytes (dalvik process?)
		int cpuNums = runtime.availableProcessors();
		long totalMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();
		context.dispatchStatusEventAsync("ActivityManager.Runtime",String.format("cpuNums: %d totalMemory: %d freeMemory: %d", cpuNums, totalMemory, freeMemory));
		// Debug data (system wide) memory numbers are in bytes
		long nativeHeapSize = Debug.getNativeHeapSize();
		long nativeHeapAllocatedSize = Debug.getNativeHeapAllocatedSize();
		long nativeHeapFreeSize = Debug.getNativeHeapFreeSize();
		context.dispatchStatusEventAsync("Debug.NativeSize",String.format("nativeHeapSize: %d nativeHeapAllocatedSize: %d nativeHeapFreeSize: %d", nativeHeapSize, nativeHeapAllocatedSize, nativeHeapFreeSize));
		// Debug memoryinfo (process specific) memory numbers are in kb
		Debug.MemoryInfo debugMemoryInfo = new Debug.MemoryInfo();
		Debug.getMemoryInfo(debugMemoryInfo);
		
		context.dispatchStatusEventAsync("Debug.MemoryInfo.Dalvik", String.format("Debug.MemoryInfo.dalvik: %d pss, %d shared, %d private", debugMemoryInfo.dalvikPss, debugMemoryInfo.dalvikSharedDirty, debugMemoryInfo.dalvikPrivateDirty));
		context.dispatchStatusEventAsync("Debug.MemoryInfo.Native", String.format("Debug.MemoryInfo.native: %d pss, %d shared, %d private", debugMemoryInfo.nativePss, debugMemoryInfo.nativeSharedDirty, debugMemoryInfo.nativePrivateDirty));
		context.dispatchStatusEventAsync("Debug.MemoryInfo.Other", String.format("Debug.MemoryInfo.other: %d pss, %d shared, %d private", debugMemoryInfo.otherPss, debugMemoryInfo.otherSharedDirty, debugMemoryInfo.otherPrivateDirty));
		
		// Device "memory class", this is how much ram each app is allowed in MB
		int memoryClass = activityManager.getMemoryClass();
		
		// Total device memory status in bytes
		ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		
		context.dispatchStatusEventAsync("ActivityManager.memoryClass",String.format("ActivityManager.memoryInfo %d availMem, %b lowMemory, %d threshold %d memoryClass", memoryInfo.availMem, memoryInfo.lowMemory, memoryInfo.threshold, memoryClass));
		
		try 
		{
			
			result.setProperty("cupNums", FREObject.newObject(cpuNums));
			result.setProperty("totalMemory", FREObject.newObject(totalMemory));
			result.setProperty("freeMemory", FREObject.newObject(freeMemory));
			
			result.setProperty("nativeHeapSize", FREObject.newObject(nativeHeapSize));
			result.setProperty("nativeHeapAllocatedSize", FREObject.newObject(nativeHeapAllocatedSize));
			result.setProperty("nativeHeapFreeSize", FREObject.newObject(nativeHeapFreeSize));
			
			result.setProperty("dalvikPss", FREObject.newObject(debugMemoryInfo.dalvikPss));
			result.setProperty("dalvikSharedDirty", FREObject.newObject(debugMemoryInfo.dalvikSharedDirty));
			result.setProperty("dalvikPrivateDirty", FREObject.newObject(debugMemoryInfo.dalvikPrivateDirty));
			
			result.setProperty("nativePss", FREObject.newObject(debugMemoryInfo.nativePss));
			result.setProperty("nativeSharedDirty", FREObject.newObject(debugMemoryInfo.nativeSharedDirty));
			result.setProperty("nativePrivateDirty", FREObject.newObject(debugMemoryInfo.nativePrivateDirty));
			
			result.setProperty("otherPss", FREObject.newObject(debugMemoryInfo.otherPss));
			result.setProperty("otherSharedDirty", FREObject.newObject(debugMemoryInfo.otherSharedDirty));
			result.setProperty("otherPrivateDirty", FREObject.newObject(debugMemoryInfo.otherPrivateDirty));
			
			result.setProperty("memoryClass", FREObject.newObject(memoryClass));
			result.setProperty("memoryInfoAvailMem", FREObject.newObject(memoryInfo.availMem));
			result.setProperty("memoryInfoLowMemory", FREObject.newObject(memoryInfo.lowMemory));
			result.setProperty("memoryInfoThreshold", FREObject.newObject(memoryInfo.threshold));
			
			
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
		} catch (FREReadOnlyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
