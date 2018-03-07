<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%--SIDEBAR HERE--%>
    <%@ include file="../vehicle/vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <legend>Edit Vehicle Models</legend>

                <c:set var="idx" value="0" scope="page" />
                <form:form cssClass="form-horizontal" modelAttribute="vehicleVO" action="/admin/vehicleModel/update" method="post">
                    <form:hidden path="vehicleMake.vehicleMakeId" />
                    <form:hidden path="vehicleMake.version" />

                    <div class="row">
                        <div class="form-group">
                            <label for="inputVehicleMakeName" class="col-sm-2 control-label">Vehicle Make</label>
                            <div class="col-sm-8">
                                <form:input path="vehicleMake.vehicleMakeName" type="text" id="inputVehicleMakeName" cssClass="form-control"></form:input>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-10">
                            <hr>
                        </div>
                    </div>

                    <c:forEach items="${vehicleVO.vehicleModelList}" var="vehicleModel">
                        <form:hidden path="vehicleModelList[${idx}].vehicleModelId" />
                        <form:hidden path="vehicleModelList[${idx}].version" />
                        <div class="row">
                            <div class="form-group">
                                <label for="${idx}" class="col-sm-3 control-label">Vehicle Model</label>
                                <div class="col-sm-7">
                                    <div class="input-group col-sm-12">
                                        <form:input path="vehicleModelList[${idx}].vehicleModelName" name="vehicleModelName" value="${vehicleModel.vehicleModelName}" id="${idx}" cssClass="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:set var="idx"    value="${idx +1}" scope="page" />
                    </c:forEach>

                    <div class="row">
                        <div class="form-group">
                            <label for="inputNewVehicleModel" class="col-sm-3 control-label">Add New Vehicle Model</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="inputNewVehicleModel" id="inputNewVehicleModel" />
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <button class="btn btn-primary">Update</button>
                    </div>

                </form:form>

        </div>
    </div>


</div>

<%@ include file="../../includes/footer.jsp" %>