import java.security.*;
import java.util.Base64;

public class DigitalSignatureExample {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String message = "Hello World";

        String signature = signMessage(message, privateKey);
        System.out.println("Digital Signature: " + signature);

        boolean isVerified = verifySignature(message, signature, publicKey);
        System.out.println("Signature Verification: " + isVerified);
    }

    public static String signMessage(String message, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(message.getBytes());

        byte[] signedBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signedBytes);
    }

    public static boolean verifySignature(String message, String signature, PublicKey publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(publicKey);
        sig.update(message.getBytes());

        byte[] signedBytes = Base64.getDecoder().decode(signature);
        return sig.verify(signedBytes);
    }
}
