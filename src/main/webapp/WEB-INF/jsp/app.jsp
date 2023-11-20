<%@ include file="/WEB-INF/jsp/header.jsp" %>

<c:url var="home" value="/aaa" />
<c:url var="app" value="/app.js" />

<div id="myApp">
    <div class="container mt-5">
        <h1>Gestionnaire de CV</h1>
        <p>Ceci est la page principale</p>

        <div v-for="person in persons" :key="person.id" class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">{{ person.firstName }} {{ person.lastName }}</h5>
                <h6 class="card-subtitle mb-2 text-muted" v-if="person.cv && person.cv.activities && person.cv.activities.length">Activités</h6>
                <ul class="list-group list-group-flush">
                    <li v-for="activity in person.cv.activities" :key="activity.id" class="list-group-item" v-if="person.cv && person.cv.activities">
                        {{ activity.title }} ({{ activity.startYear }} - {{ activity.endYear }})
                    </li>
                    <p v-else class="text-muted">Aucune activité de CV disponible.</p>
                </ul>
            </div>
        </div>
    </div>
</div>

<script src="${app}" type="module"></script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>
