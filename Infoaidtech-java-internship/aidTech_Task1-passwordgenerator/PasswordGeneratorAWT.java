import java.awt.*;
import java.awt.event.*;
import java.security.SecureRandom;

public class PasswordGeneratorAWT extends Frame implements ActionListener {
    private Label lengthLabel, characterSetLabel, resultLabel;
    private TextField lengthTextField, characterSetTextField, resultTextField;
    private Button generateButton;

    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    public PasswordGeneratorAWT() {
        setTitle("Password Generator");
        setLayout(new FlowLayout());

        lengthLabel = new Label("Password Length:");
        add(lengthLabel);

        lengthTextField = new TextField(10);
        add(lengthTextField);

        characterSetLabel = new Label("Character Set (1 for Uppercase, 2 for Lowercase, 3 for Numbers, 4 for Special Characters):");
        add(characterSetLabel);

        characterSetTextField = new TextField(10);
        add(characterSetTextField);

        generateButton = new Button("Generate Password");
        add(generateButton);

        generateButton.addActionListener(this);

        resultLabel = new Label("Generated Password:");
        add(resultLabel);

        resultTextField = new TextField(20);
        add(resultTextField);

        setSize(300, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            int passwordLength = Integer.parseInt(lengthTextField.getText());
            String selectedCharacterSet = characterSetTextField.getText();
            
            String characterSet = "";
            if (selectedCharacterSet.contains("1")) {
                characterSet += UPPERCASE_LETTERS;
            }
            if (selectedCharacterSet.contains("2")) {
                characterSet += LOWERCASE_LETTERS;
            }
            if (selectedCharacterSet.contains("3")) {
                characterSet += NUMBERS;
            }
            if (selectedCharacterSet.contains("4")) {
                characterSet += SPECIAL_CHARACTERS;
            }

            String generatedPassword = generatePassword(passwordLength, characterSet);
            resultTextField.setText(generatedPassword);
        }
    }

    public static String generatePassword(int length, String characterSet) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            char randomChar = characterSet.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public static void main(String[] args) {
        new PasswordGeneratorAWT();
    }
}
