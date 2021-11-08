package command;

import dao.BookDao;
import dao.CardDao;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookReturnCommand implements Command{
    private static final Logger log = Logger.getLogger(BookReturnCommand.class);

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("BookReturnCommand");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        log.info("bookid = "+ bookId);
        int id = Integer.parseInt(request.getParameter("id"));
        CardDao.deleteCard(id);
        BookDao.changeCount(bookId,BookDao.selectBookById(bookId).getCount()+1);
        log.info("Successful");
        return "redirect:" + request.getContextPath() + Actions.READER_CABINET;

    }
}
