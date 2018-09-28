(function(window){
	'use strict';
	
	if( window.DASH ){
		if( DASH.Constants )	return;
	}
	else
		window.DASH = {};
	
	DASH.Constants = {
		NODE_ID:'id',
		NODE_TEXT:'text',
		NODE_TYPE:'type',
		NODE_DATA:'data',
		DATA_FILE_ID:'FileId_',
		DATA_NODE_TYPE:'NodeType_',
		DATA_EXECUTE_ID:'ExecuteId_',
		DATA_BC:'bc',
		CON:'controll_',
		CON_FILE:'file',
		CON_ADD:'create',
		CON_SOLVER:'solver',
		CON_ANALYZER:'analyzer',
		CON_DELETE:'delete',
		CON_BCCREATE:'bccreate',
		CON_LOADBC:'loadbc',
		CON_EXPORT:'export',
		CON_MESH_VIEW:'mesh-view',
		ACC:'accodian_',
		ACC_PARAM:'parameter',
		ACC_BOUNDARY:'boundary',
		ACC_OPEN:'a_open_',
		NODE_VIEW_FILE:"view-icon",
		NODE_UNVIEW_FILE:"un-view-icon",
		NODE_CODE:"code-icon",
		TYPE_ROOT:'root',
		TYPE_ROOT_GEO:'root-geo',
		TYPE_ROOT_MESH:'root-mesh',
		TYPE_ROOT_BOUNDARY:'root-boundary',
		TYPE_VIEW_FILE:'view-file',
		TYPE_VIEW_DAT:'view-dat',
		TYPE_VIEW_MESH:'view-mesh',
		TYPE_VIEW_SURFACE:'view-surface',
		TYPE_GEO_PARAMETER:'geo-parameter',
		TYPE_MESH_PARAMETER:'mesh-parameter',
		GEO_FILE_EXTENSION:'obj,dat',
		MESH_FILE_EXTENSION:'p2d.zip,p3d.zip,bdf,p2d,p3d,zip',
		GEOMETRIES_PARENT_FOLDER_ID:'GEO',
		MESHES_PARENT_FOLDER_ID:'MESH',
		BOUNDARY_PARENT_FOLDER_ID:'BOUNDARY',
		BC_TYPE_GROUP_1:'group1',
		BC_TYPE_GROUP_2:'group2',
		BC_TYPE_GROUP_3:'group3',
		getShapeAnalysisParamApp:function(site){
			switch (site) {
				case 'KFLOW':
					return 'KFOIL_AirFoil_Para';
				case 'ETURB':
					return 'KFOIL_AirFoil_Para';
			}
		},
		getShapeAnalysisParamAppVersion:function(site){
			switch (site) {
				case 'KFLOW':
					return '1.0.1';
				case 'ETURB':
					return '1.0.0';
			}
		},
		getShapeAnalysisApp:function(site){
			switch (site) {
				case 'KFLOW':
					return 'KFOIL_AirFoil_Para_parin';
				case 'ETURB':
					return 'KFOIL_AirFoil_Para_parin';
			}
		},
		getShapeAnalysisAppVersion:function(site){
			switch (site) {
				case 'KFLOW':
					return '1.0.1';
				case 'ETURB':
					return '1.0.0';
			}
		},
		getMakeMeshApp:function(site){
			switch (site) {
			case 'KFLOW':
				return 'KGRID';
			case 'ETURB':
				return 'Mesher';
		}
		},
		MAKE_MESH_AERO_2D_VERSION:'1.0.0',
		getControllTypes : function(nodeType){
			var types = [];
			switch (nodeType) {
				case this.TYPE_ROOT_GEO:
					types.push(this.CON_FILE);
					break;
				case this.TYPE_ROOT_MESH:
					types.push(this.CON_FILE);
					types.push(this.CON_ADD);
					break;
				case this.TYPE_VIEW_FILE:
					types.push(this.CON_DELETE);
					break;
				case this.TYPE_VIEW_DAT:
					types.push(this.CON_ANALYZER);
					types.push(this.CON_DELETE);
					break;
				case this.TYPE_VIEW_MESH:
					types.push(this.CON_DELETE);
					types.push(this.CON_MESH_VIEW);
					types.push(this.CON_EXPORT);
					break;
				case this.TYPE_VIEW_SURFACE:
					types.push(this.CON_LOADBC);
					types.push(this.CON_DELETE);
					break;
				case this.TYPE_ROOT_BOUNDARY:
					types.push(this.CON_BCCREATE);
					break;
				default:
					break;
			}
			return types;
		},
		getAccodianTypes : function(nodeType){
			var types = [];
			switch (nodeType) {
				case this.TYPE_GEO_PARAMETER:
					types.push([this.ACC_PARAM,true]);
					break;
				case this.TYPE_VIEW_SURFACE:
					types.push([this.ACC_BOUNDARY,true]);
					break;
				case this.TYPE_ROOT_BOUNDARY:
					types.push([this.ACC_BOUNDARY,true]);
					break;
				default:
					break;
			}
			return types;
		},
		getSurfaceTypeGroups : function(){
			var types = [];
				types.push([this.BC_TYPE_GROUP_1,'진동해석']);
				types.push([this.BC_TYPE_GROUP_2,'공력해석']);
			return types;
		},
		getSurfaceTypes : function(groupText){
			var types = [];
			switch (groupText) {
				case this.BC_TYPE_GROUP_1:
					types.push(['Fixed','Fixed']);
					types.push(['Bonded','Bonded']);
					types.push(['Frictionless','Frictionless']);
					types.push(['Temperature','Temperature']);
					types.push(['Loading','Loading']);
					break;
				case this.BC_TYPE_GROUP_2:
					types.push(['IsothermalSurface','IsothermalSurface']);
					types.push(['AdiabaticSurface','AdiabaticSurface']);
					types.push(['Inlet','Inlet']);
					types.push(['Outlet','Outlet']);
					types.push(['MixingPlane','MixingPlane']);
					types.push(['BlockBoundary','BlockBoundary']);
					types.push(['Periodic','Periodic']);
					break;
				default:
					break;
			}
			return types;
		},
		getFileExtension : function(parentId){
			var exe = "";
			switch (parentId) {
			case this.GEOMETRIES_PARENT_FOLDER_ID:
				exe = this.GEO_FILE_EXTENSION;
				break;
			case this.MESHES_PARENT_FOLDER_ID:
				exe = this.MESH_FILE_EXTENSION;
				break;
			default:
				break;
			}
			return exe;
		},
		getTypeByFileName : function(fileName){
			var fileExtension = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
			var type = "";
			switch (fileExtension) {
			case "obj":
				type = this.TYPE_VIEW_FILE;
				break;
			case "dat":
				type = this.TYPE_VIEW_DAT;
				break;
			default:
				type = this.TYPE_VIEW_MESH;
				break;
			}
			return type;
		}
	};//End Constants
	
	DASH.node = function( jsonObject ){
		var node = this;
		OSP._MapObject.apply(node);
		
		node.id = function( id ){
			return node.property.apply( node, OSP.Util.addFirstArgument(DASH.Constants.NODE_ID, arguments) );
		};
		
		node.text = function( text ){
			return node.property.apply( node, OSP.Util.addFirstArgument(DASH.Constants.NODE_TEXT, arguments) );
		};
		
		node.type = function( type ){
			return node.property.apply( node, OSP.Util.addFirstArgument(DASH.Constants.NODE_TYPE, arguments) );
		};
		
		node.data = function(data){
			return node.property.apply( node, OSP.Util.addFirstArgument(DASH.Constants.NODE_DATA, arguments) );
		}
		
		node.addNode = function(text,type,data){
			if( arguments.length < 3 ){return false;}
			node.id(OSP.Util.guid());
			node.text(text);
			node.type(type);
			node.data(data);
			return true;
		}
	};

	DASH.data = function( jsonObject ){
		var data = this;
		OSP._MapObject.apply(data);
		
		
		data.executeId = function( fileId ){
			return data.property.apply( data, OSP.Util.addFirstArgument(DASH.Constants.DATA_EXECUTE_ID, arguments) );
		};
		
		
		data.file = function( fileId ){
			return data.property.apply( data, OSP.Util.addFirstArgument(DASH.Constants.DATA_FILE_ID, arguments) );
		};
		
		data.bcfunc = function( bc ){
			return data.property.apply( data, OSP.Util.addFirstArgument(DASH.Constants.DATA_BC, arguments) );
		};
		
		data.nodeType = function( nodeType ){
			return data.property.apply( data, OSP.Util.addFirstArgument(DASH.Constants.DATA_NODE_TYPE, arguments) );
		};
		
		
		
		data.deserialize = function( jsonObject ){
			for( var key in jsonObject ){
				data._deserialize( key, jsonObject[key] );
			}
		};
		
		if( arguments.length === 1 ){
			data.deserialize(jsonObject);
		}
	};
	
	DASH.meshApp = function(meshFileExt,isBC){
		alert(meshFileExt);
        var types = [];
        if(isBC){
        	types.push(['KFLOW_EDISON_Specialized','1.0.0']);
    		types.push(['PreFLOW_EDISON_Specialized','1.0.0']);
        }else{
        	if(meshFileExt == 'bdf'){
        		// 진동해석
        		types.push(['Turbine_snapshot','1.0.0']);
        		types.push(['Turbine_FOM','1.0.0']);
        		types.push(['Turbine_ROM','1.0.0']);
        	} else if(meshFileExt == 'p2d.zip') {
        		// 공력해석
        		types.push(['Aero_Turbine_2D','1.0.0']);
        	} else if(meshFileExt == 'kgrid.zip') {
        		types.push(['PreFLOW_EDISON_Specialized','1.0.0']);
        	}else if(meshFileExt == 'p3d.zip') {
        		// 공력해석
//            types.push(['KFLOW_Turbine_3D','1.0.0']);
        	}
        }
        return types;
    }
})(window);