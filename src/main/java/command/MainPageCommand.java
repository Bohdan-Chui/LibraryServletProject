package command;

import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainPageCommand implements Command {
    private static final Logger log = Logger.getLogger(MainPageCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("mainPageCommand");

            return Actions.MAIN_PAGE_JSP;

    }
}
