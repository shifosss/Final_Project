package interface_adapter.services.image_service;

import javax.swing.ImageIcon;

/**
 * Image service handler interface that must be followed by image interface_adapter.services.
 */
public interface ImageServiceInterface {

    /**
     * Fetches/retrieves an image based on the given url.
     * @param path url/location where the image is stored.
     * @return an ImageIcon that renders the image.
     */
    ImageIcon fetchImage(String path);
}
