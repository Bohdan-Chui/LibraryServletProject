package command;

import dao.CardDao;
import model.Card;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CardsViewCommand implements Command{
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Card> list = CardDao.selectAllNotConfirmedCards();
        request.setAttribute("list", list);
        return Actions.LIBRARIAN_ORDERS_JSP;
    }
}
