package services;

import services.image_service.ImageServiceInterface;

/**
 * Handles services available.
 */
public class ServiceManager {
    private final ImageServiceInterface webImageService;
    private final ImageServiceInterface localImageService;

    public ServiceManager(ImageServiceInterface webImageService,
                          ImageServiceInterface localImageService) {
        this.webImageService = webImageService;
        this.localImageService = localImageService;
    }

    public ImageServiceInterface getWebImageService() {
        return webImageService;
    }

    public ImageServiceInterface getLocalImageService() {
        return localImageService;
    }
}
