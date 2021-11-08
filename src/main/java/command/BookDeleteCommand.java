package command;

import dao.BookDao;

import org.apache.log4j.Logger;
import util.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookDeleteCommand implements Command{
    private static final Logger log = Logger.getLogger(BookDeleteCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        BookDao.deleteBookById(id);
        log.info("book deleted");
        return "redirect:" + request.getContextPath() + Actions.ADMIN_CATALOG;
    }
}