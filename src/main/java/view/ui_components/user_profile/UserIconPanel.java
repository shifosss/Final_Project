package view.ui_components.user_profile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UserIconPanel extends JPanel {

    public UserIconPanel(String username) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(169, 169, 169));

        // add user icon
        JLabel photoLabel = new JLabel();
        ImageIcon userPhoto = new ImageIcon(getClass().getResource("/image/icon.jpg"));
        Image scaledPhoto = userPhoto.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon roundedPhoto = createRoundedImage(new ImageIcon(scaledPhoto));
        photoLabel.setIcon(roundedPhoto);

        // add username
        JLabel userLabel = new JLabel("Username: " + username);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        userLabel.setForeground(Color.WHITE);

        add(photoLabel);
        add(userLabel);
    }

    private ImageIcon createRoundedImage(ImageIcon imageIcon) {
        int diameter = Math.min(imageIcon.getIconWidth(), imageIcon.getIconHeight());
        BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = mask.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(0, 0, diameter, diameter);
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(imageIcon.getImage(), 0, 0, diameter, diameter, null);
        g2d.dispose();

        return new ImageIcon(mask);
    }
}
