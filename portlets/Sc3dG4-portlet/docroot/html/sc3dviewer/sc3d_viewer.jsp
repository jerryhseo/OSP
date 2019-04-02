<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html style="height:95%">

<head>
	<script src="<%= request.getContextPath() %>/js/three/libs/Three_R86.js" type="text/javascript"></script>	
	<script src="<%= request.getContextPath() %>/js/three/libs/ThreeBSP.js" type="text/javascript"></script>	
	<script src="<%= request.getContextPath() %>/js/three/libs/TrackballControls_R68.js" type="text/javascript"></script>
	<!--
	<script src="<%= request.getContextPath() %>/js/three/font/helvetiker_regular.typeface.js" type="text/javascript"></script>
	 -->

<!-- JQuery
<script src="<%= request.getContextPath() %>/js/jquery/jquery-2.2.3.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery/jquery.blockUI.js"></script>

<link href="<%= request.getContextPath() %>/js/jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css">
<link href="<%= request.getContextPath() %>/js/jquery/bootstrap-toggle.min.css" rel="stylesheet">
<script src="<%= request.getContextPath() %>/js/jquery/bootstrap-toggle.min.js"></script>
-->
<!-- bootstrap
<link href="<%= request.getContextPath() %>/js/jquery/bootstrap.min.css" rel="stylesheet">
<script src="<%= request.getContextPath() %>/js/jquery/bootstrap.min.js"></script>
-->

<style>
	body{ background-color: rgb(255,255,255); }
	canvas{ background-color: white; float: left; }
</style>

</head>

<body style="height:100%; width:100%;">
<div id="sizeDiv">

</div>

<script type="text/javascript">


var namespace;
var imagePath = parent.atomTransitorAnalyzerImagePath;

var L_channel;
var L_gate ;
var L_source ;
var L_drain ;
var T_channel;
var W_channel;
var T0_oxide, T1_oxide, T2_oxide, T3_oxide ;
var Gate_Type;

var Dop_source , DopType_source ;
var Dop_drain , DopType_drain ;
var Dop_channel, DopType_channel;

var Color = new Array();
var Opacity = new Array();
var Transparent = new Array();


var scene_width =700;
var scene_height=700;


var scene = new THREE.Scene();
var renderer = new THREE.WebGLRenderer();
renderer.setClearColor( 0xffffff, 1 );
renderer.setSize(scene_width, scene_height);
renderer.shadowMapEnabled = true;
renderer.sortObjects = false;

var raycaster = new THREE.Raycaster();

var DeviceFrame = new THREE.Object3D();

var Device = new THREE.Object3D();

var camera = new THREE.PerspectiveCamera(45, scene_width / scene_height, 0.1, 1000);

//var camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 1, 10000 );

var ambientLight = new THREE.AmbientLight(0x1c1c1c); scene.add(ambientLight);

var pointLight = new Array();

for (var i=0; i<4; i++)
{
	pointLight[i] = new THREE.PointLight( 0xffffff );
	pointLight[i].castShadow = true;
	scene.add( pointLight[i] );
}

var mouse = new THREE.Vector2(), INTERSECTED;

var Nx, Ny, Nz;

var Nyz;

var xmx, ymx, zmx;
var xmn, ymn, zmn;

var V_mx, V_mn;
var n_mx, n_mn;
var p_mx, p_mn;

var Pallet_V =new Array(11);
var Pallet_n =new Array(11);
var Pallet_p =new Array(11);

var Dxx, Dyy, Dzz;

var um = 1e-6;

var N_t=1 ;

var ON_save_V=0;
var ON_save_R=0;


var t_i=0;
var Vertices = {};

var Species_type ;
var Species_Number ;

var iid;
var insec_name;

var Extend = new Array(3);

var LatticeVector = new Array(3);

var nonz_i = new Array();

var text_Atom_Type;
var text_Atom_Name ;
var text_x ;
var text_y ;
var text_z ;

var text_Addition_atom_x;
var text_Addition_atom_y;
var text_Addition_atom_z;

var text_Addition_atom_Name;

var text_replace_Atom;

var struc_string;

var N_Addition_max =50;

var N_Atoms_max =500;
var N_index_max =1000

var camera_Vscale=1;

//var Nx, Ny,Nz;


var R_col, G_col, B_col;

var Atom_R = [ ];

for (var i=1; i<=118; i++) Atom_R[i]=i*0.02+0.2;

var Is_N_spe = new Array(119);
for ( var i=0; i<119; i++ ) Is_N_spe[i]=0;

var Addition_Atom_i;

var addition_x;
var addition_y;
var addition_z;

var Draw_VR ;

var xi, yi, zi;

var x_cam=10.5, y_cam=-10.5, z_cam=10.5;

var Grad = [];
var Rhos = {};

var ang = Math.PI*15/180;

var canvas ;

var trackballControl ;

var container_elem;

var plateViewers = new Array(3);

var arrow_plates = new Array(3);

var N_Range=150;

var Plate = function() {
	this.mesh = function( mesh ) {
		if ( mesh ) this.Mesh = mesh;
		else return this.Mesh;
	};
	this.arrow = function( arrow ) {
		if ( arrow ) this.Arrow = arrow;
		else return this.Arrow;
	};
	this.geometry = function( geometry ) {
		if ( geometry ) this.Geometry = geometry;
		else return this.Geometry;
	};
	this.material = function( material ) {
		if ( material ) this.Material = material;
		else return this.Material;
	};
	this.vmode = function( mode ) {
		if ( arguments.length === 1 ) this.VMode = mode;
		else return this.VMode;
	};
	this.visibleArrow = function( visible ) {
		var arrow = this.arrow();
		if ( arguments.length === 1 )
		{
			arrow.visible = visible;
			if ( visible ) {
				arrow.geometry.verticesNeedUpdate = true;
			}
		}
		else return arrow.visible;
	};
};

var Canvas = function( container )
{
	var ti=0;

	// From --- middle -----

	this.plates = function( plates ) {
		if ( plates ) this.Plates = plates;
		else return this.Plates;
	};
	this.addPlate = function( id, plate ) {
		var plates = this.plates();
		if ( !plates ) {
			plates = {};
			this.plates(plates);
		}

		plates[id] = plate;
	};
	this.getPlate = function( id ) {
		var plates = this.plates();
		if ( !plates ) return false;

		return plates[id];
	};
	this.setPlateArrow = function( id, arrow ) {
		var plate = this.getPlate(id);

		plate.arrow( plate );
	};
	this.setPlateMesh = function( id, mesh ) {
		var plate = this.getPlate(id);

		plate.mesh( mesh );
	};
	this.setPlateGeometry = function( id, geometry ) {
		var plate = this.getPlate(id);

		plate.geometry( geometry );
	};
	this.setPlateMaterial = function( id, material ) {
		var plate = this.getPlate(id);

		plate.material( material );
	};
	this.getPlateArrow = function( id ) {
		var plate = this.getPlate(id);

		return plate.arrow();
	};
	this.getPlateMesh= function( id ) {
		var plate = this.getPlate(id);

		return plate.mesh();

	};
	this.getPlateGeometry = function( id ) {
		var plate = this.getPlate(id);

		return plate.geometry();
	};
	this.getPlateMaterial = function( id ) {
		var plate = this.getPlate(id);

		return plate.material();
	};



	container.appendChild( renderer.domElement );

	renderer.render(scene, camera);

};

var animate = function() {
	trackballControl.update();
	renderer.render(scene, camera );
	requestAnimationFrame(animate);

	};


