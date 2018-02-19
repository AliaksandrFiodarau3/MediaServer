<!-- Modal -->

<div id="editArtist" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.edit"/></h1>
                </center>
            </div>
            <!-- Edit form -->
            <form id="edit-artist-form" method="POST" action="javascript:void(null);" onsubmit="editArtist()">
                <div class="modal-body">
                    <div class="container">
                        <input name="command" value="edit-artist" >
                        <input id="idArtist"  name="idArtist" >
                        <div class="form-group row">
                            <label for="titleArtist" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.title"/>:</label>
                            <div class="col-sm-5">
                                <input id="titleArtist" name="titleArtist" placeholder="<fmt:message key="label.title"/>"
                                       class="form-control form-control-plaintext" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="descriptionArtist" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.description"/>:</label>
                            <div class="col-sm-5">
                                <input name="descriptionArtist" id="descriptionArtist" placeholder="<fmt:message key="label.description"/>"
                                       class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imageArtist" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.image"/>:</label>
                            <div class="col-sm-5">
                                <input name="imageArtist" id="imageArtist" placeholder="<fmt:message key="label.image"/>"
                                       class="form-control" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div id=error></div>

                <div class="modal-footer">
                    <center>
                        <button type="submit" class="btn btn-success"><fmt:message key="admin.table.sign"/></button>
                        <button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message key="admin.table.close"/></button>

                    </center>
                </div>
            </form>
        </div>
    </div>
</div>
