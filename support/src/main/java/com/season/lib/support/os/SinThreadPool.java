package com.season.lib.support.os;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 单线程线程池
 *
 */
public class SinThreadPool extends AbsThreadPool {
	@Override
	protected int getCorePoolSize() {
		return 1;
	}

	@Override
	protected int getMaximumPoolSize() {
		return 1;
	}

	@Override
	protected long getKeepAliveTime() {
		return 500;
	}

	@Override
	protected TimeUnit getTimeUnit() {
		return TimeUnit.MILLISECONDS;
	}

	@Override
	protected BlockingQueue<Runnable> newQueue() {
		return new PriorityBlockingQueue<Runnable>();
	}
	
	@Override
	protected Runnable onAddTask(Runnable newTask) {
		return super.onAddTask(new PriorityRunnable(newTask));
	}


	/**
	 * 递增排序runnable
	 */
	public static class PriorityRunnable implements Runnable,Comparable<PriorityRunnable> {
		private static long LAST_PRIORITY = 0;
		private Runnable mTask;
		private long priority;
		
		public PriorityRunnable(Runnable newTask){
			mTask = newTask;
			priority = ++LAST_PRIORITY;
		}
		@Override
		public int compareTo(PriorityRunnable another) {
			return (int) (another.priority - priority);
		}

		@Override
		public void run() {
			if(mTask != null){
				mTask.run();
				mTask = null;
			}
		}
	}
}
