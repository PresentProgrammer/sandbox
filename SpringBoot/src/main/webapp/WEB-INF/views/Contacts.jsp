<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Spring Boot Contacts</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/main.css" />" />
</head>
<body>
    <%@ include file="elements/NewContactForm.jsp" %>
    <%@ include file="elements/AllContacts.jsp" %>
</body>
</html>
