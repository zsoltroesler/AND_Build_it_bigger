package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class EndPointsAsyncTaskTest extends ApplicationTestCase<Application> {

    private String joke;
    private CountDownLatch signal = new CountDownLatch(1);

    public EndPointsAsyncTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        signal.countDown();
    }

    @Test
    public void testAsyncTask() throws InterruptedException {
        EndpointsAsyncTask endpointsAsyncTask = (EndpointsAsyncTask) new EndpointsAsyncTask(new EndpointsAsyncTask.EndPointsAsyncTaskListener() {
            @Override
            public void onResultReceived(String result) {
                try {
                    joke = result;
                    signal.countDown();
                    Log.d("EndPointsAsyncTaskTest", "Test joke retrieved: " + joke);
                } catch (Exception e) {
                    Log.e("EndPointsAsyncTaskTest", "Exception error caught: " + e);
                }
            }
        }).execute();
        signal.await();

        assertFalse(TextUtils.isEmpty(joke));
    }
}