function loadEPData( data ) {

	removeAllObjects();

var fin_i=0;

	var lines = data.split('\n');

	for ( var index=0; index<lines.length; index++ ) {
		var line = lines[index].trim();
		if ( !line ) continue;

		if ( line[0] === '@') {
			var line = line.replace('@', '');
			var keyVal = line.split(' ');
			var start = index+1;
			var end = start + Number(keyVal[1]);
			index = end-1;
			switch( keyVal[0].trim()) {

				case 'P_x': parse_Px( lines.slice(start, end)); break;
				case 'P_y': parse_Py( lines.slice(start, end)); break;
				case 'P_z': parse_Pz( lines.slice(start, end)); break;

				case 'EP_data': parseEPData( lines.slice(start, end)); break;

				//case 'EF_data':	parseEFData( lines.slice(start, end));	break;
				case 'Rho_data': parseRhoData( lines.slice(start, end)); break;
				case 'params' : parseParams( lines.slice(start, end)); break;
				default: alert( 'File type mismatch......');
			}
		}

	}

	xi=1; yi=1; zi=1;

	Write_ouput();


	Draw_VR = document.getElementsByName('Draw_VR');

	text_Atom_Type = document.getElementById("Atom_Type");
	text_Atom_Name = document.getElementById("Atom_Name");
	text_x = document.getElementById("position_x");
	text_y = document.getElementById("position_y");
	text_z = document.getElementById("position_z");

container_elem=document.getElementById("container");

	canvas = new Canvas( container_elem );

	pointLight[0].position.set( 3*xmx, 3*ymx, 3*zmx );
	pointLight[1].position.set(-3*xmx, -3*ymx, -3*zmx);

	trackballControl = new THREE.TrackballControls(camera, renderer.domElement);

	trackballControl.target = new THREE.Vector3((Dxx)*0.5, (Dyy)*0.5, (Dzz)*0.5);
	//trackballControl.target = new THREE.Vector3(15, 0, 10);

	trackballControl.rotateSpeed = 1.0;
	trackballControl.zoomSpeed = 1.0;
	trackballControl.panSpeed = 1.0;

	camera.up = new THREE.Vector3(0.0,0.0,1.0);

	camera.position.set(camera_Vscale*(Dyy+Dzz)+Dxx*0.5, camera_Vscale*(Dzz+Dxx)+Dyy*0.5, camera_Vscale*(Dxx+Dyy)+Dzz*0.5);
	//camera.position.set(0.0, camera_Vscale*(Dzz+Dxx)+Dyy*0.5, camera_Vscale*(Dxx+Dyy)+Dzz*0.5);

	var plate = new Plate();
	arrow_plates[0] = new THREE.LineSegments( new THREE.Geometry(), new THREE.LineBasicMaterial( { color: 0x000000, opacity: 1, linewidth: 1 } ) );
	arrow_plates[0].position.z=0;
	arrow_plates[0].visible = false;

	plate.arrow( arrow_plates[0] );
	plate.vmode(0);
	canvas.addPlate( 'xy', plate );
	scene.add( arrow_plates[0] );

	plate = new Plate();
	arrow_plates[1] = new THREE.LineSegments( new THREE.Geometry(), new THREE.LineBasicMaterial( { color: 0x000000, opacity: 1, linewidth: 1 } ) );
	arrow_plates[1].position.x=0;
	arrow_plates[1].visible = false;
	plate.arrow( arrow_plates[1] );
	plate.vmode(0);
	canvas.addPlate( 'yz', plate );
	scene.add( arrow_plates[1] );

	plate = new Plate();
	arrow_plates[2] = new THREE.LineSegments( new THREE.Geometry(), new THREE.LineBasicMaterial( { color: 0x000000, opacity: 1, linewidth: 1 } ) );
	arrow_plates[2].position.y=0;
	arrow_plates[2].visible = false;
	plate.arrow( arrow_plates[2] );
	plate.vmode(0);
	canvas.addPlate( 'zx', plate );
	scene.add( arrow_plates[2] );

	var plateGeometry = new THREE.Geometry();
	var plateMaterial = new THREE.MeshBasicMaterial({ vertexColors: THREE.VertexColors, transparent:true , opacity: 0.5, side:THREE.DoubleSide });
	plateViewers[0] = new THREE.Mesh( plateGeometry, plateMaterial );
	plateViewers[0].position.z = 0;

	plateViewers[0].visible=false;
	canvas.setPlateGeometry('xy', plateGeometry);
	canvas.setPlateMaterial('xy', plateMaterial);
	canvas.setPlateMesh('xy', plateViewers[0]);

	scene.add( plateViewers[0] );

	plateGeometry = new THREE.Geometry();
	plateMaterial = new THREE.MeshBasicMaterial({ vertexColors: THREE.VertexColors, transparent:true , opacity: 0.5, side:THREE.DoubleSide });
	plateViewers[1] = new THREE.Mesh( plateGeometry, plateMaterial );
	plateViewers[1].position.x = 0;
	plateViewers[1].visible=false;
	canvas.setPlateGeometry('yz', plateGeometry);
	canvas.setPlateMaterial('yz', plateMaterial);
	canvas.setPlateMesh('yz', plateViewers[1]);
	scene.add( plateViewers[1] );

	plateGeometry = new THREE.Geometry();
	plateMaterial = new THREE.MeshBasicMaterial({ vertexColors: THREE.VertexColors, transparent:true , opacity: 0.5, side:THREE.DoubleSide });
	plateViewers[2] = new THREE.Mesh( plateGeometry, plateMaterial );
	plateViewers[2].position.y = 0;
	plateViewers[2].visible=false;
	canvas.setPlateGeometry('zx', plateGeometry);
	canvas.setPlateMaterial('zx', plateMaterial);
	canvas.setPlateMesh('zx', plateViewers[2]);
	scene.add( plateViewers[2] );

	setViewerShape('xy');
	setViewerShape('yz');
	setViewerShape('zx');

	setViewerFaces();

	setArrows('xy');
	setArrows('yz');
	setArrows('zx');

	document.removeEventListener( 'click', onDocumentMouseMove, false );
	document.addEventListener( 'mousemove', onDocumentMouseMove, false );


	window.addEventListener( 'resize', onWindowResize, false );
	
	//window.addEventListener( 'resize', onWindowResize, false );

	Draw_DeviceFrame();

	animate();
}

function load_Struc(data)
{

	removeAllObjects();



	Write_input();

container_elem=document.getElementById("container");

	canvas = new Canvas( container_elem );

	pointLight[0].position.set( 100, 100, 100);
	pointLight[1].position.set(-100, -100, -100);
	pointLight[2].position.set(-100, 100, 100);
	pointLight[3].position.set(-100, -100, 100);

	trackballControl = new THREE.TrackballControls(camera, renderer.domElement);

	trackballControl.target = new THREE.Vector3(0.0, 0.0, 0.0);

	trackballControl.rotateSpeed = 1.0;
	trackballControl.zoomSpeed = 1.0;
	trackballControl.panSpeed = 1.0;

	camera.up = new THREE.Vector3(0.0, 0.0, 1.0);
	camera.rotation.z = Math.PI*0.5;

	//camera.position.set(-camera_Vscale*(ymx+zmx)-xmx*0.5, camera_Vscale*(zmx+xmx)+ymx*0.5, camera_Vscale*(xmx+ymx)+zmx*0.5);
	camera.position.set(0, -60, 0);

	Draw_Device(data); //scene.add( Atoms );
	
	window.addEventListener( 'resize', onWindowResize, false );

	animate();

}

function load_Struc_file(data)
{
	removeAllObjects();



	Write_input();

container_elem=document.getElementById("container");

	canvas = new Canvas( container_elem );

	pointLight[0].position.set( 100, 100, 100);
	pointLight[1].position.set(-100, -100, -100);
	pointLight[2].position.set(-100, 100, 100);
	pointLight[3].position.set(-100, -100, 100);

	trackballControl = new THREE.TrackballControls(camera, renderer.domElement);

	trackballControl.target = new THREE.Vector3(0.0, 0.0, 0.0);

	trackballControl.rotateSpeed = 1.0;
	trackballControl.zoomSpeed = 1.0;
	trackballControl.panSpeed = 1.0;

	camera.up = new THREE.Vector3(0.0, 0.0, 1.0);
	camera.rotation.z = Math.PI*0.5;

	//camera.position.set(-camera_Vscale*(ymx+zmx)-xmx*0.5, camera_Vscale*(zmx+xmx)+ymx*0.5, camera_Vscale*(xmx+ymx)+zmx*0.5);
	camera.position.set(0, -60, 0);

	Draw_Device_file(data); //scene.add( Atoms );
	
	window.addEventListener( 'resize', onWindowResize, false );

	animate();
}

function toggleView( plateId ) {
	setPlateMode('xy',0);
	setPlateMode('yz',0);
	setPlateMode('zx',0);

	var checkInput = document.getElementById('P'+plateId+'_visible');

	var mesh = canvas.getPlateMesh(plateId);
	//var mesh = canvas.getPlate.mesh(plateId)
	//scene.remove( Link_Group );
	//scene.add( Link_Group );

	if ( checkInput.checked ) {

		mesh.visible=true;
		mesh.geometry.elementsNeedUpdate = true;
	}
	else {
		mesh.visible = false;
	}
}


function toggleTerrain( plateId ) {

	var plate = canvas.getPlate( plateId );
	var vmodeInput = document.getElementById('P'+plateId+'_mode');
	var arrowInput = document.getElementById('P'+plateId+'_arr_visible');

	if ( vmodeInput.checked ) {
		plate.vmode( 1 );
	}
	else {
		plate.vmode( 0 );
		if ( arrowInput.checked )
			plate.visibleArrow(true);
	}

	setViewerShape( plateId );

	var mesh = plate.mesh();
	mesh.geometry.elementsNeedUpdate = true;

}

function toggleArrows( plateId ) {

	var checkInput = document.getElementById('P'+plateId+'_arr_visible');

	var plate = canvas.getPlate( plateId );
	plate.visibleArrow(checkInput.checked);

}

function movePlate( plateId ) {
	var valueInput = document.getElementById('P'+plateId+'Range');

	var V_mode = 0;

	var posInput = 'P'+plateId+'Input1';
	switch( plateId ) {
		case 'xy':
			zi = valueInput.value;

			if (Draw_VR[0].checked) { document.getElementById(posInput).value = Vertices['0'][zi-1].z; }
			if (Draw_VR[1].checked) { document.getElementById(posInput).value = Rhos['0'][zi-1].z; }

		//	alert(zi+" "+Vertices['0'][zi-1].z);

			break;
		case 'yz':
			xi = valueInput.value;

			if (Draw_VR[0].checked) { document.getElementById(posInput).value = Vertices['0'][Nz*Ny*(xi-1)].x; }
			if (Draw_VR[1].checked) { document.getElementById(posInput).value = Rhos['0'][Nz*Ny*(xi-1)].x; }

			break;
		case 'zx':
			yi = valueInput.value;

			if (Draw_VR[0].checked) { document.getElementById(posInput).value = Vertices['0'][Nz*(yi-1)].y; }
			if (Draw_VR[1].checked) { document.getElementById(posInput).value = Rhos['0'][Nz*(yi-1)].y; }


			break;
	}

	setViewerShape(plateId);
	setPlateMode(plateId, V_mode);

	var plateMesh = canvas.getPlateMesh(plateId);
	plateMesh.geometry.elementsNeedUpdate = true;

	setArrows(plateId);
}



function ON_MouseMove(){ document.addEventListener( 'mousemove', onDocumentMouseMove, false );}

function OFF_MouseMove(){ document.removeEventListener( 'mousemove', onDocumentMouseMove, false ); }

function changeOpacity( plateId ) {

	var plateMat = canvas.getPlateMaterial( plateId );
	plateMat.opacity = document.getElementById('P'+plateId+'Opacity').value;
}

