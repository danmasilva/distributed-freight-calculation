package com.sd.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: business.proto")
public final class pricingGrpc {

  private pricingGrpc() {}

  public static final String SERVICE_NAME = "pricing";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.Business.PrecoRequest,
      com.sd.grpc.Business.PrecoReturn> getDefPricingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "defPricing",
      requestType = com.sd.grpc.Business.PrecoRequest.class,
      responseType = com.sd.grpc.Business.PrecoReturn.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.sd.grpc.Business.PrecoRequest,
      com.sd.grpc.Business.PrecoReturn> getDefPricingMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.Business.PrecoRequest, com.sd.grpc.Business.PrecoReturn> getDefPricingMethod;
    if ((getDefPricingMethod = pricingGrpc.getDefPricingMethod) == null) {
      synchronized (pricingGrpc.class) {
        if ((getDefPricingMethod = pricingGrpc.getDefPricingMethod) == null) {
          pricingGrpc.getDefPricingMethod = getDefPricingMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.Business.PrecoRequest, com.sd.grpc.Business.PrecoReturn>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "pricing", "defPricing"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.Business.PrecoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.Business.PrecoReturn.getDefaultInstance()))
                  .setSchemaDescriptor(new pricingMethodDescriptorSupplier("defPricing"))
                  .build();
          }
        }
     }
     return getDefPricingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static pricingStub newStub(io.grpc.Channel channel) {
    return new pricingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static pricingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new pricingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static pricingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new pricingFutureStub(channel);
  }

  /**
   */
  public static abstract class pricingImplBase implements io.grpc.BindableService {

    /**
     */
    public void defPricing(com.sd.grpc.Business.PrecoRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.Business.PrecoReturn> responseObserver) {
      asyncUnimplementedUnaryCall(getDefPricingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDefPricingMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.sd.grpc.Business.PrecoRequest,
                com.sd.grpc.Business.PrecoReturn>(
                  this, METHODID_DEF_PRICING)))
          .build();
    }
  }

  /**
   */
  public static final class pricingStub extends io.grpc.stub.AbstractStub<pricingStub> {
    private pricingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private pricingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected pricingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new pricingStub(channel, callOptions);
    }

    /**
     */
    public void defPricing(com.sd.grpc.Business.PrecoRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.Business.PrecoReturn> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDefPricingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class pricingBlockingStub extends io.grpc.stub.AbstractStub<pricingBlockingStub> {
    private pricingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private pricingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected pricingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new pricingBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<com.sd.grpc.Business.PrecoReturn> defPricing(
        com.sd.grpc.Business.PrecoRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getDefPricingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class pricingFutureStub extends io.grpc.stub.AbstractStub<pricingFutureStub> {
    private pricingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private pricingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected pricingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new pricingFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DEF_PRICING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final pricingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(pricingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEF_PRICING:
          serviceImpl.defPricing((com.sd.grpc.Business.PrecoRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.Business.PrecoReturn>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class pricingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    pricingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sd.grpc.Business.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("pricing");
    }
  }

  private static final class pricingFileDescriptorSupplier
      extends pricingBaseDescriptorSupplier {
    pricingFileDescriptorSupplier() {}
  }

  private static final class pricingMethodDescriptorSupplier
      extends pricingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    pricingMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (pricingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new pricingFileDescriptorSupplier())
              .addMethod(getDefPricingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
