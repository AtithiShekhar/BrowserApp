package com.test.koibrowser.Interface;

import com.test.koibrowser.activities.MainActivity;
import com.test.koibrowser.adapter.SuggestionsAdapter;
import com.test.koibrowser.app.AppModule;
import com.test.koibrowser.app.BrowserApp;
import com.test.koibrowser.app.BrowserPresenter;
import com.test.koibrowser.downloads.DownloadStart;
import com.test.koibrowser.fragment.TabsFragment;
import com.test.koibrowser.manager.TabsManager;
import com.test.koibrowser.utils.AdBlock;
import com.test.koibrowser.view.BrowserView;
import com.test.koibrowser.view.webClient.WebClient;

import dagger.Component;
import javax.inject.Singleton;

@Component(modules = {AppModule.class})
@Singleton

public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(SuggestionsAdapter suggestionsAdapter);

    void inject(BrowserApp browserApp);

    void inject(BrowserPresenter browserPresenter);

    void inject(DownloadStart downloadStart);

    void inject(TabsFragment tabsFragment);

    void inject(TabsManager tabsManager);

    void inject(AdBlock adBlock);

    void inject(BrowserView browserView);

    void inject(WebClient webClient);
}
