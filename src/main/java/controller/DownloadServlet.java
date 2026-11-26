package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;
import model.ProductIO;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    private static final int THREE_YEARS_IN_SECONDS = 3 * 365 * 24 * 60 * 60;
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        registerUser(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            request.setAttribute("error", "Please enter a valid email address.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        Cookie emailCookie = new Cookie("userEmail", email.trim());
        emailCookie.setMaxAge(THREE_YEARS_IN_SECONDS);
        emailCookie.setPath(cookiePath(request));
        response.addCookie(emailCookie);

        HttpSession session = request.getSession(false);
        Product product = session == null ? null : (Product) session.getAttribute("product");
        String downloadUrl = request.getContextPath() + "/downloads";
        if (product != null) {
            downloadUrl += "?productCode=" + URLEncoder.encode(product.getCode(), "UTF-8");
        }
        response.sendRedirect(downloadUrl);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        checkUser(request, response);
    }

    private void checkUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = getServletContext();
        String productCode = request.getParameter("productCode");
        String productPath = sc.getRealPath("WEB-INF/products.txt");
        if (productPath == null) {
            throw new ServletException("The product file path is unavailable.");
        }

        Product product = ProductIO.getProduct(productCode, productPath);
        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("product", product);
        if (!hasUserEmail(request.getCookies())) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        response.sendRedirect(downloadUrl(request, product));
    }

    private boolean hasUserEmail(Cookie[] cookies) {
        if (cookies == null) {
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("userEmail".equals(cookie.getName()) && !cookie.getValue().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private String downloadUrl(HttpServletRequest request, Product product) throws IOException {
        String productCode = URLEncoder.encode(product.getCode(), "UTF-8");
        return request.getContextPath() + "/downloads?productCode=" + productCode;
    }

    private String cookiePath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        return contextPath.isEmpty() ? "/" : contextPath + "/";
    }
}
