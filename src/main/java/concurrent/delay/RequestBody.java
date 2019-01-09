package concurrent.delay;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class RequestBody implements Delayed {

	@Override
	public int compareTo(Delayed o) {
		if (this == o) {
			return 0;
		}
		
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return 0;
	}

}
