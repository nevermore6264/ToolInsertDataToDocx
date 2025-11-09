package util;

/**
 * Utility class to convert numbers to Vietnamese words
 */
public class NumberToWords {
    
    private static final String[] units = {
        "", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"
    };
    
    private static final String[] tens = {
        "", "mười", "hai mươi", "ba mươi", "bốn mươi", "năm mươi",
        "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"
    };
    
    /**
     * Convert number to Vietnamese words
     * @param number The number to convert
     * @return Vietnamese words representation
     */
    public static String convert(long number) {
        if (number == 0) {
            return "không";
        }
        
        if (number < 0) {
            return "âm " + convert(-number);
        }
        
        String result = "";
        
        // Billions
        if (number >= 1000000000) {
            result += convert(number / 1000000000) + " tỷ ";
            number %= 1000000000;
        }
        
        // Millions
        if (number >= 1000000) {
            result += convertThreeDigits((int)(number / 1000000)) + " triệu ";
            number %= 1000000;
        }
        
        // Thousands
        if (number >= 1000) {
            result += convertThreeDigits((int)(number / 1000)) + " nghìn ";
            number %= 1000;
        }
        
        // Hundreds, tens, units
        if (number > 0) {
            result += convertThreeDigits((int)number);
        }
        
        return result.trim();
    }
    
    private static String convertThreeDigits(int number) {
        if (number == 0) {
            return "";
        }
        
        String result = "";
        int hundred = number / 100;
        int remainder = number % 100;
        
        if (hundred > 0) {
            result += units[hundred] + " trăm ";
        }
        
        if (remainder > 0) {
            if (remainder < 10) {
                result += "linh " + units[remainder];
            } else if (remainder < 20) {
                if (remainder == 10) {
                    result += "mười";
                } else {
                    result += "mười " + units[remainder % 10];
                }
            } else {
                int ten = remainder / 10;
                int unit = remainder % 10;
                result += tens[ten];
                if (unit > 0) {
                    result += " " + units[unit];
                }
            }
        }
        
        return result.trim();
    }
    
    /**
     * Convert double (money) to Vietnamese words
     * @param amount The amount to convert
     * @return Vietnamese words with "đồng chẵn" (capitalized first letter, no "lẻ")
     */
    public static String convertMoney(double amount) {
        if (amount == 0) {
            return "Không đồng chẵn";
        }
        
        long wholePart = (long) amount;
        long decimalPart = Math.round((amount - wholePart) * 100);
        
        String result;
        if (decimalPart > 0) {
            result = convert(wholePart) + " phẩy " + convert(decimalPart) + " xu";
        } else {
            result = convert(wholePart) + " đồng chẵn";
        }
        
        // Capitalize first letter
        if (result.length() > 0) {
            result = result.substring(0, 1).toUpperCase() + result.substring(1);
        }
        
        return result;
    }
}

