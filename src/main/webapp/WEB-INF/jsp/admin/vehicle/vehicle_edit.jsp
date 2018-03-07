<%@ include file="../../includes/header.jsp" %>
<%@ include file="../../includes/navbar.jsp" %>
<%@ include file="../../includes/subnav_admin.jsp" %>

<script>
    $(document).ready(function() {
        //attach an onclick function to the remove buttons
        $('.remove_button').click(function() {
            //log name button, and contents of associated text box
            console.log(this.name);
            console.log($('#' + this.name).val());
            //clear the value / contents of text box
            $('#' + this.name).val('');
            //submit the form
            $('#elementType').submit();
        });
    });
</script>

<div class="wrapper">

    <%--SIDEBAR HERE--%>
    <%@ include file="vehicle_sidebar.jsp" %>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <legend>Edit Vehicle</legend>

            <c:set var="idx" value="0" scope="page" />
            <form:form cssClass="form-horizontal" modelAttribute="vehicleVO" action="/admin/vehicle/update" method="post">
                <form:hidden path="vehicle.vehicleId" />
                <form:hidden path="vehicle.version" />

                <div class="row">
                    <div class="form-group">
                        <label for="${idx}" class="col-sm-3 control-label">Vehicle Make</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input path="vehicle.vehicleModel.vehicleMake.vehicleMakeName" id="${idx}" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="${idx}" class="col-sm-3 control-label">Vehicle Model</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input path="vehicle.vehicleModel.vehicleModelName" id="${idx}" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="${idx}" class="col-sm-3 control-label">License Plate</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input path="vehicle.licensePlate" id="${idx}" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="${idx}" class="col-sm-3 control-label">VIN</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input path="vehicle.vin" id="${idx}" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="${idx}" class="col-sm-3 control-label">Year</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input path="vehicle.year" id="${idx}" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group">
                        <label for="${idx}" class="col-sm-3 control-label">Color</label>
                        <div class="col-sm-7">
                            <div class="input-group">
                                <form:input path="vehicle.color" id="${idx}" cssClass="form-control" />
                            </div>
                        </div>
                    </div>
                </div>
                <c:set var="idx"    value="${idx +1}" scope="page" />

                <div class="row">
                    <button class="btn btn-primary">Update</button>
                </div>

            </form:form>

        </div>
    </div>


</div>

<%@ include file="../../includes/footer.jsp" %>