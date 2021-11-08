package command;

import dao.BookDao;
import dao.UserDao;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class UserDeleteCommand implements Command{
    private static final Logger log = Logger.getLogger(UserDeleteCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao.deleteUserById(id);
        log.info("user deleted");
        return "redirect:" + request.getContextPath() + Actions.ADMIN_WORKERS;
    }
}

