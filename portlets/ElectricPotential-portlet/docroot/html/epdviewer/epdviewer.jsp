<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html style="height:95%">

<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/three/libs/Three_R86.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/three/libs/TrackballControls_R68.js"></script>
	<!-- 
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/three/font/helvetiker_regular.typeface.js"></script>    
	 -->
	
	<style>
			body{ background-color: rgb(255,255,255); }
			canvas{ background-color: white; float: left; }
	</style>
</head>

<body style="height:100%;">
<div>
<div style="width:45%; display:inline-block;">
<table style="width:100%;" > 
<tr bgcolor='#eeeeee'  >  <td > <SPAN style='font-size: 10pt'> Viewer </SPAN>  </td> <td> <SPAN style='font-size: 10pt'> dens </SPAN>  </td><td> <SPAN style='font-size:      10pt'> equi </SPAN>  </td><td> <SPAN style='font-size: 10pt'> terr </SPAN>  </td><td > <SPAN style='font-size: 10pt'> arrow </SPAN> </td>   <td >  <SPAN style='font-size     : 10pt'> position </SPAN> </td>     </tr>
<tr>  <td > <SPAN style='font-size: 10pt'> Pxy </SPAN> </td> 
<td> <input id='Pxy_visible'     type='checkbox' checked='false' onclick="toggleView( 'xy' )"> </td> 
<td> <input id='Pxy_equ_visible' type='checkbox' onclick="toggleEquillibrium( 'xy' )"> </td> 
<td> <input id='Pxy_mode'        type='checkbox' onclick="toggleTerrain('xy')">  </td> 
<td> <input id='Pxy_arr_visible' type='checkbox' onclick="toggleArrows('xy')"> </td> 
<td> <input id='PxyInput1'       type='text' value='0' size='3' height='1' autocomplete='off'> </td>    
 </tr>

 <tr>  <td> <SPAN style='font-size: 10pt'> Pyz </SPAN> </td>
<td> <input id='Pyz_visible'     type='checkbox' onclick="toggleView( 'yz' )"> </td> 
<td> <input id='Pyz_equ_visible' type='checkbox' onclick="toggleEquillibrium( 'yz' )"> </td> 
<td> <input id='Pyz_mode'        type='checkbox' onclick="toggleTerrain('yz')">  </td> 
<td> <input id='Pyz_arr_visible' type='checkbox' onclick="toggleArrows('yz')"> </td> 
<td> <input id='PyzInput1'       type='text' value='0' size='3' height='1' autocomplete='off'> </td>    
</tr>

<tr>  <td> <SPAN style='font-size: 10pt'> Pzx </SPAN> </td>
<td> <input id='Pzx_visible'     type='checkbox' onclick="toggleView( 'zx' )"> </td>
<td> <input id='Pzx_equ_visible' type='checkbox' onclick="toggleEquillibrium( 'zx' )"> </td>
<td> <input id='Pzx_mode'        type='checkbox' onclick="toggleTerrain('zx')">  </td>
<td> <input id='Pzx_arr_visible' type='checkbox' onclick="toggleArrows('zx')"> </td>
<td> <input id='PzxInput1' type='text' value='0' size='3' height='1' autocomplete='off'>   </td> 
</tr>
</table>
</div>
<div style="width:45%;display:inline-block;">
<table style="width:95%;"> 
<tr> <td colspan='1' > <SPAN style='font-size: 10pt'> Viewer </SPAN> </td>  <td colspan='1' bgcolor='#eeeeee'> <SPAN style='font-size: 10pt'> position </SPAN></td> <td bgcolor='#eeeeee'> <SPAN style='font-size: 10pt'> opacity </SPAN></td> </tr>
<tr> <td colspan='1' > <SPAN style='font-size: 10pt'> Pxy </SPAN> </td> <td colspan='1'>  <input id='PxyRange' type='range' style='width:90%;' value='0'        min='1' max='Nz' step='1' oninput="movePlate('xy')" autocomplete='off'/> </td> <td colspan='1'>  <input id='PxyOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput="changeOpacity('xy')" autocomplete='off'/> </td> </tr>
<tr> <td colspan='1' > <SPAN style='font-size: 10pt'> Pyz </SPAN> </td> <td colspan='1'>  <input id='PyzRange' type='range' style='width:90%;' value='0'        min='1' max='Nx' step='1' oninput="movePlate('yz')" autocomplete='off'/> </td> <td colspan='1'>  <input id='PyzOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput="changeOpacity('yz')" autocomplete='off'/> </td> </tr>
<tr> <td colspan='1' > <SPAN style='font-size: 10pt'> Pzx </SPAN> </td> <td colspan='1'>  <input id='PzxRange' type='range' style='width:90%;' value='Ny' min='1' max='Ny' step='1' oninput="movePlate('zx')" autocomplete='off'/> </td> <td colspan='1'>  <input id='PzxOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput="changeOpacity('zx')" autocomplete='off'/> </td> </tr>
</table>
</div>
</div>
<div style="width:90%; margin: auto; height:100%; ">
<div id="container" style="width:100%;margin: auto;">
</div>
</div>

<script type="text/javascript">
var Shape = function( shape ){
	this.shape = function( shape ){
		if( shape ) this.Shape = shape;
		else	return this.Shape;
	};
	this.x = function( x ){
		if( x ) this.X = x;
		else	return this.X;
	};
	this.y = function( y ){
		if( y ) this.Y = y;
		else	return this.Y;
	};
	this.z = function( z ){
		if( z ) this.Z = z;
		else	return this.Z;
	};
	this.radius = function( r ){
		if( r ) this.R = r;
		else	return this.R;
	};
	this.opacity = function( o ){
		if( o ){
			this.O = parseFloat(o) ;
			
		}
		else	return this.O ;
	};
	this.setPoistion = function( x, y, z ){
		this.X( x );
		this.Y( y );
		this.Z( z );
	};
	this.geometry = function( geometry ){
		if( geometry ) this.Geometry = geometry;
		else	return this.Geometry;
	};
	this.color = function( color ){
		if( color ) this.Color = color;
		else	return this.Color;
	};
	this.side = function( side ){
		if( side ) this.Side = side;
		else	return this.Side;
	};
	this.direction = function( direction ){
		if( direction ) this.Direction = direction;
		else	return this.Direction;
	};
	this.material =  function( mat ){
		if( mat ) this.Material = mat;
		else	return this.Material;
	};
	this.mesh =  function( mesh ){
		if( mesh ) this.Mesh = mesh;
		else	return this.Mesh;
	};
	this.setMeshPosition = function( axis, pos ){
		var mesh = this.mesh();
		mesh.position[axis] = pos;
	};
	
	this.shape(shape);
};

