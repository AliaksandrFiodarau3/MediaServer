<!-- Modal -->

<div id="newUser" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.title.add.user"/></h1>
                </center>
            </div>
            <!-- Add new user form -->
            <form class="add-user-form" method="POST" action="javascript:void(null);" onsubmit="addUser()">
                <div class="modal-body">
                    <div class="container">
                        <input  name="command" value="add-user" hidden>
                        <div class="form-group row">
                            <label for="inputLogin" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.login"/>:</label>
                            <div class="col-sm-5">
                                <input id="inputLogin" name="loginUser" placeholder="<fmt:message key="label.login"/>"
                                       class="form-control form-control-plaintext"
                                       pattern="^[A-Za-z]{3,40}" title="<fmt:message key="validation.registry.login"/>" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="inputName" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.name"/>:</label>
                            <div class="col-sm-5">
                                <input name="nameUser" id="inputName" placeholder="<fmt:message key="label.name"/>"
                                       class="form-control"
                                       pattern="^[A-Za-zА-Яа-я]{3,40}" title="<fmt:message key="validation.registry.name"/>"
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
                                       pattern="^[A-Za-zА-Яа-я]{3,40}" title="<fmt:message key="validation.registry.surname"/>"
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
                            <label for="inputPassword" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.password"/>:</label>
                            <div class="col-sm-5">
                                <input id="inputPassword" placeholder="<fmt:message key="label.password"/>"
                                       type="password" name="passwordUser" id="inputPassword" class="form-control"
                                       pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                                       title="<fmt:message key="validation.registry.password"/>" required>
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
                    </div>
                </div>

                <div id=error></div>

                <div class="modal-footer">
                    <center>
                        <button id="submit" type="submit" class="btn btn-success"><fmt:message key="admin.table.sign"/></button>
                        <button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message key="admin.table.close"/></button>
                    </center>
                </div>
            </form>
        </div>
    </div>
</div>
