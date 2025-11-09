package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import model.PriceListManager;

public class PartyAView extends JFrame {
    private JComboBox<String> cmbRegion;
    private JTextArea txtPartyAName;
    private JTextArea txtAddress;
    private JTextField txtPhone;
    private JTextField txtAccountNumber;
    private JTextField txtTaxCode;
    private JTextField txtRepresentative;
    private JTextField txtPosition;
    private JButton btnSave;
    private JButton btnCancel;
    private ProductTableView productTableView;

    public PartyAView() {
        initializeComponents();
        setupUI();
    }

    private void initializeComponents() {
        String[] regions = {"Nam", "Bắc"};
        cmbRegion = new JComboBox<>(regions);
        cmbRegion.setSelectedIndex(0); // Default to "Nam"
        txtPartyAName = createTextAreaWithPlaceholder("Nhập tên Bên A (Bên mua)");
        txtAddress = createTextAreaWithPlaceholder("Nhập địa chỉ");
        txtPhone = createTextFieldWithPlaceholder("Nhập số điện thoại");
        txtAccountNumber = createTextFieldWithPlaceholder("Nhập số tài khoản");
        txtTaxCode = createTextFieldWithPlaceholder("Nhập mã số thuế");
        txtRepresentative = createTextFieldWithPlaceholder("Nhập tên đại diện");
        txtPosition = createTextFieldWithPlaceholder("Nhập chức vụ");
        btnSave = new JButton("Lưu");
        btnCancel = new JButton("Hủy");
        productTableView = new ProductTableView();
    }
    
    private JTextField createTextFieldWithPlaceholder(String placeholder) {
        JTextField textField = new JTextField();
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
        return textField;
    }
    
