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
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import java.io.IOException;
import java.io.OutputStream;

public class StartUpdate extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
                    try {
                        // Set the 'boot recovery' command
                        Process p = Runtime.getRuntime().exec("sh");
                        OutputStream os = p.getOutputStream();
                        os.write("mkdir -p /cache/recovery/\n".getBytes());
                        os.write("echo 'boot-recovery' >/cache/recovery/command\n".getBytes());

                        // See if backups are enabled and add the nandroid flag
                        /* TODO: add this back once we have a way of doing backups that is not recovery specific
                        SharedPreferences prefs = getSharedPreferences("CMUpdate", Context.MODE_MULTI_PROCESS);
                        if (prefs.getBoolean(Constants.BACKUP_PREF, true)) {
                            os.write("echo '--nandroid'  >> /cache/recovery/command\n".getBytes());
                        }
                        */

                        // Add the update folder/file name
                        String cmd = "echo '--update_package="
                                + " /sdcard/" + "Download/cm-current.zip"
                                + "' >> /cache/recovery/command\n";
                        os.write(cmd.getBytes());
                        os.flush();

                        // Trigger the reboot
                        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
                        powerManager.reboot("recovery");

                    } catch (IOException e) {
                    }
    }
}