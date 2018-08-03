package com.xiong.common.lib.utils;

import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SlinUtil {


    public static boolean isEmail(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }


    public static boolean isMobileNO(String mobiles) {
        if (mobiles.length() < 11)
            return false;
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[0-9])|(14[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static String getVerificationCode(TextView btn) {
        String code = getRandomString(4);
        btn.setText(code);
        return code;
    }

    public static String getRandomString(int length) { // length表示生成字符串的长度
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static String mobileFilter(String mobile) {
        try {
            String str = mobile.substring(0, 3) + " **** "
                    + mobile.substring(mobile.length() - 4, mobile.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    public static String idFilter(String id) {
        if (StrUtils.isEmpty(id))
            return "";
        try {
            String str = id.substring(0, 4) + " **** **** "
                    + id.substring(id.length() - 4, id.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }


    public static String cardNoFilter(String cardNo) {
        try {
            String str = cardNo.substring(0, 4) + " **** **** "
                    + cardNo.substring(cardNo.length() - 4, cardNo.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }


    public static String cardNoFilter2(String cardNo) {
        try {
            String str = "**** "
                    + cardNo.substring(cardNo.length() - 4, cardNo.length());
            return str;
        } catch (Exception e) {
            return "";
        }
    }




    public static boolean isPasswordAvailable(String password) {
        String str2 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return password.matches(str2);
    }


    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }


    public static String fmtMicrometer(String text) {
        if ("0".equals(text) || "".equals(text) || text == null) {
            return "0.00";
        }

        int strIndexOf = text.lastIndexOf(".");
        if (strIndexOf > 0) {
            text = text + "00";
            text = text.substring(0, strIndexOf + 3);
        } else {
            text += ".00";
        }

        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }


    public static String fmtMicrometerNoPoint(String text) {
        if ("0".equals(text) || "".equals(text) || text == null) {
            return "0";
        }

        int strIndexOf = text.lastIndexOf(".");
        if (strIndexOf > 0) {
            text = text + "00";
            text = text.substring(0, strIndexOf + 3);
        }

        DecimalFormat df = null;

        df = new DecimalFormat("###,##0");

        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }

    public static String fmtMicrometerTwo(String text) {

        int strIndexOf = text.lastIndexOf(".");
        if (strIndexOf > 0) {
            text = text + "00";
            text = text.substring(0, strIndexOf + 3);
        }

        DecimalFormat df = null;
        if (text.indexOf(".") > 0) {
            if (text.length() - text.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (text.length() - text.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = 0.0;
        try {
            number = Double.parseDouble(text);
        } catch (Exception e) {
            number = 0.0;
        }
        return df.format(number);
    }



    public static BigDecimal formatScale0(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(0, 5);
    }


    public static BigDecimal formatScale2(BigDecimal v1) {
        if (v1 == null)
            return new BigDecimal("0");
        return v1.setScale(2, BigDecimal.ROUND_DOWN);
    }
}
