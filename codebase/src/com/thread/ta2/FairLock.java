package com.thread.ta2;

import java.util.ArrayList;
import java.util.List;

public class FairLock {
	private boolean isLocked = false;
	private Thread lockingThread = null;
	private List<QueueObject> waitingThreads = new ArrayList<QueueObject>();

	public void lock() throws InterruptedException {
		//新建一个队列的对象
		QueueObject queueObject = new QueueObject();
		boolean isLockedForThisThread = true;//实现锁的可重入
		//先加入等待队列
		synchronized (this) {
			waitingThreads.add(queueObject);
		}

/*	//实现重入，
	while(isLockedForThisThread){
			synchronized (this){
				isLockedForThisThread=isLocked || waitingThreads.get(0) != queueObject;
				if(!isLockedForThisThread){
					isLocked=true;
					waitingThreads.remove(queueObject);
					lockingThread = Thread.currentThread();
					return;
				}
			}
		}*/

		try {
			queueObject.doWait();
		} catch (InterruptedException e) {
			synchronized (this) {
				waitingThreads.remove(queueObject);
			}
			throw e;
		}
	}

	public synchronized void unlock() {
		if (this.lockingThread != Thread.currentThread()) {
			throw new IllegalMonitorStateException("Calling thread has not locked this lock");
		}
		isLocked = false;
		lockingThread = null;
		if (waitingThreads.size() > 0) {
			waitingThreads.get(0).doNotify();
		}
	}
}