var Plate = function(){
	this.mesh = function( mesh ){
		if( mesh ) this.Mesh = mesh;
		else	return this.Mesh;
	};
	this.arrow = function( arrow ){
		if( arrow ) this.Arrow = arrow;
		else	return this.Arrow;
	};
	this.geometry = function( geometry ){
		if( geometry ) this.Geometry = geometry;
		else	return this.Geometry;
	};
	this.material = function( material ){
		if( material ) this.Material = material;
		else	return this.Material;
	};
	this.vmode = function( mode ){
		if( arguments.length === 1 ) this.VMode = mode;
		else	return this.VMode;
	};
	this.visibleArrow = function( visible ){
		var arrow = this.arrow();
		if( arguments.length === 1 ){
			arrow.visible = visible;
			if( visible ){
				arrow.geometry.verticesNeedUpdate = true;
			}
		}
		else	return arrow.visible;
	};
};

var Canvas = function( container ){
	var scene_width =800;
	var scene_height=700;
	var um = 1e-6;
	var Nx = 95,
		Ny=95,
		Nz=95,
		x_scal=9.400000e-05,
		y_scal=9.400000e-05,  
		z_scal=9.400000e-05, 
		N_nod=857375, 
		N_t=0 ,
		Norm_dx=1.063830e-02,
		Norm_dy=1.063830e-02,
		Norm_dz=1.063830e-02, 
		xmx=4.700000e-05,
		ymx=4.700000e-05,
		zmx=4.700000e-05,
		xmn=-4.700000e-05,
		ymn=-4.700000e-05,
		zmn=-4.700000e-05, 
		dx=1.000000e-06,
		dy=1.000000e-06,
		dz=1.000000e-06; 

	var ti=0;
	
	var x_rot=Math.PI*0/180, y_rot=Math.PI*0/180, z_rot=-Math.PI*30/180;
	var x_cam=1.5, y_cam=-1.5, z_cam=1.5;
	
	var xi=1, yi=Nz, zi=1;
	
	var W_x = 1, W_y = 1, W_z = 1;
	
	var P_100=Nz*Ny*(Nx-1);
	var P_010=Nz*(Ny-1)   ;
	var P_001=(Nz-1)      ;
	
	var P_110=P_100+P_010 ;
	var P_101=P_100+P_001 ;
	var P_011=P_010+P_001 ;
	
	var P_111=P_100+P_010+P_001 ;
	
	//var Viewer = new Array();
	
	var ang = Math.PI*15/180;
	
	// From --- middle -----
	var N_t;
	
	var Vertices = {
	};
	var Grad = [];
 

	this.scene = function( scene ){
		if( scene ) this.Scene = scene;
		else	return this.Scene;
	};
	this.camera = function( camera ){
		if( camera ) this.Camera = camera;
		else	return this.Camera;
	};
	this.renderer = function( renderer ){
		if( renderer ) this.Renderer = renderer;
		else	return this.Renderer;
	};
	this.ambientLight = function( light ){
		if( light ) this.AL = light;
		else	return this.AL;
	};
	this.spotLights = function( lights ){
		if( lights ) this.SLS = lights;
		else	return this.SLS;
	};
	this.addSpotLight = function( light ){
		var spotLights = this.spotLights();
		if( !spotLights ){
			spotLights = {};
			this.spotLights( spotLights );
		}
		
		spotLights.push( light );
	};
	this.plates = function( plates ){
		if( plates ) this.Plates = plates;
		else	return this.Plates;
	};
	this.addPlate = function( id, plate ){
		var plates = this.plates();
		if( !plates ){
			plates = {};
			this.plates(plates);
		}
		
		plates[id] = plate;
	};
	this.getPlate = function( id ){
		var plates = this.plates();
		if( !plates ) return false;
		
		return plates[id];
	};
	this.setPlateArrow = function( id, arrow ){
		var plate = this.getPlate(id);
		
		plate.arrow( plate );
	};
	this.setPlateMesh = function( id, mesh ){
		var plate = this.getPlate(id);
		
		plate.mesh( mesh );
	};
	this.setPlateGeometry = function( id, geometry ){
		var plate = this.getPlate(id);
		
		plate.geometry( geometry );
	};
	this.setPlateMaterial = function( id, material ){
		var plate = this.getPlate(id);
		
		plate.material( material );
	};
	this.getPlateArrow = function( id ){
		var plate = this.getPlate(id);
		
		return plate.arrow();
	};
	this.getPlateMesh= function( id ){
		var plate = this.getPlate(id);
		
		return plate.mesh();
	};
	this.getPlateGeometry = function( id ){
		var plate = this.getPlate(id);
		
		return plate.geometry();
	};
	this.getPlateMaterial = function( id ){
		var plate = this.getPlate(id);
		
		return plate.material();
	};
	this.shapes = function( shapes ){
		if( shapes ) this.Shapes = shapes;
		else	return this.Shapes;
	};
	this.addShape = function( id, shape ){
		var shapes = this.shapes();
		if( !shapes ){
			shapes = {};
			this.shapes(shapes);
		}
		
		shapes[id] = shape;
	};
	this.getShape = function( id ){
		var shapes = this.shapes();
		if( !shapes ) return false;
		
		return shapes[id];
	};
	this.setShapeMesh = function(id, mesh ){
		var shape = this.getShape( id );
		shape.mesh(mesh);
	};
	this.removeShapeMesh = function( id ){
		var shape = this.getShape( id );
		delete shape.Mesh;
	};
	this.moveObject = function ( obj, x, y, z ) 
	{
		var shape = this.getShape( obj );
		var mesh = shape.mesh();
		if( !mesh )	return;
		mesh.position.x = x*um/x_scal;
		mesh.position.y = y*um/y_scal;
		mesh.position.z = z*um/z_scal;
	};
	this.changeEPTransparency = function( obj, o ){
		var shape = this.getShape( obj );
		var material = shape.material();
		material.opacity = o;
	};
	this.displayVolume = function(obj, x, y, z, o, r){
		var shape = this.getShape( obj );
		
		var Px=x*um/x_scal;
		var Py=y*um/y_scal;
		var Pz=z*um/z_scal;
		var Pr=r*um/z_scal; 

		var geometry;
		switch( obj ){
			case 'sphere':
				shape.geometry( new THREE.SphereGeometry(Pr,20,20) );
				break;
			case 'cube':
				shape.geometry( new THREE.CubeGeometry(Pr, Pr, Pr) );
				break;
			default:
				alert('Un-known shape!!!!!');
				return;
		}

		if( shape.mesh() )
		{
			scene.remove( shape.mesh() ); 
		}
		var mesh = new THREE.Mesh( shape.geometry(), shape.material() );       
		mesh.position.set(Px, Py, Pz);

		shape.mesh(mesh);
		scene.add(mesh);
	};
	this.displayObject = function ( obj, x, y, z, r, o, force ){
		var shape = this.getShape( obj );
		if( force )	delete shape.Mesh;
		
		
		if( r === 0 ){
			shape.radius(Math.sqrt(Norm_dx*Norm_dx + Norm_dy*Norm_dy + Norm_dz*Norm_dz)/3);
		}
		else{
			shape.radius(r*um/z_scal);
		}
		
		if( !shape.geometry() ){
			switch( obj ){
				case 'point_1':
				case 'point_2':
				case 'sphere':
					shape.geometry( new THREE.SphereGeometry(shape.radius(),20,20) );
					break;
				case 'cube':
					shape.geometry( new THREE.CubeGeometry(shape.radius(), shape.radius(), shape.radius()) );
					break;
			}
		}
		if( !shape.material() ){
			if( !o )	o = 1;
			shape.material( new THREE.MeshLambertMaterial({color: shape.color(), transparent:true , opacity: o}));
		}

		if( !shape.mesh() ){
			var mesh = new THREE.Mesh(shape.geometry(),shape.material());
			mesh.position.set(x*um/x_scal,y*um/y_scal,z*um/z_scal);		
			//mesh.position.set(x,y,z);	
			//console.log(x,y,z);

			shape.mesh(mesh);
			scene.add(mesh);
		}
		else{
			scene.remove( shape.mesh() );
			delete shape.Mesh;
		}
	};
	
	this.removeAllObject = function ( ){
		
		console.log("remove -------");
		var shape = this.getShape( 'point_1' );		
		scene.remove( shape.mesh() );
		delete shape.Mesh;
        
		shape = this.getShape( 'point_2' );
		
		scene.remove( shape.mesh() );
		delete shape.Mesh;
        
		shape = this.getShape( 'sphere' );
		
		scene.remove( shape.mesh() );
		delete shape.Mesh;
        
		shape = this.getShape( 'cube' );
		
		scene.remove( shape.mesh() );
		delete shape.Mesh;
		
	};
	
	
	this.setPlateMode = function( plateId, mode ){
		var plate = this.getPlate(plateId);
		
		var ord_XYP=0;
		var ord_YZP=0;
		var ord_ZXP=0;

		var ord_data=0;

		var f = 0;

		var axisMax = {};
		switch( plateId ){
			case 'xy':
				axisMax.i = Nx-1;
				axisMax.j = Ny-1;
				axisMax.ord = function( i, j ){
					return (zi-1) + Nz*(j -1) + Nz*Ny*(i -1);
				}
				axisMax.vf = [
					[0    , Nz, Nz*Ny   ],
					[Nz*Ny, Nz, Nz*Ny+Nz]
				];
				break;
			case 'yz':
				axisMax.i = Ny-1;
				axisMax.j = Nz-1;
				axisMax.ord = function( i, j ){
					return (j -1) + Nz*(i -1) + Nz*Ny*(xi-1);
				}
				axisMax.vf = [
					[ 0, 1, Nz  ],
					[Nz, 1, Nz+1]
				];
				break;
			case 'zx':
				axisMax.i = Nz-1;
				axisMax.j = Nx-1;
				axisMax.ord = function( i, j ){
					return (i -1) + Nz*(yi-1) + Nz*Ny*(j -1);
				}
				axisMax.vf = [
					[0, Nz*Ny, 1       ],
					[1, Nz*Ny, 1+ Nz*Ny]
				];
				break;
		}

		var vertices = Vertices['0'];
		var geometry = plate.geometry();
		
		var iMax = axisMax.i;
		var jMax = axisMax.j;
		switch(mode){
			case 0: 
				for (var i=1; i<=iMax; i++) { 
					for (var j=1; j<=jMax; j++) {
						var ord = axisMax.ord(i, j);

						for (var k=0; k<=1; k++) 
						{
							var vf = axisMax.vf[k];
							for( var kk=0; kk <=2; kk++ ){
								geometry.faces[f].vertexColors[kk] = new THREE.Color(
				                                                     vertices[ord + vf[kk]].cr, 
				                                                     vertices[ord + vf[kk]].cg,
				                                                     vertices[ord + vf[kk]].cb);
							}
							f++;
						}
					}
				}
				break;
			case 1:
				for (var i=1; i<=iMax; i++) { 
					for (var j=1; j<=jMax; j++) {
						var ord = axisMax.ord(i, j);
						for (var k=0; k<=1; k++){
							var vf = axisMax.vf[k];
							var fsec_color = (vertices[ord+vf[0]].p + vertices[ord+vf[1]].p + vertices[ord+vf[2]].p) / 3;

							fsec_color=Math.round(fsec_color*20)/20;

							for( var kk=0; kk <=2; kk++ ){
								geometry.faces[f].vertexColors[kk] = new THREE.Color(fsec_color,fsec_color,fsec_color);
							}
							f++;
						}
					}
				} 
				break; 
		}
	};
	this.setViewerShape = function( plateId ){
		var outMax, inMax, order, xyzp;
		switch( plateId ){
			case 'xy':
				outMax = Nx;
				inMax = Ny;
				order = function(i, j){
					return (zi-1) + Nz*(j-1) + Nz*Ny*(i-1);
				};
				xyzp = function( i, j ){
					return (j-1 ) + Ny*(i-1);
				};
				break;
			case 'yz':
				outMax = Ny;
				inMax = Nz;
				order = function(j, k){
					return (k-1) + Nz*(j-1) + Nz*Ny*(xi-1);
				};
				xyzp = function( j, k ){
					return (k-1) + Nz*(j-1);
				};
				break;
			case 'zx':
				outMax = Nz;
				inMax = Nx;
				order = function(k, i){
					return (k-1) + Nz*(yi-1) + Nz*Ny*(i-1);
				};
				xyzp = function( k, i ){
					return (i-1) + Nx*(k-1);
				};
				break;
		}
		
		var plate = this.getPlate( plateId );
		var geometry = plate.geometry();
		var vertices = Vertices['0'];
		
		for (var i=1; i<=outMax ; i++) { 
			for (var j=1; j<=inMax ; j++) {
				var ord_data = order( i, j );
				var ord_XYP  = xyzp( i, j );
	
				
				geometry.vertices[ord_XYP]=
					new THREE.Vector3(
					                  vertices[ord_data].x + (plateId=="yz"? 1:0)* vertices[ord_data].p*plate.vmode(),
					                  vertices[ord_data].y - (plateId=="zx"? 1:0)* vertices[ord_data].p*plate.vmode(),
					                  vertices[ord_data].z + (plateId=="xy"? 1:0)* vertices[ord_data].p*plate.vmode() ) ;
			}
		}
	};
	this.setViewerFaces = function(){
		var geoXY = this.getPlateGeometry('xy');
		var geoYZ = this.getPlateGeometry('yz');
		var geoZX = this.getPlateGeometry('zx');
		
		for (var i=1; i<=Nx-1; i++) { 
			for (var j=1; j<=Ny-1; j++) {
				var ord_XYP  = (j-1) + Ny*(i-1);
				geoXY.faces.push( new THREE.Face3(ord_XYP + Ny  , ord_XYP+1, ord_XYP     ) );   
				geoXY.faces.push( new THREE.Face3(ord_XYP + Ny+1, ord_XYP+1, ord_XYP + Ny) );

				/*
				geoXY.faces.push( new THREE.Face3(ord_XYP     , ord_XYP+1, ord_XYP + Ny  ) );   
				geoXY.faces.push( new THREE.Face3(ord_XYP + Ny, ord_XYP+1, ord_XYP + Ny+1) );
				*/
			} 
		}

		    //--------------------------------------- YZ �� ---------------------------

		for (var j=1; j<=Ny-1; j++) { 
			for (var k=1; k<=Nz-1; k++) {
				var ord_YZP  = (k-1) + Nz*(j-1);

				geoYZ.faces.push( new THREE.Face3(ord_YZP     , ord_YZP + Nz  , ord_YZP+1  ) );   
				geoYZ.faces.push( new THREE.Face3(ord_YZP + Nz, ord_YZP + Nz+1, ord_YZP+1) );  
			} 
		}

		    //--------------------------------------- ZX �� ---------------------------


		for (var k=1; k<=Nz-1; k++) { 
			for (var i=1; i<=Nx-1; i++) {
				var ord_ZXP  = (i-1) + Nx*(k-1);

				geoZX.faces.push( new THREE.Face3(ord_ZXP     , ord_ZXP+1, ord_ZXP + Nx  ) );   
				geoZX.faces.push( new THREE.Face3(ord_ZXP + Nx, ord_ZXP+1, ord_ZXP + Nx+1) );  
			} 
		}
	};
	this.toggleView = function (plateId) 
	{
		this.setPlateMode('xy',0); 
		this.setPlateMode('yz',0);
		this.setPlateMode('zx',0);

		var checkInput = document.getElementById('P'+plateId+'_visible');
		var uncheckInput = document.getElementById('P'+plateId+'_equ_visible');
		var mesh = this.getPlateMesh(plateId);
		
		if( checkInput.checked ){
			uncheckInput.checked = false;
			mesh.visible=true; 
			mesh.geometry.elementsNeedUpdate = true;
		}
		else{
			mesh.visible = false;
		}
	};
	this.toggleEquillibrium = function( plateId ){
		this.setPlateMode('xy',1); 
		this.setPlateMode('yz',1);
		this.setPlateMode('zx',1);
		
		var checkInput = document.getElementById('P'+plateId+'_equ_visible');
		var uncheckInput = document.getElementById('P'+plateId+'_visible');
		var mesh = this.getPlateMesh(plateId);
		
		if( checkInput.checked ){
			uncheckInput.checked = false; 
			mesh.visible=true; 
			mesh.geometry.elementsNeedUpdate = true;
		}
		else{
			mesh.visible = false; 	  
		}
	};
	this.toggleTerrain = function( plateId ){
		var plate = this.getPlate( plateId );
		var vmodeInput = document.getElementById('P'+plateId+'_mode');
		var arrowInput = document.getElementById('P'+plateId+'_arr_visible');
		
		if( vmodeInput.checked ) {
			plate.vmode( 1 );	 
		}
		else{
			plate.vmode( 0 ); 
			if( arrowInput.checked ) 
				plate.visibleArrow(true);
		}

		this.setViewerShape( plateId );

		var mesh = plate.mesh();
		mesh.geometry.elementsNeedUpdate = true;
	};
	this.toggleArrows = function( plateId ){
		var checkInput = document.getElementById('P'+plateId+'_arr_visible');
	
		var plate = this.getPlate( plateId );
		plate.visibleArrow(checkInput.checked);
	};
	this.movePlate = function( plateId ){
		var valueInput = document.getElementById('P'+plateId+'Range');
		var checkInput = document.getElementById('P'+plateId+'_equ_visible');
		
		var V_mode = 0;
		if ( checkInput.checked )  V_mode=1;
	
		var posInput = 'P'+plateId+'Input1';
		switch( plateId ){
			case 'xy':
				zi = valueInput.value;
				document.getElementById(posInput).value = Vertices['0'][zi-1].z;
				break;
			case 'yz':
				xi = valueInput.value;
				document.getElementById(posInput).value = Vertices['0'][Nz*Ny*(xi-1)].x;
				break;
			case 'zx':
				yi = valueInput.value;
				document.getElementById(posInput).value = Vertices['0'][Nz*(yi-1)].y;
				break;
		}
		
		this.setViewerShape(plateId);
		this.setPlateMode(plateId, V_mode);
		
		var plateMesh = this.getPlateMesh(plateId);
		plateMesh.geometry.elementsNeedUpdate = true;
			
		this.setArrows(plateId);
	};
	this.changeOpacity = function( plateId ){
		var plateMat = this.getPlateMaterial( plateId );
		plateMat.opacity = document.getElementById('P'+plateId+'Opacity').value; 
	};
	this.loadData = function( data ){
		this.removeAllObject();
		var lines = data.split('\n');
		
		for( var index=0; index<lines.length; index++ ){
			var line = lines[index].trim();
			if( !line ){
				continue;
			}
			
			if( line[0] === '@'){
				var line = line.replace('@', '');
				var keyVal = line.split(' ');
				var start = index+1;
				var end = start + Number(keyVal[1]);
				index = end-1;
				switch( keyVal[0].trim()){
					case 'EP_input':
						this.parseEPInput( lines.slice(start, end));
						break;
					case 'EP_data':
						this.parseEPData( lines.slice(start, end));
						break;
					case 'EF_data':
						this.parseEFData( lines.slice(start, end));
						break;
					case 'params':
						this.parseParams( lines.slice(start, end));
						break;
					default:
						alert( 'File type mismatch......');
				}
			}
		}
		
		this.setViewerShape('xy');
		this.setViewerShape('yz');
		this.setViewerShape('zx');
		
		this.setViewerFaces();
		
		this.setArrows('xy');
		this.setArrows('yz');
		this.setArrows('zx');
	};
	this.parseEPInput = function( dataLines ){
		for( var i=0; i<dataLines.length; i++ ){
			var line = dataLines[i];
			
			var param = line.split('=');
			var values = param[1].trim().split('/');
			
			switch( param[0].trim()){
				case 'obj1':
					if(Number(values[0])==1) this.displayObject('point_1',  Number(values[2]), Number(values[3]), Number(values[4]), Number(values[5]), 1, true);
					break;
				case 'obj2':
					if(Number(values[0])==1) this.displayObject('point_2',  Number(values[2]), Number(values[3]), Number(values[4]), Number(values[5]), 1, true);
					break;
				case 'obj3' :
					if(Number(values[0])==1) this.displayObject('sphere',  Number(values[2]), Number(values[3]), Number(values[4]), Number(values[5]), 1, true);
					break;
				case 'obj4' :
					if(Number(values[0])==1) this.displayObject('cube',  Number(values[2]), Number(values[3]), Number(values[4]), Number(values[5]), 1, true);
					break;
				case 'obj0_tra' :
				case 'obj1_tra' :
				case 'obj2_tra' :
				case 'obj3_tra' :
					break;
				default:
					alert('Un-recognizable parameter: '+param[0].trim());
					return;
			}
		}
	};
	this.parseEPData = function( dataLines ){
		var time = '0';
		Vertices[time] = [];
		
		for( var i=0; i<dataLines.length; i++ ){
			var line = dataLines[i];
			var values = line.split(',');
			
			var vertex = {};
			vertex.x = parseFloat(values[0]);
			vertex.y = parseFloat(values[1]);
			vertex.z = parseFloat(values[2]);
			vertex.cr = Number(values[3]);
			vertex.cg = Number(values[4]);
			vertex.cb = Number(values[5]);
			vertex.p = parseFloat(values[6]);
			Vertices[time].push( vertex );
		}
	};
	this.parseEFData = function( dataLines ){
		for( var i=0; i<dataLines.length; i++ ){
			var line = dataLines[i];
			var values = line.split(',');
			
			var xyz = {
				x: Number(values[0]),
				y: Number(values[1]),
				z: Number(values[2])
			};
			Grad.push( xyz );
		}
	};
	this.parseParams = function( dataLines ){
		
		for( var i=0; i<dataLines.length; i++ ){
			var line = dataLines[i];
			
			var values = line.split('=');
			
			switch( values[0].trim() ){
				case 'Nx'      :Nx      =Number(values[1]); break;
				case 'Ny'      :Ny      =Number(values[1]); break;
				case 'Nz'      :Nz      =Number(values[1]); break;				
				case 'x_scal'  :x_scal  =Number(values[1]); break;				
				case 'y_scal'  :y_scal  =Number(values[1]); break;
				case 'z_scal'  :z_scal  =Number(values[1]); break;				
				case 'N_nod'   :N_nod   =Number(values[1]); break;				
				case 'N_t'     :N_t     =Number(values[1]); break;
				case 'Norm_dx' :Norm_dx =Number(values[1]); break;				
				case 'Norm_dy' :Norm_dy =Number(values[1]); break;				
				case 'Norm_dz' :Norm_dz =Number(values[1]); break;				
				case 'xmx'     :xmx     =Number(values[1]); break;				
				case 'ymx'     :ymx     =Number(values[1]); break;				
				case 'zmx'     :zmx     =Number(values[1]); break;				
				case 'xmn'     :xmn     =Number(values[1]); break;				
				case 'ymn'     :ymn     =Number(values[1]); break;				
				case 'zmn'     :zmn     =Number(values[1]); break;				
				case 'dx'      :dx      =Number(values[1]); break;				
				case 'dy'      :dy      =Number(values[1]); break;				
				case 'dz'      :dz      =Number(values[1]); break;				

				default:
					alert('Un-recognizable parameter: '+values[0].trim());
					return;
			}
		};
	};
	this.setArrows = function( plateId ){
		var plate = this.getPlate( plateId );
		
		var i_arr=0;
		var arrow_L = 0.5; 
		var s_L=0.4;
		var expan=2;
		
		var arrow = this.getPlateArrow( plateId );
		var geometry = arrow.geometry;
		
		var outMax, inMax, order;
		
		
		var vertices = Vertices['0'];
		
		switch( plateId ){
			case 'xy':
				outMax = Nx;
				inMax = Ny;
				order = function(i, j){
					return (zi-1) + Nz*j + Nz*Ny*i ;
				};
				
				for(var i=0; i<outMax; i=i+expan){
					for(var j=0; j<inMax; j=j+expan){
						var ord = order(i, j);

						var x0=vertices[ord].x - Grad[ord].x*arrow_L*expan;
						var y0=vertices[ord].y - Grad[ord].y*arrow_L*expan;
						var x1=vertices[ord].x + Grad[ord].x*arrow_L*expan;
						var y1=vertices[ord].y + Grad[ord].y*arrow_L*expan;	
						
						// console.log("dddd",x0,y0,x1,y1);

						geometry.vertices[i_arr++]= new THREE.Vector3(x0, y0, vertices[ord].z) ;
						geometry.vertices[i_arr++]= new THREE.Vector3(x1, y1, vertices[ord].z) ;

						var x2 = ((x0-x1)*Math.cos(ang)-(y0-y1)*Math.sin(ang))*s_L+x1, y2 = ((x0-x1)*Math.sin(ang)+(y0-y1)*Math.cos(ang))*s_L+y1;

						geometry.vertices[i_arr++]= new THREE.Vector3(x1, y1, vertices[ord].z) ;
						geometry.vertices[i_arr++]= new THREE.Vector3(x2, y2, vertices[ord].z) ;

						var x3 = ((x0-x1)*Math.cos(-ang)-(y0-y1)*Math.sin(-ang))*s_L+x1, y3 = ((x0-x1)*Math.sin(-ang)+(y0-y1)*Math.cos(-ang))*s_L+y1;

						geometry.vertices[i_arr++]= new THREE.Vector3(x1, y1, vertices[ord].z) ;
						geometry.vertices[i_arr++]= new THREE.Vector3(x3, y3, vertices[ord].z) ; 
					}
				}
				
				break;
			case 'yz':
				outMax = Ny;
				inMax = Nz;
				order = function(j, k){
					return k + Nz*j + Nz*Ny*(xi-1) ;
				};
				
				for(var j=0; j<outMax; j=j+expan)
				{	
				  for(var k=0; k<inMax; k=k+expan)
				  {			
					  var ord = order(j,k);
					  
					//  console.log("ord=", ord);
				//	  console.log("ord V G=", vertices[ord].y, Grad[ord].y, vertices[ord].z, Grad[ord].z);
					  
						 var y0=vertices[ord].y- Grad[ord].y*arrow_L*expan;
						 var z0=vertices[ord].z- Grad[ord].z*arrow_L*expan;
						 var y1=vertices[ord].y+ Grad[ord].y*arrow_L*expan;
						 var z1=vertices[ord].z+ Grad[ord].z*arrow_L*expan;	
						 
					//	 console.log("dddd",y0,z0,y1,z1);

						 geometry.vertices[i_arr++]= new THREE.Vector3(vertices[ord].x, y0, z0) ;
						 geometry.vertices[i_arr++]= new THREE.Vector3(vertices[ord].x, y1, z1) ;

						 var y2 = ((y0-y1)*Math.cos(ang)-(z0-z1)*Math.sin(ang))*s_L+y1;
						 var z2 = ((y0-y1)*Math.sin(ang)+(z0-z1)*Math.cos(ang))*s_L+z1;

						 geometry.vertices[i_arr++]= new THREE.Vector3(vertices[ord].x, y1, z1) ;
						 geometry.vertices[i_arr++]= new THREE.Vector3(vertices[ord].x, y2, z2) ;

						 var y3 = ((y0-y1)*Math.cos(-ang)-(z0-z1)*Math.sin(-ang))*s_L+y1;
						 var z3 = ((y0-y1)*Math.sin(-ang)+(z0-z1)*Math.cos(-ang))*s_L+z1;

						 geometry.vertices[i_arr++]= new THREE.Vector3(vertices[ord].x, y1, z1) ;
						 geometry.vertices[i_arr++]= new THREE.Vector3(vertices[ord].x, y3, z3) ; 
				
					}
				}
				break;	
			case 'zx':
				outMax = Nz;
				inMax = Nx;
				order = function(k, i){
					return k + Nz*(yi-1) + Nz*Ny*i ;
				};
				
				for(var k=0; k<outMax ; k=k+expan)
				   {
					for(var i=0; i<inMax ; i=i+expan)
					 {	
						     var ord = order(k,i);
						     var z0=vertices[ord].z- Grad[ord].z*arrow_L*expan;
							 var x0=vertices[ord].x- Grad[ord].x*arrow_L*expan;
							 var z1=vertices[ord].z+ Grad[ord].z*arrow_L*expan;								
							 var x1=vertices[ord].x+ Grad[ord].x*arrow_L*expan;
							 
							 geometry.vertices[i_arr++]= new THREE.Vector3(x0, vertices[ord].y, z0) ;
							 geometry.vertices[i_arr++]= new THREE.Vector3(x1, vertices[ord].y, z1) ;

							 var x2 = ((x0-x1)*Math.cos(ang)-(z0-z1)*Math.sin(ang))*s_L+x1;
							 var z2 = ((x0-x1)*Math.sin(ang)+(z0-z1)*Math.cos(ang))*s_L+z1;

							 geometry.vertices[i_arr++]= new THREE.Vector3(x1, vertices[ord].y, z1) ;
							 geometry.vertices[i_arr++]= new THREE.Vector3(x2, vertices[ord].y, z2) ;

							 var x3 = ((x0-x1)*Math.cos(-ang)-(z0-z1)*Math.sin(-ang))*s_L+x1;
							 var y3 = ((x0-x1)*Math.sin(-ang)+(z0-z1)*Math.cos(-ang))*s_L+z1;

							 geometry.vertices[i_arr++]= new THREE.Vector3(x1, vertices[ord].y, z1) ;
							 geometry.vertices[i_arr++]= new THREE.Vector3(x3, vertices[ord].y, y3) ; 
						}
					  }
				
				break;
		}
		
		
		
		
		
		
		
		
						
		if( arrow.visible )
			geometry.verticesNeedUpdate = true;
	};
	
	this.drawAxis = function() {
		var scene = this.scene();
		var axis     = [];
	
		var N_axis = 9;
	
		var Label_Px = new Array();
		var Label_Py = new Array();
		var Label_Pz = new Array();
		
		var mmx=0.5;
	
		for( var i=0;i<=N_axis-1; i++) {
			axis[i] = {};
			axis[i].geo = new THREE.Geometry();
			switch( i ){
				case 0:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx, -mmx));
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx, -mmx, -mmx));
					break;
				case 1:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx, -mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx,  mmx));
					break;
				case 2:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx,  mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx,  mmx,  mmx));
					break;
				case 3:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx,  mmx,  mmx));
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx,  mmx,  mmx));
					break;
				case 4:
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx,  mmx,  mmx));
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx,  mmx, -mmx));
					break;
				case 5:
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx,  mmx, -mmx));
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx, -mmx, -mmx));
					break;
				case 6:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx, -mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx,  mmx, -mmx));
					break;
				case 7:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx,  mmx, -mmx));
					axis[i].geo.vertices.push(new THREE.Vector3(  mmx,  mmx, -mmx));
					break;
				case 8:
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx,  mmx, -mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( -mmx,  mmx,  mmx));
					break;
				
			}
			axis[i].mat = new THREE.LineBasicMaterial( { color: 0x000000, linewidth: 3 } );
			axis[i].mesh = new THREE.Line( axis[i].geo, axis[i].mat );
			scene.add( axis[i].mesh );
		}


	//----------------------- x tick ---------------------------------------
		var N_tick_X=4 ;

		var tick_X     = [];
		var margin    = W_x * 0.05;
		
	
		for(var i=0;i<=N_tick_X; i++){
			tick_X[i] = {};
			tick_X[i].geo = new THREE.Geometry(); 
			tick_X[i].mat = new THREE.LineBasicMaterial( { color: 0x000000, linewidth: 3 } );
			tick_X[i].mesh = new THREE.Line( tick_X[i].geo, tick_X[i].mat );
			tick_X[i].p = Number((i*W_x/N_tick_X-mmx).toFixed(2)) ;
			
			if( i === 0 ){
				tick_X[i].geo.vertices.push( new THREE.Vector3( -mmx, -mmx, -mmx ) ); 
				tick_X[i].geo.vertices.push( new THREE.Vector3( -mmx, -mmx-margin, -mmx) );
			}
			else if( i === N_tick_X ){
				tick_X[i].geo.vertices.push( new THREE.Vector3( mmx, -mmx, -mmx ) ); 
				tick_X[i].geo.vertices.push( new THREE.Vector3( mmx, -mmx-margin, -mmx) ); 
			}
			else{
				tick_X[i].geo.vertices.push(new THREE.Vector3( tick_X[i].p, -mmx,        -mmx));
				tick_X[i].geo.vertices.push(new THREE.Vector3( tick_X[i].p, -mmx-margin, -mmx));
			}
			scene.add( tick_X[i].mesh );
		}

		var Label_X = new THREE.FontLoader();

		Label_X.load( '<%=request.getContextPath()%>/js/three/font/typeface.helvetiker_regular.js', function ( font ){
			var texts = [];
			
			for(var i=0;i<=N_tick_X;i++) {
				texts[i] = {};
				texts[i].size = W_x * .05;
				var fine_1 = texts[i].size * 0.5, fine_2 = -W_y*0.2;
				texts[i].geo = new THREE.BufferGeometry();		 
				texts[i].geo.fromGeometry( new THREE.ShapeGeometry( font.generateShapes( (tick_X[i].p*x_scal*1e6).toFixed(2)  , texts[i].size, 1 ) ) );
				texts[i].mat = new THREE.MeshBasicMaterial( {color: 0x000000,  side: THREE.DoubleSide	} );
				texts[i].mesh = new THREE.Mesh( texts[i].geo, texts[i].mat );					
				texts[i].mesh.position.set(tick_X[i].p+fine_1,-mmx+ fine_2, -mmx);
				texts[i].mesh.rotation.z= Math.PI*0.5;						
				scene.add( texts[i].mesh );
			}
			
			var T_text_Shape = new THREE.BufferGeometry();		
			var T_text= new THREE.Mesh( T_text_Shape, texts[0].mat );					
			var T_text_size = W_x * .07;
			var fine_1= T_text_size * 1, fine_2=-T_text_size * 11;
			T_text_Shape.fromGeometry( new THREE.ShapeGeometry( font.generateShapes( 'x(um)' , T_text_size, 1 ) ) );				    	
			T_text.position.set( W_x*0.5-mmx-fine_1, fine_2, -mmx );
			scene.add(T_text );	
		}); //end load function
	  
	  //----------------------- y tick ---------------------------------------
		var N_tick_Y=4 ;

		var tick_Y     = [];
		var margin    = W_y * 0.05;
	
		for(var i=0;i<=N_tick_Y; i++){
			tick_Y[i] = {};
			tick_Y[i].geo = new THREE.Geometry(); 
			tick_Y[i].mat = new THREE.LineBasicMaterial( { color: 0x000000, linewidth: 3 } );
			tick_Y[i].mesh = new THREE.Line( tick_Y[i].geo, tick_Y[i].mat );
			tick_Y[i].p = Number((i*W_y/N_tick_Y-mmx).toFixed(2)) ;
			
			if( i === 0 ){
				tick_Y[i].geo.vertices.push( new THREE.Vector3( mmx        , i*(1)/N_tick_Y-mmx, -mmx) ); 
				tick_Y[i].geo.vertices.push( new THREE.Vector3( mmx+margin, i*(1)/N_tick_Y-mmx, -mmx) );
			}
			else if( i === N_tick_Y ){
				tick_Y[i].geo.vertices.push( new THREE.Vector3( mmx        , i*(1)/N_tick_Y-mmx, -mmx) ); 
				tick_Y[i].geo.vertices.push( new THREE.Vector3( mmx+margin, i*(1)/N_tick_Y-mmx, -mmx) ); 
			}
			else{
				tick_Y[i].geo.vertices.push(new THREE.Vector3( mmx        , i*(1)/N_tick_Y-mmx, -mmx));
				tick_Y[i].geo.vertices.push(new THREE.Vector3( mmx+margin, i*(1)/N_tick_Y-mmx, -mmx));
			}
			scene.add( tick_Y[i].mesh );
		}

		var Label_Y = new THREE.FontLoader();

		Label_Y.load( '<%=request.getContextPath()%>/js/three/font/typeface.helvetiker_regular.js', function ( font ){
			var texts = [];
			
			for(var i=0;i<=N_tick_Y;i++) {
				texts[i] = {};
				texts[i].size = W_y * .05;
				var fine_1 = texts[i].size * 0.35, fine_2 = W_y*0.05;
				texts[i].geo = new THREE.BufferGeometry();		 
				texts[i].geo.fromGeometry( new THREE.ShapeGeometry( font.generateShapes( (tick_Y[i].p*y_scal*1e6).toFixed(2)  , texts[i].size, 1 ) ) );
				texts[i].mat = new THREE.MeshBasicMaterial( {color: 0x000000,  side: THREE.DoubleSide	} );
				texts[i].mesh = new THREE.Mesh( texts[i].geo, texts[i].mat );					
				texts[i].mesh.position.set(mmx+fine_2, tick_Y[i].p+fine_1, -mmx);
								
				texts[i].mesh.rotation.z= Math.PI*0.;						
				scene.add( texts[i].mesh );
			}
			
			var T_text_Shape = new THREE.BufferGeometry();		
			var T_text= new THREE.Mesh( T_text_Shape, texts[0].mat );					
			var T_text_size = W_y * .07;
			var fine_1= T_text_size * 4, fine_2=+W_y*0.0;
			T_text_Shape.fromGeometry( new THREE.ShapeGeometry( font.generateShapes( 'y(um)' , T_text_size, 1 ) ) );				    	
			T_text.position.set(mmx+fine_1, W_y*0.5 -mmx+fine_2, -mmx );
						
			scene.add(T_text );	
			T_text.rotation.z=Math.PI*0.5;
		}); //end load function
		
		//----------------------- z tick ---------------------------------------
		var N_tick_Z=4 ;

		var tick_Z     = [];
		var margin    = W_z * 0.05;
	
		for(var i=0;i<=N_tick_Z; i++){
			tick_Z[i] = {};
			tick_Z[i].geo = new THREE.Geometry(); 
			tick_Z[i].mat = new THREE.LineBasicMaterial( { color: 0x000000, linewidth: 3 } );
			tick_Z[i].mesh = new THREE.Line( tick_Z[i].geo, tick_Z[i].mat );
			tick_Z[i].p = Number((i*W_z/N_tick_Z-1).toFixed(2)) ;
			
			if( i === 0 ){
			tick_Z[i].geo.vertices.push( new THREE.Vector3(-mmx, -mmx        , i*(1)/N_tick_Z-mmx) ); 
			tick_Z[i].geo.vertices.push( new THREE.Vector3(-mmx, -mmx-margin, i*(1)/N_tick_Z-mmx) );
			}
			else if( i === N_tick_Z ){
				tick_Z[i].geo.vertices.push( new THREE.Vector3(-mmx, -mmx        , i*(1)/N_tick_Z-mmx) ); 
				tick_Z[i].geo.vertices.push( new THREE.Vector3(-mmx, -mmx-margin, i*(1)/N_tick_Z-mmx) ); 
			}
			else{
				tick_Z[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx        , i*(1)/N_tick_Z-mmx));
				tick_Z[i].geo.vertices.push(new THREE.Vector3( -mmx, -mmx-margin, i*(1)/N_tick_Z-mmx));
			}
			 
			scene.add( tick_Z[i].mesh );
		}

		var Label_Z = new THREE.FontLoader();
		
		Label_Z.load( '<%=request.getContextPath()%>/js/three/font/typeface.helvetiker_regular.js', function ( font ){
			var texts = [];
			
			for(var i=0;i<=N_tick_Z;i++) {
				texts[i] = {};
				texts[i].size = W_z * .05;
				var fine_1 = texts[i].size * 0.4, fine_2 = -W_y*0.2;
				texts[i].geo = new THREE.BufferGeometry();		 
				texts[i].geo.fromGeometry( new THREE.ShapeGeometry( font.generateShapes( (tick_Z[i].p*z_scal*1e6+47).toFixed(2)  , texts[i].size, 1 ) ) );
				texts[i].mat = new THREE.MeshBasicMaterial( {color: 0x000000,  side: THREE.DoubleSide	} );
				texts[i].mesh = new THREE.Mesh( texts[i].geo, texts[i].mat );					
				texts[i].mesh.position.set(-mmx, -mmx+fine_2, tick_Z[i].p+fine_1+0.5);
				texts[i].mesh.rotation.x= Math.PI*0.5;
				texts[i].mesh.rotation.y= Math.PI*0.5;
				scene.add( texts[i].mesh );
			}
			
			var T_text_Shape = new THREE.BufferGeometry();		
			var T_text= new THREE.Mesh( T_text_Shape, texts[0].mat );					
			var T_text_size = W_z * .07;
			var fine_1= T_text_size * 1, fine_2=-T_text_size * 11;
			T_text_Shape.fromGeometry( new THREE.ShapeGeometry( font.generateShapes( 'z(um)' , T_text_size, 1 ) ) );				    	
			T_text.position.set( -mmx, fine_2, W_z*0.5 -mmx +fine_1 );
			scene.add(T_text );	
			T_text.rotation.z = Math.PI*0.5;
			T_text.rotation.y = Math.PI*0.5;
			T_text.rotation.x =-Math.PI*0.5;
		}); //end load function
		
	}; // End of drawAxis()
			
	var scene = new THREE.Scene();
	scene.add( new THREE.AmbientLight(0x1c1c1c) );
	var spotLight = new THREE.SpotLight( 0xffffff );  
	spotLight.position.set( 5, 5, 5 );
	spotLight.castShadow = true;
	scene.add( spotLight );
	var spotLight2 = new THREE.SpotLight( 0xffffff );  
	spotLight2.position.set( -5, -5, -5 );
	spotLight2.castShadow = true;
	scene.add( spotLight2 );
	
	this.scene( scene );
	
	var camera = new THREE.PerspectiveCamera(45, scene_width / scene_height, 0.1, 1000);
	camera.up = new THREE.Vector3(0,0,1);
	camera.position.set(x_cam,y_cam,z_cam);
	camera.lookAt(new THREE.Vector3(0,0, 0));
	camera.rotation.z=Math.PI*0.5;
	this.camera( camera );
	
	var renderer = new THREE.WebGLRenderer();
	renderer.setClearColor( 0xffffff, 1 );
	renderer.setSize(scene_width, scene_height); 
	renderer.shadowMapEnabled = true;
	renderer.sortObjects = false;
	this.renderer( renderer );
	
	var plate = new Plate();
	var arrow = new THREE.LineSegments( 
							new THREE.Geometry(),
							new THREE.LineBasicMaterial( { color: 0x000000, opacity: 1, linewidth: 1 } ) );
	arrow.position.z=0;
	arrow.visible = false;
	plate.arrow( arrow );
	plate.vmode(0);
	this.addPlate( 'xy', plate );
	scene.add( arrow );
	
	plate = new Plate();
	arrow = new THREE.LineSegments( 
							new THREE.Geometry(),
							new THREE.LineBasicMaterial( { color: 0x000000, opacity: 1, linewidth: 1 } ) );
	arrow.position.x=0;
	arrow.visible = false;
	plate.arrow( arrow );
	plate.vmode(0);
	this.addPlate( 'yz', plate );
	scene.add( arrow );

	plate = new Plate();
	arrow = new THREE.LineSegments( 
							new THREE.Geometry(),
							new THREE.LineBasicMaterial( { color: 0x000000, opacity: 1, linewidth: 1 } ) );
	arrow.position.y=0;
	arrow.visible = false;
	plate.arrow( arrow );
	plate.vmode(0);
	this.addPlate( 'zx', plate );
	scene.add( arrow );
	
	
	var plateGeometry = new THREE.Geometry();
	var plateMaterial = new THREE.MeshBasicMaterial({
						    	vertexColors: THREE.VertexColors, 
						    	transparent:true , 
						    	opacity: 0.5,
						    	side:THREE.DoubleSide
						});
	var plateViewer = new THREE.Mesh( plateGeometry, plateMaterial );
	plateViewer.position.z = 0;
	// plateViewer.visible=false;
	plateViewer.visible=true;
	this.setPlateGeometry('xy', plateGeometry);
	this.setPlateMaterial('xy', plateMaterial);
	this.setPlateMesh('xy', plateViewer);
	
	//plateViewer
	scene.add( plateViewer );
	
	plateGeometry = new THREE.Geometry();
	plateMaterial = new THREE.MeshBasicMaterial({
						    	vertexColors: THREE.VertexColors, 
						    	transparent:true , 
						    	opacity: 0.5,
						    	side:THREE.DoubleSide
						});
	plateViewer = new THREE.Mesh( plateGeometry, plateMaterial );
	plateViewer.position.x = 0;
	plateViewer.visible=false;
	this.setPlateGeometry('yz', plateGeometry);
	this.setPlateMaterial('yz', plateMaterial);
	this.setPlateMesh('yz', plateViewer);
	scene.add( plateViewer );
	
	plateGeometry = new THREE.Geometry();
	plateMaterial = new THREE.MeshBasicMaterial({
						    	vertexColors: THREE.VertexColors, 
						    	transparent:true , 
						    	opacity: 0.5,
						    	side:THREE.DoubleSide
						});
	plateViewer = new THREE.Mesh( plateGeometry, plateMaterial );
	plateViewer.position.y = 0;
	plateViewer.visible=false;
	this.setPlateGeometry('zx', plateGeometry);
	this.setPlateMaterial('zx', plateMaterial);
	this.setPlateMesh('zx', plateViewer);
	scene.add( plateViewer );
	
	var newShape = new Shape('point_1');
	newShape.color(0x77ff77);
	this.addShape( 'point_1', newShape );
	
	newShape = new Shape('point_2');
	newShape.color(0x77ff77);
	newShape.radius(Math.sqrt(Norm_dx*Norm_dx + Norm_dy*Norm_dy + Norm_dz*Norm_dz)/3);
	this.addShape( 'point_2', newShape );
	newShape = new Shape('sphere');
	newShape.color(0xff7777);
	this.addShape( 'sphere', newShape );
	newShape = new Shape('cube');
	newShape.color(0x77ff77);
	this.addShape( 'cube', newShape );
	
	container.appendChild( renderer.domElement );
	
	renderer.render(scene, camera);
	
	this.drawAxis();
	
};


