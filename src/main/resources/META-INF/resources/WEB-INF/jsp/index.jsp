<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/webjars/bootstrap/3.3.7-1/css/bootstrap.css" var="bootstrapCss"/>

<head>
    <title>Markov Chain Application</title>
    <link href="${bootstrapCss}" type="text/css" rel="stylesheet"/>
</head>


<div class="container">
    <h1 class="display-3">Markov Chain Application</h1>
    <p>This is a good UI.</p>
    <input type="button" class="btn btn-primary"
           value="This will do something eventually">
</div>