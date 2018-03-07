<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<script>

    $(document).ready(function () {

        $("#successAlert").delay(8000).fadeOut(2000);
        $("#warningAlert").delay(10000).fadeOut(2000);

    });
</script>

<div class="wrapper">

    <%--SIDEBAR--%>
    <%@ include file="vehicle_sidebar.jsp"%>

    <div class="col-sm-10" id="main-wrapper">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" modelAttribute="vehicleVO" action="/admin/vehicle/add" method="post">
                <fieldset>
                    <legend>Add Vehicle</legend>

                    <div class="form-group">
                        <label for="inputNewVehicleMake" class="col-lg-2 control-label">Vehicle Make</label>
                        <div class="col-lg-10">
                            <form:input path="newVehicleMake" type="text" class="form-control" id="inputNewVehicleMake" placeholder="Vehicle Make"></form:input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputNewVehicleModel" class="col-lg-2 control-label">Vehicle Model</label>
                        <div class="col-lg-10">
                            <form:input path="newVehicleModel" type="text" class="form-control" id="inputNewVehicleModel" placeholder="Vehicle Model"></form:input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputNewLicensePlate" class="col-lg-2 control-label">License Plate</label>
                        <div class="col-lg-10">
                            <form:input path="newLicensePlate" type="text" class="form-control" id="inputNewLicensePlate" placeholder="License Plate"></form:input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputNewVin" class="col-lg-2 control-label">VIN</label>
                        <div class="col-lg-10">
                            <form:input path="newVin" type="text" class="form-control" id="inputNewVin" placeholder="VIN"></form:input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputNewYear" class="col-lg-2 control-label">Year</label>
                        <div class="col-lg-10">
                            <form:input path="newYear" type="text" class="form-control" id="inputNewYear" placeholder="Year"></form:input>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputNewColor" class="col-lg-2 control-label">Color</label>
                        <div class="col-lg-10">
                            <form:input path="newColor" type="text" class="form-control" id="inputNewColor" placeholder="Color"></form:input>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-10 col-lg-offset-2">
                            <form:button type="reset" value="cancel" class="btn btn-default">Cancel</form:button>
                            <form:button type="submit" value="save" class="btn btn-primary">Save</form:button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>

        <div class="col-sm-4">

            <%--SUCCESS ALERT--%>
            <div class="${successAlert == null ? 'hidden' : successAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Vehicle added successfully to the database!</strong><a href="#" class="alert-link"></a>.
                </div>
            </div>

            <%--WARNING ALERT--%>
            <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="warningAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <h4>Be Advised!</h4>
                    <p>All fields required. If any are blank, nothing will be saved. <a href="#" class="alert-link"></a>.</p>
                </div>
            </div>

            <%--ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="errorAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>All Fields Required</strong> <a href="#" class="alert-link">If any are blank, nothing will be saved.</a>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="../../includes/footer.jsp" %>