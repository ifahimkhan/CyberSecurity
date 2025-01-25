import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSAAlgorithm {

    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024;
    private SecureRandom random = new SecureRandom();

    // Constructor to generate keys
    public RSAAlgorithm() {
        // Step 1: Generate two large prime numbers, p and q
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);

        // Step 2: Compute n = p * q
        n = p.multiply(q);

        // Step 3: Compute Euler's totient function phi(n) = (p-1) * (q-1)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Step 4: Select a public exponent e (1 < e < phi(n)), coprime to phi(n)
        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        // Step 5: Compute the private key d such that (e * d) mod phi(n) = 1
        d = e.modInverse(phi);
    }

    // Encrypt the plaintext message
    public BigInteger encrypt(BigInteger plaintext) {
        return plaintext.modPow(e, n);
    }

    // Decrypt the ciphertext message
    public BigInteger decrypt(BigInteger ciphertext) {
        return ciphertext.modPow(d, n);
    }

    public static void main(String[] args) {
        RSAAlgorithm rsa = new RSAAlgorithm();

        Scanner scanner = new Scanner(System.in);

        // Display public and private keys
        System.out.println("Public Key (e, n): " + rsa.e + ", " + rsa.n);
        System.out.println("Private Key (d, n): " + rsa.d + ", " + rsa.n);

        // Input plaintext message
        System.out.print("Enter a message to encrypt (numeric): ");
        BigInteger plaintext = new BigInteger(scanner.nextLine());

        // Encrypt the plaintext
        BigInteger ciphertext = rsa.encrypt(plaintext);
        System.out.println("Encrypted Message: " + ciphertext);

        // Decrypt the ciphertext
        BigInteger decryptedMessage = rsa.decrypt(ciphertext);
        System.out.println("Decrypted Message: " + decryptedMessage);

        scanner.close();
    }
}
