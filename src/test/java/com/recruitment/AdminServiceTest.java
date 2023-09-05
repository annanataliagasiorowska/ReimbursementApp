package com.recruitment;

import com.recruitment.model.ReceiptKind;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminServiceTest {
    ExpensesConfig expensesConfig = Mockito.mock(ExpensesConfig.class);
    AdminService adminService = new AdminService(expensesConfig);

    @Test
    void checkIfReceiptKindExists_exists_returnTrue() {
        Mockito.when(expensesConfig.getReceiptKindSet()).thenReturn(new HashSet<>(List.of(
                new ReceiptKind("Taxi"),
                new ReceiptKind("Hotel"),
                new ReceiptKind("Plain"),
                new ReceiptKind("Train")
        )));
        assertTrue(adminService.checkIfReceiptKindExists("Train"));
    }

    @Test
    void checkIfReceiptKindExists_doNotExists_returnFalse() {
        Mockito.when(expensesConfig.getReceiptKindSet()).thenReturn(new HashSet<>(List.of(
                new ReceiptKind("Taxi"),
                new ReceiptKind("Hotel"),
                new ReceiptKind("Plain"),
                new ReceiptKind("Train")
        )));
        assertFalse(adminService.checkIfReceiptKindExists("Boat"));
    }
    @Test
    void checkIfReceiptKindExists_emptyString_returnFalse() {
        Mockito.when(expensesConfig.getReceiptKindSet()).thenReturn(new HashSet<>(List.of(
                new ReceiptKind("Taxi"),
                new ReceiptKind("Hotel"),
                new ReceiptKind("Plain"),
                new ReceiptKind("Train")
        )));
        assertFalse(adminService.checkIfReceiptKindExists(""));
    }

}
