import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * A simple implementation of the Diffie-Hellman key exchange algorithm.
 * This program demonstrates how two parties (Alice and Bob) can securely
 * exchange a shared secret key over an insecure channel.
 */
public class DiffieHellman {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();

        BigInteger prime = new BigInteger("23");       // A prime number (p)
        BigInteger primitiveRoot = new BigInteger("5"); // A primitive root of p (g)

        System.out.println("Publicly Shared Values:");
        System.out.println("Prime (p): " + prime);
        System.out.println("Primitive Root (g): " + primitiveRoot);

        BigInteger alicePrivateKey = new BigInteger(10, random); // Alice's private key
        BigInteger bobPrivateKey = new BigInteger(10, random);   // Bob's private key

        System.out.println("\nPrivate Keys (Secret):");
        System.out.println("Alice's Private Key (a): " + alicePrivateKey);
        System.out.println("Bob's Private Key (b): " + bobPrivateKey);

        BigInteger alicePublicKey = primitiveRoot.modPow(alicePrivateKey, prime); // A = g^a mod p
        BigInteger bobPublicKey = primitiveRoot.modPow(bobPrivateKey, prime);     // B = g^b mod p

        System.out.println("\nExchanged Public Keys:");
        System.out.println("Alice sends to Bob: " + alicePublicKey);
        System.out.println("Bob sends to Alice: " + bobPublicKey);

        BigInteger aliceSharedSecret = bobPublicKey.modPow(alicePrivateKey, prime); // S = B^a mod p
        BigInteger bobSharedSecret = alicePublicKey.modPow(bobPrivateKey, prime);   // S = A^b mod p

        System.out.println("\nComputed Shared Secret Key:");
        System.out.println("Alice's Computed Key: " + aliceSharedSecret);
        System.out.println("Bob's Computed Key: " + bobSharedSecret);

        if (aliceSharedSecret.equals(bobSharedSecret)) {
            System.out.println("\nKey Exchange Successful! Both parties have the same key.");
        } else {
            System.out.println("\nError: Keys do not match!");
        }
    }
}