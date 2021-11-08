package command;

import dao.BookDao;
import dao.CardDao;
import org.apache.log4j.Logger;
import util.Actions;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Date;

public class BookOrderCommand implements Command{
    private static final Logger log = Logger.getLogger(BookOrderCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("bookOrderCommand start");
        HttpSession session = request.getSession();
        int userId = (int)session.getAttribute("userid");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int count = Integer.parseInt(request.getParameter("count"));
        String place = request.getParameter("place");
        Date returnDate = new Date();
        returnDate.getTime();
        if(place != null && place.equals("home")){
            returnDate.setMonth(returnDate.getMonth()+1);
        } else if(place != null && place.equals("library")){
            returnDate.setDate(returnDate.getDate()+1);
        } else {
            log.info("place error");
        }


        if(CardDao.orderBook(bookId, userId, place, returnDate) !=0 ){
            log.info("book added to card");
            BookDao.changeCount(bookId, count-1);
            log.info("count changed");

        }
        else {
            log.info("book didnt add to card");
        }
        return "redirect:" + request.getContextPath() + Actions.CATALOG;
    }
}
