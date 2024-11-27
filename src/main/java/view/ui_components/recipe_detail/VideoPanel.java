package view.ui_components.recipe_detail;

import entities.recipe.Recipe;
import interface_adapter.recipe_detail.RecipeDetailState;
import interface_adapter.services.ServiceManager;
import interface_adapter.services.image_service.ImageServiceInterface;
import interface_adapter.services.image_service.WebImageService;
import interface_adapter.services.video_service.VideoServiceInterface;
import interface_adapter.services.video_service.WebVideoService;
import view.AbstractViewDecorator;
import view.PageView;

import javax.swing.*;
import java.awt.*;

/**
 * Contains the playable video.
 */
public class VideoPanel extends AbstractViewDecorator<RecipeDetailState> {
    private final JLabel imageLabel;
    private final ServiceManager serviceManager;

    public VideoPanel(PageView<RecipeDetailState> view, ServiceManager serviceManager) {
        super(view);
        this.serviceManager = serviceManager;

        imageLabel = new JLabel();
        add(imageLabel);
    }

    @Override
    public void update(RecipeDetailState state) {
        super.getTempPage().update(state);
        final Recipe recipe = state.getRecipe();
        final ImageServiceInterface imageService = serviceManager.getWebImageService();
        final ImageIcon recipeImage = imageService.fetchImage(recipe.getImageLink());
        imageLabel.setIcon(recipeImage);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);
    }
}
