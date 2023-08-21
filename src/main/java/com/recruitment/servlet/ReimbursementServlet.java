package com.recruitment.servlet;

import com.recruitment.ProjectFactory;
import com.recruitment.model.ReceiptKind;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="reimbursementServlet", urlPatterns = {"/reimbursement/*"})
public class ReimbursementServlet extends HttpServlet {

    private ProjectFactory projectFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        projectFactory = ProjectFactory.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter receiptsPage = resp.getWriter();
        String title = "Add receipts";

        receiptsPage.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">Add reimbursement</h1>\n" +
                        "<select id=\"receipt_kind\" name=\"receipt_kind\">");
        receiptsPage.println("<h1>Choose receipt kind:</h1>\n" +
                "<ul>\n");
        for (ReceiptKind receiptKind : projectFactory.getExpensesConfig().getReceiptKindSet()
        ) {
            receiptsPage.println("<option value=\"" +
                    receiptKind.getName() + "\" name=\"receipt_kind\">" +
                    receiptKind.getName() + "</option>");

        }
        receiptsPage.println(
                        "</select>" +
                        "</body></html>");
    }


}
