<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@ include file="../vehicle/vehicle_sidebar.jsp"%>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">

            <legend>All Models</legend>
            <%--LIST OF EXISTING ELEMENTS--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Models</th>
                    <th>Makes</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="vehicleModel" items="${vehicleModelList}">
                    <tr>
                        <td><a href="/admin/vehicle/listModelVehicles/ + ${vehicleModel.vehicleModelId}">${vehicleModel.vehicleModelName}</a></td>
                        <td><a href="/admin/vehicle/listMakeVehicles/ + ${vehicleModel.vehicleMake.vehicleMakeId}">${vehicleModel.vehicleMake.vehicleMakeName}</a></td>
                        <td><a href="/admin/vehicleModel/edit/ + ${vehicleModel.vehicleMake.vehicleMakeId}">Edit</a></td>
                        <td><a href="/admin/vehicleModel/delete/ + ${vehicleModel.vehicleModelId}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>