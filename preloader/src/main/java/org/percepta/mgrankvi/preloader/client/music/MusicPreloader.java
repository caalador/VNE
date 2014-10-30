package org.percepta.mgrankvi.preloader.client.music;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class MusicPreloader implements MusicLoadHandler {

    private List<MusicLoadHandler> handlers = new LinkedList<MusicLoadHandler>();
    private List<MusicLoader> activeLoaders = new LinkedList<MusicLoader>();
    private static Map<String, Audio> audioCache = new HashMap<String, Audio>();

    private final Element loadingArea;

    public MusicPreloader() {
        loadingArea = DOM.createDiv();
        Style style = loadingArea.getStyle();
        style.setVisibility(Style.Visibility.HIDDEN);
        style.setPosition(Style.Position.ABSOLUTE);
        style.setHeight(1, Style.Unit.PX);
        style.setWidth(1, Style.Unit.PX);
        style.setOverflow(Style.Overflow.HIDDEN);

        Document.get().getBody().appendChild(loadingArea);
    }

    public void preloadAudio(String url) {
        if (audioCache.containsKey(url)) {
            Audio audio = audioCache.get(url);
            fireEvent(new MusicLoadEvent(audio, url, true));
        } else if (urlInPool(url)) {
            return;
        }

        MusicLoader loader = new MusicLoader(loadingArea, this);
        activeLoaders.add(loader);
        // Remove loader from list if not supported
        if (!loader.loadAudio(url)) {
            activeLoaders.remove(loader);
            fireEvent(new MusicLoadEvent(null, url, false));
        }
    }

    public boolean urlInPool(String url) {
        for (MusicLoader loader : activeLoaders) {
            if (loader.src.equals(url)) {
                return true;
            }
        }
        return false;
    }

    public void addMusicLoadHandler(MusicLoadHandler handler) {
        handlers.add(handler);
    }

    public boolean removeMusicLoadHandler(MusicLoadHandler handler) {
        return handlers.remove(handler);
    }

    private void fireEvent(MusicLoadEvent event) {
        for (MusicLoadHandler handler : handlers) {
            handler.musicLoaded(event);
        }
    }

    @Override
    public void musicLoaded(MusicLoadEvent event) {
        audioCache.put(event.getFile(), event.getAudio());
        activeLoaders.remove(event.getLoader());

        fireEvent(event);
    }
}
