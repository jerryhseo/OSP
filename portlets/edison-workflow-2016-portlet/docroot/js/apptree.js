var AppTree = (function(namespace, $, designer){

    return {
        "bindDnd" : bindDnd,
        "drawAppTree" : drawAppTree
    };

    function isDraggableNode(data, event) {
        if (!data[0]) return false;
        if (data[0].type === "group") {
            return false;
        } else if (data[0].type === "app") {
            return true;
        } else {
            return false;
        }
    }

    function drawAppTree(appTreeSelector, searchSelector, initData) {
        $(appTreeSelector).jstree({
            "core": {
                "check_callback": function (operation, node, node_parent, node_position, more) {
                    return operation !== 'move_node';
                },
                "data": initData,
                "themes": {
                    "name": "proton",
                    "responsive": true
                }
            },
            "types": {
                "category": {},
                "subCategory": {},
                "app": {
                    "icon": "icon-file"
                }
            },
            "search": {
                "case_sensitive": false,
                "show_only_matches": true
            },
            "dnd": {
                "is_draggable": isDraggableNode,
                "check_while_dragging": true
            },
            "plugins": ["types", "dnd", "search"]
        }).bind("loaded.jstree", function (event, data) {
            $(searchSelector).keyup(function (e) {
                var searchString = $(this).val();
                $(appTreeSelector).jstree(true).search(searchString);
            });
        }).bind("select_node.jstree", function (event, data) {
            var nodeId = data.node.id;
            var node = data.node;
            if (node.type !== "app") {
                if (!$("#" + nodeId).hasClass("jstree-open")) {
                    $(appTreeSelector).jstree("open_node", node);
                } else {
                    $(appTreeSelector).jstree("close_node", node);
                }
            }
        });
    }

    function bindDnd(documentObject, appTreeSelector){
        $(documentObject).on("dnd_start.vakata", function (event, data) {
            var nodeId = data.data.nodes[0];
            var nodeData = $(appTreeSelector).jstree(true).get_node(nodeId).data;
            $(".menu-panel").hide();
        });

        $(documentObject).on("dnd_stop.vakata", function (event, data) {
            var eventTarget = $(data.event.target);
            if(!eventTarget.closest('.jstree').length) {
                if(eventTarget.closest('.wf-drop').length) {
                    var nodeId = data.data.nodes[0];
                    var node = $(appTreeSelector).jstree(true).get_node(nodeId);
                    designer.addScienceApp(eventTarget.closest('.wf-drop'), data.event.pageX, data.event.pageY, node.data);
                }
            }
            $(".menu-panel").show();
        });

        $(documentObject).on("dnd_move.vakata", function (event, data) {
            var nodeId = data.data.nodes[0];
            var nodeData = $(appTreeSelector).jstree(true).get_node(nodeId).data;
            var eventTarget = $(data.event.target);
            if (!eventTarget.closest('.jstree').length) {
                if (eventTarget.closest('.wf-drop').length) {
                    data.helper.find('.jstree-icon').removeClass('jstree-er').addClass('jstree-ok');
                }
            }
        });
    }
});