function setPlateMode( plateId, mode )
{
	var plate = canvas.getPlate(plateId);

	var ord_XYP=0;
	var ord_YZP=0;
	var ord_ZXP=0;

	var ord_data=0;

	var f = 0;

	var axisMax = {};
	switch( plateId ) {
		case 'xy':
			axisMax.i = Nx-1;
			axisMax.j = Ny-1;
			axisMax.ord = function( i, j ){ return (zi-1) + Nz*(j -1) + Nz*Ny*(i -1);}
			axisMax.vf = [ [0 , Nz, Nz*Ny ], [Nz*Ny, Nz, Nz*Ny+Nz] ];
			break;
		case 'yz':
			axisMax.i = Ny-1;
			axisMax.j = Nz-1;
			axisMax.ord = function( i, j ){ return (j -1) + Nz*(i -1) + Nz*Ny*(xi-1); }
			axisMax.vf = [ [ 0, 1, Nz ], [Nz, 1, Nz+1] ];
			break;
		case 'zx':
			axisMax.i = Nz-1;
			axisMax.j = Nx-1;
			axisMax.ord = function( i, j ){ return (i -1) + Nz*(yi-1) + Nz*Ny*(j -1); }
			axisMax.vf = [ [0, Nz*Ny, 1 ], [1, Nz*Ny, 1+ Nz*Ny] ];
			break;
	}

	if (Draw_VR[0].checked) { var vertices = Vertices['0']; }
	if (Draw_VR[1].checked) { var vertices = Rhos['0']; }

	//var vertices = Vertices['0'];

	var geometry = plate.geometry();

	var iMax = axisMax.i;
	var jMax = axisMax.j;
	switch(mode) {
		case 0:
			for (var i=1; i<=iMax; i++) {
				for (var j=1; j<=jMax; j++) {
					var ord = axisMax.ord(i, j);

					for (var k=0; k<=1; k++)
					{
						var vf = axisMax.vf[k];
						for ( var kk=0; kk <=2; kk++ ) {
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

	}
};
function setArrows( plateId )
{
		var plate = canvas.getPlate( plateId );
		var i_arr=0;
		var arrow_L = 0.5;
		var s_L=0.4;
		var expan=2;

		var arrow = canvas.getPlateArrow( plateId );
		var geometry = arrow.geometry;

		var outMax, inMax, order;

		var vertices = Vertices['0'];

		switch( plateId ) {
			case 'xy':
				outMax = Nx;
				inMax = Ny;
				order = function(i, j){ return (zi-1) + Nz*j + Nz*Ny*i ; };

				for (var i=0; i<outMax; i=i+expan) {
					for (var j=0; j<inMax; j=j+expan) {
						var ord = order(i, j);

						var x0=vertices[ord].x - Grad[ord].x*arrow_L*expan;
						var y0=vertices[ord].y - Grad[ord].y*arrow_L*expan;
						var x1=vertices[ord].x + Grad[ord].x*arrow_L*expan;
						var y1=vertices[ord].y + Grad[ord].y*arrow_L*expan;

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
				order = function(j, k){ return k + Nz*j + Nz*Ny*(xi-1) ; };

				for (var j=0; j<outMax; j=j+expan)
				{
				  for (var k=0; k<inMax; k=k+expan)
				  {
					  var ord = order(j,k);

					//  console.log("ord=", ord);

						 var y0=vertices[ord].y- Grad[ord].y*arrow_L*expan;
						 var z0=vertices[ord].z- Grad[ord].z*arrow_L*expan;
						 var y1=vertices[ord].y+ Grad[ord].y*arrow_L*expan;
						 var z1=vertices[ord].z+ Grad[ord].z*arrow_L*expan;

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
				order = function(k, i){ return k + Nz*(yi-1) + Nz*Ny*i ; };

				for (var k=0; k<outMax ; k=k+expan)
				   {
					for (var i=0; i<inMax ; i=i+expan)
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

		if ( arrow.visible )
			geometry.verticesNeedUpdate = true;
	};


	function setViewerShape ( plateId )
	{
		var outMax, inMax, order, xyzp;
		switch( plateId ) {
			case 'xy':
				outMax = Nx;
				inMax = Ny;
				order = function(i, j){ return (zi-1) + Nz*(j-1) + Nz*Ny*(i-1); };
				xyzp = function( i, j ){ return (j-1 ) + Ny*(i-1); };
				break;
			case 'yz':
				outMax = Ny;
				inMax = Nz;
				order = function(j, k){ return (k-1) + Nz*(j-1) + Nz*Ny*(xi-1); };
				xyzp = function( j, k ){ return (k-1) + Nz*(j-1); };
				break;
			case 'zx':
				outMax = Nz;
				inMax = Nx;
				order = function(k, i){ return (k-1) + Nz*(yi-1) + Nz*Ny*(i-1); };
				xyzp = function( k, i ){ return (i-1) + Nx*(k-1); };
				break;
		}

		var plate = canvas.getPlate( plateId );
		var geometry = plate.geometry();


//		for (var i = 0; i < rates.length; i++) {

//		    if (rates[i].checked) {

	//	        rate_value = rates[i].value;
		//    }

	//	}
		if (Draw_VR[0].checked) { var vertices = Vertices['0']; }
		if (Draw_VR[1].checked) { var vertices = Rhos['0']; }

		for (var i=1; i<=outMax ; i++) {
			for (var j=1; j<=inMax ; j++) {
				var ord_data = order( i, j );
				var ord_XYP = xyzp( i, j );

				geometry.vertices[ord_XYP]=
					new THREE.Vector3(
					                  vertices[ord_data].x + (plateId=="yz"? 1:0)* vertices[ord_data].p*plate.vmode(),
					                  vertices[ord_data].y - (plateId=="zx"? 1:0)* vertices[ord_data].p*plate.vmode(),
					                  vertices[ord_data].z + (plateId=="xy"? 1:0)* vertices[ord_data].p*plate.vmode() ) ;
			}
		}
	};

//---------------------------------------------------------------------------------------------------------
	function setViewerFaces () {
		var geoXY = canvas.getPlateGeometry('xy');
		var geoYZ = canvas.getPlateGeometry('yz');
		var geoZX = canvas.getPlateGeometry('zx');

		for (var i=1; i<=Nx-1; i++) {
			for (var j=1; j<=Ny-1; j++) {
				var ord_XYP = (j-1) + Ny*(i-1);
//				geoXY.faces.push( new THREE.Face3(ord_XYP + Ny  , ord_XYP+1, ord_XYP     ) );
	//			geoXY.faces.push( new THREE.Face3(ord_XYP + Ny+1, ord_XYP+1, ord_XYP + Ny) );

				geoXY.faces.push( new THREE.Face3(ord_XYP , ord_XYP+1, ord_XYP + Ny ) );
				geoXY.faces.push( new THREE.Face3(ord_XYP + Ny, ord_XYP+1, ord_XYP + Ny+1) );

			}
		}

		    //--------------------------------------- YZ �� ---------------------------

		for (var j=1; j<=Ny-1; j++) {
			for (var k=1; k<=Nz-1; k++) {
				var ord_YZP = (k-1) + Nz*(j-1);

//				geoYZ.faces.push( new THREE.Face3(ord_YZP     , ord_YZP + Nz  , ord_YZP+1  ) );
	//			geoYZ.faces.push( new THREE.Face3(ord_YZP + Nz, ord_YZP + Nz+1, ord_YZP+1) );

				geoYZ.faces.push( new THREE.Face3(ord_YZP , ord_YZP + 1 , ord_YZP+Nz ) );
				geoYZ.faces.push( new THREE.Face3(ord_YZP + Nz, ord_YZP + 1, ord_YZP+Nz+1) );

			}
		}

		    //--------------------------------------- ZX �� ---------------------------

		for (var k=1; k<=Nz-1; k++) {
			for (var i=1; i<=Nx-1; i++) {
				var ord_ZXP = (i-1) + Nx*(k-1);

				geoZX.faces.push( new THREE.Face3(ord_ZXP , ord_ZXP+1, ord_ZXP + Nx ) );
				geoZX.faces.push( new THREE.Face3(ord_ZXP + Nx, ord_ZXP+1, ord_ZXP + Nx+1) );
			}
		}
	};


function parse_Px ( dataLines ) {

		for ( var i=0; i<dataLines.length; i++ )
		{

			x_g[i]=Number(dataLines[i]);

		}

	};
	function parse_Py ( dataLines ) {

		for ( var i=0; i<dataLines.length; i++ )
		{
			y_g[i]=Number(dataLines[i]);
		}

	};
	function parse_Pz ( dataLines ) {

		for ( var i=0; i<dataLines.length; i++ )
		{
			z_g[i]=Number(dataLines[i]);
		}

	};

	function parseEPData ( dataLines ) {

if (ON_save_V===0) return;

		var time = '0';
		var xi_L, yi_L, zi_L, md;
		var c1=0.25, c2=0.50, c3=0.75;
		var R_col, G_col, B_col;
		var func= new Array(N_nod);

		Vertices[time] = [];

		for ( var i=0; i<dataLines.length; i++ ) {
			var line = dataLines[i];
		//	var values = line.split(',');

			xi_L = parseInt(i/Nyz); md = parseInt(i%Nyz) ;
			yi_L = parseInt(md/Nz);
			zi_L = parseInt(md%Nz);

			func[i]=parseFloat(line);

			//if (i==6) alert(func[i]);

			if (func[i]>c3 && func[i]<=1.0) { R_col= 1.0 ; G_col= (1.0-func[i])/(1.0-c3) ; B_col= 0.0 ; }
	   else if (func[i]>c2 && func[i]<=c3 ) { R_col= (func[i]-c2)/(c3-c2) ; G_col= 1.0 ; B_col= 0.0 ; }
	   else if (func[i]>c1 && func[i]<=c2 ) { R_col= 0.0 ; G_col= 1.0 ; B_col= (c2-func[i])/(c2-c1) ; }
	   else if (func[i]>=0 && func[i]<=c1 ) { R_col= 0.0 ; G_col= (func[i]-0.0)/(c1-0.0) ; B_col= 1.0 ; }

			var vertex = {};
			vertex.x = x_g[xi_L];
			vertex.y = y_g[yi_L];
			vertex.z = z_g[zi_L];
			vertex.cr = R_col;
			vertex.cg = G_col;
			vertex.cb = B_col;
			vertex.p = func[i];

			Vertices[time].push( vertex );

		}

	//	alert(Vertices['0'][5].cr+":"+Vertices['0'][5].cg+":"+Vertices['0'][5].cb+":"+Vertices['0'][5].p);

		//--------------- to get E field  --------------

		var ord = new Array(Nx);

		for (var i=0; i<Nx; i++) ord[i] = new Array(Ny);
		for (var i=0; i<Nx; i++)
		for (var j=0; j<Ny; j++) ord[i][j] = new Array(Nz);

		var grad_fx = new Array(N_nod);
		var grad_fy = new Array(N_nod);
		var grad_fz = new Array(N_nod);

		var grad_fx_mx=-1e+100, grad_fy_mx=-1e+100, grad_fz_mx=-1e+100;
		var grad_fx_mn= 1e+100, grad_fy_mn= 1e+100, grad_fz_mn= 1e+100;

		var dx_L, dy_L, dz_L;

		var dummy;

		for (var i=0;i<N_nod;i++)
		{
			xi_L = parseInt(i / Nyz) ; dummy = parseInt(i % Nyz);
			yi_L = parseInt(dummy / Nz) ;
			zi_L = parseInt(dummy % Nz) ;
			ord[xi_L][yi_L][zi_L] = zi_L + Nz*yi_L + Nz*Ny*xi_L ;
		}

		for (var i=0;i<N_nod;i++)
		{
			xi_L = parseInt(i / Nyz) ; dummy = parseInt(i % Nyz);
			yi_L = parseInt(dummy / Nz) ;
			zi_L = parseInt(dummy % Nz) ;

			dx_L = x_g[xi_L+1] - x_g[xi_L-1];
			dy_L = y_g[yi_L+1] - y_g[yi_L-1];
			dz_L = z_g[zi_L+1] - z_g[zi_L-1];

	     	if (xi_L==0 || xi_L==Nx-1) grad_fx[i] = 0; else grad_fx[i] = ( func[ord[xi_L+1][yi_L ][zi_L ]] - func[ord[xi_L-1][yi_L ][zi_L ]] ) / dx_L;
			if (yi_L==0 || yi_L==Ny-1) grad_fy[i] = 0; else grad_fy[i] = ( func[ord[xi_L ][yi_L+1][zi_L ]] - func[ord[xi_L ][yi_L-1][zi_L ]] ) / dy_L;
			if (zi_L==0 || zi_L==Nz-1) grad_fz[i] = 0; else grad_fz[i] = ( func[ord[xi_L ][yi_L ][zi_L+1]] - func[ord[xi_L ][yi_L ][zi_L-1]] ) / dz_L;

	    	if (grad_fx[i]> grad_fx_mx) grad_fx_mx = grad_fx[i]; if (grad_fx[i]< grad_fx_mn) grad_fx_mn = grad_fx[i];
			if (grad_fy[i]> grad_fy_mx) grad_fy_mx = grad_fy[i]; if (grad_fy[i]< grad_fy_mn) grad_fy_mn = grad_fy[i];
			if (grad_fz[i]> grad_fz_mx) grad_fz_mx = grad_fz[i]; if (grad_fz[i]< grad_fz_mn) grad_fz_mn = grad_fz[i];

		//	if (i==9605) alert(xi_L+" "+yi_L+" "+zi_L+" "+(func[ord[xi_L+1][yi_L][zi_L]]-func[ord[xi_L-1][yi_L][zi_L]])+" "+(func[ord[xi_L][yi_L+1][zi_L]]- func[ord[xi_L][yi_L-1][zi_L]]) +" "+ (func[ord[xi_L][yi_L][zi_L+1]]-func[ord[xi_L][yi_L][zi_L-1]]));
			//if (i==9605) alert(dx_L+" "+dy_L+" "+dz_L );
			//if (i==9605) alert(grad_fx[i]+" "+grad_fy[i]+" "+grad_fz[i] );

		}

		var diffx_mx = grad_fx_mx - grad_fx_mn;
		var diffy_mx = grad_fy_mx - grad_fy_mn;
		var diffz_mx = grad_fz_mx - grad_fz_mn;

		var diff_mx;

		if (diffx_mx < diffy_mx && diffx_mx < diffz_mx) diff_mx = diffx_mx
		if (diffy_mx < diffz_mx && diffy_mx < diffx_mx) diff_mx = diffy_mx
		if (diffz_mx < diffx_mx && diffz_mx < diffy_mx) diff_mx = diffz_mx

		var grad_lim=6.0;

		var Norm_grad_x, Norm_grad_y, Norm_grad_z;

		var Norm_dx, Norm_dy, Norm_dz ;

		var Norm_EF;
		var Co_Grad;

		for (i=0;i<N_nod;i++)
		{
			xi_L = parseInt(i / Nyz) ; dummy = parseInt(i % Nyz);
			yi_L = parseInt(dummy / Nz) ;
			zi_L = parseInt(dummy % Nz) ;

			Norm_dx = (x_g[xi_L+1] - x_g[xi_L-1])/(xmx-xmn) ;
			Norm_dy = (y_g[yi_L+1] - y_g[yi_L-1])/(ymx-ymn) ;
			Norm_dz = (z_g[zi_L+1] - z_g[zi_L-1])/(zmx-zmn) ;

			Norm_EF = Math.sqrt(grad_fx[i]*grad_fx[i]+grad_fy[i]*grad_fy[i]+grad_fz[i]*grad_fz[i]);

			Co_Grad = Math.log10(Norm_EF+10)*5;

			Norm_grad_x = -grad_fx[i]*Co_Grad ; //if (Math.abs(Norm_grad_x)>grad_lim*Norm_dx) Norm_grad_x = Norm_grad_x/Math.abs(Norm_grad_x)*grad_lim*Norm_dx ;
			Norm_grad_y = -grad_fy[i]*Co_Grad ; //if (Math.abs(Norm_grad_y)>grad_lim*Norm_dx) Norm_grad_y = Norm_grad_y/Math.abs(Norm_grad_y)*grad_lim*Norm_dx ;
			Norm_grad_z = -grad_fz[i]*Co_Grad ; //if (Math.abs(Norm_grad_z)>grad_lim*Norm_dx) Norm_grad_z = Norm_grad_z/Math.abs(Norm_grad_z)*grad_lim*Norm_dx ;

			var xyz = {
				x: Norm_grad_x,
				y: Norm_grad_y,
				z: Norm_grad_z
			};
			Grad.push( xyz );

	//		if (i==9605) alert(Norm_grad_x+" "+Norm_grad_y+" "+Norm_grad_z);


			//if (i==6) alert(Norm_grad_x+" "+ Norm_grad_y+" "+ Norm_grad_z);
		}


	};

	 function parseRhoData ( dataLines ) {

		 if (ON_save_R===0) return;

			var time = '0';
			var xi_L, yi_L, zi_L, md;
			var c1=0.25, c2=0.50, c3=0.75;
			var R_col, G_col, B_col;
			var func;

			Rhos[time] = [];

			for ( var i=0; i<dataLines.length; i++ ) {
				var line = dataLines[i];

				xi_L = parseInt(i/Nyz); md = parseInt(i%Nyz) ;
				yi_L = parseInt(md/Nz);
				zi_L = parseInt(md%Nz);

				func=parseFloat(line);

				if (func>c3 && func<=1.0) { R_col= 1.0 ; G_col= (1.0-func)/(1.0-c3) ; B_col= 0.0 ; }
	       else if (func>c2 && func<=c3 ) { R_col= (func-c2)/(c3-c2) ; G_col= 1.0 ; B_col= 0.0 ; }
	       else if (func>c1 && func<=c2 ) { R_col= 0.0 ; G_col= 1.0 ; B_col= (c2-func)/(c2-c1) ; }
	       else if (func>=0 && func<=c1 ) { R_col= 0.0 ; G_col= (func-0.0)/(c1-0.0) ; B_col= 1.0 ; }


				xi_L = parseInt(i/Nyz); md = parseInt(i%Nyz) ;
				yi_L = parseInt(md/Nz);
				zi_L = parseInt(md%Nz);

				var vertex = {};

				vertex.x = x_g[xi_L];
				vertex.y = y_g[yi_L];
				vertex.z = z_g[zi_L];


				vertex.cr = R_col;
				vertex.cg = G_col;
				vertex.cb = B_col;
				vertex.p = func;
				Rhos[time].push( vertex );

			}

	};
	function parseParams ( dataLines ) {

		for ( var i=0; i<dataLines.length; i++ ) {
			var line = dataLines[i];

			var values = line.split('=');

			switch( values[0].trim() ) {
				case 'Nx' :Nx =Number(values[1]); break;
				case 'Ny' :Ny =Number(values[1]); break;
				case 'Nz' :Nz =Number(values[1]); break;
				case 'N_nod' :N_nod =Number(values[1]); break;
				case 'N_t' :N_t =Number(values[1]); break;
				case 'xmx' :xmx =Number(values[1]); break;
				case 'ymx' :ymx =Number(values[1]); break;
				case 'zmx' :zmx =Number(values[1]); break;
				case 'xmn' :xmn =Number(values[1]); break;
				case 'ymn' :ymn =Number(values[1]); break;
				case 'zmn' :zmn =Number(values[1]); break;
				case 'V_mx' :V_mx =Number(values[1]); break;
				case 'V_mn' :V_mn =Number(values[1]); break;
				case 'n_mx' :n_mx =Number(values[1]); break;
				case 'n_mn' :n_mn =Number(values[1]); break;
				case 'p_mx' :p_mx =Number(values[1]); break;
				case 'p_mn' :p_mn =Number(values[1]); break;

				case 'structure_type' :structure_type = values[1]; break;
				case 'Lchn' :Lchn =Number(values[1]); break;
				case 'Lgate' :Lgate =Number(values[1]); break;
				case 'Lsrc' :Lsrc =Number(values[1]); break;
				case 'Ldrn' :Ldrn =Number(values[1]); break;
				case 'Tchn' :Tchn =Number(values[1]); break;
				case 'Wchn' :Wchn =Number(values[1]); break;
				case 'ox0_T' :ox0_T =Number(values[1]); break;
				case 'ox1_T' :ox1_T =Number(values[1]); break;
				case 'ox2_T' :ox2_T =Number(values[1]); break;
				case 'ox3_T' :ox3_T =Number(values[1]); break;
				case 'gate_type':gate_type= values[1]; break;
				case 'Is_pot3d' :ON_save_V =Number(values[1]); break;
				case 'Is_nq3d' :ON_save_R =Number(values[1]); break;

				default: alert('Un-recognizable parameter: '+values[0].trim()); return;
			}
		};

		//alert(Nx+" "+Ny+" "+Nz+" "+N_nod+" "+N_t+" "+xmx+" "+ymx+" "+zmx+" "+xmn+" "+ymn+" "+zmn);

		   Dxx = xmx-xmn;
		   Dyy = ymx-ymn;
		   Dzz = zmx-zmn;

		for (var i=0;i<11;i++) Pallet_V[i]=V_mn + (V_mx-V_mn)/10*i;
		for (var i=0;i<11;i++) Pallet_n[i]=n_mn + (n_mx-n_mn)/10*i;
		for (var i=0;i<11;i++) Pallet_p[i]=p_mn + (p_mx-p_mn)/10*i;

		Nyz = Ny*Nz;

		x_g = new Array(Nx);
		y_g = new Array(Ny);
		z_g = new Array(Nz);

	};

	//------------------------
	function drawAxis () {

		var axis = [];

		var N_axis = 9;

		var Label_Px = new Array();
		var Label_Py = new Array();
		var Label_Pz = new Array();


		for ( var i=0;i<=N_axis-1; i++) {
			axis[i] = {};
			axis[i].geo = new THREE.Geometry();
			switch( i ) {
				case 0:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, 0, 0));
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, 0, 0));
					break;
				case 1:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, 0, 0));
					axis[i].geo.vertices.push(new THREE.Vector3( 0, 0, mmx));
					break;
				case 2:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, 0, mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( 0, mmx, mmx));
					break;
				case 3:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, mmx, mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, mmx, mmx));
					break;
				case 4:
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, mmx, mmx));
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, mmx, 0));
					break;
				case 5:
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, mmx, 0));
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, 0, 0));
					break;
				case 6:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, 0, 0));
					axis[i].geo.vertices.push(new THREE.Vector3( 0, mmx, 0));
					break;
				case 7:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, mmx, 0));
					axis[i].geo.vertices.push(new THREE.Vector3( mmx, mmx, 0));
					break;
				case 8:
					axis[i].geo.vertices.push(new THREE.Vector3( 0, mmx, 0));
					axis[i].geo.vertices.push(new THREE.Vector3( 0, mmx, mmx));
					break;

			}
			axis[i].mat = new THREE.LineBasicMaterial( { color: 0x000000, linewidth: 3 } );
			axis[i].mesh = new THREE.Line( axis[i].geo, axis[i].mat );
			scene.add( axis[i].mesh );
		}

	}; // End of drawAxis()
	//---------


