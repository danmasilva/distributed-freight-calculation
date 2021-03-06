// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: dfc_grpc.proto

package com.sd.dfc.server;

/**
 * Protobuf type {@code server.Cep}
 */
public  final class Cep extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:server.Cep)
    CepOrBuilder {
  // Use Cep.newBuilder() to construct.
  private Cep(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Cep() {
    origem_ = 0;
    destino_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private Cep(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            origem_ = input.readInt32();
            break;
          }
          case 16: {

            destino_ = input.readInt32();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.sd.dfc.server.DfcProto.internal_static_server_Cep_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.sd.dfc.server.DfcProto.internal_static_server_Cep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.sd.dfc.server.Cep.class, com.sd.dfc.server.Cep.Builder.class);
  }

  public static final int ORIGEM_FIELD_NUMBER = 1;
  private int origem_;
  /**
   * <code>optional int32 origem = 1;</code>
   */
  public int getOrigem() {
    return origem_;
  }

  public static final int DESTINO_FIELD_NUMBER = 2;
  private int destino_;
  /**
   * <code>optional int32 destino = 2;</code>
   */
  public int getDestino() {
    return destino_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (origem_ != 0) {
      output.writeInt32(1, origem_);
    }
    if (destino_ != 0) {
      output.writeInt32(2, destino_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (origem_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, origem_);
    }
    if (destino_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, destino_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.sd.dfc.server.Cep)) {
      return super.equals(obj);
    }
    com.sd.dfc.server.Cep other = (com.sd.dfc.server.Cep) obj;

    boolean result = true;
    result = result && (getOrigem()
        == other.getOrigem());
    result = result && (getDestino()
        == other.getDestino());
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + ORIGEM_FIELD_NUMBER;
    hash = (53 * hash) + getOrigem();
    hash = (37 * hash) + DESTINO_FIELD_NUMBER;
    hash = (53 * hash) + getDestino();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.sd.dfc.server.Cep parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sd.dfc.server.Cep parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sd.dfc.server.Cep parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.sd.dfc.server.Cep parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.sd.dfc.server.Cep parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sd.dfc.server.Cep parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sd.dfc.server.Cep parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.sd.dfc.server.Cep parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.sd.dfc.server.Cep parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.sd.dfc.server.Cep parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.sd.dfc.server.Cep prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code server.Cep}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:server.Cep)
      com.sd.dfc.server.CepOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.sd.dfc.server.DfcProto.internal_static_server_Cep_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sd.dfc.server.DfcProto.internal_static_server_Cep_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sd.dfc.server.Cep.class, com.sd.dfc.server.Cep.Builder.class);
    }

    // Construct using com.sd.dfc.server.Cep.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      origem_ = 0;

      destino_ = 0;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.sd.dfc.server.DfcProto.internal_static_server_Cep_descriptor;
    }

    public com.sd.dfc.server.Cep getDefaultInstanceForType() {
      return com.sd.dfc.server.Cep.getDefaultInstance();
    }

    public com.sd.dfc.server.Cep build() {
      com.sd.dfc.server.Cep result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.sd.dfc.server.Cep buildPartial() {
      com.sd.dfc.server.Cep result = new com.sd.dfc.server.Cep(this);
      result.origem_ = origem_;
      result.destino_ = destino_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.sd.dfc.server.Cep) {
        return mergeFrom((com.sd.dfc.server.Cep)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.sd.dfc.server.Cep other) {
      if (other == com.sd.dfc.server.Cep.getDefaultInstance()) return this;
      if (other.getOrigem() != 0) {
        setOrigem(other.getOrigem());
      }
      if (other.getDestino() != 0) {
        setDestino(other.getDestino());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.sd.dfc.server.Cep parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.sd.dfc.server.Cep) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int origem_ ;
    /**
     * <code>optional int32 origem = 1;</code>
     */
    public int getOrigem() {
      return origem_;
    }
    /**
     * <code>optional int32 origem = 1;</code>
     */
    public Builder setOrigem(int value) {
      
      origem_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 origem = 1;</code>
     */
    public Builder clearOrigem() {
      
      origem_ = 0;
      onChanged();
      return this;
    }

    private int destino_ ;
    /**
     * <code>optional int32 destino = 2;</code>
     */
    public int getDestino() {
      return destino_;
    }
    /**
     * <code>optional int32 destino = 2;</code>
     */
    public Builder setDestino(int value) {
      
      destino_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional int32 destino = 2;</code>
     */
    public Builder clearDestino() {
      
      destino_ = 0;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:server.Cep)
  }

  // @@protoc_insertion_point(class_scope:server.Cep)
  private static final com.sd.dfc.server.Cep DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.sd.dfc.server.Cep();
  }

  public static com.sd.dfc.server.Cep getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Cep>
      PARSER = new com.google.protobuf.AbstractParser<Cep>() {
    public Cep parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new Cep(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Cep> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Cep> getParserForType() {
    return PARSER;
  }

  public com.sd.dfc.server.Cep getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

