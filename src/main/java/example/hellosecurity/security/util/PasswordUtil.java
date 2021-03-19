package example.hellosecurity.security.util;

import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;

public class PasswordUtil {
    /**
     * 校验密码是否一致
     *
     * @param password: 前端传过来的密码
     * @param hashedPassword：数据库中储存加密过后的密码
     * @param salt：盐值
     * @return
     */
    public static boolean isValidPassword(String password, String hashedPassword, String salt) {
        return hashedPassword.equalsIgnoreCase(encodePassword(password, salt));
    }

    /**
     * 通过SHA1对密码进行编码
     *
     * @param password：密码
     * @param salt：盐值
     * @return
     */
    public static String encodePassword(String password, String salt) {
        String encodedPassword;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            if (salt != null) {
                digest.reset();
                digest.update(salt.getBytes());
            }
            byte[] hashed = digest.digest(password.getBytes());
            encodedPassword = new String(Hex.encode(hashed));
        } catch (Exception e) {
            return null;
        }
        return encodedPassword;
    }

}
