package com.sd.dfc.data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author jefferson
 */
public class DatabaseTest  {
    
    private final static int NUM_THREADS = 20;
    private final static int  NUM_ITERATIONS =10000;
    
    
    @Test
    public void testCreate_multiThreaded() throws InterruptedException {
    
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        Database db = new Database();
        String command = "a command";
        for (int i = 0; i < NUM_THREADS; i++) {
          executor.submit(new Runnable() {
            @Override
            public void run() {
              for (int i = 0; i < NUM_ITERATIONS; i++) {
                db.create(command.getBytes());
              }
            }
          });
        }

        executor.shutdown();
        executor.awaitTermination(1000, TimeUnit.SECONDS);

        long totalIterations = NUM_THREADS * NUM_ITERATIONS;
        //assertEquals(Database2.getCount(),  totalIterations,"Os valores não são iguais "+Database.getCount()+ " e "+ totalIterations );
        //Database.setCount(0L);
    }
}
