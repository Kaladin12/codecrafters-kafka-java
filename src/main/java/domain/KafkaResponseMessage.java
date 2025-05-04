package domain;

public record KafkaResponseMessage(int message_size, int correlation_id) {
}
