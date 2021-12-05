package command;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersViewCommand implements Command {
    private static final Logger log = Logger.getLogger(UsersViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("UsersViewCommand");
        List<User> list= UserDao.getUserListFromRole("reader");
        request.setAttribute("list", list);
        return Actions.LIBRARIAN_USERS_JSP;
    }
}
