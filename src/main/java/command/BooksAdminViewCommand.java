package command;

import dao.BookDao;
import model.Book;
import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BooksAdminViewCommand implements Command {
    private static final Logger log = Logger.getLogger(BooksAdminViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("BookAdminViewCommand start");
        HttpSession session = request.getSession();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Book> list= BookDao.selectBooksWithLimit(page, 9);
        int numberOfRecords = BookDao.selectBooks().size();
        log.info("Number of records: " + numberOfRecords);
        int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / 10);
        log.info("Number of records: " + numberOfRecords);
        session.setAttribute("list", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);
        log.info("finished");
        return Actions.ADMIN_BOOK_JSP;
    }
}
