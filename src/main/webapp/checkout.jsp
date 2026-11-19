<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout Complete</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1>Checkout</h1>
    </header>
    <main>
        <section class="empty-cart">
            <p>Your cart has been cleared.</p>
            <a href="${pageContext.request.contextPath}/product">Continue Shopping</a>
        </section>
    </main>
</body>
</html>
