package com.iptiQ.test.loadbalancer.provider;

import java.util.Random;

import com.iptiQ.test.loadbalancer.lb.registry.healthcheck.history.HealthCheckStatus;
import com.iptiQ.test.loadbalancer.model.SimpleRequest;
import com.iptiQ.test.loadbalancer.model.SimpleResponse;

import lombok.Value;

@Value
public class StringIdProvider implements Provider {

  String internalId;
  int clientRequestProcessingDelayMS;

  @Override
  public SimpleResponse process(SimpleRequest simpleRequest) {
    System.out.printf(
        "Provider[%s] is processing request from client[%s]%n",
        internalId, simpleRequest.getClientId());

    try {
      Thread.sleep(clientRequestProcessingDelayMS);
    } catch (InterruptedException exception) {
      System.out.printf("Thread[%s] is interrupted%n", Thread.currentThread().getName());
      exception.printStackTrace();
    }

    return new SimpleResponse(internalId);
  }
}
