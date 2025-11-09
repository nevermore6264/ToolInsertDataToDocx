package controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.PartyAModel;
import util.ContractGenerator;
import util.RegionDetector;
import view.PartyAView;

public class PartyAController {
    private final PartyAModel model;
    private final PartyAView view;

    public PartyAController(PartyAModel model, PartyAView view) {
        this.model = model;
        this.view = view;
        
        // Load data from model to view
        loadDataToView();
        
        // Register event listeners
        registerEventListeners();
    }

    private void loadDataToView() {
        // Load region
        if (model.getRegion() != null) {
            if (model.getRegion().equals("Bắc")) {
                view.getCmbRegion().setSelectedIndex(1);
            } else {
                view.getCmbRegion().setSelectedIndex(0);
            }
        }
        
        // Only load data if model has values, otherwise keep placeholders
        if (model.getPartyAName() != null && !model.getPartyAName().isEmpty()) {
            view.getTxtPartyAName().setText(model.getPartyAName());
            view.getTxtPartyAName().setForeground(Color.BLACK);
        }
        if (model.getAddress() != null && !model.getAddress().isEmpty()) {
            view.getTxtAddress().setText(model.getAddress());
            view.getTxtAddress().setForeground(Color.BLACK);
        }
        if (model.getPhone() != null && !model.getPhone().isEmpty()) {
            view.getTxtPhone().setText(model.getPhone());
            view.getTxtPhone().setForeground(Color.BLACK);
        }
        if (model.getAccountNumber() != null && !model.getAccountNumber().isEmpty()) {
            view.getTxtAccountNumber().setText(model.getAccountNumber());
            view.getTxtAccountNumber().setForeground(Color.BLACK);
        }
        if (model.getTaxCode() != null && !model.getTaxCode().isEmpty()) {
            view.getTxtTaxCode().setText(model.getTaxCode());
            view.getTxtTaxCode().setForeground(Color.BLACK);
        }
        if (model.getRepresentative() != null && !model.getRepresentative().isEmpty()) {
            view.getTxtRepresentative().setText(model.getRepresentative());
            view.getTxtRepresentative().setForeground(Color.BLACK);
        }
        if (model.getPosition() != null && !model.getPosition().isEmpty()) {
            view.getTxtPosition().setText(model.getPosition());
            view.getTxtPosition().setForeground(Color.BLACK);
        }
    }
    
    private String getTextFieldValue(JTextField textField, String placeholder) {
        String text = textField.getText().trim();
        // If text is placeholder or empty, return empty string
        if (text.equals(placeholder) || text.isEmpty()) {
            return "";
        }
        return text;
    }
    
    private String getTextAreaValue(javax.swing.JTextArea textArea, String placeholder) {
        String text = textArea.getText().trim();
        // If text is placeholder or empty, return empty string
        if (text.equals(placeholder) || text.isEmpty()) {
            return "";
        }
        return text;
    }

