package org.percepta.mgrankvi.vne.client.preloader.music;

import com.google.gwt.media.client.Audio;

/**
 * * @author Mikael Grankvist - Vaadin }>
 */
public class MusicLoadEvent {


    private Audio target;
    private boolean success;
    private String file;

    public MusicLoadEvent(String file, boolean success) {
        this.file = file;
        this.success = success;
    }

    public MusicLoadEvent(Audio target, String file, boolean success) {
        this.target = target;
        this.file = file;
        this.success = success;
    }

    public Audio getTarget() {
        return target;
    }

    public void setTarget(Audio target) {
        this.target = target;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
