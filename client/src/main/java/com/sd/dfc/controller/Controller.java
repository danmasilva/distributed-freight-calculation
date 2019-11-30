package com.sd.dfc.controller;

import io.grpc.ManagedChannel;

public interface Controller {
	int dealWith(String text, ManagedChannel channel);
}
