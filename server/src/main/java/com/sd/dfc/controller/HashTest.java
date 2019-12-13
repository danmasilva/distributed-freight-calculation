package com.sd.dfc.controller;

import java.nio.ByteBuffer;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashTest {
	public static void main(String[] args) throws DigestException, NoSuchAlgorithmException {
		String daniel = "oaula";
		for(int i=0; i<10; i++) {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			//messageDigest = algorithm.digest(data.getBytes("UTF-8"));
			//int messageDigest = algorithm.digest(daniel.getBytes(), 8, 16);
			byte[] messageDigest = algorithm.digest(daniel.getBytes());
			System.out.println(ByteBuffer.wrap(messageDigest).getInt()%15);
		}
	}
}
