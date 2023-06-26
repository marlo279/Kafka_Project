package com.marlo.demo.local;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class.getSimpleName());

    public static void main(String[] args) {

        log.info("Maak Kafka producer aan");


    // Maak vervbinding met kafka cluster
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.17.159.188:9092");


    // Stel producer properties in
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

    // Maak Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

    // Create a producer record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("breinscan", "33");

    // Send Data
        producer.send(producerRecord);


    // Tell the producer to send all data
        producer.flush();

    // Flush and close Producer
        producer.close();



    }

}
