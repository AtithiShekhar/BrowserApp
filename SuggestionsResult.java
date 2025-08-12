package com.test.koibrowser.Interface;

import androidx.annotation.NonNull;
import com.test.koibrowser.database.HistoryItem;

import java.util.List;


public interface SuggestionsResult {
    void resultReceived(@NonNull List<HistoryItem> list);
}
