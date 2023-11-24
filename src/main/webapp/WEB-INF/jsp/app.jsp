<%@ include file="/WEB-INF/jsp/header.jsp" %>


<c:url var="app" value="/app.js" />

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/app/home">Accueil</a>
</nav>

<div id="myApp">
    <router-view></router-view>
</div>


<script src="${app}" type="module"></script>

