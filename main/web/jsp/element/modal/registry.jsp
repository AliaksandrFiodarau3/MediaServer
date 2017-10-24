<!-- Modal -->
<div id="registryModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="label.sign.up"/></h1>
                </center>
            </div>
            <!-- Regisrtry form -->
            <form id="register-form" method="post" action="Controller?command=sign-up">

                <input type="hidden" name="command" value="sign-up"/>

                <div class="modal-body">
                    <div class="container">

                        <div class="form-group row">
                            <label for="inputName" class="col-sm-1 col-form-label"><fmt:message key="label.name"/>:</label>
                            <div class="col-sm-5">
                                <input  name="nameUser" id="inputName" placeholder="<fmt:message key="label.name"/>" class="form-control"
                                       pattern="^[\D]{3,40}$" title="<fmt:message key="validation.registry.name"/>"
                                       required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputSurname" class="col-sm-1 col-form-label"><fmt:message key="label.surname"/>:</label>
                            <div class="col-sm-5">
                                <input name="surnameUser" id="inputSurname"  placeholder="<fmt:message key="label.surname"/>" class="form-control"
                                       pattern="^[\D]{3,40}$" title="<fmt:message key="validation.registry.surname"/>"
                                       required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputLogin" class="col-sm-1 col-form-label"><fmt:message key="label.login"/>:</label>
                            <div class="col-sm-5">
                                <input id="inputLogin" name="loginUser"  placeholder="<fmt:message key="label.login"/>"
                                       class="form-control form-control-plaintext"
                                       pattern="^[A-Za-z]{3,40}" title="<fmt:message key="validation.registry.login"/>" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputPassword" class="col-sm-1 col-form-label"><fmt:message key="label.password"/>:</label>
                            <div class="col-sm-5">
                                <input id="inputPassword" placeholder="<fmt:message key="label.password"/>"
                                       type="password" name="passwordUser" id="inputPassword"  class="form-control"
                                       pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                                       title="<fmt:message key="validation.registry.password"/>" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="inputEmail" class="col-sm-1 col-form-label"><fmt:message key="label.email"/>:</label>
                            <div class="col-sm-5">
                                <input type="email" name="emailUser" id="inputEmail"  class="form-control"
                                       placeholder="<fmt:message key="label.email"/>"
                                       title="<fmt:message key="validation.registry.email"/>" required>
                            </div>
                            </div>
                        </div>
                    <div id="error"></div>
                </div>
                <div class="modal-footer">
                    <center>
                        <button type="submit" class="btn btn-success">Sign</button>
                        <button class="btn btn-default" type="button" data-dismiss="modal">Close</button>
                    </center>
                </div>
            </form>

        </div>
    </div>
</div>