package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainPage extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private final CardLayout parentCardLayout;
    private final JPanel parentPanel;

    private List<JLabel> imageLabels;
    private List<List<ImageIcon>> imageGroups;
    private int currentImageIndex = 0;

    public MainPage(CardLayout parentCardLayout, JPanel parentPanel) {
        this.parentCardLayout = parentCardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        // create content label
        JLabel headerLabel = new JLabel("cocktail lab");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // create a panel to store different images
        JPanel imagesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        imagesPanel.setBackground(BACKGROUND_COLOR);
        add(imagesPanel, BorderLayout.CENTER);

        // initialize image list
        imageGroups = loadImageGroups();
        imageLabels = new ArrayList<>();

        // create JLabel for every image and add to panel
        for (int i = 0; i < imageGroups.size(); i++) {
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            imageLabels.add(imageLabel);
            imagesPanel.add(imageLabel);
        }

        // image show effect
        startImageSlideshow();
    }

    private List<List<ImageIcon>> loadImageGroups() {
        List<List<ImageIcon>> imageGroups = new ArrayList<>();

        // define image groups
        List<ImageIcon> group1 = List.of(
                new ImageIcon(getClass().getResource("/image/OIP.jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (1).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (2).jpg"))
        );
        List<ImageIcon> group2 = List.of(
                new ImageIcon(getClass().getResource("/image/OIP (3).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (4).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (5).jpg"))
        );
        List<ImageIcon> group3 = List.of(
                new ImageIcon(getClass().getResource("/image/OIP (6).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP (7).jpg")),
                new ImageIcon(getClass().getResource("/image/OIP.jpg"))
        );

        // add image group to imageGroups
        imageGroups.add(group1);
        imageGroups.add(group2);
        imageGroups.add(group3);

        return imageGroups;
    }

    private void startImageSlideshow() {
        // set timer to 2 sec each photo
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 循环切换每个
                for (int i = 0; i < imageLabels.size(); i++) {
                    List<ImageIcon> images = imageGroups.get(i);
                    JLabel imageLabel = imageLabels.get(i);
                    imageLabel.setIcon(images.get(currentImageIndex % images.size()));
                }
                currentImageIndex++;
            }
        }, 0, 2000); // each 2 second change a photo
    }
}