    private void registerEventListeners() {
        // Save button
        view.getBtnSave().addActionListener(e -> handleSave());

        // Cancel button
        view.getBtnCancel().addActionListener(e -> handleCancel());
        
        // Auto-detect region from address
        view.getTxtAddress().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                autoDetectRegion();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                autoDetectRegion();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                autoDetectRegion();
            }
        });
    }
    
    private void autoDetectRegion() {
        String address = view.getTxtAddress().getText();
        String placeholder = "Nhập địa chỉ";
        
        // Skip if it's placeholder text
        if (address.equals(placeholder) || address.trim().isEmpty()) {
            return;
        }
        
        String detectedRegion = RegionDetector.detectRegion(address);
        
        // Update ComboBox if region is detected
        if (detectedRegion.equals("Bắc")) {
            view.getCmbRegion().setSelectedIndex(1);
        } else {
            view.getCmbRegion().setSelectedIndex(0);
        }
    }

    private void handleSave() {
        // Get data from view and update to model (ignore placeholder text)
        String selectedRegion = (String) view.getCmbRegion().getSelectedItem();
        model.setRegion(selectedRegion != null ? selectedRegion : "Nam");
        model.setPartyAName(getTextAreaValue(view.getTxtPartyAName(), "Nhập tên Bên A (Bên mua)"));
        model.setAddress(getTextAreaValue(view.getTxtAddress(), "Nhập địa chỉ"));
        
        // Auto-detect region from address if not manually selected
        String address = model.getAddress();
        if (address != null && !address.isEmpty()) {
            String detectedRegion = RegionDetector.detectRegion(address);
            model.setRegion(detectedRegion);
            // Update ComboBox to match detected region
            if (detectedRegion.equals("Bắc")) {
                view.getCmbRegion().setSelectedIndex(1);
            } else {
                view.getCmbRegion().setSelectedIndex(0);
            }
        }
        model.setPhone(getTextFieldValue(view.getTxtPhone(), "Nhập số điện thoại"));
        model.setAccountNumber(getTextFieldValue(view.getTxtAccountNumber(), "Nhập số tài khoản"));
        model.setTaxCode(getTextFieldValue(view.getTxtTaxCode(), "Nhập mã số thuế"));
        model.setRepresentative(getTextFieldValue(view.getTxtRepresentative(), "Nhập tên đại diện"));
        model.setPosition(getTextFieldValue(view.getTxtPosition(), "Nhập chức vụ"));

        // Validation
        if (validateData()) {
            // Ask if user wants to generate contract
            int option = JOptionPane.showConfirmDialog(
                view,
                "Bạn có muốn tạo file hợp đồng từ template không?",
                "Tạo hợp đồng",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (option == JOptionPane.YES_OPTION) {
                generateContract();
            } else {
                // Display saved information
                JOptionPane.showMessageDialog(
                    view,
                    "Thông tin đã nhập:\n\n" + model.toString(),
                    "Thông tin đã lưu",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private boolean validateData() {
        // Add validation logic here
        // Example: check required fields
        if (model.getPartyAName() == null || model.getPartyAName().isEmpty()) {
            JOptionPane.showMessageDialog(
                view,
                "Vui lòng nhập tên Bên A (Bên mua)!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }

    private void handleCancel() {
        int option = JOptionPane.showConfirmDialog(
            view,
            "Bạn có chắc chắn muốn đóng ứng dụng?",
            "Xác nhận",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (option == JOptionPane.YES_OPTION) {
            view.dispose();
        }
    }

    public PartyAModel getModel() {
        return model;
    }

    public PartyAView getView() {
        return view;
    }
    
    /**
     * Generate contract document from template
     */
    private void generateContract() {
        try {
            // Step 1: Select template file
            JFileChooser templateChooser = new JFileChooser("resource");
            templateChooser.setDialogTitle("Chọn file template hợp đồng");
            templateChooser.setFileFilter(new FileNameExtensionFilter(
                "Word Documents (*.doc, *.docx)", "doc", "docx"));
            templateChooser.setAcceptAllFileFilterUsed(false);
            
            int templateResult = templateChooser.showOpenDialog(view);
            if (templateResult != JFileChooser.APPROVE_OPTION) {
                return; // User cancelled
            }
            
            File templateFile = templateChooser.getSelectedFile();
            String templatePath = templateFile.getAbsolutePath();
            
            // Step 2: Select output location
            JFileChooser outputChooser = new JFileChooser();
            outputChooser.setDialogTitle("Chọn nơi lưu file hợp đồng");
            
            // Set default filename
            String defaultName = "Hop_Dong_" + model.getPartyAName()
                .replaceAll("[^a-zA-Z0-9]", "_")
                .substring(0, Math.min(30, model.getPartyAName().length()));
            
            String extension = templatePath.toLowerCase().endsWith(".docx") ? ".docx" : ".doc";
            outputChooser.setSelectedFile(new File(defaultName + extension));
            
            int outputResult = outputChooser.showSaveDialog(view);
            if (outputResult != JFileChooser.APPROVE_OPTION) {
                return; // User cancelled
            }
            
            File outputFile = outputChooser.getSelectedFile();
            String outputPath = outputFile.getAbsolutePath();
            
            // Ensure correct extension
            if (!outputPath.toLowerCase().endsWith(extension)) {
                outputPath += extension;
            }
            
            // Step 3: Generate contract
            ContractGenerator.generateContract(model, templatePath, outputPath);
            
            // Step 4: Show success message
            String message = String.format(
                "Tạo file hợp đồng thành công!%n%nFile đã được lưu tại:%n%s",
                outputPath
            );
            JOptionPane.showMessageDialog(
                view,
                message,
                "Thành công",
                JOptionPane.INFORMATION_MESSAGE
            );
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                view,
                "Lỗi khi tạo file hợp đồng:\n" + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                view,
                "Đã xảy ra lỗi:\n" + e.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}
