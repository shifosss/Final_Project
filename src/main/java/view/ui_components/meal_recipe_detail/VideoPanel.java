package view.ui_components.meal_recipe_detail;

import entities.recipe.MealRecipe;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.video_service.VideoServiceInterface;

import javax.swing.*;
import java.awt.*;

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
     * @param mealrecipe the recipe entity.
     */
    public void updateComponents(MealRecipe mealrecipe) {
        final String webLink = mealrecipe.getVideoLink();
        if (!webLink.isEmpty()) {
            final VideoServiceInterface webVideoService = serviceManager.getWebVideoService();
            // TODO: Update the Web Video Service to return a playable video. <if possible or wanted>
        }
        else {
            final ImageServiceInterface imageService = serviceManager.getWebImageService();
            final ImageIcon recipeImage = imageService.fetchImage(mealrecipe.getImageLink());
            imageLabel.setIcon(recipeImage);
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            add(imageLabel, BorderLayout.CENTER);
        }

    }
}
