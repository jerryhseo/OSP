(function(window){
	'use strict';

	if( window.OSP ){
		if( OSP.ScienceApp )	return;
	}
	else
		window.OSP = {};

	OSP.ScienceApp = function(){
		var ScienceApp = this;
		OSP._OpenObject.apply(ScienceApp);

		var _Port = function( jsonObject ){
			var _Port = this;
			OSP._MapObject.apply(_Port);

			_Port.name = function( name ){
				if( !_Port.verifyName(name) )	return false;

				return _Port.property.apply(_Port, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments));
			};

			_Port.mandatory = function( mandatory ){
				return _Port.property.apply(_Port, OSP.Util.addFirstArgument(OSP.Constants.MANDATORY, arguments));
			};

			_Port.dataType = function( dataTypeName, dataTypeVersion ){
				switch( arguments.length ){
				case 0:
					return _Port.property(OSP.Constants.DATA_TYPE);
				case 1:
					return _Port.property(OSP.Constants.DATA_TYPE, dataTypeName);
				case 2:
					var dataType = {
						name: dataTypeName,
						version: dataTypeVersion
					};
					return _Port.property(OSP.Constants.DATA_TYPE, dataType);
				default:
					return false;
				}
			};

			_Port.defaultEditor = function( scienceApp ){
				return _Port.property.apply(_Port, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_EDITOR, arguments));
			};

			_Port.defaultAnalyzer = function( scienceApp ){
				return _Port.property.apply(_Port, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_ANALYZER, arguments));
			};

			_Port.getAvailableEditors = function( url, params ){

			};

			_Port.getAvailableAnalyzers = function( url, params ){

			};

			_Port.verifyName = function(name){
				if( !name )	return true;
				if(!/^[-|_]?[a-zA-Z0-9_]+$/.test(name))  return false;
				else  return true;
			};
		}; // End of _Port

		var InputPort = function( jsonObject ){
			var InputPort = this;
			_Port.apply(InputPort);

			InputPort.order = function( order ) {
				return InputPort.property.apply(InputPort, OSP.Util.addFirstArgument(OSP.Constants.ORDER, arguments));
			};

			InputPort.inputData = function( inputData ){
				return InputPort.property.apply(InputPort, OSP.Util.addFirstArgument(OSP.Constants.INPUT_DATA, arguments));
			};

			InputPort.inputDataPath = function( path ){
				var inputData = InputPort.inputData();
				if( !inputData )	return false;
				return inputData.fullPath.apply( inputData, arguments );
			};

			InputPort.safeInputData = function(){
				var inputData = InputPort.inputData();
				if( !inputData ){
					inputData = new OSP.InputData();
					InputPort.inputData( inputData );
				}
				return inputData;
			};

			InputPort.setInputDataPath = function( id, parent, name, pathType, relative ){
				var inputData = InputPort.safeInputData();
				return inputData.setPath.apply( inputData, arguments );
			};

			InputPort.inputDataUri = function( uri ){
				var inputData = InputPort.safeInputData();
				return inputData.uri.apply( inputData, arguments );
			};

			InputPort.inputDataPathType = function( pathType ){
				var inputData = InputPort.safeInputData();
				return inputData.type.apply( inputData, arguments );
			};

			InputPort.inputDataParentFolderPath = function( parentPath ){
				var inputData = InputPort.safeInputData();
				return inputData.parent.apply( inputData, arguments );
			};

			InputPort.inputDataName = function( name ){
				var inputData = InputPort.safeInputData();
				return inputData.name.apply( inputData, arguments );
			};

			InputPort.inputDataRelative = function( relative ){
				var inputData = InputPort.safeInputData();
				return inputData.relative.apply( inputData, arguments );
			};

			InputPort.sample = function( samplePath ){
				return InputPort.property.apply(InputPort, OSP.Util.addFirstArgument(OSP.Constants.SAMPLE, arguments));
			};

			InputPort.samplePath = function( path ){
				var samplePath = InputPort.sample();
				if( !samplePath )	return false;
				return sample.fullPath.apply( samplePath, arguments );
			};

			InputPort.safeSample = function (){
				var sample = InputPort.sample();
				if( !sample ){
					sample = new OSP.Path();
					InputPort.sample( sample );
				}
				return sample;
			};

			InputPort.setSample = function( id, parent, name, type, relative ){
				var sample = InputPort.safeSample();
				return sample.setPath.apply( sample, arguments );
			};

			InputPort.sampleUri = function( uri ){
				var sample = InputPort.safeSample();
				return sample.uri.apply( sample, arguments );
			};

			InputPort.samplePathType = function( pathType ){
				var sample = InputPort.safeSample();
				return sample.type.apply( sample, arguments );
			};

			InputPort.sampleParentFolderPath = function( parentPath ){
				var sample = InputPort.safeSample();
				return sample.parent.apply( sample, arguments );
			};

			InputPort.sampleName = function( name ){
				var sample = InputPort.safeSample();
				return sample.name.apply( sample, arguments );
			};

			InputPort.sampleRelative = function( relative ){
				var sample = InputPort.safeSample();
				return sample.relative.apply( sample, arguments );
			};

			InputPort.getSampleData = function( url, params ){

			};

			InputPort.clone = function(){
				return new InputPort( OSP.Util.toJSON(InputPort) );
			};

			InputPort.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					switch( key ){
					case OSP.Constants.INPUT_DATA:
						var inputData = new OSP.InputData( jsonObject[key] );
						InputPort.inputData( inputData );
						break;
					case OSP.Constants.SAMPLE:
						var sample = new OSP.Path( jsonObject[key] );
						InputPort.sample( sample );
						break;
					default:
						InputPort._deserialize( key, jsonObject[key] );
					}
				}
			};

			InputPort.upgrade = function( oldPort ){
				for( var key in oldPort ){
					InputPort.inputDataPathType( OSP.Constants.FILE );
					switch( key ){
					case 'name':
						InputPort.name( oldPort[key] );
						break;
					case 'default-editor-id':
						InputPort.defaultEditor( oldPort[key] );
						break;
					case 'default-analyzer-id':
						InputPort.defaultAnalyzer( oldPort[key] );
						break;
					case 'port-type-name':
						InputPort.dataType( oldPort[key], '1.0.0' );
						break;
					case 'mandatory':
						InputPort.mandatory( oldPort[key] );
						break;
					case 'port-type-id':
					case 'inputdeckporttype':
						console.log( 'Deprecated: '+ key );
						break;
					default:
						console.log( 'Unknown attribute: '+ key);
					}
				}

				return InputPort;
			};

			if( arguments.length === 1 )
				InputPort.deserialize( jsonObject );
		}; // End of InputPort
		ScienceApp.newInputPort = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new InputPort();
				case 1:
					return new InputPort(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newInputPort');
					return null;
			}
		};

		var OutputPort = function( jsonObject ){
			var OutputPort = this;
			_Port.apply(OutputPort);

			OutputPort.outputData = function( path ){
				return OutputPort.property.apply(OutputPort, OSP.Util.addFirstArgument(OSP.Constants.OUTPUT_DATA, arguments));
			};

			OutputPort.safeOutputData = function(){
				var outputData = OutputPort.outputData();
				if( !outputData ){
					outputData = new OSP.Path();
					OutputPort.outputData( outputData );
				}
				return outputData;
			};

			OutputPort.setOutputDataPath = function(id, parent, name, pathType, relative ){
				var outputData = OutputPort.safeOutputData();
				return outputData.setPath.apply( outputData, arguments );
			};

			OutputPort.outputDataPathType = function( pathType ){
				var outputData = OutputPort.safeOutputData();
				return outputData.type.apply( outputData, arguments );
			};

			OutputPort.outputDataParentFolderPath = function( parentPath ){
				var outputData = OutputPort.safeOutputData();
				return outputData.parent.apply( outputData, arguments );
			};

			OutputPort.outputDataName = function( name ){
				var outputData = OutputPort.safeOutputData();
				return outputData.name.apply( outputData, arguments );
			};

			OutputPort.outputDataRelative = function( relative ){
				var outputData = OutputPort.safeOutputData();
				return outputData.relative.apply( outputData, arguments );
			};

			OutputPort.verifyOutput = function( url, params ){

				return true;
			};

			OutputPort.clone = function(){
				return new OutputPort( OSP.Util.toJSON(OutputPort) );
			};

			OutputPort.deserialize = function( jsonObject ){
				for( var key in jsonObject ){
					if( key === OSP.Constants.OUTPUT_DATA ){
						var outputPath = new OSP.Path( jsonObject[key] );
						OutputPort.outputData( outputPath );
					}else
						OutputPort.property( key, jsonObject[key] );
				}
			};

			OutputPort.upgrade = function( oldPort ){
				for( var key in oldPort ){
					switch( key ){
					case 'name':
						OutputPort.name( oldPort[key] );
						break;
					case 'default-editor-id':
						OutputPort.defaultEditor( oldPort[key] );
						break;
					case 'default-analyzer-id':
						OutputPort.defaultAnalyzer( oldPort[key] );
						break;
					case 'port-type-name':
						OutputPort.dataType( oldPort[key], '1.0.0' );
						break;
					case 'mandatory':
						OutputPort.mandatory( oldPort[key] );
						break;
					case 'data-form':
						var pathType = oldPort[key];
						if( pathType === 'extention' )
							pathType = 'ext';

						var fileName = oldPort['file-name'];
						if( fileName.indexOf( '/' ) == 0 )
							fileName = fileName.slice(1, fileName.length);

						if(fileName.indexOf( 'result') != 0)
							fileName = 'result/'+fileName;

						var filePath = OSP.Util.convertToPath(fileName);

						//console.log( 'filePath: '+JSON.stringify(filePath,null,4));

						OutputPort.setOutputDataPath( 0, filePath.parent, filePath.fileName, pathType, true);
						break;
					case 'port-type-id':
					case 'file-name':
					case 'inputdeckporttype':
					case 'type':
						console.log( 'Deprecated: '+ key );
						break;
					default:
						console.log( 'Unknown attribute: '+ key);
					}
				}

				return OutputPort;
			};

			if( arguments.length === 1 )
				OutputPort.deserialize( jsonObject );
		}; // End of OutputPort
		ScienceApp.newOutputPort = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new OutputPort();
				case 1:
					return new OutputPort(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newOutputPort');
					return null;
			}
		};
		ScienceApp.newLogPort = ScienceApp.newOutputPort;

		ScienceApp.type = function( type ){
			return ScienceApp.property.apply(ScienceApp, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments));
		};

		ScienceApp.inputPorts = function( inputPorts ){
			return ScienceApp.property.apply(ScienceApp, OSP.Util.addFirstArgument(OSP.Constants.INPUT_PORTS, arguments));
		};

		ScienceApp.deserializeInputPorts = function( portsJson ){
			var ports = ScienceApp.safeInputPorts();
			for( var portName in portsJson ){
				var port = ScienceApp.newInputPort(portsJson[portName]);
				ports[portName] = port;
			}
			
			ScienceApp.inputPorts(ports);

			return ports;
		};

		ScienceApp.inputPortsArray = function(){
			var ports = ScienceApp.inputPorts();
			if( !ports )	return [];

			var portsArray = [];
			for( var portName in ports ){
				portsArray.push( ports[portName] );
			}

			return portsArray;
		};

		ScienceApp.safeInputPorts = function(){
			var inputPorts = ScienceApp.inputPorts();
			if( !inputPorts ){
				inputPorts = {};
				ScienceApp.inputPorts( inputPorts );
			}

			return inputPorts;
		};

		ScienceApp.addInputPort = function( inputPort ){
			var inputPorts = ScienceApp.safeInputPorts();
			inputPorts[inputPort.name()] = inputPort;

			return inputPorts;
		};

		ScienceApp.removeInputPort = function( portName ){
			var inputPorts = ScienceApp.inputPorts();
			if( !inputPorts )	return true;

			return delete inputPorts[portName];
		};

		ScienceApp.inputPort = function( portName ){
			var ports = ScienceApp.inputPorts();
			if( !ports )	return false;
			return ports[portName];
		};

		ScienceApp.logPorts = function( logPorts ){
			return ScienceApp.property.apply(ScienceApp, OSP.Util.addFirstArgument(OSP.Constants.LOG_PORTS, arguments));
		};

		ScienceApp.deserializeLogPorts = function( portsJson ){
			var ports = ScienceApp.safeLogPorts();
			for( var portName in portsJson ){
				var port = ScienceApp.newLogPort(portsJson[portName]);
				ports[portName] = port;
			}
			
			ScienceApp.logPorts(ports);

			return ports;
		};

		ScienceApp.safeLogPorts = function(){
			var logPorts = ScienceApp.logPorts();
			if( !logPorts ){
				logPorts = {};
				ScienceApp.logPorts( logPorts );
			}

			return logPorts;
		};

		ScienceApp.logPortsArray = function(){
			var ports = ScienceApp.logPorts();
			if( !ports )	return [];

			var portsArray = [];
			for( var portName in ports ){
				portsArray.push( ports[portName] );
			}

			return portsArray;
		};

		ScienceApp.addLogPort = function( logPort ){
			var logPorts = ScienceApp.safeLogPorts();
			logPorts[logPort.name()] = logPort;

			return logPorts;
		};

		ScienceApp.removeLogPort = function( portName ){
			var logPorts = ScienceApp.logPorts();
			if( !logPorts )	return true;

			return delete logPorts[portName];
		};

		ScienceApp.logPort = function( portName ){
			var ports = ScienceApp.logPorts();
			if( !ports )	return false;
			return ports[portName];
		};

		ScienceApp.outputPorts = function( outputPorts ){
			return ScienceApp.property.apply(ScienceApp, OSP.Util.addFirstArgument(OSP.Constants.OUTPUT_PORTS, arguments));
		};

		ScienceApp.deserializeOutputPorts = function( portsJson ){
			var ports = ScienceApp.safeOutputPorts();
			for( var portName in portsJson ){
				var port = ScienceApp.newOutputPort(portsJson[portName]);
				ports[portName] = port;
			}

			ScienceApp.outputPorts(ports);
			return ports;
		};

		ScienceApp.outputPortsArray = function(){
			var ports = ScienceApp.outputPorts();
			if( !ports )	return [];

			var portsArray = [];
			for( var portName in ports ){
				portsArray.push( ports[portName] );
			}

			return portsArray;
		};

		ScienceApp.safeOutputPorts = function(){
			var outputPorts = ScienceApp.outputPorts();
			if( !outputPorts ){
				outputPorts = {};
				ScienceApp.outputPorts( outputPorts );
			}

			return outputPorts;
		};

		ScienceApp.addOutputPort = function( outputPort ){
			var outputPorts = ScienceApp.safeOutputPorts();
			outputPorts[outputPort.name()] = outputPort;

			return outputPorts;
		};

		ScienceApp.removeOutputPort = function( portName ){
			var outputPorts = ScienceApp.outputPorts();
			if( !outputPorts )	return true;

			return delete outputPorts[portName];
		};

		ScienceApp.outputPort = function( portName ){
			var ports = ScienceApp.outputPorts();
			if( !ports )	return false;
			return ports[portName];
		};
		
		ScienceApp.verifyPortName = function( portName ){
			var inputPorts = ScienceApp.inputPorts();
			if( inputPorts ){
				for( var index in inputPorts ){
					var port = inputPorts[index];
					if( port.name() === portName )	return false;
				}
			}
			var logPorts = ScienceApp.logPorts();
			if( logPorts ){
				for( var index in logPorts ){
					var port = logPorts[index];
					if( port.name() === portName )	return false;
				}
			}
			var outputPorts = ScienceApp.outputPorts();
			if( outputPorts ){
				for( var index in outputPorts ){
					var port = outputPorts[index];
					if( port.name() === portName )	return false;
				}
			}
			
			return true;
		};
		
		ScienceApp.getInputPortSample = function( portName ){
			var inputPorts = ScienceApp.inputPorts();
			if( inputPorts ){
				for( var index in inputPorts ){
					var port = inputPorts[index];
					if( port.name() === portName )
						return port.sample();
				}
			}
			return false;
		};

		ScienceApp.clone = function(){
			return new OSP.ScienceApp( OSP.Util.toJSON(ScienceApp) );
		};

		ScienceApp.upgradeInputPorts = function( oldPorts ){
			var newPorts = {};
			for( var portName in oldPorts ){
				var oldPort = oldPorts[portName];
				var newPort = ScienceApp.newInputPort();
				//console.log( 'oldPort: ' + JSON.stringify(oldPort,null,4));
				newPort.upgrade( oldPort );
				newPorts[portName] = newPort;
			}
			ScienceApp.inputPorts( newPorts );
			return newPorts;
		};

		ScienceApp.upgradeOutputPorts = function( oldPorts ){
			var newPorts = {};
			for( var portName in oldPorts ){
				var oldPort = oldPorts[portName];
				var newPort = ScienceApp.newOutputPort();
				newPort.upgrade( oldPort );
				newPorts[portName] = newPort;
			}
			ScienceApp.outputPorts( newPorts );
			return newPorts;
		};
	}; // End of ScienceApp

})(window);