package concurrent.delay;

import java.util.concurrent.DelayQueue;

/**
 * DelayedQueue 延迟队列
 * **/
public final class DelayedQueue {
	
	public static void main(String[] args) {
		DelayQueue<RpcRequestBody> queue = new DelayQueue<RpcRequestBody>();
		long thisTime = System.currentTimeMillis();
		RpcRequestBody body = new RpcRequestBody();
		body.setDelayTime(thisTime + 1000);
		
		RpcRequestBody body_0 = new RpcRequestBody();
		body_0.setDelayTime(thisTime + 200);
		
		RpcRequestBody body_1 = new RpcRequestBody();
		body_1.setDelayTime(thisTime + 250);
		
		System.out.println("当前时间: " + thisTime);
		
		
		queue.add(body);
		queue.add(body_0);
		queue.add(body_1);
		
		
		RpcRequestBody body2 = null;
		
		while ((body2 = queue.poll()) == null) {
			
		}
		System.out.println(System.currentTimeMillis() + "|" + body2.getDelayTime());
	
	}
}
