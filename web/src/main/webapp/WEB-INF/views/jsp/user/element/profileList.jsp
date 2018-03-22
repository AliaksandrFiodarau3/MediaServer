<script id="profile" type="text/x-handlebars-template">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1>{{user.name}} {{user.surname}}</h1>
                </center>
            </div>
            <div class="modal-body">
                <center>
                    <img src="{{photo}}"
                         name="aboutme" width="140" height="140" border="0" class="img-circle"></a>
                    <h3 class="media-heading">{{user.name}} {{user.surname}}</h3>
                </center>
                <hr>
                <center>
                    <p class="text-center"><strong><fmt:message key="admin.table.user.email"/>: </strong> {{user.email}}
                    </p>
                    <p class="text-center"><strong><fmt:message key="admin.table.user.login"/>: </strong> {{user.login}}
                    </p>
                </center>
            </div>
            <div class="modal-footer">
                <center>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Ok
                    </button>
                </center>
            </div>
        </div>
    </div>
    </div>
</script>