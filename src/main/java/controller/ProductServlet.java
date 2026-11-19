
package controller;

import model.Product;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private List<Product> products;

    @Override
    public void init() throws ServletException {

        products = new ArrayList<>();

        InputStream input = getClass().getClassLoader()
                .getResourceAsStream("data/item.txt");

        if (input == null) {
            throw new ServletException("Không tìm thấy item.txt");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|", 2);

                if (parts.length == 2) {
                    String description = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());

                    products.add(new Product(description, price));
                }
            }

        } catch (IOException | NumberFormatException e) {
            throw new ServletException("Lỗi đọc dữ liệu sản phẩm", e);
        }

        getServletContext().setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("products", products);

        request.getRequestDispatcher("/product.jsp")
               .forward(request, response);
    }
}
