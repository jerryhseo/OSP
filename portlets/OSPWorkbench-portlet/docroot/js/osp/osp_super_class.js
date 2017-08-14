(function(window){
	'use strict';

	if( window.OSP ){
		if( OSP._MapObject )	return;
	}
	else
		window.OSP = {};
	
	OSP._MapObject = function(){
		this.property = function( key, value){
			switch( arguments.length ){
				case 1:
					if( this.hasOwnProperty(key) )
						return this[key];
					else
						return false;
				case 2:
					this[key] = value;
					return true;
				default:
					return false;
			}
		};

		this.removeProperty = function( firstKey, secondKey ){
			switch( arguments.length ){
			case 1:
				delete this[firstKey];
				return true;
			case 2:
				var firstObj = this.property(firstKey);
				if( typeof firstObj === 'object' )
					return delete firstObj[secondKey];

				return false;
			default:
				return false;
			}
		};

		this.toJsonObject = function(){
			return JSON.parse( JSON.stringify(this) );
		};

		this._deserialize = function( key, value ){
			if( typeof value === 'function')	return false;
			else	this.property(key, value);

			return true;
		};
	}; // End of _MapObject

	OSP._OpenObject = function( jsonObject ){
		OSP._MapObject.apply(this);

		this.uuid = function( uuid ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.UUID, arguments) );
		};

		this.name = function( name ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.NAME, arguments) );
		};

		this.version = function( version ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.VERSION, arguments) );
		};

		this.status = function( status ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.STATUS, arguments) );
		};

		this.sample = function( samplePath ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.SAMPLE, arguments) );
		};

		this.samplePath = function( path ){
			var samplePath = this.sample();
			if( !samplePath )	return false;
			return sample.fullPath.apply( samplePath, arguments );
		};

		this.safeSample = function (){
			var sample = this.sample();
			if( !sample ){
				sample = new OSP.Path();
				this.sample( sample );
			}
			return sample;
		};

		this.setSample = function( parent, name, pathType, relative ){
			var sample = this.safeSample();
			return sample.setPath.apply( sample, arguments );
		};

		this.sampleUri = function( uri ){
			var sample = this.safeSample();
			return sample.uri.apply( sample, arguments );
		};

		this.samplePathType = function( pathType ){
			var sample = this.safeSample();
			return sample.type.apply( sample, arguments );
		};

		this.sampleParentFolderPath = function( parentPath ){
			var sample = this.safeSample();
			return sample.parent.apply( sample, arguments );
		};

		this.sampleName = function( name ){
			var sample = this.safeSample();
			return sample.name.apply( sample, arguments );
		};

		this.sampleRelative = function( relative ){
			var sample = this.safeSample();
			return sample.relative.apply( sample, arguments );
		};

		this.getSampleData = function( url, params ){

		};

		this.title = function( title ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TITLE, arguments) );
		};

		this.localizedTitle = function( languageId, text ){
			var title = this.title();

			switch( arguemnts.length ){
				case 1:
					if( !title )	return '';
					return title[languageId];
				case 2:
					if( !title ){
						title = {};
						this.title( title );
					}
					return title[languageId] = text;
				default:
					console.log( 'localizedTitle(): argument count mismatch('+arguments.length+')');
					return false;
			}
		};

		this.removeLocalizedTitle = function( languageId ){
			var title = this.title();
			if( !title )	return true;

			return delete title[languageId];
		};

		this.toTitleXml = function( availableLanguageIds, defaultLanguageId ){
			var title = this.title();
			if( !title )	title = {};
			return OSP.Util.toLocalizedXml( title, availableLanguageIds, defaultLanguageId );
		};

		this.targetLanguage = function ( languageId ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.TARGET_LANGUAGE, arguments) );
		};

		this.description = function( description ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.DESCRIPTION, arguments) );
		};

		this.localizedDescription = function( languageId, text ){
			var description = this.description();

			switch( arguemnts.length ){
				case 1:
					if( !description )	return '';
					return description[languageId];
				case 2:
					if( !description ){
						description = {};
						this.description( description );
					}
					return description[languageId] = text;
				default:
					return false;
			}
		};

		this.removeLocalizedDescription = function( languageId ){
			var description = this.description();
			if( !description )	return true;

			return delete description[languageId];
		};

		this.toDescriptionXml = function( availableLanguageIds, defaultLanguageId ){
			var description = this.description();
			if( !description )	description = {};
			return OSP.Util.toLocalizedXml( description, availableLanguageIds, defaultLanguageId );
		};

		this.icon = function( iconPath ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.ICON, arguments) );
		};

		this.image = function( imagePath ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.IMAGE, arguments) );
		};

		this.layout = function( layout ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.LAYOUT, arguments) );
		};

		this.layoutTemplateId = function( templateId ){
			var layout = this.layout();
			if( !layout ){
				layout = new OSP.Layout();
				this.layout( layout );
			}
			return layout.templateId.apply ( layout, arguments );
		};

		this.layoutColumn = function( columnId, portlets ){
			var layout = this.layout();
			if( !layout )	return false;

			return layout.column.apply ( layout, arguments );
		};

		this.layoutAddPortlet = function (columnId, portlet){
			var layout = this.layout();
			if( !layout ){
				layout = new OSP.Layout();
				this.layout( layout );
			}
			return layout.addPortlet.apply ( layout, arguments );
		};

		this.layoutRemovePortlet = function( columnId, portletId ){
			var layout = this.layout();
			if( !layout )	return true;

			return layout.removePortlet.apply ( layout, arguments );
		};

		this.layoutRemoveColumn = function( columnId ){
			var layout = this.layout();
			if( !layout )	return true;

			return layout.removeColumn.apply ( layout, arguments );
		};

		this.getLayoutColumnNames = function(){
			var layout = this.layout();
			if( !layout )	return false;

			return layout.getColumnNames.apply ( layout, arguments );
		};

		this.hasLayoutPortlet = function( portletId, columnId ){
			var layout = this.layout();
			if( !layout )	return false;

			return layout.hasPortlet.apply ( layout, arguments );
		};

		this.getLayoutPortlet = function( portletId, columnId ){
			var layout = this.layout();
			if( !layout )	return false;

			return layout.getPortlet.apply ( layout, arguments );
		};

		this.deserializeLayout = function( jsonLayout ){
			this.layout( new OSP.Layout( jsonLayout ) );
		};
	}; // End of _OpenObject

	OSP._StyleObject = function(){
		this.style = function( style ){
			return this.property.apply( this, OSP.Util.addFirstArgument(OSP.Constants.STYLE, arguments) );
		};

		this.css = function( key, value ){
			switch( arguments.length ){
			case 1:
				var style = this.style();
				if( !style )	return	false;
				return style[key];
			case 2:
				var style = this.style();
				if( !style ){
					style = {};
					this.style(style);
				}
				return style[key] = value;
			default:
				return false;
			}
		};

		this.removeCss = function( key ){
			var style = this.style();
			if( !style )	return true;

			delete style[key];
		};
	}; // End of _StyleObject
	
})(window);