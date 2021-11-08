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


public class BookAdminEditViewCommand implements Command {
    private static final Logger log = Logger.getLogger(BookAdminEditViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("BookViewAdminCommand start");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("id = " + id);
        Book book = BookDao.selectBookById(id);
        request.setAttribute("book", book);
        log.info("BookViewAdminCommand End");
        return Actions.ADMIN_EDIT_BOOK_JSP;

    }
}
