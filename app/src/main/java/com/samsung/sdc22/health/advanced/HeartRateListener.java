/*
 * Copyright 2022 Samsung Electronics Co., Ltd. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samsung.sdc22.health.advanced;

import android.util.Log;

import androidx.annotation.NonNull;

import com.samsung.android.service.health.tracking.HealthTracker;
import com.samsung.android.service.health.tracking.data.DataPoint;
import com.samsung.android.service.health.tracking.data.ValueKey;

import java.util.List;

public class HeartRateListener extends BaseListener {
    private final static String APP_TAG = "HeartRateListener";

    HeartRateListener() {
        HealthTracker.TrackerEventListener trackerEventListener = new HealthTracker.TrackerEventListener() {
            @Override
            public void onDataReceived(@NonNull List<DataPoint> list) {
                for (DataPoint dataPoint : list) {
                    readValuesFromDataPoint(dataPoint);
                }
            }

            @Override
            public void onFlushCompleted() {
                Log.i(APP_TAG, " onFlushCompleted called");
            }

            @Override
            public void onError(HealthTracker.TrackerError trackerError) {
                Log.e(APP_TAG, " onError called: " + trackerError);
                setHandlerRunning(false);
                if (trackerError == HealthTracker.TrackerError.PERMISSION_ERROR) {
                    TrackerDataNotifier.getInstance().notifyError(R.string.NoPermission);
                }
                if (trackerError == HealthTracker.TrackerError.SDK_POLICY_ERROR) {
                    TrackerDataNotifier.getInstance().notifyError(R.string.SdkPolicyError);
                }
            }
        };
        setTrackerEventListener(trackerEventListener);
    }

    /*******************************************************************************************
     * [Practice 6] Read values from DataPoint object
     *  - Get heart rate status
     *  - Get heart rate value
     *  - Get heart rate ibi value
     *  - Check retrieved heart rate’s IBI and IBI quality values
     -------------------------------------------------------------------------------------------
     *  - (Hint) Replace TODO 6 with parts of code
     *      (1) set hrData.status from 'dataPoint' object using dataPoint.getValue(ValueKey.HeartRateSet.STATUS)
     *      (2) set hrData.hr from 'dataPoint' object using dataPoint.getValue(ValueKey.HeartRateSet.HEART_RATE)
     *      (3) set local variable 'final int hrIbi' using dataPoint.getValue(ValueKey.HeartRateSet.HEART_RATE_IBI)
     *      (4) set hrData.qIbi with the first of 16 bits of 'hrIbi' value
     *          (use HeartRateData.IBI_QUALITY_SHIFT 15 bits shift and HeartRateData.IBI_MASK 1 bit mask)
     *      (5) set hrData.ibi with the rest of the 15 bits of 'hrIbi' value
     *          (use HeartRateData.IBI_QUALITY_MASK 15 bit mask)
     ******************************************************************************************/

    public void readValuesFromDataPoint(DataPoint dataPoint) {
        HeartRateData hrData = new HeartRateData();
        //"TODO 6 (1)"
        //"TODO 6 (2)"
        //"TODO 6 (3)"
        //"TODO 6 (4)"
        //"TODO 6 (5)"
        TrackerDataNotifier.getInstance().notifyHeartRateTrackerObservers(hrData);
        Log.d(APP_TAG, dataPoint.toString());
    }

}
