<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Downloads - Music Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1><a class="site-name" href="${pageContext.request.contextPath}/product">Music</a></h1>
        <h2 class="page-heading">Downloads</h2>
        <p>User email: <c:out value="${cookie.userEmail.value}" /></p>
    </header>
    <main>
        <section class="product-list">
            <p>Find and play tracks on Spotify.</p>
            <table class="downloads-table">
                <thead>
                    <tr><th>Track</th><th>Link</th></tr>
                </thead>
                <tbody>
                    <c:choose>
                    <c:when test="${not empty selectedProduct}">
                        <c:set var="spotifyUrl" value="https://open.spotify.com/search/${sessionScope.product.trackId}%20Bui%20Truong%20Linh" />
                        <tr>
                            <td>
                                <p>Product code: <c:out value="${sessionScope.product.code}" /></p>
                                <p><c:out value="${sessionScope.product.description}" /></p>
                            </td>
                            <td class="downloads-action"><a href="${spotifyUrl}" target="_blank" rel="noopener noreferrer">Open Spotify</a></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td><c:out value="${product.description}" /></td>
                                <td class="downloads-action"><a href="${pageContext.request.contextPath}/download?productCode=${product.code}">Select</a></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
            <nav class="page-links">
                <a href="${pageContext.request.contextPath}/cookies">View All Cookies</a>
                <a href="${pageContext.request.contextPath}/cookies?action=delete">Delete All Persistent Cookies</a>
                <a href="${pageContext.request.contextPath}/product">Back to album</a>
            </nav>
        </section>
    </main>
    <footer>
        <p>&copy; 2026 Music. All rights reserved.</p>
    </footer>
</body>
</html>
