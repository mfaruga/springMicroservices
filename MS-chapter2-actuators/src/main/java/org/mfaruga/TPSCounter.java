package org.mfaruga;

import java.util.Calendar;
import java.util.concurrent.atomic.LongAdder;

public class TPSCounter {

	private LongAdder count;
	private final int treshold = 2;
	private Calendar expiry = null;	

	TPSCounter() {
		this.count = new LongAdder();
		this.expiry = Calendar.getInstance();
		this.expiry.add(Calendar.MINUTE, 1);
	}

	boolean isExpired() {
		return Calendar.getInstance().after(expiry);
	}

	boolean isWeak() {
		return (count.intValue() > treshold);
	}

	void increment() {
		count.increment();
	}

	public long getCount() {
		return count.longValue();
	}

}
