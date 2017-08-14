(function(window){
	'use strict';

	if( window.OSP ){
		if( OSP.DataCollection )	return;
	}
	else
		window.OSP = {};
	
	OSP.DataCollection = function(){
		OSP._OpenObject.apply(this);

		this.dataType = function( dataTypeName, dataTypeVersion ){
			switch( arguments.length ){
			case 0:
				return this.property(OSP.Constants.DATA_TYPE);
			default:
				var dataType = {
						name: dataTypeName,
						version: dataTypeVersion
				};
				return this.property( OSP.Constants.DATA_TYPE, dataType );
			}
		};

		this.layout = function( layout ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.LAYOUT, arguments) );
		};

		this.layoutColumnPortlet = function( column, portletId ){
			var layout = this.layout();
			switch( arguments.length ){
			case 1:
				if( !layout )	return '';
				return layout[column];
			case 2:
				if( !layout ){
					layout = {};
					this.layout( layout );
				}

				layout[column] = portletId;
				return layout;
			default:
				console.log( 'Arguments count mismatch: layoutColumnPortlet('+arguments.length+')');
				return false;
			}
		};

		this.removeLayoutColumnPortlet = function( column ){
			var layout = this.layout();
			if( !layout )	return true;

			return delete layout[column];
		};

		this.code = function( code ){

		};
	}; // End of DataCollection

	OSP.DataEntry = function(){
		OSP._MapObject.apply(this);

		this.dataCollection = function( collectionName, collectionVersion ){
			switch( arguments.length ){
			case 0:
				return this.property(OSP.Constants.DATA_COLLECTION);
			default:
				var dataCollection = {
						name: collectionName,
						version: collectionVersion
				};
				return this.property( OSP.Constants.DATA_COLLECTION, dataCollection );
			}
		};

		this.scienceApp = function( scienceApp ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SCIENCE_APP, arguments) );
		};

		this.inputs = function( inputs ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.INPUTS, arguments) );
		};

		this.addInput = function( portName, inputData ){
			var inputs = this.inputs();
			if( ! inputs ){
				inputs = {};
				this.inputs( inputs );
			}

			inputs[portname] = inputData;
			return inputs;
		};

		this.inputData = function( portName ){
			var inputs = this.inputs();
			if( ! inputs )	return false;

			return inputs[portName];
		};

		this.removeInput = function( portName ){
			var inputs = this.inputs();
			if( ! inputs )	return true;

			delete inputs[portName];
			return inputs;
		};

		this.path = function( path ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.PATH, arguments) );
		};

		this.safePath = function(){
			var path = this.path();
			if( !path ){
				path = new OSP.Path();
				this.path( path );
			}
			return path;
		};

		this.setPath = function( parent, name, type, relative ){
			var path = this.safePath();
			return path.setPath.apply( path, parent, name, type, relative );
		};

		this.pathParent = function( parent ){
			var path = this.safePath();
			return path.parent.apply( path, parent );
		};

		this.pathType = function( type ){
			var path = this.safePath();
			return path.type.apply(path, type);
		};

		this.pathName = function( name ){
			var path = this.safePath();
			return path.name.apply(path, name);
		};

		this.pathRelative = function( relative ){
			var path = this.safePath();
			return path.relative.apply(path, relative);
		};

		this.clone = function(){
			return new OSP.Data( OSP.Util.toJSON(this) );
		};

		this.deserializeInputs = function( jsonObject ){
			var inputs = {};
			for( var portName in jsonObject ){
				var path = new OSP.Path( jsonObject[portName] );
				inputs[portName] = path;
			}

			this.inputs( inputs );
			return inputs;
		};

		this.deserializePath = function( jsonObject ){
			var path = new OSP.Path( jsonObject );
			this.path(path);
			return path;
		};
	}; // End of DataEntry

	OSP.Workflow = function( jsonObject ){
		OSP._OpenObject.apply(this);

		var ProceedCondition = function( jsonObject ){
			OSP._MapObject.apply(this);

			this.comparativeParam = function( param ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMPARATIVE_PARAM, arguments));
			};

			this.comparative = function( operator ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMPARATIVE, arguments));
			};

			this.comparativeValue = function( value ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.COMPARATIVE_VALUE, arguments));
			};

			this.updates = function( updates ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.UPDATES, arguments));
			};

			this.safeUpdates = function(){
				var updates = this.updates();
				if( !updates ){
					updates = {};
					this.updates(updates);
				}

				return updates;
			};

			this.addUpdate = function( param, value ){
				var updates = this.safeUpdates();
				updates[param] = value;

				return updates;
			};

			this.removeUpdate = function( param ){
				var updates = this.safeUpdates();
				delete updates[param];

				return updates;
			};

			this.action = function( action ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.ACTION, arguments));
			};

			this.check = function( value ){
				if( this.comparative() === '<' ){
					if( this.comparativeValue() < value )	return this.action();
				}
				else if( this.comparative() === '<=' ){
					if( this.comparativeValue() <= value )	return this.action();
				}
				else if( this.comparative() === '>' ){
					if( this.comparativeValue() > value )	return this.action();
				}
				else if( this.comparative() === '>=' ){
					if( this.comparativeValue() >= value )	return this.action();
				}
				else if( this.comparative() === '=' ){
					if( this.comparativeValue() == value )	return this.action();
				}
				else
					return false;
			};

			this.clone = function(){
				return new ProceedCondition( OSP.Util.toJSON(this) );
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of ProceedCondition
		this.newProceedCondition = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new ProceedCondition();
				case 1:
					return new ProceedCondition(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newProceedCondition');
					return null;
			}
		};

		var Connection = function( jsonObject ){
			OSP._MapObject.apply(this);
			OSP._StyleObject.apply(this);

			this.sourcePort = function( portName ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.SRC_PORT_NAME, arguments));
			};

			this.proceedConditions = function( conditions ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.PROCEED_CONDITIONS, arguments));
			};

			this.safeProceedConditions = function(){
				var conditions = this.proceedConditions();
				if( !conditions ){
					conditions = [];
					this.proceedConditions(conditions);
				}

				return conditions;
			};

			this.addProceedCondition = function( condition ){
				var conditions = this.safeProceedConditions();
				conditions.push( condition );
				return conditions;
			};

			this.removeProceedCondition = function( param ){
				var conditions = this.proceedConditions();
				if( !conditions )	return true;

				for( var index in conditions ){
					var condition = conditions[index];
					if( condition.comparativeParam() === param ){
						conditions.splice(index, 1);
						break;
					}
				}
				return conditions;
			};

			this.destinationTask = function( taskUuid ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TASK_UUID, arguments));
			};

			this.destinationPort = function( portName ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.DESTINATION_PORT, arguments));
			};


			this.clone = function(){
				return new Connection( OSP.Util.toJSON(this) );
			};

			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.PROCEED_CONDITIONS ){
						var conditionAry = jsonObject[key];
						for( var index in  conditionAry ){
							var condition = new ProceedCondition(conditionAry[index]);
							this.addProceedCondition(condition);
						}
					}
					else
						this.property( key, jsonObject[key] );
				}
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of Connection
		this.newConnection = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new Connection();
				case 1:
					return new Connection(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newConnection');
					return null;
			}
		};

		var Task = function( jsonObject ){
			OSP._OpenObject.apply(this);
			OSP._StyleObject.apply(this);

			this.appUuid = function( uuid ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.APP_UUID, arguments));
			};

			this.connections = function( connections ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.CONNECTIONS, arguments));
			};

			this.safeConnections = function(){
				var connections = this.connections();
				if( !connections ){
					connections = [];
					this.connections(connections);
				}

				return connections;
			};

			this.addConnection = function( connection ){
				var connections = this.safeConnections();
				connections.push( connection );
				return connections;
			};

			this.removeConnection = function( srcPort, dstUuid, dstPort ){
				var connections = this.connections();
				if( !connections )	return [];

				for( var index in connections ){
					var connection = connections[index];
					if( connection.sourcePort() === srcPort &&
						 connection.destinationTask() === dstUuid &&
						 connection.destinationPort() === dstPort ){
						 connections.splice( index, 1 );
						break;
					}
				}

				return connections;
			};

			this.inputs = function( inputs ){
				return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.INPUTS, arguments));
			};

			this.safeInputs = function(){
				var inputs = this.inputs();
				if( !inputs ){
					inputs = {};
					this.inputs( inputs );
				}

				return inputs;
			};

			this.inputData = function( portName, input ){
				switch( arguments.length ){
				case 1:
					var inputs = this.inputs();
					if( !inputs )	return false;
					return inputs[portName];
				case 2:
					var inputs = this.inputs();
					if( !inputs ){
						inputs = {};
						this.inputs = inputs;
					}
					inputs[portName] = input;
					return inputs;
				default:
					return false;
				}
			};

			this.removeInputData = function( portName ){
				var inputs = this.inputs();
				if( !inputs )	return true;
				delete inputs[portName];
				return inputs;
			};

			this.clone = function(){
				return new Task( OSP.Util.toJSON(this) );
			};

			this.execute = function(){

			};

			this.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.CONNECTIONS ){
						var jsonConnections = jsonObject[key];
						for( var index in jsonConnections ){
							this.addConnection( new Connection( jsonConnections[index] ) );
						}
					}
					else
						this.property( key, jsonObject[key] );
				}
			};

			if( arguments.length === 1 )
				this.deserialize(jsonObject);
		}; // End of Task

		this.newTask = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new Task();
				case 1:
					return new Task(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newTask');
					return null;
			}
		};
		this.tasks = function( tasks ){
			return this.property.apply(this, OSP.Util.addFirstArgument(OSP.Constants.TASKS, arguments));
		};

		this.addTask = function( task ){
			var tasks = this.tasks();
			if( !tasks ){
				tasks = [];
				this.tasks( tasks );
			}

			tasks.push( task );
			return tasks;
		};

		this.removeTask = function( taskUuid ){
			var tasks = this.tasks();
			if( !tasks )	return true;

			for( var index in tasks ){
				var task = tasks[index];
				if( task.uuid() === taskUuid ){
					tasks.splice( index, 1 );
					break;
				}
			}

			return tasks;
		};

		this.execute = function(){

		};

		this.clone = function(){
			return new OSP.Workflow( OSP.toJSON(this) );
		};

		if( arguments.length === 1 )
			this.deserialize(jsonObject);
	}; // End of Workflow

	OSP.Simulation = function( jsonObject ){
		OSP._OpenObject.apply(this);

		var Job = function( jsonObject ){

		};

		this.clone = function(){
			return new OSP.Simulation( OSP.toJSON(this) );
		};

		this.deserialize = function( jsonObject ){

		};

		if( arguments.length === 1 )
			this.deserialize(jsonObject);
	}; // End of Simulation
	
})(window);