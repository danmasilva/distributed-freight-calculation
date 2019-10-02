package com.sd.dfc;

import com.sd.dfc.client.SocketClient;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private SocketClient client;

    @BeforeMethod
    public void setUp() {
        client = new SocketClient();
    }

    @Test
    public void testConnection() {
    }

    @AfterMethod
    public void tearDown() {
        client = null;
    }

}
