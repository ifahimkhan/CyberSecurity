import java.util.BitSet;

public class SimpleDES {
    
    private static String plaintext = "ABCDEFGH";
    
    private static String key = "MYSECRET";

    public static void main(String[] args) {
        System.out.println("Plaintext: " + plaintext);

        BitSet plaintextBits = stringToBitSet(plaintext);
        BitSet keyBits = stringToBitSet(key);

        BitSet permutedText = initialPermutation(plaintextBits);
        
        BitSet left = permutedText.get(0, 32);
        BitSet right = permutedText.get(32, 64);

        BitSet[] roundKeys = generateRoundKeys(keyBits);

        for (int round = 0; round < 2; round++) {
            BitSet expandedRight = expansionPermutation(right);
            BitSet xorResult = xor(expandedRight, roundKeys[round]);
            BitSet substituted = sBoxSubstitution(xorResult);
            BitSet permuted = pBoxPermutation(substituted);
            BitSet newRight = xor(left, permuted);

            left = right;
            right = newRight;
        }

        BitSet finalText = new BitSet(64);
        for (int i = 0; i < 32; i++) {
            finalText.set(i, left.get(i));
            finalText.set(i + 32, right.get(i));
        }

        BitSet encryptedText = finalPermutation(finalText);

        System.out.println("Encrypted Text (Binary): " + bitSetToBinary(encryptedText));
    }

    private static BitSet initialPermutation(BitSet input) {
        return input; // Simplified IP
    }

    private static BitSet expansionPermutation(BitSet input) {
        BitSet expanded = new BitSet(48);
        for (int i = 0; i < 48; i++) {
            expanded.set(i, input.get(i % 32)); // Simple expansion logic
        }
        return expanded;
    }

    private static BitSet xor(BitSet a, BitSet b) {
        BitSet result = (BitSet) a.clone();
        result.xor(b);
        return result;
    }

    private static BitSet sBoxSubstitution(BitSet input) {
        BitSet output = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            output.set(i, input.get(i)); // Dummy S-Box logic
        }
        return output;
    }

    private static BitSet pBoxPermutation(BitSet input) {
        BitSet permuted = new BitSet(32);
        for (int i = 0; i < 32; i++) {
            permuted.set(i, input.get(i)); // Simplified permutation
        }
        return permuted;
    }

    private static BitSet finalPermutation(BitSet input) {
        return input; // Simplified FP
    }

    private static BitSet[] generateRoundKeys(BitSet keyBits) {
        BitSet[] roundKeys = new BitSet[2];
        roundKeys[0] = (BitSet) keyBits.clone();
        roundKeys[1] = (BitSet) keyBits.clone();
        return roundKeys;
    }

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

    private static String bitSetToBinary(BitSet bitSet) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            sb.append(bitSet.get(i) ? "1" : "0");
        }
        return sb.toString();
    }
}
