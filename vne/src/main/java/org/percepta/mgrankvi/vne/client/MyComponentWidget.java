package org.percepta.mgrankvi.vne.client;

import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.vaadin.client.Util;
import org.percepta.mgrankvi.preloader.client.image.ImageLoadEvent;
import org.percepta.mgrankvi.preloader.client.image.ImageLoadHandler;
import org.percepta.mgrankvi.preloader.client.image.ImagePreloader;
import org.percepta.mgrankvi.preloader.client.music.MusicLoadEvent;
import org.percepta.mgrankvi.preloader.client.music.MusicLoadHandler;
import org.percepta.mgrankvi.preloader.client.music.MusicPreloader;


// Extend any GWT Widget
public class MyComponentWidget extends Label implements ImageLoadHandler, MusicLoadHandler {

    ImagePreloader preloader = new ImagePreloader();
    MusicPreloader musicPreloader = new MusicPreloader();

    int loaded = 0;
    int toLoad = 0;
    int musicLoaded = 0;
    int musicToLoad = 0;

    public MyComponentWidget() {

        // CSS class-name should not be v- prefixed
        setStyleName("vne");

        // State is set to widget in MyComponentConnector
        preloader.addImageLoadListener(this);
        musicPreloader.addMusicLoadHandler(this);
    }

    private void updateText() {
        setText("Loaded: " + loaded + "/" + toLoad + " – Music: " + musicLoaded + "/" + musicToLoad);
    }

    public void preloadImage(String url) {
        preloader.preloadImage(Util.getAbsoluteUrl("./VAADIN/"+url));
        toLoad++;
        updateText();
    }
    public void preloadMusic(String url){
        musicPreloader.preloadAudio(Util.getAbsoluteUrl("./VAADIN/"+url));
        musicToLoad++;
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

    @Override
    public void musicLoaded(MusicLoadEvent event) {
        if(event.isSuccess()){
            musicLoaded++;
            event.getAudio().play();
            final Audio audio = event.getAudio();
            final double duration = audio.getDuration();
            Timer t = new Timer(){

                @Override
                public void run() {
                    setText("Loaded: " + loaded + "/" + toLoad + " – Music: " + musicLoaded + "/" + musicToLoad + "\n" + audio.getCurrentTime() + "/" + duration);
                    if(audio.hasEnded()){
                        this.cancel();
                    }
                }
            };
            t.scheduleRepeating(100);
        }
        updateText();
    }
}