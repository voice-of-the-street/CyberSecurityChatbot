package cybersecuritychatbot;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class CyberSecurityChatbot extends JFrame {

    // GUI COMPONENTS
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    // MEMORY
    private String userName = "";
    private String lastTopic = "";

    // RANDOM RESPONSES
    private Random random = new Random();

    // KEYWORD MEMORY
    private HashMap<String, String[]> responses = new HashMap<>();

    public CyberSecurityChatbot() {

        // WINDOW SETTINGS
        setTitle("CyberSafe Assistant 🛡️");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // COLORS
        Color background = new Color(20, 20, 20);
        Color panelColor = new Color(35, 35, 35);
        Color green = new Color(0, 255, 150);

        // MAIN PANEL
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(background);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // TITLE
        JLabel title = new JLabel("CYBER SECURITY CHATBOT 🛡️");
        title.setForeground(green);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // CHAT AREA
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setBackground(panelColor);
        chatArea.setForeground(Color.WHITE);
        chatArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBackground(background);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));
        inputField.setBackground(Color.WHITE);

        sendButton = new JButton("Send");
        sendButton.setBackground(green);
        sendButton.setFocusPainted(false);
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // ADD COMPONENTS
        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        add(panel);

        // LOAD RESPONSES
        loadResponses();

        // GREETING
        playGreeting();

        appendBot("Welcome to CyberSafe Assistant 🤖");
        appendBot("What is your name?");

        // BUTTON ACTION
        sendButton.addActionListener(e -> processInput());

        // ENTER KEY
        inputField.addActionListener(e -> processInput());

        setVisible(true);
    }

    // PROCESS USER INPUT
    private void processInput() {

        try {

            String input = inputField.getText().trim();

            // ERROR HANDLING
            if (input.isEmpty()) {
                appendBot("Please type something.");
                return;
            }

            appendUser(input);

            inputField.setText("");

            // FIRST INPUT = NAME
            if (userName.isEmpty()) {

                if (!input.matches("[a-zA-Z ]+")) {
                    appendBot("Please enter a valid name.");
                    return;
                }

                userName = input;

                appendBot("Nice to meet you, " + userName + " 😊");
                appendBot("Ask me anything about cybersecurity.");

                return;
            }

            // EXIT
            if (input.equalsIgnoreCase("exit")
                    || input.equalsIgnoreCase("bye")) {

                appendBot("Goodbye " + userName + "! Stay safe online 👋");
                System.exit(0);
            }

            // CONVERSATION FLOW
            handleConversation(input.toLowerCase());

        } catch (Exception e) {

            appendBot("Something went wrong. Please try again.");
        }
    }

    // CONVERSATION ENGINE
    private void handleConversation(String input) {

        // SENTIMENT DETECTION
        if (input.contains("sad")
                || input.contains("worried")
                || input.contains("scared")) {

            appendBot("I'm sorry you're feeling that way 😟");
            appendBot("Cyber threats can be stressful, but learning safety helps.");
            return;
        }

        if (input.contains("happy")
                || input.contains("good")
                || input.contains("great")) {

            appendBot("That's great to hear 😄");
        }

        // MEMORY & RECALL
        if (input.contains("remember")) {

            if (!lastTopic.isEmpty()) {
                appendBot("Earlier we talked about " + lastTopic + ".");
            } else {
                appendBot("We haven't discussed a topic yet.");
            }

            return;
        }

        // KEYWORD RECOGNITION
        for (String keyword : responses.keySet()) {

            if (input.contains(keyword)) {

                lastTopic = keyword;

                String[] possibleResponses = responses.get(keyword);

                int index = random.nextInt(possibleResponses.length);

                appendBot(possibleResponses[index]);

                // FOLLOW-UP QUESTIONS
                followUp(keyword);

                return;
            }
        }

        // EDGE CASE RESPONSE
        appendBot("I don't fully understand.");
        appendBot("Try asking about passwords, phishing, malware, scams, VPN or safe browsing.");
    }

    // RANDOM FOLLOW-UP FLOW
    private void followUp(String topic) {

        switch (topic) {

            case "password":
                appendBot("Do you use unique passwords for every account?");
                break;

            case "phishing":
                appendBot("Have you ever received a suspicious email?");
                break;

            case "vpn":
                appendBot("VPNs are useful on public WiFi.");
                break;

            case "malware":
                appendBot("Keeping antivirus updated helps prevent malware.");
                break;
        }
    }

    // LOAD KEYWORDS + RANDOM RESPONSES
    private void loadResponses() {

        responses.put("password", new String[]{
            "Use strong passwords with symbols and numbers.",
            "Avoid using birthdays as passwords.",
            "Enable two-factor authentication for extra security."
        });

        responses.put("phishing", new String[]{
            "Phishing attacks trick users into giving personal information.",
            "Never click suspicious email links.",
            "Always verify the sender before responding."
        });

        responses.put("malware", new String[]{
            "Malware is harmful software that damages systems.",
            "Keep your antivirus software updated.",
            "Do not download files from untrusted websites."
        });

        responses.put("vpn", new String[]{
            "VPNs help protect your online privacy.",
            "A VPN encrypts your internet connection.",
            "VPNs are useful when using public WiFi."
        });

        responses.put("scam", new String[]{
            "Scammers often create urgency to trick victims.",
            "Never share OTPs or passwords.",
            "Be careful of fake giveaways and offers."
        });

        responses.put("safe browsing", new String[]{
            "Always check for HTTPS websites.",
            "Avoid downloading unknown files.",
            "Keep your browser updated regularly."
        });
    }

    // BOT MESSAGE
    private void appendBot(String text) {

        chatArea.append("BOT 🤖: " + text + "\n\n");
    }

    // USER MESSAGE
    private void appendUser(String text) {

        chatArea.append(userName + " 👤: " + text + "\n\n");
    }

    // AUDIO GREETING
    public void playGreeting() {

        try {

            File file = new File("greeting.wav");

            if (!file.exists()) {
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();

            clip.open(audio);

            clip.start();

        } catch (Exception e) {

            appendBot("(Audio greeting unavailable)");
        }
    }

    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CyberSecurityChatbot());
    }
}