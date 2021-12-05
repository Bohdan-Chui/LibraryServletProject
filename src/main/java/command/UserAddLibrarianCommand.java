package command;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAddLibrarianCommand implements Command {
    private static final Logger log = Logger.getLogger(UserAddLibrarianCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("userAddLibrarianCommand");
        String errorMessage;
        String forward = Actions.ERROR_PAGE;
        User user =new User();

        String patronymic = request.getParameter("patronymic");
        String firstname = request.getParameter("firstname");
        String secondname = request.getParameter("secondname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (patronymic == null || firstname == null || secondname == null || email == null || password == null||
                patronymic.isEmpty() || firstname.isEmpty() || secondname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            errorMessage = "something empty cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.info("field empty");
            return forward;
        }
        if(UserDao.isRegistered(email)){
            response.getWriter().println("<script type='text/javascript'>alert('email already used');" +
                    "location='" + request.getContextPath() + Actions.ADMIN_WORKERS + "'</script>");
            return null;
        }

        user.setPatronymic(patronymic);
        user.setFirstname(firstname);
        user.setSecondname(secondname);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("librarian");
        UserDao.registerUser(user);
        log.info("librarian registred");

        forward ="redirect:" + request.getContextPath() + Actions.ADMIN_WORKERS;
        return forward;
    }
}

