package org.percepta.mgrankvi.preloader.client.image;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class ImageLoadEvent {

    private ImageLoader target;
    private boolean success;
    private String file;
    private Size size;

    public ImageLoadEvent(String file, Size size, boolean success) {
        this.file = file;
        this.size = size;
        this.success = success;
    }

    public ImageLoadEvent(ImageLoader target, String file, Size size, boolean success) {
        this.target = target;
        this.file = file;
        this.size = size;
        this.success = success;
    }

    public ImageLoader getTarget() {
        return target;
    }

    public void setTarget(ImageLoader target) {
        this.target = target;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
