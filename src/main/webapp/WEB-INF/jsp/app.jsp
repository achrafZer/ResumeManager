<%@ include file="/WEB-INF/jsp/header.jsp" %>


<c:url var="app" value="/app.js" />

<div id="myApp">
    <router-view></router-view>
</div>


<script src="${app}" type="module"></script>

