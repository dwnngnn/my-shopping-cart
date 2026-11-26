package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;

@WebServlet("/downloads")
public class DownloadsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (!hasUserEmail(cookies)) {
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }

        HttpSession session = request.getSession(false);
        Product selectedProduct = session == null
                ? null : (Product) session.getAttribute("product");
        String productCode = request.getParameter("productCode");
        if (productCode != null) {
            if (selectedProduct == null || !productCode.equals(selectedProduct.getCode())) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product selection not found.");
                return;
            }
            request.setAttribute("selectedProduct", selectedProduct);
        }
        request.setAttribute("products", getServletContext().getAttribute("products"));
        request.getRequestDispatcher("/downloads.jsp").forward(request, response);
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
}