function removeAllObjects()
{


	scene.remove(Device);
	scene.remove(DeviceFrame);

	for (var i=0; i<3;i++){ scene.remove(plateViewers[i]); scene.remove(arrow_plates[i]);}

}
function Draw_DeviceFrame()
{

	var DLines = [];

	var Lp_x= Lchn + Ldrn + Lsrc , Lp_y=Wchn, Lp_z=Tchn;
	//var Lp_x= xmx-xmn , Lp_y=ymx-ymn, Lp_z=zmx-zmn;

	//var Lines_p   = [[0,0,0],[Lp_x,0,0],[Lp_x,Lp_y,0],[0,Lp_y,0],[0,0,Lp_z],[Lp_x,0,Lp_z],[Lp_x,Lp_y,Lp_z],[0,Lp_y,Lp_z]];
	var Lines_p = [[0,0,0],[xmx,0,0],[xmx,ymx,0],[0,ymx,0],[0,0,zmx],[xmx,0,zmx],[xmx,ymx,zmx],[0,ymx,zmx]];

	var LL = [[0,1],[1,2],[2,3],[3,0],[4,5],[5,6],[6,7],[7,4],[0,4],[1,5],[2,6],[3,7]];

	var N_Lines = LL.length;

	DeviceFrame = new THREE.Object3D();

	for ( var i=0;i<=N_Lines-1; i++)
	{
		DLines[i] = {};
		DLines[i].geo = new THREE.Geometry();

		DLines[i].geo.vertices.push(new THREE.Vector3( Lines_p[LL[i][0]][0], Lines_p[LL[i][0]][1], Lines_p[LL[i][0]][2]));
		DLines[i].geo.vertices.push(new THREE.Vector3( Lines_p[LL[i][1]][0], Lines_p[LL[i][1]][1], Lines_p[LL[i][1]][2]));

		DLines[i].mat = new THREE.LineDashedMaterial( { color: 0x000000, linewidth: 1, scale:1, dashSize: 3, gapSize: 1, transparent: true, opacity: 0.3 } );
		DLines[i].mesh = new THREE.Line( DLines[i].geo, DLines[i].mat );
		//scene.add( DLines[i].mesh );

		DeviceFrame.add(DLines[i].mesh);
	}

	scene.add( DeviceFrame );





}
//----------------------------------------------------------------------------------------------------------------
function Draw_Device(data)
{
	//console.log( 'Draw_Device', data );
	//alert(data);
var lines = data.split('\n');

	L_channel=Number(lines[0].trim());
	L_gate =Number(lines[1].trim());
	L_source =Number(lines[2].trim());
	L_drain =Number(lines[3].trim());

	T_channel=Number(lines[4].trim());
	W_channel=Number(lines[5].trim());
	T0_oxide =Number(lines[6].trim());
	T1_oxide =Number(lines[7].trim());
	T2_oxide =Number(lines[8].trim());
	T3_oxide =Number(lines[9].trim());

	Dop_source =Number(lines[10].trim());
	Dop_drain =Number(lines[11].trim());
	Dop_channel =Number(lines[12].trim());

	Gate_Type = lines[13].trim();

	DopType_source = lines[14].trim();
	DopType_drain = lines[15].trim();
	DopType_channel = lines[16].trim();

	zmx = L_source + L_channel + L_drain;
	xmx = zmx;
	ymx = T_channel + T2_oxide+T3_oxide;

//alert(T0_oxide+T1_oxide+T2_oxide+T3_oxide);

scene.remove(Device);
Device = new THREE.Object3D();

var Geo = new Array();
var Mater = new Array();
var Compo = new Array();

	var z_T=-0;
	var x_T=0 ;
	var y_T=0 ;

	var T_gate = 2;

	var L_oxide = L_channel+L_source+L_drain;

	Opacity[0]=1.0; // source
	Opacity[1]=1.0; // drain
	Opacity[2]=1.0; // channel
	Opacity[3]=0.6; // gate
	Opacity[4]=0.65; // oxide

	//--------source  --------------------------------------

	  var Color_gate = new THREE.Color( 0.8, 0.4, 0.0 );
	  var Color_oxide = new THREE.Color( 0.6, 0.6, 0.6 );
	  var Color_semi;
	  //var Color_semi= 0x333333+ (1-Dop_ratio)*(0x00ff00)+ (1-Dop_ratio)*(0x0000ff) ;

	  Determine_color(DopType_source, Dop_source);
	  Color_semi= new THREE.Color( R_col, G_col, B_col );

	   Geo[0] = new THREE.BoxGeometry( L_source, W_channel, T_channel );

	  Mater[0] = new THREE.MeshStandardMaterial({opacity:Opacity[0], color: Color_semi, transparent:false});
	  Compo[0] = new THREE.Mesh(Geo[0], Mater[0]);
	  Compo[0].position.set(-L_channel*0.5 - 0.5*L_source+x_T, y_T, z_T);

	  Device.add(Compo[0]);
	//--------drain --------------------------------------

	  Determine_color(DopType_drain, Dop_drain);
	  Color_semi= new THREE.Color( R_col, G_col, B_col );

	  Geo[1] = new THREE.BoxGeometry(L_drain, W_channel, T_channel );
	  Mater[1] = new THREE.MeshStandardMaterial({opacity:Opacity[1], color: Color_semi, transparent:false});
	  //scene.remove(Compo[1]);
	  Compo[1] = new THREE.Mesh(Geo[1], Mater[1]);
	  Compo[1].position.set(L_channel*0.5 + 0.5*L_drain+x_T, y_T, z_T);
	  Device.add(Compo[1]);

	  //--------channel--------------------------------------

	  Determine_color(DopType_channel, Dop_channel);
	  Color_semi= new THREE.Color( R_col, G_col, B_col );

	  Geo[2] = new THREE.BoxGeometry(L_channel, W_channel, T_channel);
	  Mater[2] = new THREE.MeshStandardMaterial({opacity:Opacity[2], color: Color_semi, transparent:false});
	  //scene.remove(Compo[2]);
	  Compo[2] = new THREE.Mesh(Geo[2], Mater[2]);
	  Compo[2].position.set(x_T,y_T,z_T);
	  Device.add(Compo[2]);

	  //--------gate   --------------------------------------
	   Mater[4] = new THREE.MeshStandardMaterial({opacity:Opacity[4], color: Color_oxide, transparent:true, side: THREE.DoubleSide});
	   Mater[3] = new THREE.MeshStandardMaterial({opacity:Opacity[3], color: Color_gate, transparent:true, side: THREE.DoubleSide});


	  if (Gate_Type=='Double')
	  {

		  var x_shift ;

		  var H_T_channel_gate = (T_channel+T_gate)*0.5 ;

		  Geo[3] = new THREE.BoxGeometry(L_gate, W_channel, T_gate);
		  Compo[3] = new THREE.Mesh(Geo[3], Mater[3]);

		  var z_shift = H_T_channel_gate + T0_oxide;
		  Compo[3].position.set(x_T, y_T, z_T+ z_shift);

		  z_shift = H_T_channel_gate + T1_oxide;

		  var Compo3_clone = Compo[3].clone();
		  Compo3_clone.position.set(x_T, y_T, z_T- z_shift);

		//--------oxide   --------------------------------------

		  Geo[4] = new THREE.BoxGeometry(L_oxide, W_channel, T0_oxide);

		  Compo[4] = new THREE.Mesh(Geo[4], Mater[4]);

		  x_shift= (L_source-L_drain)*0.5 ;
		  z_shift=T_channel*0.5+T0_oxide*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T, z_T + z_shift);

		  Geo[4] = new THREE.BoxGeometry(L_oxide, W_channel, T1_oxide);

		  var Compo4_1 = new THREE.Mesh(Geo[4], Mater[4]);

		  z_shift=T_channel*0.5+T1_oxide*0.5;

		  Compo4_1.position.set(x_T-x_shift, y_T, z_T- z_shift);

		  Device.add(Compo[4]);
		  Device.add(Compo4_1);

		  Device.add(Compo[3]);
		  Device.add(Compo3_clone);
	  }
	  if (Gate_Type=='Tri')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;

		//--------oxide   --------------------------------------
		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide , T_channel+T0_oxide+T1_oxide);
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide+ T_gate);

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T, y_T+ox_shift1, z_T+ ox_shift0);
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T+ T_gate*0.5 + ox_shift0);

		var dummy3_BSP = new ThreeBSP(dummy0_BSP);

		dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP);
		dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);

	     Compo[4]     = dummy0_BSP.toMesh(Mater[4]); // oxide
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     Compo[3].position.set(x_T, y_T+ox_shift1, z_T+ T_gate*0.5+ ox_shift0);

	     var x_shift= (L_source-L_drain)*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T + ox_shift0);

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);

	  }
	  if (Gate_Type=='Pi')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;

		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide , T_channel+T0_oxide+T1_oxide*2);
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide*2+ T_gate); // gate

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T, y_T+ox_shift1, z_T- T1_oxide*0.5+ox_shift0);
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T+(T_gate-T1_oxide)*0.5 +ox_shift0); // gate

		var dummy3_BSP = new ThreeBSP(dummy0_BSP);

			dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP);
			dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);

	     Compo[4]     = dummy0_BSP.toMesh(Mater[4]); // oxide
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     var x_shift= (L_source-L_drain)*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T- T1_oxide*0.5+ox_shift0);

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);

	  }
	  if (Gate_Type=='Omega')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;
		  var x_shift =(L_source-L_drain)*0.5;

		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide         , T_channel+T0_oxide+T1_oxide+ T_gate); // oxide
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide+ 2*T_gate); // gate
		var dummy_L_Geo4= new THREE.BoxGeometry(L_oxide*2, T2_oxide+W_channel*0.1, T_gate);
		var dummy_R_Geo4= new THREE.BoxGeometry(L_oxide*2, T3_oxide+W_channel*0.1, T_gate);

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T-x_shift, y_T+ox_shift1, z_T- T_gate*0.5+ox_shift0);     // oxide
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T + ox_shift0);  // gate

		var y_shift = (W_channel+T2_oxide)*0.5-W_channel*0.05, z_shift = T_channel*0.5+T1_oxide+T_gate*0.5 ;

		var dummy4_L_BSP = new THREE.Mesh(dummy_L_Geo4, Mater[3]); dummy4_L_BSP.position.set(x_T, y_T+y_shift , z_T-z_shift);

		y_shift = (W_channel+T3_oxide)*0.5-W_channel*0.05

		var dummy4_R_BSP = new THREE.Mesh(dummy_R_Geo4, Mater[3]); dummy4_R_BSP.position.set(x_T, y_T-y_shift , z_T-z_shift);

			dummy4_L_BSP = new ThreeBSP(dummy4_L_BSP);
			dummy4_R_BSP = new ThreeBSP(dummy4_R_BSP);

		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy4_L_BSP);  // oxide
		 dummy0_BSP = dummy0_BSP.toMesh(Mater[4]); // oxide
		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy4_R_BSP);  // oxide
		 dummy0_BSP = dummy0_BSP.toMesh(Mater[4]); // oxide
		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);  // oxide
		 Compo[4]   = dummy0_BSP.toMesh(Mater[4]); // oxide

		 var dummy3_BSP = new ThreeBSP(Compo[4]);

	     dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP); // gate
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     Compo[3].position.set(x_T, y_T+ox_shift1, z_T+ox_shift0  );  // gate


	 //Compo[4]= new THREE.Mesh(dummy_Geo0, Mater[4]);

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T- T_gate*0.5+ox_shift0);   // oxide

	 //    alert((T_channel+T0_oxide+T1_oxide+ T_gate)+" "+(z_T- T_gate*0.5+ox_shift0));

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);



	  }
	  if (Gate_Type=='GAA')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;
		//  alert("GAA");

		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide         , T_channel+T0_oxide+T1_oxide); // oxide
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide+ 2*T_gate); // gate
		var dummy_Geo4= new THREE.BoxGeometry(L_oxide, T2_oxide+W_channel*0.1, T_gate);

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T, y_T+ox_shift1, z_T+ox_shift0);     // oxide
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T+ox_shift0);  // gate

		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);  // oxide
		 Compo[4]   = dummy0_BSP.toMesh(Mater[4]); // oxide

		 var dummy3_BSP = new ThreeBSP(Compo[4]);

	     dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP); // gate
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     Compo[3].position.set(x_T, y_T+ox_shift1, z_T+ox_shift0  );  // gate

	     var x_shift= (L_source-L_drain)*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T+ox_shift0);   // oxide

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);

	  }




	  scene.add(Device);

}

