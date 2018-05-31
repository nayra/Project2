package com.nayra.maraiina.notifications;

import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;

public class MaraeenaNotificationExtender extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
        // Read Properties from result
        //if(notification.payload.additionalData.has("ddd"));

        OverrideSettings overrideSettings = new OverrideSettings();
        overrideSettings.extender = builder -> {
            // Sets the background notification color to Red on Android 5.0+ devices.
            return builder;
        };


        OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);

        Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);

        // Return true to stop the notification from displaying
        return true;
    }
}
