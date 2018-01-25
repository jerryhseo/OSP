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
                if (operation === 'move_node') {
                    return false;
                }
                return true;
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
    });
}