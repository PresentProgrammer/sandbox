<%--suppress ELValidationInJSP --%>
<h2>Contacts</h2>
<c:forEach items="${contacts}" var="contact">
    <li>
        <c:out value="${contact.firstName} ${contact.lastName}, ${contact.phoneNumber}, ${contact.emailAddress}"  />
    </li>
</c:forEach>
