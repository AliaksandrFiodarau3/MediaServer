<script id="userGoodsTable" type="text/x-handlebars-template">
        <li>
            <div class="panel panel-default">
                <!--SHIPPING METHOD-->
                <div class="panel-heading text-center"><h4>Your Cart</h4></div>
                <div class="panel-body">
                    <table class="table borderless">
                        <thead>
                        <tr>
                            <td><strong>Your Cart: # item</strong></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </thead>
                        <tbody>
                        {{#each goods}}
                        <tr>
                            <td class="col-md-3">
                                <div class="media">
                                    <a class="thumbnail pull-left" href="#"> <img class="media-object"
                                                                                  src="{{song.album.image}}"
                                                                                  style="width: 72px; height: 72px;">
                                    </a>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Song</strong> {{song.title}}</h5>
                                        <h5 class="media-heading"><strong>Album</strong> {{song.album.title}} </h5>
                                    </div>
                                </div>
                            </td>
                            <td class="text-right">{{song.price}}$</td>
                            <td class="text-right">
                                <button type="button" class="btn btn-danger" onclick="removeSong('{{song.id}}')">Remove</button>
                            </td>
                        </tr>
                        {{/each}}
                        </tbody>
                    </table>
                    <hr>
                    <div class="panel-body">
                        <div class="form-group row">
                            <label for="codeOrder" class="col-sm-6 col-form-label"><fmt:message
                                    key="label.code"/>:</label>
                            <div class="col-sm-6">
                                <input id="codeOrder" name="codeOrder" placeholder="<fmt:message key="label.code"/>"
                                       class="form-control form-control-plaintext" required>
                            </div>
                        </div>
                        <button type="button" class="btn btn-primary btn-lg btn-block" onclick="useBonus()">Add</button>
                    </div>
                    <hr>
                    <div class="panel-body">
                        <div class="col-md-12">
                            <strong>Number</strong>
                            <div class="pull-right"><span></span><span>{{order.number}}</span></div>
                        </div>
                        <div class="col-md-12">
                            <strong>Order Total</strong>
                            <div class="pull-right"><span>$</span><span>{{order.price}}</span></div>
                            <hr>
                        </div>
                        <button type="button" class="btn btn-primary btn-lg btn-block" onclick="Buy()">Buy</button>
                    </div>
                </div>
            </div>
            <!--SHIPPING METHOD END-->
        </li>
</script>