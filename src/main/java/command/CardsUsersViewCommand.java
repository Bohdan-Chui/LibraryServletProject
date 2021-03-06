package command;

import dao.CardDao;
import model.Card;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CardsUsersViewCommand implements Command{
    private static final Logger log = Logger.getLogger(CardsUsersViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("CardsUsersViewCommand");
        int id = Integer.parseInt(request.getParameter("userId"));
        List<Card> list= CardDao.selectAllUsersCards(id);
        request.setAttribute("list", list);
        log.info("cards selected");
        return Actions.USER_CARDS_JSP;

    }

}
