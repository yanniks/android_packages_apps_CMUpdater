/*
 * Copyright (C) 2012 The CyanogenMod Project (DvTonder)
 *
 * * Licensed under the GNU GPLv2 license
 *
 * The text of the license can be found in the LICENSE file
 * or at https://www.gnu.org/licenses/gpl-2.0.txt
 */

package com.cyanogenmod.updater;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class UpdatesSettings extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_empty_list);
        try {
            Intent i = new Intent("android.intent.action.MAIN"); 
            ComponentName n = new 
            ComponentName("de.yanniks.cm_updatechecker", 
            "de.yanniks.cm_updatechecker.UpdateChecker"); 
            i.setComponent(n); 
            startActivity(i); 
        } catch(ActivityNotFoundException e) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=de.yanniks.cm_updatechecker"));
            startActivity(intent);
        }
    }

}
