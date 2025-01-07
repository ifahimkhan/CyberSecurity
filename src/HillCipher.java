import java.util.Scanner;

public class HillCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] keyMatrix = {
            {6, 24, 1},
            {13, 16, 10},
            {20, 17, 15}
        };

        System.out.print("Enter a 3-letter plaintext (A-Z only): ");
        String plaintext = scanner.next().toUpperCase();

        if (plaintext.length() != 3) {
            System.out.println("Plaintext must be exactly 3 letters.");
            return;
        }

        int[] messageVector = new int[3];
        for (int i = 0; i < 3; i++) {
            messageVector[i] = plaintext.charAt(i) - 'A';
        }

        int[] cipherVector = new int[3];
        for (int i = 0; i < 3; i++) {
            cipherVector[i] = 0;
            for (int j = 0; j < 3; j++) {
                cipherVector[i] += keyMatrix[i][j] * messageVector[j];
            }
            cipherVector[i] %= 26;
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int value : cipherVector) {
            ciphertext.append((char) (value + 'A'));
        }

        System.out.println("Ciphertext: " + ciphertext);

        scanner.close();
    }
}
