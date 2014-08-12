package org.percepta.mgrankvi.vne.client.preloader.image;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class ImageLoader {

    ImageElement image = DOM.createImg().cast();

//    List<ImageLoadHandler> handlers;

    String url;

    public ImageLoader(Element loadingArea) {
        Event.sinkEvents(image, Event.ONLOAD | Event.ONERROR);
        loadingArea.appendChild(image);
    }

//    public void clearHandlers() {
//        if (handlers != null)
//            handlers.clear();
//    }
//
//    public void addHander(ImageLoadHandler handler) {
//        if (handler != null) {
//            if (handlers == null) {
//                handlers = new ArrayList<ImageLoadHandler>(1);
//            }
//            handlers.add(handler);
//        }
//    }
//
//    public void fireHandlers(ImageLoadEvent event) {
//        if (handlers != null) {
//            for (ImageLoadHandler handler : handlers) {
//                handler.imageLoaded(event);
//            }
//        }
//    }

    public void start(String url) {
        this.url = url;
        image.setSrc(url);
    }

    public boolean imageEquals(ImageElement image) {
        return this.image == image;
    }

    public boolean urlEquals(String url) {
        return this.url.equals(url);
    }
}
