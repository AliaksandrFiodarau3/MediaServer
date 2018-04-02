<!-- Modal -->
<div id="newGenre" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.title.add.genre"/></h1>
                </center>
            </div>
            <!-- Add new user form -->
           <%-- <form class="add-genre-form" id="add-genre" method="POST" action="javascript:void(null);"
                  onsubmit="postMethod('/admin/genre','#genre-list', 'content', '#add-genre')">--%>
                <div class="modal-body">
                    <div class="container">

                        <div class="form-group row">
                            <label for="titleGenre" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.title"/>:</label>
                            <div class="col-sm-5">
                                <input id="titleGenre" name="titleGenre" placeholder="<fmt:message key="label.title"/>"
                                       class="form-control form-control-plaintext" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="descriptionGenre" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.description"/>:</label>
                            <div class="col-sm-5">
                                <input name="descriptionGenre" id="descriptionGenre"
                                       placeholder="<fmt:message key="label.description"/>"
                                       class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imageGenre" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.image"/>:</label>
                            <div class="col-sm-5">
                                <input name="imageGenre" id="imageGenre" placeholder="<fmt:message key="label.image"/>"
                                       class="form-control" required>
                            </div>
                        </div>

                    </div>
                </div>
                <div class="modal-footer">
                    <center>
                        <button id="submit" type="button" onclick="putMethod('/admin/genre/title/' +
                        getNode('titleGenre').value +'/description/'+ getNode('descriptionGenre').value +
                        '/image/'+ getNode('imageGenre').value
                        ,'#genre-list', 'content', '#add-genre'); getMethod('admin/genres', '#genre-list', 'content'); "

                                data-dismiss="modal" class="btn btn-success">
                            <fmt:message key="admin.table.sign"/></button>
                        <button class="btn btn-default" type="button" data-dismiss="modal">
                            <fmt:message key="admin.table.close"/></button>
                    </center>
                </div>
            <%--</form>--%>
        </div>
    </div>
</div>
