package util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.PartyAModel;

/**
 * Utility class to generate contract documents from PartyAModel
 */
public class ContractGenerator {
    
    /**
     * Generate contract document from PartyAModel
     * @param model PartyAModel containing the data
     * @param templatePath Path to the template file
     * @param outputPath Path to save the output file
     * @throws IOException If file cannot be read or written
     */
    public static void generateContract(PartyAModel model, String templatePath, String outputPath) 
            throws IOException {
        
        // Create replacement map
        Map<String, String> replacements = createReplacementMap(model);
        
        // Determine file type and use appropriate utility
        if (templatePath.toLowerCase().endsWith(".docx")) {
            DocxUtil.replacePlaceholders(templatePath, outputPath, replacements);
        } else if (templatePath.toLowerCase().endsWith(".doc")) {
            DocUtil.replacePlaceholders(templatePath, outputPath, replacements);
        } else {
            throw new IllegalArgumentException("Unsupported file format. Only .doc and .docx are supported.");
        }
    }
    
    /**
     * Create replacement map from PartyAModel
     * @param model PartyAModel containing the data
     * @return Map of placeholders to values
     */
    private static Map<String, String> createReplacementMap(PartyAModel model) {
        Map<String, String> replacements = new HashMap<>();
        
        // Date information
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Date now = new Date();
        
        replacements.put("${DATE}", dateFormat.format(now));
        replacements.put("${MONTH}", monthFormat.format(now));
        replacements.put("${YEAR}", yearFormat.format(now));
        replacements.put("${FULL_DATE}", new SimpleDateFormat("dd/MM/yyyy").format(now));
        
        // Party A information
        replacements.put("${PARTY_A_NAME}", model.getPartyAName() != null ? model.getPartyAName() : "");
        replacements.put("${ADDRESS}", model.getAddress() != null ? model.getAddress() : "");
        replacements.put("${PHONE}", model.getPhone() != null ? model.getPhone() : "");
        replacements.put("${ACCOUNT_NUMBER}", model.getAccountNumber() != null ? model.getAccountNumber() : "");
        replacements.put("${TAX_CODE}", model.getTaxCode() != null ? model.getTaxCode() : "");
        replacements.put("${REPRESENTATIVE}", model.getRepresentative() != null ? model.getRepresentative() : "");
        replacements.put("${POSITION}", model.getPosition() != null ? model.getPosition() : "");
        replacements.put("${REGION}", model.getRegion() != null ? model.getRegion() : "Nam");
        
        // Alternative placeholders (without ${})
        replacements.put("{{DATE}}", dateFormat.format(now));
        replacements.put("{{MONTH}}", monthFormat.format(now));
        replacements.put("{{YEAR}}", yearFormat.format(now));
        replacements.put("{{PARTY_A_NAME}}", model.getPartyAName() != null ? model.getPartyAName() : "");
        replacements.put("{{ADDRESS}}", model.getAddress() != null ? model.getAddress() : "");
        replacements.put("{{PHONE}}", model.getPhone() != null ? model.getPhone() : "");
        replacements.put("{{ACCOUNT_NUMBER}}", model.getAccountNumber() != null ? model.getAccountNumber() : "");
        replacements.put("{{TAX_CODE}}", model.getTaxCode() != null ? model.getTaxCode() : "");
        replacements.put("{{REPRESENTATIVE}}", model.getRepresentative() != null ? model.getRepresentative() : "");
        replacements.put("{{POSITION}}", model.getPosition() != null ? model.getPosition() : "");
        
        return replacements;
    }
}

