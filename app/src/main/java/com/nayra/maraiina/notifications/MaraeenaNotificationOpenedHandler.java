package com.nayra.maraiina.notifications;

import android.content.Context;
import android.content.Intent;

import com.nayra.maraiina.views.MainActivity;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

public class MaraeenaNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private Context mContext;

    public MaraeenaNotificationOpenedHandler(Context context) {
        mContext = context;
    }

    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        /*OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String customKey;

        if (data != null) {
            customKey = data.optString("customkey", null);
            if (customKey != null)
                Log.i("OneSignalExample", "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);
*/
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.putExtra("menu", 2);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }
}
