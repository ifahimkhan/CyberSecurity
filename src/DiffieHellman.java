import java.math.BigInteger;
import java.security.SecureRandom;

public class DiffieHellman {
    public static void main(String[] args) {
        SecureRandom rand = new SecureRandom();

        // Public values (p and g)
        BigInteger p = new BigInteger("23"); // Prime number
        BigInteger g = new BigInteger("5");  // Primitive root of p

        System.out.println("Publicly Shared Values:");
        System.out.println("Prime (p): " + p);
        System.out.println("Primitive Root (g): " + g);

        // Private keys (randomly chosen)
        BigInteger a = new BigInteger(10, rand); // Alice's private key
        BigInteger b = new BigInteger(10, rand); // Bob's private key

        System.out.println("\nPrivate Keys (Secret):");
        System.out.println("Alice's Private Key (a): " + a);
        System.out.println("Bob's Private Key (b): " + b);

        // Compute public keys
        BigInteger A = g.modPow(a, p); // A = g^a mod p
        BigInteger B = g.modPow(b, p); // B = g^b mod p

        System.out.println("\nExchanged Public Keys:");
        System.out.println("Alice sends to Bob: " + A);
        System.out.println("Bob sends to Alice: " + B);

        // Compute shared secret key
        BigInteger secretKeyAlice = B.modPow(a, p); // S = B^a mod p
        BigInteger secretKeyBob = A.modPow(b, p);   // S = A^b mod p

        System.out.println("\nComputed Shared Secret Key:");
        System.out.println("Alice's Computed Key: " + secretKeyAlice);
        System.out.println("Bob's Computed Key: " + secretKeyBob);

        // Check if both keys are the same
        if (secretKeyAlice.equals(secretKeyBob)) {
            System.out.println("\nKey Exchange Successful! Both parties have the same key.");
        } else {
            System.out.println("\nError: Keys do not match!");
        }
    }
}
