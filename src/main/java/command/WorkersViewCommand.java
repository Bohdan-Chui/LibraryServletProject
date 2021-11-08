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
import java.util.List;

public class WorkersViewCommand implements Command {
    private static final Logger log = Logger.getLogger(WorkersViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("WorkersViewCommand");
        HttpSession session = request.getSession();
        List<User> list= UserDao.getUserListFromRole("librarian");
        session.setAttribute("list", list);
        return Actions.ADMIN_WORKERS_JSP;
    }

}
