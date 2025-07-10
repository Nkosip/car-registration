package com.cars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class CarSwingApp {
    private static Collection<Car> cars = new ArrayList<Car>();
    private static JFrame mainFrame;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    public static void main(String[] args) {
        // Create the main frame
        mainFrame = new JFrame("Car Registration System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 400);
        mainFrame.setLocationRelativeTo(null);

        // Create card layout for switching between panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create panels
        JPanel menuPanel = createMenuPanel();
        JPanel capturePanel = createCapturePanel();
        JPanel reportPanel = createReportPanel();

        // Add panels to card layout
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(capturePanel, "capture");
        mainPanel.add(reportPanel, "report");

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private static JPanel createMenuPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Welcome to the Car Registration System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton captureButton = new JButton("Capture Vehicle Details");
        captureButton.addActionListener(e -> cardLayout.show(mainPanel, "capture"));

        JButton reportButton = new JButton("View Vehicle Report");
        reportButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "report");
            updateReportPanel();
        });

        JButton exitButton = new JButton("Exit App");
        exitButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame, "Thanks for using the app");
            System.exit(0);
        });

        panel.add(titleLabel);
        panel.add(captureButton);
        panel.add(reportButton);
        panel.add(exitButton);

        return panel;
    }

    private static JPanel createCapturePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create form components
        JLabel titleLabel = new JLabel("Capture Vehicle Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel makeLabel = new JLabel("Make:");
        JTextField makeField = new JTextField(20);

        JLabel modelLabel = new JLabel("Model:");
        JTextField modelField = new JTextField(20);

        JLabel vinLabel = new JLabel("VIN (17 characters):");
        JTextField vinField = new JTextField(20);

        JLabel plateFormatLabel = new JLabel("License Plate Format:");
        ButtonGroup plateFormatGroup = new ButtonGroup();
        JRadioButton oldFormatRadio = new JRadioButton("Old Format (AAA555)");
        JRadioButton newFormatRadio = new JRadioButton("New Format (AA66BBBP)");
        plateFormatGroup.add(oldFormatRadio);
        plateFormatGroup.add(newFormatRadio);

        JLabel plateLabel = new JLabel("Plate Number:");
        JTextField plateField = new JTextField(20);

        JLabel mileageLabel = new JLabel("Mileage:");
        JTextField mileageField = new JTextField(20);

        JLabel yearLabel = new JLabel("Year:");
        JTextField yearField = new JTextField(20);

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back to Menu");

        // Add components to panel
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(makeLabel, gbc);
        gbc.gridx = 1;
        panel.add(makeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(modelLabel, gbc);
        gbc.gridx = 1;
        panel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(vinLabel, gbc);
        gbc.gridx = 1;
        panel.add(vinField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(plateFormatLabel, gbc);
        gbc.gridx = 1;
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioPanel.add(oldFormatRadio);
        radioPanel.add(newFormatRadio);
        panel.add(radioPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(plateLabel, gbc);
        gbc.gridx = 1;
        panel.add(plateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(mileageLabel, gbc);
        gbc.gridx = 1;
        panel.add(mileageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(yearLabel, gbc);
        gbc.gridx = 1;
        panel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        // Add action listeners
        saveButton.addActionListener(e -> {
            // Validate inputs
            if (makeField.getText().isEmpty() || modelField.getText().isEmpty() ||
                    vinField.getText().isEmpty() || plateField.getText().isEmpty() ||
                    mileageField.getText().isEmpty() || yearField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (vinField.getText().length() != 17) {
                JOptionPane.showMessageDialog(panel, "VIN must be exactly 17 characters long", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!oldFormatRadio.isSelected() && !newFormatRadio.isSelected()) {
                JOptionPane.showMessageDialog(panel, "Please select a license plate format", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int mileage = Integer.parseInt(mileageField.getText());
                int year = Integer.parseInt(yearField.getText());

                // Validate plate number based on selected format
                String plateNumber = plateField.getText();
                if (oldFormatRadio.isSelected()) {
                    if (!plateNumber.matches("[A-Za-z]{3}\\d{3}")) {
                        JOptionPane.showMessageDialog(panel, "Old format plate must be 3 letters followed by 3 numbers (e.g., AAA555)", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } else {
                    if (!plateNumber.matches("[A-Za-z]{2}\\d{2}[A-Za-z]{3}\\d?")) {
                        JOptionPane.showMessageDialog(panel, "New format plate must be 2 letters, 2 numbers, 3 letters, and optional number (e.g., AA66BBBP)", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Create and save car
                Car car = new Car();
                car.setMake(makeField.getText());
                car.setModel(modelField.getText());
                car.setVin(vinField.getText());
                car.setPlateNumber(plateNumber);
                car.setMillage(mileage);
                car.setYear(year);

                cars.add(car);
                JOptionPane.showMessageDialog(panel, "Vehicle details saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear fields
                makeField.setText("");
                modelField.setText("");
                vinField.setText("");
                plateField.setText("");
                mileageField.setText("");
                yearField.setText("");
                plateFormatGroup.clearSelection();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Mileage and Year must be numbers", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        return panel;
    }

    private static JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Vehicle Report", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(reportArea);

        JButton backButton = new JButton("Back to Menu");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private static void updateReportPanel() {
        //JTextArea reportArea = (JTextArea) ((JScrollPane) ((BorderLayout) mainPanel.getComponent(2).getLayout()).getLayoutComponent(BorderLayout.CENTER)).getViewport().getView();
        JPanel reportPanel = (JPanel) mainPanel.getComponent(2);
        JScrollPane scrollPane = (JScrollPane) reportPanel.getComponent(1);
        JTextArea reportArea = (JTextArea) scrollPane.getViewport().getView();

        if (cars.isEmpty()) {
            reportArea.setText("There are no cars captured");
        } else {
            StringBuilder report = new StringBuilder();
            report.append("***********************************************\n");
            report.append("                   VEHICLE REPORT               \n");
            report.append("***********************************************\n\n");

            for (Car car : cars) {
                report.append(String.format("MAKE: %-15s MODEL: %-15s\n", car.getMake(), car.getModel()));
                report.append(String.format("VIN: %-17s PLATE: %-10s\n", car.getVin(), car.getPlateNumber()));
                report.append(String.format("YEAR: %-4d MILEAGE: %-6d\n\n", car.getYear(), car.getMillage()));
                report.append("-----------------------------------------------\n");
            }

            reportArea.setText(report.toString());
        }
    }
}