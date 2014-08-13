package org.percepta.mgrankvi.vne.client.preloader.music;

import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.vaadin.client.VConsole;

import java.util.LinkedList;
import java.util.List;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class MusicPreloader implements MusicLoadHandler {

    private List<MusicLoadHandler> handlers = new LinkedList<MusicLoadHandler>();

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
        MusicLoader loader = new MusicLoader(loadingArea, this);
        loader.loadAudio(url);
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
        fireEvent(event);
    }
}
