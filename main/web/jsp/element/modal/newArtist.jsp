<!-- Modal -->

<div id="newArtist" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1>New artist</h1>
                </center>
            </div>
            <!-- Add new artist form -->
            <form class="add-artist-form" method="POST" action="javascript:void(null);" onsubmit="addArtist()">
                <div class="modal-body">
                    <div class="container">
                        <input name="command" value="add-artist" hidden>
                        <input id="genreArtist"  name="genreArtist" hidden>
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
                        <c:if test="${errorMessage != null}">
                            <div class="form-group row">
                                <div class="has-error alert alert-danger" role="alert">
                                        ${errorMessage}
                                </div>
                            </div>
                        </c:if>
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