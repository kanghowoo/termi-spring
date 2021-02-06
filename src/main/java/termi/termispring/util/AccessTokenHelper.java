package termi.termispring.util;


import java.beans.Encoder;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class AccessTokenHelper {
    private static final String SALT = "!@termisalt";
    public static String createToken(String email) throws UnsupportedEncodingException {

        String target = SALT + email;
        byte[] targetBytes = target.getBytes();

        Base64.Encoder encoder = Base64.getEncoder();

        byte[] encodedBytes = encoder.encode(targetBytes);

        String token = encoder.encodeToString(encodedBytes);

        return token;
    }
}
