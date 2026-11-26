
package controller;

import model.Product;
import model.ProductIO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/product", loadOnStartup = 1)
public class ProductServlet extends HttpServlet {

    private List<Product> products;

    @Override
    public void init() throws ServletException {
        String productPath = getServletContext().getRealPath("WEB-INF/products.txt");
        if (productPath == null) {
            throw new ServletException("The product file path is unavailable.");
        }
        try {
            products = ProductIO.getProducts(productPath);
        } catch (IOException e) {
            throw new ServletException("Failed to read product data.", e);
        }

        getServletContext().setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("products", products);

        request.getRequestDispatcher("/index.jsp")
               .forward(request, response);
    }
}
