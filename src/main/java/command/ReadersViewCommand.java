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

public class ReadersViewCommand implements Command {
    private static final Logger log = Logger.getLogger(ReadersViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("ReadersViewCommand");
        HttpSession session = request.getSession();
        List<User> list= UserDao.getUserListFromRole("reader");
        session.setAttribute("list", list);
        return Actions.ADMIN_READERS_JSP;
    }

}
