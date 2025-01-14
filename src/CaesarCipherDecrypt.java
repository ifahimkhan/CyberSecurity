import java.util.Scanner;

public class CaesarCipherDecrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the ciphertext
        System.out.print("Enter the ciphertext (A-Z only): ");
        String ciphertext = scanner.nextLine().toUpperCase();

        // Input the shift key
        System.out.print("Enter the shift key (0-25): ");
        int key = scanner.nextInt();

        // Decrypt the ciphertext
        StringBuilder plaintext = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                // Apply the Caesar Cipher decryption formula
                char decryptedChar = (char) ((ch - 'A' - key + 26) % 26 + 'A');
                plaintext.append(decryptedChar);
            } else {
                // Non-alphabetic characters remain unchanged
                plaintext.append(ch);
            }
        }

        // Output the plaintext
        System.out.println("Decrypted Text: " + plaintext);

        scanner.close();
    }
}
