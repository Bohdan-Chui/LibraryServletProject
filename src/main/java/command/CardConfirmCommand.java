package command;

import dao.CardDao;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CardConfirmCommand implements Command{
    private static final Logger log = Logger.getLogger(CardConfirmCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CardDao.confirmCardById(id);
        log.info("card confirmed");

        return "redirect:" + request.getContextPath() + Actions.LIBRARIAN_ORDERS;
    }
}
