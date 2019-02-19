package org.quill.simplewebapp.servlet;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quill.simplewebapp.beans.UserAccount;
import org.quill.simplewebapp.utils.DBUtils;
import org.quill.simplewebapp.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/createUser" })
public class CreateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public CreateUserServlet() {
        super();
    }
 
    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/createUser.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String userName = (String) request.getParameter("userName");
        String gender = (String) request.getParameter("gender");
        String password = (String) request.getParameter("password");
        
        UserAccount user_name = new UserAccount(userName, gender, password);
 
        String errorString = null;
 
        // Product ID is the string literal [a-zA-Z_0-9]
        // with at least 1 character
        //String regex = "\\w+";
 
        //if (userName == null || !userName.matches(regex)) {
        //    errorString = "User Name invalid!";
        //}
 
        if (errorString == null) {
            try {
                DBUtils.insertUser(conn, user_name);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }
 
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("user_account", user_name);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/createUser.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
 
}