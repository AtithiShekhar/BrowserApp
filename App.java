package com.test.koibrowser;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Process;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

import com.test.koibrowser.app.BrowserApp;
import com.test.koibrowser.callbacks.UserAgentInterceptor;
import com.test.koibrowser.dialog.AppSettings;
import com.test.koibrowser.fragment.DownloadingFragment;
import com.test.koibrowser.services.CustomDownloader;
import com.test.koibrowser.services.MainDownloadNotificationManager;
import com.tonyodev.fetch2.Fetch;
import com.tonyodev.fetch2.FetchConfiguration;
import com.tonyodev.fetch2rx.RxFetch;

import okhttp3.OkHttpClient;

public class App extends BrowserApp {
    public final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            MultiDex.install(this);

        } catch (Exception e) {

        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        checkAppReplacingState();
      //  Fetch.Impl.setDefaultInstanceConfiguration(new FetchConfiguration.Builder(this).enableRetryOnNetworkGain(true).setDownloadConcurrentLimit(99).setHttpDownloader(new CustomDownloader(new OkHttpClient.Builder().addNetworkInterceptor(new UserAgentInterceptor("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36")).build())).setHasActiveDownloadsCheckInterval(10000L).setProgressReportingInterval(1000L).setNamespace(DownloadingFragment.FETCH_NAMESPACE).setNotificationManager(new MainDownloadNotificationManager(this)).build());


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new UserAgentInterceptor("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36"))
                .build();
        CustomDownloader customDownloader = new CustomDownloader(okHttpClient);

        FetchConfiguration.Builder configurationBuilder = new FetchConfiguration.Builder(this)
                .enableRetryOnNetworkGain(true)
                .setDownloadConcurrentLimit(3)
                .setHttpDownloader(customDownloader)
                .setHasActiveDownloadsCheckInterval(10000L)
                .setProgressReportingInterval(1000L)
                .setNamespace(DownloadingFragment.FETCH_NAMESPACE)
                .setNotificationManager(new MainDownloadNotificationManager(this));

        FetchConfiguration fetchConfiguration = configurationBuilder.build();
        Fetch.Impl.setDefaultInstanceConfiguration(fetchConfiguration);
        RxFetch.Impl.setDefaultRxInstanceConfiguration(fetchConfiguration);

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", 0);
        if (!sharedPreferences.getBoolean("prefs_bg_downloader_scheduled", false)) {
            sharedPreferences.edit().putBoolean("prefs_bg_downloader_scheduled", true).apply();
        }
    }


    @SuppressLint("LongLogTag")
    private void checkAppReplacingState() {
        Log.d(this.TAG, "app start....");
        if (getResources() == null) {
            Log.d(this.TAG, "App is replacing... Kill");
            Process.killProcess(Process.myPid());
        }
    }

    private void initDefaultPreferenceSettings() {
        AppSettings.getInstance(this).setDefaultSettingsSharedPref();

    }

}
