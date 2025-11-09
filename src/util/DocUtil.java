package util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.Map;

/**
 * Utility class for reading and writing .doc files using Apache POI
 */
public class DocUtil {
    
    /**
     * Read content from a .doc file
     * @param filePath Path to the .doc file
     * @return Content as String
     * @throws IOException If file cannot be read
     */
    public static String readDoc(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             HWPFDocument document = new HWPFDocument(fis)) {
            
            Range range = document.getRange();
            return range.text();
        }
    }
    
    /**
     * Replace placeholders in a .doc file with actual values
     * @param templatePath Path to the template .doc file
     * @param outputPath Path to save the output .doc file
     * @param replacements Map of placeholder -> replacement value
     * @throws IOException If file cannot be read or written
     */
    public static void replacePlaceholders(String templatePath, String outputPath, 
                                          Map<String, String> replacements) throws IOException {
        try (FileInputStream fis = new FileInputStream(templatePath);
             HWPFDocument document = new HWPFDocument(fis);
             FileOutputStream out = new FileOutputStream(outputPath)) {
            
            Range range = document.getRange();
            
            // Replace all placeholders
            for (Map.Entry<String, String> entry : replacements.entrySet()) {
                String placeholder = entry.getKey();
                String replacement = entry.getValue() != null ? entry.getValue() : "";
                
                // Replace all occurrences
                while (range.text().contains(placeholder)) {
                    range.replaceText(placeholder, replacement);
                }
            }
            
            document.write(out);
        }
    }
    
    /**
     * Replace a single placeholder in a .doc file
     * @param document HWPFDocument to modify
     * @param placeholder Placeholder text to find
     * @param replacement Replacement text
     */
    public static void replaceText(HWPFDocument document, String placeholder, String replacement) {
        Range range = document.getRange();
        while (range.text().contains(placeholder)) {
            range.replaceText(placeholder, replacement != null ? replacement : "");
        }
    }
}