//----------------------------------------------------------------------------------------------------------------

function Draw_Device_file(data)
{
	//if(data!="") alert(data);
    data = data.replace(/	/gi, ' ');
    data = data.replace(/\t/gi,  ' ');
    data = data.replace(/=/gi,   ' ');
    data = data.replace(/\(/gi,   ' ');
    data = data.replace(/\)/gi,   ' ');
    data = data.replace(/,/gi,   ' ');
    
    data = data.replace(/ +/gi,  " ");
	   
       	    
	var lines = data.split('\n');
	//console.log( 'Draw_Device', lines );
	//alert(JSON.stringify(lines));
	//data="";
	
	for( var i in lines )
	{
		var line = lines[i].trim();
		var dummy = line.split(' ');
		
		if(dummy[0]=="DEV_Dimen" && dummy[1]=="Lchn_Lgate"    ) { L_channel=Number(dummy[2]) ; L_gate   =Number(dummy[3]) ; }
		if(dummy[0]=="DEV_Dimen" && dummy[1]=="Lsrc_Ldrn"     ) { L_source =Number(dummy[2]) ; L_drain  =Number(dummy[3]) ; } 
		if(dummy[0]=="DEV_Dimen" && dummy[1]=="Tchn_Wchn"     ) { T_channel=Number(dummy[2]) ; W_channel=Number(dummy[3]) ; }
		if(dummy[0]=="DEV_Dimen" && dummy[1]=="ox_thickness"  ) { T0_oxide =Number(dummy[2]) ; T1_oxide =Number(dummy[3]) ; T2_oxide=Number(dummy[4]) ; T3_oxide=Number(Number(dummy[5])) ;}
		if(dummy[0]=="DEV_Dimen" && dummy[1]=="gate_type"     ) { Gate_Type=dummy[2] ;  }
		if(dummy[0]=="DEV_Mat"   && dummy[1]=="src_drn_doping") { DopType_source = DopType_drain = dummy[2] ; Dop_source = Dop_drain = Number(dummy[3]); }
		if(dummy[0]=="DEV_Mat"   && dummy[1]=="chn_doping"    ) { DopType_channel= dummy[2]                 ; Dop_channel= Number(dummy[3]) ; break; }
	}

	//console.log( 'Draw_Device', data );
	
	//alert(L_channel+" "+L_gate+" "+L_source+" "+L_drain+" "+T_channel+" "+W_channel+" "+T0_oxide+" "+T1_oxide+" "+T2_oxide+" "+T3_oxide+" "+Gate_Type+" "+DopType_source+" "+Dop_source+" "+DopType_channel+" "+Dop_channel);
	

	zmx = L_source + L_channel + L_drain;
	xmx = zmx;
	ymx = T_channel + T2_oxide+T3_oxide;

scene.remove(Device);
Device = new THREE.Object3D();

var Geo = new Array();
var Mater = new Array();
var Compo = new Array();

	var z_T=-0;
	var x_T=0 ;
	var y_T=0 ;

	var T_gate = 2;

	var L_oxide = L_channel+L_source+L_drain;

	Opacity[0]=1.0; // source
	Opacity[1]=1.0; // drain
	Opacity[2]=1.0; // channel
	Opacity[3]=0.6; // gate
	Opacity[4]=0.65; // oxide
	
	//--------source  --------------------------------------

	  var Color_gate = new THREE.Color( 0.8, 0.4, 0.0 );
	  var Color_oxide = new THREE.Color( 0.6, 0.6, 0.6 );
	  var Color_semi;
	  //var Color_semi= 0x333333+ (1-Dop_ratio)*(0x00ff00)+ (1-Dop_ratio)*(0x0000ff) ;

	  Determine_color(DopType_source, Dop_source);
	  Color_semi= new THREE.Color( R_col, G_col, B_col );

	   Geo[0] = new THREE.BoxGeometry( L_source, W_channel, T_channel );

	  Mater[0] = new THREE.MeshStandardMaterial({opacity:Opacity[0], color: Color_semi, transparent:false});
	  Compo[0] = new THREE.Mesh(Geo[0], Mater[0]);
	  Compo[0].position.set(-L_channel*0.5 - 0.5*L_source+x_T, y_T, z_T);

	  Device.add(Compo[0]);
	//--------drain --------------------------------------

	  Determine_color(DopType_drain, Dop_drain);
	  Color_semi= new THREE.Color( R_col, G_col, B_col );

	  Geo[1] = new THREE.BoxGeometry(L_drain, W_channel, T_channel );
	  Mater[1] = new THREE.MeshStandardMaterial({opacity:Opacity[1], color: Color_semi, transparent:false});
	  //scene.remove(Compo[1]);
	  Compo[1] = new THREE.Mesh(Geo[1], Mater[1]);
	  Compo[1].position.set(L_channel*0.5 + 0.5*L_drain+x_T, y_T, z_T);
	  Device.add(Compo[1]);

	  //--------channel--------------------------------------

	  Determine_color(DopType_channel, Dop_channel);
	  Color_semi= new THREE.Color( R_col, G_col, B_col );

	  Geo[2] = new THREE.BoxGeometry(L_channel, W_channel, T_channel);
	  Mater[2] = new THREE.MeshStandardMaterial({opacity:Opacity[2], color: Color_semi, transparent:false});
	  //scene.remove(Compo[2]);
	  Compo[2] = new THREE.Mesh(Geo[2], Mater[2]);
	  Compo[2].position.set(x_T,y_T,z_T);
	  Device.add(Compo[2]);

	  //--------gate   --------------------------------------
	   Mater[4] = new THREE.MeshStandardMaterial({opacity:Opacity[4], color: Color_oxide, transparent:true, side: THREE.DoubleSide});
	   Mater[3] = new THREE.MeshStandardMaterial({opacity:Opacity[3], color: Color_gate, transparent:true, side: THREE.DoubleSide});


	  if (Gate_Type=='Double')
	  {

		  var x_shift ;

		  var H_T_channel_gate = (T_channel+T_gate)*0.5 ;

		  Geo[3] = new THREE.BoxGeometry(L_gate, W_channel, T_gate);
		  Compo[3] = new THREE.Mesh(Geo[3], Mater[3]);

		  var z_shift = H_T_channel_gate + T0_oxide;
		  Compo[3].position.set(x_T, y_T, z_T+ z_shift);

		  z_shift = H_T_channel_gate + T1_oxide;

		  var Compo3_clone = Compo[3].clone();
		  Compo3_clone.position.set(x_T, y_T, z_T- z_shift);

		//--------oxide   --------------------------------------

		  Geo[4] = new THREE.BoxGeometry(L_oxide, W_channel, T0_oxide);

		  Compo[4] = new THREE.Mesh(Geo[4], Mater[4]);

		  x_shift= (L_source-L_drain)*0.5 ;
		  z_shift=T_channel*0.5+T0_oxide*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T, z_T + z_shift);

		  Geo[4] = new THREE.BoxGeometry(L_oxide, W_channel, T1_oxide);

		  var Compo4_1 = new THREE.Mesh(Geo[4], Mater[4]);

		  z_shift=T_channel*0.5+T1_oxide*0.5;

		  Compo4_1.position.set(x_T-x_shift, y_T, z_T- z_shift);

		  Device.add(Compo[4]);
		  Device.add(Compo4_1);

		  Device.add(Compo[3]);
		  Device.add(Compo3_clone);
	  }
	  if (Gate_Type=='Tri')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;

		//--------oxide   --------------------------------------
		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide , T_channel+T0_oxide+T1_oxide);
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide+ T_gate);

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T, y_T+ox_shift1, z_T+ ox_shift0);
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T+ T_gate*0.5 + ox_shift0);

		var dummy3_BSP = new ThreeBSP(dummy0_BSP);

		dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP);
		dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);

	     Compo[4]     = dummy0_BSP.toMesh(Mater[4]); // oxide
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     Compo[3].position.set(x_T, y_T+ox_shift1, z_T+ T_gate*0.5+ ox_shift0);

	     var x_shift= (L_source-L_drain)*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T + ox_shift0);

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);

	  }
	  if (Gate_Type=='Pi')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;

		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide , T_channel+T0_oxide+T1_oxide*2);
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide*2+ T_gate); // gate

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T, y_T+ox_shift1, z_T- T1_oxide*0.5+ox_shift0);
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T+(T_gate-T1_oxide)*0.5 +ox_shift0); // gate

		var dummy3_BSP = new ThreeBSP(dummy0_BSP);

			dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP);
			dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);

	     Compo[4]     = dummy0_BSP.toMesh(Mater[4]); // oxide
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     var x_shift= (L_source-L_drain)*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T- T1_oxide*0.5+ox_shift0);

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);

	  }
	  if (Gate_Type=='Omega')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;
		  var x_shift =(L_source-L_drain)*0.5;

		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide         , T_channel+T0_oxide+T1_oxide+ T_gate); // oxide
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide+ 2*T_gate); // gate
		var dummy_L_Geo4= new THREE.BoxGeometry(L_oxide*2, T2_oxide+W_channel*0.1, T_gate);
		var dummy_R_Geo4= new THREE.BoxGeometry(L_oxide*2, T3_oxide+W_channel*0.1, T_gate);

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T-x_shift, y_T+ox_shift1, z_T- T_gate*0.5+ox_shift0);     // oxide
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T + ox_shift0);  // gate

		var y_shift = (W_channel+T2_oxide)*0.5-W_channel*0.05, z_shift = T_channel*0.5+T1_oxide+T_gate*0.5 ;

		var dummy4_L_BSP = new THREE.Mesh(dummy_L_Geo4, Mater[3]); dummy4_L_BSP.position.set(x_T, y_T+y_shift , z_T-z_shift);

		y_shift = (W_channel+T3_oxide)*0.5-W_channel*0.05

		var dummy4_R_BSP = new THREE.Mesh(dummy_R_Geo4, Mater[3]); dummy4_R_BSP.position.set(x_T, y_T-y_shift , z_T-z_shift);

			dummy4_L_BSP = new ThreeBSP(dummy4_L_BSP);
			dummy4_R_BSP = new ThreeBSP(dummy4_R_BSP);

		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy4_L_BSP);  // oxide
		 dummy0_BSP = dummy0_BSP.toMesh(Mater[4]); // oxide
		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy4_R_BSP);  // oxide
		 dummy0_BSP = dummy0_BSP.toMesh(Mater[4]); // oxide
		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);  // oxide
		 Compo[4]   = dummy0_BSP.toMesh(Mater[4]); // oxide

		 var dummy3_BSP = new ThreeBSP(Compo[4]);

	     dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP); // gate
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     Compo[3].position.set(x_T, y_T+ox_shift1, z_T+ox_shift0  );  // gate


	 //Compo[4]= new THREE.Mesh(dummy_Geo0, Mater[4]);

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T- T_gate*0.5+ox_shift0);   // oxide

	 //    alert((T_channel+T0_oxide+T1_oxide+ T_gate)+" "+(z_T- T_gate*0.5+ox_shift0));

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);



	  }
	  if (Gate_Type=='GAA')
	  {
		  var ox_shift0=(T0_oxide-T1_oxide)*0.5;
		  var ox_shift1=(T2_oxide-T3_oxide)*0.5;
		//  alert("GAA");

		var dummy_Geo0= new THREE.BoxGeometry(L_oxide, W_channel+T2_oxide+T3_oxide         , T_channel+T0_oxide+T1_oxide); // oxide
		var dummy_Geo1= new THREE.BoxGeometry(L_oxide, W_channel , T_channel );
		var dummy_Geo2= new THREE.BoxGeometry(L_gate , W_channel+T2_oxide+T3_oxide+T_gate*2, T_channel+T0_oxide+T1_oxide+ 2*T_gate); // gate
		var dummy_Geo4= new THREE.BoxGeometry(L_oxide, T2_oxide+W_channel*0.1, T_gate);

		var dummy0_BSP = new THREE.Mesh(dummy_Geo0, Mater[4]); dummy0_BSP.position.set(x_T, y_T+ox_shift1, z_T+ox_shift0);     // oxide
	    var dummy1_BSP = new THREE.Mesh(dummy_Geo1, Mater[4]);
	        dummy1_BSP = new ThreeBSP(dummy1_BSP);
		var dummy2_BSP = new THREE.Mesh(dummy_Geo2, Mater[3]); dummy2_BSP.position.set(x_T, y_T+ox_shift1, z_T+ox_shift0);  // gate

		 dummy0_BSP = new ThreeBSP(dummy0_BSP).subtract(dummy1_BSP);  // oxide
		 Compo[4]   = dummy0_BSP.toMesh(Mater[4]); // oxide

		 var dummy3_BSP = new ThreeBSP(Compo[4]);

	     dummy2_BSP = new ThreeBSP(dummy2_BSP).subtract(dummy3_BSP); // gate
	     Compo[3]     = dummy2_BSP.toMesh(Mater[3]); // gate

	     Compo[3].position.set(x_T, y_T+ox_shift1, z_T+ox_shift0  );  // gate

	     var x_shift= (L_source-L_drain)*0.5;

		  Compo[4].position.set(x_T-x_shift, y_T+ox_shift1, z_T+ox_shift0);   // oxide

		  Device.add(Compo[4]);
		  Device.add(Compo[3]);

	  }




	  scene.add(Device);
	  

}



