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
    comments = "Source: transportadora.proto")
public final class transportadoraGrpc {

  private transportadoraGrpc() {}

  public static final String SERVICE_NAME = "transportadora";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.CreateRequest,
      com.sd.grpc.TransportadoraOuterClass.APIResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = com.sd.grpc.TransportadoraOuterClass.CreateRequest.class,
      responseType = com.sd.grpc.TransportadoraOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.CreateRequest,
      com.sd.grpc.TransportadoraOuterClass.APIResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.CreateRequest, com.sd.grpc.TransportadoraOuterClass.APIResponse> getCreateMethod;
    if ((getCreateMethod = transportadoraGrpc.getCreateMethod) == null) {
      synchronized (transportadoraGrpc.class) {
        if ((getCreateMethod = transportadoraGrpc.getCreateMethod) == null) {
          transportadoraGrpc.getCreateMethod = getCreateMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.TransportadoraOuterClass.CreateRequest, com.sd.grpc.TransportadoraOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "transportadora", "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new transportadoraMethodDescriptorSupplier("create"))
                  .build();
          }
        }
     }
     return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.UpdateRequest,
      com.sd.grpc.TransportadoraOuterClass.APIResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = com.sd.grpc.TransportadoraOuterClass.UpdateRequest.class,
      responseType = com.sd.grpc.TransportadoraOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.UpdateRequest,
      com.sd.grpc.TransportadoraOuterClass.APIResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.UpdateRequest, com.sd.grpc.TransportadoraOuterClass.APIResponse> getUpdateMethod;
    if ((getUpdateMethod = transportadoraGrpc.getUpdateMethod) == null) {
      synchronized (transportadoraGrpc.class) {
        if ((getUpdateMethod = transportadoraGrpc.getUpdateMethod) == null) {
          transportadoraGrpc.getUpdateMethod = getUpdateMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.TransportadoraOuterClass.UpdateRequest, com.sd.grpc.TransportadoraOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "transportadora", "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new transportadoraMethodDescriptorSupplier("update"))
                  .build();
          }
        }
     }
     return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.DeleteRequest,
      com.sd.grpc.TransportadoraOuterClass.APIResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = com.sd.grpc.TransportadoraOuterClass.DeleteRequest.class,
      responseType = com.sd.grpc.TransportadoraOuterClass.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.DeleteRequest,
      com.sd.grpc.TransportadoraOuterClass.APIResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.DeleteRequest, com.sd.grpc.TransportadoraOuterClass.APIResponse> getDeleteMethod;
    if ((getDeleteMethod = transportadoraGrpc.getDeleteMethod) == null) {
      synchronized (transportadoraGrpc.class) {
        if ((getDeleteMethod = transportadoraGrpc.getDeleteMethod) == null) {
          transportadoraGrpc.getDeleteMethod = getDeleteMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.TransportadoraOuterClass.DeleteRequest, com.sd.grpc.TransportadoraOuterClass.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "transportadora", "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new transportadoraMethodDescriptorSupplier("delete"))
                  .build();
          }
        }
     }
     return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.Empty,
      com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse> getReadallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "readall",
      requestType = com.sd.grpc.TransportadoraOuterClass.Empty.class,
      responseType = com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.Empty,
      com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse> getReadallMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.TransportadoraOuterClass.Empty, com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse> getReadallMethod;
    if ((getReadallMethod = transportadoraGrpc.getReadallMethod) == null) {
      synchronized (transportadoraGrpc.class) {
        if ((getReadallMethod = transportadoraGrpc.getReadallMethod) == null) {
          transportadoraGrpc.getReadallMethod = getReadallMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.TransportadoraOuterClass.Empty, com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "transportadora", "readall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new transportadoraMethodDescriptorSupplier("readall"))
                  .build();
          }
        }
     }
     return getReadallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static transportadoraStub newStub(io.grpc.Channel channel) {
    return new transportadoraStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static transportadoraBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new transportadoraBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static transportadoraFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new transportadoraFutureStub(channel);
  }

