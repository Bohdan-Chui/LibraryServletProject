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

public class CatalogViewCommand implements Command {
    private static final Logger log = Logger.getLogger(CatalogViewCommand.class);
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("CatalogViewCommand");
        HttpSession session = request.getSession();
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            log.info("page" + page);
        }
        List<Book> list = null;
        String publisher = request.getParameter("publisher");
        String name = request.getParameter("name");
        String sortOption = request.getParameter("options");
        log.info("sortOption: "+ sortOption +"publisher: " + publisher + "name: "+ name);

        int numberOfPages;


        if (sortOption != null && !sortOption.equals("")) {
            switch (sortOption) {
                case "SortByName":
                    list = BookDao.selectAllBooksSortedByNameWithLimit(page, 9);
                    break;
                case "SortByAuthor":
                    list = BookDao.selectAllBooksSortedByAuthorWithLimit(page, 9);
                    break;
                case "SortByPublisher":
                    list = BookDao.selectAllBooksSortedByPublisherWitLimit(page, 9);
                    break;
                case "SortByDate":
                    list = BookDao.selectAllBooksSortedByPublishedTimeWithLimit(page, 9);
                    break;
            }
            int numberOfRecords = BookDao.selectAllBooks().size();
            numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / 9);
        } else {
            if(publisher!=null && !publisher.equals("")){

                list = BookDao.findBookByPublisherWithLimit(publisher, page, 9);
                int numberOfRecords = BookDao.findBookByPublisher(publisher).size();
                numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / 9);

            }else{
                if(name!=null && !name.equals("")){
                    list = BookDao.findBookByNameWithLimit(name, page, 9);
                    int numberOfRecords = BookDao.findBookByName(name).size();
                    numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / 9);
                }else {
                    list = BookDao.selectAllBooksWithLimit(page, 9);
                    int numberOfRecords = BookDao.selectAllBooks().size();
                    numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / 9);

                }

            }
        }





        int numberOfRecords = BookDao.selectAllBooks().size();
        log.info(numberOfRecords);

        session.setAttribute("list", list);
        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", page);

        String role = (String) session.getAttribute("role");

        if (role.equals("reader")){
            return Actions.READER_CATALOG_JSP;
        }
        if (role.equals("librarian")){
            return Actions.LIBRARIAN_CATALOG_JSP;
        }
        if (role.equals("admin")){
            return Actions.ADMIN_CATALOG_JSP;
        }
        System.out.println("catalog command return null");
        return null;

    }
}
