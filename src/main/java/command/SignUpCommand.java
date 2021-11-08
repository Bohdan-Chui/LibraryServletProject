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

public class SignUpCommand implements Command {
    private static final Logger log = Logger.getLogger(SignUpCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("signUpConnamd start");
        String errorMessage;
        HttpSession session = request.getSession();
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
            log.info("one field Empty");
            return forward;
        }

        user.setPatronymic(patronymic);
        user.setFirstname(firstname);
        user.setSecondname(secondname);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("reader");
        UserDao.registerUser(user);
        log.info("user added: "+ user);

        session.setAttribute("patronymic", user.getPatronymic());
        session.setAttribute("firstname", user.getFirstname());
        session.setAttribute("secondname", user.getSecondname());
        session.setAttribute("userid", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole());
        log.info("session Attribute setted");

        forward ="redirect:" + request.getContextPath() + Actions.MAIN_PAGE;

        return forward;
    }
}

