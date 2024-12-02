import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator  {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Retro Calculator");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Absolute layout for retro styling

        // Colors and Fonts for the retro look
        Color backgroundColor = new Color(33, 33, 33); // Dark gray background
        Color buttonColor = new Color(60, 60, 60);     // Gray button background
        Color textColor = Color.WHITE;                // White text
        Font font = new Font("Courier New", Font.BOLD, 18); // Retro-styled font

        // Create the display area
        JTextField display = new JTextField();
        display.setBounds(20, 20, 250, 50);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setFont(new Font("Courier New", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        frame.add(display);

        // Create buttons
        String[] buttonLabels = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "*",
                "C", "0", "=", "/"
        };

        JButton[] buttons = new JButton[buttonLabels.length];
        int x = 20, y = 100; // Initial position for buttons

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBounds(x, y, 50, 50);
            buttons[i].setBackground(buttonColor);
            buttons[i].setForeground(textColor);
            buttons[i].setFont(font);
            frame.add(buttons[i]);

            // Move to the next column or row
            x += 60;
            if ((i + 1) % 4 == 0) {
                x = 20; // Reset to first column
                y += 60; // Move to the next row
            }
        }

        // Add action listeners for button functionality
        ActionListener listener = new ActionListener() {
            String input = ""; // Store user input
            String operator = "";
            double num1 = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();

                if (command.matches("[0-9]")) { // If a number is clicked
                    input += command;
                    display.setText(input);
                } else if (command.equals("C")) { // Clear button
                    input = "";
                    operator = "";
                    num1 = 0;
                    display.setText("");
                } else if (command.equals("=")) { // Equals button
                    if (!input.isEmpty()) {
                        double num2 = Double.parseDouble(input);
                        double result = 0;

                        switch (operator) {
                            case "+":
                                result = num1 + num2;
                                break;
                            case "-":
                                result = num1 - num2;
                                break;
                            case "*":
                                result = num1 * num2;
                                break;
                            case "/":
                                if (num2 != 0) {
                                    result = num1 / num2;
                                } else {
                                    display.setText("Error");
                                    return;
                                }
                                break;
                        }

                        display.setText(String.valueOf(result));
                        input = ""; // Reset input for the next calculation
                    }
                } else { // Operator button
                    if (!input.isEmpty()) {
                        num1 = Double.parseDouble(input);
                        operator = command;
                        input = ""; // Reset input for the second number
                    }
                }
            }
        };

        // Attach action listeners to buttons
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }

        // Set frame properties
        frame.getContentPane().setBackground(backgroundColor); // Set retro background
        frame.setVisible(true);
    }
}
