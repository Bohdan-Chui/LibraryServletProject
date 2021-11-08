package command;

import dao.BookDao;
import dao.CardDao;
import model.Book;
import model.Card;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CabinetViewCommand implements Command {
    private static final Logger log = Logger.getLogger(CabinetViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("cabinet View command");
        HttpSession session = request.getSession();
        List<Card> list= CardDao.selectAllUsersCards((int)(session.getAttribute("userid")));
        request.setAttribute("list", list);
        log.info("CabonetViewCommand end");

        return Actions.READER_CABINET_JSP;
    }

}
