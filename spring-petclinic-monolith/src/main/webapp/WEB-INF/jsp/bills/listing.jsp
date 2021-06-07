<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="bills">
    <h2>Bills</h2>

    <table id="billssTable" class="table table-striped">
        <thead>
        <tr>
            <th>Concept</th>
            <th>Amount</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bills}" var="bill">
            <tr>
                <td>
                   <c:out value="${bill.concept}"/>
                </td>
                <td>                    
                   <c:out value="${bill.amount} "/>
                </td>
                <td><a href="/bills/delete/${bill.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</petclinic:layout>