var canvas = new Canvas( document.getElementById("container") );

var trackballControl = new THREE.TrackballControls(canvas.camera(), canvas.renderer().domElement);
trackballControl.rotateSpeed = 1.0;
trackballControl.zoomSpeed = 1.0;
trackballControl.panSpeed = 0.0;

var animate = function(){
	trackballControl.update();
	canvas.renderer().render(canvas.scene(), canvas.camera() );
	requestAnimationFrame(animate); 
};

animate();

function displayObject( obj, x, y, z, r, o, force) 
{
	canvas.displayObject( obj, x, y, z, r, o, force);
}

function moveEPObject( obj, x, y, z ) 
{
	canvas.moveObject( obj, x, y, z );
}

function changeEPTransparency( shape, o ) 
{
	canvas.changeEPTransparency( shape, o );
}

function obj_position(shape, axis, position) 
{
	var object = canvas.getShape( shape );
	object.setMeshPosition( axis, position*um/x_scal ) ;  
}

function displayVolume(shape, x, y, z, o, r) 
{
	canvas.displayVolume( shape, x, y, z, o, r );
}

function obj_tranp(shape, opacity) 
{
	var object = canvas.getShape( shape );
	var material = object.material();
	material.opacity = opacity; 
}

function loadEPData( data ){
	canvas.loadData( data );
}

function toggleView( plateId ){
	canvas.toggleView( plateId );
}

function toggleEquillibrium( plateId ){
	canvas.toggleEquillibrium( plateId );
}

function toggleTerrain( plateId ){
	canvas.toggleTerrain( plateId );
}

function toggleArrows( plateId ){
	canvas.toggleArrows( plateId );
}

function movePlate( plateId ){
	canvas.movePlate( plateId );
}

function changeOpacity( plateId ){
	canvas.changeOpacity( plateId );
}
</script>

</body>
</html>