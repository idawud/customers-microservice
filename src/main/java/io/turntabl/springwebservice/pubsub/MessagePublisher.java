package io.turntabl.springwebservice.pubsub;

import io.turntabl.springwebservice.models.Customer;

public interface MessagePublisher {
    void publish(Customer customer);
}
