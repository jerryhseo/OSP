var Selectable = (function (namespace, $, designer) {
    var currentJsPlumbInstance = designer.getCurrentJsPlumbInstance();
    /** selectable **/
    $("#wf-workflow-canvas").click(function (e) {
        if ($(".ui-selected").length > 0) {
            currentJsPlumbInstance.removeFromPosse($(".wf-box"), "posse");
        }
        if ($(e.target).hasClass("wf-box") && $(e.target).hasClass("ui-selectee")) {
            /*currentJsPlumbInstance.addToPosse($(e.target), "posse");*/
        } else {
            /*$(".wf-box").removeClass("ui-selected");
            $(e.target).addClass("ui-selected");
            $(".wf-box").each(function(_){
              currentJsPlumbInstance.removeFromPosse($(this), "posse");
            });*/
        }
    });

    $("#wf-workflow-canvas").selectable({
        filter: ".wf-box",
        selected: function (event, ui) {
            $(ui.selected).each(function (_) {
                currentJsPlumbInstance.addToPosse($(this), "posse");
            });
        },
        unselected: function (event, ui) {
            $(ui.unselected).each(function (_) {
                currentJsPlumbInstance.removeFromPosse($(this), "posse");
            });
        }
    });

    $(document).keydown(function (event) {
        if (event.which === 46 && $(".ui-selected").length > 0) {
            currentJsPlumbInstance.removeFromPosse($(".wf-box"), "posse");
            designer.removeSicenceApps($(".ui-selected"));
        }
    });
});