import java.util.Scanner;

public class HillCipherDecrypt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Key matrix (3x3)
        int[][] keyMatrix = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
        };

        // Inverse key matrix (3x3)
        int[][] inverseKeyMatrix = {
            {8, 5, 10},
            {21, 8, 21},
            {21, 12, 8}
        }; // Precomputed inverse modulo 26

        System.out.print("Choose an option (1: Encrypt, 2: Decrypt): ");
        int option = scanner.nextInt();

        if (option == 1) {
            // Encryption
            System.out.print("Enter a 3-letter plaintext (A-Z only): ");
            String plaintext = scanner.next().toUpperCase();

            if (plaintext.length() != 3) {
                System.out.println("Plaintext must be exactly 3 letters.");
                return;
            }

            // Convert plaintext to vector
            int[] messageVector = new int[3];
            for (int i = 0; i < 3; i++) {
                messageVector[i] = plaintext.charAt(i) - 'A';
            }

            // Encipher the vector
            int[] cipherVector = new int[3];
            for (int i = 0; i < 3; i++) {
                cipherVector[i] = 0;
                for (int j = 0; j < 3; j++) {
                    cipherVector[i] += keyMatrix[i][j] * messageVector[j];
                }
                cipherVector[i] %= 26; // Modulo 26 for letters A-Z
            }

            // Convert cipher vector to ciphertext
            StringBuilder ciphertext = new StringBuilder();
            for (int value : cipherVector) {
                ciphertext.append((char) (value + 'A'));
            }

            // Output the ciphertext
            System.out.println("Ciphertext: " + ciphertext);

        } else if (option == 2) {
            // Decryption
            System.out.print("Enter a 3-letter ciphertext (A-Z only): ");
            String ciphertext = scanner.next().toUpperCase();

            if (ciphertext.length() != 3) {
                System.out.println("Ciphertext must be exactly 3 letters.");
                return;
            }

            // Convert ciphertext to vector
            int[] cipherVector = new int[3];
            for (int i = 0; i < 3; i++) {
                cipherVector[i] = ciphertext.charAt(i) - 'A';
            }

            // Decipher the vector
            int[] messageVector = new int[3];
            for (int i = 0; i < 3; i++) {
                messageVector[i] = 0;
                for (int j = 0; j < 3; j++) {
                    messageVector[i] += inverseKeyMatrix[i][j] * cipherVector[j];
                }
                messageVector[i] = (messageVector[i] % 26 + 26) % 26; // Ensure positive modulo 26
            }

            // Convert message vector to plaintext
            StringBuilder plaintext = new StringBuilder();
            for (int value : messageVector) {
                plaintext.append((char) (value + 'A'));
            }

            // Output the plaintext
            System.out.println("Plaintext: " + plaintext);

        } else {
            System.out.println("Invalid option.");
        }

        scanner.close();
    }
}
