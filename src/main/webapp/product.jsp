<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CD Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1>CD List</h1>
    </header>
        <main>
        <section class="product-list">
            <table>
                <thead>
                    <tr>
                        <th class="description-col">Description</th>
                        <th class="price-col">Price</th>
                        <th class="action-col"></th> 
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr> 
                            <td>${product.description}</td>
                            <td class="price-col">
                                <fmt:formatNumber value="${product.price}" 
                                                  type="currency" 
                                                  currencySymbol="$" 
                                                  minFractionDigits="2" 
                                                  maxFractionDigits="2" />
                            </td>
                            <td class="action-col">
                                <form action="cart" method="post" style="margin:0;">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="description" value="${product.description}">
                                    <input type="hidden" name="quantity" value="1">
                                    <input type="submit" value="Add to Cart">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
    <footer>
        <p>&copy; 2026 CD Store. All rights reserved.</p>
    </footer>
</body>
</html>