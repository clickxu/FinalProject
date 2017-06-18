package com.udacity.gradle.builditbigger.testing;


import android.support.v4.util.Pair;
import android.text.TextUtils;

import com.udacity.gradle.builditbigger.JokesAsyncTask;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;

/**
 * Created by t-xu on 2017/06/13.
 */
public class JokeTellerAsyncTaskTest {

    @Test
    public void jokesAsyncTaskFreeOK() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        new JokesAsyncTask().execute(
                new Pair<JokesAsyncTask.CallBack, Boolean>(
                        new JokesAsyncTask.CallBack() {
            @Override
            public void onResult(String result) {
                assertFalse(TextUtils.isEmpty(result));
                signal.countDown();
            }
        }, false));
        signal.await();
    }

    @Test
    public void jokesAsyncTaskPaidOK() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        new JokesAsyncTask().execute(
                new Pair<JokesAsyncTask.CallBack, Boolean>(
                        new JokesAsyncTask.CallBack() {
                            @Override
                            public void onResult(String result) {
                                assertFalse(TextUtils.isEmpty(result));
                                signal.countDown();
                            }
                        }, true));
        signal.await();
    }
}
