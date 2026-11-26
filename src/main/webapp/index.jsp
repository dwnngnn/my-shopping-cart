<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Music Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1><a class="site-name" href="${pageContext.request.contextPath}/product">Music</a></h1>
        <c:if test="${not empty cookie.userEmail}">
            <p>User email: <c:out value="${cookie.userEmail.value}" /></p>
        </c:if>
        <nav class="page-links">
            <a href="${pageContext.request.contextPath}/register">Register</a>
            <a href="${pageContext.request.contextPath}/downloads">Downloads</a>
        </nav>
    </header>
    <main>
        <section class="product-list">
            <h2>Bùi Trường Linh</h2>
            <table>
                <thead>
                    <tr>
                        <th class="description-col">Track</th>
                        <th class="price-col">Price</th>
                        <th class="action-col">Cart</th>
                        <th class="action-col">Download</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.description}</td>
                            <td class="price-col">
                                <fmt:formatNumber value="${product.price}" type="currency"
                                                  currencySymbol="$" minFractionDigits="2"
                                                  maxFractionDigits="2" />
                            </td>
                            <td class="action-col">
                                <form action="${pageContext.request.contextPath}/cart" method="post">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="description" value="${product.description}">
                                    <input type="hidden" name="quantity" value="1">
                                    <input type="submit" value="Add to Cart">
                                </form>
                            </td>
                            <td class="action-col">
                                <a href="${pageContext.request.contextPath}/download?productCode=${product.code}">Download</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
    </main>
    <footer>
        <p>&copy; 2026 Music. All rights reserved.</p>
    </footer>
</body>
</html>
