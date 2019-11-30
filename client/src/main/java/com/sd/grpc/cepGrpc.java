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
    comments = "Source: cep.proto")
public final class cepGrpc {

  private cepGrpc() {}

  public static final String SERVICE_NAME = "cep";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.CreateRequest,
      com.sd.grpc.CepOuterClass.APICepResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = com.sd.grpc.CepOuterClass.CreateRequest.class,
      responseType = com.sd.grpc.CepOuterClass.APICepResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.CreateRequest,
      com.sd.grpc.CepOuterClass.APICepResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.CreateRequest, com.sd.grpc.CepOuterClass.APICepResponse> getCreateMethod;
    if ((getCreateMethod = cepGrpc.getCreateMethod) == null) {
      synchronized (cepGrpc.class) {
        if ((getCreateMethod = cepGrpc.getCreateMethod) == null) {
          cepGrpc.getCreateMethod = getCreateMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.CepOuterClass.CreateRequest, com.sd.grpc.CepOuterClass.APICepResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cep", "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.APICepResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new cepMethodDescriptorSupplier("create"))
                  .build();
          }
        }
     }
     return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.UpdateRequest,
      com.sd.grpc.CepOuterClass.APICepResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = com.sd.grpc.CepOuterClass.UpdateRequest.class,
      responseType = com.sd.grpc.CepOuterClass.APICepResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.UpdateRequest,
      com.sd.grpc.CepOuterClass.APICepResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.UpdateRequest, com.sd.grpc.CepOuterClass.APICepResponse> getUpdateMethod;
    if ((getUpdateMethod = cepGrpc.getUpdateMethod) == null) {
      synchronized (cepGrpc.class) {
        if ((getUpdateMethod = cepGrpc.getUpdateMethod) == null) {
          cepGrpc.getUpdateMethod = getUpdateMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.CepOuterClass.UpdateRequest, com.sd.grpc.CepOuterClass.APICepResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cep", "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.APICepResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new cepMethodDescriptorSupplier("update"))
                  .build();
          }
        }
     }
     return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.DeleteRequest,
      com.sd.grpc.CepOuterClass.APICepResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = com.sd.grpc.CepOuterClass.DeleteRequest.class,
      responseType = com.sd.grpc.CepOuterClass.APICepResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.DeleteRequest,
      com.sd.grpc.CepOuterClass.APICepResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.DeleteRequest, com.sd.grpc.CepOuterClass.APICepResponse> getDeleteMethod;
    if ((getDeleteMethod = cepGrpc.getDeleteMethod) == null) {
      synchronized (cepGrpc.class) {
        if ((getDeleteMethod = cepGrpc.getDeleteMethod) == null) {
          cepGrpc.getDeleteMethod = getDeleteMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.CepOuterClass.DeleteRequest, com.sd.grpc.CepOuterClass.APICepResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "cep", "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.APICepResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new cepMethodDescriptorSupplier("delete"))
                  .build();
          }
        }
     }
     return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.Empty,
      com.sd.grpc.CepOuterClass.CepResponse> getReadallMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "readall",
      requestType = com.sd.grpc.CepOuterClass.Empty.class,
      responseType = com.sd.grpc.CepOuterClass.CepResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.Empty,
      com.sd.grpc.CepOuterClass.CepResponse> getReadallMethod() {
    io.grpc.MethodDescriptor<com.sd.grpc.CepOuterClass.Empty, com.sd.grpc.CepOuterClass.CepResponse> getReadallMethod;
    if ((getReadallMethod = cepGrpc.getReadallMethod) == null) {
      synchronized (cepGrpc.class) {
        if ((getReadallMethod = cepGrpc.getReadallMethod) == null) {
          cepGrpc.getReadallMethod = getReadallMethod = 
              io.grpc.MethodDescriptor.<com.sd.grpc.CepOuterClass.Empty, com.sd.grpc.CepOuterClass.CepResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "cep", "readall"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.sd.grpc.CepOuterClass.CepResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new cepMethodDescriptorSupplier("readall"))
                  .build();
          }
        }
     }
     return getReadallMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static cepStub newStub(io.grpc.Channel channel) {
    return new cepStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static cepBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new cepBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static cepFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new cepFutureStub(channel);
  }

  /**
   */
  public static abstract class cepImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(com.sd.grpc.CepOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void update(com.sd.grpc.CepOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    public void delete(com.sd.grpc.CepOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void readall(com.sd.grpc.CepOuterClass.Empty request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.CepResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getReadallMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sd.grpc.CepOuterClass.CreateRequest,
                com.sd.grpc.CepOuterClass.APICepResponse>(
                  this, METHODID_CREATE)))
          .addMethod(
            getUpdateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sd.grpc.CepOuterClass.UpdateRequest,
                com.sd.grpc.CepOuterClass.APICepResponse>(
                  this, METHODID_UPDATE)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.sd.grpc.CepOuterClass.DeleteRequest,
                com.sd.grpc.CepOuterClass.APICepResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            getReadallMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.sd.grpc.CepOuterClass.Empty,
                com.sd.grpc.CepOuterClass.CepResponse>(
                  this, METHODID_READALL)))
          .build();
    }
  }

  /**
   */
  public static final class cepStub extends io.grpc.stub.AbstractStub<cepStub> {
    private cepStub(io.grpc.Channel channel) {
      super(channel);
    }

    private cepStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected cepStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new cepStub(channel, callOptions);
    }

    /**
     */
    public void create(com.sd.grpc.CepOuterClass.CreateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(com.sd.grpc.CepOuterClass.UpdateRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(com.sd.grpc.CepOuterClass.DeleteRequest request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readall(com.sd.grpc.CepOuterClass.Empty request,
        io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.CepResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getReadallMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class cepBlockingStub extends io.grpc.stub.AbstractStub<cepBlockingStub> {
    private cepBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private cepBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected cepBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new cepBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.sd.grpc.CepOuterClass.APICepResponse create(com.sd.grpc.CepOuterClass.CreateRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sd.grpc.CepOuterClass.APICepResponse update(com.sd.grpc.CepOuterClass.UpdateRequest request) {
      return blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.sd.grpc.CepOuterClass.APICepResponse delete(com.sd.grpc.CepOuterClass.DeleteRequest request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.sd.grpc.CepOuterClass.CepResponse> readall(
        com.sd.grpc.CepOuterClass.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getReadallMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class cepFutureStub extends io.grpc.stub.AbstractStub<cepFutureStub> {
    private cepFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private cepFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected cepFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new cepFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sd.grpc.CepOuterClass.APICepResponse> create(
        com.sd.grpc.CepOuterClass.CreateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sd.grpc.CepOuterClass.APICepResponse> update(
        com.sd.grpc.CepOuterClass.UpdateRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.sd.grpc.CepOuterClass.APICepResponse> delete(
        com.sd.grpc.CepOuterClass.DeleteRequest request) {
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
    private final cepImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(cepImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((com.sd.grpc.CepOuterClass.CreateRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((com.sd.grpc.CepOuterClass.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((com.sd.grpc.CepOuterClass.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.APICepResponse>) responseObserver);
          break;
        case METHODID_READALL:
          serviceImpl.readall((com.sd.grpc.CepOuterClass.Empty) request,
              (io.grpc.stub.StreamObserver<com.sd.grpc.CepOuterClass.CepResponse>) responseObserver);
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

  private static abstract class cepBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    cepBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.sd.grpc.CepOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("cep");
    }
  }

  private static final class cepFileDescriptorSupplier
      extends cepBaseDescriptorSupplier {
    cepFileDescriptorSupplier() {}
  }

  private static final class cepMethodDescriptorSupplier
      extends cepBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    cepMethodDescriptorSupplier(String methodName) {
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
      synchronized (cepGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new cepFileDescriptorSupplier())
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
