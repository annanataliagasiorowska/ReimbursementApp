package com.recruitment.servlet;

import com.recruitment.ProjectFactory;
import com.recruitment.model.Receipt;
import com.recruitment.model.ReceiptKind;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
                        "<h1 align = \"center\">Add receipts</h1>\n" +
                        "<form method=\"POST\">" +
                        "<b>Choose receipt kind:</b>\n" +
                        "<select id=\"receipt_kind\" name=\"receipt_kind\"><br>" +
                        "<ul>\n"
                        );
        receiptsPage.println();
        for (ReceiptKind receiptKind : projectFactory.getExpensesConfig().getReceiptKindSet()
        ) {
            receiptsPage.println("<option value=\"" +
                    receiptKind.getName() + "\" name=\"receipt_kind\">" +
                    receiptKind.getName() + "</option>");

        }
        receiptsPage.println("</select><br>" +
                        "<b>Receipt date</b>:\n" +
                        "<input name=\"receipt_date\"></input><br>\n" +
                        "<b>Receipt amount</b>:\n" +
                        "<input name=\"receipt_amount\"></input><br>\n" +
                        "<b>Description</b>:\n" +
                        "<input name=\"description\"></input><br>\n" +
                        "<input type=\"submit\"></input></form>"
                        );

        receiptsPage.println(
                        "</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter reimbursementPage = resp.getWriter();
        String title = "R page";

        String[] pathInfoParts = req.getPathInfo().split("/");
        long id = 0;
        if (pathInfoParts.length == 2) {
            try {
                id = Long.parseLong(pathInfoParts[1]);
            } catch (NumberFormatException e) {
                // Handle the case when the parameter is not a valid integer
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid ID parameter");
            }
        } else {
            // Handle the case when the URL format is not as expected
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL format");
        }
        String strReceiptKind = req.getParameter("receipt_kind");
        String strReceiptDate = req.getParameter("receipt_date");
        String strReceiptAmount = req.getParameter("receipt_amount");
        String description = req.getParameter("description");

        List<String> errors = new ArrayList<>();
        ReceiptKind receiptKind = null;
        LocalDate receiptDate = null;
        Double receiptAmount = 0.0;

        if (projectFactory.getExpensesConfig().getReceiptKindSet().stream()
                .anyMatch(receiptKind1 -> receiptKind1.getName().equalsIgnoreCase(strReceiptKind))) {
            try {
                receiptKind = new ReceiptKind(strReceiptKind);
            } catch (IllegalArgumentException exception) {
                errors.add("Choose from the given list");
            }
        }

        if (strReceiptDate != null && !strReceiptDate.isEmpty()) {
            try {
                receiptDate = LocalDate.parse(strReceiptDate, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException exception) {
                errors.add("Date must be in format \"YYYY-MM-DD\".");
            }
        }

        if (strReceiptAmount != null && !strReceiptAmount.isEmpty()) {
            try {
                receiptAmount = Double.parseDouble(strReceiptAmount);
            } catch (NumberFormatException exception) {
                errors.add("Receipt amount must be double.");
            }
        }

        Receipt receipt = new Receipt(projectFactory.getReimbursementService().findReimbursement(id), receiptDate, receiptKind, receiptAmount, description);

        reimbursementPage.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n");
        if (errors.isEmpty() && projectFactory.getReimbursementService().addReceipt(receipt, id)) {
            reimbursementPage.println(
                    "<p>Reimbursement data: " +
                            projectFactory.getProjectRepository().findReimbursement(id).toString() +
                            "</p>");
        } else if (!errors.isEmpty()) {
            reimbursementPage.println(
                    "<p>Errors:\n" +
                            "<ul>\n");
            for (String error : errors
            ) {
                reimbursementPage.println("<li>" + error + "\n");
            }
            reimbursementPage.println("</ul>\n");
        } else {
            reimbursementPage.println(
                    "<p>Receipt date must be within reimbursement date.</p>");
        }
        reimbursementPage.println("</body></html>");
    }


}
