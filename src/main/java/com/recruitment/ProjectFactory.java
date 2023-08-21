package com.recruitment;

public class ProjectFactory {

    public static ProjectFactory INSTANCE = new ProjectFactory();

    private ExpensesConfig expensesConfig;

    private ProjectRepository projectRepository;

    private ReimbursementService reimbursementService;

    public ExpensesConfig getExpensesConfig() {
        if (expensesConfig == null)
            expensesConfig = new ExpensesConfig();
        return expensesConfig;
    }

    public ProjectRepository getProjectRepository() {
        if (projectRepository == null)
            projectRepository = new ProjectRepository();
        return projectRepository;
    }

    public ReimbursementService getReimbursementService() {
        if (reimbursementService == null)
            reimbursementService = new ReimbursementService(getExpensesConfig(), getProjectRepository());
        return reimbursementService;
    }
}