function CBox_replace_Atom()
{
}
//---------------------------------------------------------------------
function onDocumentMouseMove( event )
{



	//movePlate('xy');
}

//-------------------------------
function onDocumentMouseClick()
{


}
function Determine_color(DopType_region,Dop )
{
	var c1 = 0.33, c2 = 0.66 ;

	var Dop_max = 1e25, Dop_min = 9.999e4;


	var Dop_ratio = Math.log10(Dop-Dop_min)/Math.log10(Dop_max-Dop_min);


	if (DopType_region=="n")
	  {

			 if (Dop_ratio> c2 && Dop_ratio<=1.0) { R_col= 1 ; G_col= 0 ; B_col= (Dop_ratio-c2)/(1.0-c2) ; }
		else if (Dop_ratio>=c1 && Dop_ratio<=c2 ) { R_col= 1 ; G_col= (Dop_ratio-c2)/(c1-c2) ; B_col= 0.0 ; }
		else if (Dop_ratio>= 0 && Dop_ratio< c1 ) { R_col= (Dop_ratio)/(c1-0.0) ; G_col= 1.0 ; B_col= 0.0 ; }
	  }
	  if (DopType_region=="p")
	  {
			 if (Dop_ratio> c2 && Dop_ratio<=1.0) { R_col= (Dop_ratio-c2)/(1.0-c2)*0.5 ; G_col= 0 ; B_col= ((1.0-Dop_ratio)/(1.0-c2)+1)*0.5 ; }
		else if (Dop_ratio>=c1 && Dop_ratio<=c2 ) { R_col= 0 ; G_col= (c2-Dop_ratio)/(c2-c1) ; B_col= 1 ; }
		else if (Dop_ratio>= 0 && Dop_ratio< c1 ) { R_col= 0 ; G_col= 1.0 ; B_col= (Dop_ratio)/(c1-0.0) ; }
	//  alert(DopType_region);
	  }
	  if (DopType_region=="i")
	  {
		  R_col=0.6 ; G_col=0.9 ; B_col=0.3;
	  }


}

