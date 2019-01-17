<!DOCTYPE>
<html>
<head >
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/jstree/themes/default/style.min.css"/>
    <style>
	.inner-canvas{font-size:12px;}
    </style>
    <script src="<%=request.getContextPath()%>/js/jquery/jquery-3.1.0.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/jstree/jstree.min.js"></script>
</head>
<body>
	<div class="inner-canvas">
		<div id="panel" style="padding:0px;"></div>
	</div>
</body>
	<script>
	
		var namespace;
		var disabled = false;
		
		function setNamespace( ns ){
			console.log('jsp namespace: '+ns );
			namespace = ns;
		}
		
		function disable( flag ){
			disabled = flag;
		}
		
		function loadFileExplorer( folderPath, fileInfoList){
			var nodeDataAry = [];
			
			console.log('explorer: ', folderPath );
			if( folderPath !== '' && folderPath !== '/' ){
				var parentPath = (getParentPath( folderPath)) === '' ? '/' : getParentPath( folderPath);
				//console.log('ParentPath: '+parentPath);
				var parentNode = {
					'id': parentPath,
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
			else if( folderPath === '/' )
				folderPath = '';

			for(var index in fileInfoList ){
				var fileInfo = fileInfoList[index];
				var type = (fileInfo.isFile) ? 'file' : 'closed-folder';
				var obj = {
					'id': mergePath( folderPath, fileInfo.name),
					'text': fileInfo.name+' ['+fileInfo.size+']',
					'type':type,
					'li_attr': {
						'childLength': 0
					}
				};
				nodeDataAry.push(obj);
			}
			
			var rootData = [{
				'id':folderPath,
				'text':getFileName(folderPath),
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
								'check_callback' : true
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
							'keep_selected_style': true,
							'whole_node': false,
							'three_state':false,
							'visible': false
						},
						'plugins' : ['contextmenu', 'state', 'sort', 'types', 'checkbox']
				}).bind('loaded.jstree', function(event, data) { 
						$('#panel').jstree('open_all');
						$('#panel').jstree('deselect_all');
				}).bind('select_node.jstree',function(e, data){
						console.log( 'disabled: '+disabled );
						if( disabled === true )	return;
						
						var selectedNodes = $('#panel').jstree(true).get_selected();
						//console.log('selected nodes: ', selectedNodes );
						if( selectedNodes.length > 1 ){
							return;
						}
						
						if( data.node.type == 'file' ){
							var parentPath = getParentPath( data.node.id );
							//console.log('file: '+data.node.id);
							var fileName = getFileName( data.node.id );
							fireDataChangedEvent( parentPath, fileName, 'file' );
						}else if( data.node.type == 'closed-folder'){
							//console.log('closed-folder: '+data.node.id);
							fireDataChangedEvent( data.node.id, '' );
							lookupFolder( data.node.id );
						}
						else if( data.node.type == 'prev-folder' ){
							//console.log( 'prev-folder: '+data.node.id);
							var nextRootPath = data.node.id;

							fireDataChangedEvent( nextRootPath, '', 'folder' );
							lookupFolder( nextRootPath );
						}
				});
		}
		
		function fireDataChangedEvent( folderPath, fileName, type ){
			setTimeout(
				function(){
					console.log( 'namespace: '+namespace );
					if( namespace ){
						window.parent[namespace+'fireDataChangedEvent']( folderPath, fileName, type );
					}
					else{
						fireDataChangedEvent( folderPath, fileName, type );
					}
				},
				10
			);
		}
		
		function lookupFolder( folderPath ){
			//console.log( namespace );
			setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'lookupFolder']( folderPath );
					}
					else{
						lookupFolder( folderPath );
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
		
		function getSelectedFiles(){
			var selectedNodes = $('#panel').jstree(true).get_selected();
			selectedNodes.forEach(function(node, index){
				var lastIndex = node.lastIndexOf( '/' );
				if( lastIndex < 0)
					selectedNodes[index] = node;
				else
					selectedNodes[index] = node.slice( lastIndex+1 );
			});
			
			return selectedNodes;
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
</html>