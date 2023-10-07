<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@page import="java.util.Date"%>

<div class="container">
	<h1>Hello de ${message}</h1>

	<h2>Inclure du java</h2>
	<p>
	<%
	Date d = new Date();
	out.print("Aujourd'hui : ");
	out.print(d);
	request.setAttribute("today", d);
	%>
	</p>
	
	<h2>Explorer la requête avec les EL</h2>
	<p>params = ${param}</p>
	<p>today = ${today}</p>
	<p>Compteur en session = ${sessionScope.counter}</p>
	<p>1+2 = ${1 + 2}</p>
	
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>
