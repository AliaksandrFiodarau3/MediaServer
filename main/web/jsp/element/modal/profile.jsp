<!-- Modal -->
<div id="profileModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content -->
        <div class="modal-content">
            <div class="modal-header">
                <input id="loginUser" value="${loginUser}" hidden>
                <center>
                    <h1>${nameUser} ${surnameUser}</h1>
                </center>
            </div>
            <div class="modal-body">
                <center>
                    <img src="${photoUser}"
                         name="aboutme" width="140" height="140" border="0" class="img-circle"></a>
                    <h3 class="media-heading">${nameUser} ${surnameUser}</h3>
                    <span><strong>Bonuses: </strong></span>

                    <c:forEach var="bonus" items="${bonuses}">

                        <span class="label label-info">${bonus.title}</span>

                    </c:forEach>
                </center>
                <hr>
                <center>
                    <p class="text-center"><strong>E-mail: </strong> ${emailUser}</p>
                    <p class="text-center"><strong>Login: </strong> ${loginUser}</p>
                </center>
            </div>
            <div class="modal-footer">
                <center>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Ok
                    </button>
                </center>
            </div>
        </div>
    </div>
</div>