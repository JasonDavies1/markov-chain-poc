<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<spring:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.css" var="bootstrapCss"/>

<head>
    <title>Markov Chain Application</title>
    <link href="${bootstrapCss}" type="text/css" rel="stylesheet"/>
</head>

<form:form method="post" action="/" modelAttribute="inputModel">
    <div class="container">
        <h1 class="display-3">Markov Chain Application</h1>
        <p>Enter text below to submit values for Markov chain generation</p>

        <div class="form-group">
            <spring:bind path="textInput">
                <form:input path="textInput" class="form-control" id="textInput"
                            placeholder="Enter text in me!"/>
            </spring:bind>
        </div>
        <input type="submit" class="btn btn-primary"
               value="Submit new input">

        <c:if test="${message != null}">
            <h1><c:out value="${message}"/></h1>
        </c:if>

        <c:if test="${nodeRelationships != null}">
            <table class="table table-striped" id="nodes">
                <tr>
                    <th>Key</th>
                    <th>Known values</th>
                </tr>
                <c:forEach items="${nodeRelationships}" var="node">
                    <tr>
                        <td>${node.key}</td>
                        <td>${node.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</form:form>