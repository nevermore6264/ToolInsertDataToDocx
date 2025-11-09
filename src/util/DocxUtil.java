package util;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading and writing .docx files using Apache POI
 */
public class DocxUtil {
    
    /**
     * Read content from a .docx file
     * @param filePath Path to the .docx file
     * @return Content as String
     * @throws IOException If file cannot be read
     */
    public static String readDocx(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {
            
            // Read paragraphs
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                content.append(paragraph.getText()).append("\n");
            }
            
            // Read tables
            List<XWPFTable> tables = document.getTables();
            for (XWPFTable table : tables) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        content.append(cell.getText()).append("\t");
                    }
                    content.append("\n");
                }
            }
        }
        
        return content.toString();
    }
    
    /**
     * Write content to a .docx file
     * @param filePath Path to save the .docx file
     * @param content Content to write
     * @throws IOException If file cannot be written
     */
    public static void writeDocx(String filePath, String content) throws IOException {
        try (FileOutputStream out = new FileOutputStream(filePath);
             XWPFDocument document = new XWPFDocument()) {
            
            String[] lines = content.split("\n");
            for (String line : lines) {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(line);
            }
            
            document.write(out);
        }
    }
    
    /**
     * Replace placeholders in a .docx file with actual values
     * @param templatePath Path to the template .docx file
     * @param outputPath Path to save the output .docx file
     * @param replacements Map of placeholder -> replacement value
     * @throws IOException If file cannot be read or written
     */
    public static void replacePlaceholders(String templatePath, String outputPath, 
                                          Map<String, String> replacements) throws IOException {
        try (FileInputStream fis = new FileInputStream(templatePath);
             XWPFDocument document = new XWPFDocument(fis);
             FileOutputStream out = new FileOutputStream(outputPath)) {
            
            // Replace in paragraphs
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                replaceInParagraph(paragraph, replacements);
            }
            
            // Replace in tables
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            replaceInParagraph(paragraph, replacements);
                        }
                    }
                }
            }
            
            document.write(out);
        }
    }
    
    /**
     * Replace placeholders in a paragraph
     */
    private static void replaceInParagraph(XWPFParagraph paragraph, Map<String, String> replacements) {
        String text = paragraph.getText();
        String newText = text;
        
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            newText = newText.replace(entry.getKey(), entry.getValue());
        }
        
        if (!text.equals(newText)) {
            // Clear existing runs
            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }
            // Add new run with replaced text
            XWPFRun run = paragraph.createRun();
            run.setText(newText);
        }
    }
    
    /**
     * Create a new .docx file with a table
     * @param filePath Path to save the .docx file
     * @param headers Table headers
     * @param data Table data rows
     * @throws IOException If file cannot be written
     */
    public static void createDocxWithTable(String filePath, String[] headers, 
                                           String[][] data) throws IOException {
        try (FileOutputStream out = new FileOutputStream(filePath);
             XWPFDocument document = new XWPFDocument()) {
            
            // Create table
            XWPFTable table = document.createTable(data.length + 1, headers.length);
            
            // Add headers
            XWPFTableRow headerRow = table.getRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.getCell(i).setText(headers[i]);
            }
            
            // Add data rows
            for (int i = 0; i < data.length; i++) {
                XWPFTableRow row = table.getRow(i + 1);
                for (int j = 0; j < data[i].length; j++) {
                    row.getCell(j).setText(data[i][j]);
                }
            }
            
            document.write(out);
        }
    }
}

