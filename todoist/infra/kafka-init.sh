#!/bin/bash

# Create topics with configurations
/opt/kafka/bin/kafka-topics.sh --bootstrap-server kafka:9092 --create --topic user_topic \
  --partitions 3 --replication-factor 1 \
  --config retention.ms=604800000

/opt/kafka/bin/kafka-topics.sh --bootstrap-server kafka:9092 --create --topic task_topic \
  --partitions 3 --replication-factor 1 \
  --config retention.ms=604800000

echo "Created topics:"
/opt/kafka/bin/kafka-topics.sh --bootstrap-server kafka:9092 --list