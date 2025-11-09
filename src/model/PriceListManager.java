package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager class to handle price lists for Nam and Bắc regions
 */
public class PriceListManager {
    
    /**
     * Get price list for Nam region
     */
    public static List<Product> getNamPriceList() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("1", 
            "Que thử xét nghiệm định tính MET(MAMP)-THC-MOR-MDMA-KET trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd  \n(5.1)\n- Morphine/ Heroin (MOP/OPI)\n- Cần sa (THC)\n- Ma túy đá (MET)\n- Thuốc lắc (ECSTASY/MDMA)\n- Ketamine (KET)\nModel: 75025",
            "Chemtron Biotech \nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 25 Test",
            "Test",
            66800));
        
        products.add(new Product("2",
            "Que thử xét nghiệm định tính MET(MAMP)-THC-MOR-MDMA trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (4.1)\n- Morphine/ Heroin (MOP/OPI)\n- Cần sa (THC)\n- Ma túy đá (MET)\n- Thuốc lắc (ECSTASY/MDMA)\nModel: 74716",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 25 Test",
            "Test",
            60800));
        
        products.add(new Product("3",
            "Que thử xét nghiệm định tính MET(MAMP)-THC-MOR-MDMA-KET-COC trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (6.1)\n- Morphine (MOP)\n- Cần sa (THC)\n- Ma túy đá (MET)\n- Thuốc lắc (ECSTASY/MDMA)\n- Ketamine (KET)\n- Cocaine (COC)\nModel: 76005",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 25 Test",
            "Test",
            70800));
        
        products.add(new Product("4",
            "Que thử xét nghiệm định tính Cỏ Mỹ trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (K2)\nModel: 7021S",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 50 Test",
            "Test",
            22000));
        
        products.add(new Product("5",
            "Que thử xét nghiệm định tính Morphine trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (MOP)\nModel: 7001S",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 50 Test",
            "Test",
            22000));
        
        products.add(new Product("6",
            "Que thử xét nghiệm định tính Methamphetamine trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (MET)\nModel: 7004S",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 50 test",
            "Test",
            22000));
        
        products.add(new Product("7",
            "Que thử xét nghiệm định tính Marijuana (THC) trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (THC)\nModel: 7005S",
            "Healgen – Mỹ/China\nAssuretech – Hà Lan / China",
            "Hộp 100 Test\nHộp 50 Test",
            "Test",
            22000));
        
        products.add(new Product("8",
            "Que thử xét nghiệm định tính Ketamine trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (KET)\nModel: 7016S",
            "Assuretech – Hà Lan / China",
            "Hộp 40 Test",
            "Test",
            29000));
        
        products.add(new Product("9",
            "Que thử xét nghiệm định tính thuốc lắc (MDMA) trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (MDMA)\nModel: 7002S",
            "Assuretech – Hà Lan / China",
            "Hộp 50 Test",
            "Test",
            22000));
        
        return products;
    }
    
    /**
     * Get price list for Bắc region
     */
    public static List<Product> getBacPriceList() {
        List<Product> products = new ArrayList<>();
        
        products.add(new Product("1",
            "Que thử xét nghiệm định tính MET(MAMP)-THC-MOR-MDMA-KET trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (5.1)\n- Morphine/ Heroin (MOP/OPI)\n- Cần sa (THC)\n- Ma túy đá (MET)\n- Thuốc lắc (ECSTASY/MDMA)\n- Ketamine (KET)\nModel: 75025",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 25 Test",
            "Test",
            62000));
        
        products.add(new Product("2",
            "Que thử xét nghiệm định tính MET(MAMP)-THC-MOR-MDMA trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (4.1)\n- Morphine/ Heroin (MOP/OPI)\n- Cần sa (THC)\n- Ma túy đá (MET)\n- Thuốc lắc (ECSTASY/MDMA)\nModel: 74716",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 25 Test",
            "Test",
            53000));
        
        products.add(new Product("3",
            "Que thử xét nghiệm định tính MET(MAMP)-THC-MOR-MDMA-KET-COC trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (6.1)\n- Morphine (MOP)\n- Cần sa (THC)\n- Ma túy đá (MET)\n- Thuốc lắc (ECSTASY/MDMA)\n- Ketamine (KET)\n- Cocaine (COC)\nModel: 76005",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 25 Test",
            "Test",
            69000));
        
        products.add(new Product("4",
            "Que thử xét nghiệm định tính Cỏ Mỹ trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (K2)\nModel: 7021S",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 50 Test",
            "Test",
            24000));
        
        products.add(new Product("5",
            "Que thử xét nghiệm định tính Morphine trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (MOP)\nModel: 7001S",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 50 Test",
            "Test",
            24000));
        
        products.add(new Product("6",
            "Que thử xét nghiệm định tính Methamphetamine trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (MET)\nModel: 7004S",
            "Chemtron Biotech\nThương hiệu: Mỹ\nXuất xứ: Trung Quốc",
            "Hộp 50 test",
            "Test",
            24000));
        
        products.add(new Product("7",
            "Que thử xét nghiệm định tính Marijuana (THC) trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (THC)\nModel: 7005S",
            "Healgen – Mỹ/China\nAssuretech – Hà Lan / China",
            "Hộp 100 Test\nHộp 50 Test",
            "Test",
            24000));
        
        products.add(new Product("8",
            "Que thử xét nghiệm định tính Ketamine trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (KET)\nModel: 7016S",
            "Assuretech – Hà Lan / China",
            "Hộp 40 Test",
            "Test",
            33000));
        
        products.add(new Product("9",
            "Que thử xét nghiệm định tính thuốc lắc (MDMA) trong nước tiểu, HSX: Shanghai Chemtron Biotech Co.,Ltd (MDMA)\nModel: 7002S",
            "Assuretech – Hà Lan / China",
            "Hộp 50 Test",
            "Test",
            24000));
        
        return products;
    }
}

