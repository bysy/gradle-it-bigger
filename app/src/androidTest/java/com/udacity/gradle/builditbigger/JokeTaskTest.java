/*
 * Copyright (c) 2016. Benjamin Schulz
 */

package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Test JokeTask.
 */
public class JokeTaskTest extends AndroidTestCase {
    public void testExecute() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        class TestExecuteHelper implements JokeTask.PostJokeTask {
            String joke;
            @Override
            public void runPostJokeTask(String result) {
                joke = result;
                latch.countDown();
            }
        }
        TestExecuteHelper helper = new TestExecuteHelper();
        new JokeTask().execute(helper);
        latch.await(5, TimeUnit.SECONDS);
        assertTrue("Did not retrieve a valid joke string",
                helper.joke!=null && !helper.joke.isEmpty());
    }
}
