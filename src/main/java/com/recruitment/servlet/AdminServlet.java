package com.recruitment.servlet;

import com.recruitment.ProjectFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "adminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    private ProjectFactory projectFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        projectFactory = ProjectFactory.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter adminPage = resp.getWriter();
        String title = "Admin page";

        adminPage.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">Set and edit expenses and limits</h1>\n" +
                        "<form method=\"POST\">" +
                        "<b>Daily allowance:</b>\n" +
                        "<input name=\"daily_allowance\"></input><br>\n" +
                        "<b>Refund per mile:</b>\n" +
                        "<input name=\"refund_per_mile\"></input><br>\n" +
                        "<b>Reimbursement limit:</b>\n" +
                        "<input name=\"reimbursement_limit\"></input><br>\n" +
                        "<b>Distance limit:</b>\n" +
                        "<input name=\"distance_limit\"></input><br>\n" +
                        "<input type=\"submit\"></input></form>");


        adminPage.println("</ul></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter adminPage = resp.getWriter();
        String title = "Admin page";

        String strDailyAllowance = req.getParameter("daily_allowance");
        String strRefundPerMile = req.getParameter("refund_per_mile");
        String strReimbursementLimit = req.getParameter("reimbursement_limit");
        String strDistanceLimit = req.getParameter("distance_limit");

        List<String> errors = new ArrayList<>();
        double dailyAllowance = ProjectFactory.INSTANCE.getExpensesConfig().getDailyAllowance();
        double refundPerMile = ProjectFactory.INSTANCE.getExpensesConfig().getCarMileage();
        double reimbursementLimit = ProjectFactory.INSTANCE.getExpensesConfig().getReimbursementLimit();
        int distanceLimit = ProjectFactory.INSTANCE.getExpensesConfig().getDistanceLimit();
        if (strDailyAllowance != null && !strDailyAllowance.isEmpty()) {
            try {
                dailyAllowance = Double.parseDouble(strDailyAllowance);
                ProjectFactory.INSTANCE.getExpensesConfig().setDailyAllowance(dailyAllowance);
            } catch (NumberFormatException exception) {
                errors.add("Daily allowance must be double.");
            }
        }
        if (strRefundPerMile != null && !strRefundPerMile.isEmpty()) {
            try {
                refundPerMile = Double.parseDouble(strRefundPerMile);
                ProjectFactory.INSTANCE.getExpensesConfig().setCarMileage(refundPerMile);
            } catch (NumberFormatException exception) {
                errors.add("Refund per mile must be float.");
            }
        }
        if (strReimbursementLimit != null && !strReimbursementLimit.isEmpty()) {
            try {
                reimbursementLimit = Double.parseDouble(strReimbursementLimit);
                ProjectFactory.INSTANCE.getExpensesConfig().setReimbursementLimit(reimbursementLimit);
            } catch (NumberFormatException exception) {
                errors.add("Reimbursement limit must be double.");
            }
        }
        if (strDistanceLimit != null && !strDistanceLimit.isEmpty()) {
            try {
                distanceLimit = Integer.parseInt(strDistanceLimit);
                ProjectFactory.INSTANCE.getExpensesConfig().setDistanceLimit(distanceLimit);
            } catch (NumberFormatException exception) {
                errors.add("Distance limit must be integer.");
            }
        }



        adminPage.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n");
        if (errors.isEmpty()) {
            adminPage.println(
                "<p>Limits:\n" +
                        "<ul>\n" +
                    "<li>Daily allowance: " + dailyAllowance +"\n" +
                    "<li>Refund per mile: " + refundPerMile +"\n" +
                    "<li>Reimbursement limit: "+ reimbursementLimit +"\n" +
                    "<li>Distance limit: " + distanceLimit +"\n");

        } else {
            adminPage.println(
                    "<p>Errors:\n" +
                            "<ul>\n");
            for (String error: errors
            ) {
                adminPage.println("<li>"+ error +"\n");
            }
            adminPage.println("</ul>\n");
        }
        adminPage.println("</body></html>");
    }

}
