package org.percepta.mgrankvi.vne.client.preloader.music;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.event.dom.client.CanPlayThroughEvent;
import com.google.gwt.event.dom.client.CanPlayThroughHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.vaadin.client.VConsole;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class MusicLoader implements EventListener, CanPlayThroughHandler {
    AudioElement element;
    Audio audio;
    MusicLoadHandler handler;

    public MusicLoader(Element loadingArea, MusicLoadHandler handler) {
        this.handler = handler;
        audio = Audio.createIfSupported();
        if (audio != null) {
            element = audio.getAudioElement();

            audio.addCanPlayThroughHandler(this);
            Event.setEventListener(element, this);

            loadingArea.appendChild(element);
        }
    }

    public void loadAudio(String url) {
        String type = ".mp3";
        if (audio != null) {
            if (MediaElement.CAN_PLAY_PROBABLY.equals(audio.canPlayType(AudioElement.TYPE_OGG))) {
                type = ".ogg";
            } else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio.canPlayType(AudioElement.TYPE_MP3))) {
                type = ".mp3";
            } else if (MediaElement.CAN_PLAY_PROBABLY.equals(audio.canPlayType(AudioElement.TYPE_WAV))) {
                type = ".wav";
            } else if (MediaElement.CAN_PLAY_MAYBE.equals(audio.canPlayType(AudioElement.TYPE_OGG))) {
                type = ".ogg";
            } else if (MediaElement.CAN_PLAY_MAYBE.equals(audio.canPlayType(AudioElement.TYPE_MP3))) {
                type = ".mp3";
            } else if (MediaElement.CAN_PLAY_MAYBE.equals(audio.canPlayType(AudioElement.TYPE_WAV))) {
                type = ".wav";
            }else{
                return;
            }
            VConsole.log("Using audio type: " + type);
            audio.setSrc(url + type);
            audio.load();

        }
    }

    @Override
    public void onBrowserEvent(Event event) {
//        if (Event.ONLOAD == event.getTypeInt()) {
//            handler.musicLoaded(new MusicLoadEvent(audio, audio.getSrc(), true));
//        } else if (Event.ONERROR == event.getTypeInt()) {
//            handler.musicLoaded(new MusicLoadEvent(audio, audio.getSrc(), false));
//        }
        if (event.getType().equals("canplaythrough")) {
            handler.musicLoaded(new MusicLoadEvent(audio, audio.getSrc(), true));
        }
    }

    @Override
    public void onCanPlayThrough(CanPlayThroughEvent event) {
        VConsole.log(" -- Can play through!!");
        handler.musicLoaded(new MusicLoadEvent(audio, audio.getSrc(), true));
    }
}
