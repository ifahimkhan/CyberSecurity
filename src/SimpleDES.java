import java.util.BitSet;

public class SimpleDES {
    
    // Example 64-bit plaintext (8 bytes)
    private static String plaintext = "ABCDEFGH"; 
    
    // Example 64-bit key (8 bytes)
    private static String key = "MYSECRET";  

    public static void main(String[] args) {
        System.out.println("Plaintext: " + plaintext);

        // Convert text and key to binary
        BitSet plaintextBits = stringToBitSet(plaintext);
        BitSet keyBits = stringToBitSet(key);

        // Initial Permutation (IP)
        BitSet permutedText = initialPermutation(plaintextBits);
        
        // Split into left and right halves
        BitSet left = permutedText.get(0, 32);
        BitSet right = permutedText.get(32, 64);

        // Generate round keys (For simplicity, we use a basic transformation)
        BitSet[] roundKeys = generateRoundKeys(keyBits);

        // Perform two rounds of DES
        for (int round = 0; round < 2; round++) {
            BitSet expandedRight = expansionPermutation(right);
            BitSet xorResult = xor(expandedRight, roundKeys[round]);
            BitSet substituted = sBoxSubstitution(xorResult);
            BitSet permuted = pBoxPermutation(substituted);
            BitSet newRight = xor(left, permuted);

            // Swap halves
            left = right;
            right = newRight;
        }

        // Combine halves
        BitSet finalText = new BitSet(64);
        for (int i = 0; i < 32; i++) {
            finalText.set(i, left.get(i));
            finalText.set(i + 32, right.get(i));
        }

        // Final Permutation (FP)
        BitSet encryptedText = finalPermutation(finalText);

        System.out.println("Encrypted Text (Binary): " + bitSetToBinary(encryptedText));
    }

    // Initial Permutation (IP) (For simplicity, returning same data)
    private static BitSet initialPermutation(BitSet input) {
        return input; // Simplified IP
    }

    // Expansion Function (Expands 32-bit to 48-bit)
    private static BitSet expansionPermutation(BitSet input) {
        BitSet expanded = new BitSet(48);
        for (int i = 0; i < 48; i++) {
            expanded.set(i, input.get(i % 32)); // Simple expansion logic
        }
        return expanded;
    }

    // XOR Function
    private static BitSet xor(BitSet a, BitSet b) {
        BitSet result = (BitSet) a.clone();
        result.xor(b);
        return result;
    }

    // S-Box Substitution (Simplified version)
    private static BitSet sBoxSubstitution(BitSet input) {
        BitSet output = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            output.set(i, input.get(i)); // Dummy S-Box logic
        }
        return output;
    }

    // P-Box Permutation (Simplified)
    private static BitSet pBoxPermutation(BitSet input) {
        BitSet permuted = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            permuted.set(i, input.get(i)); // Simplified permutation
        }
        return permuted;
    }

    // Final Permutation (FP)
    private static BitSet finalPermutation(BitSet input) {
        return input; // Simplified FP
    }

    // Generate Two Round Keys (For simplicity, modify key)
    private static BitSet[] generateRoundKeys(BitSet keyBits) {
        BitSet[] roundKeys = new BitSet[2];
        roundKeys[0] = (BitSet) keyBits.clone();
        roundKeys[1] = (BitSet) keyBits.clone();
        return roundKeys;
    }

    // Convert String to BitSet (Binary)
    private static BitSet stringToBitSet(String input) {
        BitSet bitSet = new BitSet(input.length() * 8);
        byte[] bytes = input.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                bitSet.set(i * 8 + j, (bytes[i] & (1 << (7 - j))) != 0);
            }
        }
        return bitSet;
    }

    // Convert BitSet to Binary String
    private static String bitSetToBinary(BitSet bitSet) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            sb.append(bitSet.get(i) ? "1" : "0");
        }
        return sb.toString();
    }
}
