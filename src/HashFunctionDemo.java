import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HashFunctionDemo {

    // Method to generate hash using the specified algorithm
    public static String generateHash(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error: Unsupported Hash Algorithm";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input text: ");
        String inputText = scanner.nextLine();

        // Generating hash values for different algorithms
        System.out.println("\nGenerated Hashes:");
        System.out.println("MD5    : " + generateHash(inputText, "MD5"));
        System.out.println("SHA-1  : " + generateHash(inputText, "SHA-1"));
        System.out.println("SHA-256: " + generateHash(inputText, "SHA-256"));
        System.out.println("SHA-512: " + generateHash(inputText, "SHA-512"));

        scanner.close();
    }
}
