(function(window){
	'use strict';
	
	if(typeof(OSPPort) === 'undefined'){
        window.OSPPort = {};
        
        window.OSPPort.Constants = {
       	    NAME : 'name',
       	    MANDATORY : 'mandatory',
       	    DATA_TYPE_ID : 'port-type-id',
       	    DATA_TYPE_NAME : 'port-type-name',
       	    DEFAULT_EDITOR_ID : 'default-editor-id',
       	    DEFAULT_ANALYZER_ID : 'default-analyzer-id',
       	    ORDER : 'order',
       	    FILE_NAME : 'file-name',
       	    CONTAINER : 'container',
       	    UNDEFINED : 'undefined',
       	    INPUTDECKPORTTYPE : 'inputdeckporttype',
       		
       		DATA_FORM_FILE : 'file',
       		DATA_FORM_FOLDER : 'folder',
       		DATA_FORM_EXTENSION : 'extention',
       		
       		PORT_TYPE: 'type',
       		PORT_TYPE_INPUT: 'input',
       		PORT_TYPE_OUTPUT: 'output',
       		
       		DATA_FORM : 'data-form',
       		
       		getDefinedDataForms : function(){
       			var form = [];
       			form.push(this.DATA_FORM_FILE);
       			form.push(this.DATA_FORM_FOLDER);
       			form.push(this.DATA_FORM_EXTENSION);
       			
       			return form;
       		}
		};
        window.OSPPort.PortErrors = {
       	    INVALID_PORT_NAME : 'invalid-port-name',
       	    DUPLICATED_PORT_NAME : 'duplicated-port-name',
		};

        window.OSPPort._PortBase = function(){
       	    this.getName = function(){
       	      return this[OSPPort.Constants.NAME];
       	    };
       	    this.setName = function(name){
       	      var verified = this.verifyPortName(name);
       	      if( verified != true )
       	        return verified;
       	      this[OSPPort.Constants.NAME] = name;
       	      return true;
       	    };
       	    this.isMandatory = function(){
       	      return this[OSPPort.Constants.MANDATORY];
       	    };
       	    this.setMandatory = function(flag){
       	      this[OSPPort.Constants.MANDATORY]=flag;
       	    };
       	    this.getDataTypeId = function(){
       	      return this[OSPPort.Constants.DATA_TYPE_ID];
       	    };
       	    this.setDataTypeId = function(id){
       	      this[OSPPort.Constants.DATA_TYPE_ID] = id;
       	    };
       	    this.getDataTypeName = function(){
       	      return this[OSPPort.Constants.DATA_TYPE_NAME];
       	    };
       	    this.setDataTypeName = function(name){
       	      this[OSPPort.Constants.DATA_TYPE_NAME] = name;
       	    };
       	    this.getPortType = function(){
       	      return this[OSPPort.Constants.PORT_TYPE];
       	    };
       	    this.setPortType = function(type){
       	      this[OSPPort.Constants.PORT_TYPE] = type;
       	    };
       	   this.getInputDeckPortType = function(){
             return this[OSPPort.Constants.INPUTDECKPORTTYPE];
           };
           this.setInputDeckPortType = function(type){
             this[OSPPort.Constants.INPUTDECKPORTTYPE] = type;
           };
           this.isInputDeckPortType = function(){
             return this[OSPPort.Constants.INPUTDECKPORTTYPE];
           };
       	    this.isCompatible = function(port){
       	      if( this.getDataTypeId() === port.getDataTypeId() )
       	        return true;
       	      else
       	        return false;
       	    };
       	    this.verifyPortName = function(name){
       	      if(!/^[-|_]?[a-zA-Z0-9_]+$/.test(name))  return PortErrors.INVALID_PORT_NAME;
       	      else  return true;
       	    };
		};

		 window.OSPPort.InputPort = function(){
       	    OSPPort._PortBase.apply(this);
       	    this[OSPPort.Constants.PORT_TYPE] = OSPPort.Constants.PORT_TYPE_INPUT;
       	    
       	    this.getDefaultEditorId = function(){
       	      return this[OSPPort.Constants.DEFAULT_EDITOR_ID];
       	    };
       	    this.setDefaultEditorId = function(id){
       	      this[OSPPort.Constants.DEFAULT_EDITOR_ID] = id;
       	    } ;
       	    this.getOrder = function() {
       	      return this[OSPPort.Constants.ORDER];
       	    };
       	    this.setOrder = function(order){
       	      this[OSPPort.Constants.ORDER] = order;
       	    };
       	    this.setData = function(jsonData){
       	      var property;
       	      for(property in jsonData ){
       	        this[property] = jsonData[property];
       	      }
       	    };
		};

		window.OSPPort.OutputPort = function(){
			OSPPort._PortBase.apply(this);
			this[OSPPort.Constants.PORT_TYPE] = OSPPort.Constants.PORT_TYPE_OUTPUT;
			
       	    this.getDefaultAnalyzerId = function(){
       	      return this[OSPPort.Constants.DEFAULT_ANALYZER_ID];
       	    };
       	    this.setDefaultAnalyzerId = function(id){
       	      this[OSPPort.Constants.DEFAULT_ANALYZER_ID] = id;
       	    };
       	    this.getFileName = function(){
       	      return this[OSPPort.Constants.FILE_NAME];
       	    };
       	    this.setFileName = function(fileName){
       	      this[OSPPort.Constants.FILE_NAME] = fileName;
       	    };
       	    this.setData = function(jsonData){
       	      var property;
       	      for(property in jsonData ){
       	        this[property] = jsonData[property];
       	      }
       	    };
       	    this.getDataForm = function(){
    			return this[OSPPort.Constants.DATA_FORM];
    		};
    		this.setDataForm = function(form){
    			this[OSPPort.Constants.DATA_FORM] = form;
    		};
		};
		
		window.OSPPort.PortMap = function(){
        	    this.getPort = function(portName){
        	      return this[portName];
        	    };
        	    this.addPort = function(port){
        	      var name = port.getName();
        	      var unique = this.isPortNameDuplicated(name);
        	      if( unique === false ) 
        	        return PortErrors.DUPLICATED_PORT_NAME;
        	      this[name] = port;
        	      return true;
        	    };
        	    this.createInputPort = function(portName){
        	      var port = new OSPPort.InputPort();
        	      var verified = port.setName(portName);
        	      if( !verified ) 
        	        return PortErrors.INVALID_PORT_NAME;
        	      var unique = this.isPortNameDuplicated(portName);
        	      if( !unique )
        	        return PortErrors.DUPLICATED_PORT_NAME;
        	      this.addPort(port);
        	      return port;
        	    };
        	    this.createOutputPort = function(portName){
        	      var port = new OSPPort.OutputPort();
        	      var verified = port.setName(portName);
        	      if( !verified ) 
        	        return PortErrors.INVALID_PORT_NAME;
        	      var unique = this.isPortNameDuplicated(portName);
        	      if( !unique )
        	        return PortErrors.DUPLICATED_PORT_NAME;
        	      this.addPort(port);
        	      return port;
        	    };
        	    this.removePort = function(portName){
        	      delete this[portName];
        	    };
        	    this.getPortArray = function(){
        	      var ports = [];
        	      var property;
        	      for(property in this ){
        	        var port = this[property];
        	        if( typeof port === 'object' )
        	          ports.push(port);
        	      }
        	      return ports;
        	    };
        	    this.getPortNameArray = function(){
        	      var portNames = [];
        	      var property;
        	      for(property in this){
        	        var port = this[property];
        	        if( typeof port === 'object' )
        	          portNames.push(property);
        	      }
        	      return portNames;
        	    };
        	    this.isInputDeckPortType = function(){
        	      var result = false;
        	      var portNames = this.getPortNameArray();
        	      for(var i=0, item; item=portNames[i]; i++) {
        	        var port = this.getPort(item);
        	        if(port.isInputDeckPortType()){
        	          result = true;
        	          break;
        	        }
        	      }
        	      return result;
        	    };
        	    this.isPortNameDuplicated = function(name){
        	      if ( this.hasOwnProperty(name) ){
        	        if(console){
        	          console.log(PortErrors.DUPLICATED_PORT_NAME);
        	        }
        	        return false;
        	      }
        	      else
        	        return true;
        	    };
        	    this.setData = function(jsonData, isInput){
        	    var property;
    	    	 for( property in jsonData){
	    	        var port;
	    	        if( isInput )
	    	          port = this.createInputPort(property);
	    	        else
	    	          port = this.createOutputPort(property);
	    	        port.setData(jsonData[property]);
	    	        this[property] = port;
    	    	  }
			};
		};

	}
    else{
        console.log("OSPInputdeck already defined.");
    }
	
})(window);
