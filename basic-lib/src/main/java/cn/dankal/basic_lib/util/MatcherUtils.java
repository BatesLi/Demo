package cn.dankal.basic_lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description: 正则匹配工具类
 *
 * @author vane
 * @since 2018/3/13
 */

public class MatcherUtils {

    final static Pattern MOBILE_PATTERN = Pattern.compile("^[1][3-8][0-9]{9}$");
    final static Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9]{6,20}$");
    final static Pattern REGEX_ID_CARD18 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$");

    /**
     * 验证手机号码
     *
     * @param phone 手机号
     */
    public static boolean checkPhone(CharSequence phone) {
        Matcher phoneMatcher = MOBILE_PATTERN.matcher(phone);
        return phoneMatcher.matches();
    }

    /**
     * 验证密码
     *
     * @param password 密码
     */
    public static boolean checkPassword(String password) {
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        return passwordMatcher.matches();
    }

    /**
     * 验证身份证
     *
     * @param idNum 身份证号码
     */
    public static boolean checkIdCardNum(String idNum) {
        Matcher idNumMatcher = REGEX_ID_CARD18.matcher(idNum);
        return idNumMatcher.matches();
    }


}
