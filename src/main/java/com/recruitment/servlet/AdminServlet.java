package com.recruitment.servlet;

import com.recruitment.ProjectFactory;
import com.recruitment.ReimbursementService;
import com.recruitment.model.Reimbursement;
import com.recruitment.model.User;

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
        ReimbursementService reimbursementService = projectFactory.getReimbursementService();
        String strDailyAllowance = req.getParameter("daily_allowance");
        String strRefundPerMile = req.getParameter("refund_per_mile");
        String strReimbursementLimit = req.getParameter("reimbursement_limit");
        String strDistanceLimit = req.getParameter("distance_limit");

        List<String> errors = new ArrayList<>();
        int dailyAllowance = 0;
        double refundPerMile = 0.0;
        int reimbursementLimit = 0;
        int distanceLimit = 0;
        try {
            dailyAllowance = Integer.parseInt(strDailyAllowance);
        } catch (NumberFormatException exception) {
            errors.add("Daily allowance must be integer.");
        }
        try {
            refundPerMile = Double.parseDouble(strRefundPerMile);
        } catch (NumberFormatException exception) {
            errors.add("Refund per mile must be float.");
        }
        try {
            carMileage = Integer.parseInt(strCarMileage);
        } catch (NumberFormatException exception) {
            errors.add("Car mileage must be integer.");
        }
        PrintWriter reimbursementPage = resp.getWriter();
        String title = "Reimbursement page";

        reimbursementPage.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n");
        if (errors.isEmpty()) {
            reimbursementService.createReimbursement(new User("Ania"), date, duration, null, carMileage);
        } else {
            reimbursementPage.println(
                    "<p>Errors:\n" +
                            "<ul>\n");
            for (String error: errors
            ) {
                reimbursementPage.println("<li>"+ error +"\n");
            }
            reimbursementPage.println("</ul>\n");
        }
        reimbursementPage.println("</body></html>");
    }

}
