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
import java.util.List;

public class BookAddCommand implements Command {
    private static final Logger log = Logger.getLogger(BookAddCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.info("bookAddCommand start");
            String errorMessage;
            HttpSession session = request.getSession();
            String forward = Actions.ERROR_PAGE;
            Book book = new Book();

            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publisher = request.getParameter("publisher");
            String sDate = request.getParameter("publishedTime");
            int count = Integer.parseInt(request.getParameter("count"));
            System.out.println(sDate);

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);


            if (name == null || author == null || publisher == null || date == null ||
                    name.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
                errorMessage = "something empty cannot be empty";
                request.setAttribute("errorMessage", errorMessage);
                log.info("field empty in bookAddCommnad");
                return forward;
            }


            book.setName(name);
            book.setAuthor(author);
            book.setPublisher(publisher);
            book.setPublishedTime(date);
            book.setCount(count);

            if (isRegistered(book)) {
                response.getWriter().println("<script type='text/javascript'>alert('book already used');" +
                        "location='" + request.getContextPath() + Actions.ADMIN_CATALOG + "'</script>");
                log.info("Book already registred");
                return null;
            }
            BookDao.addBook(book);
            log.info("book added");
            forward = "redirect:" + request.getContextPath() + Actions.ADMIN_CATALOG;
            return forward;

        } catch (ParseException e) {
            request.setAttribute("errorMessage", "parse Exeption");
            request.getRequestDispatcher(Actions.ERROR_PAGE).forward(request, response);
            return null;
        }
    }
    private boolean isRegistered(Book book){
        List<Book> list= BookDao.selectBooks();
       return list.contains(book);
    }
}
