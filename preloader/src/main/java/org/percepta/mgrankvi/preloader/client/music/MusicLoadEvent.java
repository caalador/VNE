package org.percepta.mgrankvi.preloader.client.music;

import com.google.gwt.media.client.Audio;

/**
 * Audio load event
 *
 * @author Mikael Grankvist - Vaadin }>
 */
public class MusicLoadEvent {

    private MusicLoader loader;
    private Audio audio;
    private boolean success;
    private String file;

    public MusicLoadEvent(MusicLoader loader, Audio audio, String file, boolean success) {
        this.loader = loader;
        this.audio = audio;
        this.file = file;
        this.success = success;
    }
    public MusicLoadEvent(Audio audio, String file, boolean success) {
        this.audio = audio;
        this.file = file;
        this.success = success;
    }

    protected MusicLoader getLoader() {
        return loader;
    }

    protected void setLoader(MusicLoader loader) {
        this.loader = loader;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
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
