package com.marlo.demo.cloudproviders.conduktor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class.getSimpleName());

    public static void main(String[] args) {

        log.info("I am a Kafka Consumer");

        String groupId = "my-java-application";
        String topic = "demo_java";

        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "cluster.playground.cdkt.io:9092");
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"3Wn5LNN9bN2r4ZMW2FV7j7\" password=\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2F1dGguY29uZHVrdG9yLmlvIiwic291cmNlQXBwbGljYXRpb24iOiJhZG1pbiIsInVzZXJNYWlsIjpudWxsLCJwYXlsb2FkIjp7InZhbGlkRm9yVXNlcm5hbWUiOiIzV241TE5OOWJOMnI0Wk1XMkZWN2o3Iiwib3JnYW5pemF0aW9uSWQiOjc0MDcwLCJ1c2VySWQiOjg2MTUwLCJmb3JFeHBpcmF0aW9uQ2hlY2siOiIzN2YwZDcxZC0yMTljLTRlNjgtOTViYS0yMTg3YTQ1NzYyNzYifX0.YmIQ8G2g9-Wydr9lcTjiZ7oJhWLIgMVzNQKWOQ6wwV8\";");
        properties.setProperty("sasl.mechanism", "PLAIN");

        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topic));

        List<Double> numbers = new ArrayList<>();

        while (numbers.size() < 50) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records) {
                log.info("Key: " + record.key() + ", Value: " + record.value());
                log.info("Partition: " + record.partition() + ", Offset: " + record.offset());

                double average = calculateAverage(numbers);
                double standardDeviation = calculateStandardDeviation(numbers);

                log.info("Average: " + average);
                log.info("Standard Deviation: " + standardDeviation);

                // Assuming the value is a double
                try {
                    double number = Double.parseDouble(record.value());
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    log.error("Invalid number format: " + record.value());
                }
            }
        }

        // Calculate average and standard deviation
        double average = calculateAverage(numbers);
        double standardDeviation = calculateStandardDeviation(numbers);

        log.info("Average: " + average);
        log.info("Standard Deviation: " + standardDeviation);

        consumer.close();
    }

    private static double calculateAverage(List<Double> values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    private static double calculateStandardDeviation(List<Double> values) {
        double average = calculateAverage(values);
        double sumOfSquaredDifferences = 0;
        for (double value : values) {
            double difference = value - average;
            sumOfSquaredDifferences += difference * difference;
        }
        double variance = sumOfSquaredDifferences / values.size();
        return Math.sqrt(variance);
    }
}