function Sel_Draw_VR(mode)
{
	var plateId;

	var plateMesh;

	var valueInput ;

	var posInput ;

	var V_mode = 0;

	if (document.getElementById('Pxy_visible').checked)
	{

		plateId='xy';

		valueInput = document.getElementById('P'+plateId+'Range');
		posInput = 'P'+plateId+'Input1';

		zi = valueInput.value;

		if (mode==0) { document.getElementById(posInput).value = Vertices['0'][zi-1].z; }
		if (mode==1) { document.getElementById(posInput).value = Rhos['0'][zi-1].z; }

		setViewerShape(plateId);
		setPlateMode(plateId, V_mode);

		plateMesh = canvas.getPlateMesh(plateId);
		plateMesh.geometry.elementsNeedUpdate = true;
	}

	if (document.getElementById('Pyz_visible').checked)
	{

		plateId='yz';

		valueInput = document.getElementById('P'+plateId+'Range');
		posInput = 'P'+plateId+'Input1';

		xi = valueInput.value;

		if (mode==0) { document.getElementById(posInput).value = Vertices['0'][Nz*Ny*(xi-1)].x; }
		if (mode==1) { document.getElementById(posInput).value = Rhos['0'][Nz*Ny*(xi-1)].x; }

		setViewerShape(plateId);
		setPlateMode(plateId, V_mode);

		plateMesh = canvas.getPlateMesh(plateId);
		plateMesh.geometry.elementsNeedUpdate = true;

	}

	if (document.getElementById('Pzx_visible').checked)
	{
		plateId='zx';
		valueInput = document.getElementById('P'+plateId+'Range');
		posInput = 'P'+plateId+'Input1';

		yi = valueInput.value;

		if (mode==0) { document.getElementById(posInput).value = Vertices['0'][Nz*(yi-1)].y; }
		if (mode==1) { document.getElementById(posInput).value = Rhos['0'][Nz*(yi-1)].y; }

		setViewerShape(plateId);
		setPlateMode(plateId, V_mode);

		plateMesh = canvas.getPlateMesh(plateId);
		plateMesh.geometry.elementsNeedUpdate = true;
	}


	//setArrows(plateId);

}

