package cc.sukazyo.untitled.test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class OutOfMemoryModel {
	
	// 测试用的默认值
	public static String NOW = "q09urv 9U rp;(U frpU@VNROUVRP Q";
	
	// OOM标记
	public static volatile boolean stopMark = false;
	
	// 运行时间标记
	static long start = 0;
	
	// 测试主线程
	public static class TestIt extends Thread {
		
		// OOM逻辑
		@SuppressWarnings("all")
		public void run() {
			StringBuilder str = new StringBuilder(NOW);
			while (true) {
				try {
					TestIt thread = new TestIt();
					thread.start();
					str.append(NOW).append(Math.random());
					NOW = str.toString();
				} catch (OutOfMemoryError e) {
					stopMark = true;
				}
			}
		}
		
	}
	
	// OOM 处理线程，用于抓取OOM异常计数和程序运行控制
	public static class GCOOMCollection extends Thread {
		
		// 程序运行控制逻辑
		@SuppressWarnings("all")
		public void run () {
			GarbageCollectorMXBean fullGcMXBean = null;
			List<GarbageCollectorMXBean> gcMxBeanList = ManagementFactory.getGarbageCollectorMXBeans();
			for(GarbageCollectorMXBean gcMxBean : gcMxBeanList){
				if(gcMxBean.getName().endsWith("MarkSweep")){
					fullGcMXBean = gcMxBean;
					break;
				}
			}
			int listSize;
			final String thru = Thread.currentThread().getName();
			while(!stopMark) {
				System.out.println("[" + thru + "]" + "We have " + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " m of total memory. We have " + (Runtime.getRuntime().freeMemory() / (1024 * 1024)) + " m memory left. and we triggered full gc for " + fullGcMXBean.getCollectionCount() + " times");
			}
			System.out.println("oom! time used " + ((System.currentTimeMillis() - start) / 1000D) + "s");
			System.exit(0);
		}
	}
	
	@SuppressWarnings("all")
	public static void main (String[] args) {

		/*
		// 开启OOM处理线程
		GCOOMCollection thr = new GCOOMCollection();
		thr.start();
		
		// 开启测试
		while (true) {
			TestIt a = new TestIt();
			a.start();
		}
		 */
		heapspaceOOM(false);
	}

	public static void arrayOOM(boolean falsetostart) {
		if (falsetostart) return;
		long t = System.currentTimeMillis();
		try {
			int[] list = new int[Integer.MAX_VALUE];
		} catch (OutOfMemoryError e) {
			System.out.println("OOM! Time used: " + (System.currentTimeMillis() - t) + " ms");
			e.printStackTrace();
		}
	}

	public static void heapspaceOOM(boolean falsetostart) {
		if (falsetostart) return;
		long t = System.currentTimeMillis();
		try {
			while (true) {
				int[] list = new int[Integer.MAX_VALUE - 5];
			}
		} catch (OutOfMemoryError e) {
			System.out.println("OOM! Time used: " + (System.currentTimeMillis() - t) + " ms");
			e.printStackTrace();
		}
	}
	
}
