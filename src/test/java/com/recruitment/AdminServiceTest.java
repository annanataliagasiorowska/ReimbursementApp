package com.recruitment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminServiceTest {
    ExpensesConfig expensesConfig = new ExpensesConfig();
    AdminService adminService = new AdminService(expensesConfig);

    @Test
    void checkIfReceiptKindExists_exists_returnTrue() {
        assertTrue(adminService.checkIfReceiptKindExists("Train"));
    }

    @Test
    void checkIfReceiptKindExists_doNotExists_returnFalse() {
        assertFalse(adminService.checkIfReceiptKindExists("Boat"));
    }
    @Test
    void checkIfReceiptKindExists_emptyString_returnFalse() {
        assertFalse(adminService.checkIfReceiptKindExists(""));
    }

    @Test
    void addReceiptKind_properString_returnTrue() {
        adminService.addReceiptKind("Boat");
        assertTrue(adminService.checkIfReceiptKindExists("Boat"));
    }

    @Test
    void deleteReceiptKind_properString_returnFalse() {
        adminService.deleteReceiptKind("Train");
        assertFalse(adminService.checkIfReceiptKindExists("Train"));
    }

}
