package com.thread.ta2;

public class QueueObject {
	//是否被叫醒
	private boolean isNotified = false;

	public synchronized void doWait() throws InterruptedException {

		//没被叫醒就一直等待
		while (!isNotified) {
			this.wait();
		}

		//叫醒了就设置为没被叫醒
		this.isNotified = false;

	}

	public synchronized void doNotify() {
		this.isNotified = true;
		this.notify();
	}

	public boolean equals(Object o) {
		return this == o;
	}

}
