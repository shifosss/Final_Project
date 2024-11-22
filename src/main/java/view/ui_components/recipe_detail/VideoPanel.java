package view.ui_components.recipe_detail;

import entities.recipe.Recipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.image_service.WebImageService;
import interface_adapter.services.video_service.VideoServiceInterface;
import interface_adapter.services.video_service.WebVideoService;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the playable video.
 */
public class VideoPanel extends JPanel {
    private final JLabel imageLabel;
    private final ServiceManager serviceManager;

    public VideoPanel(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;

        imageLabel = new JLabel();
        add(imageLabel);
    }

    /**
     * Updates the components of the video panel.
     * @param recipe the recipe entity.
     */
    public void updateComponents(Recipe recipe) {
        final String webLink = recipe.getVideoLink();
        if (!webLink.isEmpty()) {
            final VideoServiceInterface webVideoService = serviceManager.getWebVideoService();
            // TODO: Update the Web Video Service to return a playable video. <if possible or wanted>
        }
        else {
            final ImageServiceInterface imageService = serviceManager.getWebImageService();
            final ImageIcon recipeImage = imageService.fetchImage(recipe.getImageLink());
            imageLabel.setIcon(recipeImage);
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            add(imageLabel, BorderLayout.CENTER);
        }

    }
}
