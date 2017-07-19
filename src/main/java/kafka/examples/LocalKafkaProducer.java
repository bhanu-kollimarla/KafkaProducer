package kafka.examples;

import java.io.IOException;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

public class LocalKafkaProducer {

    public static void main(String[] args) throws IOException {
//Set kafka producer configuration
        Properties props = Configuration.setKafkaProducerParameter();
        Producer<String, String> producer = new KafkaProducer(props);
//Read data from directory
        ProducerCreator.readDataFromDir(producer);
// declayer another source
        producer.close();
    }
}
