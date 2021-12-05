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

public class LoginCommand implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("loginCommand start");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email == null || password == null || email.isEmpty() || password.isEmpty()){
            response.getWriter().println("<script type='text/javascript'>alert('Login/password cannot be empty');" +
                    "location='" + request.getContextPath() + Actions.LOGIN_JSP + "'</script>");
            log.info("Login/password cannot be empty");
            return null;
        }

        HttpSession session = request.getSession();
        User user = UserDao.findUserByEmail(email);
        if(user == null ){
            response.getWriter().println("<script type='text/javascript'>alert('Cannot find user with such login');" +
                    "location='" + request.getContextPath() + Actions.LOGIN_JSP+ "'</script>");
            log.info("Cannot find user with such login");
            return null;
        }
        if(!user.getPassword().equals(password)){
            response.getWriter().println("<script type='text/javascript'>alert('Wrong password');" +
                    "location='" + request.getContextPath() + Actions.LOGIN_JSP + "'</script>");
            log.info("Wrong password");
            return null;
        }


        session.setAttribute("patronymic", user.getPatronymic());
        session.setAttribute("firstname", user.getFirstname());
        session.setAttribute("secondname", user.getSecondname());
        session.setAttribute("userid", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("role", user.getRole());
        log.info("login successfully");
        return "redirect:" + request.getContextPath()+Actions.MAIN_PAGE ;
    }

}

