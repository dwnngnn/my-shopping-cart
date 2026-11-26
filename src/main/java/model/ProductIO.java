package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class ProductIO {

    private ProductIO() {
    }

    public static List<Product> getProducts(String productPath) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(productPath), StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split("\\|", 3);
                if (fields.length != 3) {
                    throw new IOException("Invalid product record on line " + lineNumber + ".");
                }

                String code = fields[0].trim();
                String description = fields[1].trim();
                double price;
                try {
                    price = Double.parseDouble(fields[2].trim());
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid product price on line " + lineNumber + ".", e);
                }
                products.add(new Product(description, price, code));
            }
        }
        return products;
    }

    public static Product getProduct(String productCode, String productPath) throws IOException {
        if (productCode == null || productCode.trim().isEmpty()) {
            return null;
        }

        for (Product product : getProducts(productPath)) {
            if (productCode.equals(product.getCode())) {
                return product;
            }
        }
        return null;
    }
}
