/**
 * @file  Plugins
 * @author Alexander Rose <alexander.rose@weirdbyte.de>
 */


NGL.PluginRegistry = {

    dict: {},

    add: function( name, path ){
        this.dict[ name ] = path;
    },

    get: function( name ){
    	console.log("test1");
        if( name in this.dict ){
            return this.dict[ name ];
        }else{
            throw "NGL.PluginRegistry '" + name + "' not defined";
        }
    },

    get names(){
    	console.log("test2");
    	return Object.keys( this.dict );
    },

    get count(){
    	console.log("test3");
        return this.names.length;
    },

    load: function( name, stage ){
        var path = this.get( name );
        console.log("test plugin js");
        console.log(path);
        stage.loadFile( path, { name: name + " plugin" } );
    }

};
