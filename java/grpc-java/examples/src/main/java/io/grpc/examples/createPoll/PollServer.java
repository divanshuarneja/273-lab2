//package io.grpc.examples.createPoll;
package edu.sjsu.cmpe273.lab2;

import io.grpc.ServerImpl;
import io.grpc.stub.StreamObserver;
import io.grpc.transport.netty.NettyServerBuilder;

import java.util.logging.Logger;
import java.util.Random;

/**
 * Server that manages startup/shutdown of a {@code Greeter} server.
 */
public class PollServer {
  private static final Logger logger = Logger.getLogger(PollServer.class.getName());

  /* The port on which the server should run */
  private int port = 50051;
  private ServerImpl server;

  private void start() throws Exception {
    server = NettyServerBuilder.forPort(port)
        .addService(PollServiceGrpc.bindService(new PollImpl()))
        .build().start();
    logger.info("Server started, listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        PollServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Main launches the server from the command line.
   */
  public static void main(String[] args) throws Exception {
    final PollServer server = new PollServer();
    server.start();
  }

  private class PollImpl implements PollServiceGrpc.PollService {

  public String getUniqueId(){

  Random randomNumber= new Random();
  String base36UniqueId= Long.toString(randomNumber.nextLong(),36);
  return base36UniqueId.substring(6);
  }

    @Override
//    public void createPoll(edu.sjsu.cmpe273.lab2.createPoll.PollRequest request,io.grpc.stub.StreamObserver<edu.sjsu.cmpe273.lab2.createPoll.PollResponse> responseObserver) {
    public void createPoll(PollRequest request,StreamObserver<PollResponse> responseObserver) {
	  logger.info("Poll details are :");
	  logger.info("Poll details are :\n"+"moderatorID :"+request.getModeratorId()+
	  "\n question :"+request.getQuestion()+
	   "\n started_at :"+request.getStartedAt()+
	    "\n expired_at :"+request.getExpiredAt()+
		"\n Choice :"+request.getChoiceList()
		);
   PollResponse reply = PollResponse.newBuilder().setId(getUniqueId()).build();
      responseObserver.onValue(reply);
      responseObserver.onCompleted();
    }
  }
}

