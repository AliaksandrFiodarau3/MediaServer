<!-- Modal -->

<div id="newAlbum" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.title.add.album"/></h1>
                </center>
            </div>
            <!-- Add new user form -->
            <div class="modal-body">
                <div class="container">
                    <input id="artistId" name="artistId" hidden>
                    <div class="form-group row">
                        <label for="titleAlbum" class="col-sm-1 col-form-label"><fmt:message
                                key="label.title"/>:</label>
                        <div class="col-sm-5">
                            <input id="titleAlbum" name="titleAlbum" placeholder="<fmt:message key="label.title"/>"
                                   class="form-control form-control-plaintext" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="yearAlbum" class="col-sm-1 col-form-label"><fmt:message
                                key="label.year"/>:</label>
                        <div class="col-sm-5">
                            <input id="yearAlbum" name="yearAlbum" placeholder="<fmt:message key="label.year"/>"
                                   class="form-control form-control-plaintext" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="descriptionAlbum" class="col-sm-1 col-form-label"><fmt:message
                                key="label.description"/>:</label>
                        <div class="col-sm-5">
                            <input name="descriptionAlbum" id="descriptionAlbum"
                                   placeholder="<fmt:message key="label.description"/>"
                                   class="form-control" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="imageAlbum" class="col-sm-1 col-form-label"><fmt:message
                                key="label.image"/>:</label>
                        <div class="col-sm-5">
                            <input name="imageAlbum" id="imageAlbum" placeholder="<fmt:message key="label.image"/>"
                                   class="form-control" required>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <center>
                    <button id="submit" type="button" class="btn btn-success" data-dismiss="modal"
                            onclick="
                            addAlbum();
                            getByArtist('{{id}}')">

                        <fmt:message key="admin.table.sign"/></button>
                    <button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message
                            key="admin.table.close"/></button>
                    <div id=error></div>
                </center>
            </div>
        </div>
    </div>
</div>