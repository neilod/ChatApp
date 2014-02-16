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
 * TIME: 21:13
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("cmd");

        if(command.equals("create")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String screenName = req.getParameter("screenname");

            password = ChatAppContext.getInstance().getPasswordService().encryptPassword(password);
            User user = new User(email, password, screenName);
            int created = 0;
            try {
                created = ChatAppContext.getInstance().getPersistenceService().createUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
                created = 0;
            }
            req.getSession().setAttribute("user", user);
            if (created == 1) {
                resp.sendRedirect("/app/index.html");
            } else {
                resp.sendRedirect("/register.html?oops=true");
            }

        }
    }
}
