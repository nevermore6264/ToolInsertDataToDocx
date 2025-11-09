package util;

/**
 * Utility class to detect region (Nam/Bắc) based on address
 */
public class RegionDetector {
    
    // Các tỉnh/thành phố miền Bắc
    private static final String[] NORTH_PROVINCES = {
        "Hà Nội", "Hanoi", "Hà Giang", "Cao Bằng", "Bắc Kạn", "Tuyên Quang",
        "Lào Cai", "Điện Biên", "Lai Châu", "Sơn La", "Yên Bái", "Hoà Bình",
        "Thái Nguyên", "Lạng Sơn", "Quảng Ninh", "Bắc Giang", "Phú Thọ",
        "Vĩnh Phúc", "Bắc Ninh", "Hải Dương", "Hải Phòng", "Hưng Yên",
        "Thái Bình", "Hà Nam", "Nam Định", "Ninh Bình"
    };
    
    // Các tỉnh/thành phố miền Nam
    private static final String[] SOUTH_PROVINCES = {
        "Hồ Chí Minh", "Ho Chi Minh", "HCM", "TP.HCM", "TP HCM",
        "Cần Thơ", "An Giang", "Bà Rịa - Vũng Tàu", "Bạc Liêu", "Bến Tre",
        "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Đắk Lắk",
        "Đắk Nông", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hậu Giang",
        "Kiên Giang", "Lâm Đồng", "Long An", "Sóc Trăng", "Tây Ninh",
        "Tiền Giang", "Trà Vinh", "Vĩnh Long", "Phú Yên", "Khánh Hòa",
        "Ninh Thuận", "Bình Định", "Quảng Ngãi", "Quảng Nam", "Đà Nẵng",
        "Da Nang", "Thừa Thiên Huế", "Huế"
    };
    
    /**
     * Detect region (Nam/Bắc) based on address
     * @param address The address string
     * @return "Nam" or "Bắc", default to "Nam" if cannot determine
     */
    public static String detectRegion(String address) {
        if (address == null || address.trim().isEmpty()) {
            return "Nam"; // Default
        }
        
        String addressUpper = address.toUpperCase();
        
        // Check for North provinces
        for (String province : NORTH_PROVINCES) {
            if (addressUpper.contains(province.toUpperCase())) {
                return "Bắc";
            }
        }
        
        // Check for South provinces
        for (String province : SOUTH_PROVINCES) {
            if (addressUpper.contains(province.toUpperCase())) {
                return "Nam";
            }
        }
        
        // Check for common keywords
        if (addressUpper.contains("MIỀN BẮC") || addressUpper.contains("MIEN BAC") ||
            addressUpper.contains("BẮC") || addressUpper.contains("BAC")) {
            return "Bắc";
        }
        
        if (addressUpper.contains("MIỀN NAM") || addressUpper.contains("MIEN NAM") ||
            addressUpper.contains("NAM")) {
            return "Nam";
        }
        
        // Default to Nam if cannot determine
        return "Nam";
    }
    
    /**
     * Check if address contains a province name
     * @param address The address string
     * @return true if contains a known province
     */
    public static boolean containsProvince(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        
        String addressUpper = address.toUpperCase();
        
        for (String province : NORTH_PROVINCES) {
            if (addressUpper.contains(province.toUpperCase())) {
                return true;
            }
        }
        
        for (String province : SOUTH_PROVINCES) {
            if (addressUpper.contains(province.toUpperCase())) {
                return true;
            }
        }
        
        return false;
    }
}

