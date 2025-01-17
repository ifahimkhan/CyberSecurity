import java.util.*;

public class ColumnarTranspositionCipher {

    public static String encrypt(String plaintext, String key) {
        plaintext =plaintext.toUpperCase();
        key = key.toUpperCase();

        int keyLength = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / keyLength);
        char[][] grid = new char[rows][keyLength];

        // Fill the grid row by row
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (index < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(index++);
                } else {
                    grid[i][j] = '_';
                }
            }
        }

        Integer[] columnOrder = getColumnOrder(key);

        // Read columns in the specified order
        StringBuilder ciphertext = new StringBuilder();
        for (int col : columnOrder) {
            for (int row = 0; row < rows; row++) {
                ciphertext.append(grid[row][col]);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        key = key.toUpperCase();

        int keyLength = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / keyLength);
        char[][] grid = new char[rows][keyLength];

        // Determine column order based on key
        Integer[] columnOrder = getColumnOrder(key);

        int index = 0;
        for (int col : columnOrder) {
            for (int row = 0; row < rows; row++) {
                grid[row][col] = ciphertext.charAt(index++);
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyLength; j++) {
                plaintext.append(grid[i][j]);
            }
        }

        return plaintext.toString().replace("_", "");
    }

    private static Integer[] getColumnOrder(String key) {
        Character[] keyChars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyChars[i] = key.charAt(i);
        }

        // Create a map of the original positions
        Integer[] columnOrder = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            columnOrder[i] = i;
        }

        // Sort the column order based on the key characters
        Arrays.sort(columnOrder, Comparator.comparingInt(o -> keyChars[o]));

        return columnOrder;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
