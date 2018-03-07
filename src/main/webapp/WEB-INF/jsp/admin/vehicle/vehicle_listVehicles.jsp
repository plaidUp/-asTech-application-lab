<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@ include file="vehicle_sidebar.jsp"%>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-12">

            <legend>All Vehicles</legend>
            <%--LIST OF EXISTING ELEMENTS--%>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Vehicle Make</th>
                        <th>Vehicle Models</th>
                        <th>License Plate</th>
                        <th>VIN</th>
                        <th>Year</th>
                        <th>Color</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vehicle" items="${vehicleList}">
                        <tr>
                            <td>${vehicle.vehicleId}</td>
                            <td><a href="/admin/vehicle/listMakeVehicles/ + ${vehicle.vehicleModel.vehicleMake.vehicleMakeId}">${vehicle.vehicleModel.vehicleMake.vehicleMakeName}</a></td>
                            <td><a href="/admin/vehicle/listModelVehicles/ + ${vehicle.vehicleModel.vehicleModelId}">${vehicle.vehicleModel.vehicleModelName}</a></td>
                            <td>${vehicle.licensePlate}</td>
                            <td>${vehicle.vin}</td>
                            <td>${vehicle.year}</td>
                            <td>${vehicle.color}</td>
                            <td><a href="/admin/vehicle/edit/ + ${vehicle.vehicleId}">Edit</a> </td>
                            <td><a href="/admin/vehicle/delete/ + ${vehicle.vehicleId}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../../includes/footer.jsp" %>