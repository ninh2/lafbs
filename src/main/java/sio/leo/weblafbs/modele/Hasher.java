package sio.leo.weblafbs.modele;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 *
 * @author ChatGPT notre ami commun
 */
public class Hasher {

   //constructeur par défaut
    public Hasher(){
        
    }
    public static String hashPassword(String password) throws Exception {
        
        // Générer un sel aléatoire (32 octets)
        byte[] salt = generateSalt();

        // Utilisation de l'algorithme PBKDF2 avec SHA-256
        int iterations = 10000;  // Nombre d'itérations
        int keyLength = 256;     // Longueur de la clé résultante en bits (32 octets)

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 
        keyLength);
        SecretKeyFactory factory = 
                             SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        // Encodage du hachage et du sel en Base64 pour les stocker
        String hashBase64 = Base64.getEncoder().encodeToString(hash);
        String saltBase64 = Base64.getEncoder().encodeToString(salt);

        // Retourner la chaîne contenant le sel et le hachage
        return saltBase64 + ":" + hashBase64;  // Format: sel:hash
    }


    // génération d'un sel aléatoire
    private static byte[] generateSalt() {
        byte[] salt = new byte[32];  // 32 octets pour le sel
        new SecureRandom().nextBytes(salt);
        return salt;
    }
    
    public static boolean verifyPassword(String passwordSaisi, String storedPassword) throws Exception {
    // Extraire le sel et le hachage depuis le format "sel:hash"
    String[] parts = storedPassword.split(":");
    String saltBase64 = parts[0];  // Sel en Base64
    String storedHashBase64 = parts[1];  // Hachage en Base64

    // Décoder le sel et le hachage à partir du Base64
    byte[] salt = Base64.getDecoder().decode(saltBase64);
    byte[] storedHash = Base64.getDecoder().decode(storedHashBase64);

    // Appliquer la même fonction de hachage avec le sel et les mêmes paramètres (itérations, longueur de la clé)
    int iterations = 10000;  // Nombre d'itérations
    int keyLength = 256;     // Longueur de la clé en bits

    PBEKeySpec spec = new PBEKeySpec(passwordSaisi.toCharArray(), salt, iterations, 
                                                    keyLength);
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

    // Calculer le hachage du mot de passe saisi
    byte[] hash = factory.generateSecret(spec).getEncoded();

    // Comparer le hachage calculé avec le hachage stocké
    return MessageDigest.isEqual(hash, storedHash);
}

}