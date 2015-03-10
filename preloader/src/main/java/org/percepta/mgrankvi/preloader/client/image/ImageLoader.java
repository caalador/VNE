package org.percepta.mgrankvi.preloader.client.image;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

/**
 * Image loader
 *
 * @author Mikael Grankvist - Vaadin }>
 */
public class ImageLoader implements EventListener {

    ImageElement image = DOM.createImg().cast();

    ImageLoadHandler handler;

    String url;

    /**
     * Create a image loader
     *
     * @param loadingArea Element to use for loading
     * @param handler     Callback handler
     */
    public ImageLoader(Element loadingArea, ImageLoadHandler handler) {
        this.handler = handler;
        Event.sinkEvents(image, Event.ONLOAD | Event.ONERROR);
        Event.setEventListener(image, this);
        loadingArea.appendChild(image);
    }

    public ImageElement getElement() {
        return image;
    }

    /**
     * Begin loading image from url
     *
     * @param url image url
     */
    public void start(String url) {
        this.url = url;
        image.setSrc(url);
    }

    /**
     * See if this loader handles given url.
     *
     * @param url image url
     * @return url for this loader
     */
    public boolean urlEquals(String url) {
        return this.url.equals(url);
    }

    @Override
    public void onBrowserEvent(Event event) {
        if (Event.ONLOAD == event.getTypeInt()) {
            handler.imageLoaded(new ImageLoadEvent(this, url, null, true));
        } else if (Event.ONERROR == event.getTypeInt()) {
            handler.imageLoaded(new ImageLoadEvent(this, url, null, false));
        }
    }
}
