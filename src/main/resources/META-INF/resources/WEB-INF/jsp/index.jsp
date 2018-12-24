<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.css" var="bootstrapCss"/>

<head>
    <title>Markov Chain Application</title>
    <link href="${bootstrapCss}" type="text/css" rel="stylesheet"/>
</head>

<form:form method="post" action="/">
<div class="container">
    <h1 class="display-3">Markov Chain Application</h1>
    <p>This is a good UI.</p>
    <input type="submit" class="btn btn-primary"
           value="This will do something eventually">

    <c:if test="${message != null}">
        <h1><c:out value="${message}"/></h1>
    </c:if>
</div>

</form:form>