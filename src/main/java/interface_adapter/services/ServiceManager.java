package interface_adapter.services;

import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.video_service.VideoServiceInterface;

/**
 * Handles/manages services available.
 */
public class ServiceManager {
    private final ImageServiceInterface webImageService;
    private final ImageServiceInterface localImageService;

    private final VideoServiceInterface webVideoService;

    public ServiceManager(ImageServiceInterface webImageService,
                          ImageServiceInterface localImageService,
                          VideoServiceInterface webVideoService) {
        this.webImageService = webImageService;
        this.localImageService = localImageService;
        this.webVideoService = webVideoService;
    }

    public ImageServiceInterface getWebImageService() {
        return webImageService;
    }

    public ImageServiceInterface getLocalImageService() {
        return localImageService;
    }

    public VideoServiceInterface getWebVideoService() {
        return webVideoService;
    }
}
