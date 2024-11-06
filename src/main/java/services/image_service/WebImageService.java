package services.image_service;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Web Image Service that fetches image from the web.
 */
public class WebImageService implements ImageServiceInterface {
    @Override
    public ImageIcon fetchImage(String path) {
        return getImageIcon(path);
    }

    private ImageIcon getImageIcon(String path) {
        ImageIcon result = new ImageIcon();
        try {
            final URL url = new URL(path);
            final BufferedImage image = ImageIO.read(url);

            // Optionally scale the image to fit the panel size
            final Image scaledImage = image.getScaledInstance(180, 150, Image.SCALE_SMOOTH);
            result = new ImageIcon(scaledImage);

        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
