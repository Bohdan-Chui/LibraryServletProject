package command;

import dao.BookDao;
import dao.UserDao;
import org.apache.log4j.Logger;
import util.Actions;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class UserBlockUnblockCommand implements Command{
    private static final Logger log = Logger.getLogger(UserBlockUnblockCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("BlockUnblockCommand");
        HttpSession session = request.getSession();
        String email = request.getParameter("email");

        String block = request.getParameter("block");
        log.info(email +  ' '+ block);
        if(block.equals("block")){
            UserDao.changeBlockStatus(email,true);
        }else {
            UserDao.changeBlockStatus(email,false);
        }

        return "redirect:" + request.getContextPath() + Actions.ADMIN_USERS;
    }
}