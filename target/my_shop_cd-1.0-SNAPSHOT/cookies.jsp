<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cookies - Music Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1><a class="site-name" href="${pageContext.request.contextPath}/product">Music</a></h1>
        <h2 class="page-heading">All Cookies</h2>
    </header>
    <main>
        <section class="product-list">
            <table>
                <thead><tr><th>Name</th><th>Value</th></tr></thead>
                <tbody>
                    <c:forEach var="cookieEntry" items="${cookie}">
                        <tr>
                            <td><c:out value="${cookieEntry.key}" /></td>
                            <td><c:out value="${cookieEntry.value.value}" /></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty cookie}">
                        <tr><td colspan="2">No cookies were sent with this request.</td></tr>
                    </c:if>
                </tbody>
            </table>
            <nav class="page-links">
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
