package view.ui_components.user_profile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * User Icon Panel that stores the username and photo of the user.
 */
public class UserIconPanel extends JPanel {
    private final JLabel userLabel;
    private final JLabel photoLabel;
    private final JButton preferenceButton;

    public UserIconPanel(JButton preferenceButton) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(169, 169, 169));

        this.preferenceButton = preferenceButton;
        // add user icon
        photoLabel = new JLabel();
        final ImageIcon userPhoto = new ImageIcon(getClass().getResource("/image/icon.jpg"));
        final Image scaledPhoto = userPhoto.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        final ImageIcon roundedPhoto = createRoundedImage(new ImageIcon(scaledPhoto));
        photoLabel.setIcon(roundedPhoto);

        // add username
        userLabel = new JLabel();
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        userLabel.setForeground(Color.WHITE);

        add(photoLabel);
        add(userLabel);
        add(preferenceButton);
    }

    private ImageIcon createRoundedImage(ImageIcon imageIcon) {
        final int diameter = Math.min(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        final BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        final Graphics2D g2d = mask.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(0, 0, diameter, diameter);
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(imageIcon.getImage(), 0, 0, diameter, diameter, null);
        g2d.dispose();

        return new ImageIcon(mask);
    }

    /**
     * Updates the user icon panel components.
     * @param username the username
     */
    public void updateComponents(String username) {
        userLabel.setText(String.format("Username: %s", username));
    }
}
