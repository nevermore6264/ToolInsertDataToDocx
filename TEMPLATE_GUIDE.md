# Hướng dẫn sử dụng Template

## Cách sử dụng Placeholder trong file Word template

Để tự động điền dữ liệu vào file hợp đồng, bạn cần đặt các placeholder trong file template Word.

### Danh sách Placeholder hỗ trợ:

#### Thông tin ngày tháng:

- `${DATE}` hoặc `{{DATE}}` → Ngày (ví dụ: 15)
- `${MONTH}` hoặc `{{MONTH}}` → Tháng (ví dụ: 03)
- `${YEAR}` hoặc `{{YEAR}}` → Năm (ví dụ: 2025)
- `${FULL_DATE}` → Ngày đầy đủ (ví dụ: 15/03/2025)

#### Thông tin Bên A (Bên mua):

- `${PARTY_A_NAME}` hoặc `{{PARTY_A_NAME}}` → Tên Bên A
- `${ADDRESS}` hoặc `{{ADDRESS}}` → Địa chỉ
- `${PHONE}` hoặc `{{PHONE}}` → Điện thoại
- `${ACCOUNT_NUMBER}` hoặc `{{ACCOUNT_NUMBER}}` → Tài khoản số
- `${TAX_CODE}` hoặc `{{TAX_CODE}}` → Mã số thuế
- `${REPRESENTATIVE}` hoặc `{{REPRESENTATIVE}}` → Đại diện
- `${POSITION}` hoặc `{{POSITION}}` → Chức vụ
- `${REGION}` hoặc `{{REGION}}` → Khu vực (Nam/Bắc)

## Ví dụ sử dụng trong template:

```
Hôm nay, ngày ${DATE} tháng ${MONTH} năm ${YEAR}, tại Văn phòng Hội đồng nhân dân và Ủy ban nhân dân xã Lộc Ninh - Công an.....

Bên A (Bên mua): ${PARTY_A_NAME}
Địa chỉ: ${ADDRESS}
Điện thoại: ${PHONE}
Tài khoản số: ${ACCOUNT_NUMBER}
Mã số thuế: ${TAX_CODE}
Đại diện: Ông ${REPRESENTATIVE}
Chức vụ: ${POSITION}
```

## Cách tạo file hợp đồng:

1. Nhập đầy đủ thông tin vào form
2. Nhấn nút "Lưu"
3. Chọn "Có" khi được hỏi có muốn tạo file hợp đồng
4. Chọn file template (file .doc hoặc .docx trong thư mục resource)
5. Chọn nơi lưu file output
6. File hợp đồng sẽ được tạo với dữ liệu đã điền

## Lưu ý:

- File template có thể là .doc hoặc .docx
- Placeholder phải chính xác (phân biệt chữ hoa/thường)
- Nếu placeholder không được thay thế, giá trị sẽ là chuỗi rỗng
- File output sẽ có cùng định dạng với file template
