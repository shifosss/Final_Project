package interface_adapter.services;

import interface_adapter.services.image_service.ImageServiceInterface;

/**
 * Handles/manages services available.
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
