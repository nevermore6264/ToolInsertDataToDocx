package model;

public class PartyAModel {
    private String region; // "Nam" or "Bắc"
    private String partyAName;
    private String address;
    private String phone;
    private String accountNumber;
    private String taxCode;
    private String representative;
    private String position;

    public PartyAModel() {
        // Initialize with null values - will be set by user input
        this.region = "Nam"; // Default to "Nam"
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPartyAName() {
        return partyAName;
    }

    public void setPartyAName(String partyAName) {
        this.partyAName = partyAName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Khu vực: ").append(region != null ? region : "").append("\n");
        sb.append("Bên A (Bên mua): ").append(partyAName != null ? partyAName : "").append("\n");
        sb.append("Địa chỉ: ").append(address != null ? address : "").append("\n");
        sb.append("Điện thoại: ").append(phone != null ? phone : "").append("\n");
        sb.append("Tài khoản số: ").append(accountNumber != null ? accountNumber : "").append("\n");
        sb.append("Mã số thuế: ").append(taxCode != null ? taxCode : "").append("\n");
        sb.append("Đại diện: Ông ").append(representative != null ? representative : "").append("\n");
        sb.append("Chức vụ: ").append(position != null ? position : "");
        return sb.toString();
    }
}
