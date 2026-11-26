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
        <h1>Your Cart</h1>
    </header>
    <main>
        <c:choose>
            <c:when test="${empty sessionScope.cart.items}">
                <section class="empty-cart">
                    <p>Your cart is empty.</p>

                    <form action="${pageContext.request.contextPath}/product" method="get">
                        <input type="submit" value="Continue Shopping" class="btn-secondary">
                    </form>
                </section>
            </c:when>

            <c:otherwise>
                <section class="cart-details">
                    <table>
                        <thead>
                            <tr>
                                <th>Quantity</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Amount</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:set var="grandTotal" value="0"/>
                            <c:forEach var="item" items="${sessionScope.cart.items}">
                                <c:set var="itemAmount" value="${item.product.price * item.quantity}"/>
                                <c:set var="grandTotal" value="${grandTotal + itemAmount}"/>

                                <tr>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/cart" method="post" style="display:inline-flex; gap:5px;">

                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="description" value="${item.product.description}">
                                            <input type="number" name="quantity" value="${item.quantity}" min="1" required class="qty-input">
                                            <input type="submit" value="Update">
                                        </form>
                                    </td>

                                    <td>${item.product.description}</td>

                                    <td>
                                        <fmt:formatNumber
                                            value="${item.product.price}"
                                            type="currency"
                                            currencySymbol="$"
                                            minFractionDigits="2"/>
                                    </td>

                                    <td>
                                        <fmt:formatNumber
                                            value="${itemAmount}"
                                            type="currency"
                                            currencySymbol="$"
                                            minFractionDigits="2"/>
                                    </td>

                                    <td>
                                        <c:url var="removeUrl" value="/cart">
                                            <c:param name="action" value="remove" />
                                            <c:param name="description" value="${item.product.description}" />
                                            <c:param name="quantity" value="1" />
                                        </c:url>
                                        <a href="${removeUrl}" class="btn-remove">Remove</a>
                                    </td>
                                </tr>

                            </c:forEach>

                            <tr class="total-row">
                                <td colspan="3"><b>Total</b></td>

                                <td>
                                    <b>
                                        <fmt:formatNumber value="${grandTotal}"
                                            type="currency"
                                            currencySymbol="$"
                                            minFractionDigits="2"/>
                                    </b>
                                </td>

                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                    <p>
                        <b>To change the quantity,</b> enter the new quantity and click Update.
                    </p>

                    <nav class="btn-group">
                        <form action="${pageContext.request.contextPath}/product" method="get">
                            <input type="submit" value="Continue Shopping"
                                class="btn-secondary">
                        </form>

                        <form action="${pageContext.request.contextPath}/checkout" method="post">
                            <input type="submit" value="Checkout">
                        </form>
                    </nav>
                </section>
            </c:otherwise>
        </c:choose>
    </main>
    <footer>
        <p>&copy; 2026 CD Store. All rights reserved.</p>
    </footer>
</body>
</html>