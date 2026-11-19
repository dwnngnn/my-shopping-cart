package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    // Lấy danh sách kết quả
    public ArrayList<CartItem> getItems() {
        return items;
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getDescription().equals(product.getDescription())) {
                item.setProduct(product);
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public void updateItem(Product product, int quantity){
        for (CartItem item : items) {
            if (item.getProduct().getDescription().equals(product.getDescription())) {
                if (quantity <= 0) {
                    items.remove(item);
                } else {
                    item.setProduct(product);
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().getDescription().equals(product.getDescription()));
    }

    // Xóa toàn bộ giỏ hàng
    public void clearCart() {
        items.clear();
    }

    // Tính tổng giá trị giỏ hàng
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getTotalPrice(item.getQuantity());
        }
        return total;
    }

    // Kiểm tra xem giỏ hàng có rỗng hay không
    public boolean isEmpty() {
        return items.isEmpty();
    } 
}
