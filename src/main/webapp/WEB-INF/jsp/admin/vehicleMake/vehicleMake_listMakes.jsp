<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@ include file="../vehicle/vehicle_sidebar.jsp"%>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">

            <legend>All Makes</legend>
            <%--LIST OF EXISTING ELEMENTS--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Make</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicleMake" items="${vehicleMakeList}">
                    <tr>
                        <td><a href="/admin/vehicle/listMakeVehicles/ + ${vehicleMake.vehicleMakeId}">${vehicleMake.vehicleMakeName}</a></td>
                        <td><a href="/admin/vehicleMake/edit/ + ${vehicleMake.vehicleMakeId}">Edit</a></td>
                        <td><a href="/admin/vehicleMake/delete/ + ${vehicleMake.vehicleMakeId}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>