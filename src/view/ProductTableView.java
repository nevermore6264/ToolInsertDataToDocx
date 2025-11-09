package view;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Product;

/**
 * View for displaying product table - displays based on selected region
 */
public class ProductTableView extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel totalLabel;
    private JLabel totalWordsLabel;
    private String currentRegion;
    
    private static final DecimalFormat numberFormat = new DecimalFormat("#,###");
    
    public ProductTableView() {
        setLayout(new BorderLayout());
        currentRegion = "Nam"; // Default
        initializeComponents();
    }
    
    private void initializeComponents() {
        // Create single table panel
        Object[] components = createTablePanel();
        JPanel panel = (JPanel) components[0];
        table = (JTable) components[1];
        tableModel = (DefaultTableModel) table.getModel();
        totalLabel = (JLabel) components[2];
        totalWordsLabel = (JLabel) components[3];
        
        add(panel, BorderLayout.CENTER);
    }
    
    private Object[] createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Table
        String[] columnNames = {"STT", "Hàng hóa cung cấp", "Xuất xứ", "Quy cách", "ĐVT", "Số lượng", "Đơn giá", "Thành Tiền"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) { // Số lượng
                    return Integer.class;
                } else if (columnIndex == 6 || columnIndex == 7) { // Đơn giá, Thành Tiền
                    return Double.class;
                }
                return String.class;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing: ĐVT (4), Số lượng (5), Đơn giá (6), Thành Tiền (7)
                return column == 4 || column == 5 || column == 6 || column == 7;
            }
        };
        
        JTable tableInstance = new JTable(model);
        tableInstance.setRowHeight(60);
        tableInstance.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableInstance.getColumnModel().getColumn(1).setPreferredWidth(350);
        tableInstance.getColumnModel().getColumn(2).setPreferredWidth(180);
        tableInstance.getColumnModel().getColumn(3).setPreferredWidth(100);
        // 4 cột cuối: ĐVT, Số lượng, Đơn giá, Thành Tiền - mỗi cột 1 dòng riêng
        tableInstance.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableInstance.getColumnModel().getColumn(5).setPreferredWidth(120);
        tableInstance.getColumnModel().getColumn(6).setPreferredWidth(140);
        tableInstance.getColumnModel().getColumn(7).setPreferredWidth(160);
        
        // Set multi-line renderer for "Hàng hóa cung cấp" (column 1) and "Xuất xứ" (column 2)
        tableInstance.getColumnModel().getColumn(1).setCellRenderer(new MultiLineTableCellRenderer());
        tableInstance.getColumnModel().getColumn(2).setCellRenderer(new MultiLineTableCellRenderer());
        
        // Set renderer and editor for number columns
        tableInstance.getColumnModel().getColumn(6).setCellRenderer(new CurrencyRenderer());
        tableInstance.getColumnModel().getColumn(6).setCellEditor(new NumberCellEditor());
        tableInstance.getColumnModel().getColumn(7).setCellRenderer(new CurrencyRenderer());
        tableInstance.getColumnModel().getColumn(7).setCellEditor(new NumberCellEditor());
        
        // Set editor for quantity column
        tableInstance.getColumnModel().getColumn(5).setCellEditor(new NumberCellEditor());
        
        // Add popup menu for column management
        JPopupMenu columnMenu = new JPopupMenu();
        JMenuItem hideColumnItem = new JMenuItem("Ẩn cột đã chọn");
        hideColumnItem.addActionListener(e -> {
            int[] selectedColumns = tableInstance.getSelectedColumns();
            if (selectedColumns.length > 0) {
                for (int i = selectedColumns.length - 1; i >= 0; i--) {
                    int col = selectedColumns[i];
                    if (col >= 0 && col < tableInstance.getColumnCount()) {
                        tableInstance.removeColumn(tableInstance.getColumnModel().getColumn(col));
                    }
                }
            }
        });
        columnMenu.add(hideColumnItem);
        
        // Add mouse listener for column header context menu
        tableInstance.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showColumnMenu(e);
                }
            }
            
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showColumnMenu(e);
                }
            }
            
            private void showColumnMenu(java.awt.event.MouseEvent e) {
                int col = tableInstance.columnAtPoint(e.getPoint());
                if (col >= 0) {
                    tableInstance.setColumnSelectionInterval(col, col);
                    columnMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        // Add table change listener
        tableInstance.getModel().addTableModelListener(e -> {
            int column = e.getColumn();
            int row = e.getFirstRow();
            
            if (column == 5 || column == 6) {
                // Recalculate row total when Số lượng or Đơn giá changes
                recalculateRowTotal(model, row);
            }
            
            if (column == 4 || column == 5 || column == 6 || column == 7) {
                // Update total when any editable column changes
                updateTotal();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tableInstance);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Total panel
        JPanel totalPanel = new JPanel(new GridBagLayout());
        totalPanel.setBorder(BorderFactory.createTitledBorder("Tổng cộng"));
        totalPanel.setBackground(new Color(245, 247, 250));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        JLabel totalLabelText = new JLabel("Tổng cộng: ");
        totalLabelText.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        totalPanel.add(totalLabelText, gbc);
        
        JLabel totalValueLabel = new JLabel("0 đ");
        totalValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalValueLabel.setForeground(new Color(52, 73, 94));
        gbc.gridx = 1;
        totalPanel.add(totalValueLabel, gbc);
        
        JLabel wordsLabelText = new JLabel("Bằng chữ: ");
        wordsLabelText.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 1;
        totalPanel.add(wordsLabelText, gbc);
        
        JLabel wordsValueLabel = new JLabel("không đồng");
        wordsValueLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        wordsValueLabel.setForeground(new Color(100, 100, 100));
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        totalPanel.add(wordsValueLabel, gbc);
        
        panel.add(totalPanel, BorderLayout.SOUTH);
        
        return new Object[]{panel, tableInstance, totalValueLabel, wordsValueLabel};
    }
    
    public void loadProducts(String region, List<Product> products) {
        currentRegion = region;
        
        // Clear existing data
        tableModel.setRowCount(0);
        
        // Add products
        for (Product product : products) {
            Object[] row = {
                product.getStt(),
                product.getProductName(),
                product.getOrigin(),
                product.getSpecification(),
                product.getUnit(),
                product.getQuantity(),
                product.getUnitPrice(),
                product.getTotalPrice()
            };
            tableModel.addRow(row);
        }
        
        updateTotal();
    }
    
    private void recalculateRowTotal(DefaultTableModel model, int row) {
        try {
            Object quantityObj = model.getValueAt(row, 5);
            Object unitPriceObj = model.getValueAt(row, 6);
            
            int quantity = 0;
            double unitPrice = 0.0;
            
            if (quantityObj != null) {
                if (quantityObj instanceof Number) {
                    quantity = ((Number) quantityObj).intValue();
                } else if (quantityObj instanceof String && !((String) quantityObj).trim().isEmpty()) {
                    quantity = Integer.parseInt(((String) quantityObj).trim());
                }
            }
            
            if (unitPriceObj != null) {
                if (unitPriceObj instanceof Number) {
                    unitPrice = ((Number) unitPriceObj).doubleValue();
                } else if (unitPriceObj instanceof String && !((String) unitPriceObj).trim().isEmpty()) {
                    // Remove formatting and parse
                    String priceStr = ((String) unitPriceObj).replaceAll("[^0-9.]", "");
                    unitPrice = Double.parseDouble(priceStr);
                }
            }
            
            double total = quantity * unitPrice;
            model.setValueAt(total, row, 7);
        } catch (Exception ex) {
            // Ignore parsing errors
        }
    }
    
    public void updateTotal() {
        double total = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Object totalObj = tableModel.getValueAt(i, 7);
            if (totalObj != null) {
                double rowTotal = 0.0;
                if (totalObj instanceof Number) {
                    rowTotal = ((Number) totalObj).doubleValue();
                } else if (totalObj instanceof String && !((String) totalObj).trim().isEmpty()) {
                    String totalStr = ((String) totalObj).replaceAll("[^0-9.]", "");
                    if (!totalStr.isEmpty()) {
                        rowTotal = Double.parseDouble(totalStr);
                    }
                }
                total += rowTotal;
            }
        }
        
        totalLabel.setText(numberFormat.format(total) + " đ");
        totalWordsLabel.setText(convertToWords(total));
    }
    
    private String convertToWords(double amount) {
        // Use NumberToWords utility
        return util.NumberToWords.convertMoney(amount);
    }
    
    public JTable getTable() {
        return table;
    }
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
    
    public String getCurrentRegion() {
        return currentRegion;
    }
    
    // Custom renderer for multi-line text (Hàng hóa cung cấp, Xuất xứ)
    private static class MultiLineTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            JTextArea textArea = new JTextArea();
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setOpaque(true);
            
            if (value != null) {
                textArea.setText(value.toString());
            }
            
            // Set colors based on selection
            if (isSelected) {
                textArea.setBackground(table.getSelectionBackground());
                textArea.setForeground(table.getSelectionForeground());
            } else {
                textArea.setBackground(table.getBackground());
                textArea.setForeground(table.getForeground());
            }
            
            // Set border
            textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            // Calculate preferred height based on number of lines
            int lineCount = textArea.getLineCount();
            int preferredHeight = Math.max(60, lineCount * 20 + 10); // Minimum 60, +20 per line
            
            // Set preferred size
            textArea.setSize(table.getColumnModel().getColumn(column).getWidth(), preferredHeight);
            
            return textArea;
        }
    }
    
    // Custom renderer for currency
    private static class CurrencyRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof Number) {
                ((JLabel) c).setText(numberFormat.format(value) + " đ");
                ((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
            }
            return c;
        }
    }
    
    // Custom editor for number cells
    private static class NumberCellEditor extends DefaultCellEditor {
        public NumberCellEditor() {
            super(new JTextField());
            ((JTextField) getComponent()).setHorizontalAlignment(SwingConstants.RIGHT);
        }
        
        @Override
        public Object getCellEditorValue() {
            String text = ((JTextField) getComponent()).getText().trim();
            if (text.isEmpty()) {
                return 0;
            }
            // Remove formatting characters
            text = text.replaceAll("[^0-9.]", "");
            if (text.isEmpty()) {
                return 0;
            }
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            JTextField textField = (JTextField) super.getTableCellEditorComponent(
                table, value, isSelected, row, column);
            if (value instanceof Number) {
                textField.setText(String.valueOf(value));
            } else {
                textField.setText("");
            }
            return textField;
        }
    }
}

