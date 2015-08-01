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
import android.widget.ListAdapter;

import com.afollestad.materialdialogs.MaterialDialog;

public class DialogHelper {

    public static MaterialDialog showAlertDialog(int message, int acceptButtonText, Context context) {
        return new MaterialDialog.Builder(context).content(message).positiveText(acceptButtonText).show();
    }

    public static MaterialDialog showInformationDialog(int title, int message, int acceptButtonText, Context context) {
        return new MaterialDialog.Builder(context).title(title).content(message).positiveText(acceptButtonText).show();
    }

    public static MaterialDialog showInformationDialog(int title, int message, int acceptButtonText, MaterialDialog.ButtonCallback callback, Context context) {
        return new MaterialDialog.Builder(context).title(title).content(message).positiveText(acceptButtonText).callback(callback).show();
    }

    public static MaterialDialog showConfirmationDialog(int title, int message, int acceptButtonText, int cancelButtonText, Context context, MaterialDialog.ButtonCallback callback) {
        return new MaterialDialog.Builder(context).title(title).content(message).positiveText(acceptButtonText).negativeText(cancelButtonText).callback(callback).show();
    }

    public static MaterialDialog showConfirmationDialog(int message, int acceptButtonText, int cancelButtonText, Context context, MaterialDialog.ButtonCallback callback) {
        return new MaterialDialog.Builder(context).content(message).positiveText(acceptButtonText).negativeText(cancelButtonText).callback(callback).show();
    }

    public static MaterialDialog showSelectionDialog(int title, String[] items, Context context, MaterialDialog.ListCallback listCallback) {
        return new MaterialDialog.Builder(context).title(title).items(items).itemsCallback(listCallback).show();
    }

    public static MaterialDialog showSingleChoiceDialog(int title, int selectedIndex, String[] items, Context context, MaterialDialog.ListCallbackSingleChoice listCallback) {
        return new MaterialDialog.Builder(context).title(title).items(items).itemsCallbackSingleChoice(selectedIndex, listCallback).show();
    }

    public static MaterialDialog getCustomDialog(int title, int acceptButtonText, int cancelButtonText, int layout, Context context, MaterialDialog.ButtonCallback callback) {
        return new MaterialDialog.Builder(context).title(title).customView(layout, false).positiveText(acceptButtonText).negativeText(cancelButtonText).callback(callback).build();
    }

    public static MaterialDialog getCustomListViewDialog(int title, int acceptButtonText, int cancelButtonText, ListAdapter adapter, Context context, MaterialDialog.ListCallback listCallback, MaterialDialog.ButtonCallback buttonCallback) {
        return new MaterialDialog.Builder(context).autoDismiss(false).title(title).adapter(adapter, listCallback).positiveText(acceptButtonText).negativeText(cancelButtonText).callback(buttonCallback).build();
    }

}
