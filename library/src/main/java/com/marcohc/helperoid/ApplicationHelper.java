/*
 * Copyright (C) 2015 Marco Hernaiz Cao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.marcohc.helperoid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.os.Vibrator;

public class ApplicationHelper {

    private static final String GOOGLE_PLAY_STORE_TOKEN = "<div class=\"content\" itemprop=\"softwareVersion\">";
    private static final String GOOGLE_PLAY_STORE_APP_URL = "https://play.google.com/store/apps/details?id=";

    @SuppressWarnings("deprecation")
    public static void gotoAppInGooglePlay(Context context) {
        String appPackageName = context.getPackageName();
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName));
        marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        context.startActivity(marketIntent);
    }

    public static void shareApp(Context context, String shareText) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    public static void createApplicationShortcut(Context context, String packageName, int applicationName, int applicationIcon) {
        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        shortcutIntent.setClassName(context, packageName);

        Intent i = new Intent();
        i.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        i.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        i.putExtra(Intent.EXTRA_SHORTCUT_NAME, context.getResources().getString(applicationName));
        Parcelable iconResource = Intent.ShortcutIconResource.fromContext(context, applicationIcon);
        i.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);

        context.sendBroadcast(i);
    }

    public static boolean isConnectedToWifi(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static void sendEmail(Context context, String dialogTitle, String[] recipients, String subject, String body, String path) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (path != null) {
            intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + path));
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(Intent.createChooser(intent, dialogTitle));
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }

    public static void vibrate(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 50, 100, 50, 100, 50, 100};
        vibrator.vibrate(pattern, -1);
    }
}
