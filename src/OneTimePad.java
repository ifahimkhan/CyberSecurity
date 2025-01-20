import java.util.Random;
import java.util.Scanner;

public class OneTimePad {

    private static String generateKey(int length) {
        Random random = new Random();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < length; i++) {
            key.append(random.nextInt(2));
        }
        return key.toString();
    }

    private static String xorStrings(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str1.length(); i++) {
            // XOR operation: 1 ^ 1 = 0, 0 ^ 0 = 0, 1 ^ 0 = 1, 0 ^ 1 = 1
            result.append(str1.charAt(i) ^ str2.charAt(i));
        }
        return result.toString();
    }

    private static String stringToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            // Convert each character to an 8-bit binary string
            binary.append(String.format("%08d", Integer.parseInt(Integer.toBinaryString(c))));
        }
        return binary.toString();
    }

    private static String binaryToString(String binary) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            int charCode = Integer.parseInt(byteString, 2);
            text.append((char) charCode);
        }
        return text.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        String binaryPlaintext = stringToBinary(plaintext);
        System.out.println("Binary representation of plaintext: " + binaryPlaintext);

        String key = generateKey(binaryPlaintext.length());
        System.out.println("Generated Key: " + key);

        String ciphertext = xorStrings(binaryPlaintext, key);
        System.out.println("Ciphertext (Binary): " + ciphertext);

        String decryptedBinary = xorStrings(ciphertext, key);
        String decryptedText = binaryToString(decryptedBinary);
        System.out.println("Decrypted Binary: " + decryptedBinary);
        System.out.println("Decrypted Text: " + decryptedText);


        scanner.close();
    }
}
