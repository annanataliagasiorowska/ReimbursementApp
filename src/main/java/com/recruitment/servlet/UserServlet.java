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

@WebServlet(name="userServlet", urlPatterns = {"/"})
public class UserServlet extends HttpServlet {

    private ProjectFactory projectFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        projectFactory = ProjectFactory.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter reimbursementPage = resp.getWriter();
        String title = "Reimbursement page";

        reimbursementPage.println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">Add reimbursement</h1>\n" +
                        "<form method=\"POST\">" +
                        "<b>Trip date</b>:\n" +
                        "<input name=\"trip_date\"></input><br>\n" +
                        "<b>Duration</b>:\n" +
                        "<input name=\"duration\"></input><br>\n" +
                        "<b>Car mileage</b>:\n" +
                        "<input name=\"car_mileage\"></input><br>\n" +
                        "<button type=\"submit\">Send</button></form>");

        reimbursementPage.println("<h1>User reimbursements</h1>\n" +
                "<ul>\n");
        for (Reimbursement reimbursement : projectFactory.getProjectRepository().getUserReimbursements(new User("Ania"))
             ) {
            reimbursementPage.println("<li><a id=\"link\" href=\"http://localhost:8080/reimbursement/" +
                    reimbursement.getId() + "\">" +
                    reimbursement.getTripDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "</a>\n");
        }
        reimbursementPage.println("</ul></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReimbursementService reimbursementService = projectFactory.getReimbursementService();
        String strDate = req.getParameter("trip_date");
        String strDuration = req.getParameter("duration");
        String strCarMileage = req.getParameter("car_mileage");
        List<String> errors = new ArrayList<>();
        LocalDate date = null;
        int duration = 0;
        int carMileage = 0;
        try {
            date = LocalDate.parse(strDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException exception) {
            errors.add("Date must be in format \"YYYY-MM-DD\".");
        }
        try {
            duration = Integer.parseInt(strDuration);
        } catch (NumberFormatException exception) {
            errors.add("Duration must be integer.");
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
            Reimbursement reimbursement = reimbursementService.createReimbursement(new User("Ania"), date, duration, null, carMileage);
//            reimbursement.setTotalReimbursement(reimbursement.getMileage() * projectFactory.getExpensesConfig().getCarMileage());
            projectFactory.getReimbursementService().sumReimbursement(reimbursement);
            reimbursementPage.println(
                    "<p>Reimbursement data: " +
                            projectFactory.getProjectRepository().findReimbursement(reimbursement.getId()).toString() +
                            "</p>");
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
