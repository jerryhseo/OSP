<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="">
<head>
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jstree/jstree.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jstree/themes/default/style.min.css"/>
    <style>
	.inner-canvas{overflow: hidden; font-size:12px;}
    </style>
	<script>
		var namespace;
		var inDownload = false;
		
		function setNamespace( ns ){
			namespace = ns;
		}
		
		function loadFileExplorer( parentPath, fileList, width, height ){
			$('#canvas').width( width );
			$('#canvas').height( height );
			
			drawFileExplorer( fileList, parentPath );
		}
		
		function passSelectedFile( folderPath, fileName, type ){
			setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'setSelectedFile']( folderPath, fileName, type );
					}
					else{
						passSelectedFile( folderPath, fileName, type );
					}
				},
				10
			);
		}
		
		function lookupFolder( parentPath, folderName ){
			setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'lookupFolder']( parentPath, folderName );
					}
					else{
						lookupFolder( parentPath, folderName );
					}
				},
				10
			);
		}
		
		function mergePath( parent, child ){
			if( !parent && !child )	return '';
			if( !parent )
				return child;
			if( !child )
				return parent;
			
			return parent+'/'+child;
		}
		
		function showCheckbox( flag ){
			inDownload = flag;
			if( flag ){
				$('#panel').jstree().show_checkboxes();
			}
			else{
				$('#panel').jstree().hide_checkboxes();
			}
			
			// $('#panel').jstree().redraw(true);
		}
		
		function getSelectedFiles(){
			var selectedNodes = $('#panel').jstree(true).get_selected();
			for( var index in  selectedNodes ){
				var node = selectedNodes[index];
				if( node === '..' ){
					selectedNodes = OSP.Util.removeArrayElement(selectedNodes, index);
					break;
				}
			}
			
			return selectedNodes;
		}
		
		function drawFileExplorer( fileInfoList, parentPath )
		{
			var nodeDataAry = [];
			
			if( parentPath !== '' ){
				var parentNode = {
					'id': getParentPath( parentPath) === '' ? '/' : getParentPath( parentPath),
					'text': '..',
					'type': 'prev-folder',
					'li_attr': {
						'childLength': 0
					},
					'a_attr': {
						'class': 'no_checkbox'
					}
				};
				
				nodeDataAry.push( parentNode );
			}

			for(var index in fileInfoList ){
				var fileInfo = fileInfoList[index];
				var type = (fileInfo.isFile) ? 'file' : 'closed-folder';
				var obj = {
					'id': mergePath( parentPath, fileInfo.name),
					'text': fileInfo.name+' ['+fileInfo.size+']',
					'type':type,
					'li_attr': {
						'childLength': 0
					}
				};
				nodeDataAry.push(obj);
			}
			
			var rootData = [{
				'id':parentPath,
				'text':(parentPath === '') ? '' : parentPath,
				'type':'opened-folder',
				'children': nodeDataAry,
				'li_attr':{
					'childLength' : nodeDataAry.length			
				}
			}];
			
			if($.jstree.reference($('#panel')))
			    $('#panel').jstree().destroy();
				// $('#panel').jstree(true).settings.core.data = rootData;
				// $('#panel').jstree(true).refresh();
				$('#panel').jstree({
						'core' : {
								'data': rootData,
								'check_callback' : true,
								'multiple': true
						},
						'state' : 'open',
						'types' : {
								'prev-folder':{
										'icon' : '<%=request.getContextPath()%>/js/jstree/images/myfile-icon02.png'
								},
								'opened-folder' : {
										'icon' : '<%=request.getContextPath()%>/js/jstree/images/myfile-icon01.png'
								},
								'closed-folder' : {
										'icon' : '<%=request.getContextPath()%>/js/jstree/images/myfile-icon02.png'
								},
								'file' : {
										'icon' : '<%=request.getContextPath()%>/js/jstree/images/fileicon.png'
								}
						},
						'sort': function (a, b) {
								return this.get_text(a).toLowerCase() > this.get_text(b).toLowerCase() ? 1 : -1; 
						},
						'checkbox': {
							'keep_selected_style': false,
							'visible': inDownload
						},
						'progressive_render' : true,
						'plugins' : ['contextmenu', 'state', 'dnd', 'sort', 'types', 'progressive_render' , 'hotkeys', 'changed', 'checkbox']
				}).bind('loaded.jstree', function(event, data) { 
						$('#panel').jstree('open_all');
						$('#panel').jstree('deselect_all');
				}).bind('select_node.jstree',function(e, data){
						if( data.node.type == 'file' ){
							var parentPath = getParentPath( data.node.id );
							var folderName = getFileName( data.node.id );
							if( !inDownload ){
								passSelectedFile( parentPath, folderName, 'file' );
							}
						}else if( data.node.type == 'closed-folder'){
							var parentPath = getParentPath( data.node.id );
							var folderName = getFileName( data.node.id );
							if( !inDownload ){
								passSelectedFile( parentPath, folderName );
							}
							lookupFolder( parentPath, folderName );
						}
						else if( data.node.type == 'prev-folder' ){
							var folderName = '';
							var nextRootPath = getParentPath( data.node.id );
							var selectFolderName = getFileName( data.node.id );

							console.log( 'prev-folder: '+nextRootPath+', '+selectFolderName);
							if( !inDownload ){
								passSelectedFile( nextRootPath, '' );
							}
							lookupFolder( nextRootPath, '' );
						}
				});
		}

		function getParentPath( path ){
		    var slashIndex = path.lastIndexOf('/');
			var parentPath = slashIndex > 0 ? path.substring(0, slashIndex) : '';
			return parentPath;
		}
		
		function getFileName( path ){
		    var slashIndex = path.lastIndexOf('/');
			var fileName = slashIndex >= 0 ? path.substring(slashIndex+1) : path;
			return fileName;
		}
		
		function mergePath( parent, child ){
			if( !parent && !child )	return '';
			if( !parent )
				return child;
			if( !child )
				return parent;
			
			return parent+'/'+child;
		}
	</script>
</head>
<body style="">
	<div class="inner-canvas" id="panel"></div>
</body>
</html>