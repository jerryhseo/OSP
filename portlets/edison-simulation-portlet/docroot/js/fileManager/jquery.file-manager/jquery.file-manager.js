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
            contextmenu: {
                general: [{
                	name: 'New Folder',
                	img: '',
                	title: 'New Folder',
                	fun: function(){
                		_fileManager_createNewFolder();
                	}
                },{
                	name: 'Paste',
                	img: '',
                	title: 'Paste',
                	fun: function(){
                		var selectedItem = file_manager.explorer.find('.ui-selected');
                		if(selectCopyNode!==null){
                			_fileManager_pasteNode(selectedItem, false);
                		}
                	}
                }, {
                	name: 'Info',
                	img: '',
                	title: 'Info',
                	fun: function(){
                		var selectedItemId = j('.breadcrumb_item.sub.selected').attr('id');
                		_fileManager_viewInfo(selectedItemId, 'folder', true);
                	}
                }],
                
                folderItem: [{
                	name: 'Rename',
                	img: '',
                	title: 'Rename',
                	fun: function(){
                		var selectedItem = file_manager.explorer.find('.ui-selected');
                		_fileManager_renameFolder(selectedItem);
                	}
                }, {
                	name: 'Delete',
                	img: '',
                	title: 'Delete',
                	fun: function(){
                		var selectedItem = file_manager.explorer.find('.ui-selected');
                		_fileManager_deleteFolder(selectedItem);
                	}
                }, {
                	name: 'Paste',
                	img: '',
                	title: 'Paste',
                	fun: function(){
                		var selectedItem = file_manager.explorer.find('.ui-selected');
                		_fileManager_pasteNode(selectedItem, true);
                	}
                }, {
                	name: 'Info',
                	img: '',
                	title: 'Info',
                	fun: function(){
                		var selectedItemId = file_manager.explorer.find('.ui-selected').attr("id");
                		_fileManager_viewInfo(selectedItemId, 'folder');
                	}
                }],

                fileItem: [{
                    name: 'Download',
                    img: '',
                    title: 'File Download',
                    fun: function(e) {
                    	var selectedItem = file_manager.explorer.find('.ui-selected');
                    	_fileManager_fileDownload(selectedItem);
                    }
                }, {
                	name: 'Delete',
                	img: '',
                	title: 'delete',
                    fun: function(e) {
                    	var selectedItem = file_manager.explorer.find('.ui-selected');
                    	_fileManager_fileDelete(selectedItem);
                    }
                }, {
                	name: 'Copy',
                	img: '',
                	title: 'Copy',
                	fun: function(){
                		var selectedItem = file_manager.explorer.find('.ui-selected');
                		_fileManager_copyNode(selectedItem, false);
                	}
                }, {
                	name: 'Info',
                	img: '',
                	title: 'Info',
                	fun: function(){
                		var selectedItemId = file_manager.explorer.find('.ui-selected').attr("id");
                		_fileManager_viewInfo(selectedItemId, 'file');
                	}
                }]
            }
        },
        _fn = {
            renderNav: function(el) {
                /*j.get('./js/jquery.file-manager/nav-template.htm', function(nav, textStatus, jqXHR) {
                    el.before(nav);
                    file_manager.searchBox = j('.fileSearchBox');
                });*/
            },

            initContextMenu: function() {
                file_manager.explorer.find('.bg').contextMenu(file_manager.contextmenu.general, {
                    triggerOn: 'contextmenu'
                });

                file_manager.explorer.find('folder').contextMenu(file_manager.contextmenu.folderItem, {
                    triggerOn: 'contextmenu'
                });
                
                file_manager.explorer.find('file').contextMenu(file_manager.contextmenu.fileItem, {
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
                        
                        var selectedItem = file_manager.explorer.find('.ui-selected');
                        _fileManager_moveNode(selectedItem, j(this));
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
                    changeBreadCrumb(folder.data());
                    viewSelectedFolder(folder.data().id);
                });
                
                j('file').dblclick(function(e) {
                	
                	// Popup인 경우 dblclick 시 파일 선택
                	if($("input[name='isPopUp'").val() == 'true'){
                		_fileManager_selectBtn('dbl');
                	}
                });
            },

            drop: function(item, container) {
                /*console.clear();
                print(item.data());
                print(container.data());*/
            },

            renderExplorer: function(explorer, filesArray) {
                explorer.html('<div class="bg" style="position:absolute;top:0;left:0;right:0;bottom:0;z-index:0;"></div>');
                
                var listTypeTitleTable = j("<table/>").addClass("list-view-file-info")
                									  .css("display", "none").css("width", "100%")
                									  .css("border-bottom", "1px solid #f0f0f0").css("font-size", "15px");
                var titleTr = j("<tr/>");
                j("<td/>").css("width", "26%").css("text-align","center").text("Name").appendTo(titleTr);
                j("<td/>").css("width", "14%").css("text-align","center").text("Modified Date").appendTo(titleTr);
                j("<td/>").css("width", "10%").css("text-align","center").text("Size").appendTo(titleTr);
                j("<td/>").css("width", "26%").css("text-align","center").text("Name").appendTo(titleTr);
                j("<td/>").css("width", "14%").css("text-align","center").text("Modified Date").appendTo(titleTr);
                j("<td/>").css("width", "10%").css("text-align","center").text("Size").appendTo(titleTr);
                
                titleTr.appendTo(listTypeTitleTable);
                
                explorer.append(listTypeTitleTable);
                explorer.append("<div class='h10'></div>");
                
                for (var i = 0; i < filesArray.length; i++) {
                    var file = filesArray[i];
                    if (file.type == 'directory') {
                        file_manager.tag = file_manager.folderTag.clone();
                        
                        file_manager.tag.attr("nodeType", "directory");
                        
                        var gridTypeFileView = j("<span/>").addClass("grid-view-file-info").css("display","block").text(file.name)
                        var listTypeFileViewTable = j("<table/>").addClass("list-view-file-info").css("display", "none").css("width", "100%");
                        var tr = j("<tr/>")
                        j("<td/>").css("width", "50%").text(file.name).appendTo(tr);
                        j("<td/>").css("width", "30%").css("text-align", "center").text(file.lastModified).appendTo(tr);
                        j("<td/>").css("width", "20%").css("text-align", "center").text(file.size + " byte").appendTo(tr);
                        
                        tr.appendTo(listTypeFileViewTable);
                        
                        file_manager.tag
                        			.append(gridTypeFileView)
                        			.append(listTypeFileViewTable);
                        
                        /*file_manager.tag.html(file.name);*/
                        
                    } else if (file.type == 'file') {
                        file_manager.tag = file_manager.fileTag.clone();
                        file_manager.tag.attr({
                            extension: file.extension
                        });
                        
                        file_manager.tag.attr("nodeType", "file");
                        
                        var gridTypeFileView = j("<span/>").addClass("grid-view-file-info").css("display","block").text(file.name)
                        var listTypeFileViewTable = j("<table/>").addClass("list-view-file-info").css("display", "none").css("width", "100%");
                        var tr = j("<tr/>")
                        j("<td/>").css("width", "50%").text(file.name).appendTo(tr);
                        j("<td/>").css("width", "30%").css("text-align", "center").text(file.lastModified).appendTo(tr);
                        j("<td/>").css("width", "20%").css("text-align", "center").text(file.size + " byte").appendTo(tr);
                        
                        tr.appendTo(listTypeFileViewTable);
                        
                        file_manager.tag
                        			.append(gridTypeFileView)
                        			.append(listTypeFileViewTable)
                					.attr('title', file.name);
                        
                        /*file_manager.tag
                            .html(file.name)
                            .attr('title', file.name);*/
                    }

                    file_manager.tag.attr({
                        id: file.id,
                        class: 'fileManager_searchNode',
                        node_name: file.name
                    }).data(file);

                    explorer.append(file_manager.tag);
                }
                
                if($("#fileManagerViewType").val() === 'list'){
                	/* tableHeader and folder(file) data info view */
            		$(".toolbar-listview").click();
            	}

                _fn.initSelectable(explorer);
                _fn.initDraggable(j('file, folder'));
                _fn.initDroppable(j('folder'));
                _fn.initContextMenu();
                _fn.bindDoubleClick(explorer);
            }
        };

    j.fn.extend({
        fileManager: function(jsonData) {
                return this.each(function() {
                    file_manager.files = jsonData;
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

                    file_manager.explorer.delegate('.bg', 'click', function() {
                        file_manager.explorer.find('.bg, folder, file').contextMenu('close');
                    });

                    file_manager.viewport.delegate('.fileSearchBox', 'keyup', function() {
                        var key = j(this).val(),
                            contents = j('file, folder').show();
                        if (key == '' || key == undefined) {
                            contents.show();
                        } else {
                            contents.not(':contains("' + key + '")').hide();
                        }
                    }).delegate('.fileSearchBox', 'focusin', function() {
                        j(this).animate({
                            width: "350px"
                        }, 500);
                    }).delegate('.fileSearchBox', 'focusout', function() {
                        j(this).animate({
                            width: "172px"
                        }, 500);
                    });

                    file_manager.viewport.delegate('folder, file', 'click', function(e) {
                        var el = j(this);
                        
                        if (file_manager.CTRL || file_manager.CMND) {
                            el.toggleClass('ui-selected').toggleClass('selected');
                        } else {
                            el.siblings().removeClass('ui-selected').removeClass('selected');
                            el.addClass('ui-selected').addClass('selected');
                        }

                        _fn.getSelection();
                    });
                    
                    file_manager.viewport.delegate('folder, file', 'mousedown', function(e) {
    	                var el = j(this);
    	                
    	                if (file_manager.CTRL || file_manager.CMND) {
    	                    el.toggleClass('ui-selected').toggleClass('selected');
    	                } else {
    	                    el.siblings().removeClass('ui-selected').removeClass('selected');
    	                    el.addClass('ui-selected').addClass('selected');
    	                }
    	
    	                _fn.getSelection();
    	            });
                    
                    // folder, file grid view
                    file_manager.viewport.delegate('.toolbar-gridview', 'click', function() {
                        file_manager.explorer.removeClass('list-view');
                        j(".grid-view-file-info").show();
                        j(".list-view-file-info").hide();
                        $("#fileManagerViewType").val("grid")
                    });
                    
                    // folder, file list view
                    file_manager.viewport.delegate('.toolbar-listview', 'click', function() {
                        file_manager.explorer.addClass('list-view');
                        j(".grid-view-file-info").hide();
                        j(".list-view-file-info").show();
                        $("#fileManagerViewType").val("list");
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
                });
            } // fileManager
    });
})(jQuery);