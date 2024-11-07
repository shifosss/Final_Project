package interface_adapter.services.video_service;

import javax.swing.*;

/**
 * Video Service Interface that retrieves video to be played.
 */
public interface VideoServiceInterface {

    /**
     * Returns a playable video in the form of JComponent.
     * @param path source link where the video is stored.
     * @return a playable video JComponent.
     */
    JComponent fetchVideo(String path);
}
