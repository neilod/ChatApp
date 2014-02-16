package ie.neilod.chat.servlets;

import ie.neilod.chat.ChatAppContext;
import ie.neilod.chat.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * User: neilod
 * Date: 15/02/2014
 * TIME: 20:37
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = null;
        try {
            user = ChatAppContext.getInstance().getPersistenceService().getUser(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean correctPassword = false;
        if(user != null) {
            correctPassword = ChatAppContext.getInstance().getPasswordService().isCorrectPassword(password, user.getPassword());
        }

        if (correctPassword) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/app/index.jsp");
        } else {
            resp.sendRedirect("/login.html?in=false");
        }
    }
}
