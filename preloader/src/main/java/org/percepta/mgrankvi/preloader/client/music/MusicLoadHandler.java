package org.percepta.mgrankvi.preloader.client.music;

/**
 * Audio loading handler interface
 *
 * @author Mikael Grankvist - Vaadin }>
 */
public interface MusicLoadHandler {

    /**
     * Audio load event containing if load successful or failed, audio, file and loader.
     *
     * @param event
     */
    public void musicLoaded(MusicLoadEvent event);
}
