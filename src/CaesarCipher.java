import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext (A-Z only): ");
        String plaintext = scanner.nextLine().toUpperCase();

        System.out.print("Enter the shift key (0-25): ");
        int key = scanner.nextInt();

        StringBuilder ciphertext = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char encryptedChar = (char) ((ch - 'A' + key) % 26 + 'A');
                ciphertext.append(encryptedChar);
            } else {
                ciphertext.append(ch);
            }
        }

        System.out.println("Encrypted Text: " + ciphertext);

        scanner.close();
    }
}