  /**
   */
  public static abstract class transportadoraImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(com.sd.grpc.TransportadoraOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void update(com.sd.grpc.TransportadoraOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    public void delete(com.sd.grpc.TransportadoraOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void readall(com.sd.grpc.TransportadoraOuterClass.Empty request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReadallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sd.grpc.TransportadoraOuterClass.CreateRequest,
                com.sd.grpc.TransportadoraOuterClass.APIResponse>(
                  this, METHODID_CREATE)))
          .addMethod(
            getUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sd.grpc.TransportadoraOuterClass.UpdateRequest,
                com.sd.grpc.TransportadoraOuterClass.APIResponse>(
                  this, METHODID_UPDATE)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sd.grpc.TransportadoraOuterClass.DeleteRequest,
                com.sd.grpc.TransportadoraOuterClass.APIResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            getReadallMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.sd.grpc.TransportadoraOuterClass.Empty,
                com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse>(
                  this, METHODID_READALL)))
          .build();
    }
  }

  /**
   */
  public static final class transportadoraStub extends io.grpc.stub.AbstractStub<transportadoraStub> {
    private transportadoraStub(io.grpc.Channel channel) {
      super(channel);
    }

    private transportadoraStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected transportadoraStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new transportadoraStub(channel, callOptions);
    }

    /**
     */
    public void create(com.sd.grpc.TransportadoraOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(com.sd.grpc.TransportadoraOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(com.sd.grpc.TransportadoraOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readall(com.sd.grpc.TransportadoraOuterClass.Empty request,
        io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReadallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class transportadoraBlockingStub extends io.grpc.stub.AbstractStub<transportadoraBlockingStub> {
    private transportadoraBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private transportadoraBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected transportadoraBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new transportadoraBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.sd.grpc.TransportadoraOuterClass.APIResponse create(com.sd.grpc.TransportadoraOuterClass.CreateRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sd.grpc.TransportadoraOuterClass.APIResponse update(com.sd.grpc.TransportadoraOuterClass.UpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sd.grpc.TransportadoraOuterClass.APIResponse delete(com.sd.grpc.TransportadoraOuterClass.DeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse> readall(
        com.sd.grpc.TransportadoraOuterClass.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getReadallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class transportadoraFutureStub extends io.grpc.stub.AbstractStub<transportadoraFutureStub> {
    private transportadoraFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private transportadoraFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected transportadoraFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new transportadoraFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sd.grpc.TransportadoraOuterClass.APIResponse> create(
        com.sd.grpc.TransportadoraOuterClass.CreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sd.grpc.TransportadoraOuterClass.APIResponse> update(
        com.sd.grpc.TransportadoraOuterClass.UpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sd.grpc.TransportadoraOuterClass.APIResponse> delete(
        com.sd.grpc.TransportadoraOuterClass.DeleteRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_UPDATE = 1;
  private static final int METHODID_DELETE = 2;
  private static final int METHODID_READALL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final transportadoraImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(transportadoraImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((com.sd.grpc.TransportadoraOuterClass.CreateRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((com.sd.grpc.TransportadoraOuterClass.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((com.sd.grpc.TransportadoraOuterClass.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.APIResponse>) responseObserver);
          break;
        case METHODID_READALL:
          serviceImpl.readall((com.sd.grpc.TransportadoraOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.TransportadoraOuterClass.TransportadoraResponse>) responseObserver);
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

  private static abstract class transportadoraBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    transportadoraBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sd.grpc.TransportadoraOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("transportadora");
    }
  }

  private static final class transportadoraFileDescriptorSupplier
      extends transportadoraBaseDescriptorSupplier {
    transportadoraFileDescriptorSupplier() {}
  }

  private static final class transportadoraMethodDescriptorSupplier
      extends transportadoraBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    transportadoraMethodDescriptorSupplier(String methodName) {
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
      synchronized (transportadoraGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new transportadoraFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getReadallMethod())
              .build();
        }
      }
    }
    return result;
  }
}
