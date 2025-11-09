# ImportExcel Project

## Cài đặt thư viện Apache POI

Dự án này sử dụng Apache POI để đọc và ghi file .docx.

### Cách 1: Sử dụng Maven (Khuyến nghị)

1. **Nếu bạn đang dùng IntelliJ IDEA:**

   - Mở project trong IntelliJ IDEA
   - Click chuột phải vào file `pom.xml` → chọn "Add as Maven Project"
   - IntelliJ sẽ tự động tải các dependencies

2. **Hoặc sử dụng command line:**
   ```bash
   mvn clean install
   ```

### Cách 2: Tải thư viện thủ công

Nếu không dùng Maven, bạn có thể tải các file JAR từ:

- https://poi.apache.org/download.html

Cần tải các file sau:

- `poi-ooxml-5.2.5.jar` (cho file .docx)
- `poi-5.2.5.jar`
- `poi-scratchpad-5.2.5.jar` (cho file .doc cũ)
- Các dependencies khác (xem trong pom.xml)

Sau đó thêm vào classpath của project.

## Sử dụng DocxUtil

Class `DocxUtil` đã được tạo sẵn với các phương thức:

- `readDocx(String filePath)` - Đọc nội dung từ file .docx
- `writeDocx(String filePath, String content)` - Ghi nội dung vào file .docx
- `replacePlaceholders(...)` - Thay thế placeholder trong template
- `createDocxWithTable(...)` - Tạo file .docx với bảng

## Ví dụ sử dụng

```java
import util.DocxUtil;
import java.io.IOException;

// Đọc file .docx
String content = DocxUtil.readDocx("resource/7. Đề nghị thanh toán.docx");
System.out.println(content);

// Ghi file .docx
DocxUtil.writeDocx("output.docx", "Nội dung cần ghi");

// Thay thế placeholder
Map<String, String> replacements = new HashMap<>();
replacements.put("${partyAName}", "Tên công ty");
DocxUtil.replacePlaceholders("template.docx", "output.docx", replacements);
```
