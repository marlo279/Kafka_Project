package com.marlo.demo;

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
        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"3Wn5LNN9bN2r4ZMW2FV7j7\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIzV241TE5OOWJOMnI0Wk1XMkZWN2o3Iiwib3JnYW5pemF0aW9uSWQiOjc0MDcwLCJ1c2VySWQiOjg2MTUwLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiIzN2YwZDcxZC0yMTljLTRlNjgtOTViYS0yMTg3YTQ1NzYyNzYifX0.YmIQ8G2g9-Wydr9lcTjiZ7oJhWLIgMVzNQKWOQ6wwV8\";");
        properties.setProperty("sasl.mechanism", "PLAIN");

    // Stel producer properties in
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

    // Maak Kafka producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

    // Create a producer record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_java", "Laptop");

    // Send Data
        producer.send(producerRecord);


    // Tell the producer to send all data
        producer.flush();

    // Flush and close Producer
        producer.close();



    }

}
