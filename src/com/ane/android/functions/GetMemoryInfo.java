package com.ane.android.functions;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class GetMemoryInfo implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] arg1) 
	{
		
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
		
		return null;
	}

}
