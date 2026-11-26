<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Music Store</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/main.css">
</head>
<body>
    <header>
        <h1>Register</h1>
    </header>
    <main>
        <section class="register-form">
            <p>Enter your email address to register and view your downloads.</p>
            <c:if test="${not empty error}">
                <p class="form-error"><c:out value="${error}" /></p>
            </c:if>
            <form action="${pageContext.request.contextPath}/download" method="post">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
                <input type="submit" value="Register">
            </form>
        </section>
    </main>
</body>
</html>
