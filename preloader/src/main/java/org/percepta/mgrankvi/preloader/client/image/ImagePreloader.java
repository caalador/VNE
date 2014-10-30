package org.percepta.mgrankvi.preloader.client.image;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class ImagePreloader implements ImageLoadHandler {

    private List<ImageLoadHandler> listeners = new LinkedList<ImageLoadHandler>();

    private List<ImageLoader> activeLoaders = new LinkedList<ImageLoader>();
    private static Map<String, Size> imageSizeCache = new HashMap<String, Size>();

    private final Element loadingArea;

    public ImagePreloader() {
        loadingArea = DOM.createDiv();
        Style style = loadingArea.getStyle();
        style.setVisibility(Style.Visibility.HIDDEN);
        style.setPosition(Style.Position.ABSOLUTE);
        style.setHeight(1, Style.Unit.PX);
        style.setWidth(1, Style.Unit.PX);
        style.setOverflow(Style.Overflow.HIDDEN);

        Document.get().getBody().appendChild(loadingArea);
    }

    public void preloadImage(String url) {

        if (url == null) {
            // Fire failure event for null url.
            fireEvent(new ImageLoadEvent(url, null, false));
            return;
        }

        if (imageSizeCache.containsKey(url)) {
            Size cachedDimensions = imageSizeCache.get(url);
            if (cachedDimensions.getWidth() == -1) {
                // image load failed
                fireEvent(new ImageLoadEvent(url, null, false));
            } else {
                // image load succeeded
                fireEvent(new ImageLoadEvent(url, cachedDimensions, true));
            }
            return;
        } else {
            int index = findUrlInPool(url);
            if (index != -1) {
                return;
            }
        }

        ImageLoader img = new ImageLoader(loadingArea, this);
        activeLoaders.add(img);
        img.start(url);

    }

    public void addImageLoadListener(ImageLoadHandler listener) {
        listeners.add(listener);
    }

    public boolean removeImageLoadListener(ImageLoadHandler listener) {
        return listeners.remove(listener);
    }

    private void fireEvent(ImageLoadEvent event) {
        for (ImageLoadHandler listener : listeners) {
            listener.imageLoaded(event);
        }
    }

    private int findUrlInPool(String url) {
        for (int index = 0; index < activeLoaders.size(); index++) {
            if (activeLoaders.get(index).urlEquals(url)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void imageLoaded(ImageLoadEvent event) {

        boolean success = event.isSuccess();

        ImageLoader loader = event.getTarget();

        ImageElement image = loader.getElement();

        Size imageSize = null;
        if (success) {
            imageSize = new Size(image.getWidth(), image.getHeight());
            imageSizeCache.put(loader.url, imageSize);
        } else {
            imageSizeCache.put(loader.url, new Size(-1, -1));
        }

        loadingArea.removeChild(image);
        activeLoaders.remove(loader);

        fireEvent(new ImageLoadEvent(image.getSrc(), imageSize, success));

    }
}
