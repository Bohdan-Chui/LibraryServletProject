package controller;

import command.Command;
import command.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FrontController extends HttpServlet {
    private static final Logger log = Logger.getLogger(FrontController.class);

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put("login", new LoginCommand());
        commands.put("signup", new SignUpCommand());
        commands.put("logout", new LogOutCommand());
        commands.put("catalog", new CatalogViewCommand());
        commands.put("mainPage", new MainPageCommand());
        commands.put("orderBook", new BookOrderCommand());
        commands.put("returnBook", new BookReturnCommand());
        commands.put("reader/cabinet", new CabinetViewCommand());
        commands.put("admin/workers", new WorkersViewCommand());
        commands.put("admin/addWorker", new UserAddLibrarianCommand());
        commands.put("admin/deleteLibrarian", new UserDeleteCommand());
        commands.put("admin/users", new ReadersViewCommand());
        commands.put("admin/blockUnblockReader", new UserBlockUnblockCommand());
        commands.put("admin/catalog", new BooksAdminViewCommand());
        commands.put("admin/deleteBook", new BookDeleteCommand());
        commands.put("admin/addBook", new BookAddCommand());
        commands.put("admin/editBook", new BookEditCommand());
        commands.put("admin/bookView", new BookAdminEditViewCommand());
        commands.put("librarian/orders", new CardsViewCommand());
        commands.put("librarian/confirm", new CardConfirmCommand());
        commands.put("librarian/delete", new CardDeleteCommand());
        commands.put("librarian/viewCards", new CardsUsersViewCommand());
        commands.put("librarian/users",new UsersViewCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getRequestURI();
        path = path.replaceAll(".*/library/", "");

        Command command = commands.get(path);
        if(command == null){
            req.setAttribute("errorMessage", "the page doesn't exist");
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
            log.info("null command: " + command);
            return;
        }
        try {
            String page = command.process(req, resp);
            if(page != null){
                if (page.contains("redirect:")) {
                    resp.sendRedirect(page.replace("redirect:", ""));
                    log.info("redirect" + page);
                }
                else {
                    req.getRequestDispatcher(page).forward(req, resp);
                    log.info("forward" + page);
                }
            }
        }catch (Exception e){
            req.setAttribute("errorMessage", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
            log.info(e);
        }
    }
}