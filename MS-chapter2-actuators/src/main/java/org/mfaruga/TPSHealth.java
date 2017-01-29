package org.mfaruga;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TPSHealth implements HealthIndicator {

	TPSCounter counter = new TPSCounter();

	@Override
	public Health health() {
		boolean notHealthy = counter.isWeak();
		if (notHealthy) {
			return Health.outOfService().withDetail("Too many requests: " + counter.getCount(), "OutOfService").build();
		}
		return Health.up().build();
	}

	void updateTx() {
		if (counter == null || counter.isExpired()) {
			counter = new TPSCounter();
		}
		counter.increment();
	}
	
}
