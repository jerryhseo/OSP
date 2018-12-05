document.createElement('folder');
document.createElement('file');

var print = function(param) {
    console.log(param);
};

(function(j) {
    var file_manager = {
            viewport: null,
            explorer: null,
            files: {},
            folderTag: null,
            fileTag: null,
            tag: null,
            searchBox: null,
            selection: [],
            CTRL: false,
            CMND: false,
            namespace: "",
            currentPath: "",
            contextmenu: {
                file: [{
                    name: 'Download',
                    img: '',
                    title: 'delete file or folder',
                    fun: function(event) {
                        var fn = window[file_manager.namespace + "download"];
                        if(typeof fn === 'function') {
                            fn(event.trigger[0].id);
                        }
                    }
                }]
            }
        },
        _fn = {
            renderNav: function(el) {
            },

            initContextMenu: function() {
                file_manager.explorer.find('file').contextMenu(file_manager.contextmenu.file, {
                    triggerOn: 'contextmenu'
                });
            },

            initSelectable: function(el) {
                var preSelectedItems = [];
                el.selectable({
                    filter: 'file, folder',
                    distance: 1,
                    selected: function(event, ui) {
                        preSelectedItems.push(j(ui.selected));
                    },
                    stop: function(event, ui) {
                        if (file_manager.CTRL || file_manager.CMND) {
                            for (var i = 0; i < preSelectedItems.length; i++) {
                                var item = j(preSelectedItems[i]);
                                if (item.hasClass('selected')) {
                                    item.removeClass('ui-selected').removeClass('selected');
                                } else {
                                    item.addClass('selected');
                                }
                            }
                        }

                        preSelectedItems = [];
                        _fn.getSelection();
                    }
                });
            },

            initDraggable: function(el) {
                el.draggable({
                    revert: true,
                    delay: 200,
                    containment: "parent",
                    drag: function(event, ui) {
                        var item = ui.helper;
                        item.siblings().removeClass('ui-selected').removeClass('selected');
                        item.addClass('ui-selected').addClass('selected');
                    }
                });
            },

            initDroppable: function(el) {
                el.droppable({
                    hoverClass: 'drop-container',
                    drop: function(event, ui) {
                        _fn.drop(j(ui.draggable[0]), j(this));
                    }
                });
            },

            getSelection: function() {
                file_manager.selection = [];
                var selectedItems = file_manager.explorer.find('.ui-selected'),
                    item;
                for (var a = 0; a < selectedItems.length; a++) {
                    item = selectedItems.eq(a);
                    file_manager.selection.push(item.attr('id'));
                }
            },

            sort: function(array, propArray, asc) {
                array = array.sort(function(a, b) {
                    if (asc) {
                        for (var i = 0; i < propArray.length; i++) {
                            if (a[propArray[i]] == b[propArray[i]]) {
                                continue;
                            }
                            return (a[propArray[i]] > b[propArray[i]]) ? 1 : -1;
                        }
                    } else {
                        for (var i = 0; i < propArray.length; i++) {
                            if (a[propArray[i]] == b[propArray[i]]) {
                                continue;
                            }
                            return (a[propArray[i]] < b[propArray[i]]) ? 1 : ((a[propArray[i]] > b[propArray[i]]) ? -1 : 0);
                        }
                    }
                    return 0;
                });
            },

            renderSorted: function(sortBy, asc) {
                _fn.sort(file_manager.files, sortBy, asc);
                _fn.renderExplorer(file_manager.explorer, file_manager.files);
            },

            bindDoubleClick: function(explorer) {
                j('folder').dblclick(function(e) {
                    var folder = j(this);
                    
                    var fn = window[file_manager.namespace + "girdFile"];
                    if(typeof fn === 'function') {
                        fn(_fn.getCurrentPath()+"/"+folder.data().path);
                    }
                });
            },
            setCurrentPath(fullPath){
            	file_manager.currentPath = fullPath;
            },
            getCurrentPath(){
            	return file_manager.currentPath;
            },
            drop: function(item, container) {
                /*console.clear();
                print(item.data());
                print(container.data());*/
            },

            renderExplorer: function(explorer, filesArray) {
                explorer.html('<div class="bg" style="position:absolute;top:0;left:0;right:0;bottom:0;z-index:0;"></div>');

                for (var i = 0; i < filesArray.length; i++) {
                    var file = filesArray[i];
                    if (file.type == 'directory') {
                        file_manager.tag = file_manager.folderTag.clone();
                        file_manager.tag.html(file.name);
                    } else if (file.type == 'file') {
                        file_manager.tag = file_manager.fileTag.clone();
                        file_manager.tag.attr({
                            extension: file.extension
                        });

                        var fileName = file.extension!=""?file.name + '.' + file.extension:file.name;
                        file_manager.tag
                            .html(fileName)
                            .attr('title', file.name);
                    }

                    file_manager.tag.attr({
                        id: file.id
                    }).data(file);

                    explorer.append(file_manager.tag);
                }

                _fn.initSelectable(explorer);
                _fn.initDraggable(j('file, folder'));
                _fn.initDroppable(j('folder'));
                _fn.initContextMenu();
                _fn.bindDoubleClick(explorer);
            }
        };

    j.fn.extend({
        fileManager: function(jsonData,namespace,currentPath) {
                return this.each(function() {
                    file_manager.files = jsonData;
                    file_manager.namespace = namespace;
                    file_manager.explorer = j(this);

                    file_manager.viewport = j(document).keydown(function(e) {
                        file_manager.CTRL = e.ctrlKey;
                        file_manager.CMND = e.metaKey;
                    }).keyup(function(e) {
                        file_manager.CTRL = e.ctrlKey;
                        file_manager.CMND = e.metaKey;
                    });

                    file_manager.explorer.addClass('file-manager-window');
                    _fn.renderNav(file_manager.explorer);

                    file_manager.folderTag = j('<folder></folder>');
                    file_manager.fileTag = j('<file></file>');

                    file_manager.viewport.delegate('folder, file', 'click', function() {
                        var el = j(this);
                        if (file_manager.CTRL || file_manager.CMND) {
                            el.toggleClass('ui-selected').toggleClass('selected');
                        } else {
                            el.siblings().removeClass('ui-selected').removeClass('selected');
                            el.addClass('ui-selected').addClass('selected');
                        }

                        _fn.getSelection();
                    });

                    file_manager.viewport.delegate('.toolbar-new-folder', 'click', function() {
                        var person = prompt("Enter the folder name", "New folder");
                    });

                    file_manager.viewport.delegate('.toolbar-gridview', 'click', function() {
                        file_manager.explorer.removeClass('list-view');
                    });

                    file_manager.viewport.delegate('.toolbar-listview', 'click', function() {
                        file_manager.explorer.addClass('list-view');
                    });

                    _fn.sort(file_manager.files, ['type', 'name'], true);

                    _fn.renderExplorer(file_manager.explorer, file_manager.files);

                    file_manager.explorer.click(function() {
                        if (!file_manager.CTRL && !file_manager.CMND) {
                            j('.ui-selected').removeClass('ui-selected');
                        }
                        _fn.getSelection();
                    });

                    _fn.initContextMenu();
                    
                    _fn.setCurrentPath(currentPath);
                });
            }, // fileManager
            fileManagerGetParentPath: function() {
            	var path = _fn.getCurrentPath();
            	var parentPath = '';
            	
            	if(path=='result'){
            		parentPath = path;
            	}else{
            		parentPath = path.substring(0,path.lastIndexOf('/'));
            	}
            	
            	return parentPath;
            }
    });
})(jQuery);
