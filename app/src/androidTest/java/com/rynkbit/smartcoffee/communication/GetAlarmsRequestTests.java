package com.rynkbit.smartcoffee.communication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.android.volley.VolleyError;
import com.rynkbit.smartcoffee.communication.request.GetAlarmsRequest;
import com.rynkbit.smartcoffee.communication.listener.GetAlarmRequestListener;
import com.rynkbit.smartcoffee.entitiy.Alarm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Integration Tests
 *  Python Coffee Server must be running to be successful
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4.class)
public class GetAlarmsRequestTests {
    Context mContext;

    @Before
    public void setup(){
        mContext = InstrumentationRegistry.getInstrumentation().getContext();
    }

    @Test
    public void testGetAlarms(){
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        GetAlarmsRequest alarmsRequest = new GetAlarmsRequest(mContext);
        alarmsRequest.setListener(new GetAlarmRequestListener() {
            @Override
            public void onResponse(List<Alarm> alarms) {
                assertNotNull(alarms);
                countDownLatch.countDown();
            }

            @Override
            public void onError(VolleyError error) {
                fail(error.getMessage());
            }
        });
        alarmsRequest.sendGetAlarmsRequest(mContext);

        try {
            countDownLatch.await(10000, TimeUnit.SECONDS);

            if(countDownLatch.getCount() > 0){
                fail();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            fail();
        }
    }
}