function onWindowResize() {

	camera.aspect = scene_width / scene_height;
	camera.updateProjectionMatrix();

	renderer.setSize( scene_width, scene_height );

}



//---------------------------------------------------------------------------------------------------------
function Write_ouput()
{

	var document_string="";

	document.open();
	document_string +="<table style=\"width:100%;\"> ";
	document_string +="<tr>";
	document_string +="<td>";
	document_string +="<div id=\"container\" style=\"width:100%;margin: auto;\"> <\div>";
	document_string +="</td>";
	document_string +="<td>";


	document_string +="<div id='Draw_V_Rho' style=\"border:1px solid #aaaaaa;\">";
	document_string +="<input type='radio' id='Draw_V' name='Draw_VR' checked='checked' onclick=\"Sel_Draw_VR(0)\"> Potential";
	document_string +="<input type='radio' id='Draw_Rho' name='Draw_VR' onclick=\"Sel_Draw_VR(1)\"> Charge density";
	document_string +="</div>";


	document_string +="<table style=\"width:100%;\"> ";
	document_string +="<tr bgcolor='#eeeeee' > ";
	document_string +="<td > <SPAN style='font-size: 10pt'> Viewer </SPAN> </td>";
	document_string +="<td> <SPAN style='font-size: 10pt'> density </SPAN> </td>";
	document_string +="<td> <SPAN style='font-size: 10pt'> terrain </SPAN> </td>";
	document_string +="<td > <SPAN style='font-size: 10pt'> E field </SPAN> </td> ";
	document_string +="<td > <SPAN style='font-size : 10pt'> position </SPAN> </td> </tr>";
	document_string +="<tr> ";
	document_string +="<td > <SPAN style='font-size: 10pt'> Pxy </SPAN> </td>";
	document_string +="<td> <input id='Pxy_visible' type='checkbox' onclick=\"toggleView( 'xy' )\"> </td>";
	document_string +="<td> <input id='Pxy_mode' type='checkbox' onclick=\"toggleTerrain('xy')\"> </td> ";
	document_string +="<td> <input id='Pxy_arr_visible' type='checkbox' onclick=\"toggleArrows('xy')\"> </td> ";
	document_string +="<td> <input id='PxyInput1' type='text' value='0' size='3' height='1' autocomplete='off'> </td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td> <SPAN style='font-size: 10pt'> Pyz </SPAN> </td>";
	document_string +="<td> <input id='Pyz_visible' type='checkbox' onclick=\"toggleView( 'yz' )\"> </td>";
	document_string +="<td> <input id='Pyz_mode' type='checkbox' onclick=\"toggleTerrain('yz')\"> </td> ";
	document_string +="<td> <input id='Pyz_arr_visible' type='checkbox' onclick=\"toggleArrows('yz')\"> </td> ";
	document_string +="<td> <input id='PyzInput1' type='text' value='0' size='3' height='1' autocomplete='off'> </td> </tr>";
	document_string +="<tr> ";
	document_string +="<td> <SPAN style='font-size: 10pt'> Pzx </SPAN> </td>";
	document_string +="<td> <input id='Pzx_visible' type='checkbox' onclick=\"toggleView( 'zx' )\"> </td> ";
	document_string +="<td> <input id='Pzx_mode' type='checkbox' onclick=\"toggleTerrain('zx')\"> </td> ";
	document_string +="<td> <input id='Pzx_arr_visible' type='checkbox' onclick=\"toggleArrows('zx')\"> </td> ";
	document_string +="<td> <input id='PzxInput1' type='text' value='0' size='3' height='1' autocomplete='off'> </td>";
	document_string +="</tr> ";
	document_string +="</table> ";
	document_string +="<table style=\"width:95%;\"> ";
	document_string +="<tr> ";
	document_string +="<td colspan='1'> <SPAN style='font-size: 10pt'> Viewer </SPAN> </td>";
	document_string +="<td colspan='1' bgcolor='#eeeeee'> <SPAN style='font-size: 10pt'> position </SPAN></td>";
	document_string +="<td bgcolor='#eeeeee'> <SPAN style='font-size: 10pt'> opacity </SPAN></td> ";
	document_string +="</tr> ";
	document_string +="<tr> ";
	document_string +="<td colspan='1'> <SPAN style='font-size: 10pt'> Pxy </SPAN> </td>";
	document_string +="<td colspan='1'> <input id='PxyRange' type='range' style='width:90%;' value='0'  min='1' max='"+Nz+"' step='1' oninput=\"movePlate('xy')\"  autocomplete='off'/> </td>";
	document_string +="<td colspan='1'> <input id='PxyOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput=\"changeOpacity('xy')\"  autocomplete='off'/> </td>";
	document_string +="</tr> ";
	document_string +="<tr> ";
	document_string +="<td colspan='1'> <SPAN style='font-size: 10pt'> Pyz </SPAN> </td>";
	document_string +="<td colspan='1'> <input id='PyzRange' type='range' style='width:90%;' value='0'  min='1' max='"+Nx+"' step='1' oninput=\"movePlate('yz')\"  autocomplete='off'/> </td>";
	document_string +="<td colspan='1'> <input id='PyzOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput=\"changeOpacity('yz')\"  autocomplete='off'/> </td>";
	document_string +="</tr>";
	document_string +="<tr> ";
	document_string +="<td colspan='1'> <SPAN style='font-size: 10pt'> Pzx </SPAN> </td>";
	document_string +="<td colspan='1'> <input id='PzxRange'   type='range' style='width:90%;' value='0' min='1' max='"+Ny+"' step='1' oninput=\"movePlate('zx')\"  autocomplete='off'/> </td>";
	document_string +="<td colspan='1'> <input id='PzxOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput=\"changeOpacity('zx')\"  autocomplete='off'/> </td> ";
	document_string +="</tr>";
	document_string +="</table>";

	if (ON_save_V==1 || ON_save_R==1)
	{

	document_string +="<table> ";
	document_string +="<tr>";
	document_string +="<td>Color</td>";
	if (ON_save_V==1) document_string +="<td>Potential(V)</td>";
	if (ON_save_R==1) document_string +="<td>Charge density(C/m^3)</td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td rowspan='11'><img src="+imagePath+" width='50' height='231'></td>";
	if (ON_save_V==1) document_string +="<td>"+Pallet_V[0].toExponential(2)+"</td>";
	if (ON_save_R==1) {
		document_string +="<td>"+Pallet_n[0].toExponential(2)+"</td>";
		document_string +="<td>"+Pallet_p[0].toExponential(2)+"</td>";
	}
	document_string +="</tr>";

	for (var i=1; i<11; i++)
	{
		document_string +="<tr>";
		if (ON_save_V==1) document_string +="<td>"+Pallet_V[i].toExponential(2)+"</td>";
		if (ON_save_R==1) {
			document_string +="<td>"+Pallet_n[i].toExponential(2)+"</td>";
			document_string +="<td>"+Pallet_p[i].toExponential(2)+"</td>";
		}
		document_string +="</tr>";
	}

	document_string +="</table> ";

	}

	document_string +="</td>";
	document_string +="</tr>";
	document_string +="</table> ";

	document.write(document_string);

	document.close();


}
//---------------------------------------------------------------------------------------------------------
function Write_input()
{

	var document_string="";

	//document.open();
	document_string +="<table style=\"width:100%;\"> ";
	document_string +="<tr>";
	document_string +="<td><div id=\"container\" style=\"width:100%;margin: auto;\"> <\div></td>";
	document_string +="</tr>";
	document_string +="</table > ";
//	<input id='PxyRange' type='range' style='width:90%;' value='0'  min='1' max='"+Nz+"' step='1' oninput=\"movePlate('xy')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/>
	document.write(document_string);

	//document.close();

}


function setNamespace(ns) {
	 namespace = ns;
	 namespace = ns;
}

function fireSendStrucEvent(data) {

	setTimeout(
			function() {
				if ( namespace ) {

					window.parent[namespace+'Send_Struc_to_Editor']( data );
				}
				else {
					fireSendStrucEvent(data);
				}
			},
			10
	);
}


</script>

</body>
</html>