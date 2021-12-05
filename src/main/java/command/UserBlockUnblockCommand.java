package command;

import dao.UserDao;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserBlockUnblockCommand implements Command{
    private static final Logger log = Logger.getLogger(UserBlockUnblockCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("BlockUnblockCommand");
        String email = request.getParameter("email");

        String block = request.getParameter("block");
        log.info(email +  ' '+ block);
        UserDao.changeBlockStatus(email, block.equals("block"));

        return "redirect:" + request.getContextPath() + Actions.ADMIN_USERS;
    }
}