package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
 
public class ProducerTest {
 
    public static void main(String[] args) {
        Properties pros=new Properties();
        String groupID="test-group";
        pros.put("bootstrap.servers","localhost:9092");
        pros.put("group.id",groupID);
        pros.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        pros.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        pros.put("acks","-1");
        pros.put("retries",3);
        pros.put("batch.size",323840);
        pros.put("linger.ms",10);
        pros.put("buffer.memory",33554432);
        pros.put("max.block.ms",3000);
        Producer<String,String> producer = new KafkaProducer<String,String>(pros);
        producer.send(new ProducerRecord<String,String>("geweixin","Java","Hello World"));
        
        System.out.println("发送");
        producer.close();
    }
}