package termi.termispring.util;


import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AccessTokenHelper {

    public static String createToken() {

        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();
        String token = base64Encoder.encodeToString(bytes);
        return token;
    }
}
