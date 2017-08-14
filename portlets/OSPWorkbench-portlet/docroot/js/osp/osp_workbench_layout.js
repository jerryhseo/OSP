(function(window){
	'use strict';

	if( window.OSP ){
		if( OSP.Layout )	return;
	}
	else
		window.OSP = {};
	
	OSP.Layout = function( jsonLayout ){
		var Layout = this;
		OSP._MapObject.apply( Layout );

		var Portlet = function( jsonPortlet ){
			var Portlet = this;
			OSP._MapObject.apply(Portlet);

			Portlet.instanceId = function( instanceId ){
				return Portlet.property.apply(Portlet, OSP.Util.addFirstArgument(OSP.Constants.INSTANCE_ID, arguments));
			};
			
			Portlet.portName = function( portName ){
				if( portName === '' )	return false;
				return Portlet.property.apply(Portlet, OSP.Util.addFirstArgument(OSP.Constants.PORT_NAME, arguments));
			};

			Portlet.generateInstanceId = function( instanceIndex ){
				var instanceId = Portlet.instanceId();
				if( instanceId.indexOf('_INSTANCE_') > 0)
					instanceId = instanceId.slice(0, instanceId.indexOf('_INSTANCE_'));
				
				var instanceString;
				var idStr = "" + instanceIndex;
				var pad = "0000";
				var instanceString = pad.substring(0, pad.length - idStr.length) + idStr;
				Portlet.instanceId(instanceId+'_INSTANCE_'+instanceString);
				
				return Portlet.instanceId();
			};
			
			Portlet.getRootId = function(){
				var instanceId = Portlet.instanceId();
				var index = instanceId.indexOf('_INSTANCE_');
				if( index < 0 )
					return instanceId;
				else
					return instanceId.slice(0, instanceId.indexOf('_INSTANCE_'));
			};

			Portlet.getNamespace = function(){
				return '_'+Portlet.instanceId()+'_';
			};

			Portlet.data = function( data ){
				return Portlet.property.apply(Portlet, OSP.Util.addFirstArgument(OSP.Constants.DATA, arguments));
			};

			Portlet.preferences = function( preferences ){
				return Portlet.property.apply(Portlet, OSP.Util.addFirstArgument(OSP.Constants.PREFERENCES, arguments));
			};

			Portlet.addPreference = function( key, value ){
				var preferences = Portlet.preferences();
				switch( arguments.length){
				case 1:
					if( !preferences )	return '';
					return preferences[key];
				case 2:
					if( !preferences ){
						preferences = [];
						Portlet.preferences( preferences );
					}
					return preferences[key] = value;
				default:
					console.log( 'Arguments mismatch: Portlet.preference.');
					return false;
				}
			};

			Portlet.removePreference = function( preference ){
				var preferences = Portlet.preferences();
				if( !preferences )	return true;
				return delete preferences[preference];
			};

			Portlet.clone = function(){
				return new Portlet( OSP.Util.toJSON(Portlet) );
			};

			Portlet.deserialize = function( jsonPortlet ){
				for( var key in jsonPortlet ){
					switch( key ){
					case OSP.Constants.INSTANCE_ID:
					case OSP.Constants.PREFERENCES:
					case OSP.Constants.PORT_NAME:
					case OSP.Constants.DATA:
						Portlet.property( key, jsonPortlet[key] );
						break;
					default:
						console.log( 'Un-recognizable Portlet property: '+key);
					}
				}
			};

			if( arguments.length === 1 )
				Portlet.deserialize( jsonPortlet );
		}; // End of Portlet
		Layout.newPortlet = function(jsonPortlet ){
			return new Portlet(jsonPortlet);
		};
		
		var Column = function( jsonColumn ){
			var Column = this;
			OSP._MapObject.apply(Column);
			
			// Column Definitions
			Column.id = function( id ){
				return Column.property.apply(Column, OSP.Util.addFirstArgument(OSP.Constants.ID, arguments));
			};
			
			Column.height = function( height ){
				return Column.property.apply(Column, OSP.Util.addFirstArgument(OSP.Constants.HEIGHT, arguments));
			};
			
			Column.currentPortlet = function( instanceId ){
				return Column.property.apply(Column, OSP.Util.addFirstArgument(OSP.Constants.CURRENT_PORTLET, arguments));
			};

			Column.portlets = function( portlets ){
				return Column.property.apply(Column, OSP.Util.addFirstArgument(OSP.Constants.PORTLETS, arguments));
			};
			
			Column.getPortlet = function( instanceId /* or port name */){
				var portlets  = Column.portlets();
				if( !portlets )	false;
				
				var portName = instanceId;
				for( var index in portlets ){
					var portlet = portlets[index];
					if( instanceId === portlet.instanceId() || portName === portlet.portName() )
						return portlet;
				}
				return false;
			};
			
			Column.addPortlet = function( portlet ){
				var portlets = Column.portlets();
				if( !portlets ){
					portlets = [];
					Column.portlets(portlets);
				}

				var portName = portlet.portName();
				
				for( var index in portlets ){
					var savedPortlet = portlets[index];
					if( portlet.instanceId() === savedPortlet.instanceId() ){
						console.log('ERROR]portlet is already assigned to the column: '+portlet.instanceId());
						return false;
					}
					else if( portlet.portName() && portlet.portName() === savedPortlet.portName() ){
						savedPortlet.instanceId( portlet.instanceId() );
						var preferences = portlet.preferences();
						if( preferences ){
							savedPortlet.preferences(preferences);
							return portlets;
						}
					}
				}
				
				portlets.push(portlet);
				
				return portlets;
			};
			
			Column.getAssignedPortNames = function(){
				var portlets = Column.portlets();
				if( !portlets ){
					console.log('[ERROR]no AssignedPorts to getAssignedPortNames');
					return false;
				}
				
				var portNames = [];
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portlet.portName() && portlet.portName() !== '' )
						portNames.push(portlet.portName());
				}
				
				return portNames;
			};
			
			// if portName is a portlet id, then specified connection removed also
			var removePortlet = function( instanceId /* or port name */){
				var portlets = Column.portlets();
				if( !portlets ){
					console.log('[ERROR]no portlets to be removed: '+portlerId);
					return false;
				}
				var portName = instanceId;
				
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portName === portlet.portName() || instanceId === portlet.instanceId()){
						OSP.Util.removeArrayElement(portlets, index);
						return portlets;
					}
				}
				
				return false;
			};
			
			Column.isAssigned = function( portName /* or portlet instance id */ ){
				var portlets = Column.portlets();
				if( !portlets ){
					return false;
				}
				
				var instanceId = portName;
				for( var index in portlets ){
					var portlet = portlets[index];
					if( portName === portlet.portName() || instanceId === portlet.instanceId() ){
						return true;
					}
				}
				return false;
			};
			
			Column.getAssignedPortName = function( instanceId ){
				var portlets = Column.portlets();
				if( !portlets )	return false;
				
				for( var index in portlets ){
					var portlet = portlets[index];
					if( instanceId === portlet.instanceId() )
						return portlet.portName();
				}
				
				return false;
			};
			
			Column.switchDisplayPortlet = function( namespace, instanceId/* or port name*/, renderURL){
				var portlet = Column.getPortlet(instanceId);
				if( !portlet ){
					console.log('[ERROR]no target portlet or port name: '+instanceid);
					return false;
				}
				
				var portlet = Column.getPortlet(instanceId);
				
				Column.currentPortlet(portlet.instanceId());
				var sectionId = Column.getPortletSectionId(namespace);
				
				renderURL.setPortlet( portlet.instanceId);
				
				$('#'+sectionId).load(renderURL);
				
				return Column.currentPortlet();
			};
			
			Column.adjustSectionHeight = function( namespace, height ){
				$('#'+Column.getPortletSectionId(namespace)).outerHeight( height*Column.height() );
			};
			
			Column.getPortletSectionId = function( namespace ){
					return namespace+Column.id();
			};
			
			Column.clone = function(){
				return new Column( OSP.Util.toJSON(Column) );
			};

			Column.deserialize = function( jsonColumn ){
				for( var key in jsonColumn ){
					switch( key ){
					case OSP.Constants.ID:
					case OSP.Constants.HEIGHT:
					case OSP.Constants.CURRENT_PORTLET:
						Column.property( key, jsonColumn[key] );
						break;
					case OSP.Constants.PORTLETS:
						var jsonPortlets = jsonColumn[key];
						for( var index in jsonPortlets ){
							Column.addPortlet( new Portlet(jsonPortlets[index]) );
						}
						break;
					default:
						console.log( 'Un-recognizable Portlet property: '+key);
					}
				}
			};
			
			if( arguments.length === 1 )
				Column.deserialize( jsonColumn );
			
		}; /* End of Column */
		Layout.newColumn = function( jsonColumn ){
			return new Column( jsonColumn );
		};
		
		Layout.templateId = function( templateId ){
			return Layout.property.apply(Layout, OSP.Util.addFirstArgument(OSP.Constants.TEMPLATE_ID, arguments));
		};
		
		Layout.height = function( templateId ){
			return Layout.property.apply(Layout, OSP.Util.addFirstArgument(OSP.Constants.HEIGHT, arguments));
		};

		Layout.columns = function( columns ){
			return Layout.property.apply(Layout, OSP.Util.addFirstArgument(OSP.Constants.COLUMNS, arguments));
		};

		Layout.getColumnIds = function(){
			var columns = Layout.columns();
			if( !columns )	return [];
			
			var columnIds = [];
			for( var index in columns ){
				var column = columns[index];
				columnids.push( column.id() );
			}
			return columnIds;
		};

		Layout.getPortlet = function( instanceId /* or port name */ ){
			var columns = Layout.columns();
			if( !columns ){
				console.log('[ERROR]there is no columns to be searched: ');
				return false;
			}
			var portName = instanceId;

			for( var index in columns ){
				var column  = columns[index];
				
				if( column.isAssigned( portName ) )
					return column.getPortlet(instanceId);
			}
			return false;
		};
		
		Layout.addColumn = function( column ){
			var columns = Layout.columns();
			if( !columns ){
				columns = [];
				Layout.columns(columns);
			} 
			
			columns.push(column);
			return columns;
		};

		Layout.getColumn = function( columnId /* or port name or instance id */ ){
			var columns = Layout.columns();
			if( !columns )	return false;
			
			var instanceId = columnId;
			
			for( var index in columns ){
				var column = columns[index];
				if( column.id() === columnId )
					return column;
				else{
					if( column.isAssigned(instanceId) ){
						return column;
					}
				}
			}
			
			return false;
		};
		
		Layout.getAssignedPortName = function( portletId ){
			var columns = Layout.columns();
			if( !columns )	return false;
			
			for( var index in columns ){
				var column = columns[index];
				var portName = column.getAssignedPortName( portletId );
				if( portName != false )
					return portName;
			}
			
			return false;
		};
		
		Layout.removeColumn = function( columnId ){
			var columns = Layout.columns();
			if( !columns )	return true;
			
			for( var index in columns ){
				var column = columns[index];
				if( column.id() === columnId )
					return OSP.Util.removeArrayElement(columns, index);
			}
			
			return false;
		};
		
		Layout.hasPortlet = function( instanceId ){
			var columns = Layout.columns();
			if( !columns )	return [];
			
			for( var index in columns ){
				var column = columns[index];
				if( column.isAssigned(instanceId) === true )
					return true;
			}
			
			return false;
		};
		
		var retriveRootPortlets = function( rootPortletId ){
			var columns = Layout.columns();
			if( !columns )	return [];
			
			var retrieved = [];
			for( var index in columns ){
				var column = columns[index];
				var portlets = column.portlets();
				for( var idx in portlets ){
					var portlet = portlets[idx];
					if( portlet.getRootId() === rootPortletId )
						retrieved.push(portlet);
				}
			}
			
			return retrieved;
		};
		
		Layout.addPortlet = function( columnId, rootId, display, portName, preferences ){
			var column = Layout.getColumn(columnId);
			if( !column ){
				column = new Column();
				column.id(columnId);
				Layout.addColumn(column);
			}
			
			var portlet = new Portlet();
			portlet.instanceId(rootId);
			if( portName )	portlet.portName(portName);
			if( preferences )	portlet.preferences( preferences );
			var retrievedPortlets = retriveRootPortlets(rootId);
			switch( retrievedPortlets.length ){
				case 0:
					column.addPortlet( portlet );
					if( display )
						column.currentPortlet(portlet.instanceId());
					return column;
				case 1:
					var prevId = retrievedPortlets[0].instanceId();
					var prevColumn = Layout.getColumn(prevId);
					retrievedPortlets[0].generateInstanceId( 1 );
					if( prevId === prevColumn.currentPortlet() )
						prevColumn.currentPortlet( retrievedPortlets[0].instanceId() );
					
					portlet.generateInstanceId( 2 );
					column.addPortlet(portlet);
					if( display )
						column.currentPortlet(portlet.instanceId());
					return column;
				default:
					var instanceIndex = 1;
					var duplicated = false;
					do{
						for( var index in retrievedPortlets ){
							portlet.generateInstanceId(instanceIndex);
							if( retrievedPortlets[index].instanceId() === portlet.instanceId() ){
								duplicated = true;
								instanceIndex++;
							}
							else
								duplicated = false;
						}
					}while( duplicated );

					column.addPortlet(portlet);
					if( display )
						column.currentPortlet(portlet.instanceId());
					return column;
			}
		};
		
		Layout.removeColumn = function( columnId ){
			var columns = Layout.columns();
			
			for( var index in columns ){
				var column = columns[index];
				if( columnId === column.id() )
					return OSP.Util.removeArrayElement(columns, index);
			}

			return false;
		};

		Layout.removeColumnPortlet = function( columnId, portletId /* or port name */){
			var column = Layout.column(columnId);
			if( !column ){
				console.log( "[ERROR]column does not exist: "+columnId);
				return false;
			}
			
			return column.removePortlet(portletId);
		};

		Layout.loadPortlets = function( namespace, windowState ){
			AUI().use('liferay-portlet-url', function(A){
				var columns = Layout.columns();
				if( !columns )	return false;
				
				for( var index in columns ){
					var column = columns[index];
					var columnId = column.getPortletSectionId(namespace);
					
					var portletURL = Liferay.PortletURL.createRenderURL();
					portletURL.setPortletId( column.currentPortlet());
					portletURL.setWindowState(windowState);
					
					console.log('Portlet ID: '+column.currentPortlet());
					$('#'+columnId).load( 
							portletURL.toString(), 
							{async:false}, 
							function( result ){
								var eventData = {
									portletId: OSP.Event.stripNamespace(namespace),
									targetPortlet: OSP.Event.Constants.BROADCAST
								};
								Liferay.fire(OSP.Event.OSP_HANDSHAKE, eventData);
								console.log('HANDSHAKE fired');
							}
					);
				}
			}); 
		};
		
		Layout.switchDisplayColumnPortlet = function( namespace, columnId, toPortletId /* or port name */, renderURL ){
			var column = Layout.getColumn(columnId);
			if( !column ){
				console.log('[ERROR]no column: '+columnId);
				return false;
			}
			
			return column.switchDisplayPortlet(namespace, toPortletId, renderURL);
		};

		Layout.getPortletSectionId = function( namespace, instanceId /* or port name */ ){
			var column = Layout.getColumn( instanceId );
			if( !column ){
				console.log('[ERROR]no columns for: '+instanceId);
				return false;
			}

			return column.getPortletSectionId(namespace);
		};
		
		Layout.clone = function(){
			return new OSP.Layout( OSP.Util.toJSON(Layout) );
		};

		Layout.deserialize = function( jsonLayout ){
			for( var key in jsonLayout ){
				switch( key ){
					case OSP.Constants.TEMPLATE_ID:
					case OSP.Constants.HEIGHT:
						Layout.property( key, jsonLayout[key] );
						break;
					case OSP.Constants.COLUMNS:
						var columnsJson = jsonLayout[key];
						var columns = [];
						for( var index in columnsJson ){
							var column = new Column( columnsJson[index] );
							columns.push(column);
						}
						Layout.columns(columns);
						break;
					default:
						console.log("Un-recognizable key: "+ key);
				}
			}
		};
		
		if( arguments.length === 1 )
			Layout.deserialize( jsonLayout );
		
		//return Layout;
	}; // End of Layout
	OSP.newLayout = function( jsonLayout ){
		return new OSP.Layout( jsonLayout );
	};
	
})(window);