<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@ include file="vehicle_sidebar.jsp"%>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">
            <c:set var="makeVehicles" value="0" scope="page" />

            <legend>${vehicleMakeName} Vehicles</legend>
            <%--LIST OF EXISTING ELEMENTS--%>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>License Plate</th>
                    <th>VIN</th>
                    <th>Year</th>
                    <th>Color</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${vehicleMakeModelList}" var="vehicle" >
                    <tr>
                        <td>${vehicle.vehicleId}</td>
                        <td>${vehicle.vehicleModel.vehicleMake.vehicleMakeName}</td>
                        <td><a href="/admin/vehicle/listModelVehicles/ + ${vehicle.vehicleModel.vehicleModelId}">${vehicle.vehicleModel.vehicleModelName}</a></td>
                        <td>${vehicle.licensePlate}</td>
                        <td>${vehicle.vin}</td>
                        <td>${vehicle.year}</td>
                        <td>${vehicle.color}</td>
                        <td><a href="/admin/vehicle/edit/ + ${vehicle.vehicleId}">Edit</a> </td>
                        <td><a href="/admin/vehicleMake/deleteMakeModels/ + ${vehicle.vehicleId}">Delete</a></td>
                    </tr>
                    <c:set var="makeVehicles"    value="${makeVehicles +1}" scope="page" />
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>