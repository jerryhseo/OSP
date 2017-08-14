(function(window){
	'use strict';
	
	if(typeof(OSPInputdeck) === 'undefined'){
        window.OSPInputdeck = {};
		window.OSPInputdeck.Constants = {
				VARIABLE_TYPE_STRING : 'string',
				VARIABLE_TYPE_NUMERIC : 'numeric',
				VARIABLE_TYPE_GROUP : 'group',
				VARIABLE_TYPE_COMMENT : 'comment',
				VARIABLE_TYPE_LIST : 'list',
				VARIABLE_TYPE_VECTOR: 'vector',
				VARIABLE_TYPE_FILE: 'file', //deprecated
				BRACE_TYPE_SQUARE:"SQUARE",
				BRACE_TYPE_SQUARE_SPACE:"SQUARE_SPACE",
				BRACE_TYPE_ROUND:"ROUND",
				BRACE_TYPE_ROUND_SPACE:"ROUND_SPACE",
				LOWER_LIMIT : 'lower-limit',
				UPPER_LIMIT : 'upper-limit',
				UNDEFINED : 'undefined',
				OPERAND : 'operand',
				LOWER_OPERAND : 'lower-operand',
				UPPER_OPERAND : 'upper-operand',
				MAP : 'map',
				VARIABLE_NAME: 'variable-name',
				LIST_ITEM_VALUE : 'list-item-value',
				DOMAIN : 'domain',
				CONTAINER : 'container',
				LOCALIZED_TEXT_MAP : 'localized-text-map',
				VALUE : 'value',
				ACTIVATE_CONDITION_CONTAINER : 'activate-condition-container',
				NAME : 'name',
				NAME_TEXT_MAP : 'name-text-map',
				TYPE : 'type',
				VALUE_DOMAIN : 'value-domain',
				LIST_MAP : 'list-map',
				SWEEP_DOMAIN : 'sweep-domain',
				ORDER : 'order',
				DESCRIPTION_MAP : 'description-map',
				DIMENSION : 'dimension',
				BRACE_CHAR : 'brace-char',
				DELIMITER : 'delimiter',
				SAMPLE : 'sample',
				SPACE : 'space',
				VALUE_DELIMITER : 'value-delimiter',
				VARIABLE_DELIMITER : 'variable-delimiter',
				COMMENT_CHAR : 'comment-char',
				CONTROL_CHAR : 'control-char',
				VECTOR_FORM : 'vector-form',
				VARIABLE_FORM : 'variable-form',
				AVAILABLE_LANGUAGE_IDS : 'available-language-ids',
				DEFAULT_LANGUAGE_ID : 'default-language-id',
				VARIABLE_MAP : 'variable-map',
				UNIT : 'unit',
				SWEEP : 'sweep',
				SWEEPED : 'sweeped',
				SWEEPABLE : 'sweepable',
				SWEEP_BY_SLICE : 'sweep-by-slice',
				SWEEP_METHOD : 'sweep-method',
				SWEEP_SLICE : 'slice',
				SWEEP_SLICE_VALUE : 'slice-value',
				SWEEP_SLICE_MAX : 'slice-max',
				SWEEP_MAX : 'sweep-max',
				SWEEP_COUNT : 'sweep-count',
				TARGET_LANGUAGE_ID : 'target-language-id',
				FILE_CONTENT:'file-content',
				ASSIGN_VALUE:'assign-value',
				STYLE:'style',
				CSS_WIDTH:'css-width',
				ACTIVE: 'active',
				DISABLE:'disable',
				MAX_DIGIT: 1e15,
				getBraceTypes : function (){
					var types = [];
					types.push(this.BRACE_TYPE_SQUARE);
					types.push(this.BRACE_TYPE_SQUARE_SPACE);
					types.push(this.BRACE_TYPE_ROUND);
					types.push(this.BRACE_TYPE_ROUND_SPACE);
					return types;
				},
				getVariableTypes : function (){
					var types = [];
					types.push(this.VARIABLE_TYPE_NUMERIC);
					types.push(this.VARIABLE_TYPE_VECTOR);
					types.push(this.VARIABLE_TYPE_STRING);
					types.push(this.VARIABLE_TYPE_LIST);
					types.push(this.VARIABLE_TYPE_GROUP);
					types.push(this.VARIABLE_TYPE_COMMENT);
					return types;
				}
			};
			window.OSPInputdeck.Errors = {
				LOWER_LIMIT_INVALID : "lower limit invalid",
				UPPER_LIMIT_INVALID : "upper limit invalid",
				OPERAND_INVALID : "operand invalid",
				TYPE_MISMATCH:"type mismatch"
			};
			window.OSPInputdeck.Util = {
				safeFloatSum : function ( x, y ){
					return ( parseFloat(x)*OSPInputdeck.Constants.MAX_DIGIT + 
								  parseFloat(y)*OSPInputdeck.Constants.MAX_DIGIT) / 
								  OSPInputdeck.Constants.MAX_DIGIT; 
				},
				safeFloatSubtract : function ( x, y ){
					return (	parseFloat(x)*OSPInputdeck.Constants.MAX_DIGIT - 
									parseFloat(y)*OSPInputdeck.Constants.MAX_DIGIT) / 
									OSPInputdeck.Constants.MAX_DIGIT; 
				},
				isInteger : function ( num ){
					return num%1 == 0;
				},
				isExponetioal : function( numStr ){
					if( numStr.search(/[eEdD]/i) == -1 )
						return false;
					else
						return true;
				},
				toFloatString : function( num, exponential ){
					if( exponential )
						return num.toExponential();
					else
						return num.toString();
				}
			};
			
			window.OSPInputdeck.Domain = function() {
				this.getLowerLimit = function() {
					return this[OSPInputdeck.Constants.LOWER_LIMIT];
				};
				this.setLowerLimit = function(limit) {
					if (isNaN(limit)){
						console.log(limit + ' is not a number!');
						return OSPInputdeck.Errors.LOWER_LIMIT_INVALID;
					}
					var upperLimit = Number(this.getUpperLimit());
					if( !upperLimit ||	upperLimit >= limit )
						this[OSPInputdeck.Constants.LOWER_LIMIT] = limit.toString();
					else	return OSPInputdeck.Errors.LOWER_LIMIT_INVALID;
					return true;
				};
				this.getUpperLimit = function() {
					return this[OSPInputdeck.Constants.UPPER_LIMIT];
				};
				this.setUpperLimit = function(limit) {
					if (isNaN(limit))		return OSPInputdeck.Errors.UPPER_LIMIT_INVALID;
					var lowerLimit = Number(this.getLowerLimit());
					if( typeof lowerLimit === OSPInputdeck.Constants.UNDEFINED || 
							lowerLimit <= limit )	
						this[OSPInputdeck.Constants.UPPER_LIMIT] = limit.toString();
					else	return OSPInputdeck.Errors.UPPER_LIMIT_INVALID;
					return true;
				};
				this.getOperand = function() {
					return this[OSPInputdeck.Constants.OPERAND];
				};
				this.setOperand = function(operand) {
					if (operand.length != 2 || !/[=<][=>]/.test(operand))
						return OSPInputdeck.Errors.OPERAND_INVALID;
					this[OSPInputdeck.Constants.OPERAND] = operand;
					return true;
				};
				this.containLowerLimit = function(flag){
					var operand = this.getOperand();
					var text;
					if( typeof operand === OSPInputdeck.Constants.UNDEFINED )
						text = [];
					else
						text = operand.split('');
					if(flag == true)
						text[0] = '=';
					else
						text[0] = '<';
					this.setOperand(text.join(''));
					return true;
				};
				this.containUpperLimit = function(flag){
					var operand = this.getOperand();
					var text;
					if( typeof operand === OSPInputdeck.Constants.UNDEFINED )
						text = [];
					else
						text = operand.split('');
					if(flag == true)
						text[1] = '=';
					else
						text[1] = '>';
					this.setOperand(text.join(''));
					return true;
				};
				this.isLowerLimitContained = function(){
					var operand = this.getOperand();
					if( operand.charAt(0) === '=')	return true;
					else if( operand.charAt(0) === '<')	return false;
					else	return void(0); 
				};
				this.isUpperLimitContained = function(){
					var operand = this.getOperand();
					if( operand.charAt(1) === '=')	return true;
					else if( operand.charAt(1) === '>')	return false;
					else	return void(0); 
				};
				this.setValues = function(lowerLimit, upperLimit, operand) {
					var result = this.setLowerLimit(lowerLimit);
					if( result !== true )	return result;
					result = this.setUpperLimit(upperLimit);
					if( result !== true )	return result;
					result = this.setOperand(operand);
					if( result !== true )	return result;
					return result;
				};
				this.isInDomain = function(valueStr){
					var lowerLimitContained = this.isLowerLimitContained();
					var upperLimitContained = this.isUpperLimitContained();
					var lowerLimit = this.getLowerLimit();
					var upperLimit = this.getUpperLimit();
					var value = Number(valueStr);
					
					if( !lowerLimit && !upperLimit )return true;
					
					if( lowerLimitContained == true && typeof upperLimit === OSPInputdeck.Constants.UNDEFINED){
						if( value >= lowerLimit )	return true;
					}
					else if ( lowerLimitContained == false && typeof upperLimit === OSPInputdeck.Constants.UNDEFINED ){
						if( value > lowerLimit )	return true;
					}
					else if ( typeof lowerLimitContained === OSPInputdeck.Constants.UNDEFINED && upperLimitContained == true){
						if( value <= upperLimit )	return true;
					}
					else if( typeof lowerLimitContained === OSPInputdeck.Constants.UNDEFINED && upperLimitContained == false){
						if( value < upperLimit )	return true;
					}
					else if(  lowerLimitContained && upperLimitContained ){
						if( value >= lowerLimit && value <= upperLimit )	
							return true;
					}
					else if( !lowerLimitContained && upperLimitContained ){
						if( value > lowerLimit && value <= upperLimit )	
							return true;
					}
					else if( lowerLimitContained && !upperLimitContained ){
						if( value >= lowerLimit && value < upperLimit )	
							return true;
					}
					else if( !lowerLimitContained && !upperLimitContained ){
						if( value > lowerLimit && value < upperLimit )	
							return true;
					}
					return false;
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						switch( property ){
						case 'line-delimiter':
							this[OSPInputdeck.Constants.VARIABLE_DELIMITER] = jsonData[property];
							break;
						default:
							this[property] = jsonData[property];	
						}
					}
				};
				this.clean = function(){
					delete this[OSPInputdeck.Constants.LOWER_LIMIT];
					delete this[OSPInputdeck.Constants.UPPER_LIMIT];
					delete this[OSPInputdeck.Constants.OPERAND];
				};
			};
			window.OSPInputdeck.createDomain = function(){
				return new OSPInputdeck.Domain();
			};
			
			window.OSPInputdeck.LocalizedTextMap = function(){
				this.getMap = function() {
					var map = this[OSPInputdeck.Constants.MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = {};
						this[OSPInputdeck.Constants.MAP] = map;
					}
					return map;
				};
				this.setMap = function(map) {
					this[OSPInputdeck.Constants.MAP] = map;
				};
				this.getLocalizedText = function(languageId) {
					var map = this.getMap();
					return map[languageId];
				};
				this.addLocalizedText = function(languageId, text) {
					var map = this.getMap();
					map[languageId] = text;
				};
				this.removeLocalizedText = function(languageId) {
					var map = this.getMap();
					delete map[languageId];
				};
				this.getLocalizedXML = function(availableLanguageIds, defaultLanguageId) {
					var xml = '<?xml version=\'1.0\' encoding=\'UTF-8\'?>';
					xml += '<root available-locales=\'';
					xml += availableLanguageIds+ '\' ';
					xml += 'default-locale=\'' + defaultLanguageId + '\'>';

					var map = this.getMap();
					var keys = Object.keys(map);
					keys.forEach(function(item, index) {
						var value = map[item];
						if( typeof value != OSPInputdeck.Constants.UNDEFINED)
							xml += '<display language-id=\'' + item + '\'>' + value
								+ '</display>';
					});
					xml += '</root>';

					return xml;
				};
				this.clean = function(){
					delete this[OSPInputdeck.Constants.MAP];
				};
				this.setData = function(jsonData){
					this.setMap(jsonData[OSPInputdeck.Constants.MAP]);
				};
			};
			window.OSPInputdeck.createLocalizedTextMap = function(){
				return new OSPInputdeck.LocalizedTextMap();
			};
			
			window.OSPInputdeck.ActivateCondition = function(){
				this.getVariableName = function() {
					return this[OSPInputdeck.Constants.VARIABLE_NAME];
				};
				this.setVariableName = function(name) {
					this[OSPInputdeck.Constants.VARIABLE_NAME] = name;
				};
				this.getType = function(){
					return this[OSPInputdeck.Constants.TYPE];
				};
				this.setType = function(type){
					this[OSPInputdeck.Constants.TYPE] = type;
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						this[property] = jsonData[property];
					}
				};
			};
			window.OSPInputdeck.createActivateCondition = function(){
				return new OSPInputdeck.ActivateCondition();
			};
			
			window.OSPInputdeck.NumericActivateCondition = function(){
				OSPInputdeck.ActivateCondition.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC);
				this.getDomain = function(){
					var domain = this[OSPInputdeck.Constants.DOMAIN];
					if( typeof domain === OSPInputdeck.Constants.UNDEFINED ){
						domain = OSPInputdeck.createDomain();
						this[OSPInputdeck.Constants.DOMAIN] = domain;
					} 
					return domain;
				};
				this.setDomain = function(domain){
					if( this.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC)
						this[OSPInputdeck.Constants.DOMAIN] = domain;
					else
						return OSPInputdeck.Errors.TYPE_MISMATCH;
				};
				this.getDomainLowerLimit = function() {
					var domain = this.getDomain();
					return domain.getLowerLimit();
				};
				this.setDomainLowerLimit = function(limit) {
					var domain = this.getDomain();
					return domain.setLowerLimit(limit);
				};
				this.getDomainUpperLimit = function() {
					var domain = this.getDomain();
					return domain.getUpperLimit();
				};
				this.setDomainUpperLimit = function(limit) {
					var domain = this.getDomain();
					return domain.setUpperLimit(limit);
				};
				this.getDomainOperand = function() {
					var domain = this.getDomain();
					return domain.getOperand();
				};
				this.setDomainOperand = function(operand) {
					var domain = this.getDomain();
					return domain.setOperand(operand);
				};
				this.containDomainLowerLimit = function(){
					var domain = this.getDomain();
					return domain.containLowerLimit();
				};
				this.containDomainUpperLimit = function(){
					var domain = this.getDomain();
					return domain.containUpperLimit();
				};
				this.isDomainLowerLimitContained = function(){
					var domain = this.getDomain();
					return domain.isLowerLimitContained();
				};
				this.isDomainUpperLimitContained = function(){
					var domain = this.getDomain();
					return domain.isUpperLimitContained();
				};
				this.isInDomain = function(value){
					var domain = this.getDomain();
					return domain.isInDomain(value);
				};
				this.setCondition = function(variableName, lower, upper, operand, assignValue) {
					var domain = this.getDomain();
					this.setVariableName(variableName);
					if( typeof assignValue !== OSPInputdeck.Constants.UNDEFINED)
						this.setAssignValue(assignValue);
					return domain.setValues(lower, upper, operand);
				};
				this.checkActivate = function(value){
					if( this.isInDomain(value) == true ){
						if( typeof this.getAssignValue() === OSPInputdeck.Constants.UNDEFINED||this.getAssignValue() === '')
							return true;
						else
							return this.getAssignValue();
					}
					else
						return false;
				};
				this.getAssignValue = function(){
					return this[OSPInputdeck.Constants.ASSIGN_VALUE];
				};
				this.setAssignValue = function(assignValue){
					this[OSPInputdeck.Constants.ASSIGN_VALUE] = assignValue.toString();
				};
				this.cleanAssignValue = function(){
					delete this[OSPInputdeck.Constants.ASSIGN_VALUE];
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						switch (property){
						case OSPInputdeck.Constants.DOMAIN:
							this.getDomain().setData(jsonData[OSPInputdeck.Constants.DOMAIN]);
							break;
						default:
							this[property] = jsonData[property];
						}
					}
				};
			};
			window.OSPInputdeck.createNumericActivateCondition = function(){
				return new OSPInputdeck.NumericActivateCondition();
			};
			
			window.OSPInputdeck.ListItemActivateCondition = function(){
				OSPInputdeck.ActivateCondition.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_LIST);
				this.getValue = function() {
					return this[OSPInputdeck.Constants.LIST_ITEM_VALUE];
				};
				this.setValue = function(value) {
					this[OSPInputdeck.Constants.LIST_ITEM_VALUE] = value.toString();
				};
				this.setCondition = function(variableName, listItemValue, assignValue) {
					this.setVariableName(variableName);
					this.setValue(listItemValue);
					if( typeof assignValue !== OSPInputdeck.Constants.UNDEFINED)
						this.setAssignValue(assignValue);
				};
				this.getAssignValue = function(){
					return this[OSPInputdeck.Constants.ASSIGN_VALUE];
				};
				this.setAssignValue = function(assignValue){
					this[OSPInputdeck.Constants.ASSIGN_VALUE] = assignValue.toString();
				};
				this.cleanAssignValue = function(){
					delete this[OSPInputdeck.Constants.ASSIGN_VALUE];
				};
				this.checkActivate = function(value){
					if(this.getValue() == value){
						if( typeof this.getAssignValue() === OSPInputdeck.Constants.UNDEFINED||this.getAssignValue() === '')
							return true;
						else
							return this.getAssignValue();
					}
					else	return false;
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						this[property] = jsonData[property];
					}
				};
			};
			window.OSPInputdeck.createListItemActivateCondition = function(){
				return new OSPInputdeck.ListItemActivateCondition();
			};
			
			window.OSPInputdeck.ActivateConditionContainer = function() {
				this.getContainer = function() {
					var container = this[OSPInputdeck.Constants.CONTAINER];
					if (typeof container === OSPInputdeck.Constants.UNDEFINED) {
						container = [];
						this[OSPInputdeck.Constants.CONTAINER] = container;
					}
					return container;
				};
				this.setContainer = function(container) {
					this[OSPInputdeck.Constants.CONTAINER] = container;
				};
				this.addActivateCondition = function(condition) {
					var container = this.getContainer();
					container.push(condition);
					return condition;
				};
				this.addListItemActivateCondition = function(variableName, listItemValue, assignValue) {
					var condition = OSPInputdeck.createListItemActivateCondition();
					condition.setCondition(variableName, listItemValue, assignValue);
					return this.addActivateCondition(condition);
				};
				this.addNumericActivateCondition = function(variableName, min, max, operand, assignValue) {
					var condition = OSPInputdeck.createNumericActivateCondition();
					var result = condition.setCondition(variableName, min, max, operand, assignValue);
					if( result != true)
						return result;
					return this.addActivateCondition(condition);
				};
				this.addGroupActivateCondition = function(variableName){
					var container = this.getContainer();
					for(var i=0; i<container.length; i++){
						if(container[i].getVariableName() === variableName)
							return null;
					}
					var condition = OSPInputdeck.createActivateCondition();
					condition.setVariableName(variableName);
					container.push(condition);
					
					return condition;
				};
				this.removeActivateConditions = function(variableName) {
					var container = this.getContainer();
					var updatedContainer = [];
					var reval = false;

					container.forEach(function(condition, index) {
						if (variableName != condition.getVariableName())
							updatedContainer.push(condition);
						else
							reval = true;
					});

					this.setContainer(updatedContainer);
					return reval;
				};
				this.getActivateConditions = function(variableName) {
					var container = this.getContainer();
					if( typeof variableName === OSPInputdeck.Constants.UNDEFINED)
						return container;
					
					var resultContainer = [];
					container.forEach(function(condition) {
						//console.log('condition: '+variableName+'-'+condition.getVariableName());
						if (variableName === condition.getVariableName())
							resultContainer.push(condition);
					});

					return resultContainer;
				};
				this.removeListItemActivateCondition = function(variableName, listItemValue) {
					var container = this.getContainer();
					var updatedContainer = [];
					var reval = false;

					container.forEach(function(condition, index) {
						if (variableName != condition.getVariableName()
								|| listItemValue != condition.getValue())
							updatedContainer.push(condition);
						else
							reval = true;
					});

					this.setContainer(updatedContainer);
					return reval;
				};
				this.removeNumericActivateCondition = function(variableName, min, max, operand) {
					var container = this.getContainer();
					var updatedContainer = [];
					var reval = false;

					container.forEach(function(condition, index) {
						if (variableName != condition.getVariableName()
								|| min != condition.getDomainLowerLimit() 
								|| max != condition.getDomainUpperLimit()
								|| operand != condition.getDomainOperand())
							updatedContainer.push(condition);
						else
							reval = true;
					});

					this.setContainer(updatedContainer);
					return reval;
				};
				this.cleanActivateConditions = function(){
					delete this[OSPInputdeck.Constants.CONTAINER];
				};
				this.checkActivate = function(variableName, value){
					if(this.getContainer().length == 0)		return true;
					
					var conditions = this.getActivateConditions(variableName);
					if(conditions.length == 0) return true
					//console.log('checkActivate: '+variableName+'-'+value);
					//console.log('Conditions: '+JSON.stringify(conditions));
					for( var i=0; i<conditions.length; i++){
						var condition = conditions[i];
						//console.log('Condition: '+JSON.stringify(condition));
						var result = condition.checkActivate(value);
						if( result !== false )	return true;
					}
					return false;
				};
				this.getActivateConditionVariableNames = function(){
					var container = this.getContainer();
					var names = [];
					for(var i=0; i<container.length; i++){
						names.push(container[i].getVariableName());
					}
					return names;
				};
				this.setData = function(jsonData){
					var dataContainer = jsonData[OSPInputdeck.Constants.CONTAINER];
					var that = this;
					if(typeof dataContainer !== OSPInputdeck.Constants.UNDEFINED) {
						dataContainer.forEach(function(conditionData, index){
							var condition;
							if( conditionData[OSPInputdeck.Constants.TYPE] === OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC){
								condition = OSPInputdeck.createNumericActivateCondition();
							}
							else
								condition = OSPInputdeck.createListItemActivateCondition();
							condition.setData(conditionData);
							that.addActivateCondition(condition);
						});
					}
				};
			};
			window.OSPInputdeck.createActivateConditionContainer = function(){
				return new OSPInputdeck.ActivateConditionContainer();	
			};
			
			window.OSPInputdeck.ListItem = function() {
				this.getLocalizedTextMap = function() {
					var map = this[OSPInputdeck.Constants.LOCALIZED_TEXT_MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = OSPInputdeck.createLocalizedTextMap();
						this[OSPInputdeck.Constants.LOCALIZED_TEXT_MAP] = map;
					}

					return map;
				};
				this.setLocalizedTextMap = function(map) {
					this[OSPInputdeck.Constants.LOCALIZED_TEXT_MAP] = map;
				};
				this.getItemValue = function() {
					return this[OSPInputdeck.Constants.VALUE];
				};
				this.setItemValue = function(value) {
					this[OSPInputdeck.Constants.VALUE] = value.toString();
				};
				this.getLocalizedText = function(languageId) {
					var map = this.getLocalizedTextMap();
					return map.getLocalizedText(languageId);
				};
				this.addLocalizedText = function(languageId, text) {
					var map = this.getLocalizedTextMap();
					map.addLocalizedText(languageId, text);
				};
				this.removeLocalizedText = function(languageId) {
					var map = this.getLocalizedTextMap();
					map.removeLocalizedText(languageId);
				};
				this.getLocalizedXML = function(availableLanguageIds, defalutLanguageId) {
					var map = this.getLocalizedTextMap();
					return map.getLocalizedXML(availableLanguageIds, defalutLanguageId);
				};
				this.cleanLocalizedTextMap = function(){
					var map = this.getLocalizedTextMap();
					map.clean();
				};
				this.getActivateConditionContainer = function() {
					var container = this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER];
					if (typeof container === OSPInputdeck.Constants.UNDEFINED) {
						container = OSPInputdeck.createActivateConditionContainer();
						this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER] = container;
					}
					return container;
				};
				this.setActivateConditionContainer = function(container) {
					this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER] = container;
				};
				this.getActivateConditions = function() {
					var container = this.getActivateConditionContainer();
					return container.getContainer();
				};
				this.setActivateConditions = function(conditions) {
					var container = this.getActivateConditionContainer();
					container.setContainer(conditions);
				};
				this.addActivateCondition = function(condition) {
					var container = this.getActivateConditionContainer();
					return container.addActivateCondition(condition);
				};
				this.addListItemActivateCondition = function(variableName, listItemValue, assignValue) {
					var container = this.getActivateConditionContainer();
					return container.addListItemActivateCondition(variableName, listItemValue, assignValue);
				};
				this.addNumericActivateCondition = function(variableName, min, max, operand, assignValue) {
					var container = this.getActivateConditionContainer();
					return container.addNumericActivateCondition(variableName, min, max, operand, assignValue);
				};
				this.removeActivateConditionsByVariableName = function(variableName) {
					var container = this.getActivateConditionContainer();
					container.removeActivateConditionsByVariableName(variableName);
				};
				this.removeListItemActivateCondition = function(variableName, listItemValue) {
					var container = this.getActivateConditionContainer();
					container.removeListItemActivateCondition(variableName, listItemValue);
				};
				this.removeNumericActivateCondition = function(variableName, min, max, operand) {
					var container = this.getActivateConditionContainer();
					container.removeNumericActivateCondition(variableName, min, max, operand);
				};
				this.checkActivate = function(variableName, value){
					var conditionContainer = this.getActivateConditionContainer();
					return conditionContainer.checkActivate(variableName, value);
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						switch (property){
						case OSPInputdeck.Constants.LOCALIZED_TEXT_MAP:
							this.getLocalizedTextMap().setData(jsonData[OSPInputdeck.Constants.LOCALIZED_TEXT_MAP]);
							break;
						case OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER:
							this.getActivateConditionContainer().setData(jsonData[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER]);
							break;
						default:
							this[property] = jsonData[property];
						}
					}
				};
			};
			window.OSPInputdeck.createListItem = function(){
				return new OSPInputdeck.ListItem();
			};
			
			window.OSPInputdeck.ListMap = function() {
				this.getListMap = function() {
					var map = this[OSPInputdeck.Constants.MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = {};
						this[OSPInputdeck.Constants.MAP] = map;
					}

					return map;
				};
				this.setListMap = function(map) {
					this[OSPInputdeck.Constants.MAP] = map;
				};
				this.getListItem = function(itemValue) {
					var map = this.getListMap();
					return map[itemValue];
				};
				this.getListItems = function(){
					var map = this.getListMap();
					var listItems = [];
					var key;
					for(key in map){
						listItems.push(map[key]);
					}
					
					return listItems;
				};
				this.pushListItem = function(itemValue, listItem){
					var map = this.getListMap();
					map[itemValue] = listItem;
				};
				this.addListItem = function(itemValue) {
					var map = this.getListMap();
					var listItem = OSPInputdeck.createListItem();
					listItem.setItemValue(itemValue);
					map[itemValue] = listItem;
					
					return listItem;
				};
				this.addLocalizedListItemText = function(itemValue, languageId, text){
					var listItem = this.getListItem(itemValue);
					if(typeof listItem === OSPInputdeck.Constants.UNDEFINED)	return false;
					listItem.addLocalizedText(languageId, text);
					return true;
				};
				this.getLocalizedListItemText = function(itemValue, languageId) {
					var listItem = this.getListItem(itemValue);
					return listItem.getLocalizedText(languageId);
				};
				this.getLocalizedListItemXML = function(itemValue, availablLanguageIds,
						defaultLanguageId) {
					var listItem = this.getListItem(itemValue);
					return listItem.getLocalizedXML(availablLanguageIds, defaultLanguageId);
				};
				this.getLocalizedListMap = function(languageId) {
					var listMap = {};
					var map = this.getListMap();
					var property;
					for( property in map){
						var listItem = map[property];
						listMap[property] = listItem.getLocalizedText(languageId);
					}
					return listMap;
				};
				this.getListXMLMap = function(availablLanguageIds, defaultLanguageId) {
					var listXML = {};
					var map = this.getListMap();
					var property;
					for( property in map){
						var listItem =map[property];
						listXML[property] = listItem.getLocalizedXML(availableLanguageIds,
								defaultLanguageId);
					}
					return listXml;
				};
				this.removeListItem = function(itemValue) {
					var map = this.getListMap();
					delete map[itemValue];
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						if( property === OSPInputdeck.Constants.MAP){
							var dataMap = jsonData[OSPInputdeck.Constants.MAP];
							var key;
							for( key in dataMap){
								var listItem = OSPInputdeck.createListItem();
								listItem.setData(dataMap[key]);
								this.pushListItem(key, listItem);
							}
						}
					}
				};
			};
			window.OSPInputdeck.createListMap = function(){
				return new OSPInputdeck.ListMap();
			};
			
			window.OSPInputdeck.Sweep = function(){
				this.getSlice = function(){
					return this[OSPInputdeck.Constants.SWEEP_SLICE];
				};
				this.setSlice = function(slice){
					this[OSPInputdeck.Constants.SWEEP_SLICE] = slice.toString();
				};
				this.getSliceValue = function(){
					return this[OSPInputdeck.Constants.SWEEP_SLICE_VALUE];
				};
				this.setSliceValue = function(value){
					this[OSPInputdeck.Constants.SWEEP_SLICE_VALUE] = value.toString();
				};
				this.getMaxSlice = function(){
					return this[OSPInputdeck.Constants.SWEEP_SLICE_MAX];
				};
				this.setMaxSlice = function(maxSlice){
					this[OSPInputdeck.Constants.SWEEP_SLICE_MAX] = maxSlice.toString();
				};
				this.getDomain = function(){
					var domain = this[OSPInputdeck.Constants.DOMAIN];
					if( typeof domain === OSPInputdeck.Constants.UNDEFINED ){
						domain = OSPInputdeck.createDomain();
						this[OSPInputdeck.Constants.DOMAIN] = domain;
					}
					return domain;
				};
				this.setDomain = function(domain){
					this[OSPInputdeck.Constants.DOMAIN];
				};
				this.getDomainLowerLimit = function() {
					var domain = this.getDomain();
					return domain.getLowerLimit();
				};
				this.setDomainLowerLimit = function(limit) {
					var domain = this.getDomain();
					return domain.setLowerLimit(limit);
				};
				this.getDomainUpperLimit = function() {
					var domain = this.getDomain();
					return domain.getUpperLimit();
				};
				this.setDomainUpperLimit = function(limit) {
					var domain = this.getDomain();
					return domain.setUpperLimit(limit);
				};
				this.getDomainOperand = function() {
					var domain = this.getDomain();
					return domain.getOperand();
				};
				this.setDomainOperand = function(operand) {
					var domain = this.getDomain();
					return domain.setOperand(operand);
				};
				this.containDomainLowerLimit = function(flag){
					var domain = this.getDomain();
					return domain.containLowerLimit(flag);
				};
				this.containDomainUpperLimit = function(flag){
					var domain = this.getDomain();
					return domain.containUpperLimit(flag);
				};
				this.isDomainLowerLimitContained = function(){
					var domain = this.getDomain();
					var domain = this.getDomain();
					if ( typeof domain === OSPInputdeck.Constants.UNDEFINED ||
						  JSON.stringify(domain) === '{}' )
						return false;
					return domain.isLowerLimitContained(); 
				};
				this.isDomainUpperLimitContained = function(){
					var domain = this.getDomain();
					var domain = this.getDomain();
					if ( typeof domain === OSPInputdeck.Constants.UNDEFINED ||
						  JSON.stringify(domain) === '{}' )
						return false;
					return domain.isUpperLimitContained(); 
				};
				this.setDomainValues = function(lowerLimit, upperLimit, operand) {
					var domain = this.getDomain();
					return domain.setValues(lowerLimit, upperLimit, operand);
				};
				this.isInDomain = function(value){
					var domain = this.getDomain();
					if ( typeof domain === OSPInputdeck.Constants.UNDEFINED ||
						  JSON.stringify(domain) === '{}' )
						return false;
					
					return domain.isInDomain(value);
				};
				
				this.getSlicedValues = function(){
					if( typeof this.getDomainLowerLimit() === OSPInputdeck.Constants.UNDEFINED || 
						 typeof this.getDomainUpperLimit() === OSPInputdeck.Constants.UNDEFINED )
						return [];
					var values = [];
					var lower = Number(this.getDomainLowerLimit());
					var isExponential = OSPInputdeck.Util.isExponetioal(this.getDomainLowerLimit());
					var upper = Number(this.getDomainUpperLimit());
					var includeLower = this.isDomainLowerLimitContained();
					var includeUpper = this.isDomainUpperLimitContained();
					var sliceValue;
					var method = this.isSweepBySlice(); 
					
					var index =0;
					var sliceCount;
					
					if( method == true ){
							sliceCount = parseInt(this.getSlice());
							//console.log('sliceCount: '+sliceCount);
					}
					else {
							sliceValue = Number(this.getSliceValue());
							sliceCount =  Math.floor(OSPInputdeck.Util.safeFloatSubtract(upper, lower)/sliceValue);
							//console.log('sliceCount: '+sliceCount);
					}

					//console.log("included: "+includeLower+','+includeUpper);
					if(includeLower == true  && includeUpper == true){
						values[index] = OSPInputdeck.Util.toFloatString(lower, isExponential);
						//console.log('sliced value['+index+']:'+lower);
						++index;
						--sliceCount;
						//console.log('sliced value['+sliceCount+']:'+upper);
					}
					else if(includeLower == true){
						values[index] = OSPInputdeck.Util.toFloatString(lower, isExponential);
						++index;
					}
					else{
						++sliceCount;	
					}
					
					if(method)
						sliceValue = OSPInputdeck.Util.safeFloatSubtract(upper, lower) / sliceCount;
					
					var value;
					(index == 0) ? ( value = OSPInputdeck.Util.safeFloatSum(lower, sliceValue)) : 
											  (value = OSPInputdeck.Util.safeFloatSum(values[index-1], sliceValue)); 
					while(value < upper){
						values[index] = OSPInputdeck.Util.toFloatString(value, isExponential);
//						alert('sliced value['+index+']:'+values[index]);
						//console.log('sliced value['+index+']:'+values[index]);
						++index;
						value = OSPInputdeck.Util.safeFloatSum(value, sliceValue);
					}
					if(includeUpper == true){
						values[index] = OSPInputdeck.Util.toFloatString(upper, isExponential);
						//console.log('sliced value['+(sliceCount-1)+']:'+upper);
					}

					return values;
				};
				this.isSweepBySlice = function(){
					if(typeof this[OSPInputdeck.Constants.SWEEP_BY_SLICE] === OSPInputdeck.Constants.UNDEFINED)
						return true;
					
					if(  this[OSPInputdeck.Constants.SWEEP_BY_SLICE] == true || 
							 this[OSPInputdeck.Constants.SWEEP_BY_SLICE] == 'true')
						return true;
					else
						return false;
				};
				this.setSweepBySlice = function(flag){
					this[OSPInputdeck.Constants.SWEEP_BY_SLICE] = flag;
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						switch(property){
						case OSPInputdeck.Constants.DOMAIN:
							this.getDomain().setData(jsonData[property])
							break;
						default:
							this[property] = jsonData[property];
						}
					}
				};
			};
			window.OSPInputdeck.createSweep = function(){
				return new OSPInputdeck.Sweep();
			};

			window.OSPInputdeck.Variable = function() {
				this.getName = function() {
					return this[OSPInputdeck.Constants.NAME];
				};
				this.setName = function(name) {
					if(this.verifyName(name) == true) 
						this[OSPInputdeck.Constants.NAME] = name;
				};
				this.getNameTextMap = function() {
					var map = this[OSPInputdeck.Constants.NAME_TEXT_MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = OSPInputdeck.createLocalizedTextMap();
						this[OSPInputdeck.Constants.NAME_TEXT_MAP] = map;
					}
					return map;
				};
				this.setNameTextMap = function(map) {
					this[OSPInputdeck.Constants.NAME_TEXT_MAP] = map;
					return true;
				};
				this.getNameLocalizedText = function(languageId) {
					var map = this.getNameTextMap();
					return map.getLocalizedText(languageId);
				};
				this.addNameLocalizedText = function(languageId, text) {
					var map = this.getNameTextMap();
					map.addLocalizedText(languageId, text);
				};
				this.removeNameLocalizedText = function(languageId) {
					var map = this.getNameTextMap();
					map.removeLocalizedText(languageId);
				};
				this.getNameLocalizedXML = function(availableLanguageIds, defaultLanguageId) {
					var map = this.getNameTextMap();
					return map.getLocalizedXML(availableLanguageIds, defaultLanguageId);
				};
				this.cleanNameText = function(){
					var map = this.getNameTextMap();
					map.clean();
				};
				this.getType = function() {
					return this[OSPInputdeck.Constants.TYPE];
				};
				this.setType = function(type) {
					this[OSPInputdeck.Constants.TYPE] = type;
				};
				this.getActivateConditionContainer = function() {
					var container = this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER];
					if (typeof container === OSPInputdeck.Constants.UNDEFINED) {
						container = OSPInputdeck.createActivateConditionContainer();
						this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER] = container;
					}

					return container;
				};
				this.setActivateConditionContainer = function(container) {
					this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER] = container;
				};
				this.addActivateCondition = function(condition) {
					var container = this.getActivateConditionContainer();
					return container.addActivateCondition(condition);
				};
				this.addListItemActivateCondition = function(variableName, listItemValue, assignValue) {
					if( typeof assignValue !== OSPInputdeck.Constants.UNDEFINED){
						if(typeof this.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC)
							if( this.isInValueDomain(assignValue) )		return null;
					}
					var container = this.getActivateConditionContainer();
					return container.addListItemActivateCondition(variableName, listItemValue, assignValue);
				};
				this.addNumericActivateCondition = function(variableName, min, max, operand, assignValue) {
					if( typeof assignValue !== OSPInputdeck.Constants.UNDEFINED){
						if(typeof this.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC)
							if( this.isInValueDomain(assignValue) )		return null;
					}
					var container = this.getActivateConditionContainer();
					return container.addNumericActivateCondition(variableName, min, max, operand, assignValue);
				};
				this.removeActivateConditions = function(variableName) {
					var container = this.getActivateConditionContainer();
					return container.removeActivateConditions(variableName);
				};
				this.removeListItemActivateCondition = function(variableName, listItemValue) {
					var container = this.getActivateConditionContainer();
					return container.removeListItemActivateCondition(variableName, listItemValue);
				};
				this.removeNumericActivateCondition = function(variableName, min, max, operand) {
					var container = this.getActivateConditionContainer();
					return container.removeNumericActivateCondition(variableName, min, max, operand);
				};
				this.getListItemActivateConditions = function(){
					var container = this.getActivateConditionContainer();
					var listItemConditionContainer = [];
					container.forEach(function(activateCondition){
						if(activateCondition.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_LIST)
							listItemConditionContainer.push(activateCondition);
					});
					return listItemConditionContainer;
				};
				this.getNumericActivateConditions = function(){
					var container = this.getActivateConditionContainer();
					var numericConditionContainer = [];
					container.forEach(function(activateCondition){
						if(activateCondition.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC)
							numericConditionContainer.push(activateCondition);
					});
					return numericConditionContainer;
				};
				this.getActivateConditions = function(){
					var container = this.getActivateConditionContainer();
					return container.getActivateConditions();					
				};
				this.cleanActivateConditions = function(){
					delete this[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER];
				};
				this.checkActivate = function(variableName, value){
					var conditionContainer = this.getActivateConditionContainer();
					return conditionContainer.checkActivate(variableName, value);
				};
				this.getOrder = function() {
					return this[OSPInputdeck.Constants.ORDER];
				};
				this.setOrder = function(order){
					this[OSPInputdeck.Constants.ORDER] = order.toString();
				};
				this.getDescriptionMap = function() {
					var map = this[OSPInputdeck.Constants.DESCRIPTION_MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = OSPInputdeck.createLocalizedTextMap();
						this[OSPInputdeck.Constants.DESCRIPTION_MAP] = map;
					}
					return map;
				};
				this.setDescriptionMap = function(map) {
					this[OSPInputdeck.Constants.DESCRIPTION_MAP] = map;
				};
				this.getLocalizedDescription = function(languageId) {
					var map = this.getDescriptionMap();
					return map.getLocalizedText(languageId);
				};
				this.addLocalizedDescription = function(languageId, text) {
					var map = this.getDescriptionMap();
					map.addLocalizedText(languageId, text);
				};
				this.removeLocalizedDescription = function(languageId) {
					var map = this.getDescriptionMap();
					map.removeLocalizedText(languageId);
				};
				this.getLocalizedDescriptionXML = function(availableLanguageIds, defaultLanguageId) {
					var map = this.getDescriptionMap();
					return map.getLocalizedXML(availableLanguageIds, defaultLanguageId);
				};
				this.isActive = function(){
					return this[OSPInputdeck.Constants.ACTIVE];
				};
				this.setActive = function(flag){
					this[OSPInputdeck.Constants.ACTIVE] = flag;
				};
				this.isDisabled = function(){
					if(typeof this[OSPInputdeck.Constants.DISABLE] === OSPInputdeck.Constants.UNDEFINED)
						return false;
					return this[OSPInputdeck.Constants.DISABLE];
				};
				this.setDisable = function(flag){
					this[OSPInputdeck.Constants.DISABLE] = flag;
				};
				this.cleanDescription = function(){
					var map = this.getDescriptionMap();
					map.clean();
				};
				this.verifyName = function(name){
					if(/[a-zA-Z\\_]/.test(name.charAt(0)) == false) return false;
					if(/[^a-zA-Z0-9\\_]/.test(name))	return false;
					return true;
				};
				this.setData = function (jsonData){
					var property;
					for( property in jsonData){
						switch(property){
						case OSPInputdeck.Constants.NAME_TEXT_MAP:
							this.getNameTextMap().setData(jsonData[OSPInputdeck.Constants.NAME_TEXT_MAP]);
							break;
						case OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER:
							this.getActivateConditionContainer().setData(jsonData[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER]);
							break;
						case OSPInputdeck.Constants.DESCRIPTION_MAP:
							this.getDescriptionMap().setData(jsonData[OSPInputdeck.Constants.DESCRIPTION_MAP]);
							break;
						default:
							this[property] = jsonData[property];	
						}
					}
				};
			};
			window.OSPInputdeck.createVariable = function(){
				return new OSPInputdeck.Variable();
			};
			
			window.OSPInputdeck.NumericVariable = function(){
				OSPInputdeck.Variable.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC);
				this.setActive(true);

				this.getUnit = function(){
					return this[OSPInputdeck.Constants.UNIT];
				};
				this.setUnit = function(unit){
					this[OSPInputdeck.Constants.UNIT] = unit;
				};
				this.getValue = function() {
					return this[OSPInputdeck.Constants.VALUE];
				};
				this.setValue = function(value) {
					if( this.isInValueDomain(value) == true){
						this[OSPInputdeck.Constants.VALUE] = value.toString();
						return true;
					}
					return false;
				};
				this.getValueDomain = function() {
					var domain = this[OSPInputdeck.Constants.VALUE_DOMAIN];
					if (typeof domain === OSPInputdeck.Constants.UNDEFINED) {
						domain = OSPInputdeck.createDomain();
						this[OSPInputdeck.Constants.VALUE_DOMAIN] = domain;
					}
					return domain;
				};
				this.setValueDomain = function(domain) {
					this[OSPInputdeck.Constants.VALUE_DOMAIN] = domain;
				};
				this.getValueDomainLowerLimit = function() {
					var domain = this.getValueDomain();
					return domain.getLowerLimit();
				};
				this.setValueDomainLowerLimit = function(limit) {
					var domain = this.getValueDomain();
					return domain.setLowerLimit(limit);
				};
				this.getValueDomainUpperLimit = function() {
					var domain = this.getValueDomain();
					return domain.getUpperLimit();
				};
				this.setValueDomainUpperLimit = function(limit) {
					var domain = this.getValueDomain();
					return domain.setUpperLimit(limit);
				};
				this.getValueDomainOperand = function() {
					var domain = this.getValueDomain();
					return domain.getOperand();
				};
				this.setValueDomainOperand = function(operand) {
					var domain = this.getValueDomain();
					return domain.setOperand(operand);
				};
				this.containValueDomainLowerLimit = function(flag){
					var domain = this.getValueDomain();
					return domain.containLowerLimit(flag);
				};
				this.containValueDomainUpperLimit = function(flag){
					var domain = this.getValueDomain();
					return domain.containUpperLimit(flag);
				};
				this.isValueDomainLowerLimitContained = function(){
					var domain = this.getValueDomain();
					return domain.isLowerLimitContained(); 
				};
				this.isValueDomainUpperLimitContained = function(){
					var domain = this.getValueDomain();
					return domain.isUpperLimitContained(); 
				};
				this.setValueDomainValues = function(lowerLimit, upperLimit, operand) {
					var domain = this.getValueDomain();
					return domain.setValues(lowerLimit, upperLimit, operand);
				};
				this.isInValueDomain = function(value){
					var domain = this.getValueDomain();
					if( typeof domain === OSPInputdeck.Constants.UNDEFINED || JSON.stringify(domain) === '{}' )
						return true;
					return domain.isInDomain(value);
				};
				this.getSweep = function(){
					var sweep = this[OSPInputdeck.Constants.SWEEP];
					if( typeof sweep === OSPInputdeck.Constants.UNDEFINED ){
						sweep = OSPInputdeck.createSweep();
						this[OSPInputdeck.Constants.SWEEP] = sweep;
					}
					return sweep;
				};
				this.setSweep = function(sweep){
					this[OSPInputdeck.Constants.SWEEP] = sweep;
				};
				this.isSweepable = function(){
					var sweepable = this[OSPInputdeck.Constants.SWEEPABLE];
					if( typeof sweepable === OSPInputdeck.Constants.UNDEFINED )
						return false;
					return sweepable;
				};
				this.setSweepable = function(flag){
					this[OSPInputdeck.Constants.SWEEPABLE] = flag;
				};
				this.getMaxSweepSlice = function(){
					var sweep = this.getSweep();
					return sweep.getMaxSlice();
				};
				this.setMaxSweepSlice = function(maxSlice){
					var sweep = this.getSweep();
					sweep.setMaxSlice(maxSlice);
				};
				this.isSweepBySlice = function(){
					var sweep = this.getSweep();
					return sweep.isSweepBySlice();
				};
				this.setSweepBySlice = function(flag){
					var sweep = this.getSweep();
					sweep.setSweepBySlice(flag);
				};
				this.getSweepSlice = function(){
					var sweep = this.getSweep();
					return sweep.getSlice();
				};
				this.setSweepSlice = function(slice){
					var sweep = this.getSweep();
					sweep.setSlice(slice) ;
					this[OSPInputdeck.Constants.SWEEPED] = true;
				};
				this.getSweepSliceValue = function(){
					var sweep = this.getSweep();
					return sweep.getSliceValue();
				};
				this.setSweepSliceValue = function(value){
					var sweep = this.getSweep();
					sweep.setSliceValue(value);
				};
				this.getSlicedValues = function(){
					var sweep = this.getSweep();
					return sweep.getSlicedValues();
				};
				this.getSweepDomainLowerLimit = function() {
					var sweep = this.getSweep();
					return sweep.getDomainLowerLimit();
				};
				this.setSweepDomainLowerLimit = function(limit) {
					var lowerLimit = this.getValueDomainLowerLimit();
					var upperLimit = this.getValueDomainUpperLimit();
					var sweep = this.getSweep();
					if( !lowerLimit && !upperLimit )
						return sweep.setDomainLowerLimit(limit);
					else if( !lowerLimit && upperLimit ){
						if( limit < upperLimit )	return sweep.setDomainLowerLimit(limit);
					}
					else if( lowerLimit && !upperLimit ){
						if( limit > lowerLimit )	return sweep.setDomainLowerLimit(limit);
					}
					else{
						if( limit > lowerLimit && limit < upperLimit )	return sweep.setDomainLowerLimit(limit);
					}
					return false;
				};
				this.getSweepDomainUpperLimit = function() {
					var sweep = this.getSweep();
					return sweep.getDomainUpperLimit();
				};
				this.setSweepDomainUpperLimit = function(limit) {
					var lowerLimit = this.getValueDomainLowerLimit();
					var upperLimit = this.getValueDomainUpperLimit();
					var sweep = this.getSweep();
					if( !lowerLimit && !upperLimit )
						return sweep.setDomainUpperLimit(limit);
					else if( !lowerLimit && upperLimit ){
						if( limit < upperLimit )	return sweep.setDomainUpperLimit(limit);
					}
					else if( lowerLimit && !upperLimit ){
						if( limit > lowerLimit )	return sweep.setDomainUpperLimit(limit);
					}
					else{
						if( limit > lowerLimit && limit < upperLimit )	return sweep.setDomainUpperLimit(limit);
					}
					return false;
				};
				this.getSweepDomainOperand = function() {
					var sweep = this.getSweep();
					return sweep.getDomainOperand();
				};
				this.setSweepDomainOperand = function(operand) {
					var sweep = this.getSweep();
					return sweep.setDomainOperand(operand);
				};
				this.containSweepDomainLowerLimit = function(flag){
					var sweep = this.getSweep();
					return sweep.containDomainLowerLimit(flag);
				};
				this.containSweepDomainUpperLimit = function(flag){
					var sweep = this.getSweep();
					return sweep.containDomainUpperLimit(flag);
				};
				this.isSweepDomainLowerLimitContained = function(){
					var sweep = this.getSweep();
					return sweep.isDomainLowerLimitContained(); 
				};
				this.isSweepDomainUpperLimitContained = function(){
					var sweep = this.getSweep();
					return sweep.isDomainUpperLimitContained(); 
				};
				this.setSweepDomainValues = function(lowerLimit, upperLimit, operand) {
					if( Number(lowerLimit) < this.getValueDomainLowerLimit() ){
						alert(OSPInputdeck.Errors.LOWER_LIMIT_INVALID);
						return OSPInputdeck.Errors.LOWER_LIMIT_INVALID;
					}
					else if( Number(upperLimit) > this.getValueDomainUpperLimit()){
						alert(OSPInputdeck.Errors.UPPER_LIMIT_INVALID);
						return OSPInputdeck.Errors.UPPER_LIMIT_INVALID;
					}
					var sweep = this.getSweep();
					return sweep.setDomainValues(lowerLimit, upperLimit, operand);
				};
				this.isInSweepDomain = function(value){
					var sweep = this.getSweep();
					return sweep.isInDomain(value);
				};
				this.removeSweep = function(){
					delete this[OSPInputdeck.Constants.SWEEPABLE];
					delete this[OSPInputdeck.Constants.SWEEPED];
					delete this[OSPInputdeck.Constants.SWEEP];
				};
				this.cleanSweep = function(){
					delete this[OSPInputdeck.Constants.SWEEPED];
					delete this[OSPInputdeck.Constants.SWEEP];
				};
				this.isSweeped = function(){
					if( typeof this[OSPInputdeck.Constants.SWEEPED] === OSPInputdeck.Constants.UNDEFINED)
						return false;
					return this[OSPInputdeck.Constants.SWEEPED];
				};
				this.setSweeped = function(flag){
					this[OSPInputdeck.Constants.SWEEPED] = flag;
				};
				this.setData = function (jsonData){
					var property;
					for( property in jsonData){
						switch(property){
						case OSPInputdeck.Constants.NAME_TEXT_MAP:
							this.getNameTextMap().setData(jsonData[OSPInputdeck.Constants.NAME_TEXT_MAP]);
							break;
						case OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER:
							this.getActivateConditionContainer().setData(jsonData[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER]);
							break;
						case OSPInputdeck.Constants.VALUE_DOMAIN:
							this.getValueDomain().setData(jsonData[OSPInputdeck.Constants.VALUE_DOMAIN]);
							break;
						case OSPInputdeck.Constants.SWEEP:
							this.getSweep().setData(jsonData[OSPInputdeck.Constants.SWEEP]);
							break;
						case OSPInputdeck.Constants.DESCRIPTION_MAP:
							this.getDescriptionMap().setData(jsonData[OSPInputdeck.Constants.DESCRIPTION_MAP]);
							break;
						default:
							this[property] = jsonData[property];	
						}
					}
				};
			};
			window.OSPInputdeck.createNumericVariable = function(){
				return new OSPInputdeck.NumericVariable();
			};
			
			window.OSPInputdeck.ListVariable = function(){
				OSPInputdeck.Variable.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_LIST);
				this.setActive(true);

				this.getValue = function() {
					return this[OSPInputdeck.Constants.VALUE];
				};
				this.setValue = function(value) {
					this[OSPInputdeck.Constants.VALUE] = value.toString();
				};
				this.getListMap = function() {
					var map = this[OSPInputdeck.Constants.LIST_MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = OSPInputdeck.createListMap();
						this[OSPInputdeck.Constants.LIST_MAP] = map;
					}
					return map;
				};
				this.setListMap = function(map) {
					this[OSPInputdeck.Constants.LIST_MAP] = map;
				};
				this.getListItem = function(itemValue) {
					var map = this.getListMap();
					return map.getListItem(itemValue);
				};
				this.pushListItem = function(itemValue, listItem) {
					var map = this.getListMap();
					map.pushListItem(itemValue, listItem);
				};
				this.addListItem = function(itemValue) {
					var map = this.getListMap();
					var listItem = map.addListItem(itemValue);
					//listItem.addLocalizedText(languageId, text);
					return listItem;
				};
				this.getListItems = function(){
					var map = this.getListMap();
					return map.getListItems();
				};
				this.addLocalizedListItemText = function(itemValue, languageId, text){
					var map = this.getListMap();
					map.addLocalizedListItemText(itemValue, languageId, text);
				};
				this.getLocalizedListItemText = function(itemValue, languageId) {
					var map = this.getListMap();
					return map.getLocalizedListItemText(itemValue, languageId);
				};
				this.getLocalizedListItemXML = function(itemValue, availablLanguageIds,
						defaultLanguageId) {
					var map = this.getListMap();
					return map.getLocalizedListItemXML(itemValue, availablLanguageIds, defaultLanguageId);
				};
				this.getLocalizedListMap = function(languageId) {
					var map = this.getListMap();
					return map.getLocalizedListMap(languageId);
				};
				this.getListXMLMap = function(availablLanguageIds, defaultLanguageId) {
					var map = this.getListMap();
					return map.getListXMLMap(availablLanguageIds, defaultLanguageId);
				};
				this.removeListItem = function(itemValue) {
					var map = this.getListMap();
					map.removeListItem(itemValue);
				};
				this.setData = function (jsonData){
					var property;
					for( property in jsonData){
						switch(property){
						case OSPInputdeck.Constants.NAME_TEXT_MAP:
							this.getNameTextMap().setData(jsonData[OSPInputdeck.Constants.NAME_TEXT_MAP]);
							break;
						case OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER:
							this.getActivateConditionContainer().setData(jsonData[OSPInputdeck.Constants.ACTIVATE_CONDITION_CONTAINER]);
							break;
						case OSPInputdeck.Constants.LIST_MAP:
							this.getListMap().setData(jsonData[OSPInputdeck.Constants.LIST_MAP]);
							break;
						case OSPInputdeck.Constants.DESCRIPTION_MAP:
							this.getDescriptionMap().setData(jsonData[OSPInputdeck.Constants.DESCRIPTION_MAP]);
							break;
						default:
							this[property] = jsonData[property];	
						}
					}
				};
			};
			window.OSPInputdeck.createListVariable = function(){
				return new OSPInputdeck.ListVariable();
			};

			window.OSPInputdeck.StringVariable = function(){
				OSPInputdeck.Variable.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_STRING);
				this.setActive(true);

				this.getValue = function() {
					return this[OSPInputdeck.Constants.VALUE];
				};
				this.setValue = function(value) {
					this[OSPInputdeck.Constants.VALUE] = value;
				};
			};
			window.OSPInputdeck.createStringVariable = function(){
				return new OSPInputdeck.StringVariable();
			};

			window.OSPInputdeck.VectorVariable = function(){
				OSPInputdeck.Variable.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR);
				this.setActive(true);

				this.getValue = function() {
					return this[OSPInputdeck.Constants.VALUE];
				};
				this.getValueString = function(startChar, endChar, delimiter, space){
					if(delimiter !== ' ')	delimiter += space;
					var string = JSON.stringify(this.getValue());
					string = string.replace('[', startChar).replace(']', endChar).replace(/,/g, delimiter).replace(/\"/gi,"");
					return string;
				};
				this.setValue = function(value) {
					this[OSPInputdeck.Constants.VALUE] = value;
				};
				this.getDimension = function() {
					return this[OSPInputdeck.Constants.DIMENSION];
				};
				this.setDimension = function(dimension) {
					this[OSPInputdeck.Constants.DIMENSION] = dimension.toString();
				};
			};
			window.OSPInputdeck.createVectorVariable = function(){
				return new OSPInputdeck.VectorVariable();
			};
			
			window.OSPInputdeck.CommentVariable = function(){
				OSPInputdeck.Variable.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT);
				this.setActive(true);
				
				this.getValue = function() {
					return this[OSPInputdeck.Constants.VALUE];
				};
				this.setValue = function(value){
					this[OSPInputdeck.Constants.VALUE] = value;
				};
			};
			window.OSPInputdeck.createCommentVariable = function(){
				return new OSPInputdeck.CommentVariable();
			};

			window.OSPInputdeck.GroupVariable = function(){
				OSPInputdeck.Variable.apply(this);
				this.setType(OSPInputdeck.Constants.VARIABLE_TYPE_GROUP);
				this.setActive(true);
				
				this.attachVariable = function(variableName){
					var container = this.getActivateConditionContainer();
					container.addGroupActivateCondition(variableName);
				};
				this.detachVariable = function(variableName){
					var container = this.getActivateConditionContainer();
					container.removeActivateConditions(variableName);
				};
				this.getAttachedVariableNames = function(){
					var container = this.getActivateConditionContainer();
					return container.getActivateConditionVariableNames();
				};
				this.hasVariable = function(variableName){
					var names = this.getAttachedVariableNames();
					for(var i=0; i<names.length; i++){
						if(names[i] === variableName)	return true;
					}
					return false;
				};
			};
			window.OSPInputdeck.createGroupVariable = function(){
				return new OSPInputdeck.GroupVariable();
			};

			window.OSPInputdeck.VariableMap = function() {
				this.getMap = function() {
					var map = this[OSPInputdeck.Constants.MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = {};
						this[OSPInputdeck.Constants.MAP] = map;
					}
					return map;
				};
				this.setMap = function(map) {
					this[OSPInputdeck.Constants.MAP] = map;
				};
				this.getVariable = function(variableName) {
					var map = this.getMap();
					return map[variableName];
				};
				this.getVariableByOrder = function(order, groupOrder){
					var map = this.getMap();
					var property;
					for(property in map){
						if( typeof groupOrder === OSPInputdeck.Constants.UNDEFINED){
							if(!this.isInGroup(property)){
								var variable = map[property];
								if( variable.getOrder() == order)	return variable;
							}
						}
						else{
							var group = this.getVariableByOrder(groupOrder);
							var names = group.getAttachedVariableNames();
							for(var i=0; i<names.length; i++){
								var variable = this.getVariable(names[i]);
								if(variable.getOrder() == order)	return variable;
							}
						}
					}
					return null;
				};
				this.addVariable = function(variable) {
					if(this.verifyVariableName(variable.getName()) == false)	return null;
					var map = this.getMap();
					map[variable.getName()] = variable;
					return variable;
				};
				this.removeVariable = function(variableName) {
					var map = this.getMap();
					var property;
					for(property in map){
						var variable = map[property];
						variable.removeActivateConditions(variableName);
					}
					delete map[variableName];
				};
				this.isInGroup = function(variableName){
					var names = this.getVariableNamesByType(OSPInputdeck.Constants.VARIABLE_TYPE_GROUP);
					//console.log(JSON.stringify(names));
					for(var i=0; i<names.length; i++){
						var group = this.getVariable( names[i] );
						if( group.hasVariable(variableName))	return true;
					}
					return false;
				};
				this.createVariable = function(variableName, type){
					if(this.verifyVariableName(variableName) == false)	return null;
					
					var map = this.getMap();
					var variable;
					switch(type){
					case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
						variable = OSPInputdeck.createNumericVariable();
						break;
					case OSPInputdeck.Constants.VARIABLE_TYPE_LIST:
						variable = OSPInputdeck.createListVariable();
						break;
					case OSPInputdeck.Constants.VARIABLE_TYPE_STRING:
						variable = OSPInputdeck.createStringVariable();
						break;
					case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
						variable = OSPInputdeck.createVectorVariable();
						break;
					case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
						variable = OSPInputdeck.createCommentVariable();
						break;
					case OSPInputdeck.Constants.VARIABLE_TYPE_GROUP:
						variable = OSPInputdeck.createGroupVariable();
						break;
					case OSPInputdeck.Constants.VARIABLE_TYPE_FILE:
					default:
						return null;
					}
					variable.setName(variableName);
					map[variableName] = variable;
					
					return variable;
				};
				this.getVariableNames = function(){
					var map = this.getMap();
					return Object.keys(map);
				};
				this.getAllVariables = function(){
					var map = this.getMap();
					var variables = [];
					var key;
					for(key in map){
						variables.push(map[key]);
					}
					
					return variables;
				};
				this.getTopLevelVariables = function(){
					var map = this.getMap();
					var variables = [];
					var variableName;
					for(variableName in map){
						if( !this.isInGroup(variableName) )
							variables.push(map[variableName]);
					}
					return variables;
				};
				this.getVariableNamesByType = function(type){
					var allNames = this.getVariableNames();
					var filteredNames = [];
					for(var i=0; i<allNames.length; i++){
						var variable =this.getVariable(allNames[i]);
						if(variable.getType() === type)
							filteredNames.push(variable.getName());
					}
					return filteredNames;
				};
				this.getVariableNamesActivateList = function(){
					var map = this.getMap();
					var names = [];
					var property;
					var existBoolean = false;
					for( property in map){
						var variable = map[property];
						if(variable.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC){
							names.push(property);
						}else if(variable.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_LIST){
							names.push(property);
						}
					}
					return names;
				};
				this.getActiveVariables = function(){
					var map = this.getMap();
					var variableName;
					var activeVariables = [];
					for(variableName in map){
						var variable = map[variableName];
						if(variable.isActive() == true)
							activeVariables.push(variable);
					}
					
					return activeVariables;
				};
				this.getSweepedVariables = function(){
					var variables = this.getActiveVariables();
					var sweepedVariables = [];
					for(var i=0; i<variables.length; i++){
						if(variables[i].isSweeped())
							sweepedVariables.push(variables[i]);
					}
					return sweepedVariables;
				}
				this.verifyVariableName = function(variableName){
					if(/[a-zA-Z\\_]/.test(variableName.charAt(0)) == false) return false;
					if(/[^a-zA-Z0-9\\_]/.test(variableName))		return false;
					
					var map = this.getMap();
					if(map.hasOwnProperty(variableName))	return false;
					
					return true;
				};
				this.setData = function(jsonData){
					var variableMapData = jsonData[OSPInputdeck.Constants.MAP];
					var key;
					for(key in variableMapData){
						//console.log('SetData: '+key);
						var type = variableMapData[key][OSPInputdeck.Constants.TYPE];
						//console.log('Variable type: '+type);
						var variable;
						switch(type){
						case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
							variable = OSPInputdeck.createCommentVariable();
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_GROUP:
							variable = OSPInputdeck.createGroupVariable();
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_LIST:
							variable = OSPInputdeck.createListVariable();
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
							variable = OSPInputdeck.createNumericVariable();
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_STRING:
							variable = OSPInputdeck.createStringVariable();
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
							variable = OSPInputdeck.createVectorVariable();
							break;
						}
						variable.setData(variableMapData[key]);
						this.addVariable(variable);
					}
				};
			};
			window.OSPInputdeck.createVariableMap = function(){
				return new OSPInputdeck.VariableMap();
			};

			window.OSPInputdeck.VectorForm = function() {
				this.setValues = function(braceChar, delimiter, sample) {
					this[OSPInputdeck.Constants.BRACE_CHAR] = braceChar;
					this[OSPInputdeck.Constants.DELIMITER] = delimiter;
					this[OSPInputdeck.Constants.SAMPLE] = sample;
				};
				this.getBraceChar = function() {
					return this[OSPInputdeck.Constants.BRACE_CHAR];
				};
				this.setBraceChar = function(braceChar) {
					this[OSPInputdeck.Constants.BRACE_CHAR] = braceChar;
				};
				this.getDelimiter = function() {
					return this[OSPInputdeck.Constants.DELIMITER];
				};
				this.setDelimiter = function(delimiter) {
					this[OSPInputdeck.Constants.DELIMITER] = delimiter;
				};
				this.getSpace = function() {
					return this[OSPInputdeck.Constants.SPACE];
				};
				this.setSpace = function(space) {
					this[OSPInputdeck.Constants.SPACE] = space;
				};
				this.getSample = function() {
					return this[OSPInputdeck.Constants.SAMPLE];
				};
				this.setSample = function(sample) {
					this[OSPInputdeck.Constants.SAMPLE] = sample;
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						this[property] = jsonData[property];
					}
				};
			};
			window.OSPInputdeck.createVectorForm = function(){
				return new OSPInputdeck.VectorForm();
			};

			window.OSPInputdeck.VariableForm = function() {
				this.setValues = function(valueDelimiter, variableDelimiter, commentChar, controlChar) {
					this[OSPInputdeck.Constants.VALUE_DELIMITER] = valueDelimiter;
					this[OSPInputdeck.Constants.VARIABLE_DELIMITER] = variableDelimiter;
					if( typeof commentChar !== OSPInputdeck.Constants.UNDEFINED)
						this[OSPInputdeck.Constants.COMMENT_CHAR] = commentChar;
					if( typeof controlChar !== OSPInputdeck.Constants.UNDEFINED)
						this[OSPInputdeck.Constants.CONTROL_CHAR] = controlChar;
				};
				this.getValueDelimiter = function() {
					return this[OSPInputdeck.Constants.VALUE_DELIMITER];
				};
				this.setValueDelimiter = function(delimiter) {
					this[OSPInputdeck.Constants.VALUE_DELIMITER] = delimiter;
				};
				this.getVariableDelimiter = function() {
					return this[OSPInputdeck.Constants.VARIABLE_DELIMITER];
				};
				this.setVariableDelimiter = function(delimiter) {
					this[OSPInputdeck.Constants.VARIABLE_DELIMITER] = delimiter;
				};
				this.getCommentChar = function() {
					return this[OSPInputdeck.Constants.COMMENT_CHAR];
				};
				this.setCommentChar = function(commentChar) {
					this[OSPInputdeck.Constants.COMMENT_CHAR] = commentChar;
				};
				this.isSpace = function() {
					var space = this[OSPInputdeck.Constants.SPACE];
					if( typeof space == OSPInputdeck.Constants.UNDEFINED )	return false;
					return space; 
				};
				this.setSpace = function(space) {
					this[OSPInputdeck.Constants.SPACE] = space;
				};
				this.getControlChar = function(){
					return [OSPInputdeck.Constants.CONTROL_CHAR];
				};
				this.setControlChar = function(controlChar){
					this[OSPInputdeck.Constants.CONTROL_CHAR] = controlChar;
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData){
						switch( property ){
						case 'line-delimiter':
							this[OSPInputdeck.Constants.VARIABLE_DELIMITER] = jsonData[property];
							break;
						default:
							this[property] = jsonData[property];	
						}
					}
				};
			};
			window.OSPInputdeck.createVariableForm = function(){
				return new OSPInputdeck.VariableForm();
			};

			window.OSPInputdeck.InputdeckForm = function() {
				this.getVectorForm = function() {
					var vectorForm = this[OSPInputdeck.Constants.VECTOR_FORM];
					if (typeof vectorForm === OSPInputdeck.Constants.UNDEFINED) {
						vectorForm = OSPInputdeck.createVectorForm();
						this[OSPInputdeck.Constants.VECTOR_FORM] = vectorForm;
					}
					return vectorForm;
				};
				this.setVectorForm = function(vectorForm) {
					this[OSPInputdeck.Constants.VECTOR_FORM] = vectorForm;
				};
				this.setVectorFormValues = function(braceChar, delimiter, sample) {
					var vectorForm = this.getVectorForm();
					vectorForm.setValues(braceChar, delimiter, sample);
				};
				this.getVectorFormBraceChar = function() {
					var vectorForm = this.getVectorForm();
					return vectorForm.getBraceChar();
				};
				this.setVectorFormBraceChar = function(braceChar) {
					var vectorForm = this.getVectorForm();
					vectorForm.setBraceChar(braceChar);
				};
				this.getVectorFormDelimiter = function() {
					var vectorForm = this.getVectorForm();
					return vectorForm.getDelimiter();
				};
				this.setVectorFormDelimiter = function(delimiter) {
					var vectorForm = this.getVectorForm();
					vectorForm.setDelimiter(delimiter);
				};
				this.getVectorFormSpace = function() {
					var vectorForm = this.getVectorForm();
					return vectorForm.getSpace();
				};
				this.setVectorFormSpace = function(space) {
					var vectorForm = this.getVectorForm();
					vectorForm.setSpace(space);
				};
				this.getVectorFormSample = function() {
					var vectorForm = this.getVectorForm();
					return vectorForm.getSample();
				};
				this.setVectorFormSample = function(sample) {
					var vectorForm = this.getVectorForm();
					vectorForm.setSample(sample);
				};
				this.getVariableForm = function() {
					var variableForm = this[OSPInputdeck.Constants.VARIABLE_FORM];
					if (typeof variableForm === OSPInputdeck.Constants.UNDEFINED) {
						variableForm = OSPInputdeck.createVariableForm();
						this[OSPInputdeck.Constants.VARIABLE_FORM] = variableForm;
					}

					return variableForm;
				};
				this.setVariableForm = function(variableForm) {
					this[OSPInputdeck.Constants.VARIABLE_FORM] = variableForm;
				};
				this.setVariableFormValues = function(valueDelimiter, variableDelimiter, commentChar, controlChar) {
					var variableForm = this.getVariableForm();
					variableForm.setValues(valueDelimiter, variableDelimiter, commentChar, controlChar);
				};
				this.getVariableFormValueDelimiter = function() {
					var form = this.getVariableForm();
					return form.getValueDelimiter();
				};
				this.setVariableFormValueDelimiter = function(delimiter) {
					var form = this.getVariableForm();
					form.setValueDelimiter(delimiter);
				};
				this.getVariableFormVariableDelimiter = function() {
					var form = this.getVariableForm();
					return form.getVariableDelimiter();
				};
				this.setVariableFormVariableDelimiter = function(delimiter) {
					var form = this.getVariableForm();
					form.setVariableDelimiter(delimiter);
				};
				this.getVariableFormCommentChar = function() {
					var form = this.getVariableForm();
					return form.getCommentChar();
				};
				this.setVariableFormCommentChar = function(commentChar) {
					var form = this.getVariableForm();
					form.setCommentChar(commentChar);
				};
				this.getVariableFormCotrolChar = function() {
					var form = this.getVariableForm();
					return form.getControlChar();
				};
				this.setVariableFormControlChar = function(controlChar) {
					var form = this.getVariableForm();
					form.setControlChar(controlChar);
				};
				this.isVariableFormSpace = function() {
					var form = this.getVariableForm();
					return form.isSpace(); 
				};
				this.setVariableFormSpace = function(space) {
					var form = this.getVariableForm();
					form.setSpace(space);
				};
				this.getAvailableLanguageIds = function() {
					var array = this[OSPInputdeck.Constants.AVAILABLE_LANGUAGE_IDS];
					if( typeof array == OSPInputdeck.Constants.UNDEFINED){
						array = [];
						this[OSPInputdeck.Constants.AVAILABLE_LANGUAGE_IDS] = array;
					}
					return array;
				};
				this.setAvailableLanguageIds = function(languageIds) {
					this[OSPInputdeck.Constants.AVAILABLE_LANGUAGE_IDS] = languageIds;
				};
				this.getDefaultLanguageId = function() {
					return this[OSPInputdeck.Constants.DEFAULT_LANGUAGE_ID];
				};
				this.setDefaultLanguageId = function(languageId) {
					this[OSPInputdeck.Constants.DEFAULT_LANGUAGE_ID] = languageId;
				};
				this.getVariableMap = function() {
					var map = this[OSPInputdeck.Constants.VARIABLE_MAP];
					if (typeof map === OSPInputdeck.Constants.UNDEFINED) {
						map = OSPInputdeck.createVariableMap();
						this[OSPInputdeck.Constants.VARIABLE_MAP] = map;
					}
					return map;
				};
				this.setVariableMap = function(map) {
					this[OSPInputdeck.Constants.VARIABLE_MAP] = map;
				};
				this.getVariable = function(variableName) {
					var map = this.getVariableMap();
					return map.getVariable(variableName);
				};
				this.getVariableByOrder= function(order, groupOrder){
					var map = this.getVariableMap();
					return map.getVariableByOrder(order, groupOrder);
				};
				this.getTopLevelVariables = function(){
					var map = this.getVariableMap();
					return map.getTopLevelVariables(); 
				};
				this.getAllVariables = function(){
					var map = this.getVariableMap();
					return map.getAllVariables();
				};
				this.getActiveVariables = function(){
					var map = this.getVariableMap();
					return map.getActiveVariables();
				};
				this.addVariable = function(variable) {
					var map = this.getVariableMap();
					map.addVariable(variable);;
				};
				this.removeVariable = function(variableName) {
					var map = this.getVariableMap();
					map.removeVariable(variableName);
				};
				this.createVariable = function(variableName, type){
					var map = this.getVariableMap();
					return map.createVariable(variableName, type);
				};
				this.getSweepMax = function(){
					return this[OSPInputdeck.Constants.SWEEP_MAX];
				};
				this.setSweepMax = function(max){
					this[OSPInputdeck.Constants.SWEEP_MAX] = max;
				};
				this.getSweepCount = function(){
					var count = this[OSPInputdeck.Constants.SWEEP_COUNT];
					if(typeof count === OSPInputdeck.Constants.UNDEFINED){
						count = 0;
						this.setSweepCount(count);
					}
					return count;
				};
				this.setSweepCount = function(count){
					this[OSPInputdeck.Constants.SWEEP_COUNT] = count;
				};
				this.increaseSweepCount = function(){
					var count = this.getSweepCount();
					this.setSweepCount(++count);
				};
				this.decreaseSweepCount = function(){
					var count = this.getSweepCount();
					this.setSweepCount(count>0 ? --count : 0);
				};
				this.getSweepedVariables = function(){
					var map = this.getVariableMap();
					var sweepedVariables = map.getSweepedVariables();
					if( sweepedVariables.length == 0)	return null;
					return sweepedVariables;
				};
				this.isSweeped = function(){
					if(this.getSweepCount() == 0)	return false;
					else		return true;
				};
				this.verifyName = function(name){
					var map = this.getVariableMap();
					return map.verifyVariableName(name);
				};
				this.cloneVariable = function(variable, count){
					var jsonData = JSON.stringify(variable);
					//console.log(JSON.stringify(variable));
					var prefix = variable.getName();
					var index;
					var clones = [];
					for(index=0; index<count; index++){
						var name = prefix + "_" + (index+1);
						var emptyIndex = index+1;
						
						while(!this.verifyName(name)){
							name = prefix + "_" + emptyIndex;
							emptyIndex++;
						}
						
						var clone = this.createVariable(name,variable.getType());
						var data = JSON.parse(jsonData);
						clone.setData(data);
						if( clone.verifyName(name) ){
							clone.setName(name);
							clone.cleanActivateConditions();
							clones.push(clone);
							//console.log(JSON.stringify(clone));
						}
					}
					
					//console.log("index: "+index);
					if( index == count ){
						for( index = 0; index < count; index++ ){
							this.addVariable(clones[index]);
						}
					}
					else
						return null;
					
					return clones;
				};
				this.cloneVariableGroup = function(groupVariable, count){
					if( groupVariable.getType() != OSPInputdeck.Constants.VARIABLE_TYPE_GROUP )
						return null;
					
					var newGroups = this.cloneVariable(groupVariable, count);
					if( newGroups == null )	return null;
					
					var that = this;

					newGroups.forEach(function(group, i){
						group.cleanActivateConditions();
						var orgConditionContainer = groupVariable.getActivateConditionContainer().getContainer();
						orgConditionContainer.forEach(function(condition, j){
							var orgVarName = condition.getVariableName();
							var orgVar = that.getVariable(orgVarName);
							var varClones = that.cloneVariable(orgVar, 1);
							var clone = varClones[0];
							clone.setName(orgVarName+"_"+(i+1));
							group.attachVariable(clone.getName());
							that.addVariable(clone);
						});
					});
					
					return newGroups;
				};
				this.getVariableNames = function(){
					var map = this.getVariableMap();
					return map.getVariableNames();
				};
				this.getVariableNamesByType = function(type){
					var map = this.getVariableMap();
					return map.getVariableNamesByType(type);
				};
				this.getVariableNamesActivateList = function(){
					var map = this.getVariableMap();
					return map.getVariableNamesActivateList();
				};
				this.setData = function(jsonData){
					var property;
					for( property in jsonData ){
						switch (property){
						case OSPInputdeck.Constants.VECTOR_FORM:
							this.getVectorForm().setData(jsonData[property]);
							break;
						case OSPInputdeck.Constants.VARIABLE_FORM:
							this.getVariableForm().setData(jsonData[property]);
							break;
						case 'line-form': //after finishing the migration, this case should be removed...
							this.getVariableForm().setData(jsonData[property]);
							break;
						case OSPInputdeck.Constants.VARIABLE_MAP:
							this.getVariableMap().setData(jsonData[property]);
							break;
						default:
							this[property] = jsonData[property];
						}
					}
				};
				this.getFileContent = function(){
					return this[OSPInputdeck.Constants.FILE_CONTENT];
				};
				this.setFileContent = function (content){
					this[OSPInputdeck.Constants.FILE_CONTENT] = content;
				};
				this.clone = function(){
					var strData = JSON.stringify(this);
					var clone = OSPInputdeck.createForm();
					clone.setData(JSON.parse(strData));
					return clone;
				};
				this.getActiveVariableFormattedInputs = function(){
					var variableDelimiter = this.getVariableFormVariableDelimiter();
					var valueDelimiter = this.getVariableFormValueDelimiter();
					var variableSpaceChar;
					this.isVariableFormSpace() ? variableSpaceChar = ' ': variableSpaceChar = '';
					var inputdeck = this;
					
					var getFormattedValue = function(variable){
						var formattedString = [];
						switch(variable.getType()){
						case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
							if( variable.isSweeped()){
								var sweepValues = variable.getSlicedValues();
								
								for(var i=0; i<sweepValues.length; i++){
									formattedString[i] =  
										variable.getName() + variableSpaceChar + valueDelimiter + 
										variableSpaceChar + sweepValues[i] + variableSpaceChar + variableDelimiter;
								}
							}
							else
								formattedString[0] = 
									variable.getName() + variableSpaceChar + valueDelimiter + 
									variableSpaceChar + variable.getValue() + variableSpaceChar + variableDelimiter;
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_LIST:
						case OSPInputdeck.Constants.VARIABLE_TYPE_STRING:
							formattedString[0] = 
								variable.getName() + variableSpaceChar + valueDelimiter + 
								variableSpaceChar + variable.getValue() + variableSpaceChar + variableDelimiter;
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
							formattedString[0] = inputdeck.getVariableFormCommentChar() +
								variable.getValue() + variableSpaceChar + variableDelimiter;
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
							var startBraceChar, endBraceChar; 
							var vectorSpace;
							switch(inputdeck.getVectorFormBraceChar()){
							case OSPInputdeck.Constants.BRACE_TYPE_ROUND:
								startBraceChar = '(';
								endBraceChar = ')';
								vectorSpace = '';
								break;
							case OSPInputdeck.Constants.BRACE_TYPE_ROUND_SPACE:
								startBraceChar = '( ';
								endBraceChar = ' )';
								vectorSpace = ' ';
								break;
							case OSPInputdeck.Constants.BRACE_TYPE_SQUARE:
								startBraceChar = '[';
								endBraceChar = ']';
								vectorSpace = '';
								break;
							case OSPInputdeck.Constants.BRACE_TYPE_SQUARE_SPACE:
								startBraceChar = '[ ';
								endBraceChar = ' ]';
								vectorSpace = ' ';
								break;
							}
							
							formattedString[0] = 
								variable.getName() + variableSpaceChar + valueDelimiter + variableSpaceChar +
								variable.getValueString(
										startBraceChar,
										endBraceChar,
										inputdeck.getVectorFormDelimiter(),
										vectorSpace) + variableSpaceChar + variableDelimiter; 
							break;
						default:
							break;
						}
						//console.log(formattedString);
						return formattedString;
					};

					var getFormattedInputs = function(input, variables){
						var inputs = [];
						for(var i=0; i<variables.length; i++){
							var variable = variables[i];
							var values = getFormattedValue(variable);
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
									var newInputs = getFormattedInputs(cloneInput, variables.slice(i+1));
									
									sweepInputs = sweepInputs.concat(newInputs);
								}
								return sweepInputs;
							}
						}
						inputdeck.setFileContent(input.join("\n")+"\n");
						inputs.push(inputdeck.clone());
						return inputs;
					};

					var activeVariables = this.getActiveVariables();
					var formattedInputs = getFormattedInputs([], activeVariables);
					//console.log(JSON.stringify(formattedInputs, null, 4));
					return formattedInputs;
				};
				
				this.setAllVariablesInactive = function(){
					var variables = this.getActiveVariables();
					for(var i=0; i<variables.length; i++){
						var variable = variables[i];
						switch(variable.getType()){
						case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
						case OSPInputdeck.Constants.VARIABLE_TYPE_GROUP:
							break;
						default:
							variable.setActive(false);
						}
					}
				};
				this.loadInput = function(input){
					var valueDelimiter = this.getVariableFormValueDelimiter();
					var variableDelimiter = this.getVariableFormVariableDelimiter();
					var commentChar = this.getVariableFormCommentChar();
					if(typeof variableDelimiter === OSPInputdeck.Constants.UNDEFINED)
						variableDelimiter = '\n';
					var lines =input.split(variableDelimiter);
					this.setAllVariablesInactive();
					for(var i=0; i<lines.length; i++){
						var line = lines[i].trim().split(valueDelimiter);
						//console.log(line);
						var variableName = line[0].trim();
						if( variableName.length == 0) continue;
						var variableValue;
						if ( variableName.charAt(0) == commentChar  )
							variableValue = variableName.slice(1);
						else {
							variableValue = line[1].trim();
							var variable = this.getVariable(variableName);
							//console.log('variable value: '+variable.getType());
							switch(variable.getType()){
							case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
								variable.setValue(JSON.parse(variableValue));
								break;
							case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
								if(variable.isSweeped() == true){
									variable.setSweeped(false);
									this.decreaseSweepCount();
								}
								//alert(variableName + ': '+variable.isSweeped());
							default:
								variable.setValue(variableValue);
								//console.log('value: '+variableValue);
							}
								
							variable.setActive(true);
						}
					}
				};
			};
			window.OSPInputdeck.createForm = function(){
				return new OSPInputdeck.InputdeckForm();
			};
			
			window.OSPInputdeck.Editor = function(inputdeck, namespace, canvas, languageId, jsonDisplay, jsonInputDisplay){
				canvas.empty();
				
				var Layout;
				var Style;
				
				var Util = {
					refreshJSON : function(){
						if( typeof jsonDisplay === OSPInputdeck.Constants.UNDEFINED)	return;
						jsonDisplay.empty();
						jsonDisplay.html(JSON.stringify(inputdeck, null, 4));
						if( typeof jsonInputDisplay === OSPInputdeck.Constants.UNDEFINED)	return;
						jsonInputDisplay.empty();
						jsonInputDisplay.html(JSON.stringify(inputdeck.getActiveVariableFormattedInputs(), null, 4));
					},
					convertUndefinedToEmptyString : function(value){
						if(typeof value === OSPInputdeck.Constants.UNDEFINED)
							return '';
						else
							return value;
					},
					isFormView: function(flag){
						if( typeof flag === OSPInputdeck.Constants.UNDEFINED || flag == false )
							return false;
						else return true;
					}
				};

				var TagAttr = {
					getValueDomainInputId : function(variable){
						return namespace+'_'+variable.getName()+'_domain';
					},
					getValueDomainInputName : function(variable){
						return namespace+'_'+variable.getName()+'_domain';
					},
					getSweepCheckBoxId : function(variable){
						return namespace+'_'+variable.getName()+'_sweepCheckBox';
					},
					getSweepMethodRadioBySliceId: function(variable){
						return namespace+'_'+variable.getName()+'_sweepMethodBySlice';
					},
					getSweepMethodRadioName: function(variable){
						return namespace+'_'+variable.getName()+'_sweepMethod';
					},
					getSweepMethodRadioByValueId: function(variable){
						return namespace+'_'+variable.getName()+'_sweepMethodByValue';
					},
					getSweepDomainLowerLimitId : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_lowerLimit';
					},
					getSweepDomainUpperLimitId : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_upperLimit';
					},
					getSweepDomainLowerOperandId : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_lowerOperand';
					},
					getSweepDomainUpperOperandId : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_upperOperand';
					},
					getSweepSliceValueId : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_sliceValue';
					},
					getSweepSliceValueName : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_sliceValue';
					},
					getSweepSliceValueUnitId : function(variable){
						return namespace+'_'+variable.getName()+'_sweep_sliceValueUnit';
					},
					getVectorInputDivId : function(variable){
						return namespace+'_'+variable.getName()+'_vectorInputDiv';
					},
					getVariableRowId : function(variable){
						return namespace+'_'+variable.getName();
					},
					getInputPageId: function(page){
						return namespace+'_page_'+page;
					}
				};
				var Event = {
					onVariableValueChange : function(event){
						var newValue = event.data.sourceTag.val();
						//console.log('variable value changed: ' + newValue);
						var variable = 	event.data.sourceVariable;
						switch(variable.getType()){
						case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
							var vector = variable.getValue();
							var index = event.data.index;
							vector[index]=newValue;
							variable.setValue(vector);
							break;
						default:
							if( variable.setValue(newValue) == false ){
								alert(newValue+' is out of the domain.');
								event.data.sourceTag.val(variable.getValue());
							}
						}
						checkActivateVariables(variable);
						Util.refreshJSON();
					},
					onSweepChange : function(event){
						var newValue = event.data.sourceTag.val();
						var section = event.data.section;
						var variable = 	event.data.sourceVariable;
						switch(section){
						case OSPInputdeck.Constants.LOWER_LIMIT:
							console.log('setSweepDomainLowerLimit: '+variable.setSweepDomainLowerLimit(newValue));
							if(variable.setSweepDomainLowerLimit(newValue) == false){
								alert(newValue+' is out of the domain.');
								event.data.sourceTag.val(variable.getSweepDomainLowerLimit());
							}
							//console.log(JSON.stringify(variable.getSweep(), null, 4));
							break;
						case OSPInputdeck.Constants.UPPER_LIMIT:
							//console.log('setSweepDomainLowerLimit: '+variable.setSweepDomainLowerLimit(newValue));
							if(variable.setSweepDomainUpperLimit(newValue) == false){
								alert(newValue+' is out of the domain.');
								event.data.sourceTag.val(variable.getSweepDomainUpperLimit());
							}
							//console.log(JSON.stringify(variable.getSweep(), null, 4));
							break;
						case OSPInputdeck.Constants.LOWER_OPERAND:
						//console.log('lower operand changed: '+newValue);
						if( newValue === '=')
								variable.containSweepDomainLowerLimit(true);
							else
								variable.containSweepDomainLowerLimit(false);
							break;
						case OSPInputdeck.Constants.UPPER_OPERAND:
							//console.log('upper operand changed: '+newValue);
							if( newValue === '=')
								variable.containSweepDomainUpperLimit(true);
							else
								variable.containSweepDomainUpperLimit(false);
							break;
						case OSPInputdeck.Constants.SWEEP_SLICE_VALUE:
							var maxSlice = Number(variable.getMaxSweepSlice());
							var isSweepBySlice = variable.isSweepBySlice();
							//console.log('isSweepBySlice: '+ isSweepBySlice);
							if( isSweepBySlice == true ){
								if(newValue > maxSlice || newValue < 2){
									alert('Slice should be between 2 and ' + maxSlice + '.');
									event.data.sourceTag.val(variable.getSweepSlice());
								}
								else
									variable.setSweepSlice(newValue);
							}
							else{
								var slices = OSPInputdeck.Util.safeFloatSubtract(variable.getSweepDomainUpperLimit(), 
																											variable.getSweepDomainLowerLimit()) / newValue;
								if( slices > maxSlice || slices < 2){
									alert('Slice should be between 2 and ' + maxSlice + ' by slice value.');
									event.data.sourceTag.val(variable.getSweepSliceValue());
								}
								else
									variable.setSweepSliceValue(newValue);
								//console.log(JSON.stringify(variable.getSweep(), null, 4));
							}
							break;
						default:
						}
						Util.refreshJSON();
					},
					onSweepDivToggle : function(event){
						var origin = event.data.origin;
						var target = event.data.target;
						var variable = event.data.sourceVariable;
						if( variable.isSweeped() ){
							variable.setSweeped(false);
							inputdeck.getSweepCount() > 0 ? inputdeck.decreaseSweepCount() : 0;
							target.toggle();
						}
						else{
							if( inputdeck.getSweepMax() == inputdeck.getSweepCount() ){
								origin.removeAttr('checked');
							}
							else{
								inputdeck.increaseSweepCount();
								variable.setSweeped(true);
								target.toggle();
							}
						} 
						Util.refreshJSON();
					},
					onSweepMethodChange : function(event){
						var sweepMethodDiv = event.data.sweepMethodDiv;
						var sweepDomainDiv = event.data.sweepDomainDiv;
						var variable = event.data.variable;
						var method = sweepMethodDiv.find('input:checked').val();
						//console.log(method);
						if(method === 'slice'){
							variable.setSweepBySlice(true);
							sweepDomainDiv.setSweepSliceValue(
									Util.convertUndefinedToEmptyString(variable.getSweepSlice()));
							sweepDomainDiv.setSweepSliceValueUnit('Slices');
						}
						else{
							variable.setSweepBySlice(false);
							sweepDomainDiv.setSweepSliceValue(
									Util.convertUndefinedToEmptyString(variable.getSweepSliceValue()));
							sweepDomainDiv.setSweepSliceValueUnit(
									Util.convertUndefinedToEmptyString(variable.getUnit()));
						}
						Util.refreshJSON();
					}
				};

				var getValueDomainLimitDiv = function(variable, place, formFlag){
					var limit;
					var div = $('<div/>');
					if( place === OSPInputdeck.Constants.LOWER_LIMIT){
						limit = variable.getValueDomainLowerLimit();
						div.attr('style', Style.ValueDomainLowerLimitDiv);
					}
					else{
						limit = variable.getValueDomainUpperLimit();
						div.attr('style', Style.ValueDomainUpperLimitDiv);
					}
					div.append(limit);
					
					return div;
				};
				var getDomainUnitDiv = function(variable, formFlag){
					var unit = variable.getUnit();
					if( typeof unit === OSPInputdeck.Constants.UNDEFINED)
						unit = '';
					
					var div = $('<div/>');
					div.attr('style', Style.DomainUnitDiv);
					div.append(unit);
					
					return div;
				};
				var getValueDomainOperandDiv = function(variable, place, formFlag){
					var div = $('<div/>');
					div.attr('style', Style.ValueDomainOperandDiv);
					var operand;
					if( place === OSPInputdeck.Constants.LOWER_LIMIT){
						if( variable.isValueDomainLowerLimitContained())
							operand = '&le;';
						else
							operand = '<';
					}
					else {
						if( variable.isValueDomainUpperLimitContained())
							operand = '&le;';
						else
							operand = '<';
					}
					div.append(operand);
					
					return div;
				};
				var getValueDomainInputDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.attr('style', Style.ValueDomainInputDiv);
					
					var input = $('<input>');
					input.attr({
						'type':'text',
						'id' : TagAttr.getValueDomainInputId(variable),
						'name': TagAttr.getValueDomainInputName(variable),
						'style': Style.ValueDomainInput,
						'value': variable.getValue()
					});
					
					if( !Util.isFormView( formFlag )){
						//console.log('onChange Handler binded...');
						
						input.bind('change', 
								{
									'sourceTag': input,
									'sourceVariable':variable
								},
								Event.onVariableValueChange
						);
		
						div.setVariableValue = function(value){
							input.attr('value', value);
							input.trigger( "change" );
						};
					}
					
					div.append(input);
					
					return div;
				};
				var getSweepCheckBoxDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.attr('style', Style.SweepCheckBoxDiv);
					//div.addClass(Layout.SweepCheckBoxDiv);
					var label = $('<label/>');
					label.attr('style', Style.SweepRadio);
					var checkBox = $('<input type=\"checkbox\" >Sweep</input>');
					if(variable.isSweeped())	checkBox.attr('checked', 'checked');
					var id = TagAttr.getSweepCheckBoxId(variable);
					checkBox.attr('id', id);
					
					
					label.append(checkBox);
					div.append(label);
					
					return div;
				};
				var getSweepMethodDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.SweepMethodValue);
					div.addClass(Layout.SweepMethodDiv);
					div.attr('style', Style.SweepMethodDiv);
					
					var radioDiv = $('<div/>');
					radioDiv.addClass(Layout.SweepRadioBySlice);
					
					var label = $("<label/>");
					var radioBySlice = $('<input type=\"radio\" value=\"slice\">By Slice</input>');
					//radioBySlice.addClass(Layout.SweepRadioBySlice);
					radioBySlice.attr({
						'id': TagAttr.getSweepMethodRadioBySliceId(variable),
						'name': TagAttr.getSweepMethodRadioName(variable),
						'style': Style.SweepRadioBySlice
					});
					label.append(radioBySlice)
					radioDiv.append(label);
					div.append(radioDiv);
					
					radioDiv = $('<div/>');
					radioDiv.addClass(Layout.SweepRadioByValue);
					label = $("<label/>");
					
					var radioByValue = $('<input type=\"radio\" value=\"value\">By Value</input>');
					//radioByValue.addClass(Layout.SweepRadioByValue);
					radioByValue.attr({
						'id': TagAttr.getSweepMethodRadioByValueId(variable),
						'name': TagAttr.getSweepMethodRadioName(variable),
						'style': Style.SweepRadioByValue
					});
					
					if(variable.isSweepBySlice() == true || variable.isSweepBySlice() === 'true' )
						radioBySlice.attr('checked', 'checked');
					else
						radioByValue.attr('checked', 'checked');
					
					label.append(radioByValue)
					radioDiv.append(label);
					div.append(radioDiv);
					
					return div;
				};
				
				var getSweepDomainLimitDiv = function(variable, place, formFlag){
					var limit;
					var div = $('<div/>');
					div.attr('style', Style.SweepDomainLowerLimitDiv);
					var input = $('<input/>');
					var inputId;
					if( place === OSPInputdeck.Constants.LOWER_LIMIT){
						limit = variable.getSweepDomainLowerLimit();
						inputId = TagAttr.getSweepDomainLowerLimitId(variable);
					}
					else{
						limit = variable.getSweepDomainUpperLimit();
						inputId = TagAttr.getSweepDomainUpperLimitId(variable);
					}
					var attrs = {
						'type' : 'text',
						'id' : inputId,
						'value' : limit,
						'style' : Style.SweepDomainLowerLimit
					};
					input.attr(attrs);
					input.bind('change', 
							{
								'sourceTag': input,
								'sourceVariable':variable,
								'section' : place
							},
							Event.onSweepChange
					);
					div.append(input);
					
					return div;
				};
				var getSweepDomainOperandDiv = function(variable, place, formFlag){
					var div = $('<div/>'); 
					div.attr('style', Style.SweepDomainOperandDiv);
					var select = $('<select/>');
					select.attr('style', Style.SweepDomainOperandSelect);
					var selectId;
					var optionE, optionT;
					
					if(place === OSPInputdeck.Constants.LOWER_OPERAND){
						selectId = TagAttr.getSweepDomainLowerOperandId(variable);
						optionE = $('<option value=\"=\">&le;</option>');
						optionT = $('<option value=\"&lt;\">&lt;</option>');
						//console.log('isSweepDomainLowerLimitContained :' + variable.isSweepDomainLowerLimitContained());
						if(variable.isSweepDomainLowerLimitContained() == true)
							optionE.attr('selected', 'selected');
						else
							optionT.attr('selected', 'selected');
					}
					else{
						//console.log('isSweepDomainUpperLimitContained :' + variable.isSweepDomainUpperLimitContained());
						selectId = TagAttr.getSweepDomainUpperOperandId(variable);
						optionE = $('<option value=\"=\">&le;</option>');
						optionT = $('<option value=\"&gt;\">&lt;</option>');
						if(variable.isSweepDomainUpperLimitContained() == true)
							optionE.attr('selected', 'selected');
						else
							optionT.attr('selected', 'selected');
					}

					select.append(optionE);
					select.append(optionT);
					select.bind('change', 
							{
								'sourceTag': select,
								'sourceVariable':variable,
								'section' : place
							},
							Event.onSweepChange
					);

					div.append(select);
					return div;
				};
				var getSweepSliceValueDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.attr('style', Style.SweepSliceValueDiv);
					var input = $('<input>');
					input.attr({
						'type':'text',
						'id' : TagAttr.getSweepSliceValueId(variable),
						'name': TagAttr.getSweepSliceValueName(variable),
						'style': Style.SweepSliceValue
					});
					var value;
					if(variable.isSweepBySlice() == true  ){
						value = Util.convertUndefinedToEmptyString(variable.getSweepSlice());
					}else{
						value = Util.convertUndefinedToEmptyString(variable.getSweepSliceValue());
					}
					input.val(value);
					input.bind('change', 
									{
										'sourceTag': input,
										'sourceVariable':variable,
										'section': OSPInputdeck.Constants.SWEEP_SLICE_VALUE
									},
									Event.onSweepChange
					);
					
					div.append(input);
					div.append(getSweepSliceValueUnitDiv(variable));
					
					return div;
				};
				var getSweepSliceValueUnitDiv = function (variable){
					var div = $('<div/>');
					div.attr({
						'id': TagAttr.getSweepSliceValueUnitId(variable),
						'style': Style.SweepSliceValueUnitDiv
					});
					if( variable.isSweepBySlice() == true)
						div.append('Slices');
					else{
						if (typeof variable.getUnit() !== OSPInputdeck.Constants.UNDEFINED)
							div.append(variable.getUnit());
					}
					return div;
				};

				var getSweepDomainDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.SweepMethodValue);
					div.addClass(Layout.SweepMethodDiv);
					div.attr('style', Style.SweepDomainDiv);
					
					div.append(getSweepDomainLimitDiv(variable, OSPInputdeck.Constants.LOWER_LIMIT));
					div.append(getDomainUnitDiv(variable));
					div.append(getSweepDomainOperandDiv(variable, OSPInputdeck.Constants.LOWER_OPERAND));
					div.append($('<div style=\"display:inline;\">x</div>'));
					div.append(getSweepDomainOperandDiv(variable, OSPInputdeck.Constants.UPPER_OPERAND));
					div.append(getSweepDomainLimitDiv(variable, OSPInputdeck.Constants.UPPER_LIMIT));
					div.append(getDomainUnitDiv(variable));
					
					var sweepSliceValueDiv = getSweepSliceValueDiv(variable);
					div.append(sweepSliceValueDiv);
					div.setSweepSliceValue = function(value){
						var input = sweepSliceValueDiv.find('input');
						input.val(value);
					};
					div.setSweepSliceValueUnit = function(unit){
						var unitDiv = $('#'+TagAttr.getSweepSliceValueUnitId(variable));
						unitDiv.html(unit);
					};
					
					return div;
				};

				var getSweepDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.SweepMethodValueDiv);
					
					if(variable.isSweeped()){
						div.attr('style', Style.SweepDivVisible);
					}else{
						if( Util.isFormView( formFlag )){
							div.attr('style', Style.SweepDivVisible);
						}else{
						    div.attr('style', Style.SweepDivInvisible);
						}
					} 
						
					
					var sweepDomainDiv = getSweepDomainDiv(variable);
					var sweepMethodDiv = getSweepMethodDiv(variable);					
					//console.log('Is form view? : ' + Util.isFormView( formFlag ));
					if( !Util.isFormView( formFlag )){
						var data = {
								'sweepMethodDiv': sweepMethodDiv,
								'sweepDomainDiv': sweepDomainDiv,
								'variable': variable
						};
						sweepMethodDiv.bind('change', data, Event.onSweepMethodChange);
					}
					
					div.append(sweepMethodDiv);
					div.append(sweepDomainDiv);
					
					return div;
				};
				
				var getVariableNameDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.VariableNameDiv);
					div.attr('style', Style.VariableNameDiv);
					div.append(variable.getNameLocalizedText(languageId)+' : ');
					return div;
				};
				var getNumericVariableValueDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.VariableDomainDiv);
					div.attr('style', Style.NumericVariableValueDiv);
					var domain = variable.getValueDomain();
					var inputDiv = getValueDomainInputDiv(variable, formFlag);
					if( typeof domain === OSPInputdeck.Constants.UNDEFINED || JSON.stringify(domain) === '{}'){
						div.append(inputDiv);
						div.append(getDomainUnitDiv(variable));
					}
					else {
						var lowerLimit = variable.getValueDomainLowerLimit();
						if( typeof lowerLimit !== OSPInputdeck.Constants.UNDEFINED){
							div.append(getValueDomainLimitDiv(variable, OSPInputdeck.Constants.LOWER_LIMIT));
							div.append(getDomainUnitDiv(variable));
							div.append(getValueDomainOperandDiv(variable, OSPInputdeck.Constants.LOWER_OPERAND));
						}
						var inputDiv = getValueDomainInputDiv(variable, formFlag);
						div.append(inputDiv);
						div.append(getDomainUnitDiv(variable));
						
						var upperLimit = variable.getValueDomainUpperLimit();
						if( typeof upperLimit !== OSPInputdeck.Constants.UNDEFINED){
							div.append(getValueDomainOperandDiv(variable, OSPInputdeck.Constants.UPPER_OPERAND));
							div.append(getValueDomainLimitDiv(variable, OSPInputdeck.Constants.UPPER_LIMIT));
							div.append(getDomainUnitDiv(variable));
						}
					}
					
					if( variable.isSweepable()){
						var sweepDiv = getSweepDiv(variable, formFlag);
						var checkBoxDiv = getSweepCheckBoxDiv(variable);
						var checkBox = checkBoxDiv.find('input');

						if( !Util.isFormView( formFlag )){
							var data = {
									'origin': checkBox,
									'target': sweepDiv,
									'sourceVariable': variable
							};
							checkBox.bind('change', data, Event.onSweepDivToggle);
						}
						div.append(checkBoxDiv);
						div.append(sweepDiv);
					}
					

					if( !Util.isFormView( formFlag )){
						div.setVariableValue = function(value){
							inputDiv.setVariableValue(value);
						};
					}

					return div;
				};
				var getListVariableValueDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.VariableDomainDiv);
					div.attr('style', Style.ListVariableValueDiv);
					var select = $('<select/>');
					var id = TagAttr.getValueDomainInputId(variable);
					var name = id;
					select.attr({
						'id' : id,
						'name' : name
					});
					var listMap = variable.getLocalizedListMap(languageId);
					//console.log(JSON.stringify(listMap));
					var key;
					var savedValue = variable.getValue();
					for(key in listMap){
						var option = $('<option/>');
						option.val(key);
						if( savedValue == key )
							option.attr('selected', 'selected');
						option.text(listMap[key]);
						select.append(option);
					}
					
					if( !Util.isFormView( formFlag )){
						var data = {
								'sourceTag': select,
								'sourceVariable': variable
						};
						select.bind('change', data, Event.onVariableValueChange);
						div.setVariableValue = function(value){
							select.val(value);
							select.trigger('change');
						};
						div.setDisplayNone = function(itemValue){
							//console.log('Display None: ' + 'getListVariableValueDiv');
							var option = select.find('option[value=\"'+itemValue+'\"]');
							option.attr('style', 'display:none');
						};
						div.listItemToggle = function(itemValue, flag){
							//console.log('ListItem Toggle: ' + 'getListVariableValueDiv');
							var option = select.find('option[value=\"'+itemValue+'\"]');
							option.toggle(flag);
						}
					}

					div.append(select);
					return div;
				};
				var getVectorVariableValueDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.VariableDomainDiv);
					div.attr('style', Style.VectorVariableValueDiv);
					var leftBrace, rightBrace;
					switch(inputdeck.getVectorFormBraceChar()){
					case OSPInputdeck.Constants.BRACE_TYPE_SQUARE_SPACE:
					case OSPInputdeck.Constants.BRACE_TYPE_SQUARE:
						leftBrace = '[ ';
						rightBrace = ' ]';
						break;
					case OSPInputdeck.Constants.BRACE_TYPE_ROUND_SPACE:
					case OSPInputdeck.Constants.BRACE_TYPE_ROUND:
						leftBrace = '( ';
						rightBrace = ' )';
						break;
					}
					var vector = variable.getValue();
					var leftBraceDiv = $('<div style=\"display:inline;\"/>');
					leftBraceDiv.append(leftBrace);
					div.append(leftBraceDiv);
					var inputDiv = $('<div style=\"display:inline;\"/>');
					inputDiv.attr('id', TagAttr.getVectorInputDivId(variable));
					var dimension = Number( variable.getDimension() );
					for(var i=0; i<dimension; i++){
						if( i !== 0){
							inputDiv.append(inputdeck.getVectorFormDelimiter());
						}
						var input = $('<input/>');
						input.attr({
							'type' : 'text',
							'style': Style.VectorInput,
							'value': vector[i]
						});
						if( !Util.isFormView( formFlag ) ){
							var data = {
									'sourceTag': input,
									'sourceVariable': variable,
									'index': i
							};
							input.bind('change', data, Event.onVariableValueChange);
						}
						
						inputDiv.append(input);
					}
					div.append(inputDiv);
					var rightBraceDiv = 	$('<div style=\"display:inline;\"/>');
					rightBraceDiv.append(rightBrace);
					div.append(rightBraceDiv);
					
					if( !Util.isFormView( formFlag ) ){
						div.setVariableValue = function(value){
							var inputs = inputDiv.find('input');
							for(var i=0; i<inputs.length; i++){
								inputs[i].val(value[i]);
								inputs[i].trigger('change');
							}
						};
					}

					return div;
				};
				var getStringVariableValueDiv = function(variable, formFlag){
					var div = $('<div/>');
					div.addClass(Layout.VariableDomainDiv);
					div.attr('style', Style.StringVariableValueDiv);
					
					var input = $('<input/>');
					input.attr({
						'type' : 'text',
						'style': Style.StringInput,
						'value': variable.getValue()
					});
					if( !Util.isFormView( formFlag )){
						var data = {
								'sourceTag': input,
								'sourceVariable': variable
						};
						input.bind('change', data, Event.onVariableValueChange);
						div.setVariableValue = function(value){
							input.val(value);
							input.trigger('change');
						};					
					}
					
					div.append(input);
					
					return div;
				};
				var getVariableDescriptionDiv = function(variable, formFlag){
					var div = $('<div/>');
					//description 
					console.log(Layout.VariableDescriptionDiv);
					div.addClass(Layout.VariableDescriptionDiv);
					div.attr('style', Style.VariableDescriptionDiv);
					
					//description image add
					var descriptionImg = $('<img/>').attr("src",contextPath +"/images/btn_question.jpg")
													.attr ("width", 20).attr("height", 20).css("cursor","pointer")
													.attr("title",variable.getLocalizedDescription(languageId));
					
					div.append(descriptionImg);
					//div.append(variable.getLocalizedDescription(languageId));
					//div.append(variable.getNameLocalizedText(languageId));
					return div;
				};
				
				//This checks if a variable is active or not from its activation conditions. 
				var checkInitialActivate = function(variable, tag){
					var checkListItem = function(){
						if(variable.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_LIST){
							//console.log('Check List Item Activate.... ');	
							var listItems = variable.getListItems();
							//console.log(JSON.stringify(listItems));
							for(var j=0; j<listItems.length; j++){
								//console.log('Check List Item Activate: '+listItems[j].getItemValue());
								var itemConditions = listItems[j].getActivateConditions();
								for(var k=0; k<itemConditions.length; k++){
									var itemConditionVariable = inputdeck.getVariable(itemConditions[k].getVariableName());
									if(itemConditions[k].checkActivate(itemConditionVariable.getValue()) == false){
										//console.log('ListItem Toggle: ' + 'checkInitialActivate');
										tag.setDisplayNone(listItems[j].getItemValue());
									}
								}
							}
						}
					};
					
					var conditions = variable.getActivateConditions();
					if(conditions.length == 0){
						checkListItem();
						variable.setActive(true);
						return;
					}
					//console.log(JSON.stringify(conditions));
					for(var i=0; i<conditions.length; i++){
						var conditionVariable = inputdeck.getVariable(conditions[i].getVariableName());
						var assignValue = conditions[i].checkActivate(conditionVariable.getValue()); 
						switch( assignValue ){
						case true:
							//console.log(variable.getName()+': '+true);
							break;
						case false:
							//console.log(variable.getName()+': '+false);
							break;
						default:
							//console.log(variable.getName()+': '+assignValue);
							tag.setVariableValue(assignValue);
							tag.find('input').attr('disabled', 'disabled');
							tag.find('select').attr('disabled', 'disabled');
							break;
						}
						if( assignValue !== false ){
							//console.log('Activate: '+variable.getName());
							//checkListItem();
							variable.setActive(true);
							return;
						}
					}
					//console.log('Inactivate: '+variable.getName());
					tag.attr('style', 'display:none;');
					variable.setActive(false);
				};
				var checkActivateVariables = function(variable){
					//console.log('========== checkActivateVariables ==================');
					//console.log('Source Variable: '+variable.getName());
					//console.log('Source Value: '+variable.getValue());
					var variables = inputdeck.getAllVariables();
					
					for(var i=0; i<variables.length; i++){
						//console.log('-------------');
						var targetVariable = variables[i];
						//console.log('Target Variable: '+targetVariable.getName());
						//console.log('Target Type: '+targetVariable.getType());
						//Check ItemLists for LIST type variables
						if(targetVariable.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_LIST){
							//console.log('Processing ListVariable...........');
							var listItems = targetVariable.getListItems();
							//console.log(JSON.stringify(listItems));
							//console.log('List Item Count: '+listItems.length);
							for(var j=0; j<listItems.length; j++){
								var targetItem = listItems[j];
								//console.log('++Target Item: '+targetItem.getItemValue());
								var conditionContainer = targetItem.getActivateConditionContainer();
								var activate = conditionContainer.checkActivate(variable.getName(), variable.getValue());
								//console.log('++Activate: '+activate);
								var select = $('#'+TagAttr.getValueDomainInputId(targetVariable));
								//console.log('++SelectId: '+select.attr('id'));
								var option = select.find('option[value=\"'+targetItem.getItemValue()+'\"]');
								//console.log('++Option Value: ' + option.val());
								option.toggle(activate);
							}					
						}

						if( targetVariable === variable){
							//console.log('Source and Target are same: omitted');
							continue;
						}
						if(targetVariable.getType() === OSPInputdeck.Constants.VARIABLE_TYPE_GROUP){
							//console.log('Target variable type is a GROUP: omitted');
							targetVariable.setActive(true);
							continue;
						}
						var activate = false;
						var disable = false;
						var conditions = targetVariable.getActivateConditions();
						if(conditions.length == 0)	activate = true;
						else{
							for(var j=0; j<conditions.length; j++){
								var condition = conditions[j];
								var conditionVariable = inputdeck.getVariable(condition.getVariableName());
								var assignValue = condition.checkActivate(conditionVariable.getValue()); 
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
									variable.setActive(true);
									break;
								}
							}
						}
						//console.log('Activate: '+activate);
						//console.log('Disabled: '+disable);

						targetVariable.setActive(activate);
						if( activate == true ){
							targetVariable.setDisable(disable);
							$('#'+TagAttr.getVariableRowId(targetVariable)).toggle(true);
							if( disable == true ){
								$('#'+TagAttr.getVariableRowId(targetVariable)).find('input').attr('disabled', 'disabled');
								$('#'+TagAttr.getVariableRowId(targetVariable)).find('select').attr('disabled', 'disabled');
							}
							else{
								$('#'+TagAttr.getVariableRowId(targetVariable)).find('input').removeAttr('disabled');
								$('#'+TagAttr.getVariableRowId(targetVariable)).find('select').removeAttr('disabled');
							}
						}
						else{
							$('#'+TagAttr.getVariableRowId(targetVariable)).toggle(false);
						}
						
						//console.log('-------------');
					}
				};
				var getNumericVariableRow = function(variable, formFlag, callback){
					var div = $('<div/>');
					div.attr({
						'id': TagAttr.getVariableRowId(variable),
						'style': Style.VariableRow
					});
					div.addClass(Layout.VariableRow);
					div.append(getVariableNameDiv(variable));
					var valueDiv = getNumericVariableValueDiv(variable, formFlag);
					div.append(valueDiv);
					div.append(getVariableDescriptionDiv(variable));
					
					if( !Util.isFormView( formFlag ) ){
						div.setVariableValue = function(value){
							valueDiv.setVariableValue(value);
						};
						checkInitialActivate(variable, div);
					}
					else{
						div.css("cursor","pointer");
						//console.log('Numeric callback bined');
						div.on('click',
								{
									'sourceTag': div,
									'sourceVariable': variable
								},
								callback
						);
						div.find('input').attr('disabled', 'disabled');
						div.find('select').attr('disabled', 'disabled');
					}

					return div;
				};
				var getGroupVariableRow = function(variable, formFlag, callback){
					var outerDiv = $('<div  style="text-align:right;"/>');/*class=\"span12\" 삭제*/
					outerDiv.attr('id', TagAttr.getVariableRowId(variable));
					var div = $('<div/>');
					div.attr('style', Style.VariableRow);
					div.addClass(Layout.VariableRow);
					//div.attr('style', Style.GroupVariableRow);
					var groupNameDiv = $('<div class=\"span6\" style=\"font-weight:bold; text-align:left; display:inline;\"/>');
					groupNameDiv.append("&nbsp;").append(variable.getNameLocalizedText(languageId));
					div.append(groupNameDiv);
					var groupDescriptionDiv = $('<div class=\"span6\" style=\"display:inline; text-align:left; \"/>');
					groupDescriptionDiv.append(variable.getLocalizedDescription(languageId));
					div.append(groupDescriptionDiv);

					var attachedNames = variable.getAttachedVariableNames();
					if(attachedNames.length > 0){
						for(var i=0; i<attachedNames.length; i++){
							var subVariable = inputdeck.getVariable(attachedNames[i]);
							switch(subVariable.getType()){
							case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
								div.append(getNumericVariableRow(subVariable, formFlag, callback));
								break;
							case OSPInputdeck.Constants.VARIABLE_TYPE_LIST:
								div.append(getListVariableRow(subVariable, formFlag, callback));
								break;
							case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
								div.append(getVectorVariableRow(subVariable, formFlag, callback));
								break;
							case OSPInputdeck.Constants.VARIABLE_TYPE_STRING:
								div.append(getStringVariableRow(subVariable, formFlag, callback));
								break;
							case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
								div.append(getCommentRow(subVariable, formFlag, callback));
								break;
							}
						}
					}else{
						div.css("padding", "5px 0px");
					}
					
					//console.log('Group variable row event bind.....isFormView: '+Util.isFormView(formFlag));
					if( Util.isFormView( formFlag ) ){
						//console.log('Group variable row event binded.....formFlag: '+formFlag);
						div.css("cursor","pointer");
						div.bind('click',
								{
									'sourceTag': div,
									'sourceVariable': variable
								}, 
								callback
						);
					}
					outerDiv.append(div)
					return outerDiv;
				};
				var getListVariableRow = function(variable, formFlag, callback){
					var div = $('<div/>');
					div.attr({
						'id': TagAttr.getVariableRowId(variable),
						'style': Style.VariableRow
					});
					div.addClass(Layout.VariableRow);
					div.append(getVariableNameDiv(variable));
					var valueDiv = getListVariableValueDiv(variable, formFlag);
					div.append(valueDiv);
					div.append(getVariableDescriptionDiv(variable));
					
					if( !Util.isFormView( formFlag ) ){
						div.setVariableValue = function(value){
							valueDiv.setVariableValue(value);
						};
						div.setDisplayNone = function(itemValue){
							valueDiv.setDisplayNone(itemValue);
						};
						div.listItemToggle = function(key, flag){
							valueDiv.listItemToggle(key, flag);
						};
						checkInitialActivate(variable, div);
					}
					else{
						div.css("cursor","pointer");
						div.bind('click',
								{
									'sourceTag': div,
									'sourceVariable': variable
								}, 
								callback
						);
					}
					
					return div;
				};
				var getVectorVariableRow = function(variable, formFlag, callback){
					var div = $('<div/>');
					div.attr({
						'id': TagAttr.getVariableRowId(variable),
						'style': Style.VariableRow
					});
					div.addClass(Layout.VariableRow);
					div.append(getVariableNameDiv(variable));
					var valueDiv = getVectorVariableValueDiv(variable, formFlag);
					div.append(valueDiv);
					div.append(getVariableDescriptionDiv(variable));
					if( !Util.isFormView( formFlag ) ){
						div.setVariableValue = function(value){
							valueDiv.setVariableValue(value);
						};
						checkInitialActivate(variable, div);
					}
					else {
						div.css("cursor","pointer");
						div.bind('click',
								{
									'sourceTag': div,
									'sourceVariable': variable
								}, 
								callback
						);
						div.find('input').attr('disabled', 'disabled');
					}

					return div;
				};
				var getStringVariableRow = function(variable, formFlag, callback){
					var div = $('<div/>');
					div.attr({
						'id': TagAttr.getVariableRowId(variable),
						'style': Style.VariableRow
					});
					div.addClass(Layout.VariableRow);
					div.append(getVariableNameDiv(variable));
					var valueDiv = getStringVariableValueDiv(variable, formFlag);
					div.append(valueDiv);
					div.append(getVariableDescriptionDiv(variable));
					if( !Util.isFormView( formFlag ) ){
						div.setVariableValue = function(value){
							valueDiv.setVariableValue(value);
						};
						checkInitialActivate(variable, div);
					}
					else {
						div.css("cursor","pointer");
						div.bind('click',
								{
									'sourceTag': div,
									'sourceVariable': variable
								}, 
								callback
						);
						div.find('input').attr('disabled', 'disabled');
					}

					return div;
				};
				var getCommentRow =  function(variable, formFlag, callback){
					var div = $('<div/>');
					div.attr({
						'id': TagAttr.getVariableRowId(variable),
						'style': Style.VariableRow
					});
					div.addClass(Layout.VariableRow);
					var nameDiv = $('<div/>');
					nameDiv.addClass(Layout.VariableNameDiv);
					nameDiv.attr('style', Style.VariableNameDiv);
					nameDiv.append('Comment : ');
					div.append(nameDiv);
					var valueDiv = getStringVariableValueDiv(variable, formFlag); 
					div.append(valueDiv);
					div.append(getVariableDescriptionDiv(variable));

					if( !Util.isFormView( formFlag ) ){
						div.setVariableValue = function(value){
							valueDiv.setVariableValue(value);
						};
						checkInitialActivate(variable, div);
					}
					else {
						div.bind('click',
								{
									'sourceTag': div,
									'sourceVariable': variable
								}, 
								callback
						);
						div.find('input').attr('disabled', 'disabled');
					}
						
					return div;
				};
			
				this.showEditor = function(){
					Layout = {
							SweepCheckBoxDiv: 'span1',
							SweepMethodDiv: 'row',
							SweepRadioBySlice: 'span4',
							SweepRadioByValue: 'span4',
							SweepMethodValueDiv: 'span11',
							SweepMethodValue: 'span12',
							VariableNameDiv: 'span3',
							VariableDomainDiv: 'span8',
							VariableDescriptionDiv: 'span1',
							VariableRow: 'row variable-row',
					};
					
					Style = {
							SweepCheckBoxDiv: 'padding-left:30px; display:inline;',
							SweepMethodDiv: ' border-bottom:2px solid grey;',
							SweepRadio: 'display:inline;',
							SweepRadioBySlice: 'display:inline;',
							SweepRadioByValue: 'display:inline;',
							SweepDomainLowerLimitDiv: 'display:inline;',
							SweepDomainLowerLimit: 'width:40px; text-align:right;',
							SweepDomainOperandDiv: 'padding: 0px 10px; display:inline;',
							SweepDomainOperandSelect: 'width:50px; height:27px; display:inline;',
							SweepDomainDiv: 'padding:5px;',
							SweepSliceValueDiv: 'padding:5px;',
							SweepSliceValue: 'width:40px; text-align:right; display:inline;',
							SweepSliceValueUnitDiv: 'width:20px; display:inline;',
							SweepDivVisible: 'border:1px solid #d0d0d0;display:block; margin:5px auto;',
							SweepDivInvisible: 'border:1px solid #d0d0d0; padding-left:20px; display:none;',
							VariableNameDiv:'text-align:right; display:inline;word-wrap: break-word;',
							VariableDescriptionDiv:'text-align:center; display:inline; margin-left: 0px;',
							ValueDomainLowerLimitDiv: 'width:60px; text-align:right; display:inline;',
							ValueDomainUpperLimitDiv: 'width:60px; display:inline;',
							DomainUnitDiv:'width:20px; display:inline;',
							ValueDomainOperandDiv: 'padding: 1px 10px; display:inline;',
							ValueDomainInputDiv: 'display:inline;',
							ValueDomainInput: 'width: 60px; text-align:right; display:inline;',
							NumericVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							ListVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							GroupVariableRow:'border:3px inset #cce7ff; padding:0px 0px 0px 30px; margin:5px 0px 10px 0px;', 
							VariableRow:'padding:5px 0px;',
							VectorVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							VectorInput: 'width: 40px; text-align:right;',
							StringVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							StringInput: 'width: 70%;',
					};
					
					var variables = inputdeck.getTopLevelVariables();
					for(var i=0; i<variables.length; i++){
						if((i % 2)== 0)
							Style.VariableRow += ' background-color:#f0f0f0;';
						else
							Style.VariableRow += ' background-color:#f8f8f8;';
						
						var div;
						switch(variables[i].getType()){
						case OSPInputdeck.Constants.VARIABLE_TYPE_GROUP:
							div = getGroupVariableRow(variables[i]);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
							div = getNumericVariableRow(variables[i]);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_LIST:
							div = getListVariableRow(variables[i]);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
							div = getVectorVariableRow(variables[i]);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_STRING:
							div = getStringVariableRow(variables[i]);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
							div = getCommentRow(variables[i]);
							break;
						}
						canvas.append(div);
					}
				};
				this.showForm = function(callback){
					Layout = {
							SweepCheckBoxDiv: 'span1',
							SweepMethodDiv: 'row',
							SweepRadioBySlice: 'span1',
							SweepRadioByValue: 'span1',
							SweepMethodValueDiv: 'span4',
							SweepMethodValue: 'span4',
							VariableNameDiv: 'span1',
							VariableDomainDiv: 'span4',
							VariableDescriptionDiv: 'span1',
							VariableRow: 'row variable-row',
					};
					Style = {
							SweepCheckBoxDiv: 'padding-left:30px; display:inline;',
							SweepMethodDiv: ' border-bottom:2px solid grey;',
							SweepRadio: 'display:inline;',
							SweepRadioBySlice: 'display:inline;',
							SweepRadioByValue: 'display:inline;',
							SweepDomainLowerLimitDiv: 'display:inline;',
							SweepDomainLowerLimit: 'width:40px; text-align:right;',
							SweepDomainOperandDiv: 'padding: 0px 10px; display:inline;',
							SweepDomainOperandSelect: 'width:50px; height:27px; display:inline;',
							SweepDomainDiv: 'padding:5px;',
							SweepSliceValueDiv: 'padding:5px;',
							SweepSliceValue: 'width:40px; text-align:right; display:inline;',
							SweepSliceValueUnitDiv: 'width:20px; display:inline;',
							SweepDivVisible: 'border:1px solid #d0d0d0;display:block; margin:5px auto;',
							SweepDivInvisible: 'border:1px solid #d0d0d0; padding-left:20px; display:none;',
							VariableNameDiv:'text-align:right; display:inline;word-wrap: break-word;',
							VariableDescriptionDiv:'text-align:center; display:inline; margin-left: 0px;',
							ValueDomainLowerLimitDiv: 'width:60px; text-align:right; display:inline;',
							ValueDomainUpperLimitDiv: 'width:60px; display:inline;',
							DomainUnitDiv:'width:20px; display:inline;',
							ValueDomainOperandDiv: 'padding: 1px 10px; display:inline;',
							ValueDomainInputDiv: 'display:inline;',
							ValueDomainInput: 'width: 60px; text-align:right; display:inline;',
							NumericVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							ListVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							GroupVariableRow:'border:3px inset #cce7ff; padding:0px 0px 0px 30px; margin:5px 0px 10px 0px;', 
							VariableRow:'padding:5px 0px;',
							VectorVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin:0px;',
							VectorInput: 'width: 40px; text-align:right;',
							StringVariableValueDiv:'text-align:center; padding: 2px 10px; display:inline;margin: 0 auto;',
							StringInput: 'width: 70%;',
					};

					var variables = inputdeck.getTopLevelVariables();
					for(var i=0; i<variables.length; i++){
						if((i % 2)== 0)
							Style.VariableRow += ' background-color:#f0f0f0;';
						else
							Style.VariableRow += ' background-color:#f8f8f8;';
						
						var div;
						switch(variables[i].getType()){
						case OSPInputdeck.Constants.VARIABLE_TYPE_GROUP:
							div = getGroupVariableRow(variables[i], true, callback);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC:
							div = getNumericVariableRow(variables[i], true, callback);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_LIST:
							div = getListVariableRow(variables[i], true, callback);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR:
							div = getVectorVariableRow(variables[i], true, callback);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_STRING:
							div = getStringVariableRow(variables[i], true, callback);
							break;
						case OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT:
							div = getCommentRow(variables[i], true, callback);
							break;
						}
						canvas.append(div);
					}
				};
			};
			window.OSPInputdeck.createEditor = function (inputdeck, namespace, canvas, languageId, jsonDisplay, jsonInputDisplay){
				return new window.OSPInputdeck.Editor (inputdeck, namespace, canvas, languageId, jsonDisplay, jsonInputDisplay);
			};
			
			
			window.OSPInputdeck.createInputViewer = function(inputdeck, namespace, canvas){
				var formattedInputs = inputdeck.getActiveVariableFormattedInputs();
				var inputCount = formattedInputs.length;
				var currentViewPage = 1;
				var viewerDiv = $('<div style="padding-left:50px;">');
				var viewSection = $('<div class="row">');
				
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
					
					var navSection = $('<div class="row">');
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
					inputDiv.attr('id', namespace+'_page_'+(i+1));
					if((i+1) !== currentViewPage )	inputDiv.attr('style', 'display:none');
					var input = formattedInputs[i];
					inputDiv.html(input["file-content"].replace(/\n/gi,"<br/>"));
					viewSection.append(inputDiv);
				}
				
				viewerDiv.append(viewSection);
				canvas.append(viewerDiv);
				
				return formattedInputs;
			};
			
			window.OSPInputdeck.setTestData = function(defaultLanguageId, availableLanguageIds){
				var inputdeckForm = OSPInputdeck.createForm();
				inputdeckForm.setDefaultLanguageId( defaultLanguageId );
				inputdeckForm.setAvailableLanguageIds( availableLanguageIds );
				
				
				inputdeckForm.setVectorFormValues(
						OSPInputdeck.Constants.BRACE_TYPE_SQUARE_SPACE,
						',',
						'[ 1, 2, 3 ]');
				inputdeckForm.setVariableFormValues('=', ';', '#');
				inputdeckForm.setVariableFormSpace(true);
				inputdeckForm.setSweepMax(1);
				inputdeckForm.setSweepCount(1);
				
				//Add Variables
				var variable = inputdeckForm.createVariable('numVar_01', OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC);
				variable.setValue(3.231);
				//variable.setValueDomainValues(1, 9, '==');
				variable.setUnit('mm');
				variable.addNameLocalizedText('en_US', 'Non-sweepable variable type of numeric');
				variable.addNameLocalizedText('ko_KR', '스윕이 불가능한 숫자 형의 변수');
				variable.addListItemActivateCondition('listVar_01', 'itemKey02', 3);
				variable.addLocalizedDescription('en_US', 'numVar_01 desctiption');
				variable.addLocalizedDescription('ko_KR', 'numVar_01 설명');
				variable.setOrder(0);
				
				variable = inputdeckForm.createVariable('numVar_02', OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC);
				variable.setValueDomainValues('1.0e-1', '1.1e0', '==');
				variable.setValue(0.5);
				variable.setUnit('&deg;');
				variable.addNameLocalizedText('en_US', 'Sweepable variable type of numeric');
				variable.addNameLocalizedText('ko_KR', '스윕 가능한 숫자 형의 변수');
				
				variable.setSweepable(true);
				variable.setSweepDomainValues(0.2, 0.6, '<=');
				variable.setSweepBySlice(true);
				variable.setSweepSlice(10);
				variable.setSweepSliceValue(0.03);
				variable.setMaxSweepSlice(20);
				variable.setSweeped(true);
				
				variable.addNumericActivateCondition('numVar_03', 5, 9, '==', 0.6);
//				variable.addListItemActivateCondition('listVar_01', 'itemKey01', 0.3);
				variable.addNumericActivateCondition('numVar_03', 1, 3, '==');
				variable.addLocalizedDescription('en_US', 'numVar_02 desctiption');
				variable.addLocalizedDescription('ko_KR', 'numVar_02 설명');
				variable.setOrder(2);
				
				variable = inputdeckForm.createVariable('numVar_03', OSPInputdeck.Constants.VARIABLE_TYPE_NUMERIC);
				variable.setValueDomainValues(0, 10, '<>');
				variable.setValue(6);
				variable.addNameLocalizedText('en_US', 'Non-sweepable variable type of numeric');
				variable.addNameLocalizedText('ko_KR', '스윕이 불가능한 숫자 형의 변수');
				variable.addLocalizedDescription('en_US', 'numVar_03 desctiption');
				variable.addLocalizedDescription('ko_KR', 'numVar_03 설명');
				variable.setOrder(1);

				variable = inputdeckForm.createVariable('stringVar_01', OSPInputdeck.Constants.VARIABLE_TYPE_STRING);
				variable.setValue('Default string value');
				variable.addNameLocalizedText('en_US', 'A variable, type of string');
				variable.addNameLocalizedText('ko_KR', '문자열 형의 변수');
				variable.addLocalizedDescription('en_US', 'stringVar_01 desctiption');
				variable.addLocalizedDescription('ko_KR', 'stringVar_01 설명');
				variable.setOrder(0);

				variable = inputdeckForm.createVariable('commentVar_01', OSPInputdeck.Constants.VARIABLE_TYPE_COMMENT);
				variable.setValue('Default comments');
				variable.addNameLocalizedText('en_US', 'A variable, type of comment');
				variable.addNameLocalizedText('ko_KR', '코멘트 형의 변수');
				variable.addLocalizedDescription('en_US', 'commentVar_01 desctiption');
				variable.addLocalizedDescription('ko_KR', 'commentVar_01 설명');
				variable.setOrder(3);

				variable = inputdeckForm.createVariable('vectorVar_01', OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR);
				variable.setDimension(4);
				var vector = ['1','3','5','7'];
				variable.setValue(vector);
				variable.addNameLocalizedText('en_US', 'A variable, type of vector');
				variable.addNameLocalizedText('ko_KR', '벡터 형의 변수');
				variable.addLocalizedDescription('en_US', 'vectorVar_01 desctiption');
				variable.addLocalizedDescription('ko_KR', 'vectorVar_01 설명');
				variable.setOrder(2);
				
				variable = inputdeckForm.createVariable('groupVar_01', OSPInputdeck.Constants.VARIABLE_TYPE_GROUP);
				variable.addNameLocalizedText('en_US', 'Group For Calc');
				variable.addNameLocalizedText( 'ko_KR', '계산을 위한 변수 그룹');
				variable.addLocalizedDescription('en_US', 'A Group 01');
				variable.addLocalizedDescription('ko_KR', '그룹 01');
				variable.attachVariable('numVar_01');
				variable.attachVariable('numVar_02');
				variable.setOrder(1);

				variable = inputdeckForm.createVariable('vectorVar_02', OSPInputdeck.Constants.VARIABLE_TYPE_VECTOR);
				variable.setDimension(3);
				vector = ['2','4','6'];
				variable.setValue(vector);
				variable.addNameLocalizedText('en_US', 'A variable, type of vector');
				variable.addNameLocalizedText('ko_KR', '벡터 형의 변수');
				variable.addLocalizedDescription('en_US', 'vectorVar_02 desctiption');
				variable.addLocalizedDescription('ko_KR', 'vectorVar_02 설명');
				variable.setOrder(4);

				variable = inputdeckForm.createVariable('listVar_01', OSPInputdeck.Constants.VARIABLE_TYPE_LIST);
				variable.setValue('itemKey02');
				variable.addNameLocalizedText('en_US', 'A variable, type of list');
				variable.addNameLocalizedText('ko_KR', '리스트 형의 변수');
				variable.addLocalizedDescription('en_US', 'listVar_01 desctiption');
				variable.addLocalizedDescription('ko_KR', 'listVar_01 설명');
				var listItem = variable.addListItem('itemKey01');
				variable.addLocalizedListItemText('itemKey01', 'en_US', 'listVar_01 item 01');
				variable.addLocalizedListItemText('itemKey01', 'ko_KR', 'listVar_01 항목 01');
				listItem = variable.addListItem('itemKey02');
				variable.addLocalizedListItemText('itemKey02', 'en_US', 'listVar_01 item 02');
				variable.addLocalizedListItemText('itemKey02', 'ko_KR', 'listVar_01 항목 02');
				listItem = variable.addListItem('itemKey03');
				listItem.addLocalizedText('en_US', 'listVar_01 item 03');
				listItem.addLocalizedText('ko_KR', 'listVar_01 항목 03');
				listItem.addNumericActivateCondition('numVar_02', 0.8, 0.9, '==');
				variable.setOrder(6);

				variable = inputdeckForm.createVariable('listVar_02', OSPInputdeck.Constants.VARIABLE_TYPE_LIST);
				variable.setValue('itemKey13');
				variable.addNameLocalizedText('en_US', 'A variable, type of list');
				variable.addNameLocalizedText('ko_KR', '리스트 형의 변수');
				variable.addLocalizedDescription('en_US', 'listVar_02 desctiption');
				variable.addLocalizedDescription('ko_KR', 'listVar_02 설명');
				listItem = variable.addListItem('itemKey11');
				variable.addLocalizedListItemText('itemKey11', 'en_US', 'listVar_02 item 01');
				variable.addLocalizedListItemText('itemKey11', 'ko_KR', 'listVar_02 항목 01');
				listItem = variable.addListItem('itemKey12');
				variable.addLocalizedListItemText('itemKey12', 'en_US', 'listVar_02 item 02');
				variable.addLocalizedListItemText('itemKey12', 'ko_KR', 'listVar_02 항목 02');
				listItem = variable.addListItem('itemKey13');
				listItem.addLocalizedText('en_US', 'listVar_02 item 03');
				listItem.addLocalizedText('ko_KR', 'listVar_02 항목 03');
				variable.setOrder(5);
				
				//var jsonData = JSON.stringify(inputdeckForm);
				//inputdeckForm = OSPInputdeck.createForm();
				//inputdeckForm.setData(JSON.parse(jsonData));
				
				return inputdeckForm;
			};
	}
    else{
        console.log("OSPInputdeck already defined.");
    }
	
})(window);
