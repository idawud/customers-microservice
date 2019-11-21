package io.turntabl.springwebservice.pubsub;

public interface MessagePublisher {
    void publish(String message);
}
