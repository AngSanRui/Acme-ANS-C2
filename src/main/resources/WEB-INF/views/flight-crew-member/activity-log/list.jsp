<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="flight-crew-member.activity-log.list.label.registrationMoment" path="registrationMoment" width="25%"/>
	<acme:list-column code="flight-crew-member.activity-log.list.label.typeOfIncident" path="typeOfIncident" width="25%"/>
	<acme:list-column code="flight-crew-member.activity-log.list.label.description" path="description" width="25%"/>
	<acme:list-column code="flight-crew-member.activity-log.list.label.severityLevel" path="severityLevel" width="25%"/>
	<acme:list-payload path="payload"/>
</acme:list>

<jstl:if test="${showCreate}">
	<acme:button code="flight-crew-member.activity-log.list.button.create" action="/flight-crew-member/activity-log/create?assignmentId=${assignmentId}"/>
</jstl:if>