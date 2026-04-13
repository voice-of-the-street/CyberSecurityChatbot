package cybersecuritychatbot;

import java.util.Scanner;
import javax.sound.sampled.*;
import java.io.File;

public class CyberSecurityChatbot {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // ASCII ART
        System.out.println("""
  _____            _               
 / ____|          | |              
| |     ___  _ __ | |_ ___  _ __   
| |    / _ \\| '_ \\| __/ _ \\| '__|  
| |___| (_) | | | | || (_) | |     
 \\_____\\___/|_| |_|\\__\\___/|_|     
   CYBER SECURITY BOT 🛡️
        """);

        // Play audio
        playGreeting();

        System.out.println("Welcome to CyberSafe Assistant 🤖");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        if (!isValid(name)) {
            System.out.println("Invalid name. Restart.");
            return;
        }

        startChat(name);
    }

    // 🔊 AUDIO METHOD
    public static void playGreeting() {
        try {
            File file = new File("greeting.wav");
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            Thread.sleep(3000); // wait for audio
        } catch (Exception e) {
            System.out.println("(Audio failed)");
        }
    }

    // 🧠 CHAT SYSTEM
    public static void startChat(String name) {

        System.out.println("\nHello " + name + ", let's learn cybersecurity! 🛡️");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Password Safety");
            System.out.println("2. Phishing Awareness");
            System.out.println("3. Safe Browsing");
            System.out.println("4. Ask a Question");
            System.out.println("5. Exit");

            System.out.print("Option: ");
            String choice = scanner.nextLine();

            if (!isValid(choice)) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case "1":
                    passwordScenario();
                    break;
                case "2":
                    phishingScenario();
                    break;
                case "3":
                    browsingScenario();
                    break;
                case "4":
                    generalChat();
                    break;
                case "5":
                    System.out.println("Stay safe online 👋");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // 🎭 SCENARIOS
    public static void passwordScenario() {
        System.out.println("\nYour password is '12345'. Is it safe?");
        String ans = scanner.nextLine().toLowerCase();

        if (ans.contains("no")) {
            System.out.println("✅ Correct! Weak passwords are dangerous.");
        } else {
            System.out.println("❌ Wrong. Use strong passwords.");
        }
    }

    public static void phishingScenario() {
        System.out.println("\nEmail: 'Click here to fix your bank account'");
        System.out.print("Do you click it? ");
        String ans = scanner.nextLine().toLowerCase();

        if (ans.contains("yes")) {
            System.out.println("⚠️ Phishing attack!");
        } else {
            System.out.println("✅ Good decision!");
        }
    }

    public static void browsingScenario() {
        System.out.println("\nWebsite shows HTTP not HTTPS. Safe?");
        String ans = scanner.nextLine().toLowerCase();

        if (ans.contains("no")) {
            System.out.println("✅ Correct!");
        } else {
            System.out.println("❌ Not safe!");
        }
    }

    // 💬 GENERAL CHAT
    public static void generalChat() {
        System.out.print("\nAsk: ");
        String input = scanner.nextLine().toLowerCase();

        if (!isValid(input)) {
            System.out.println("Invalid input.");
            return;
        }

        System.out.println("Bot: " + getResponse(input));
    }

    public static String getResponse(String input) {
        if (input.contains("password"))
            return "Use strong passwords with symbols.";

        if (input.contains("phishing"))
            return "Phishing steals your info.";

        if (input.contains("safe"))
            return "Always check HTTPS.";

        if (input.contains("scam"))
            return "Avoid suspicious links.";

        return "Ask about cybersecurity topics.";
    }

    // ✅ VALIDATION
    public static boolean isValid(String input) {
        return input != null && !input.trim().isEmpty();
    }
}