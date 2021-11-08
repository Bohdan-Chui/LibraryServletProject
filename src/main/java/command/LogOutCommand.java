package command;

import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutCommand implements Command {
    private static final Logger log = Logger.getLogger(LogOutCommand.class);

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        log.info("logout start");

        HttpSession session = request.getSession();

       session.invalidate();

        log.info("logout finished");
        return "redirect:" +request.getContextPath() + Actions.INDEX_JSP;
    }
}

