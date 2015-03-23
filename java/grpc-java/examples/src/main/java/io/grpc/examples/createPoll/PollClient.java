//package io.grpc.examples.createPoll;
package edu.sjsu.cmpe273.lab2;

import io.grpc.ChannelImpl;
import io.grpc.transport.netty.NegotiationType;
import io.grpc.transport.netty.NettyChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class PollClient {
  private static final Logger logger = Logger.getLogger(PollClient.class.getName());

  private final ChannelImpl channel;
  private final PollServiceGrpc.PollServiceBlockingStub blockingStub;

  public PollClient(String host, int port) {
    channel =
        NettyChannelBuilder.forAddress(host, port).negotiationType(NegotiationType.PLAINTEXT)
            .build();
    blockingStub = PollServiceGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTerminated(5, TimeUnit.SECONDS);
  }

  public void poll(PollRequest poll) {
    try {
      logger.info("Poll has been created with following details :");
      /*PollRequest request = PollRequest.newBuilder().setName(name).build();*/
      PollResponse response = blockingStub.createPoll(poll);

	  logger.info("Poll generated with ID: " + response.getId());
    } catch (RuntimeException e) {
      logger.log(Level.WARNING, "CreatePoll RPC failed", e);
      return;
    }
  }

  public static void main(String[] args) throws Exception {
    PollClient client = new PollClient("localhost", 50051);
    String[] pollChoice={"d","f"};
	try {
	   PollRequest poll = PollRequest.newBuilder()
	   .setModeratorId("12345")
	   .setQuestion("smartphone you like")
	   .setStartedAt("03/22/2015")
	   .setExpiredAt("03/23/2015")
	   .addChoice("iphone")
	   .addChoice("samsung")
	   .build();
      client.poll(poll);
    } finally {
      client.shutdown();
    }
  }
}

