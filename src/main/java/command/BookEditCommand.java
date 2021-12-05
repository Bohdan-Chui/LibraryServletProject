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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookEditCommand implements  Command {
    private static final Logger log = Logger.getLogger(BookEditCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.info("BookEditCommand start");
            String errorMessage;
            String forward = Actions.ERROR_PAGE;
            Book book = new Book();

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String sDate = request.getParameter("publishedTime");
            int count = Integer.parseInt(request.getParameter("count"));

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);


            if (name == null || author == null || publisher == null || date == null ||
                    name.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
                errorMessage = "something empty cannot be empty";
                request.setAttribute("errorMessage", errorMessage);
                log.info("Null field in BookEditCommand");
                return forward;
            }
            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setPublishedTime(date);
            book.setCount(count);
            BookDao.editBook(book);
            log.info("book edited");

            forward = "redirect:" + request.getContextPath() + Actions.ADMIN_CATALOG;
            return forward;

        } catch (ParseException e) {
            request.setAttribute("errorMessage", "parse Exeption");
            request.getRequestDispatcher(Actions.ERROR_PAGE).forward(request, response);
            return null;
        }
    }
}
