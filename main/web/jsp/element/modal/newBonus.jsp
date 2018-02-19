<!-- Modal -->

<div id="newBonus" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.title.add.bonus"/></h1>
                </center>
            </div>
            <!-- Add new user form -->
            <form class="add-bonus-form" method="POST" action="javascript:void(null);" onsubmit="addBonus()">
                <div class="modal-body">
                    <div class="container">
                        <input  name="command" value="add-bonus" hidden>
                        <div class="form-group row">
                            <label for="titleBonus" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.title"/>:</label>
                            <div class="col-sm-5">
                                <input id="titleBonus" name="titleBonus" placeholder="<fmt:message key="label.title"/>"
                                       class="form-control form-control-plaintext" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="descriptionBonus" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.description"/>:</label>
                            <div class="col-sm-5">
                                <input name="descriptionBonus" id="descriptionBonus" placeholder="<fmt:message key="label.description"/>"
                                       class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="discountBonus" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.discount"/>:</label>
                            <div class="col-sm-5">
                                <input name="discountBonus" id="discountBonus" placeholder="<fmt:message key="label.discount"/>"
                                       class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="codeBonus" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.code"/>:</label>
                            <div class="col-sm-5">
                                <input name="codeBonus" id="codeBonus" placeholder="<fmt:message key="label.code"/>"
                                       class="form-control" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="errorBonus"></div>

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