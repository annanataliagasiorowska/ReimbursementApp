package com.recruitment;

import org.mockito.Mockito;

public class ReimbursementServiceTest {
    ExpensesConfig expensesConfig = Mockito.mock(ExpensesConfig.class);
    ProjectRepository projectRepository = Mockito.mock(ProjectRepository.class);

    ReimbursementService reimbursementService = new ReimbursementService(expensesConfig, projectRepository);


}
