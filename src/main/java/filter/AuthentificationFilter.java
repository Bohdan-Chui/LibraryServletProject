package filter;

import dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthentificationFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpSession session = request.getSession();

            String path = request.getRequestURI();
            String contextPath = request.getContextPath();
            path = path.replaceAll(".*" + contextPath + "/", "");

            if (session == null || session.getAttribute("role") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
            if(path.contains("reader") && !session.getAttribute("role").equals("reader")){
                System.out.println("reader");
                request.setAttribute("errorMessage" , "access denied");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }
            if(path.contains("librarian") && !session.getAttribute("role").equals("librarian")){
                System.out.println("libraryasn");
                request.setAttribute("errorMessage" , "access denied");
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            }

            if(path.contains("admin") && !session.getAttribute("role").equals("admin")){
                System.out.println(path.contains("admin") + "role"+ !session.getAttribute("role").equals("admin") );
            request.setAttribute("errorMessage" , "access denied");
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);

            }


            if(UserDao.isBlocked((String) session.getAttribute("email"))){
                response.getWriter().println("<script type='text/javascript'>alert('User Blocked');" +
                        "location='" + request.getContextPath() + "/" + "'</script>");
                session.invalidate();
                return;
            }

            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }

}
