var Decision = (function(namespace, $, currentJsPlumb){
    'use strict';
    var jqModalSelector = "#" + namespace + "wf-modal";
    var CRITERIA = "criteria";

    function createOpenModal(title, inputs, btns, saveHandler) {
        var modal = $(jqModalSelector);
        modal.find(".modal-title").text(title);
        modal.find(".modal-body").empty().append($.Mustache.render("tpl-modal-body", { "inputs": inputs }));
        modal.find(".modal-footer").empty().append($.Mustache.render("tpl-modal-footer", btns));
        modal.modal({ "backdrop": "static", "keyboard": false });
        $(jqModalSelector).find("input[name='Title']").select();
        _delay(function (selector) { $(selector).find("input[name='Title']").select(); }, 500, jqModalSelector);
        $(jqModalSelector).find("button[name='Save']").click(saveHandler);
        _enterkey(jqModalSelector + " input[name='Title']", saveHandler);
    }

    //.select({target: "8f66fa29-1e4e-4281-02f3-5c409d600e58"}).each(
    //function(connection){ console.log(connection.endpoints[1].getParameter("data").name());});
    function condtions(wfId){
        var jqWfId = "#" + wfId;
        var wfData = $(jqWfId).data();
        currentJsPlumb.select({ target: wfId }).each(
            function (connection) {
                if(connection.endpoints[1].getParameter("data").name() === CRITERIA){
                    console.log(connection.endpoints[0].getParameter("data"));
                }
            });
    }
    return {
        "condtions": condtions
    };
    
});