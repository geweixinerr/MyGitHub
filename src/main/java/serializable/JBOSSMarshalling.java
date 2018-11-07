package serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.marshalling.ByteBufferInput;
import org.jboss.marshalling.ByteBufferOutput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

/**
 * JBOSS序列化框架
 **/
public final class JBOSSMarshalling {

	public JBOSSMarshalling() {
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		List<String> xyList = new ArrayList<String>();
		xyList.add("Java");
		xyList.add(".NET");
		xyList.add("C++");
		xyList.add("Hello World!");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "葛伟新");
		map.put("address", "江苏镇江大港镇");
		map.put("age", 32);
		map.put("xyList", xyList);

		// 需要jboss-marshalling-serial jar支持
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

		// 创建了MarshallingConfiguration对象
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		// 将它的版本号设置为5(仅支持5)
		configuration.setVersion(5);

		final Marshaller marshaller = marshallerFactory.createMarshaller(configuration); // 编码
		final Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration); // 解码

		// JBOSSMarshalling序列化方式
		long start_1 = System.currentTimeMillis();
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		marshaller.start(Marshalling.createByteOutput(bout));
		marshaller.writeObject(map);
		marshaller.finish();
		marshaller.close();

		ByteArrayInputStream bais = new ByteArrayInputStream(bout.toByteArray());
		unmarshaller.start(Marshalling.createByteInput(bais));
		Map<String, String> cloneObject = (Map<String, String>) unmarshaller.readObject();
		unmarshaller.finish();
		unmarshaller.close();
		long end_1 = System.currentTimeMillis();

		System.out.println("JBOSSMarshalling序列化耗时: " + (end_1 - start_1) + ",码流大小:" + bout.toByteArray().length + ",对象:"
				+ cloneObject);

		// 原始JDK序列化方式
		long start_2 = System.currentTimeMillis();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(out);
		outputStream.writeObject(map);
		outputStream.flush();
		outputStream.close();

		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream inputStream = new ObjectInputStream(in);
		Map<String, String> cloneObject2 = (Map<String, String>) inputStream.readObject();
		inputStream.close();
		long end_2 = System.currentTimeMillis();

		System.out.println("JDK序列化耗时: " + (end_2 - start_2) + ",码流大小:" + out.toByteArray().length + ",对象:"
				+ cloneObject2);

		// 堆外内存序列化
		long start_3 = System.currentTimeMillis();
		ByteBuffer b = ByteBuffer.allocateDirect(1024 * 1000);
		ByteBufferOutput bo = new ByteBufferOutput(b);
		marshaller.start(Marshalling.createByteOutput(bo));
		marshaller.writeObject(map);
		marshaller.finish();
		marshaller.close();
		b.flip();
		ByteBufferInput bi = new ByteBufferInput(b);
		unmarshaller.start(Marshalling.createByteInput(bi));
		Map<String, String> cloneObject3 = (Map<String, String>) unmarshaller.readObject();
		unmarshaller.finish();
		unmarshaller.close();
		long end_3 = System.currentTimeMillis();

		System.out.println("JBOSSMarshalling堆外内存序列化耗时: " + (end_3 - start_3) + ",码流大小:" + b.position() + ",对象:"
				+ cloneObject3);

	}
}
