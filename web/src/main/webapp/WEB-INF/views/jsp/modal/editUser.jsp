<!-- Modal --><%--

<div id="editUser" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.edit"/></h1>
                </center>
            </div>
            <!-- Edit form -->
            <form id="edit-user-form" method="POST" action="javascript:void(null);" onsubmit="call()">
                <div class="modal-body">
                    <div class="container">
                        <input name="command" value="edit-user" hidden>
                        <input id="idUserTable" name="idUser" hidden>
                        <div class="form-group row">
                            <label for="inputLogin" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.login"/>:</label>
                            <div class="col-sm-5">
                                <input id="inputLogin" name="loginUser" placeholder="<fmt:message key="label.login"/>"
                                       class="form-control form-control-plaintext"
                                       pattern="^[A-Za-z]{3,40}" title="<fmt:message key="validation.registry.login"/>"
                                       required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputName" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.name"/>:</label>
                            <div class="col-sm-5">
                                <input name="nameUser" id="inputName" placeholder="<fmt:message key="label.name"/>"
                                       class="form-control"
                                       pattern="^[\D]{3,40}" title="<fmt:message key="validation.registry.name"/>"
                                       required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputSurname" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.surname"/>:</label>
                            <div class="col-sm-5">
                                <input name="surnameUser" id="inputSurname"
                                       placeholder="<fmt:message key="label.surname"/>"
                                       class="form-control"
                                       pattern="^[\D]{3,40}" title="<fmt:message key="validation.registry.surname"/>"
                                       required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputEmail" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.email"/>:</label>
                            <div class="col-sm-5">
                                <input type="email" name="emailUser" id="inputEmail" class="form-control"
                                       placeholder="<fmt:message key="label.email"/>"
                                       title="<fmt:message key="validation.registry.email"/>" required>
                            </div>
                            <div id="error-email">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputPhoto" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.photo"/>:</label>
                            <div class="col-sm-5">
                                <input type="text" name="photoUser" id="inputPhoto" class="form-control"
                                       placeholder="<fmt:message key="label.photo"/>"
                                       title="<fmt:message key="info.photo"/>">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputRoot" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.root"/>:</label>
                            <div class="col-sm-5">
                                <input type="text" name="rootUser" id="inputRoot" class="form-control"
                                       placeholder="<fmt:message key="label.root"/>"
                                       title="<fmt:message key="info.root"/>">
                            </div>
                        </div>
                        <div id="error"></div>
                    </div>
                </div>

                <div class="modal-footer">
                    <center>
                        <button id="userEdit" type="submit" class="btn btn-success"><fmt:message
                                key="admin.table.sign"/></button>
                        <button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message
                                key="admin.table.close"/></button>
                    </center>
                </div>
            </form>
        </div>
    </div>
</div>--%>