    private JTextArea createTextAreaWithPlaceholder(String placeholder) {
        JTextArea textArea = new JTextArea();
        textArea.setForeground(Color.GRAY);
        textArea.setText(placeholder);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textArea.getText().equals(placeholder)) {
                    textArea.setText("");
                    textArea.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (textArea.getText().isEmpty()) {
                    textArea.setForeground(Color.GRAY);
                    textArea.setText(placeholder);
                }
            }
        });
        return textArea;
    }

    private void setupUI() {
        setTitle("Nhập thông tin Bên A (Bên mua)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null);

        // Set modern Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Header panel with title
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Content panel with form and table
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setResizeWeight(0.4);
        splitPane.setBorder(null);
        
        // Form panel (left)
        JPanel formContainer = new JPanel(new BorderLayout());
        formContainer.setBackground(new Color(245, 247, 250));
        JPanel formPanel = createFormPanel();
        formContainer.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        formContainer.add(buttonPanel, BorderLayout.SOUTH);
        
        splitPane.setLeftComponent(formContainer);
        
        // Product table panel (right)
        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBackground(new Color(245, 247, 250));
        tableContainer.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            "Bảng giá sản phẩm",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(52, 73, 94)
        ));
        tableContainer.add(productTableView, BorderLayout.CENTER);
        
        splitPane.setRightComponent(tableContainer);
        
        mainPanel.add(splitPane, BorderLayout.CENTER);

        add(mainPanel);
        
        // Load products after UI is set up
        loadProductsForRegion("Nam");
        
        // Add listener to region ComboBox
        cmbRegion.addActionListener(e -> {
            String selectedRegion = (String) cmbRegion.getSelectedItem();
            if (selectedRegion != null) {
                loadProductsForRegion(selectedRegion);
            }
        });
        
        // Initialize with sample data for testing
        initSampleData();
    }
    
    /**
     * Initialize form with sample data for testing
     * TODO: Remove this method after testing
     */
    private void initSampleData() {
        // Set region
        cmbRegion.setSelectedIndex(0); // Nam
        
        // Set Party A name
        txtPartyAName.setText("CÔNG TY TNHH THƯƠNG MẠI VÀ DỊCH VỤ ABC");
        txtPartyAName.setForeground(Color.BLACK);
        
        // Set address
        txtAddress.setText("123 Đường Nguyễn Văn A, Phường 1, Quận 1, TP. Hồ Chí Minh");
        txtAddress.setForeground(Color.BLACK);
        
        // Set phone
        txtPhone.setText("0901234567");
        txtPhone.setForeground(Color.BLACK);
        
        // Set account number
        txtAccountNumber.setText("1234567890");
        txtAccountNumber.setForeground(Color.BLACK);
        
        // Set tax code
        txtTaxCode.setText("0123456789");
        txtTaxCode.setForeground(Color.BLACK);
        
        // Set representative
        txtRepresentative.setText("Nguyễn Văn B");
        txtRepresentative.setForeground(Color.BLACK);
        
        // Set position
        txtPosition.setText("Giám đốc");
        txtPosition.setForeground(Color.BLACK);
    }
    
    private void loadProductsForRegion(String region) {
        if (region.equals("Nam")) {
            productTableView.loadProducts("Nam", PriceListManager.getNamPriceList());
        } else {
            productTableView.loadProducts("Bắc", PriceListManager.getBacPriceList());
        }
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titleLabel = new JLabel("THÔNG TIN BÊN A (BÊN MUA)");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        return headerPanel;
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 247, 250));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            new TitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                "Nhập thông tin",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(52, 73, 94)
            ),
            new EmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Font for labels
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 11);

        // Row 0: Khu vực (full width)
        addFormField(formPanel, gbc, "Khu vực:", cmbRegion, labelFont, fieldFont, 0, 0, 2);

        // Row 1: Bên A (full width) - TextArea
        addTextAreaField(formPanel, gbc, "Bên A (Bên mua):", txtPartyAName, labelFont, fieldFont, 1, 0, 2);

        // Row 2: Địa chỉ (full width) - TextArea
        addTextAreaField(formPanel, gbc, "Địa chỉ:", txtAddress, labelFont, fieldFont, 2, 0, 2);

        // Row 3: Điện thoại (full width)
        addFormField(formPanel, gbc, "Điện thoại:", txtPhone, labelFont, fieldFont, 3, 0, 2);

        // Row 4: Tài khoản số (full width)
        addFormField(formPanel, gbc, "Tài khoản số:", txtAccountNumber, labelFont, fieldFont, 4, 0, 2);

        // Row 5: Mã số thuế (full width)
        addFormField(formPanel, gbc, "Mã số thuế:", txtTaxCode, labelFont, fieldFont, 5, 0, 2);

        // Row 6: Đại diện (full width)
        addFormField(formPanel, gbc, "Đại diện:", txtRepresentative, labelFont, fieldFont, 6, 0, 2);

        // Row 7: Chức vụ (full width)
        addFormField(formPanel, gbc, "Chức vụ:", txtPosition, labelFont, fieldFont, 7, 0, 2);

        return formPanel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, 
                             JComponent component, Font labelFont, Font fieldFont, 
                             int row, int startCol, int colSpan) {
        // Calculate grid positions: each column has label + field = 2 grid positions
        // startCol 0 = columns 0-1, startCol 1 = columns 2-3
        int labelCol = startCol * 2;
        int fieldCol = startCol * 2 + 1;
        int fieldWidth = colSpan * 2 - 1; // If colSpan=2, field spans 3 grid positions
        
        // Label
        gbc.gridx = labelCol;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(new Color(52, 73, 94));
        label.setPreferredSize(new Dimension(90, 25));
        panel.add(label, gbc);

        // Field
        gbc.gridx = fieldCol;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridwidth = fieldWidth;
        
        if (component instanceof JTextField textField) {
            textField.setFont(fieldFont);
            textField.setPreferredSize(new Dimension(180, 28));
            textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            textField.setBackground(Color.WHITE);
        } else if (component instanceof JComboBox<?> comboBox) {
            comboBox.setFont(fieldFont);
            comboBox.setPreferredSize(new Dimension(180, 28));
        }
        
        panel.add(component, gbc);
    }
    
    private void addTextAreaField(JPanel panel, GridBagConstraints gbc, String labelText, 
                                 JTextArea textArea, Font labelFont, Font fieldFont, 
                                 int row, int startCol, int colSpan) {
        // Calculate grid positions
        int labelCol = startCol * 2;
        int fieldCol = startCol * 2 + 1;
        int fieldWidth = colSpan * 2 - 1;
        
        // Label
        gbc.gridx = labelCol;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(new Color(52, 73, 94));
        label.setPreferredSize(new Dimension(90, 25));
        panel.add(label, gbc);

        // TextArea with scroll pane
        gbc.gridx = fieldCol;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        gbc.gridwidth = fieldWidth;
        
        textArea.setFont(fieldFont);
        textArea.setRows(3);
        textArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        textArea.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(180, 70));
        panel.add(scrollPane, gbc);
        
        // Reset weighty for next row
        gbc.weighty = 0;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        buttonPanel.setBackground(new Color(245, 247, 250));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Style for Save button
        styleButton(btnSave, new Color(46, 204, 113), Color.WHITE, new Color(39, 174, 96));
        btnSave.setPreferredSize(new Dimension(120, 40));
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Style for Cancel button
        styleButton(btnCancel, new Color(231, 76, 60), Color.WHITE, new Color(192, 57, 43));
        btnCancel.setPreferredSize(new Dimension(120, 40));
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 13));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        return buttonPanel;
    }

    private void styleButton(JButton button, Color bgColor, Color textColor, Color hoverColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    // Getters for text fields
    public JTextArea getTxtPartyAName() {
        return txtPartyAName;
    }

    public JTextArea getTxtAddress() {
        return txtAddress;
    }

    public JTextField getTxtPhone() {
        return txtPhone;
    }

    public JTextField getTxtAccountNumber() {
        return txtAccountNumber;
    }

    public JTextField getTxtTaxCode() {
        return txtTaxCode;
    }

    public JTextField getTxtRepresentative() {
        return txtRepresentative;
    }

    public JTextField getTxtPosition() {
        return txtPosition;
    }

    public JButton getBtnSave() {
        return btnSave;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JComboBox<String> getCmbRegion() {
        return cmbRegion;
    }
    
    public ProductTableView getProductTableView() {
        return productTableView;
    }
}
