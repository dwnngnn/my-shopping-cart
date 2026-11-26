<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Music | Checkout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1><a class="site-name" href="${pageContext.request.contextPath}/product">Music</a></h1>
        <h2 class="page-heading">Checkout</h2>
    </header>
    <main>
        <section class="empty-cart">
            <p>Your cart has been cleared.</p>
            <a href="${pageContext.request.contextPath}/product">Continue Shopping</a>
        </section>
    </main>
    <footer>
        <p>&copy; 2026 Music. All rights reserved.</p>
    </footer>
</body>
</html>
