package org.percepta.mgrankvi.vne.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.client.Util;
import org.percepta.mgrankvi.vne.client.preloader.image.ImageLoadEvent;
import org.percepta.mgrankvi.vne.client.preloader.image.ImageLoadHandler;
import org.percepta.mgrankvi.vne.client.preloader.image.ImagePreloader;

// Extend any GWT Widget
public class MyComponentWidget extends Label implements ImageLoadHandler {

    ImagePreloader preloader = new ImagePreloader();
    int loaded = 0;
    int toLoad = 0;

    public MyComponentWidget() {

        // CSS class-name should not be v- prefixed
        setStyleName("vne");

        // State is set to widget in MyComponentConnector
        preloader.addImageLoadListener(this);
    }

    private void updateText() {
        setText("Loaded: " + loaded + "/" + toLoad);
    }

    public void preload(String url) {
        preloader.preloadImage(Util.getAbsoluteUrl("./VAADIN/"+url));
        toLoad++;
        updateText();
    }

    @Override
    public void imageLoaded(ImageLoadEvent event) {
        if (event.isSuccess())
            loaded++;
        else
            Window.alert("Failed to load image " + event.getFile());
        updateText();
    }
}