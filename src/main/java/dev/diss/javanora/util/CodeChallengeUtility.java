package dev.diss.javanora.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CodeChallengeUtility {
    public static String generate(final String codeVerifier) {
        byte[] digest = null;
        try {
            byte[] bytes = codeVerifier.getBytes("US-ASCII");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes,0, bytes.length);
            digest = messageDigest.digest();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException exception) {
            System.out.println(exception.getMessage());
        }

        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }
}
