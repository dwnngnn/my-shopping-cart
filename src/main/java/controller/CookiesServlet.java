package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookies")
public class CookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("delete".equals(request.getParameter("action"))) {
            String contextPath = request.getContextPath();
            String contextCookiePath = contextPath.isEmpty() ? "/" : contextPath;
            String nestedCookiePath = contextPath.isEmpty() ? "/" : contextPath + "/";
            expireCookie(response, "userEmail", contextCookiePath);
            expireCookie(response, "userEmail", nestedCookiePath);
            expireCookie(response, "emailCookie", contextCookiePath);
            expireCookie(response, "emailCookie", nestedCookiePath);
            response.sendRedirect(request.getContextPath() + "/cookies");
            return;
        }

        request.getRequestDispatcher("/cookies.jsp").forward(request, response);
    }

    private void expireCookie(HttpServletResponse response, String name, String path) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
}
