<!-- Modal -->

<div id="newArtist" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.title.add.artist"/></h1>
                </center>
            </div>
            <!-- Add new artist form -->
            <div class="modal-body">
                <div class="container">
                    <input id="genreArtist" name="genre" hidden>
                    <div class="form-group row">
                        <label for="titleArtist" class="col-sm-1 col-form-label"><fmt:message
                                key="label.title"/>:</label>
                        <div class="col-sm-5">
                            <input id="titleArtist" name="title"
                                   placeholder="<fmt:message key="label.title"/>"
                                   class="form-control form-control-plaintext" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="descriptionArtist" class="col-sm-1 col-form-label"><fmt:message
                                key="label.description"/>:</label>
                        <div class="col-sm-5">
                            <input name="description" id="descriptionArtist"
                                   placeholder="<fmt:message key="label.description"/>"
                                   class="form-control" required>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="imageArtist" class="col-sm-1 col-form-label"><fmt:message
                                key="label.image"/>:</label>
                        <div class="col-sm-5">
                            <input name="image" id="imageArtist"
                                   placeholder="<fmt:message key="label.image"/>"
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
                    <button id="submit" type="button" class="btn btn-success" data-dismiss="modal" onclick="

                            putMethod('admin/artist' +
                             '/genre/' + getNode('genreArtist').value +
                              '/title/' + getNode('titleArtist').value +
                              '/description/'+ getNode('descriptionArtist').value +
                              '/image/'+ getNode('imageArtist').value);">

                        <fmt:message key="admin.table.sign"/></button>
                    <button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message
                            key="admin.table.close"/></button>
                    <div id=error></div>
                </center>
            </div>
        </div>
    </div>
</div>