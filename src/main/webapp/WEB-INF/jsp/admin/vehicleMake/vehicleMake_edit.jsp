<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<div class="wrapper">

    <%@ include file="../vehicle/vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <legend>Edit Vehicle Make</legend>

            <c:set var="idx" value="0" scope="page" />
            <form:form cssClass="form-horizontal" modelAttribute="vehicleMake" var="vehicleMake" action="/admin/vehicleMake/update" method="post">
                <form:hidden path="vehicleMakeId" />
                <form:hidden path="version" />

                <div class="row">
                    <div class="form-group">
                        <label for="inputVehicleMakeName" class="col-sm-3 control-label">Vehicle Make</label>
                        <div class="col-sm-7">
                            <form:input path="vehicleMakeName" type="text" id="inputVehicleMakeName" cssClass="form-control" />
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-10">
                        <hr>
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