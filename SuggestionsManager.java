package com.test.koibrowser.search;

import android.content.Context;
import androidx.annotation.NonNull;

import com.test.koibrowser.Interface.Action;
import com.test.koibrowser.Interface.Subscriber;
import com.test.koibrowser.Interface.SuggestionsResult;
import com.test.koibrowser.app.BrowserApp;
import com.test.koibrowser.database.HistoryItem;
import com.test.koibrowser.utils.Observable;

import java.util.List;

public class SuggestionsManager {
    private static volatile boolean sIsTaskExecuting;

    public enum Source {
        GOOGLE
    }

    public static boolean isRequestInProgress() {
        return sIsTaskExecuting;
    }

    public static Observable<List<HistoryItem>> getObservable(@NonNull final String str, @NonNull Context context, @NonNull final Source source) {
        final BrowserApp browserApp = BrowserApp.get(context);

        return Observable.create(new Action<List<HistoryItem>>() {
            @Override
            public void onSubscribe(@NonNull final Subscriber<List<HistoryItem>> subscriber) {
                sIsTaskExecuting = true;

                if (source == Source.GOOGLE) {
                    new GoogleSuggestionsTask(str, browserApp, new SuggestionsResult() {
                        @Override
                        public void resultReceived(@NonNull List<HistoryItem> list) {
                            subscriber.onNext(list);
                            subscriber.onComplete();
                        }
                    }).run();
                }

                sIsTaskExecuting = false;
            }
        });
    }
}