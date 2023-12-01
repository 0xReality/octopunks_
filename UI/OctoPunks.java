package UI;

import java.awt.*;
import javax.swing.*;

import Compilation.Compilator;

import java.awt.event.*;
import java.io.IOException;

public class OctoPunks extends JFrame {

    private int width = 1980;
    private int height = 1080;
    private JTextArea textArea;
    private JButton execute;
    private JButton pause;
    private JButton step;

    public OctoPunks() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("OctoPunks");
        this.setBounds(0, 0, width, height);
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(width, 20));


        textArea = new JTextArea(20, 20);
        textArea.setBackground(Color.decode("#19171c"));
        textArea.setForeground(Color.decode("#7d7f88"));
        textArea.setFont(new Font("Consolas", Font.PLAIN, 20));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        execute = createButton("resources/play.png", Color.decode("#868f9e"), Color.DARK_GRAY);
        pause = createButton("resources/pause.png", Color.decode("#868f9e"), Color.DARK_GRAY);
        step = createButton("resources/step.png", Color.decode("#868f9e"), Color.DARK_GRAY);

        execute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Compilator k = new Compilator(textArea.getText(),1);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 350, 0); 
        buttonPanel.add(pause, gbc); 
        gbc.insets = new Insets(0, 0, 350, 0);
        buttonPanel.add(execute, gbc);
        gbc.insets = new Insets(0, 0, 350, 0);
        buttonPanel.add(step, gbc);

        this.add(textArea, BorderLayout.WEST); 
        this.add(buttonPanel, BorderLayout.SOUTH); 
        this.add(spacer, BorderLayout.NORTH);
        this.add(textArea, BorderLayout.WEST);

        this.setVisible(true);  
    }


    private JButton createButton(String path, Color normalColor, Color hoverColor) {
        ImageIcon originalIcon = null;
        try {
            originalIcon = new ImageIcon(path);
            if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
                throw new IOException("Image load failed.");
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
            originalIcon = new ImageIcon(); // Create an empty icon as fallback
        }
    
        // Resize the icon
        Image image = originalIcon.getImage();
        Image resizedImage = image.getScaledInstance(35, 35, Image.SCALE_REPLICATE);
        ImageIcon icon = new ImageIcon(resizedImage);
    
        JButton button = new JButton(icon);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(normalColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(40, 40)); // Set preferred size, slightly larger than the image
    
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
                button.setForeground(normalColor);
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(normalColor);
                button.setForeground(hoverColor);
            }
        });
    
        return button;
    }
    
}
