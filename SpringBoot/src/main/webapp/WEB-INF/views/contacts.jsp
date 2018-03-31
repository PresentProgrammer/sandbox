<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Spring Boot Contacts</title>
</head>
<body>
    <h2>Spring Boot Contacts</h2>
    <form method="POST">
        First Name: <input type="text" name="firstName" /><br/>
        Last Name: <input type="text" name="lastName" /><br/>
        Phone #: <input type="text" name="phoneNumber" /><br/>
        Email: <input type="email" name="emailAddress" /><br/>
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
