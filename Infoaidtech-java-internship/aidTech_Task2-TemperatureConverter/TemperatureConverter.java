import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JComboBox<String> fromScale;
    private JComboBox<String> toScale;

    public TemperatureConverter() {
        // Set up the frame
        setTitle("Temperature Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JPanel panel = new JPanel(new GridLayout(5, 2));
        inputField = new JTextField();
        resultLabel = new JLabel("RESULT: ");
        fromScale = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        toScale = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        JButton convertButton = new JButton("Convert");
        JButton clearButton = new JButton("Clear");

        // Set heading labels with custom font and color
        JLabel enterTempLabel = new JLabel("Enter Temperature:");
        enterTempLabel.setForeground(Color.red);
        enterTempLabel.setFont(new Font("Arial", Font.BOLD, 19));

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.BLUE);
        fromLabel.setFont(new Font("Arial", Font.BOLD, 19));

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.BLUE);
        toLabel.setFont(new Font("Arial", Font.BOLD, 19));

        // Add components to the panel
        panel.add(enterTempLabel);
        panel.add(inputField);
        panel.add(fromLabel);
        panel.add(fromScale);
        panel.add(toLabel);
        panel.add(toScale);
        panel.add(convertButton);
        panel.add(clearButton);
        panel.add(resultLabel);

        // Set result label color
        resultLabel.setForeground(Color.red);


        // Add action listeners
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double temperature = Double.parseDouble(inputField.getText());
                    String from = fromScale.getSelectedItem().toString();
                    String to = toScale.getSelectedItem().toString();
                    double result = convertTemperature(temperature, from, to);
                    resultLabel.setText("RESULT: " + result);
                } catch (  IllegalArgumentException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid temperature.");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                resultLabel.setText("RESULT: ");
            }
        });

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }

    private double convertTemperature(double temperature, String from, String to) {
        if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            return (temperature * 9 / 5) + 32;
        } else if (from.equals("Celsius") && to.equals("Kelvin")) {
            return temperature + 273.15;
        } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            return (temperature - 32) * 5 / 9;
        } else if (from.equals("Fahrenheit") && to.equals("Kelvin")) {
            return (temperature - 32) * 5 / 9 + 273.15;
        } else if (from.equals("Kelvin") && to.equals("Celsius")) {
            return temperature - 273.15;
        } else if (from.equals("Kelvin") && to.equals("Fahrenheit")) {
            return (temperature - 273.15) * 9 / 5 + 32;
        } else {
            return temperature;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConverter());
    }
}
