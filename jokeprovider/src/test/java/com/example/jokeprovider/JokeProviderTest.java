/*
 * Copyright (c) 2016. Benjamin Schulz (github.com/bysy)
 */

package com.example.jokeprovider;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class JokeProviderTest {
    @Test
    public void getJoke_returnsSomething() {
        String joke = new JokeProvider().getJoke();
        assertTrue(joke!=null && !joke.isEmpty());
    }
}
