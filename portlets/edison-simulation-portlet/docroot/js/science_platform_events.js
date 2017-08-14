(function(window){
	'use strict';
	
	if(typeof(OSPEvent) === 'undefined'){
        window.OSPEvent = {};

		window.OSPEvent.Constants = {
			IPC_EVENT_CONTENT_BROADCAST: 'ipc-event-content-broadcat',
			IPC_EVENT_REQUEST_CONTENT: 'ipc-event-request-content',
			
			TYPE_BROADCAST : 'broadcast',
			TYPE_TARGET: 'target',
			
			EVENT_TYPE:'eventType',
			EVENT_EMITTER: 'event-emitter',
			EVENT_TARGET: 'event-target',
			PORT_NAME: 'portName',
			EVENT_DATA: 'event-data'
		};

		window.OSPEvent.Event = function( portName, type, emitter, target, data ){
			this[OSPEvent.Constants.PORT_NAME] = portName;
			this[OSPEvent.Constants.EVENT_TYPE] = type;
			this[OSPEvent.Constants.EVENT_EMITTER] = emitter;
			this[OSPEvent.Constants.EVENT_TARGET] = target;
			this[OSPEvent.Constants.EVENT_DATA] = data;
			
			this.getPortName = function(){
				return this[OSPEvent.Constants.PORT_NAME];
			};
			this.setPortName = function(portName){
				this[OSPEvent.Constants.PORT_NAME] = portName;
			};
			this.getEventType = function(){
				return this[OSPEvent.Constants.EVENT_TYPE];
			};
			this.setEventType = function(type){
				this[OSPEvent.Constants.EVENT_TYPE] = type;
			};
			this.getEventEmitter = function(){
				return this[OSPEvent.Constants.EVENT_EMITTER];
			};
			this.setEventEmitter = function(emitter){
				this[OSPEvent.Constants.EVENT_EMITTER] = emitter;
			};
			this.getEventTarget = function (){
				return this[OSPEvent.Constants.EVENT_TARGET];
			};
			this.setEventTarget = function(target){
				this[OSPEvent.Constants.EVENT_TARGET] = target;
			};
			this.getEventData = function(){
				return this[OSPEvent.Constants.EVENT_DATA];
			};
			this.setEventData = function(data){
				this[OSPEvent.Constants.EVENT_DATA] = data;
			};
		};
		window.OSPEvent.createEvent = function(portName, type, emitter, target, data){
			return new OSPEvent.Event(portName, type, emitter, target, data);
		};
	}
    else{
        console.log("OSPEvent already defined.");
    }
		
})(window);
