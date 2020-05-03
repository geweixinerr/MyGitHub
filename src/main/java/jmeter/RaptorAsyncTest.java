package jmeter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import raptor.RaptorRpc;
import raptor.core.AbstractCallBack;
import raptor.core.client.RpcClient;
import raptor.core.client.RpcClientTaskPool;
import raptor.core.client.task.RpcClientMonitor;
import raptor.core.init.RpcParameter;
import raptor.core.message.RpcRequestBody;
import raptor.core.message.RpcResponseBody;
import raptor.exception.RpcException;

/**
 * Raptor压测-异步
 **/
public final class RaptorAsyncTest extends AbstractJavaSamplerClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RaptorAsyncTest.class);

	static {
		System.out.println("初始化服务器参数...");
		List<Map<String, String>> clientConfig = new ArrayList<Map<String, String>>();

		Map<String, String> config = new HashMap<String, String>();
		config.put("serverNode", "mc"); // 服务节点
		config.put("remote", "localhost"); // 服务节点IP地址
		config.put("port", "8090"); // 端口号
		config.put("maxclients", "128"); // 最大TCP连接数
		config.put("minclients", "6"); // 最小TCP连接数

		clientConfig.add(config);

		RpcParameter.INSTANCE.initRpcParameter(clientConfig);
		RpcClientTaskPool.initPool();
		RpcClientMonitor.scan();
		try {
			RpcClient.connection();
		} catch (Exception e1) {
			System.out.println("启动异常: " + e1.getMessage());
		}
		System.out.println("初始化完毕...");
	}

	@SuppressWarnings("unchecked")
	@Override
	public SampleResult runTest(JavaSamplerContext context) {
		SampleResult result = new SampleResult();
		result.sampleStart();
		try {
			// 组装发送消息
			String message = "Netty RPC Send, Netty is VeryGood!";

			@SuppressWarnings("rawtypes")
			RaptorRpc rpc = new RaptorRpc();
			rpc.sendAsyncMessage("mc", "LoginAuth", new AbstractCallBack() {
				@Override
				public void invoke(RpcResponseBody resp) {

				}

				@Override
				public void invoke(RpcRequestBody req, RpcResponseBody resp) {
					LOGGER.info("" + req);
				}
			}, 5, message, message);
			result.setSuccessful(true);
		} catch (Exception e) {
			result.setSuccessful(false);
		} finally {
			result.sampleEnd();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws RpcException {
		// 组装发送消息
		String message = "Netty RPC Send, Netty is VeryGood!";
		
		@SuppressWarnings("rawtypes")
		RaptorRpc rpc = new RaptorRpc();

		rpc.sendAsyncMessage("mc", "LoginAuth", new AbstractCallBack() {
			@Override
			public void invoke(RpcResponseBody resp) {
			}

			@Override
			public void invoke(RpcRequestBody req, RpcResponseBody resp) {
			}

		}, 5, message, message);
	}
}
