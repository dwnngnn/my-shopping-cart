package controller;

import model.*;

import java.io.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{
    
    // Hiển thị giỏ hàng
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // Thao tác của giỏ hàng
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
            for (CartItem item : cart.getItems()) {
                Product product = findProduct(item.getProduct().getDescription());
                if (product != null) {
                    item.setProduct(product);
                }
            }
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
            return;
        }
        if ("shop".equals(action)) {
            response.sendRedirect(request.getContextPath() + "/product");
            return;
        }
        if ("checkout".equals(action)) {
            request.getRequestDispatcher("/checkout").forward(request, response);
            return;
        }

        String description = request.getParameter("description");
        int quantity = 1;
        if ("update".equals(action)) {
            quantity = Integer.parseInt(request.getParameter("quantity"));
        }

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        Product product = findProduct(description);
        if (product == null && ("add".equals(action) || "update".equals(action))) {
            response.sendError(400, "Product not found.");
            return;
        }
        if (product == null) {
            product = new Product(description, 0);
        }

        switch (action) {
            case "add":
                cart.addItem(product, quantity);
                break;
            case "update":
                cart.updateItem(product, quantity);
                break;
            case "remove":
                cart.removeItem(product);
                break;
            case "clear":
                cart.clearCart();
                break;
            default:
                throw new IllegalArgumentException("Hành động không hợp lệ: " + action);
        }
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    @SuppressWarnings("unchecked")
    private Product findProduct(String description) {
        List<Product> products = (List<Product>) getServletContext().getAttribute("products");
        if (products == null || description == null) {
            return null;
        }
        for (Product product : products) {
            if (product.getDescription().equals(description)) {
                return product;
            }
        }
        return null;
    }
}
