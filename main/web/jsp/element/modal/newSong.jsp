<!-- Modal -->

<div id="newSong" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <div class="modal-header">
                <center>
                    <h1><fmt:message key="admin.table.title.add.song"/></h1>
                </center>
            </div>
            <!-- Add new user form -->
            <form class="add-song-form" method="POST" action="javascript:void(null);" onsubmit="addSong()">
                <div class="modal-body">
                    <div class="container">
                        <input  name="command" value="add-song" hidden>
                        <input id="albumSong" name="albumSong" hidden>
                        <div class="form-group row">
                            <label for="titleSong" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.title"/>:</label>
                            <div class="col-sm-5">
                                <input id="titleSong" name="titleSong" placeholder="<fmt:message key="label.title"/>"
                                       class="form-control form-control-plaintext" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="durationSong" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.duration"/>:</label>
                            <div class="col-sm-5">
                                <input name="durationSong" id="durationSong" placeholder="<fmt:message key="label.duration"/>"
                                       class="form-control" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="priceSong" class="col-sm-1 col-form-label"><fmt:message
                                    key="label.price"/>:</label>
                            <div class="col-sm-5">
                                <input name="priceSong" id="priceSong" placeholder="<fmt:message key="label.price"/>"
                                       class="form-control" required>
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
