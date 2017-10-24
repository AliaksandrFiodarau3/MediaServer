<!-- Modal -->

<div id="newAlbum" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1>New album</h1>
                </center>
            </div>
            <!-- Add new user form -->
            <form class="add-album-form" method="POST" action="javascript:void(null);" onsubmit="addAlbum()">
                <div class="modal-body">
                    <div class="container">
                        <input  name="command" value="add-album" hidden>
                        <input id="artistAlbum"  name="artistAlbum" hidden>
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
                            <label for="descriptionGenre" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.description"/>:</label>
                            <div class="col-sm-5">
                                <input name="descriptionAlbum" id="descriptionGenre" placeholder="<fmt:message key="label.description"/>"
                                       class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imageGenre" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.image"/>:</label>
                            <div class="col-sm-5">
                                <input name="imageAlbum" id="imageGenre" placeholder="<fmt:message key="label.image"/>"
                                       class="form-control" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <center>
                        <button id="submit" type="submit" class="btn btn-success">Sign</button>
                        <button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
                        <div id=error></div>
                    </center>
                </div>
            </form>
        </div>
    </div>
</div>