(function(window){
	'use strict';

	if( window.OSP ){
		if( OSP.DataType )	return;
	}
	else
		window.OSP = {};
	
	OSP.Path = function( jsonObject ){
		var Path = this;
		OSP._MapObject.apply(Path);

		Path.uri = function( uri ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.URI, arguments) );
		};

		Path.setPath = function( id, parent, name, type, relative ){
			if( arguments.length < 3 )
				return false;
			Path.id( id );
			Path.parent(parent);
			Path.type(type);
			if( type === OSP.Constants.FOLDER )
				Path.folderName(name);
			else if( type === OSP.Constants.EXT )
				Path.extension(name);
			else
				Path.fileName(name);

			Path.relative(relative);
			return true;
		};

		Path.id = function( id ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.ID, arguments) );
		};

		Path.type = function( type ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments) );
		};

		Path.parent = function( parent ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.PARENT, arguments) );
		};

		Path.name = function( name ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};

		Path.fileName = function( fileName ){
			if( Path.type() !== OSP.Constants.FILE )
				return false;

			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};

		Path.relative = function( relative ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.RELATIVE, arguments) );
		};
		
		Path.dlEntryId = function( entryId ){
			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.URI, arguments) );
		};

		Path.extension = function( ext ){
			if( Path.type() !== OSP.Constants.EXT )
				return false;

			switch( arguments.length ){
			case 0:
				return Path.property(OSP.Constants.NAME).replace('*', '').replace('.', '').replace('/','');
			case 1:
				return Path.property(OSP.Constants.NAME, ext.replace('*', '').replace('.', '').replace('/',''));
			default:
				return false;
			}
		};

		Path.folderName = function( folderName ){
			if( Path.type() !== OSP.Constants.FOLDER )
				return false;

			return Path.property.apply( Path, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};

		Path.fullPath = function( path ){
			switch( arguments.length ){
			case 0:
				if( Path.type() === OSP.Constants.URI )
					return Path.uri();
				if( !Path.parent() && !Path.name())
					return '';
				if( !Path.parent() )
					return Path.name();
				if( !Path.name() )
					return Path.parent();

				return Path.parent()+'/'+ Path.name();
			case 1:
				var pathObj = OSP.Util.convertToPath( path );
				Path.parent(pathObj.parent());
				return Path.name(pathObj.name());
			default:
				console.log( 'Argument count mismatch: fullPath' );
				return false;
			}
		};

		Path.clone = function(){
			return new OSP.Path( OSP.Util.toJSON( Path ) );
		};

		Path.deserialize = function( jsonObject ){
			for( var key in jsonObject )
				Path._deserialize( key, jsonObject[key] );
		};

		if( arguments.length === 1 ){
			Path.deserialize(jsonObject);
		}
	}; // End of Path

	OSP.InputData = function( jsonObject ){
		var InputData = this;
		OSP.Path.apply(InputData);

		InputData.context = function( context ){
			return InputData.property.apply( InputData, OSP.Util.addFirstArgument(OSP.Constants.CONTEXT, arguments) );
		};

		InputData.clone = function(){
			return new OSP.InputData( OSP.toJSON(InputData) );
		};

		if( arguments.length === 1 )
			InputData.deserialize(jsonObject);
	}; // End of InputData

	OSP.DataType = function(){
		var DataType = this;
		OSP._OpenObject.apply(DataType);

		var DataStructure = function( jsonObject ){
			var DataStructure = this;
			OSP._MapObject.apply(DataStructure);

			var Range = function( jsonObject ) {
				var Range = this;
				OSP._MapObject.apply( Range );

				Range.lowerBoundary = function( boundary ){
					return Range.property.apply( Range, OSP.Util.addFirstArgument(OSP.Constants.LOWER_BOUNDARY, arguments) );
				};

				Range.upperBoundary = function( boundary ){
					return Range.property.apply( Range, OSP.Util.addFirstArgument(OSP.Constants.UPPER_BOUNDARY, arguments) );
				};

				Range.operand = function( operand ){
					return Range.property.apply( Range, OSP.Util.addFirstArgument(OSP.Constants.OPERAND, arguments) );
				};

				Range.includeBoundary = function( whichEnd, flag ){
					var operand = Range.operand();
					if( !operand )	return false;

					if( whichEnd === OSP.Constants.LOWER_BOUNDARY && flag == true )
						operand = '=' + operand.substring(1);
					else if( whichEnd === OSP.Constants.LOWER_BOUNDARY && flag == false )
						operand = '<' + operand.substring(1);
					else if( whichEnd === OSP.Constants.UPPER_BOUNDARY && flag == true )
						operand = operand.substring(0, 1) + '=';
					else if( whichEnd === OSP.Constants.UPPER_BOUNDARY && flag == false )
						operand = operand.substring(0, 1) + '>';
					else
						return false;

					Range.operand( operand );
					return true;
				};

				Range.checkLowerBoundary = function(){
					var operand = Range.operand();
					if( !operand )	return false;
					else{
						if( operand[0] === '=' ) return true;
						else	return false;
					}
				};

				Range.checkUpperBoundary = function(){
					var operand = Range.operand();
					if( !operand )	return false;
					else{
						if( operand[1] === '=' ) return true;
						else	return false;
					}
				};

				Range.setRange = function( lowerBoundary, upperBoundary, operand ) {
					var result = Range.property( OSP.Constants.LOWER_BOUNDARY, lowerBoundary );
					if( result === false )	return result;
					result = Range.property( OSP.Constants.UPPER_BOUNDARY, upperBoundary );
					if( result === false )	return result;
					result = Range.property( OSP.Constants.OPERAND, operand);

					return result;
				};

				Range.verify = function( value ){
					var lowerBoundaryContained = Range.checkLowerBoundary();
					var upperBoundaryContained = Range.checkUpperBoundary();
					var lowerBoundary = Range.lowerBoundary();
					var upperBoundary = Range.upperBoundary();

					if( !lowerBoundary && !upperBoundary )	return true;

					if( lowerBoundaryContained && !upperBoundary){
						if( Number(value) >= Number(lowerBoundary) )	return true;
					}
					else if ( !lowerBoundaryContained && !upperBoundary ){
						if( Number(value) > Number(lowerBoundary) )	return true;
					}
					else if ( !lowerBoundary && upperBoundaryContained ){
						if( Number(value) <= Number(upperBoundary) )	return true;
					}
					else if( !lowerBoundary && !upperBoundaryContained){
						if( Number(value) < Number(upperBoundary) )	return true;
					}
					else if(  lowerBoundaryContained && upperBoundaryContained ){
						if( Number(value) >= Number(lowerBoundary) && Number(value) <= Number(upperBoundary) )
							return true;
					}
					else if( !lowerBoundaryContained && upperBoundaryContained ){
						if( Number(value) > Number(lowerBoundary) && Number(value) <= Number(upperBoundary) )
							return true;
					}
					else if( lowerBoundaryContained && !upperBoundaryContained ){
						if( Number(value) >= Number(lowerBoundary) && Number(value) < Number(upperBoundary) )
							return true;
					}
					else if( !lowerBoundaryContained && !upperBoundaryContained ){
						if( Number(value) > Number(lowerBoundary) && Number(value) < Number(upperBoundary) )
							return true;
					}

					return false;
				};

				Range.clone = function(){
					return new Range( OSP.Util.toJSON(Range) );
				};

				Range.deserialize = function( jsonRange ){
					for( var key in jsonRange ){
						Range.property( key, jsonRange[key] );
					}
				};

				Range.upgrade = function( oldRange ){
					for( var key in oldRange ){
						if( OSP.Util.isEmpty(oldRange[key]) )	continue;
						switch( key ){
						case 'lower-limit':
							Range.lowerBoundary(oldRange[key]);
							break;
						case 'upper-limit':
							Range.upperBoundary(oldRange[key]);
							break;
						case 'operand':
							Range.operand(oldRange[key]);
							break;
						default:
							alert( 'Undefined Range property: '+key);
						}
					}
				};

				if( arguments.length === 1 )
					Range.deserialize(jsonObject);
			}; // End of Range
			DataStructure.newRange = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new Range();
					case 1:
						return new Range(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newRange');
						return null;
				}
			};

			var _ActivateCondition = function( jsonObject ){
				var _ActivateCondition = this;
				OSP._MapObject.apply(_ActivateCondition);

				_ActivateCondition.parameterName = function( name ) {
					return _ActivateCondition.property.apply( _ActivateCondition, OSP.Util.addFirstArgument(OSP.Constants.PARAMETER_NAME, arguments) );
				};

				_ActivateCondition.type = function( type ){
					return _ActivateCondition.property.apply( _ActivateCondition, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments) );
				};

				_ActivateCondition._upgrade = function( key, value, newCondition ){
					if( OSP.Util.isEmpty(value) ) return;

					switch( key ){
					case 'variable-name':
						newCondition.parameterName( value );
						break;
					case 'type':
						newCondition.type( value );
						break;
					}
				};
			}; // End of _ActivateCondition

			var NumericActivateCondition = function( jsonObject ){
				var NumericActivateCondition = this;
				_ActivateCondition.apply(NumericActivateCondition);
				NumericActivateCondition.type(OSP.Constants.NUMERIC);

				NumericActivateCondition.range = function( range ){
					return NumericActivateCondition.property.apply( NumericActivateCondition, OSP.Util.addFirstArgument(OSP.Constants.RANGE, arguments) );
				};

				NumericActivateCondition.rangeLowerBoundary = function( boundary ){
					var range = NumericActivateCondition.range();
					if( !range )	return false;

					return range.lowerBoundary.apply( range, arguments );
				};

				NumericActivateCondition.rangeUpperBoundary = function( boundary ){
					var range = NumericActivateCondition.range();
					if( !range )	return false;

					return range.upperBoundary.apply( range, boundary );
				};

				NumericActivateCondition.rangeOperand = function( operand ){
					var range = NumericActivateCondition.range();
					if( !range )	return false;

					return range.operand.apply( range, operand );
				};

				NumericActivateCondition.rangeCheckLowerBoundary = function(){
					var range = NumericActivateCondition.range();
					if( !range )	return false;
					return range.checkLowerBoundary();
				};

				NumericActivateCondition.rangeCheckUpperBoundary = function(){
					var range = NumericActivateCondition.range();
					if( !range )	return false;
					return range.checkUpperBoundary();
				};

				NumericActivateCondition.rangeIncludeBoundary = function( whichEnd, flag ){
					var range = NumericActivateCondition.range();
					if( !range )	return false;
					return range.includeBoundary( whichEnd, flag );
				};

				NumericActivateCondition.verifyRange = function( value ){
					var range = NumericActivateCondition.range();
					if( !range )	return true;
					return range.verify( value );
				};

				NumericActivateCondition.setRange = function( lowerBoundary, upperBoundary, operand ){
					var range = NumericActivateCondition.range();
					if( !range ){
						range = new Range();
						NumericActivateCondition.range( range );
					}

					return range.setRange(lowerBoundary, upperBoundary, operand);
				};

				NumericActivateCondition.setCondition = function(parameterName, lower, upper, operand, assignValue) {
					var range = NumericActivateCondition.range();
					if( !range ){
						range = new Range();
						NumericActivateCondition.range( range );
					}

					NumericActivateCondition.parameterName(parameterName);
					switch( arguments.length ){
					case 5:
						NumericActivateCondition.property(OSP.Constants.ASSIGN_VALUE, assignValue);
					case 4:
						return range.setRange(lower, upper, operand);
					default:
						return false;
					}
				};

				NumericActivateCondition.checkActivate = function( value ){
					if( NumericActivateCondition.verifyRange(value) == true ){
						if( NumericActivateCondition.assignValue() == false )
							return true;
						else
							return NumericActivateCondition.assignValue();
					}
					else
						return false;
				};

				NumericActivateCondition.assignValue = function( assignValue ){
					return NumericActivateCondition.property.apply( NumericActivateCondition, OSP.Util.addFirstArgument(OSP.Constants.ASSIGN_VALUE, arguments) );
				};

				NumericActivateCondition.clone = function(){
					return new NumericActivateCondition( OSP.Util.toJSON(NumericActivateCondition) );
				};

				NumericActivateCondition.deserialize = function(jsonObject){
					for( var key in jsonObject){
						switch( key ){
						case OSP.Constants.RANGE:
							NumericActivateCondition.range( new Range( jsonObject[key] ) );
							break;
						default:
							NumericActivateCondition.property(key, jsonObject[key]);
						}
					}
				};

				NumericActivateCondition.upgrade = function( oldCondition ){
					for( var key in oldCondition ){
						if( OSP.Util.isEmpty(oldCondition[key]) )	continue;

						switch( key ){
						case 'domain':
							var range = new Range();
							range.upgrade( oldCondition[key] );
							NumericActivateCondition.range( range );
							break;
						default:
							NumericActivateCondition._upgrade( key, oldCondition[key], NumericActivateCondition );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					NumericActivateCondition.deserialize(jsonObject);
			}; // End of NumericActivateCondition
			DataStructure.newNumericActivateCondition = function( jsonObject ){
				switch( arguments.length ){
				case 0:
					return new NumericActivateCondition();
				case 1:
					return new NumericActivateCondition(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newNumericActivateCondition');
					return null;
				}
			};

			var ListItemActivateCondition = function( jsonObject ){
				var ListItemActivateCondition = this;
				_ActivateCondition.apply(ListItemActivateCondition);
				
				ListItemActivateCondition.type(OSP.Constants.LIST);

				ListItemActivateCondition.value = function( value ) {
					return ListItemActivateCondition.property.apply( ListItemActivateCondition, OSP.Util.addFirstArgument(OSP.Constants.LIST_ITEM_VALUE, arguments) );
				};

				ListItemActivateCondition.setCondition = function(parameterName, listItemValue, assignValue) {
					switch( arguments.length ){
					case 3:
						ListItemActivateCondition.assignValue(assignValue);
					case 2:
						ListItemActivateCondition.parameterName(parameterName);
						ListItemActivateCondition.value(listItemValue);
						return ListItemActivateCondition;
					}

					return false;
				};

				ListItemActivateCondition.assignValue = function( value ){
					return ListItemActivateCondition.property.apply( ListItemActivateCondition, OSP.Util.addFirstArgument(OSP.Constants.ASSIGN_VALUE, arguments) );
				};

				ListItemActivateCondition.checkActivate = function(value){
					if(ListItemActivateCondition.value() === value){
						if( !ListItemActivateCondition.assignValue() || ListItemActivateCondition.assignValue() === '')
							return true;
						else
							return ListItemActivateCondition.assignValue();
					}
					else	return false;
				};

				ListItemActivateCondition.clone = function(){
					return new ListItemActivateCondition( OSP.Util.toJSON(ListItemActivateCondition) );
				};

				ListItemActivateCondition.upgrade = function( oldCondition ){
					for( var key in oldCondition ){
						if( OSP.Util.isEmpty(oldCondition[key]) )	continue;

						switch( key ){
						case 'list-item-value':
							ListItemActivateCondition.value(oldCondition[key]);
							break;
						case 'assign-value':
							ListItemActivateCondition.assignValue(oldCondition[key]);
							break;
						default:
							ListItemActivateCondition._upgrade( key, oldCondition[key], ListItemActivateCondition );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					ListItemActivateCondition.deserialize(jsonObject);
			}; // End of ListItemActivateCondition
			DataStructure.newListItemActivateCondition = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ListItemActivateCondition();
					case 1:
						return new ListItemActivateCondition(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newListItemActivateCondition');
						return null;
				}
			};

			var ListItem = function( jsonObject ) {
				var ListItem = this;
				OSP._MapObject.apply(ListItem);

				ListItem.text = function( textObject ) {
					return ListItem.property.apply( ListItem, OSP.Util.addFirstArgument(OSP.Constants.TEXT, arguments) );
				};

				ListItem.value = function( value ) {
					return ListItem.property.apply( ListItem, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments) );
				};

				ListItem.localizedText = function(languageId, text) {
					var itemText = ListItem.text();

					switch( arguments.length ){
					case 1:
						if( !itemText )	return '';
						else	return itemText[languageId];
					case 2:
						if( !itemText ){
							itemText = {};
							ListItem.text( itemText );
						}
						return itemText[languageId] = text;
					default:
						return false;
					}
				};

				ListItem.removeItemText = function(languageId) {
					var itemText = ListItem.text();
					if( !itemText )	return true;

					return delete itemText[languageId];
				};

				ListItem.textXml = function(availableLanguageIds, defalutLanguageId) {
					var itemText = ListItem.text();
					if( !itemText )	itemText = {};
					return OSP.Util.toLocalizedXml( itemText, availableLanguageIds, defalutLanguageId );
				};

				ListItem.activateConditions = function( conditions ) {
					return ListItem.property.apply(ListItem, OSP.Util.addFirstArgument(OSP.Constants.ACTIVATE_CONDITIONS, arguments));
				};

				ListItem.addActivateCondition = function( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue ) {
					var conditions = ListItem.activateConditions();
					if( !conditions ){
						conditions = [];
						ListItem.activateConditions(conditions);
					}

					var condition;
					switch( arguments.length ){
					case 1:
						condition = conditionOrPrameterName;
						break;
					case 2:
						condition = new ListItemActivateCondition();
						condition.setCondition( conditionOrPrameterName, minOrListItemValue );
						break;
					case 3:
						condition = new ListItemActivateCondition();
						condition.setCondition(conditionOrPrameterName, minOrListItemValue, maxOrAssignValue );
						break;
					case 4:
						condition = new NumericActivateCondition();
						condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand );
						break;
					case 5:
						condition = new NumericActivateCondition();
						condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue );
						break;
					default:
						return conditions;
					}

					conditions.push( condition );
					return conditions;
				};

				ListItem.removeActivateCondition = function( parameterName, minOrListItemValue, max ) {
					var conditions = ListItem.activateConditions();
					if( !conditions )	return true;

					switch( arguments.length ){
					case 1:
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1);
								ListItem.removeActivateCondition(parameterName);
								break;
							}
						}
						return conditions;
					case 2:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.value() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					case 3:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.rangeLowerBoundary() &&
								max === condition.rangeUpperBoundary() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					default:
						return conditions;
					}
				};

				ListItem.checkActivate = function(parameterName, value){
					var conditions = ListItem.activateConditions();
					if( !conditions )	return true;

					var activate = false;
					for( var index in conditions ){
						var condition = conditions[index];
						if( condition.parameterName() === parameterName ){
							activate = condition.checkActivate( value );
							if( activate )	return activate;
						}
					}

					return activate;
				};

				ListItem.clone = function(){
					return new ListItem( OSP.Util.toJSON(ListItem) );
				};

				ListItem.deserialize = function( jsonObject ){
					for( var key in jsonObject ){
						if( key === OSP.Constants.ACTIVATE_CONDITIONS ){
							var jsonConditions = jsonObject[key];
							for( var index in jsonConditions ){
								var jsonCondition = jsonConditions[index];
								var condition;
								if( jsonCondition[OSP.Constants.TYPE] === OSP.Constants.LIST)
									condition = new ListItemActivateCondition( jsonCondition );
								else
									condition = new NumericActivateCondition( jsonCondition );

								ListItem.addActivateCondition(condition);
							}
						}
						else
							ListItem.property( key, jsonObject[key] );
					}
				};

				ListItem.upgrade = function( oldListItem ){
					for( var key in oldListItem ){
						if( OSP.Util.isEmpty(oldListItem[key]) )	continue;

						switch( key ){
						case 'value':
							ListItem.value(oldListItem[key]);
							break;
						case 'localized-text-map':
							ListItem.text(oldListItem[key]['map']);
							break;
						case 'activate-condition-container':
							var oldConditions = oldListItem[key]['container'];
							var newConditions = [];
							for( var index in oldConditions ){
								var oldCondition = oldConditions[index];
								if( OSP.Util.isEmpty(oldCondition) )	continue;
								var newCondition;
								switch( oldCondition['type'] ){
								case 'numeric':
									newCondition = new NumericActivateCondition();
									break;
								case 'list':
									newCondition = new ListItemActivateCondition();
									break;
								default:
									alert( 'Unknown condition: '+oldCondition['type']);
									return;
								}
								newCondition.upgrade( oldCondition['type'] );
								newConditions.push(newCondition);
							}

							ListItem.activateConditions(newConditions);
							break;
						}
					}
				};

				if( arguments.length === 1 )
					ListItem.deserialize(jsonObject);
			}; // End of ListItem
			DataStructure.newListItem = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ListItem();
					case 1:
						return new ListItem(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newListItem');
						return null;
				}
			};

			var Sweep = function( jsonObject ){
				var Sweep = this;
				OSP._MapObject.apply(Sweep);

				Sweep.sliceCount = function( count ){
					return Sweep.property.apply( Sweep, OSP.Util.addFirstArgument(OSP.Constants.SLICE_COUNT, arguments) );
				};

				Sweep.sliceValue = function( value ){
					return Sweep.property.apply( Sweep, OSP.Util.addFirstArgument(OSP.Constants.SLICE_VALUE, arguments) );
				};

				Sweep.maxSlice = function( maxSlice ){
					return Sweep.property.apply( Sweep, OSP.Util.addFirstArgument(OSP.Constants.SLICE_MAX, arguments) );
				};

				Sweep.range = function( range ){
					return Sweep.property.apply( Sweep, OSP.Util.addFirstArgument(OSP.Constants.RANGE, arguments) );
				};

				Sweep.rangeLowerBoundary = function( boundary){
					var range = Sweep.range();
					if( !range )	return false;

					return range.lowerBoundary.apply( range, arguments );
				};

				Sweep.rangeUpperBoundary = function( boundary ){
					var range = Sweep.range();
					if( !range )	return false;
					else	return range.upperBoundary.apply(range, arguments);
				};

				Sweep.rangeOperand = function( operand ){
					var range = Sweep.range();
					if( !range )	return false;

					return range.operand.apply( range, operand );
				};

				Sweep.rangeCheckLowerBoundary = function(){
					var range = Sweep.range();
					if( !range )	return false;

					return range.checkLowerBoundary();
				};

				Sweep.rangeCheckUpperBoundary = function(){
					var range = Sweep.range();
					if( !range )	return false;

					return range.checkUpperBoundary();
				};

				Sweep.rangeIncludeBoundary = function( whichEnd, flag ){
					var range = Sweep.range();
					if( !range )	return false;

					return range.includeBoundary( whichEnd, flag );
				};

				Sweep.verifyRange = function( value ){
					var range = Sweep.range();
					if( !range )	return true;
					return range.verify( value );
				};

				Sweep.setRange = function( lowerBoundary, upperBoundary, operand ){
					var range = Sweep.range();
					if( !range ){
						range = new Range();
						Sweep.range( range );
					}

					return range.setRange.apply(range, arguments);
				};

				Sweep.getSlicedValues = function(){
					var strLower = Sweep.rangeLowerBoundary();
					var strUpper = Sweep.rangeUpperBoundary();
					if( !strLower || !strUpper ) return false;

					var lower = Number( strLower );
					var upper = Number( strUpper );

					var values = [];
					var isExponential = OSP.Util.isExponetioal(strLower) || OSP.Util.isExponetioal(strUpper);
					var includeLower = Sweep.rangeCheckLowerBoundary();
					var includeUpper = Sweep.rangeCheckUpperBoundary();
					var sliceValue;
					var method = Sweep.sweepMethod();

					var index =0;
					var sliceCount;

					if( method == OSP.Enumeration.SweepMethod.BY_SLICE ){
							sliceCount = Number(Sweep.sliceCount());
							//console.log('sliceCount: '+sliceCount);
					}
					else {
							sliceValue = Number(Sweep.sliceValue());
							sliceCount =  Math.floor(OSP.Util.safeFloatSubtract(upper, lower)/sliceValue);
							//console.log('sliceCount: '+sliceCount);
					}

					//console.log("included: "+includeLower+','+includeUpper);
					if(includeLower == true  && includeUpper == true){
						values[index] = OSP.Util.toFloatString(lower, isExponential);
						//console.log('sliced value['+index+']:'+lower);
						++index;
						--sliceCount;
						//console.log('sliced value['+sliceCount+']:'+upper);
					}
					else if(includeLower == true){
						values[index] = OSP.Util.toFloatString(lower, isExponential);
						++index;
					}
					else{
						++sliceCount;
					}

					if( method == OSP.Enumeration.SweepMethod.BY_SLICE )
						sliceValue = OSP.Util.safeFloatSubtract(upper, lower) / sliceCount;

					var value;
					(index == 0) ? ( value = OSP.Util.safeFloatSum(lower, sliceValue)) :
											  (value = OSP.Util.safeFloatSum(values[index-1], sliceValue));
					while(value < upper){
						values[index] = OSP.Util.toFloatString(value, isExponential);
//							alert('sliced value['+index+']:'+values[index]);
						//console.log('sliced value['+index+']:'+values[index]);
						++index;
						value = OSP.Util.safeFloatSum(value, sliceValue);
					}
					if(includeUpper == true){
						values[index] = OSP.Util.toFloatString(upper, isExponential);
						//console.log('sliced value['+(sliceCount-1)+']:'+upper);
					}

					return values;
				};

				Sweep.sweepMethod = function( method ){
					return Sweep.property.apply( Sweep, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_METHOD, arguments) );
				};

				Sweep.deserialize = function( jsonObject ){
					for( var key in jsonObject){
						switch(key){
						case OSP.Constants.RANGE:
							Sweep.range( new Range( jsonObject[key] ) );
							break;
						default:
							Sweep.property( key, jsonObject[key] );
						}
					}
				};

				Sweep.upgrade = function( oldSweep ){
					for( var key in oldSweep ){
						if( OSP.Util.isEmpty(oldSweep[key]) )	continue;

						switch( key ){
						case 'slice':
							Sweep.sliceCount( oldSweep[key] );
							break;
						case 'slice-value':
							Sweep.sliceValue( oldSweep[key] );
							break;
						case 'slice-max':
							Sweep.maxSlice( oldSweep[key] );
							break;
						case 'sweep-by-slice':
							if( oldSweep[key] == true )
								Sweep.sweepMethod( OSP.Enumeration.SweepMethod.BY_SLICE );
							else
								Sweep.sweepMethod( OSP.Enumeration.SweepMethod.BY_VALUE );
							break;
						case 'domain':
							var range = new Range();
							range.upgrade( oldSweep[key] );
							Sweep.range( range );
							break;
						default:
							alert( 'Unknown sweep attribute: '+ key);
							return;
						}
					}
				};

				if( arguments.length === 1 )
					Sweep.deserialize(jsonObject);
			}; // End of Sweep
			DataStructure.newSweep = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new Sweep();
					case 1:
						return new Sweep(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newSweep');
						return null;
				}
			};

			var _Parameter = function( jsonObject ) {
				var _Parameter = this;
				OSP._MapObject.apply(_Parameter);

				_Parameter.name = function( name ) {
					return _Parameter.property.apply( _Parameter, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
				};

				_Parameter.type = function( type ){
					return _Parameter.property.apply( _Parameter, OSP.Util.addFirstArgument(OSP.Constants.TYPE, arguments) );
				};

				_Parameter.nameText = function( nameText ) {
					return _Parameter.property.apply( _Parameter, OSP.Util.addFirstArgument(OSP.Constants.NAME_TEXT, arguments) );
				};

				_Parameter.localizedNameText = function( languageId, text ) {
					var nameText = _Parameter.nameText();

					switch( arguments.length ){
					case 1:
						if( !nameText )	return '';
						return nameText[languageId];
					case 2:
						if( !nameText ){
							nameText = {};
							_Parameter.nameText( nameText );
						}
						return nameText[languageId] = text;
					default:
						return '';
					}
				};

				_Parameter.removeNameText = function( languageId ) {
					return _Parameter.removeProperty( OSP.Constants.NAME_TEXT, languageId );
				};

				_Parameter.localizedNameTextXML = function(availableLanguageIds, defaultLanguageId) {
					var nameText = _Parameter.nameText();
					if( !nameText ){
						nameText = {};
					}

					return OSP.Util.toLocalizedXml(nameText, availableLanguageIds, defaultLanguageId);
				};

				_Parameter.description = function( description ) {
					return _Parameter.property.apply(_Parameter, OSP.Util.addFirstArgument(OSP.Constants.DESCRIPTION, arguments));
				};

				_Parameter.localizedDescription = function( languageId, text ) {
					var description = _Parameter.description();

					switch( arguments.length ){
					case 1:
						if( !description )	return '';
						else	return description[languageId];
					case 2:
						if( !description ){
							description = {};
							_Parameter.description( description );
						}
						return description[languageId] = text;
					default:
						return '';
					}
				};

				_Parameter.removeLocalizedDescription = function( languageId ) {
					return _Parameter.removeProperty(OSP.Constants.NAME_TEXT, languageId);
				};

				_Parameter.localizedDescriptionXML = function(availableLanguageIds, defaultLanguageId) {
					var description = _Parameter.description();
					if( !description )	description = {};

					return OSP.Util.toLocalizedXml(description, availableLanguageIds, defaultLanguageId);
				};

				_Parameter.activateConditions = function( conditions ) {
					return _Parameter.property.apply(_Parameter, OSP.Util.addFirstArgument(OSP.Constants.ACTIVATE_CONDITIONS, arguments));
				};

				_Parameter.addActivateCondition = function( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue ) {
					var conditions = _Parameter.activateConditions();
					if( !conditions ){
						conditions = [];
						_Parameter.activateConditions(conditions);
					}

					var condition;
					switch( arguments.length ){
						case 1:
							condition = conditionOrPrameterName;
							break;
						case 2:
							condition = new ListItemActivateCondition();
							condition.setCondition( conditionOrPrameterName, minOrListItemValue );
							break;
						case 3:
							condition = new ListItemActivateCondition();
							condition.setCondition(conditionOrPrameterName, minOrListItemValue, maxOrAssignValue );
							break;
						case 4:
							condition = new NumericActivateCondition();
							condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand );
							break;
						case 5:
							condition = new NumericActivateCondition();
							condition.setCondition( conditionOrPrameterName, minOrListItemValue, maxOrAssignValue, operand, assignValue );
							break;
						default:
							return conditions;
					}

					conditions.push( condition );
					return conditions;
				};

				_Parameter.cleanActivateConditions = function(){
					return _Parameter.removeProperty(OSP.Constants.ACTIVATE_CONDITIONS);
				};

				_Parameter.removeActivateCondition = function( parameterName, minOrListItemValue, max ) {
					var conditions = _Parameter.activateConditions();
					if( !conditions )	return true;

					switch( arguments.length ){
					case 1:
						for( var index in conditions ){
							var condition = conditions[index];
							if( condition.parameterName() === parameterName ){
								conditions.splice( index, 1);
								_Parameter.removeActivateCondition(parameterName);
								break;
							}
						}
						return conditions;
					case 2:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.value() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					case 3:
						for(var index in conditions ){
							var condition = conditions[index];
							if( parameterName === condition.parameterName() &&
								minOrListItemValue === condition.rangeLowerBoundary() &&
								max === condition.rangeUpperBoundary() ){
								conditions.splice( index, 1 );
								break;
							}
						}
						return conditions;
					default:
						return conditions;
					}
				};

				_Parameter.checkActivate = function(parameterName, value){
					var conditions = _Parameter.activateConditions();
					if( !conditions )	return true;

					var activate = false;
					for( var index in conditions ){
						var condition = conditions[index];
						if( condition.parameterName() === parameterName ){
							activate = condition.checkActivate( value );
							if( activate )	return activate;
						}
					}

					return activate;
				};

				_Parameter.order = function( order ) {
					return _Parameter.property.apply(_Parameter, OSP.Util.addFirstArgument(OSP.Constants.ORDER, arguments));
				};

				_Parameter.active = function( active ){
					return _Parameter.property.apply(_Parameter, OSP.Util.addFirstArgument(OSP.Constants.ACTIVE, arguments));
				};

				_Parameter.disabled = function( disabled ){
					return _Parameter.property.apply(_Parameter, OSP.Util.addFirstArgument(OSP.Constants.DISABLED, arguments));
				};

				_Parameter.verifyName = function(name){
					if(/[a-zA-Z\\_]/.test(name.charAt(0)) == false) return false;
					if(/[^a-zA-Z0-9\\_]/.test(name))	return false;
					return true;
				};

				_Parameter._deserialize = function( key, jsonObject ){
					switch(key){
					case OSP.Constants.ACTIVATE_CONDITIONS:
						var jsonConditions = jsonObject;

						for( var index in jsonConditions ){
							var jsonCondition = jsonConditions[index];
							var condition;
							if( jsonCondition[type] === OSP.Constants.VARIABLE_TYPE_LIST )
								condition = _Parameter.newListItemActivateCondition( jsonCondition );
							else
								condition = _Parameter.newNumericActivateCondition( jsonCondition );

							_Parameter.addActivateCondition(condition);
						}
						break;
					default:
						_Parameter.property( key, jsonObject );
					}
				};

				_Parameter._upgrade = function( key, value, newParameter ){
					if( OSP.Util.isEmpty(value) )	return;

					switch( key ){
					case 'name':
						newParameter.name( value );
						break;
					case 'type':
						if( value === 'control_active' || value === 'control_associate')
							value = 'list';
						newParameter.type( value );
						break;
					case 'name-text-map':
						newParameter.nameText( value['map'] );
						break;
					case 'description-map':
						newParameter.description( value['map'] );
						break;
					case 'order':
						newParameter.order( value );
						break;
					case 'active':
						newParameter.active( value );
						break;
					case 'disable':
						newParameter.disabled( value );
						break;
					case 'activate-condition-container':
						if( OSP.Util.isEmpty(value) )	break;

						var oldConditions = value['container'];
						if( oldConditions.length === 0 )	break;
						var newConditions = [];

						for( var index in oldConditions ){
							var oldCondition = oldConditions[index];
							if( OSP.Util.isEmpty(oldCondition) )	continue;

							var newCondition;
							switch( oldCondition['type'] ){
							case 'numeric':
								newCondition = new NumericActivateCondition();
								break;
							case 'list':
								newCondition = new ListItemActivateCondition();
								break;
							default:
								alert( 'Unknown condition type: '+oldCondition['type'] );
								return;
							}
							newCondition.upgrade( oldCondition );
							newConditions.push( newCondition );
						}

						newParameter.activateConditions(newConditions);
						break;
					}
				}
			}; // End of _Parameter

			var NumericParameter = function( jsonObject ){
				var NumericParameter = this;
				_Parameter.apply(NumericParameter);
				NumericParameter.type( OSP.Constants.NUMERIC);
				NumericParameter.active(true);

				NumericParameter.unit = function( unit ){
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.UNIT, arguments));
				};

				NumericParameter.value = function( value ) {
					if( arguments.length === 1 ){
						if( NumericParameter.verifyRange( value ) === false )
							return false;
					}
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				NumericParameter.range = function( range ) {
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.RANGE, arguments));
				};

				NumericParameter.rangeLowerBoundary = function( boundary ){
					var range = NumericParameter.range();
					if( !range )	return false;

					return range.lowerBoundary.apply( range, arguments );
				};

				NumericParameter.rangeUpperBoundary = function( boundary ){
					var range = NumericParameter.range();
					if( !range )	return false;

					return range.upperBoundary.apply( range, arguments );
				};

				NumericParameter.rangeOperand = function( operand ){
					var range = NumericParameter.range();
					if( !range )	return false;

					return range.operand.apply( range, operand );
				};

				NumericParameter.rangeCheckLowerBoundary = function(){
					var range = NumericParameter.range();
					if( !range )	return false;

					return range.checkLowerBoundary();
				};

				NumericParameter.rangeCheckUpperBoundary = function(){
					var range = NumericParameter.range();
					if( !range )	return false;

					return range.checkUpperBoundary();
				};

				NumericParameter.rangeIncludeBoundary = function( whichEnd, flag ){
					var range = NumericParameter.range();
					if( !range )	return false;

					return range.includeBoundary( whichEnd, flag );
				};

				NumericParameter.verifyRange = function( value ){
					var range = NumericParameter.range();
					if( !range )	return true;
					return range.verify( value );
				};

				NumericParameter.setRange = function( lowerBoundary, upperBoundary, operand ){
					var range = NumericParameter.range();
					if( !range ){
						range = new Range();
						NumericParameter.range( range );
					}

					return range.setRange.apply( range, arguments );
				};

				NumericParameter.sweep = function( sweep ){
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.SWEEP, arguments));
				};

				NumericParameter.sweepable = function( flag ){
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.SWEEPABLE, arguments));
				};

				NumericParameter.maxSweepSlice = function( max ){
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.maxSlice.apply( sweep, arguments );
				};

				NumericParameter.sweepMethod = function( method ){
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.sweepMethod.apply( sweep, arguments );
				};

				NumericParameter.sweepCount = function( count ){
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_COUNT, arguments));
				};

				NumericParameter.sweepSliceCount = function( count ){
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.sliceCount.apply( sweep, arguments );
				};

				NumericParameter.sweepSliceValue = function( value ){
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.sliceValue.apply( sweep, arguments );
				};

				NumericParameter.getSweepSlicedValues = function(){
					var sweep = NumericParameter.sweep();
					if( !sweep )	return false;

					return sweep.getSlicedValues();
				};

				NumericParameter.sweepRange = function( range ){
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.range.apply( sweep, arguments );
				};

				NumericParameter.sweepRangeLowerBoundary = function( boundary ) {
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.rangeLowerBoundary.apply(sweep, arguments);
				};

				NumericParameter.sweepRangeUpperBoundary = function( boundary ) {
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.rangeUpperBoundary.apply(sweep, arguments);
				};

				NumericParameter.sweepRangeOperand = function( operand ) {
					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep(sweep);
					}

					return sweep.rangeOperand.apply(sweep, arguments);
				};

				NumericParameter.sweepRangeCheckLowerBoundary = function(){
					var sweep = NumericParameter.sweep();
					if( !sweep )	return false;

					return sweep.rangeCheckLowerBoundary();
				};

				NumericParameter.sweepRangeCheckUpperBoundary = function(){
					var sweep = NumericParameter.sweep();
					if( !sweep )	return false;

					return sweep.rangeCheckUpperBoundary();
				};

				NumericParameter.sweepRangeIncludeBoundary = function( whichEnd, flag ){
					var sweep = NumericParameter.sweep();
					if( !sweep )	return false;

					return sweep.rangeIncludeBoundary( whichEnd, flag );
				};

				NumericParameter.setSweepRange = function(lowerBoundary, upperBoundary, operand) {
					if( Number(lowerBoundary) > Number(upperBoundary) )
						return false;
					if( !NumericParameter.verifyRange(lowerBoundary) ||
						 !NumericParameter.verifyRange(upperBoundary))		return false;


					var sweep = NumericParameter.sweep();
					if( !sweep ){
						sweep = new Sweep();
						NumericParameter.sweep( sweep );
					}

					return sweep.setRange.apply( sweep, arguments );
				};

				NumericParameter.verifySweepRange = function( value ){
					var sweep = NumericParameter.getSweep();
					if( !sweep )	return false;

					return sweep.verifyRange(value);
				};

				NumericParameter.removeSweep = function(){
					NumericParameter.removeProperty(OSP.Constants.SWEEPED);
					NumericParameter.removeProperty(OSP.Constants.SWEEP);
				};

				NumericParameter.sweeped = function( flag ){
					return NumericParameter.property.apply(NumericParameter, OSP.Util.addFirstArgument(OSP.Constants.SWEEPED, arguments));
				};

				NumericParameter.deserialize = function (jsonObject){
					for( var key in jsonObject){
						switch(key){
						case OSP.Constants.RANGE:
							NumericParameter.range(new Range(jsonObject[key]) );
							break;
						case OSP.Constants.SWEEP:
							NumericParameter.sweep( new Sweep( jsonObject[key] ) );
							break;
						default:
							NumericParameter._deserialize(key, jsonObject[key]);
						}
					}
				};

				NumericParameter.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )continue;

						switch( key ){
							case 'unit':
								NumericParameter.unit(variable[key]);
								break;
							case 'value':
								NumericParameter.value(variable[key]);
								break;
							case 'sweeped':
								NumericParameter.sweeped(variable[key]);
								break;
							case 'sweepable':
								NumericParameter.sweepable(variable[key]);
								break;
							case 'value-domain':
								var range = new Range();
								range.upgrade( variable[key] );
								NumericParameter.range( range );
								break;
							case 'sweep':
								var sweep = new Sweep();
								sweep.upgrade( variable[key] );
								NumericParameter.sweep(sweep);
								break;
							default:
								NumericParameter._upgrade( key, variable[key], NumericParameter );
								break;
						}
					}
				};

				if( arguments.length === 1 )
					NumericParameter.deserialize(jsonObject);
			}; // End of NumericParameter
			DataStructure.newNumericParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new NumericParameter();
					case 1:
						return new NumericParameter(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newNumericParameter');
						return null;
				}
			};

			var ListParameter = function( jsonObject ){
				var ListParameter = this;
				_Parameter.apply(ListParameter);
				ListParameter.type( OSP.Constants.LIST);
				ListParameter.active(true);

				ListParameter.value = function( value ) {
					return ListParameter.property.apply(ListParameter, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				ListParameter.listItems = function( list ) {
					return ListParameter.property.apply(ListParameter, OSP.Util.addFirstArgument(OSP.Constants.LIST_ITEMS, arguments));
				};

				ListParameter.listItem = function(itemValue, listItem) {
					var listItems = ListParameter.listItems();

					switch( arguments.length ){
					case 1:
						if( !listItems )	return false;
						return listItems[itemValue];
					case 2:
						if( !listItems ){
							listItems = {};
							ListParameter.listItems( listItems );
						}
						return listItems[itemValue] = listItem;
					default:
						return false;
					}
				};

				ListParameter.addListItem = function( itemValue ){
					var listItems = ListParameter.listItems();
					if( !listItems ){
						listItems = {};
						ListParameter.listItems( listItems );
					}

					var listItem = new ListItem();
					listItem.value( itemValue );
					//console.log( 'list: '+JSON.stringify(listItems,null,4));
					//console.log( 'itemValue: '+itemValue);
					listItems[itemValue] = listItem;
					return listItem;
				};

				ListParameter.localizedListItemText = function(itemValue, languageId, text){
					var list = ListParameter.listItems();
					if( !list )	return false;

					var listItem = list[itemValue];
					switch( arguments.length ){
					case 1:
						return listItem;
					case 2:
						return listItem.localizedText( languageId );
					case 3:
						return listItem.localizedText( languageId, text );
					default:
						return false;
					}
				};

				ListParameter.localizedListItems = function( languageId ){
					var list = ListParameter.listItems();
					if( !list )	return false;
					//console.log('List: '+ JSON.stringify(list,null,4));
					var localizedListItems = {};
					for( var key in list ){
						var listItem = list[key];
						localizedListItems[key] = listItem.localizedText(languageId);
					}

					return localizedListItems;
				};

				ListParameter.listXml = function( availablLanguageIds, defaultLanguageId ){
					var list = ListParameter.listItems();
					if( !list )	return false;

					var listXml = [];
					for( var key in list ){
						var listItem = list[key];
						listXml.push( listItem.textXml(availablLanguageIds, defaultLanguageId) );
					}

					return listXml;
				};

				ListParameter.removeListItem = function(itemValue) {
					var list = ListParameter.list();
					if( !list )	return true;

					return delete list[itemValue];
				};

				ListParameter.clone = function(){
					return new ListParameter( OSP.Util.toJSON(ListParameter) );
				};

				ListParameter.deserialize = function ( jsonObject ){
					for( var key in jsonObject ){
						switch( key ){
						case OSP.Constants.LIST_ITEMS:
							var jsonList = jsonObject[key];
							for( var index in jsonList ){
								var listItem = new ListItem( jsonList[index] );
								ListParameter.listItem( listItem.value(), listItem );
							}
							break;
						default:
							ListParameter._deserialize( key, jsonObject[key] );
						}
					}
				};

				ListParameter.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;

						switch( key ){
						case 'value':
							ListParameter.value(variable[key]);
							break;
						case 'list-map':
							var oldListItems = variable[key]['map'];
							if( oldListItems.length === 0 )	break;

							for( var listItemKey in oldListItems ){
								var oldListItem = oldListItems[listItemKey];
								var newListItem = ListParameter.addListItem(listItemKey);
								newListItem.upgrade( oldListItem );
							}
							break;
						default:
							ListParameter._upgrade( key, variable[key], ListParameter );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					ListParameter.deserialize(jsonObject);
			}; // End of ListParameter
			DataStructure.newListParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ListParameter();
					case 1:
						return new ListParameter(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newListParameter');
						return null;
				}
			};

			var StringParameter = function( jsonObject ){
				var StringParameter = this;
				_Parameter.apply(StringParameter);
				StringParameter.type( OSP.Constants.STRING);
				StringParameter.active(true);

				StringParameter.value = function( value ) {
					return StringParameter.property.apply(StringParameter, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				StringParameter.clone = function(){
					return new StringParameter( OSP.Util.toJSON(StringParameter) );
				};

				StringParameter.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						StringParameter._deserialize( key, jsonObject[key] );
				};

				StringParameter.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;

						switch( key ){
						case 'value':
							StringParameter.value(variable[key]);
							break;
						default:
							StringParameter._upgrade( key, variable[key], StringParameter );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					StringParameter.deserialize(jsonObject);
			}; // End of StringParameter
			DataStructure.newStringParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new StringParameter();
					case 1:
						return new StringParameter(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newStringParameter');
						return null;
				}
			};

			var VectorParameter = function( jsonObject ){
				var VectorParameter = this;
				_Parameter.apply(VectorParameter);
				VectorParameter.type( OSP.Constants.VECTOR);
				VectorParameter.active(true);

				VectorParameter.value = function( value ) {
					return VectorParameter.property.apply(VectorParameter, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				VectorParameter.getValueString = function(leftBrace, rightBrace, delimiter, space){
					if(delimiter !== ' ')	delimiter += space;
					var string = JSON.stringify(VectorParameter.value());
					string = string.replace('[', leftBrace).replace(']', rightBrace).replace(/,/g, delimiter).replace(/\"/gi,"");
					return string;
				};

				VectorParameter.dimension = function( dimension ) {
					return VectorParameter.property.apply(VectorParameter, OSP.Util.addFirstArgument(OSP.Constants.DIMENSION, arguments));
				};

				VectorParameter.clone = function(){
					return new VectorParameter( OSP.Util.toJSON(VectorParameter) );
				};

				VectorParameter.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						VectorParameter._deserialize( key, jsonObject[key] );
				};

				VectorParameter.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;

						switch( key ){
						case 'value':
							VectorParameter.value(variable[key]);
							break;
						case 'dimension':
							VectorParameter.dimension(variable[key]);
							break;
						default:
							VectorParameter._upgrade( key, variable[key], VectorParameter );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					VectorParameter.deserialize(jsonObject);
			}; // End of VectorParameter
			DataStructure.newVectorParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new VectorParameter();
					case 1:
						return new VectorParameter(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newVectorParameter');
						return null;
				}
			};

			var CommentParameter = function( jsonObject ){
				var CommentParameter = this;
				_Parameter.apply(CommentParameter);
				CommentParameter.type( OSP.Constants.COMMENT);
				CommentParameter.active(true);

				CommentParameter.value = function( value ) {
					return CommentParameter.property.apply(CommentParameter, OSP.Util.addFirstArgument(OSP.Constants.VALUE, arguments));
				};

				CommentParameter.clone = function(){
					return new CommentParameter( OSP.Util.toJSON(CommentParameter) );
				};

				CommentParameter.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						CommentParameter._deserialize( key, jsonObject[key] );
				};

				CommentParameter.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;

						switch( key ){
						case 'value':
							CommentParameter.value(variable[key]);
							break;
						default:
							CommentParameter._upgrade( key, variable[key], CommentParameter );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					CommentParameter.deserialize(jsonObject);
			}; // End of CommentParameter
			DataStructure.newCommentParameter = function(){
				switch( arguments.length ){
					case 0:
						return new CommentParameter();
					case 1:
						return new CommentParameter(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newCommentParameter');
						return null;
				}
			};

			var GroupParameter = function( jsonObject ){
				var GroupParameter = this;
				_Parameter.apply(GroupParameter);
				GroupParameter.type( OSP.Constants.GROUP);
				GroupParameter.active(true);

				GroupParameter.parameters = function( parameters ){
					return GroupParameter.property.apply(GroupParameter, OSP.Util.addFirstArgument(OSP.Constants.PARAMETERS, arguments));
				};

				GroupParameter.attachParameter = function( parameterName ){
					var parameters = GroupParameter.parameters();
					if( !parameters ){
						parameters = [];
						GroupParameter.parameters( parameters );
					}

					parameters.push( parameterName );
					return parameters;
				};

				GroupParameter.detachParameter = function( parameterName ){
					var parameters = GroupParameter.parameters();
					if( !parameters )	return true;

					for( var index in parameters ){
						var parameter = conditions[index];
						if( parameter === parameterName ){
							parameters.splice( index, 1 );
							return parameters;
						}
					}

					return parameters;
				};

				GroupParameter.hasParameter = function( parameterName ){
					var parameters = GroupParameter.parameters();
					for(var index in parameters ){
						if(parameters[index] === parameterName )	return true;
					}
					return false;
				};

				GroupParameter.clone = function(){
					return new CommentParameter( OSP.Util.toJSON(GroupParameter) );
				};

				GroupParameter.deserialize = function( jsonObject ){
					for( var key in jsonObject )
						GroupParameter._deserialize( key, jsonObject[key] );
				};

				GroupParameter.upgrade = function( variable ){
					for( var key in variable ){
						if( OSP.Util.isEmpty(variable[key]) )	continue;

						switch( key ){
						case 'activate-condition-container':
							var parameters = [];
							var oldConditions = variable[key]['container'];
							for( var index in oldConditions ){
								parameters.push( oldConditions[index]['variable-name'] );
							}
							GroupParameter.parameters( parameters );
							break;
						default:
							GroupParameter._upgrade( key, variable[key], GroupParameter );
							break;
						}
					}
				};

				if( arguments.length === 1 )
					GroupParameter.deserialize(jsonObject);
			}; // End of GroupParameter
			DataStructure.newGroupParameter = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new GroupParameter();
					case 1:
						return new GroupParameter(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newGroupParameter');
						return null;
				}
			};

			var VectorForm = function( jsonObject ) {
				var VectorForm = this;
				OSP._MapObject.apply( VectorForm );

				VectorForm.setForm = function(braceChar, delimiter, sample) {
					VectorForm.braceChar( braceChar );
					VectorForm.delimiter( delimiter );
					VectorForm.sample( sample );
				};
				VectorForm.braceChar = function( char ) {
					return VectorForm.property.apply(VectorForm, OSP.Util.addFirstArgument(OSP.Constants.BRACE_CHAR, arguments));
				};

				VectorForm.delimiter = function( delimiter ) {
					return VectorForm.property.apply(VectorForm, OSP.Util.addFirstArgument(OSP.Constants.DELIMITER, arguments));
				};

				VectorForm.space = function( space ) {
					return VectorForm.property.apply(VectorForm, OSP.Util.addFirstArgument(OSP.Constants.SPACE, arguments));
				};

				VectorForm.sample = function( sample ) {
					return VectorForm.property.apply(VectorForm, OSP.Util.addFirstArgument(OSP.Constants.SAMPLE, arguments));
				};

				VectorForm.clone = function(){
					return new VectorForm( OSP.Util.toJSON(VectorForm) );
				};

				VectorForm.deserialize = function( jsonForm ){
					for( var key in jsonForm ){
						VectorForm._deserialize( key, jsonForm[key] );
					}
				};

				VectorForm.upgrade = function( oldForm ){
					for( var key in oldForm ){
						if( OSP.Util.isEmpty(oldForm[key]) )	continue;
						//console.log( key + ": " + oldForm[key]);
						switch( key ){
						case 'brace-char':
							VectorForm.braceChar(oldForm[key]);
							break;
						case 'delimiter':
							VectorForm.delimiter(oldForm[key]);
							break;
						case 'space':
							VectorForm.space( oldForm[key] );
							break;
						case 'sample':
							VectorForm.sample( oldForm[key] );
							break;
						default:
							alert( 'Unknown vector form key: '+key);
							break;
						}
					}
				};

				if( arguments.length === 1 )
					VectorForm.deserialize(jsonObject);
			}; // End of VectorForm
			DataStructure.newVectorForm = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new VectorForm();
					case 1:
						return new VectorForm(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newVectorForm');
						return null;
				}
			};

			var ParameterForm = function( jsonObject ) {
				var ParameterForm = this;
				OSP._MapObject.apply( ParameterForm );

				ParameterForm.setForm = function(valueDelimiter, parameterDelimiter, commentChar, controlChar) {
					ParameterForm.valueDelimiter( valueDelimiter );
					ParameterForm.parameterDelimiter( parameterDelimiter );
					ParameterForm.commentChar( commentChar );
					ParameterForm.controlChar( controlChar );
				};

				ParameterForm.valueDelimiter = function( delimiter ) {
					return ParameterForm.property.apply(ParameterForm, OSP.Util.addFirstArgument(OSP.Constants.VALUE_DELIMITER, arguments));
				};

				ParameterForm.parameterDelimiter = function( delimiter ) {
					return ParameterForm.property.apply(ParameterForm, OSP.Util.addFirstArgument(OSP.Constants.PARAMETER_DELIMITER, arguments));
				};

				ParameterForm.commentChar = function( char ) {
					return ParameterForm.property.apply(ParameterForm, OSP.Util.addFirstArgument(OSP.Constants.COMMENT_CHAR, arguments));
				};

				ParameterForm.space = function( space ) {
					return ParameterForm.property.apply(ParameterForm, OSP.Util.addFirstArgument(OSP.Constants.SPACE, arguments));
				};

				ParameterForm.controlChar = function( char ){
					return ParameterForm.property.apply(ParameterForm, OSP.Util.addFirstArgument(OSP.Constants.CONTROL_CHAR, arguments));
				};

				ParameterForm.clone = function(){
					return new ParameterForm( OSP.Util.toJSON(ParameterForm) );
				};

				ParameterForm.deserialize = function( jsonForm ){
					for( var key in jsonForm ){
						ParameterForm._deserialize( key, jsonForm[key] );
					}
				};

				ParameterForm.upgrade = function( oldForm ){
					for( var key in oldForm ){
						if( OSP.Util.isEmpty(oldForm[key]) )	continue;

						switch( key ){
						case 'value-delimiter':
							ParameterForm.valueDelimiter(oldForm[key]);
							break;
						case 'variable-delimiter':
							ParameterForm.parameterDelimiter(oldForm[key]);
							break;
						case 'space':
							ParameterForm.space( oldForm[key] );
							break;
						case 'control-char':
							ParameterForm.controlChar( oldForm[key] );
							break;
						case 'comment-char':
							ParameterForm.commentChar( oldForm[key] );
							break;
						default:
							alert( 'Unknown parameter form key: '+key);
							break;
						}
					}
				};

				if( arguments.length === 1 )
					ParameterForm.deserialize( jsonObject );
			}; // End of ParameterForm
			DataStructure.newParameterForm = function( jsonObject ){
				switch( arguments.length ){
					case 0:
						return new ParameterForm();
					case 1:
						return new ParameterForm(jsonObject);
					default:
						colsole.log( 'Arguments mismatch: newParameterForm');
						return null;
				}
			};

			DataStructure.vectorForm = function( form ) {
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.VECTOR_FORM, arguments));
			};

			DataStructure.setVectorForm = function(braceChar, delimiter, sample) {
				var vectorForm = DataStructure.vectorForm();
				if( !vectorForm ){
					vectorForm = DataStructure.newVectorForm();
					DataStructure.vectorForm( vectorForm );
				}

				return vectorForm.setForm(braceChar, delimiter, sample);
			};

			DataStructure.vectorFormBraceChar = function( char ) {
				var vectorForm = DataStructure.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.braceChar.apply(vectorForm, arguments);
			};

			DataStructure.vectorFormDelimiter = function( delimiter ) {
				var vectorForm = DataStructure.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.delimiter.apply(vectorForm, arguments);
			};

			DataStructure.vectorFormSpace = function( space ) {
				var vectorForm = DataStructure.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.space.apply(vectorForm, arguments);
			};

			DataStructure.vectorFormSample = function( sample ) {
				var vectorForm = DataStructure.vectorForm();
				if( !vectorForm )	return false;
				return vectorForm.sample.apply(vectorForm, arguments);
			};

			DataStructure.parameterForm = function( form ) {
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.PARAMETER_FORM, arguments));
			};

			DataStructure.setParameterForm = function(valueDelimiter, parameterDelimiter, commentChar, controlChar) {
				var parameterForm = DataStructure.parameterForm();
				if( !parameterForm ){
					parameterForm = new ParameterForm();
					DataStructure.parameterForm( parameterForm );
				}

				return parameterForm.setForm(valueDelimiter, parameterDelimiter, commentChar, controlChar);
			};

			DataStructure.parameterFormValueDelimiter = function( delimiter ) {
				var form = DataStructure.parameterForm();
				if( !form )	return false;
				return form.valueDelimiter.apply(form, arguments);
			};

			DataStructure.parameterFormParameterDelimiter = function( delimiter ) {
				var form = DataStructure.parameterForm();
				if( !form )	return false;
				return form.parameterDelimiter.apply(form, arguments);
			};

			DataStructure.parameterFormCommentChar = function( char ) {
				var form = DataStructure.parameterForm();
				if( !form )	return false;
				return form.commentChar.apply(form, arguments);
			};

			DataStructure.parameterFormControlChar = function( char ) {
				var form = DataStructure.parameterForm();
				if( !form )	return false;
				return form.controlChar.apply(form, arguments);
			};

			DataStructure.parameterFormSpace = function( space ) {
				var form = DataStructure.parameterForm();
				if( !form )	return false;
				return form.space.apply(form, arguments);
			};

			DataStructure.parameters = function( parameters ) {
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.PARAMETERS, arguments));
			};

			DataStructure.parameter = function( parameterName ){
				var parameters = DataStructure.parameters();
				if( !parameters )	return false;

				if( parameters.hasOwnProperty(parameterName) )
					return parameters[parameterName];
				else
					return false;
			};

			DataStructure.parameterByOrder= function( order, groupOrder ){
				var parameters = DataStructure.parameters();
				if( !parameters )	return false;

				for( var key in parameters ){
					var parameter = parameters[key];

					if( !groupOrder ){
						if( !DataStructure.isInGroup( parameter.name() ) ){
							if( parameter.order() == order)	return parameter;
						}
					}
					else{
						var group = DataStructure.parameterByOrder(groupOrder);
						var names = group.getAttachedParameterNames();
						for(var i in names ){
							parameter = DataStructure.parameter(names[i]);
							if(parameter.order() == order)	return parameter;
						}
					}
				}
			};

			DataStructure.topLevelParameters = function(){
				var parameters = DataStructure.parameters();
				if( !parameters )	return false;

				var topLevels = [];
				for( var key in parameters ){
					var parameter = parameters[key];
					if( !DataStructure.isInGroup(parameter.name()) )
						topLevels.push( parameter );
				}
				return topLevels;
			};

			DataStructure.activeParameters = function(){
				var parameters = DataStructure.parameters();
				if( !parameters )	return false;

				var actives = [];
				for( var key in parameters ){
					var parameter = parameters[key];
					if( parameter.active() )
						actives.push( parameter );
				}
				return actives;
			};

			DataStructure.addParameter = function( parameter ) {
				var parameters = DataStructure.parameters();
				if( !parameters ){
					parameters = {};
					DataStructure.parameters( parameters );
				};

				parameters[parameter.name()] = parameter;
				return parameters;
			};

			DataStructure.removeParameter = function( parameterName ) {
				var parameters = DataStructure.parameters();
				if( !parameters )	return true;

				delete parameters[parameterName];

				return parameters;
			};

			DataStructure.newParameter = function( parameterName, type ){
				if( DataStructure.verifyParameterName(parameterName) == false )
					return false;

				var parameters = DataStructure.parameters();

				var parameter;
				switch( type ){
					case OSP.Constants.NUMERIC:
						parameter = DataStructure.newNumericParameter();
						break;
					case OSP.Constants.LIST:
						parameter = DataStructure.newListParameter();
						break;
					case OSP.Constants.STRING:
						parameter = DataStructure.newStringParameter();
						break;
					case OSP.Constants.VECTOR:
						parameter = DataStructure.newVectorParameter();
						break;
					case OSP.Constants.COMMENT:
						parameter = DataStructure.newCommentParameter();
						break;
					case OSP.Constants.GROUP:
						parameter = DataStructure.newGroupParameter();
						break;
					case OSP.Constants.FILE:
					default:
						parameter = false;
				}

				parameter.name( parameterName );
				DataStructure.addParameter(parameter);
				return parameter;
			};

			DataStructure.sweepMax = function( max ){
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_MAX, arguments));
			};

			DataStructure.sweepCount = function( count ){
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.SWEEP_COUNT, arguments));
			};

			DataStructure.increaseSweepCount = function(){
				var count = DataStructure.sweepCount();
				DataStructure.sweepCount(++count);
				return DataStructure.sweepCount();
			};

			DataStructure.decreaseSweepCount = function(){
				var count = DataStructure.sweepCount();
				DataStructure.sweepCount(count>0 ? --count : 0);
				return DataStructure.sweepCount();
			};

			DataStructure.sweepedParameters = function(){
				var parameters = DataStructure.parameters();

				var sweepedParameters = [];
				for(var i in parameters ){
					var parameter = parameters[i];
					if( parameter.sweeped() )
						sweepedParameters.push( parameter );
				}

				return sweepedParameters;
			};

			DataStructure.cleanSweepedParameters = function(){
				var parameters = DataStructure.sweepedParameters();
				if( OSP.Util.isEmpty(parameters) )	return false;

				for( var index in parameters ){
					var parameter = parameters[index];
					parameter.removeSweep();
				}

				return true;
			};

			DataStructure.sweeped = function(){
				if( DataStructure.sweepCount() === 0)	return false;
				else		return true;
			};

			DataStructure.defaultLanguageId = function( languageId ){
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_LANGUAGE_ID, arguments));
			};

			DataStructure.availableLanguageIds = function( languageIds ){
				return DataStructure.property.apply(DataStructure, OSP.Util.addFirstArgument(OSP.Constants.AVAILABLE_LANGUAGE_IDS, arguments));
			};

			DataStructure.addLanuageId = function( languageId ){
				var languageIds = DataStructure.availableLanguageIds();
				if( !languageIds ){
					languageIds = [];
					DataStructure.availableLanguageIds(languageIds);
				}

				for( var index in languageIds){
					if( languageIds[index] === languageId )	return false;
				}

				languageIds.push(languageId);
				return true;
			};

			DataStructure.removeLanguageId = function( languageId ){
				var languageIds = DataStructure.availableLanuageIds();
				if( !languageIds )	return true;

				for( var index in languageIds){
					if( languageIds[index] === languageId ){
						languageIds.splice( index, 1);
						return true;
					}
				}

				return false;
			};

			DataStructure.verifyParameterName = function( parameterName ){
				if(/[a-zA-Z\\_]/.test(parameterName.charAt(0)) === false) return false;
				if(/[^a-zA-Z0-9\\_]/.test(parameterName))		return false;

				var parameters = DataStructure.parameters();
				if( !parameters )	return true;

				for( var i in parameters ){
					var parameter = parameters[i];
					if( parameter.name() === parameterName )
						return false;
				}

				return true;
			};

			DataStructure.cloneParameter = function( parameter, count ){
				var jsonData = JSON.stringify( parameter );
				//console.log(JSON.stringify(parameter));
				var prefix = parameter.name();
				var index;
				var clones = [];
				for(index=0; index<count; index++){
					var name = prefix + "_" + (index+1);
					var emptyIndex = index+1;

					while(!DataStructure.verifyParameterName(name)){
						name = prefix + "_" + emptyIndex;
						emptyIndex++;
					}

					var clone = DataStructure.newParameter( name, parameter.type());
					var data = JSON.parse(jsonData);
					clone.deserialize(data);
					if( clone.verifyParameterName(name) ){
						clone.name(name);
						clone.removeProperty(OSP.Constants.ACTIVATE_CONDITIONS);
						clones.push(clone);
						//console.log(JSON.stringify(clone));
					}
				}

				//console.log("index: "+index);
				if( index == count ){
					for( index = 0; index < count; index++ ){
						DataStructure.addParameter(clones[index]);
					}
				}
				else
					return null;

				return clones;
			};

			DataStructure.cloneParameterGroup = function( groupParameter, count ){
				if( groupParameter.type() !== OSP.Constants.GROUP )
					return [];

				var newGroups = DataStructure.cloneParameter( groupParameter, count );
				if( newGroups == null )	return null;

				for( var i in newGroups ){
					var group = newGroups[i];
					group.removeProperty(OSP.Constants.ACTIVATE_CONDITIONS);

					var orgSet = groupParameter.activateConditions();
					for( var j in orgSet ){
						var orgParamName = orgSet[j].parameterName();
						var orgParameter = DataStructure.getParameter( orgParameterName );
						var paramClones = DataStructure.cloneParameter( orgParameter, 1 );

						var clone = paramClones[0];
						DataStructure.addParameter(clone);
						group.attachParameter( clone.name() );
					}
				}

				return newGroups;
			};

			DataStructure.parameterNames = function(){
				var parameters = DataStructure.parameters();
				if( !parameters )	return false;

				var names = [];
				switch( arguments.length ){
				case 0:
					for( var index in parameters ){
						names.push( parameters[index].name() );
					}
					break;
				default:
					for(var index = 0; index < arguments.length; index++) {
						for( var param in parameters ){
							if( parameters[param].type() === arguments[index] ){
								names.push( parameters[param].name() );
							}
						}
					}
				}
				return names;
			};

			DataStructure.isInGroup = function(parameterName){
				var names = DataStructure.parameterNames(OSP.Constants.GROUP);
				//console.log(JSON.stringify(names));
				for(var index in names ){
					var group = DataStructure.parameter(names[index]);
					if( group.hasParameter(parameterName))	return true;
				}
				return false;
			};

			DataStructure.deserialize = function( jsonObject ){
				//console.log( JSON.stringify(jsonObject,null,4));
				for( var key in jsonObject ){
					switch ( key ){
					case OSP.Constants.VECTOR_FORM:
						DataStructure.vectorForm( DataStructure.newVectorForm( jsonObject[key]) );
						break;
					case OSP.Constants.PARAMETER_FORM:
						DataStructure.parameterForm( DataStructure.newParameterForm( jsonObject[key]) );
						break;
					case OSP.Constants.PARAMETERS:
						var jsonParameters = jsonObject[key];
						for( var i in jsonParameters ){
							var jsonParameter = jsonParameters[i];
							var parameter;
							switch( jsonParameter[OSP.Constants.TYPE] ){
							case OSP.Constants.COMMENT:
								parameter = DataStructure.newCommentParameter( jsonParameter );
								break;
							case OSP.Constants.GROUP:
								parameter = DataStructure.newGroupParameter( jsonParameter );
								break;
							case OSP.Constants.LIST:
								parameter = DataStructure.newListParameter( jsonParameter );
								break;
							case OSP.Constants.NUMERIC:
								parameter = DataStructure.newNumericParameter( jsonParameter );
								break;
							case OSP.Constants.STRING:
								parameter = DataStructure.newStringParameter( jsonParameter );
								break;
							case OSP.Constants.VECTOR:
								parameter = DataStructure.newVectorParameter( jsonParameter );
								break;
							default:
								console.log('Un-recognizable parameter type: '+jsonParameter[OSP.Constants.TYPE]);
								return false;
							}

							DataStructure.addParameter(parameter);
						}
						break;
					default:
						//console.log( key+':'+jsonObject[key]);
						DataStructure._deserialize( key, jsonObject[key] );
					}
				}
			};

			DataStructure.clone = function(){
				return new DataStructure( OSP.Util.toJSON(DataStructure) );
			};

			DataStructure.activeParameterFormattedInputs = function( page ){
				//console.log( DataStructure);
				var parameterDelimiter = DataStructure.parameterFormParameterDelimiter();
				if( !parameterDelimiter ){parameterDelimiter = '\n';}else{ parameterDelimiter+='\n'; }
				var valueDelimiter = DataStructure.parameterFormValueDelimiter();
				var parameterSpaceChar = DataStructure.parameterFormSpace() ? parameterSpaceChar = ' ': parameterSpaceChar = '';
				var parameterCommentChar = DataStructure.parameterFormCommentChar();
				var vectorBraceChar = DataStructure.vectorFormBraceChar();
				var vectorDelimiter = DataStructure.vectorFormDelimiter();

				function getFormattedValue(parameter){
					var formattedString = [];
					switch(parameter.type()){
					case OSP.Constants.NUMERIC:
						if( parameter.sweeped() ){
							var sweepValues = parameter.getSweepSlicedValues();

							for(var i=0; i<sweepValues.length; i++){
								formattedString[i] =
									parameter.name() + parameterSpaceChar + valueDelimiter +
									parameterSpaceChar + sweepValues[i] + parameterSpaceChar + parameterDelimiter;
							}
						}
						else
							formattedString[0] =
								parameter.name() + parameterSpaceChar + valueDelimiter +
								parameterSpaceChar + parameter.value() + parameterSpaceChar + parameterDelimiter;
						break;
					case OSP.Constants.LIST:
					case OSP.Constants.STRING:
						formattedString[0] =
							parameter.name() + parameterSpaceChar + valueDelimiter +
							parameterSpaceChar + parameter.value() + parameterSpaceChar + parameterDelimiter;
						break;
					case OSP.Constants.COMMENT:
						formattedString[0] = parameterCommentChar +
							parameter.value() + parameterSpaceChar + parameterDelimiter;
						break;
					case OSP.Constants.VECTOR:
						var startBraceChar, endBraceChar;
						var vectorSpace;
						switch( vectorBraceChar ){
						case OSP.Constants.ROUND:
							startBraceChar = '(';
							endBraceChar = ')';
							vectorSpace = '';
							break;
						case OSP.Constants.ROUND_SPACE:
							startBraceChar = '( ';
							endBraceChar = ' )';
							vectorSpace = ' ';
							break;
						case OSP.Constants.SQUARE:
							startBraceChar = '[';
							endBraceChar = ']';
							vectorSpace = '';
							break;
						case OSP.Constants.SQUARE_SPACE:
							startBraceChar = '[ ';
							endBraceChar = ' ]';
							vectorSpace = ' ';
							break;
						}

						formattedString[0] =
							parameter.name() + parameterSpaceChar + valueDelimiter + parameterSpaceChar +
							parameter.getValueString(
									startBraceChar,
									endBraceChar,
									vectorDelimiter,
									vectorSpace) + parameterSpaceChar + parameterDelimiter;
						break;
					default:
						break;
					}
					//console.log(formattedString);
					return formattedString;
				};

				function getFormattedInputs(input, parameters){
					var inputs = [];
					for(var index in parameters){
						if( typeof parameters[index] === 'function') continue;

						var parameter = parameters[index];
						var values = getFormattedValue(parameter);
						switch(values.length){
						case 0:
							break;
						case 1:
							input.push(values[0]);
							break;
						default:
							var sweepInputs = [];
							for(var j=0; j<values.length; j++){
								var cloneInput = input.slice(0);
								cloneInput.push(values[j]);
								var newInputs = getFormattedInputs(cloneInput, parameters.slice(index+1));

								sweepInputs = sweepInputs.concat(newInputs);
							}
							return sweepInputs;
						}
					}
					//DataStructure.setFileContent(input.join("\n")+"\n");
					//inputs.push(DataStructure.clone());
					inputs.push(input);
					return inputs;
				};

				var activeParameters = DataStructure.activeParameters();

				var formattedInputs = getFormattedInputs([], activeParameters);
				//console.log(JSON.stringify(formattedInputs, null, 4));
				if( arguments.length === 1)
					return formattedInputs[page];
				else
					return formattedInputs;
			};

			DataStructure.setAllParametersInactive = function(){
				var parameters = DataStructure.activeParameters();
				for(var i=0; i<parameters.length; i++){
					var parameter = parameters[i];
					switch(parameter.type()){
					case OSP.Constants.COMMENT:
					case OSP.Constants.GROUP:
						break;
					default:
						parameter.active(false);
					}
				}
			};

			DataStructure.loadInput = function(input){
				var valueDelimiter = DataStructure.parameterFormValueDelimiter();
				var parameterDelimiter = DataStructure.parameterFormParameterDelimiter();
				if( !parameterDelimiter )	parameterDelimiter = '\n';
				var commentChar = DataStructure.parameterFormCommentChar();
				var lines =input.split(parameterDelimiter);
				DataStructure.setAllParametersInactive();

				for(var i in lines){
					var line = lines[i].trim().split(valueDelimiter);
					//console.log(line);
					var parameterName = line[0].trim();
					if( parameterName.length === 0) continue;
					var parameterValue;
					if ( parameterName.charAt(0) == commentChar  )
						parameterValue = parameterName.slice(1);
					else {
						parameterValue = line[1].trim();
						var parameter = DataStructure.parameter(parameterName);
						//console.log('parameter value: '+parameter.type());
						switch(parameter.type()){
						case OSP.Constants.VECTOR:
							parameter.value(JSON.parse(parameterValue));
							break;
						case OSP.Constants.NUMERIC:
							if(parameter.sweeped() === true){
								parameter.sweeped(false);
								DataStructure.decreaseSweepCount();
							}
							//alert(parameterName + ': '+parameter.sweeped());
						default:
							parameter.value(parameterValue);
							//console.log('value: '+parameterValue);
						}

						parameter.active(true);
					}
				}
			};

			DataStructure.upgrade = function ( oldStructure ){
				for( var key in oldStructure ){
					//console.log( 'key: '+key);
					if( OSP.Util.isEmpty( oldStructure[key] ) )
						continue;

					switch( key ){
					case 'sweep-max':
						DataStructure.sweepMax(oldStructure[key]);
						break;
					case 'variable-map':
						var variables = oldStructure[key]['map'];
						for( var variableName in variables ){
							var variable = variables[variableName];
							if( OSP.Util.isEmpty(variable) )	continue;

							var parameter;
							switch( variable['type']){
							case 'numeric':
								parameter = DataStructure.newNumericParameter();
								break;
							case 'control_active':
							case 'control_associate':
							case 'list':
								parameter = DataStructure.newListParameter();
								break;
							case 'string':
								parameter = DataStructure.newStringParameter();
								break;
							case 'vector':
								parameter = DataStructure.newVectorParameter();
								break;
							case 'group':
								parameter = DataStructure.newGroupParameter();
								break;
							case 'comment':
								parameter = DataStructure.newCommentParameter();
								break;
							default:
								alert('Unknown variable type: '+variable['type']);
								return;
							}

							parameter.upgrade(variable);
							DataStructure.addParameter( parameter );
						}
						break;
					case 'vector-form':
						var vectorForm = new VectorForm();
						vectorForm.upgrade( oldStructure[key] );
						DataStructure.vectorForm(vectorForm);
						break;
					case 'variable-form':
						var parameterForm = new ParameterForm();
						parameterForm.upgrade( oldStructure[key] );
						DataStructure.parameterForm(parameterForm);
						break;
					case 'default-language-id':
						DataStructure.defaultLanguageId(oldStructure[key]);
						break;
					case 'available-language-ids':
						var value = oldStructure[key].replace('{', '').replace('}', '');
						var idStrs =value.split(',');
						var languageIds = [];
						for( var index in idStrs ){
							var id = idStrs[index].trim();
							if( id.length > 0 )
								languageIds.push( id );
						}
						DataStructure.availableLanguageIds(languageIds);
						break;
					default:
						alert( 'Unknown data structure key: '+key);
						break;
					}
				}

				//newCanvas.append( JSON.stringify(DataStructure,null,4));
			};

			if( arguments.length === 1 )
				DataStructure.deserialize(jsonObject);
		}; // End of DataStructure
		DataType.newDataStructure = function( jsonObject ){
			switch( arguments.length ){
				case 0:
					return new DataStructure();
				case 1:
					return new DataStructure(jsonObject);
				default:
					colsole.log( 'Arguments mismatch: newDataStructure');
					return null;
			}
		};

		DataType.structure = function( structure ){
			return DataType.property.apply(DataType, OSP.Util.addFirstArgument(OSP.Constants.STRUCTURE, arguments));
		};

		DataType.defaultEditor = function( uuid ){
			return DataType.property.apply(DataType, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_EDITOR, arguments));
		};

		DataType.defaultAnalyzer = function( uuid ){
			return DataType.property.apply(DataType, OSP.Util.addFirstArgument(OSP.Constants.DEFAULT_ANALYZER, arguments));
		};

		DataType.availableEditors = function( editors ){
			return DataType.property.apply(DataType, OSP.Util.addFirstArgument(OSP.Constants.AVAILABLE_EDITORS, arguments));
		};

		DataType.availableAnalyzers = function( analyzers ){
			return DataType.property.apply(DataType, OSP.Util.addFirstArgument(OSP.Constants.AVAILABLE_ANALYZERS, arguments));
		};

		DataType.addEditor = function( editor ){
			var editors = DataType.availableEditors();
			if( !editors ){
				editors = [];
				DataType.availableEditors(editors);
			}
			editors.push(editor);

			return editors;
		};

		DataType.removeEditor = function( appId ){
			var editors = DataType.availableEditors();
			if( !editors )	return false;

			for( var key in editors ){
				if( editors[key] === appId ){
					editors.splice( key, 1);
					if( appId === DataType.defaultEditor() )
						DataType.removeProperty(OSP.Constants.DEFAULT_EDITOR);
					return true;
				}
			}

			return false;
		};

		DataType.addAnalyzer = function( editor ){
			var analyzers = DataType.availableAnalyzers();
			if( !analyzers ){
				analyzers = [];
				DataType.availableAnalyzers(analyzers);
			}
			analyzers.push(editor);

			return analyzers;
		};

		DataType.removeAnalyzer = function( uuid ){
			var analyzers = DataType.availableAnalyzers();

			if( !analyzers )	return false;

			for( var key in analyzers ){
				if( analyzers[key] === uuid ){
					analyzers.splice( key, 1);
					if( uuid === DataType.defaultAnalyzer() )
						DataType.removeProperty(OSP.Constants.DEFAULT_ANALYZER);
					return true;
				}
			}

			return false;
		};

		DataType.clone = function(){
			return new OSP.DataType( OSP.Util.toJSON(DataType) );
		};

		var displayUI =  function( dataStructure, namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay){
			canvas.empty();
			var Layout;
			var Style;

			var Util = {
				refreshJSON : function(){
					if( !jsonDisplay )	return;
					jsonDisplay.empty();
					jsonDisplay.html(JSON.stringify(dataStructure, null, 4));
					if( !jsonInputDisplay )	return;
					jsonInputDisplay.empty();
					jsonInputDisplay.html(JSON.stringify(dataStructure.getActiveParameterFormattedInputs(), null, 4));
				},
				convertUndefinedToEmptyString : function(value){
					if( !value )
						return '';
					else
						return value;
				},
				isFormView: function(flag){
					if( !flag || flag == false )
						return false;
					else return true;
				}
			}; // End of Util

			var TagAttr = {
				valueRangeInputId : function(parameter){
					return namespace+'_'+parameter.name()+'_range';
				},
				valueRangeInputName : function(parameter){
					return namespace+'_'+parameter.name()+'_range';
				},
				sweepCheckBoxId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweepCheckBox';
				},
				sweepMethodRadioBySliceId: function(parameter){
					return namespace+'_'+parameter.name()+'_sweepMethodBySlice';
				},
				sweepMethodRadioName: function(parameter){
					return namespace+'_'+parameter.name()+'_sweepMethod';
				},
				sweepMethodRadioByValueId: function(parameter){
					return namespace+'_'+parameter.name()+'_sweepMethodByValue';
				},
				sweepRangeLowerBoundaryId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_lowerBoundary';
				},
				sweepRangeUpperBoundaryId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_upperBoundary';
				},
				sweepRangeLowerOperandId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_lowerOperand';
				},
				sweepRangeUpperOperandId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_upperOperand';
				},
				sweepSliceValueId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_sliceValue';
				},
				sweepSliceValueName : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_sliceValue';
				},
				sweepSliceValueUnitId : function(parameter){
					return namespace+'_'+parameter.name()+'_sweep_sliceValueUnit';
				},
				vectorInputDivId : function(parameter){
					return namespace+'_'+parameter.name()+'_vectorInputDiv';
				},
				parameterRowId : function(parameter){
					return namespace+'_'+parameter.name();
				},
				inputPageId: function(page){
					return namespace+'_page_'+page;
				}
			}; // End of TagAttr

			var valueRangeBoundaryDiv = function(parameter, place){
				var boundary;
				var div = $('<div/>');
				if( place === OSP.Constants.LOWER_BOUNDARY){
					boundary = parameter.rangeLowerBoundary();
					div.prop('style', Style.ValueRangeLowerBoundaryDiv);
				}
				else{
					boundary = parameter.rangeUpperBoundary();
					div.prop('style', Style.ValueRangeUpperBoundaryDiv);
				}
				div.append( boundary );

				return div;
			};
			var rangeUnitDiv = function(parameter){
				var unit = parameter.unit();
				if( !unit )
					unit = '';

				var div = $('<div/>');
				div.prop('style', Style.RangeUnitDiv);
				div.append(unit);

				return div;
			};
			var valueRangeOperandDiv = function(parameter, place){
				var div = $('<div/>');
				div.prop('style', Style.ValueRangeOperandDiv);
				var operand;
				if( place === OSP.Constants.LOWER_BOUNDARY ){
					if( parameter.rangeCheckLowerBoundary() )
						operand = '&le;';
					else
						operand = '<';
				}
				else {
					if( parameter.rangeCheckUpperBoundary())
						operand = '&le;';
					else
						operand = '<';
				}
				div.append(operand);

				return div;
			};
			var valueRangeInputDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop('style', Style.ValueRangeInputDiv);

				var input = $('<input>');
				input.prop({
					'type':'text',
					'id' : TagAttr.valueRangeInputId(parameter),
					'name': TagAttr.valueRangeInputName(parameter),
					'style': Style.ValueRangeInput,
					'value': parameter.value()
				});

				if( eventFlag === true ){
					//console.log('onChange Handler binded...');
					var onInputChange = function (event){
						var newValue = event.data.sourceTag.val();
						//console.log('parameter value changed: ' + newValue);
						var parameter = 	event.data.sourceParameter;
						if( parameter.value(newValue) == false ){
							alert(newValue+' is out of the range.');
							event.data.sourceTag.val(parameter.value());
						}
						checkActivateParameters(parameter);
						Util.refreshJSON();
					}

					input.bind('change',
							{
								'sourceTag': input,
								'sourceParameter':parameter
							},
							onInputChange
					);

					div.setParameterValue = function(value){
						input.prop('value', value);
						input.trigger( "change" );
					};
				}

				div.append(input);

				return div;
			};
			var sweepCheckBoxDiv = function(parameter){
				var div = $('<div/>');
				div.prop('style', Style.SweepCheckBoxDiv);
				//div.addClass(Layout.SweepCheckBoxDiv);
				var label = $('<label/>');
				label.prop('style', Style.SweepRadio);
				var checkBox = $('<input type=\"checkbox\" >Sweep</input>');
				if( parameter.sweeped() )	checkBox.prop('checked', 'checked');
				var id = TagAttr.sweepCheckBoxId(parameter);
				checkBox.prop('id', id);

				label.append(checkBox);
				div.append(label);

				return div;
			};
			var sweepMethodDiv = function(parameter){
				var div = $('<div/>');
				div.addClass(Layout.SweepMethodValue);
				div.addClass(Layout.SweepMethodDiv);
				div.prop('style', Style.SweepMethodDiv);

				var divRadio = $('<div/>');
				divRadio.addClass(Layout.SweepRadioBySlice);

				var label = $("<label/>");
				var radioBySlice = $('<input type=\"radio\" value=\"slice\">By Slice</input>');
				//radioBySlice.addClass(Layout.SweepRadioBySlice);
				radioBySlice.prop({
					'id': TagAttr.sweepMethodRadioBySliceId(parameter),
					'name': TagAttr.sweepMethodRadioName(parameter),
					'style': Style.SweepRadioBySlice
				});
				label.append(radioBySlice)
				divRadio.append(label);
				div.append(divRadio);

				divRadio = $('<div/>');
				divRadio.addClass(Layout.SweepRadioByValue);
				label = $("<label/>");

				var radioByValue = $('<input type=\"radio\" value=\"value\">By Value</input>');
				//radioByValue.addClass(Layout.SweepRadioByValue);
				radioByValue.prop({
					'id': TagAttr.sweepMethodRadioByValueId(parameter),
					'name': TagAttr.sweepMethodRadioName(parameter),
					'style': Style.SweepRadioByValue
				});

				if( parameter.sweepMethod() == OSP.Enumeration.SweepMethod.BY_SLICE )
					radioBySlice.prop('checked', 'checked');
				else
					radioByValue.prop('checked', 'checked');

				label.append(radioByValue)
				divRadio.append(label);
				div.append(divRadio);

				return div;
			};

			function onSweepChange(event){
				var newValue = event.data.sourceTag.val();
				var section = event.data.section;
				var parameter = 	event.data.sourceParameter;
				switch(section){
				case OSP.Constants.LOWER_BOUNDARY:
					if(parameter.sweepRangeLowerBoundary(newValue) == false){
						alert(newValue+' is out of the range.');
						event.data.sourceTag.val(parameter.sweepRangeLowerBoundary());
					}
					//console.log(JSON.stringify(parameter.getSweep(), null, 4));
					break;
				case OSP.Constants.UPPER_BOUNDARY:
					//console.log('sweepRangeUpperBoundary: '+parameter.sweepRangeUpperBoundary());
					if(parameter.sweepRangeUpperBoundary(newValue) == false){
						alert(newValue+' is out of the range.');
						event.data.sourceTag.val(parameter.sweepRangeUpperBoundary());
					}
					//console.log(JSON.stringify(parameter.sweep(), null, 4));
					break;
				case OSP.Constants.LOWER_OPERAND:
				//console.log('lower operand changed: '+newValue);
				if( newValue === '=')
						parameter.sweepRangeIncludeBoundary(OSP.Constants.LOWER_BOUNDARY, true);
					else
						parameter.sweepRangeIncludeBoundary(OSP.Constants.LOWER_BOUNDARY, false);
					break;
				case OSP.Constants.UPPER_OPERAND:
					//console.log('upper operand changed: '+newValue);
					if( newValue === '=')
						parameter.sweepRangeIncludeBoundary(OSP.Constants.UPPER_BOUNDARY, true);
					else
						parameter.sweepRangeIncludeBoundary(OSP.Constants.UPPER_BOUNDARY, false);
					break;
				case OSP.Enumeration.DivSection.SWEEP_SLICE_VALUE:
					var maxSlice = Number(parameter.maxSweepSlice());
					var sweepMethod = parameter.sweepMethod();
					if( sweepMethod === OSP.Enumeration.SweepMethod.BY_SLICE ){
						if(newValue > maxSlice || newValue < 2){
							alert('Slice should be between 2 and ' + maxSlice + '.');
							event.data.sourceTag.val( newValue < 2 ? 2 : parameter.sweepSliceCount() );
						}
						else
							parameter.sweepSliceCount( newValue );
					}
					else{
						var slices = OSP.Util.safeFloatSubtract(parameter.sweepRangeUpperBoundary(),
																									parameter.sweepRangeLowerBoundary()) / newValue;
						if( slices > maxSlice || slices < 2){
							alert('Slice should be between 2 and ' + maxSlice + ' by slice value.');
							event.data.sourceTag.val(parameter.sweepSliceValue());
						}
						else
							parameter.sweepSliceValue(newValue);
					}
					break;
				default:
				}
				Util.refreshJSON();
			};

			var sweepRangeBoundaryDiv = function(parameter, place){
				var limit;
				var div = $('<div/>');
				div.prop('style', Style.SweepRangeLowerBoundaryDiv);
				var input = $('<input/>');
				var inputId;
				if( place === OSP.Constants.LOWER_BOUNDARY){
					limit = parameter.sweepRangeLowerBoundary();
					inputId = TagAttr.sweepRangeLowerBoundaryId(parameter);
				}
				else{
					limit = parameter.sweepRangeUpperBoundary();
					inputId = TagAttr.sweepRangeUpperBoundaryId(parameter);
				}
				var props = {
					'type' : 'text',
					'id' : inputId,
					'value' : limit,
					'style' : Style.SweepRangeLowerBoundary
				};
				input.prop(props);
				input.bind('change',
						{
							'sourceTag': input,
							'sourceParameter':parameter,
							'section' : place
						},
						onSweepChange
				);
				div.append(input);

				return div;
			};
			var sweepRangeOperandDiv = function(parameter, place){
				var div = $('<div/>');
				div.prop('style', Style.SweepRangeOperandDiv);
				var select = $('<select/>');
				select.prop('style', Style.SweepRangeOperandSelect);
				var selectId;
				var optionE, optionT;

				if( place === OSP.Constants.LOWER_OPERAND ){
					selectId = TagAttr.sweepRangeLowerOperandId(parameter);
					optionE = $('<option value=\"=\">&le;</option>');
					optionT = $('<option value=\"&lt;\">&lt;</option>');
					//console.log('isSweepRangeLowerBoundaryContained :' + parameter.isSweepRangeLowerBoundaryContained());
					if(parameter.sweepRangeCheckLowerBoundary() == true)
						optionE.prop('selected', 'selected');
					else
						optionT.prop('selected', 'selected');
				}
				else{
					//console.log('isSweepRangeUpperBoundaryContained :' + parameter.isSweepRangeUpperBoundaryContained());
					selectId = TagAttr.sweepRangeUpperOperandId(parameter);
					optionE = $('<option value=\"=\">&le;</option>');
					optionT = $('<option value=\"&gt;\">&lt;</option>');
					if(parameter.sweepRangeCheckUpperBoundary() == true)
						optionE.prop('selected', 'selected');
					else
						optionT.prop('selected', 'selected');
				}

				select.append(optionE);
				select.append(optionT);
				select.bind('change',
						{
							'sourceTag': select,
							'sourceParameter':parameter,
							'section' : place
						},
						onSweepChange
				);

				div.append(select);
				return div;
			};
			var sweepSliceValueDiv = function(parameter){
				var div = $('<div/>');
				div.prop('style', Style.SweepSliceValueDiv);
				var input = $('<input>');
				input.prop({
					'type':'text',
					'id' : TagAttr.sweepSliceValueId(parameter),
					'name': TagAttr.sweepSliceValueName(parameter),
					'style': Style.SweepSliceValue
				});
				var value;
				if(parameter.sweepMethod() == OSP.Enumeration.SweepMethod.BY_SLICE  ){
					value = Util.convertUndefinedToEmptyString(parameter.sweepSliceCount());
				}else{
					value = Util.convertUndefinedToEmptyString(parameter.sweepSliceValue());
				}

				if( value )
					input.val(value);

				input.bind('change',
						{
							'sourceTag': input,
							'sourceParameter':parameter,
							'section': OSP.Enumeration.DivSection.SWEEP_SLICE_VALUE
						},
						onSweepChange
				);

				div.append(input);
				div.append( sweepSliceValueUnitDiv(parameter) );

				return div;
			};
			var sweepSliceValueUnitDiv = function (parameter){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.sweepSliceValueUnitId(parameter),
					'style': Style.SweepSliceValueUnitDiv
				});
				if( parameter.sweepMethod() == true)
					div.append('Slices');
				else{
					if ( parameter.unit() )
						div.append(parameter.unit());
				}
				return div;
			};
			var sweepRangeDiv = function(parameter){
				var div = $('<div/>');
				div.addClass(Layout.SweepMethodValue);
				div.addClass(Layout.SweepMethodDiv);
				div.prop('style', Style.SweepRangeDiv);

				div.append(sweepRangeBoundaryDiv(parameter, OSP.Constants.LOWER_BOUNDARY));
				div.append(rangeUnitDiv(parameter));
				div.append(sweepRangeOperandDiv(parameter, OSP.Constants.LOWER_OPERAND));
				div.append($('<div style=\"display:inline;\">x</div>'));
				div.append(sweepRangeOperandDiv(parameter, OSP.Constants.UPPER_OPERAND));
				div.append(sweepRangeBoundaryDiv(parameter, OSP.Constants.UPPER_BOUNDARY));
				div.append(rangeUnitDiv(parameter));

				var divSweepSliceValue = sweepSliceValueDiv(parameter);
				div.append(divSweepSliceValue);
				div.setSweepSliceValue = function(value){
					var input = divSweepSliceValue.find('input');
					input.val(value);
				};
				div.setSweepSliceValueUnit = function(unit){
					var unitDiv = $('#'+TagAttr.sweepSliceValueUnitId(parameter));
					unitDiv.html(unit);
				};

				return div;
			};

			var sweepDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.SweepMethodValueDiv);

				if(parameter.sweeped()){
					div.prop('style', Style.SweepDivVisible);
				}
				else{
					if( eventFlag === true ){
						div.prop('style', Style.SweepDivInvisible);
					}else{
					    div.prop('style', Style.SweepDivVisible);
					}
				}

				var divSweepRange = sweepRangeDiv(parameter);
				var divSweepMethod = sweepMethodDiv(parameter);

				var onSweepMethodChange = function (event){
					var sweepMethodDiv = event.data.sweepMethodDiv;
					var sweepRangeDiv = event.data.sweepRangeDiv;
					var parameter = event.data.parameter;
					var method = sweepMethodDiv.find('input:checked').val();
					if( method === OSP.Enumeration.SweepMethod.BY_SLICE ){
						parameter.sweepMethod( OSP.Enumeration.SweepMethod.BY_SLICE );
						sweepRangeDiv.setSweepSliceValue(
								Util.convertUndefinedToEmptyString(parameter.sweepSliceCount()));
						sweepRangeDiv.setSweepSliceValueUnit('Slices');
					}
					else{
						parameter.sweepMethod( OSP.Enumeration.SweepMethod.BY_VALUE);
						sweepRangeDiv.setSweepSliceValue(
								Util.convertUndefinedToEmptyString(parameter.sweepSliceValue()));
						sweepRangeDiv.setSweepSliceValueUnit(
								Util.convertUndefinedToEmptyString(parameter.unit()));
					}
					Util.refreshJSON();
				}

				var data = {
						'sweepMethodDiv': divSweepMethod,
						'sweepRangeDiv': divSweepRange,
						'parameter': parameter
				};
				divSweepMethod.bind('change',
						data,
						onSweepMethodChange);

				div.append(divSweepMethod);
				div.append(divSweepRange);

				return div;
			};
			var parameterNameDiv = function(parameter){
				var div = $('<div/>');
				div.addClass(Layout.ParameterNameDiv);
				div.prop('style', Style.ParameterNameDiv);
				//console.log('languageId: '+languageId);
				var name = parameter.name();
				div.append(name+' : ');
				return div;
			};
			var numericParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.NumericParameterValueDiv);
				var range = parameter.range();
				var divInput = valueRangeInputDiv(parameter, eventFlag);
				if( !range || JSON.stringify(range) === '{}'){
					div.append(divInput);
					div.append(rangeUnitDiv(parameter));
				}
				else {
					var lowerBoundary = parameter.rangeLowerBoundary();
					if( lowerBoundary ){
						div.append(valueRangeBoundaryDiv(parameter, OSP.Constants.LOWER_BOUNDARY));
						div.append(rangeUnitDiv(parameter));
						div.append(valueRangeOperandDiv(parameter, OSP.Constants.LOWER_OPERAND));
					}
					divInput = valueRangeInputDiv(parameter, eventFlag);
					div.append(divInput);
					div.append(rangeUnitDiv(parameter));

					var upperBoundary = parameter.rangeUpperBoundary();
					if( upperBoundary ){
						div.append(valueRangeOperandDiv(parameter, OSP.Constants.UPPER_OPERAND));
						div.append(valueRangeBoundaryDiv(parameter, OSP.Constants.UPPER_BOUNDARY));
						div.append(rangeUnitDiv(parameter));
					}
				}

				if( parameter.sweepable() ){
					var divSweep = sweepDiv(parameter, eventFlag);
					var divCheckBox = sweepCheckBoxDiv(parameter);
					var checkBox = divCheckBox.find('input');

					var onSweepDivToggle = function (event){
						var origin = event.data.origin;
						var target = event.data.target;
						var parameter = event.data.sourceParameter;
						if( parameter.sweeped() ){
							parameter.sweeped(false);
							dataStructure.sweepCount() > 0 ? dataStructure.decreaseSweepCount() : 0;
							target.toggle();
						}
						else{
							if( dataStructure.sweepMax() == dataStructure.sweepCount() ){
								origin.prop('checked', false);
							}
							else{
								dataStructure.increaseSweepCount();
								parameter.sweeped(true);
								target.toggle();
							}
						}
						Util.refreshJSON();
					}

					var data = {
							'origin': checkBox,
							'target': divSweep,
							'sourceParameter': parameter
					};
					checkBox.bind('change', data, onSweepDivToggle);

					div.append(divCheckBox);
					div.append(divSweep);
				}


				if( eventFlag === true){
					div.setParameterValue = function(value){
						divInput.setParameterValue(value);
					};
				}

				return div;
			};
			var listParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.ListParameterValueDiv);
				var select = $('<select/>');
				var id = TagAttr.valueRangeInputId(parameter);
				var name = id;
				select.prop({
					'id' : id,
					'name' : name
				});
				var listItems = parameter.localizedListItems(languageId);
				if( OSP.Util.isEmpty(listItems) ){
					listItems = parameter.localizedListItems(dataStructure.defaultLanguageId());
				}
				//console.log(JSON.stringify(listItems,null,4));

				var savedValue = parameter.value();
				for(var key in listItems){
					var listItem = listItems[key];
					var option = $('<option/>');
					option.val(key);
					if( savedValue == key )
						option.prop('selected', 'selected');
					option.text(listItem);
					select.append(option);
				}

				if( eventFlag === true ){
					var onSelectChange = function (event){
						var newValue = event.data.sourceTag.val();
						//console.log('parameter value changed: ' + newValue);
						var parameter = 	event.data.sourceParameter;
						if( parameter.value(newValue) == false ){
							alert(newValue+' is out of the range.');
							event.data.sourceTag.val(parameter.value());
						}
						checkActivateParameters(parameter);
						Util.refreshJSON();
					}

					var data = {
							'sourceTag': select,
							'sourceParameter': parameter
					};
					select.bind('change', data, onSelectChange);
					div.setParameterValue = function(value){
						select.val(value);
						select.trigger('change');
					};
					div.setDisplayNone = function(itemValue){
						//console.log('Display None: ' + 'listParameterValueDiv');
						var option = select.find('option[value=\"'+itemValue+'\"]');
						option.prop('style', 'display:none');
					};
					div.listItemToggle = function(itemValue, flag){
						//console.log('ListItem Toggle: ' + 'listParameterValueDiv');
						var option = select.find('option[value=\"'+itemValue+'\"]');
						option.toggle(flag);
					}
				}

				div.append(select);
				return div;
			};
			var vectorParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.VectorParameterValueDiv);
				var leftBrace, rightBrace;
				switch(dataStructure.vectorFormBraceChar()){
				case OSP.Constants.SQUARE_SPACE:
				case OSP.Constants.SQUARE:
					leftBrace = '[ ';
					rightBrace = ' ]';
					break;
				case OSP.Constants.ROUND_SPACE:
				case OSP.Constants.ROUND:
					leftBrace = '( ';
					rightBrace = ' )';
					break;
				}
				var vector = parameter.value();
				var leftBraceDiv = $('<div style=\"display:inline;\"/>');
				leftBraceDiv.append(leftBrace);
				div.append(leftBraceDiv);
				var inputDiv = $('<div style=\"display:inline;\"/>');
				inputDiv.prop('id', TagAttr.vectorInputDivId(parameter));
				var dimension = Number( parameter.dimension() );
				for(var i=0; i<dimension; i++){
					if( i !== 0){
						inputDiv.append(dataStructure.vectorFormDelimiter());
					}
					var input = $('<input/>');
					input.prop({
						'type' : 'text',
						'style': Style.VectorInput,
						'value': vector[i]
					});
					if( eventFlag === true ){
						var onElementChange = function (event){
							var newValue = event.data.sourceTag.val();
							var parameter = 	event.data.sourceParameter;
							var vector = parameter.value();
							var index = event.data.index;
							vector[index]=newValue;
							parameter.value(vector);
						}

						var data = {
								'sourceTag': input,
								'sourceParameter': parameter,
								'index': i
						};
						input.bind('change', data, onElementChange);
					}

					inputDiv.append(input);
				}
				div.append(inputDiv);
				var rightBraceDiv = 	$('<div style=\"display:inline;\"/>');
				rightBraceDiv.append(rightBrace);
				div.append(rightBraceDiv);

				if( eventFlag === true ){
					div.setParameterValue = function(value){
						var inputs = inputDiv.find('input');
						for(var i=0; i<inputs.length; i++){
							inputs[i].val(value[i]);
							inputs[i].trigger('change');
						}
					};
				}

				return div;
			};
			var stringParameterValueDiv = function(parameter, eventFlag){
				var div = $('<div/>');
				div.addClass(Layout.ParameterRangeDiv);
				div.prop('style', Style.StringParameterValueDiv);

				var input = $('<input/>');
				input.prop({
					'type' : 'text',
					'style': Style.StringInput,
					'value': parameter.value()
				});
				if( eventFlag === true ){
					var onInputChange = function ( event ){
						var newValue = event.data.sourceTag.val();
						//console.log('parameter value changed: ' + newValue);
						var parameter = 	event.data.sourceParameter;
						parameter.value(newValue);
						Util.refreshJSON();
					}

					var data = {
							'sourceTag': input,
							'sourceParameter': parameter
					};
					input.bind('change', data, onInputChange);
					div.setParameterValue = function(value){
						input.val(value);
						input.trigger('change');
					};
				}

				div.append(input);

				return div;
			};
			var parameterDescriptionDiv = function(parameter){
				var div = $('<div/>');
				//description
				//console.log(Layout.ParameterDescriptionDiv);
				div.addClass(Layout.ParameterDescriptionDiv);
				div.prop('style', Style.ParameterDescriptionDiv);

				//description image add
				var descriptionImg = $('<img/>').prop("src",contextPath +"/images/question.jpg")
												.prop ("width", 20).prop("height", 20).css("cursor","pointer")
												.prop("title",parameter.localizedDescription(languageId));

				div.append(descriptionImg);
				//div.append(parameter.getLocalizedDescription(languageId));
				//div.append(parameter.getNameLocalizedText(languageId));
				return div;
			};

			//This checks if a parameter is active or not from its activation conditions.
			var checkInitialActivate = function(parameter, tag){
				var checkListItem = function(){
					if(parameter.type() === OSP.Constants.LIST){
						//console.log('Check List Item Activate.... ');
						var listItems = parameter.listItems();
						//console.log(JSON.stringify(listItems));
						for( var key in listItems ){
							//console.log('Check List Item Activate: '+listItems[j].getItemValue());
							var listItem = listItems[key];
							var itemConditions = listItem.activateConditions();
							for(var k=0; k<itemConditions.length; k++){
								var itemConditionParameter = dataStructure.parameter(itemConditions[k].parameterName());
								if(itemConditions[k].checkActivate(itemConditionParameter.value()) == false){
									//console.log('ListItem Toggle: ' + 'checkInitialActivate');
									tag.setDisplayNone(listItem.value());
								}
							}
						}
					}
				};

				var conditions = parameter.activateConditions();
				if(conditions == false ){
					checkListItem();
					parameter.active(true);
					return;
				}
				//console.log(JSON.stringify(conditions));
				for(var i=0; i<conditions.length; i++){
					var conditionParameter = dataStructure.parameter(conditions[i].parameterName());
					var assignValue = conditions[i].checkActivate(conditionParameter.value());
					switch( assignValue ){
					case true:
						//console.log(parameter.getName()+': '+true);
						break;
					case false:
						//console.log(parameter.getName()+': '+false);
						break;
					default:
						//console.log(parameter.getName()+': '+assignValue);
						tag.setParameterValue(assignValue);
						tag.find('input').prop('disabled', 'disabled');
						tag.find('select').prop('disabled', 'disabled');
						break;
					}
					if( assignValue !== false ){
						//console.log('Activate: '+parameter.getName());
						//checkListItem();
						parameter.active(true);
						return;
					}
				}
				//console.log('Inactivate: '+parameter.name());
				tag.prop('style', 'display:none;');
				parameter.active(false);
			};
			var checkActivateParameters = function(parameter){
				//console.log('========== checkActivateParameters ==================');
				//console.log('Source Parameter: '+parameter.getName());
				//console.log('Source Value: '+parameter.getValue());
				var parameters = dataStructure.parameters();

				for(var paramName in parameters){
					//console.log('-------------');
					var targetParameter = parameters[paramName];
					//console.log('Target Parameter: '+targetParameter.getName());
					//console.log('Target Type: '+targetParameter.getType());
					//Check ItemLists for LIST type parameters
					if(targetParameter.type() === OSP.Constants.LIST){
						//console.log('Processing ListParameter...........');
						var listItems = targetParameter.listItems();
						//console.log(JSON.stringify(listItems));
						//console.log('List Item Count: '+listItems.length);
						for( var itemValue in listItems ){
							var targetItem = listItems[itemValue];
							//console.log('++Target Item: '+targetItem.getItemValue());
							var activate = targetItem.checkActivate(parameter.name(), parameter.value());
							//console.log('++Activate: '+activate);
							var select = $('#'+TagAttr.valueRangeInputId(targetParameter));
							//console.log('++SelectId: '+select.prop('id'));
							var option = select.find('option[value=\"'+targetItem.value()+'\"]');
							//console.log('++Option Value: ' + option.val());
							option.toggle(activate);
						}
					}

					if( targetParameter === parameter){
						//console.log('Source and Target are same: omitted');
						continue;
					}
					if(targetParameter.type() === OSP.Constants.GROUP){
						//console.log('Target parameter type is a GROUP: omitted');
						targetParameter.active(true);
						continue;
					}
					var activate = false;
					var disable = false;
					var conditions = targetParameter.activateConditions();
					if( conditions == false )	activate = true;
					else{
						for(var j=0; j<conditions.length; j++){
							var condition = conditions[j];
							var conditionParameter = dataStructure.parameter(condition.parameterName());
							var assignValue = condition.checkActivate(conditionParameter.value());
							switch( assignValue ){
							case true:
								activate= true;
								disable = false;
								break;
							case false:
								activate = false;
								break;
							default:
								activate= true;
								disable = true;
								break;
							}
							if( activate == true ){
								parameter.active(true);
								break;
							}
						}
					}
					//console.log('Activate: '+activate);
					//console.log('Disabled: '+disable);

					targetParameter.active(activate);
					if( activate == true ){
						targetParameter.disabled(disable);
						$('#'+TagAttr.parameterRowId(targetParameter)).toggle(true);
						if( disable == true ){
							$('#'+TagAttr.parameterRowId(targetParameter)).find('input').prop('disabled', 'disabled');
							$('#'+TagAttr.parameterRowId(targetParameter)).find('select').prop('disabled', 'disabled');
						}
						else{
							$('#'+TagAttr.parameterRowId(targetParameter)).find('input').removeAttr('disabled');
							$('#'+TagAttr.parameterRowId(targetParameter)).find('select').removeAttr('disabled');
						}
					}
					else{
						$('#'+TagAttr.parameterRowId(targetParameter)).toggle(false);
					}

					//console.log('-------------');
				}
			};

			var numericParameterRow = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = numericParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));

				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};
			var groupParameterRow = function( parameter, eventFlag ){
				var outerDiv = $('<div  style="text-align:right;"/>');/*class=\"span12\" 삭제*/
				outerDiv.prop('id', TagAttr.parameterRowId(parameter));
				var div = $('<div/>');
				div.prop('style', Style.ParameterRow);
				div.addClass(Layout.ParameterRow);
				//div.prop('style', Style.GroupParameterRow);
				var groupNameDiv = $('<div class=\"span6\" style=\"font-weight:bold; text-align:left; display:inline;\"/>');
				var groupName = parameter.localizedNameText(languageId);
				if( !groupName || groupName == false || groupName == '' )
					groupName = parameter.localizedNameText(dataStructure.defaultLanguageId());

				if( OSP.Util.isEmpty(groupName) )
					groupName = parameter.name();

				groupNameDiv.append("&nbsp;").append(groupName);
				div.append(groupNameDiv);
				var groupDescriptionDiv = $('<div class=\"span6\" style=\"display:inline; text-align:left; \"/>');
				groupDescriptionDiv.append(parameter.localizedDescription(languageId));
				div.append(groupDescriptionDiv);

				var attachedNames = parameter.parameters();
				if(attachedNames.length > 0){
					for(var index in attachedNames){
						var subParameter = dataStructure.parameter(attachedNames[index]);
						div.append(  parameterRow( subParameter, eventFlag) );
					}
				}else{
					div.css("padding", "5px 0px");
				}

				outerDiv.append(div)
				return outerDiv;
			};
			var listParameterRow = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = listParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));

				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					div.setDisplayNone = function(itemValue){
						valueDiv.setDisplayNone(itemValue);
					};
					div.listItemToggle = function(key, flag){
						valueDiv.listItemToggle(key, flag);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};

			var vectorParameterRow = function( parameter, eventFlag ){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = vectorParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};
			var stringParameterRow = function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				div.append(parameterNameDiv(parameter));
				var valueDiv = stringParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));
				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};
			var commentRow =  function(parameter, eventFlag){
				var div = $('<div/>');
				div.prop({
					'id': TagAttr.parameterRowId(parameter),
					'style': Style.ParameterRow
				});
				div.addClass(Layout.ParameterRow);
				var nameDiv = $('<div/>');
				nameDiv.addClass(Layout.ParameterNameDiv);
				nameDiv.prop('style', Style.ParameterNameDiv);
				nameDiv.append('Comment : ');
				div.append(nameDiv);
				var valueDiv = stringParameterValueDiv(parameter, eventFlag);
				div.append(valueDiv);
				div.append(parameterDescriptionDiv(parameter));

				if( eventFlag === true ){
					div.setParameterValue = function(value){
						valueDiv.setParameterValue(value);
					};
					checkInitialActivate(parameter, div);
				}

				return div;
			};

			var parameterRow = function( parameter, eventFlag){
				var div;
				switch(parameter.type()){
				case OSP.Constants.GROUP:
					div = groupParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.NUMERIC:
					div = numericParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.LIST:
					div = listParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.VECTOR:
					div = vectorParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.STRING:
					div = stringParameterRow(parameter, eventFlag);
					break;
				case OSP.Constants.COMMENT:
					div = commentRow(parameter, eventFlag);
					break;
				}

				var parameterClicked = function( event ){
					event.stopPropagation();
					Liferay.fire( 'parameterSelected', event.data);
				};

				div.css("cursor","pointer");
				//console.log('Numeric callback bined');
				div.on('click',
						{
							'targetPortlet': namespace,
							'sourceTag': div,
							'sourceParameter': parameter
						},
						parameterClicked
				);

				return div ;
			};

			return {
				editor : function(){

					Layout = {
							SweepCheckBoxDiv: 'span1',
							SweepMethodDiv: 'row-fluid',
							SweepRadioBySlice: 'span4',
							SweepRadioByValue: 'span4',
							SweepMethodValueDiv: 'span11',
							SweepMethodValue: 'span12',
							ParameterNameDiv: 'span3',
							ParameterRangeDiv: 'span8',
							ParameterDescriptionDiv: 'span1',
							ParameterRow: 'row-fluid parameter-row',
					};

					Style = {
							SweepCheckBoxDiv: 'padding-left:30px; display:inline;',
							SweepMethodDiv: ' border-bottom:2px solid grey;',
							SweepRadio: 'display:inline;',
							SweepRadioBySlice: 'display:inline;',
							SweepRadioByValue: 'display:inline;',
							SweepRangeLowerBoundaryDiv: 'display:inline;',
							SweepRangeLowerBoundary: 'width:40px; text-align:right;',
							SweepRangeOperandDiv: 'padding: 0px 10px; display:inline;',
							SweepRangeOperandSelect: 'width:50px; height:27px; display:inline;',
							SweepRangeDiv: 'padding:5px;',
							SweepSliceValueDiv: 'padding:5px;',
							SweepSliceValue: 'width:40px; text-align:right; display:inline;',
							SweepSliceValueUnitDiv: 'width:20px; display:inline;',
							SweepDivVisible: 'border:1px solid #d0d0d0;display:block; margin:5px auto;',
							SweepDivInvisible: 'border:1px solid #d0d0d0; padding-left:20px; display:none;',
							ParameterNameDiv:'text-align:right; display:inline;word-wrap: break-word;',
							ParameterDescriptionDiv:'text-align:center; display:inline; margin-left: 0px;',
							ValueRangeLowerBoundaryDiv: 'width:60px; text-align:right; display:inline;',
							ValueRangeUpperBoundaryDiv: 'width:60px; display:inline;',
							RangeUnitDiv:'width:20px; display:inline;',
							ValueRangeOperandDiv: 'padding: 1px 10px; display:inline;',
							ValueRangeInputDiv: 'display:inline;',
							ValueRangeInput: 'width: 60px; text-align:right; display:inline;',
							NumericParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							ListParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							GroupParameterRow:'border:3px inset #cce7ff; padding:0px 0px 0px 30px; margin:5px 0px 10px 0px;',
							ParameterRow:'padding:5px 0px;',
							VectorParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							VectorInput: 'width: 40px; text-align:right;',
							StringParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							StringInput: 'width: 70%;',
					};

					var parameters = dataStructure.topLevelParameters();
					//console.log( JSON.stringify(parameters,null,4));
					for(var index in parameters){
						if((index % 2)== 0)
							Style.ParameterRow += ' background-color:#f0f0f0;';
						else
							Style.ParameterRow += ' background-color:#f8f8f8;';

						canvas.append(parameterRow( parameters[index], true ));
					}
				},
				form : function(){
					Layout = {
							SweepCheckBoxDiv: 'span1',
							SweepMethodDiv: 'row-fluid',
							SweepRadioBySlice: 'span4',
							SweepRadioByValue: 'span4',
							SweepMethodValueDiv: 'span11',
							SweepMethodValue: 'span12',
							ParameterNameDiv: 'span3',
							ParameterRangeDiv: 'span8',
							ParameterDescriptionDiv: 'span1',
							ParameterRow: 'row-fluid parameter-row',
					};
					Style = {
							SweepCheckBoxDiv: 'padding-left:30px; display:inline;',
							SweepMethodDiv: ' border-bottom:2px solid grey;',
							SweepRadio: 'display:inline;',
							SweepRadioBySlice: 'display:inline;',
							SweepRadioByValue: 'display:inline;',
							SweepRangeLowerBoundaryDiv: 'display:inline;',
							SweepRangeLowerBoundary: 'width:40px; text-align:right;',
							SweepRangeOperandDiv: 'padding: 0px 10px; display:inline;',
							SweepRangeOperandSelect: 'width:50px; height:27px; display:inline;',
							SweepRangeDiv: 'padding:5px;',
							SweepSliceValueDiv: 'padding:5px;',
							SweepSliceValue: 'width:40px; text-align:right; display:inline;',
							SweepSliceValueUnitDiv: 'width:20px; display:inline;',
							SweepDivVisible: 'border:1px solid #d0d0d0;display:block; margin:5px auto;',
							SweepDivInvisible: 'border:1px solid #d0d0d0; padding-left:20px; display:none;',
							ParameterNameDiv:'text-align:right; display:inline;word-wrap: break-word;',
							ParameterDescriptionDiv:'text-align:center; display:inline; margin-left: 0px;',
							ValueRangeLowerBoundaryDiv: 'width:60px; text-align:right; display:inline;',
							ValueRangeUpperBoundaryDiv: 'width:60px; display:inline;',
							RangeUnitDiv:'width:20px; display:inline;',
							ValueRangeOperandDiv: 'padding: 1px 10px; display:inline;',
							ValueRangeInputDiv: 'display:inline;',
							ValueRangeInput: 'width: 60px; text-align:right; display:inline;',
							NumericParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							ListParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							GroupParameterRow:'border:3px inset #cce7ff; padding:0px 0px 0px 30px; margin:5px 0px 10px 0px;',
							ParameterRow:'padding:5px 0px;',
							VectorParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							VectorInput: 'width: 40px; text-align:right;',
							StringParameterValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							StringInput: 'width: 70%;',
					};

					var parameters = dataStructure.topLevelParameters();
					for(var index in parameters){
						if((index % 2)== 0)
							Style.ParameterRow += ' background-color:#f0f0f0;';
						else
							Style.ParameterRow += ' background-color:#f8f8f8;';

						canvas.append(parameterRow( parameters[index] ));
					}
				},

				viewer : function(){
					var formattedInputs = dataStructure.activeParameterFormattedInputs();
					//console.log("formattedInputs--->"+JSON.stringify(formattedInputs,null,4));
					var inputCount = formattedInputs.length;
					var currentViewPage = 1;
					var viewerDiv = $('<div>');
					var viewSection = $('<div class="row-fluid">');

					if(inputCount>1){
						var getPageId = function (page){
							return namespace+'_page_'+page;
						};
						var getPrevPageId = function (page){
							return namespace+'_page_'+(page-1);
						};
						var getNextPageId = function (page){
							return namespace+'_page_'+(page+1);
						};

						var previousPage = function (event){
							if( currentViewPage == 1)	return;
							var pageTag = event.data.pageTag;
							$('#'+getPageId(currentViewPage)).toggle();
							$('#'+getPrevPageId(currentViewPage)).toggle();
							currentViewPage -= 1;
							pageTag.text(currentViewPage);
						};
						var nextPage = function(event){
							if( currentViewPage ==  (inputCount)) return;
							var pageTag = event.data.pageTag;
							$('#'+getPageId(currentViewPage)).toggle();
							$('#'+getNextPageId(currentViewPage)).toggle();
							currentViewPage += 1;
							pageTag.text(currentViewPage);
						};

						var navSection = $('<div class="row-fluid">');
						var  buttonDiv = $('<div class="span4">');
						var pagerUl = $('<ul class="pager">');
						var prevLi = $('<li class="previous" style="cursor: pointer;"><span>Prev</span></li>');
						var pageLi = $('<li></li>');
						pageLi.text(currentViewPage);
						var nextLi = $('<li class="next" style="cursor: pointer;"><span>Next</span></li>');
						prevLi.find('span').bind('click', {'pageTag':pageLi}, previousPage);
						nextLi.find('span').bind('click', {'pageTag':pageLi}, nextPage);

						pagerUl.append(prevLi);
						pagerUl.append(pageLi);
						pagerUl.append(nextLi);
						buttonDiv.append(pagerUl);
						navSection.append(buttonDiv);

						viewerDiv.append(navSection);
					}


					for( var i=0; i<formattedInputs.length; i++){
						var inputDiv = $('<div>');
						inputDiv.prop('id', namespace+'_page_'+(i+1));
						if((i+1) !== currentViewPage )	inputDiv.prop('style', 'display:none');
						var input = formattedInputs[i].toString().replace(/,/g, '');
						inputDiv.html(input);
						viewSection.append(inputDiv);
					}

					viewerDiv.append(viewSection);
					canvas.append(viewerDiv);
				}
			};
		}; // End of displayUI

		DataType.showStructuredDataEditor = function( namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay ){
			var ui = displayUI( DataType.structure(), namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay );
			ui.editor();
		};

		DataType.showDataStructureForm = function(namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay){
			var ui = displayUI( DataType.structure(), namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay );
			ui.form();
		};

		DataType.showStructuredDataViewer = function(namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay){
			//console.log(JSON.stringify(DataType.structure(),null,4));
			var ui = displayUI( DataType.structure(), namespace, canvas, contextPath, languageId, jsonDisplay, jsonInputDisplay );
			ui.viewer();
		};

		DataType.deserializeStructure = function( jsonObject ){
			return DataType.structure(DataType.newDataStructure( jsonObject));
		};
		
		DataType.loadStructure = function( content ){
			var structure = DataType.newStructure();
			structure.loadData( content );
			DataType.structure( structure );
			
			return structure;
		};
		
	}; // End of DataType

})(window);