<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html style="height:95%">

<head>
	<script src="<%= request.getContextPath() %>/js/three/libs/Three_R86.js" type="text/javascript"></script>
	<script src="<%= request.getContextPath() %>/js/three/libs/TrackballControls_R68.js" type="text/javascript"></script>
	<!--
	<script src="<%= request.getContextPath() %>/js/three/font/helvetiker_regular.typeface.js" type="text/javascript"></script>
	 -->

<!-- JQuery -->
<script src="<%= request.getContextPath() %>/js/jquery/jquery-2.2.3.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery/jquery.blockUI.js"></script>

<link href="<%= request.getContextPath() %>/js/jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath() %>/css/main.css" rel="stylesheet" type="text/css">
<link href="<%= request.getContextPath() %>/js/jquery/bootstrap-toggle.min.css" rel="stylesheet">
<script src="<%= request.getContextPath() %>/js/jquery/bootstrap-toggle.min.js"></script>

<!-- bootstrap -->
<link href="<%= request.getContextPath() %>/js/jquery/bootstrap.min.css" rel="stylesheet">
<script src="<%= request.getContextPath() %>/js/jquery/bootstrap.min.js"></script>

<style>
	body{ background-color: rgb(255,255,255); }
	canvas{ background-color: white; float: left; }
</style>

</head>

<body style="height:100%;">

<script type="text/javascript">
var atomAnalyzerNamespace;
var disabled=false;

var imagePath = parent.atomTransitorAnalyzerImagePath;

var scene_width = 700;
var scene_height= 700;

var scene = new THREE.Scene();
var renderer = new THREE.WebGLRenderer();
renderer.setClearColor( 0xffffff, 1 );
renderer.setSize(scene_width, scene_height);
renderer.shadowMapEnabled = true;
renderer.sortObjects = false;

var raycaster = new THREE.Raycaster();

var Atoms = new THREE.Object3D();
var Link_Group = new THREE.Object3D();
var aBoxLine = new THREE.Object3D();

var F_ini_Box=0;

var BoxLines ;

var BoxLineGroup = new THREE.Object3D();

//var Boxes;
var BoxGroup = new THREE.Object3D();

var camera = new THREE.PerspectiveCamera(45, scene_width / scene_height, 0.01, 10000);

//var camera = new THREE.PerspectiveCamera( 70, window.innerWidth / window.innerHeight, 1, 10000 );

var ambientLight = new THREE.AmbientLight(0x1c1c1c); scene.add(ambientLight);

var directionalLight = new THREE.DirectionalLight(0xffffff,1);
//directionalLight.position.set(1, 0, 1).normalize();
scene.add(directionalLight);

var directionalLight2 = new THREE.DirectionalLight(0xffffff,1);
//directionalLight.position.set(1, 0, 1).normalize();
scene.add(directionalLight2);

//------------------------
/*
var pointLight = new THREE.PointLight( 0xffffff );

pointLight.castShadow = true;
scene.add( pointLight );

var pointLight2 = new THREE.PointLight( 0xffffff );

pointLight2.castShadow = true;
scene.add( pointLight2 );
*/

var mouse = new THREE.Vector2(), INTERSECTED;

var Nx, Ny, Nz;
var Nzy;
var xmx, ymx, zmx;
var xmn, ymn, zmn;

var P_max=-1e1000, P_min=1e1000;
var R_max=-1e1000, R_min=1e1000;

var Pallet_P =new Array(11);  // potential
var Pallet_R =new Array(11);  // Rho

var Dxx, Dyy, Dzz;

var dx, dy ,dz;
var dx_g, dy_g, dz_g;

var Real_N_Atoms ;
var N_Atoms ;
var N_Species, T_N_Species ;

var Mx_Species_Number;

var Lattice_C ;
var unit ;
var R_Bohr = 0.53;

var mmx=Lattice_C/R_Bohr;

var um = 1e-6;

var N_t=1 ;

var P_Atoms ;
var ID_Atom ;

var N_links;
var Links= new Array(1) ;

var atom_Geo = new Array();
var atom_Mat = new Array();
var atom = new Array();
var Link = new Array();

var Addition_atom;
var Addition_atom_Geo;
var Addition_atom_Mat;

var ON_save_P=0;
var ON_save_R=0;


var t_i=0;
var Vertices = {};

var Max_N_Species =50;

var Species_DelOrder = new Array(Max_N_Species);
var Species_Order = new Array(Max_N_Species);
var Species_Number= new Array(Max_N_Species);
var Species_Name = new Array(Max_N_Species);

var iid;
var insec_name;

var Extend = new Array(3);
Extend[0]=0;
Extend[1]=0;
Extend[2]=0;

var LatticeVector = new Array(3);

var nonz_i = new Array();

//var text_Atom_Type;
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

var Atom_Name=["Removed",
		       "H" , "He",
		       "Li","Be", "B" ,"C" ,"N" ,"O" ,"F" ,"Ne",
		       "Na","Mg", "Al","Si","P" ,"S" ,"Cl","Ar",
		       "K" ,"Ca","Sc","Ti","V" ,"Cr","Mn","Fe","Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr",
			   "Rb","Sr","Y" ,"Zr","Nb","Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I" ,"Xe",
			   "Cs","Ba",
			             "La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm","Yb","Lu",
			                  "Hf","Ta","W" ,"Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po","At","Rn",
		       "Fr","Ra",
			             "Ac","Th","Pa","U" ,"Np","Pu","Am","Cm","Bk","Cf","Es","Fm","Md","No","Lr",
			                  "Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Cn","Nh","Fl","Mc","Lv","Ts","Og"  ];


var Atom_color = [ '000000',
				   'ff4444', '777777',
				   'ff4422','dd2200', 'ffff44','44ff44','ff8800','4444dd','4444ff','666666',
				   'ff2244','dd3300', 'ffff22','22ff22','ffcc00','4400dd','2222ff','555555',
				   'ff2222','dd3300','aa6600','ff9900','ffcc00','ff0000','ff0000','ff0000','ffcc00','ff0000','ff0000','ff0000','ffff00','00ff00','ffcc00','0044dd','0000ff','444444',
				   'ff0000','dd3300','aa6600','ff9900','ffcc00','ff0000','ff0000','ff0000','ffcc00','ff0000','ff0000','ff0000','dddd00','00dd00','ffcc00','2222dd','0000dd','333333',
				   'dd0000','dd0000',
									 'ff0000','dd3300','aa6600','ff9900','ffcc00','ff0000','ff0000','ff0000','ffcc00','ff0000','ff0000','ff0000', 'ff6600','ff9900','ffcc00',
											  'ff0000','ff3300','ff6600','ff9900','ffcc00','ff0000','ff0000','ff0000','ffcc00','bbbb00','00bb00','ff0000', '2200dd','0000bb','222222',
				   'bb0000','dd0000',
									 'ff0000','dd3300','aa6600','ff9900','ffcc00','ff0000','ff0000','ff0000','ffcc00','ff0000','ff0000','ff0000', 'ff6600','ff9900','ffcc00',
											  'ff0000','ff3300','ff6600','ff9900','ffcc00','ff0000','ff0000','ff0000','ffcc00','999900','009900','ff0000', '0022dd','000099','111111'
				   ];

var Atom_R = [ ];

for (var i=1; i<=118; i++) Atom_R[i]=i*0.01+0.3;

var Is_N_spe = new Array(119);
for ( var i=0; i<119; i++ ) Is_N_spe[i]=0;

var Addition_Atom_i, Gra_Atom_i;

var addition_x;
var addition_y;
var addition_z;

var Draw_VR ;

var xi, yi, zi;

var EBeam_color=0xddccff;

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
	
	ON_save_P=0;
	ON_save_R=0;

	for ( var index=0; index<lines.length; index++ ) {

		var line = lines[index].trim();
		if ( !line ) continue;
		line=line.replace(/ +/g, " ");

		var keyVal = line.split(' ');
		switch( keyVal[0].trim())
		{
		case 'LatticeConstant' : Lattice_C = Number(keyVal[1]); mmx=Lattice_C/R_Bohr; fin_i++; break;
		case 'SaveRho' : if (keyVal[1]=='T' || keyVal[1]=='.true.') ON_save_R=1; fin_i++; break;
		case 'SaveElectrostaticPotential': if (keyVal[1]=='T' || keyVal[1]=='.true.') ON_save_P=1; fin_i++; break;
		}
		if (fin_i==3) break;

	}

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
				case 'Atom_coor':
					N_Atoms=Number(keyVal[1]);
					parseAtomCoor( lines.slice(start, end));

					break;
				case 'Link_coor':
					N_links=Number(keyVal[1]); Define_Links();
					parseLinkCoor( lines.slice(start, end));

					break;
				case 'EP_data': parseEPData( lines.slice(start, end)); break;				
				case 'Rho_data': parseRhoData( lines.slice(start, end)); break;
				case 'params': parseParams( lines.slice(start, end)); break;
				default: alert( 'File type mismatch......');
			}
		}
		if ( line[0] === '%') {
			var line = line.replace('%', '');
			var keyVal = line.split(' ');

			if (keyVal[0].trim()==='block' && keyVal[1].trim()==='SuperCell')
			{
				var start = index+1; var end = start + 3;
				parseSuperCell( lines.slice(start, end));
			}
			if (keyVal[0].trim()==='block' && keyVal[1].trim()==='LatticeVectors')
			{
				var start = index+1; var end = start + 3;
				parseLatticeVectors( lines.slice(start, end));
			}

		}



	}

	xi=1; yi=1; zi=1;

	Write_ouput();

	Draw_VR = document.getElementsByName('Draw_VR');

	//text_Atom_Type = document.getElementById("Atom_Type");
	text_Atom_Name = document.getElementById("Atom_Name");
	text_x = document.getElementById("position_x");
	text_y = document.getElementById("position_y");
	text_z = document.getElementById("position_z");

container_elem=document.getElementById("container");

	canvas = new Canvas( container_elem );

	Draw_Atoms(); scene.add( Atoms );
	Draw_Links(); scene.add( Link_Group );

	directionalLight.position.set(3*xmx, 3*ymx, 3*zmx).normalize();
	directionalLight2.position.set(-3*xmx, -3*ymx, -3*zmx).normalize();

	//pointLight.position.set( 3*xmx, 3*ymx, 3*zmx );
	//pointLight2.position.set(-3*xmx, -3*ymx, -3*zmx);

	trackballControl = new THREE.TrackballControls(camera, renderer.domElement);

	trackballControl.target = new THREE.Vector3((Dxx)*0.5, (Dyy)*0.5, (Dzz)*0.5);

	trackballControl.rotateSpeed = 1.0;
	trackballControl.zoomSpeed = 1.0;
	trackballControl.panSpeed = 1.0;

	camera.up = new THREE.Vector3(0.0,0.0,1.0);

	camera.rotation.z=Math.PI*0.5;

	camera.position.set(camera_Vscale*(Dyy+Dzz)+Dxx*0.5, camera_Vscale*(Dzz+Dxx)+Dyy*0.5, camera_Vscale*(Dxx+Dyy)+Dzz*0.5);
	//camera.position.set(Dxx*0.5, camera_Vscale*Dyy, Dzz*0.5);

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
	
	if (ON_save_P==1 || ON_save_R==1)
	{

	setViewerShape('xy');
	setViewerShape('yz');
	setViewerShape('zx');

	setViewerFaces();

	setArrows('xy');
	setArrows('yz');
	setArrows('zx');

	}
	
	drawBox();

	document.removeEventListener( 'click', onDocumentMouseMove, false );
	document.addEventListener( 'mousemove', onDocumentMouseMove, false );

	document.addEventListener( 'mouseover', onDocumentMouseOver, false );

	window.addEventListener( 'resize', onWindowResize, false );

	animate();
}

function load_AtomCoor(data)
{
	console.log("[YEJIN]Test Viewer load ", data);
	removeAllObjects();

	data=data.replace(/\t/g, " ");
	data=data.replace(/ +/g, " "); // several space -> one space
	
	

	var lines = data.split('\n');


	for ( var index=0; index<lines.length; index++ )
	{
		var line = lines[index].trim();

		if ( !line ) continue;

	    var keyVal = line.split(' ');

		if ( keyVal[0] === 'NumberOfAtoms') {N_Atoms = Number(keyVal[1]); }
		if ( keyVal[0] === 'NumberOfSpecies'){N_Species = Number(keyVal[1]); T_N_Species=N_Species; }

		if ( keyVal[0] === '%block' && (keyVal[1] === 'ChemicalSpeciesLabel' || keyVal[1] === 'Chemical_Species_Label') )
		{

			var start = index+1; var end = start + Number(N_Species);
			parseSpecies( lines.slice(start, end));

		}

		if ( keyVal[0] === 'LatticeConstant') { Lattice_C = Number(keyVal[1]); mmx=Lattice_C/R_Bohr; unit = keyVal[2]; }

		if ( keyVal[0] === '%block' && keyVal[1] === 'LatticeVectors')
		{
			var start = index+1; var end = start + 3;
			parseLatticeVectors( lines.slice(start, end));
		}

		if ( keyVal[0] === '%block' && keyVal[1] === 'SuperCell')
		{
			var start = index+1; var end = start + 3;
			parseSuperCell( lines.slice(start, end));
		}

	}
	
	//alert("mmm---Species_Order[i]"+Species_Order[1]+" "+Species_Name[Species_Order[1]]+" "+Species_Order[2]+" "+Species_Name[Species_Order[2]]+" "+Species_Order[3]+" "+Species_Name[Species_Order[3]]+Species_Order[4]+" "+Species_Name[Species_Order[4]]);


	for ( var index=0; index<lines.length; index++ )
	{
		var line = lines[index].trim();

		if ( !line ) continue;


	    var keyVal = line.split(' ');

		if ( keyVal[0] === '%block' && keyVal[1] === 'AtomicCoordinatesAndAtomicSpecies')
		{
			var start = index+1; var end = start + Number(N_Atoms);
			parseAtomCoor_input( lines.slice(start, end));
		}
	}


	xi=1; yi=1; zi=1;

	Write_input();

	addition_x=document.getElementById('Addition_atom_x');
	addition_y=document.getElementById('Addition_atom_y');
	addition_z=document.getElementById('Addition_atom_z');

	//text_Atom_Type = document.getElementById("Atom_Type");
	text_Atom_Name = document.getElementById("Atom_Name");
	text_x = document.getElementById("position_x");
	text_y = document.getElementById("position_y");
	text_z = document.getElementById("position_z");

	text_Addition_atom_Name=document.getElementById("Addition_atom_Name");
	text_Addition_atom_x=document.getElementById("Addition_atom_x");
	text_Addition_atom_y=document.getElementById("Addition_atom_y");
	text_Addition_atom_z=document.getElementById("Addition_atom_z");


	text_Addition_atom_x.value = 0;
	text_Addition_atom_y.value = 0;
	text_Addition_atom_z.value = 0;

	text_replace_Atom = document.getElementById("TBox_replace_Atom");

	container_elem=document.getElementById("container");

	canvas = new Canvas( container_elem );
	drawBox();
	F_ini_Box=0;

	Draw_Atoms(); scene.add( Atoms );

	directionalLight.position.set(3*xmx, 3*ymx, 3*zmx).normalize();
	directionalLight2.position.set(-3*xmx, -3*ymx, -3*zmx).normalize();


	//pointLight.position.set(  3*xmx,  3*ymx,  3*zmx);
	//pointLight2.position.set(-3*xmx, -3*ymx, -3*zmx);

	trackballControl = new THREE.TrackballControls(camera, renderer.domElement);
	trackballControl.target = new THREE.Vector3(Dxx*0.5, Dyy*0.5, Dzz*0.5);

	trackballControl.rotateSpeed = 1.0;
	trackballControl.zoomSpeed = 1.0;
	trackballControl.panSpeed = 1.0;

	camera.up = new THREE.Vector3(0.0,0.0,1.0);
	camera.rotation.z=Math.PI*0.5;

	camera.position.set(camera_Vscale*(Dyy+Dzz)+Dxx*0.5, camera_Vscale*(Dzz+Dxx)+Dyy*0.5, camera_Vscale*(Dxx+Dyy)+Dzz*0.5);
	//camera.position.set(Dxx*0.5, camera_Vscale*Dyy, Dzz*0.5);

	document.addEventListener( 'mousemove', onDocumentMouseMove, false );
	document.addEventListener( 'click', onDocumentMouseClick, false );
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



function move_addition_atom()
{
	var x, y, z;

	var x_ini, y_ini ,z_ini;


	x = document.getElementById('Range_addition_x').value;
	y = document.getElementById('Range_addition_y').value;
	z = document.getElementById('Range_addition_z').value;

	x_ini = xmn-0.1*(xmx-xmn); y_ini = ymn-0.1*(ymx-ymn); z_ini = zmn-0.1*(zmx-zmn);

	//dx = 1.5*(xmx-xmn)/(N_Range*Extend[0]);	dy = 1.5*(ymx-ymn)/(N_Range*Extend[1]);	dz = 1.5*(zmx-zmn)/(N_Range*Extend[2]);

	x=x_ini+dx*x; y=y_ini+dy*y; z=z_ini+dz*z;

	Addition_atom.position.set(x, y, z);

	text_Addition_atom_x.value = x;
	text_Addition_atom_y.value = y;
	text_Addition_atom_z.value = z;

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
	//----------------------------------------------------
	function parseSpecies ( dataLines ) {

		//Species_DelOrder = new Array(Max_N_Species);
		//Species_Order = new Array(Max_N_Species);
		//Species_Number= new Array(Max_N_Species);
		//Species_Name  = new Array(Max_N_Species);

		Mx_Species_Number=0;
		
		//alert(dataLines);


		for ( var i=1; i<=N_Species; i++ ) {

			var avalue = dataLines[i-1].trim().split(' ');

			Species_Order[i] = Number(avalue[0]);
			Species_Number[Species_Order[i]] = Number(avalue[1]);
			Species_Name[Species_Order[i]] = avalue[2];
			
			//alert("Species_Order[i]"+Species_Order[i]+" "+Species_Name[Species_Order[i]]);

			if (Species_Number[Species_Order[i]] > Mx_Species_Number ) Mx_Species_Number = Species_Number[Species_Order[i]];

			
		}

		if (Mx_Species_Number<201) Mx_Species_Number=200;

	};
	//----------------------------------------------------
	function parseLatticeVectors ( dataLines ) {

		for ( var i=0; i<3; i++ )
		{
			dataLines[i]=dataLines[i].replace(/ +/g, " ");
			var avalue = dataLines[i].trim().split(' ');

			LatticeVector[i] = Number(avalue[i]);		
		}
	};
	function parseAtomCoor( dataLines )
	{
		Define_P_Atoms(N_Atoms);

		
		for ( var i=0; i<dataLines.length; i++ ) {
			var avalue = dataLines[i].trim().split(' ');

			P_Atoms[0][i][0]=avalue[0];
			P_Atoms[0][i][1]=avalue[1]; 
			P_Atoms[0][i][2]=avalue[0];
			P_Atoms[0][i][3]=Number(avalue[3]);
			P_Atoms[0][i][4]=Number(avalue[4]);
			P_Atoms[0][i][5]=Number(avalue[5]);
			P_Atoms[0][i][6]=Number(avalue[6]);

			for (var j=0; j<5;j++)
			{
				if ( isNaN(P_Atoms[0][i][2].substring(j,1))===true ) break;
			}

			P_Atoms[0][i][2]=P_Atoms[0][i][2].substring(j-1);

		}
	};
	function parseAtomCoor_input ( dataLines )
	{
		var N_Atoms_u=dataLines.length;

		var ai=0;

		var Lp_x=mmx*LatticeVector[0], Lp_y=mmx*LatticeVector[1], Lp_z=mmx*LatticeVector[2];
		
	//	alert(dataLines);
		//alert(N_Atoms_u);

		N_Atoms=N_Atoms*Extend[0]*Extend[1]*Extend[2];
		
		Real_N_Atoms=N_Atoms;

		Define_P_Atoms(N_Atoms+N_Addition_max);

		for ( var i=0; i<119; i++ ) Is_N_spe[i]=0;

		for ( var i=0; i<N_Atoms_u; i++ )
		{
			//dataLines[i]=dataLines[i].replace(/ +/g, " ");
			var avalue = dataLines[i].trim().split(' ');
			var spe=Number(avalue[3]);
			
		//	alert(avalue[0]+" "+avalue[1]+" "+avalue[2]+" "+avalue[3]+" ");

			P_Atoms[0][ai][0]=spe;
			P_Atoms[0][ai][2]=Species_Name[spe];
			
		//	alert(spe+" "+Species_Name[spe]);

			var msign = P_Atoms[0][ai][2].indexOf("-");

			if (msign==-1)
			{
				P_Atoms[0][ai][1]=Species_Number[spe];
			}
			else
			{
				var T_Atom_name = P_Atoms[0][ai][2].substring(0,msign);
				for (var j=1; j<119; j++) { if (T_Atom_name===Atom_Name[j]) { P_Atoms[0][ai][1]=j; break;} }
			}

			P_Atoms[0][ai][3]=Number(avalue[0])*mmx;
			P_Atoms[0][ai][4]=Number(avalue[1])*mmx;
			P_Atoms[0][ai][5]=Number(avalue[2])*mmx;
			P_Atoms[0][ai][6]=0;

	     	Is_N_spe[spe] ++;

			ai++;
		}

		for (var ix=0; ix<Extend[0] ; ix++)
		{
			for (var iy=0; iy<Extend[1] ; iy++)
			{
				for (var iz=0; iz<Extend[2] ; iz++)
				{
					if (ix==0 && iy==0 && iz==0) continue;

					for ( var i=0; i<N_Atoms_u; i++ )
					{

						P_Atoms[0][ai][0]=P_Atoms[0][i][0];
						P_Atoms[0][ai][1]=P_Atoms[0][i][1];
						P_Atoms[0][ai][2]=P_Atoms[0][i][2];
						P_Atoms[0][ai][3]=P_Atoms[0][i][3]+Lp_x*ix;
						P_Atoms[0][ai][4]=P_Atoms[0][i][4]+Lp_y*iy;
						P_Atoms[0][ai][5]=P_Atoms[0][i][5]+Lp_z*iz;
						P_Atoms[0][ai][6]=P_Atoms[0][i][6];

						Is_N_spe[P_Atoms[0][ai][0]] ++;

						ai++;
					}
				}
			}

		}



	};
	function parseSuperCell ( dataLines ) {

		//dataLines.replace( /\t/gi, " ");

		for ( var i=0; i<3; i++ ){ Extend[i]=1; var avalue = dataLines[i].trim().split(' '); Extend[i]=avalue[i]; }

	};
	function parseLinkCoor ( dataLines ) {
		for ( var i=0; i<dataLines.length; i++ ) {
			var avalue = dataLines[i].trim().split(' ');

			Links[0][i][0]=Number(avalue[0]); Links[0][i][1]=Number(avalue[1]); Links[0][i][2]=Number(avalue[2]);

		}
	};
	function parseEPData ( dataLines ) {

//--------------- to get V field  --------------

		if (ON_save_P===0) return;

		var time = '0';
		var xi_L, yi_L, zi_L, md;
		var c1=0.25, c2=0.50, c3=0.75;
		var R_col, G_col, B_col;
		var func= new Array(N_nod);

		Vertices[time] = [];
		Grad = [];
		

		for ( var i=0; i<dataLines.length; i++ ) {
			var line = dataLines[i];
		//	var values = line.split(',');

			xi_L = i/Nzy; md = i%Nzy ;
			yi_L = md/Nz;
			zi_L = md%Nz;

			func[i]=parseFloat(line);

			if (func[i]>c3 && func[i]<=1.0) { R_col= 1.0 ; G_col= (1.0-func[i])/(1.0-c3) ; B_col= 0.0 ; }
	   else if (func[i]>c2 && func[i]<=c3 ) { R_col= (func[i]-c2)/(c3-c2) ; G_col= 1.0 ; B_col= 0.0 ; }
	   else if (func[i]>c1 && func[i]<=c2 ) { R_col= 0.0 ; G_col= 1.0 ; B_col= (c2-func[i])/(c2-c1) ; }
	   else if (func[i]>=0 && func[i]<=c1 ) { R_col= 0.0 ; G_col= (func[i]-0.0)/(c1-0.0) ; B_col= 1.0 ; }

			var vertex = {};
			vertex.x = xi_L*dx_g;
			vertex.y = yi_L*dy_g;
			vertex.z = zi_L*dz_g;
			vertex.cr = R_col;
			vertex.cg = G_col;
			vertex.cb = B_col;
			vertex.p = func[i];

			Vertices[time].push( vertex );

		}

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

		for (var i=0;i<N_nod;i++)
		{
			var dummy;
			xi_L = parseInt(i / (Ny*Nz)) ; dummy = i % (Ny*Nz);
			yi_L = parseInt(dummy / Nz) ;
			zi_L = dummy % Nz ;
			ord[xi_L][yi_L][zi_L] = zi_L + Nz*yi_L + Nz*Ny*xi_L ;
		}

		var Norm_dx = dx_g/(xmx-xmn) ;
		var Norm_dy = dy_g/(ymx-ymn) ;
		var Norm_dz = dz_g/(zmx-zmn) ;

		for (var i=0;i<N_nod;i++)
		{
			var dummy;

			xi_L = parseInt(i / (Ny*Nz)) ; dummy = i % (Ny*Nz);
			yi_L = parseInt(dummy / Nz) ;
			zi_L = dummy % Nz ;

	     	if (xi_L==0 || xi_L==Nx-1) grad_fx[i] = 0; else grad_fx[i] = ( func[ord[xi_L+1][yi_L][zi_L]] - func[ord[xi_L-1][yi_L][zi_L]] ) / dx_g;
			if (yi_L==0 || yi_L==Ny-1) grad_fy[i] = 0; else grad_fy[i] = ( func[ord[xi_L][yi_L+1][zi_L]] - func[ord[xi_L][yi_L-1][zi_L]] ) / dy_g;
			if (zi_L==0 || zi_L==Nz-1) grad_fz[i] = 0; else grad_fz[i] = ( func[ord[xi_L][yi_L][zi_L+1]] - func[ord[xi_L][yi_L][zi_L-1]] ) / dz_g;

	    	if (grad_fx[i]> grad_fx_mx) grad_fx_mx = grad_fx[i]; if (grad_fx[i]< grad_fx_mn) grad_fx_mn = grad_fx[i];
			if (grad_fy[i]> grad_fy_mx) grad_fy_mx = grad_fy[i]; if (grad_fy[i]< grad_fy_mn) grad_fy_mn = grad_fy[i];
			if (grad_fz[i]> grad_fz_mx) grad_fz_mx = grad_fz[i]; if (grad_fz[i]< grad_fz_mn) grad_fz_mn = grad_fz[i];

		}

		var diffx_mx = grad_fx_mx - grad_fx_mn;
		var diffy_mx = grad_fy_mx - grad_fy_mn;
		var diffz_mx = grad_fz_mx - grad_fz_mn;

		var grad_lim=16.0;

		for (i=0;i<N_nod;i++)
		{

			var dummy;

			var Norm_grad_x, Norm_grad_y, Norm_grad_z;

			xi_L = parseInt(i / (Ny*Nz)) ; dummy = i % (Ny*Nz);
			yi_L = parseInt(dummy / Nz) ;
			zi_L = dummy % Nz ;

			Norm_grad_x = -grad_fx[i]/diffx_mx ; if (Math.abs(Norm_grad_x)>grad_lim*Norm_dx) Norm_grad_x = Norm_grad_x/Math.abs(Norm_grad_x)*grad_lim*Norm_dx ;
			Norm_grad_y = -grad_fy[i]/diffy_mx ; if (Math.abs(Norm_grad_y)>grad_lim*Norm_dy) Norm_grad_y = Norm_grad_y/Math.abs(Norm_grad_y)*grad_lim*Norm_dy ;
			Norm_grad_z = -grad_fz[i]/diffz_mx ; if (Math.abs(Norm_grad_z)>grad_lim*Norm_dz) Norm_grad_z = Norm_grad_z/Math.abs(Norm_grad_z)*grad_lim*Norm_dz ;

//				fprintf(fw, "%.2e, %.2e, %.2e\n",  Norm_grad_x, Norm_grad_y, Norm_grad_z );

			var xyz = {
				x: Norm_grad_x,
				y: Norm_grad_y,
				z: Norm_grad_z
			};
			Grad.push( xyz );
		}

	};

	 function parseRhoData ( dataLines ) {

		 if (ON_save_R===0) return;

		var time = '0';
		var xi, yi, zi, md;
		var c1=0.25, c2=0.50, c3=0.75;
		var R_col, G_col, B_col;
		var func;

		Rhos[time] = [];

		for ( var i=0; i<dataLines.length; i++ ) {
			var line = dataLines[i];

			xi = i/Nzy; md = i%Nzy ;
			yi = md/Nz;
			zi = md%Nz;

			func=parseFloat(line);

			if (func>c3 && func<=1.0) { R_col= 1.0 ; G_col= (1.0-func)/(1.0-c3) ; B_col= 0.0 ; }
	   else if (func>c2 && func<=c3 ) { R_col= (func-c2)/(c3-c2) ; G_col= 1.0 ; B_col= 0.0 ; }
	   else if (func>c1 && func<=c2 ) { R_col= 0.0 ; G_col= 1.0 ; B_col= (c2-func)/(c2-c1) ; }
	   else if (func>=0 && func<=c1 ) { R_col= 0.0 ; G_col= (func-0.0)/(c1-0.0) ; B_col= 1.0 ; }

			//var rhos = line.split(',');

			xi = i/Nzy; md = i%Nzy ;
			yi = md/Nz;
			zi = md%Nz;
			//XYZ[i][0]=xi*dx; XYZ[i][1]=yi*dy; XYZ[i][2]=zi*dz;

			var vertex = {};
			vertex.x = xi*dx_g;
			vertex.y = yi*dy_g;
			vertex.z = zi*dz_g;

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
				case 'dx' :dx_g =Number(values[1]); break;
				case 'dy' :dy_g =Number(values[1]); break;
				case 'dz' :dz_g =Number(values[1]); break;
				case 'P_max' :P_max =Number(values[1]); break;
				case 'P_min' :P_min =Number(values[1]); break;
				case 'R_max' :R_max =Number(values[1]); break;
				case 'R_min' :R_min =Number(values[1]); break;
				default: alert('Un-recognizable parameter: '+values[0].trim()); return;
			}
		};

		Nzy=Nz*Ny;

		for (var i=0;i<11;i++) Pallet_P[i]=P_min + (P_max-P_min)/10*i;
		for (var i=0;i<11;i++) Pallet_R[i]=R_min + (R_max-R_min)/10*i;

	};

	//------------------------
	function drawBox () {

		var BLines = [];

		var Lp_x=mmx*LatticeVector[0], Lp_y=mmx*LatticeVector[1], Lp_z=mmx*LatticeVector[2];

		var Lines_p = [[0,0,0],[Lp_x,0,0],[Lp_x,Lp_y,0],[0,Lp_y,0],[0,0,Lp_z],[Lp_x,0,Lp_z],[Lp_x,Lp_y,Lp_z],[0,Lp_y,Lp_z]];

		var LL = [[0,1],[1,2],[2,3],[3,0],[4,5],[5,6],[6,7],[7,4],[0,4],[1,5],[2,6],[3,7]];

		var N_Lines = LL.length;

		aBoxLine = new THREE.Object3D();

		if (Extend[0]===0) Extend[0]=1;
		if (Extend[1]===0) Extend[1]=1;
		if (Extend[2]===0) Extend[2]=1;


		for ( var i=0;i<=N_Lines-1; i++)
		{
			BLines[i] = {};
			BLines[i].geo = new THREE.Geometry();

			BLines[i].geo.vertices.push(new THREE.Vector3( Lines_p[LL[i][0]][0], Lines_p[LL[i][0]][1], Lines_p[LL[i][0]][2]));
			BLines[i].geo.vertices.push(new THREE.Vector3( Lines_p[LL[i][1]][0], Lines_p[LL[i][1]][1], Lines_p[LL[i][1]][2]));

			BLines[i].mat = new THREE.LineDashedMaterial( { color: 0x666666, linewidth: 1, scale:1, dashSize: 3, gapSize: 1 } );
			BLines[i].mesh = new THREE.Line( BLines[i].geo, BLines[i].mat );
			//scene.add( BLines[i].mesh );

			aBoxLine.add(BLines[i].mesh);
		}

		                               BoxLines = new Array(Extend[0]);
		for (var i=0; i<Extend[0]; i++) BoxLines[i] = new Array(Extend[1]);
		for (var i=0; i<Extend[0]; i++)
		for (var j=0; j<Extend[1]; j++) BoxLines[i][j] = new Array(Extend[2]);

		scene.remove( BoxLineGroup );
		BoxLineGroup = new THREE.Object3D();

		for (var i=0; i<Extend[0]; i++)
		for (var j=0; j<Extend[1]; j++)
		for (var k=0; k<Extend[2]; k++)
		{
			BoxLines[i][j][k]=aBoxLine.clone();

			BoxLines[i][j][k].position.set(i*Lp_x, j*Lp_y, k*Lp_z);

			//scene.add( BoxLines[i][j][k] );

			BoxLineGroup.add(BoxLines[i][j][k]);
		}

		scene.add( BoxLineGroup );


		var aBox = new THREE.Mesh(new THREE.BoxGeometry(Lp_x,Lp_y,Lp_z), new THREE.MeshLambertMaterial({color: 0xffffff, transparent:true, opacity:0.1}));

		                               var Boxes = new Array(Extend[0]);
			for (var i=0; i<Extend[0]; i++) Boxes[i] = new Array(Extend[1]);
			for (var i=0; i<Extend[0]; i++)
			for (var j=0; j<Extend[1]; j++) Boxes[i][j] = new Array(Extend[2]);

			scene.remove( BoxGroup );
			BoxGroup = new THREE.Object3D();

			for (var i=0; i<Extend[0]; i++)
			for (var j=0; j<Extend[1]; j++)
			for (var k=0; k<Extend[2]; k++)
			{
				Boxes[i][j][k]=aBox.clone();

				Boxes[i][j][k].position.set(i*Lp_x+Lp_x*0.5, j*Lp_y+Lp_y*0.5, k*Lp_z+Lp_z*0.5);

		//		scene.add( Boxes[i][j][k] );
				BoxGroup.add( Boxes[i][j][k] );
			}

			scene.add( BoxGroup );



	}; // End of drawAxis()
	//---------
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
function Draw_Atoms()
{
// var Num_loaded_Atoms = P_Atoms[t_i].length;

var j=0;
var i=0;

xmx= -1e1000; ymx= -1e1000; zmx= -1e1000;
xmn= 1e1000; ymn= 1e1000; zmn= 1e1000;

for (var i=0; i<N_Atoms; i++)
{
	   if (P_Atoms[0][i][6]==1) continue;
	  
	  atom_Geo[i] = new THREE.SphereGeometry(Atom_R[P_Atoms[0][i][1]],15,15);
	  atom_Mat[i] = new THREE.MeshStandardMaterial({color: Number('0x'+Atom_color[P_Atoms[0][i][1]]) });

	  atom[j] = new THREE.Mesh(atom_Geo[i],atom_Mat[i]);
	  atom[j].position.set(P_Atoms[0][i][3], P_Atoms[0][i][4], P_Atoms[0][i][5]);
	  atom[j].castShadow = true;

	  
	  
	   
	  if (atom[j].position.x>xmx) xmx=atom[j].position.x; if (atom[j].position.y>ymx) ymx=atom[j].position.y; if (atom[j].position.z>zmx) zmx=atom[j].position.z;
	  if (atom[j].position.x<xmn) xmn=atom[j].position.x; if (atom[j].position.y<ymn) ymn=atom[j].position.y; if (atom[j].position.z<zmn) zmn=atom[j].position.z;

	  Atoms.add(atom[j]);

	  Atoms.children[j].name=j;

	  nonz_i[j]=i;

	  j++;

}

Dxx = xmx-xmn;
Dyy = ymx-ymn;
Dzz = zmx-zmn;

dx = 1.5*Dxx/(N_Range*Extend[0]); dy = 1.5*Dyy/(N_Range*Extend[1]); dz = 1.5*Dzz/(N_Range*Extend[2]);

//iid=Number(Atoms.children[0].id);

}

//---------------------------------------------------------------------------------------------------------
function Draw_Links()
{

	for (var i=0; i<Links[t_i].length; i++)
	{

		var EB_points = [];

		EB_points[0]= new THREE.Vector3(P_Atoms[t_i][Links[t_i][i][0]][3] , P_Atoms[t_i][Links[t_i][i][0]][4] , P_Atoms[t_i][Links[t_i][i][0]][5]) ;
		EB_points[1]= new THREE.Vector3(P_Atoms[t_i][Links[t_i][i][1]][3] , P_Atoms[t_i][Links[t_i][i][1]][4] , P_Atoms[t_i][Links[t_i][i][1]][5]) ;

		Link[i] = new THREE.Mesh( new THREE.TubeGeometry( new THREE.CatmullRomCurve3(EB_points), 0, 0.1, 6, true ), new THREE.MeshStandardMaterial( { color: EBeam_color, transparent:true, opacity:0.8 } ) );

		Link_Group.add(Link[i]);
		//scene.add(Link[i]);
	}

}

//---------------------------------------------------------------------------------------------------------
function Define_P_Atoms(Def_N_Atom)
{

	P_Atoms=[];
	P_Atoms= new Array(1) ;

for (var i=0; i<N_t ; i++)
{
	P_Atoms[i] = new Array(Def_N_Atom);
	for (var j=0; j<Def_N_Atom; j++)
	{
		P_Atoms[i][j] = new Array(7);
	}
}


}
//---------------------------------------------------------------------------------------------------------
function Define_Links()
{

	for (var i=0; i<N_t ; i++)
	{
		Links[i] = new Array(N_links);
		for (var j=0; j<N_links; j++)
		{
			Links[i][j] = new Array(3);
		}
	}
}

function removeAllObjects()
{
	scene.remove( Atoms ); Atoms = new THREE.Object3D();
	scene.remove( Link_Group ); Link_Group = new THREE.Object3D();

	aBoxLine = new THREE.Object3D();

	scene.remove( BoxLineGroup ); BoxLineGroup = new THREE.Object3D();

	scene.remove(BoxGroup); BoxGroup = new THREE.Object3D();

	for (var i=0; i<3;i++){ scene.remove(plateViewers[i]); scene.remove(arrow_plates[i]);}

}
function CBox_replace_Atom()
{
}
function getStructure()
{

return 'This is a Structure';

}
//---------------------------------------------------------------------
function onDocumentMouseMove( event )
{

	event.preventDefault();

	mouse.x = ( event.clientX / scene_width ) * 2 - 1;
	mouse.y = - ( event.clientY / scene_height ) * 2 + 1;

	raycaster.setFromCamera( mouse, camera );

	var intersects = raycaster.intersectObjects( Atoms.children );

	if ( intersects.length > 0 )
	{
		if ( INTERSECTED != intersects[ 0 ].object )
		{

			if ( INTERSECTED ) INTERSECTED.material.color.setHex( INTERSECTED.currentHex );

			INTERSECTED = intersects[ 0 ].object;
			INTERSECTED.currentHex = INTERSECTED.material.color.getHex();
			//INTERSECTED.material.emissive.setHex( 0x666666 );
			INTERSECTED.material.color.setHex( 0xffffff );

			insec_name=Number(INTERSECTED.name);
			//insec_name=Number(intersects[ 0 ].object);

			text_Atom_Name.value = P_Atoms[0][nonz_i[insec_name]][2];

			text_x.value = P_Atoms[0][nonz_i[insec_name]][3];
			//text_x.value         = INTERSECTED.name;
			text_y.value = P_Atoms[0][nonz_i[insec_name]][4];
			text_z.value = P_Atoms[0][nonz_i[insec_name]][5];

			container_elem.style.cursor = 'pointer';

		}

	}
	else
	{
			if ( INTERSECTED ) INTERSECTED.material.color.setHex( INTERSECTED.currentHex );

			//text_Atom_Type.value = "";
			text_Atom_Name.value = "";

			text_x.value = "";
			text_y.value = "";
			text_z.value = "";

			INTERSECTED = null;

			container_elem.style.cursor = 'default';

	}

	//movePlate('xy');
}
//---------------------------------------------------------------------
function onDocumentMouseOver( event )
{
	event.preventDefault();

	//movePlate('xy');
}
//-------------------------------
function onDocumentMouseClick()
{
	if ( INTERSECTED && document.getElementById('CBox_replace_Atom').checked==true)
	{
		var Replace_Atom_name = text_replace_Atom.value.trim();

		var N_spe;

		if (Replace_Atom_name === text_Atom_Name.value )
		{
			alert("same atom");
			return;
		}
		else if (Replace_Atom_name === "" )
		{
			P_Atoms[0][insec_name][6]=1;

			Is_N_spe[P_Atoms[0][insec_name][0]]--;

			if (Is_N_spe[P_Atoms[0][insec_name][0]]==0) { N_Species--; Species_DelOrder[P_Atoms[0][insec_name][0]]=1; }

			Atoms.children[insec_name].visible=false;

			Real_N_Atoms--;
		}
		else
		{
			// "-" check

			var Replace_Atom_name_RC = Replace_Atom_name;

			var msign = Replace_Atom_name.indexOf("-");
			Addition_Atom_i=0;
			if (msign != -1) { Replace_Atom_name_RC = Replace_Atom_name.substring(0,msign); }
		    for (var i=1; i<119; i++) { if (Replace_Atom_name_RC===Atom_Name[i]) { Gra_Atom_i=i; break;} }
			if (msign != -1 && Replace_Atom_name !="In-4d" && Replace_Atom_name !="Ga-3d" ) { Addition_Atom_i=200;} else { Addition_Atom_i = Gra_Atom_i; }

			if (Addition_Atom_i==0) {alert("no atom"); return;}

			 var F_Add_Species = 1;
			 var add_Species;

				for (var i=1; i<=T_N_Species; i++)
				{
					if (Species_DelOrder[i]==1) continue;
					if (Replace_Atom_name===Species_Name[i])
					{
						var insec_spe = P_Atoms[0][insec_name][0] ;

						F_Add_Species=0; add_Species=i;
						Is_N_spe[add_Species]++ ;
						Is_N_spe[insec_spe]-- ;
						if (Is_N_spe[insec_spe]==0) {N_Species--; Species_DelOrder[insec_spe] =1;}
						break;
					}
				}

				if (F_Add_Species==1)
				{
					if (Addition_Atom_i==200) { Mx_Species_Number++; Addition_Atom_i=Mx_Species_Number ; }

					var insec_spe = P_Atoms[0][insec_name][0] ;

					N_Species++; T_N_Species++;
					Species_Order[T_N_Species] = T_N_Species;
					Species_Number[T_N_Species] = Addition_Atom_i;
					Species_Name[T_N_Species] = Replace_Atom_name;

					add_Species=T_N_Species;
					Is_N_spe[add_Species] =1;
					Is_N_spe[insec_spe]--;
					if (Is_N_spe[insec_spe]==0) {N_Species--; Species_DelOrder[insec_spe] =1;}

				}


			P_Atoms[0][insec_name][0]=add_Species;
			P_Atoms[0][insec_name][1]=Addition_Atom_i;
			P_Atoms[0][insec_name][2] = Replace_Atom_name ;

			Atoms.children[insec_name].geometry = new THREE.SphereGeometry(Atom_R[Gra_Atom_i],15,15);
			INTERSECTED.material.color.setHex( '0x'+Atom_color[Gra_Atom_i] );
			INTERSECTED.currentHex = INTERSECTED.material.color.getHex();

		}

		   if (F_ini_Box ===0)
	        {
	        	F_ini_Box = 1;
	        	xmx=xmx*Extend[0]; ymx=ymx*Extend[1]; zmx=zmx*Extend[2];
	        	Dxx = xmx-xmn; Dyy = ymx-ymn; Dzz = zmx-zmn;
	        	for (var j=0; j<=2; j++) { LatticeVector[j]=LatticeVector[j]*Extend[j]; Extend[j]=1; }
	    		drawBox();
	        }

		struc_string="";

		struc_string+="NumberOfAtoms "+Real_N_Atoms+"\n";
		struc_string+="NumberOfSpecies "+N_Species+"\n";

		struc_string+="%block ChemicalSpeciesLabel\n";
		for ( var i=1; i<=T_N_Species; i++ )
		{
			if (Species_DelOrder[i]==1) continue;
			struc_string+=i + " " + Species_Number[i] + " " + Species_Name[i]+"\n";

		}

		struc_string+="%endblock ChemicalSpeciesLabel\n";

		struc_string+="%block LatticeVectors\n";
		struc_string+=LatticeVector[0]*Extend[0]+" 0 0\n";
		struc_string+="0 "+LatticeVector[1]*Extend[1]+" 0\n";
		struc_string+="0 0 "+LatticeVector[2]*Extend[2]+"\n";
		struc_string+="%endblock LatticeVectors\n";

		struc_string+="%block AtomicCoordinatesAndAtomicSpecies\n";

		for ( var i=0; i<N_Atoms; i++ )
		{
			if (P_Atoms[0][i][6]==1) continue;
			struc_string+=(P_Atoms[0][i][3]/mmx).toFixed(5) +" "+(P_Atoms[0][i][4]/mmx).toFixed(5)+" "+(P_Atoms[0][i][5]/mmx).toFixed(5)+" "+ P_Atoms[0][i][0] +"\n";
		//modify
		}

		struc_string+="%endblock AtomicCoordinatesAndAtomicSpecies\n";

		struc_string+="%block SuperCell\n";
		struc_string+="1 0 0\n";
		struc_string+="0 1 0\n";
		struc_string+="0 0 1\n";
		struc_string+="%endblock SuperCell\n";

		console.log("[Atom Analyzer] mouse click event, befre fireSendStrucEvent : ", struc_string);
		fireSendStrucEvent(struc_string);


		//text_Atom_Name.value = Atom_Name[P_Atoms[0][nonz_i[insec_name]][1]];
		text_Atom_Name.value = P_Atoms[0][nonz_i[insec_name]][2];

		text_x.value = P_Atoms[0][nonz_i[insec_name]][3];
		text_y.value = P_Atoms[0][nonz_i[insec_name]][4];
		text_z.value = P_Atoms[0][nonz_i[insec_name]][5];

	}

}

function get_Struc()
{
	
	struc_string="";

	struc_string+="NumberOfAtoms "+Real_N_Atoms+"\n";
	struc_string+="NumberOfSpecies "+N_Species+"\n";

	struc_string+="%block ChemicalSpeciesLabel\n";
	for ( var i=1; i<=T_N_Species; i++ )
	{
		if (Species_DelOrder[i]==1) continue;
		struc_string+=i + " " + Species_Number[i] + " " + Species_Name[i]+"\n";

	}

	struc_string+="%endblock ChemicalSpeciesLabel\n";

	struc_string+="%block LatticeVectors\n";
	struc_string+=LatticeVector[0]*Extend[0]+" 0 0\n";
	struc_string+="0 "+LatticeVector[1]*Extend[1]+" 0\n";
	struc_string+="0 0 "+LatticeVector[2]*Extend[2]+"\n";
	struc_string+="%endblock LatticeVectors\n";

	struc_string+="%block AtomicCoordinatesAndAtomicSpecies\n";

	for ( var i=0; i<N_Atoms; i++ )
	{
		if (P_Atoms[0][i][6]==1) continue;
		struc_string+=(P_Atoms[0][i][3]/mmx).toFixed(5) +" "+(P_Atoms[0][i][4]/mmx).toFixed(5)+" "+(P_Atoms[0][i][5]/mmx).toFixed(5)+" "+ P_Atoms[0][i][0] +"\n";
	}

	struc_string+="%endblock AtomicCoordinatesAndAtomicSpecies\n";

	struc_string+="%block SuperCell\n";
	struc_string+="1 0 0\n";
	struc_string+="0 1 0\n";
	struc_string+="0 0 1\n";
	struc_string+="%endblock SuperCell\n";
	
	return struc_string;
	
	
}
function clear()
{
	removeAllObjects();
	initial_var();
	
}
function disable(flag)
{
	disabled=flag;
		
}

function initial_var()
{
	Real_N_Atoms=0;
	N_Species=0;

	T_N_Species=0;
	Species_DelOrder=[];
	Species_Number=[]; 
	Species_Name=[];

	LatticeVector=[]; 
	Extend=[];
	N_Atoms=0;
	
	P_Atoms=[];
mmx=0;
	
	
	
}

//-------  Addition Atom ------------------------
function CBox_Addition_Atom()
{
	var checked=document.getElementById('CBox_Addition_Atom').checked;

	if (checked==true)
	{
		var addition_atom_name = text_Addition_atom_Name.value.trim();
		var addition_atom_name_RC = addition_atom_name;
		var msign = addition_atom_name.indexOf("-");

		Addition_Atom_i=0;
		if (msign != -1) { addition_atom_name_RC = addition_atom_name.substring(0,msign); }
	    for (var i=1; i<119; i++) { if (addition_atom_name_RC===Atom_Name[i]) { Gra_Atom_i=i; break;} }
		if (msign != -1 && addition_atom_name !="In-4d" && addition_atom_name !="Ga-3d" ) { Addition_Atom_i=200;} else { Addition_Atom_i = Gra_Atom_i; }

		if (Addition_Atom_i==0) {alert("no atom"); return;}



	  Addition_atom_Geo = new THREE.SphereGeometry(Atom_R[Gra_Atom_i],15,15);
	  Addition_atom_Mat = new THREE.MeshStandardMaterial({color: Number('0x'+Atom_color[Gra_Atom_i]) });

	  scene.remove(Addition_atom);
	  Addition_atom = new THREE.Mesh(Addition_atom_Geo, Addition_atom_Mat);

	  Addition_atom.position.set(Number(addition_x.value), Number(addition_y.value), Number(addition_z.value));

	  scene.add(Addition_atom);

	}
	else
	{

		scene.remove(Addition_atom);
	}


}

function change_Addition_atom()
{
	var checked=document.getElementById('CBox_Addition_Atom').checked;

	if (checked==true)
	{
		var addition_atom_name = text_Addition_atom_Name.value.trim();
	    var addition_atom_name_RC = addition_atom_name;
		var msign = addition_atom_name.indexOf("-");

		Addition_Atom_i=0;
		if (msign != -1) { addition_atom_name_RC = addition_atom_name.substring(0,msign); }
	    for (var i=1; i<119; i++) { if (addition_atom_name_RC===Atom_Name[i]) { Gra_Atom_i=i; break;} }
		if (msign != -1 && addition_atom_name !="In-4d" && addition_atom_name !="Ga-3d" ) { Addition_Atom_i=200;} else { Addition_Atom_i = Gra_Atom_i; }

	  if (Addition_Atom_i==0) {alert("no atom"); return;}

	  Addition_atom_Geo = new THREE.SphereGeometry(Atom_R[Gra_Atom_i],15,15);
	  Addition_atom_Mat = new THREE.MeshStandardMaterial({color: Number('0x'+Atom_color[Gra_Atom_i]) });

	  scene.remove(Addition_atom);
	  Addition_atom = new THREE.Mesh(Addition_atom_Geo, Addition_atom_Mat);
	  Addition_atom.position.set(Number(addition_x.value),Number(addition_y.value), Number(addition_z.value));

	  scene.add(Addition_atom);

	}

}
//-------  Addition Atom ------------------------
function Button_Addition_Atom()
{
	var checked=document.getElementById('CBox_Addition_Atom').checked;

	var addition_atom_name = text_Addition_atom_Name.value.trim();

	if (checked)
	{

		 var F_Add_Species=1;
		 var add_Species;

			for (var i=1; i<=T_N_Species; i++)
			{
				if (Species_DelOrder[i]==1) continue;
				if (addition_atom_name===Species_Name[i]) {F_Add_Species=0; add_Species=i; break;}

			}

			if (F_Add_Species==1)
			{

				if (Addition_Atom_i==200) { Mx_Species_Number++; Addition_Atom_i=Mx_Species_Number ; }

				N_Species++; T_N_Species++;
				Species_Order[T_N_Species] = T_N_Species;
				Species_Number[T_N_Species] = Addition_Atom_i;
				Species_Name[T_N_Species] = addition_atom_name;

				add_Species=T_N_Species;

				//alert(Addition_Atom_i+" "+addition_atom_name);

			}

			//alert(Addition_Atom_i);

		var i;

		i = N_Atoms;

	    P_Atoms[0][i][0]=add_Species;
		P_Atoms[0][i][1]=Addition_Atom_i;
		P_Atoms[0][i][2]=addition_atom_name;
		P_Atoms[0][i][3]=Number(text_Addition_atom_x.value);
		P_Atoms[0][i][4]=Number(text_Addition_atom_y.value);
		P_Atoms[0][i][5]=Number(text_Addition_atom_z.value);
		P_Atoms[0][i][6]=0;

		atom_Geo[i] = new THREE.SphereGeometry(Atom_R[Gra_Atom_i],15,15);
	    atom_Mat[i] = new THREE.MeshStandardMaterial({color: Number('0x'+Atom_color[Gra_Atom_i]) });
	    atom[i] = new THREE.Mesh(atom_Geo[i],atom_Mat[i]);

	    atom[i].position.set(P_Atoms[0][i][3], P_Atoms[0][i][4], P_Atoms[0][i][5]);
	    atom[i].castShadow = true;

	    scene.remove(Addition_atom);

	    //---------------------------------
	    scene.remove(Atoms);
	    Atoms=[];
	    Atoms = new THREE.Object3D();

	    for (var j=0; j<N_Atoms+1; j++)
	   {
	    		if (P_Atoms[0][j][6]==1) atom[j].visible=false;
	   		Atoms.add(atom[j]);
	   }

	    for (var j=0; j<N_Atoms+1; j++)
	    {
	 	    	Atoms.children[j].name=j;
	    }

	    scene.add(Atoms);

	    nonz_i[i]=i;

		 document.getElementById('CBox_Addition_Atom').checked=false;

		 //----------------------------------------
		 //Is_N_spe[P_Atoms[0][N_Atoms][0]] ++;
		 Is_N_spe[add_Species] ++;

		 N_Atoms++;

		 Real_N_Atoms ++;

		//----------------------------------------------------------

		 if (F_ini_Box ===0)
		{
			F_ini_Box = 1;

			xmx=xmx*Extend[0]; ymx=ymx*Extend[1]; zmx=zmx*Extend[2];
			Dxx = xmx-xmn; Dyy = ymx-ymn; Dzz = zmx-zmn;
			for (var j=0; j<=2; j++) { LatticeVector[j]=LatticeVector[j]*Extend[j]; Extend[j]=1; }

	   	   drawBox();
		}

		//----------

		 struc_string="";

			struc_string+="NumberOfAtoms "+Real_N_Atoms+"\n";
			struc_string+="NumberOfSpecies "+N_Species+"\n";

			struc_string+="%block ChemicalSpeciesLabel\n";
			for ( var i=1; i<=T_N_Species; i++ )
			{
				if (Species_DelOrder[i]==1) continue;
				struc_string+=i + " " + Species_Number[i] + " " + Species_Name[i]+"\n";

				//alert(Species_Number[i]+" "+Species_Name[i]+T_N_Species);
			}

			struc_string+="%endblock ChemicalSpeciesLabel\n";

			struc_string+="%block LatticeVectors\n";
			struc_string+=LatticeVector[0]*Extend[0]+" 0 0\n";
			struc_string+="0 "+LatticeVector[1]*Extend[1]+" 0\n";
			struc_string+="0 0 "+LatticeVector[2]*Extend[2]+"\n";
			struc_string+="%endblock LatticeVectors\n";

			struc_string+="%block AtomicCoordinatesAndAtomicSpecies\n";

			for ( var i=0; i<N_Atoms; i++ )
			{
				if (P_Atoms[0][i][6]==1) continue;
				struc_string+=(P_Atoms[0][i][3]/mmx).toFixed(5) +" "+(P_Atoms[0][i][4]/mmx).toFixed(5)+" "+(P_Atoms[0][i][5]/mmx).toFixed(5)+" "+P_Atoms[0][i][0]+"\n";	//modify

			}

			struc_string+="%endblock AtomicCoordinatesAndAtomicSpecies\n";

			struc_string+="%block SuperCell\n";
			struc_string+="1 0 0\n";
			struc_string+="0 1 0\n";
			struc_string+="0 0 1\n";
			struc_string+="%endblock SuperCell\n";

			console.log("[ATOM ANALYZER] file changed data :", struc_string);

			fireSendStrucEvent(struc_string);
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

	document_string +=String_Atom_spe_P();
	document_string +="<div id='Draw_V_Rho' style=\"border:1px solid #aaaaaa;\">";
//	document_string +="<input type='radio' id='Draw_V'   name='Draw_VR' checked='checked' onclick=\"Sel_Draw_VR(0)\"> Potential";
	//document_string +="<input type='radio' id='Draw_Rho' name='Draw_VR'                   onclick=\"Sel_Draw_VR(1)\"> Charge density";
	document_string +="</div>";

	if (ON_save_P==1 || ON_save_R==1)
	{
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
	document_string +="<td colspan='1'> <input id='PxyRange' type='range' style='width:90%;' value='0' min='1' max='"+Nz+"' step='1' oninput=\"movePlate('xy')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/> </td>";
	document_string +="<td colspan='1'> <input id='PxyOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput=\"changeOpacity('xy')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/> </td>";
	document_string +="</tr> ";
	document_string +="<tr> ";
	document_string +="<td colspan='1'> <SPAN style='font-size: 10pt'> Pyz </SPAN> </td>";
	document_string +="<td colspan='1'> <input id='PyzRange' type='range' style='width:90%;' value='0' min='1' max='"+Nx+"' step='1' oninput=\"movePlate('yz')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/> </td>";
	document_string +="<td colspan='1'> <input id='PyzOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput=\"changeOpacity('yz')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/> </td>";
	document_string +="</tr>";
	document_string +="<tr> ";
	document_string +="<td colspan='1'> <SPAN style='font-size: 10pt'> Pzx </SPAN> </td>";
	document_string +="<td colspan='1'> <input id='PzxRange' type='range' style='width:90%;' value='0' min='1' max='"+Ny+"' step='1' oninput=\"movePlate('zx')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/> </td>";
	document_string +="<td colspan='1'> <input id='PzxOpacity' type='range' style='width:90%;' value='0.5' min='0' max='1' step='0.1' oninput=\"changeOpacity('zx')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/> </td> ";
	document_string +="</tr>";
	document_string +="</table>";

	

	document_string +="<table> ";
	document_string +="<tr>";
	document_string +="<td>Color</td>";
	if (ON_save_P==1)	document_string +="<td><input type='radio' id='Draw_V' name='Draw_VR' checked='checked' onclick=\"Sel_Draw_VR(0)\"> Potential(V)</td>";

//		document_string +="<td>Potential(V)</td>";
	if (ON_save_R==1)	document_string +="<td><input type='radio' id='Draw_Rho' name='Draw_VR' onclick=\"Sel_Draw_VR(1)\"> Charge density(C/m^3)</td>";

//		document_string +="<td>Charge density(C/m^3)</td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td rowspan='11'><img src="+imagePath+" width='50' height='231'></td>";
	if (ON_save_P==1)	document_string +="<td>"+Pallet_P[0].toExponential(2)+"</td>";
	if (ON_save_R==1)	document_string +="<td>"+Pallet_R[0].toExponential(2)+"</td>";
	document_string +="</tr>";

	for (var i=1; i<11; i++)
	{
		document_string +="<tr>";
		if (ON_save_P==1) document_string +="<td>"+Pallet_P[i].toExponential(2)+"</td>";
		if (ON_save_R==1) document_string +="<td>"+Pallet_R[i].toExponential(2)+"</td>";
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

	document.open();
	document_string +="<table style=\"width:100%; border: 1px solid #444444; \"> ";
	document_string +="<tr>";
	document_string +="<td width='80%'>";
	document_string +="<div id=\"container\" style=\"width:100%;margin: auto;\"> <\div>";
	document_string +="</td>";
	document_string +="<td width='20%'>";
	document_string +=String_Atom_spe_P();

	document_string +="<br>";
	document_string +="<hr>";
	document_string +="<br>";

	document_string +="<table >";
	document_string +="<tr>";
	document_string +="<td colspan='2'>";
	document_string +="<input type='checkbox' id='CBox_replace_Atom' onclick='CBox_replace_Atom()'> Substitution with";
	document_string +="</td>";
	document_string +="<td>";
	document_string +="<input id='TBox_replace_Atom' type='text' value='' size='2' >";
	document_string +="</td>";
	document_string +="</tr>";
	document_string +="</table>";

	document_string +="<br>";
	document_string +="<hr>";
	document_string +="<br>";

	document_string +="<table>";
	document_string +="<tr>";
	document_string +="<td colspan='1'>";
	document_string +="<input type='checkbox' id='CBox_Addition_Atom' onclick='CBox_Addition_Atom()'> <button onclick=\"Button_Addition_Atom()\">Add an atom</button> ";
	document_string +="</td>";
	document_string +="<td>";
	document_string +="<input id='Addition_atom_Name' type='text' value='H' size='2' oninput=\"change_Addition_atom()\">";
	document_string +="</td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td>x=<input id='Addition_atom_x' type='text' value='' size='4' ></td>";
	document_string +="<td><input id='Range_addition_x' type='range' style='width:90%;' value='0' min='0' max='"+((N_Range+1)*Extend[0])+"' step='1' oninput=\"move_addition_atom()\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/></td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td>y=<input id='Addition_atom_y' type='text' value='' size='4' ></td>";
	document_string +="<td><input id='Range_addition_y' type='range' style='width:90%;' value='0' min='0' max='"+((N_Range+1)*Extend[1])+"' step='1' oninput=\"move_addition_atom()\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/></td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td>z=<input id='Addition_atom_z' type='text' value='' size='4' ></td>";
	document_string +="<td><input id='Range_addition_z' type='range' style='width:90%;' value='0' min='0' max='"+((N_Range+1)*Extend[2])+"' step='1' oninput=\"move_addition_atom()\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/></td>";
	document_string +="</tr>";
	document_string +="</table > ";

	document_string +="</td>";

	document_string +="</tr>";
	document_string +="</table > ";
//	<input id='PxyRange' type='range' style='width:90%;' value='0'  min='1' max='"+Nz+"' step='1' oninput=\"movePlate('xy')\" onmouseover=\"OFF_MouseMove()\" onmouseleave=\"ON_MouseMove()\" autocomplete='off'/>
	document.write(document_string);

	document.close();

}

function String_Atom_spe_P()
{
	var document_string="";

//	document_string +="<div style=\"border:1px solid #aaaaaa;\">	";
//	document_string +="<div>";
	document_string +="<table style='text-align:center;'> ";
	document_string +="<tr>";
	document_string +="<td colspan='2'> Atom information</td> ";
	document_string +="</tr>";

	document_string +="<tr>";
	document_string +="<td>Species</td> <td><input id='Atom_Name' type='text' value='' size='5' ></td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td>x</td> <td><input id='position_x' type='text' value='' size='5' ></td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td>y</td> <td><input id='position_y' type='text' value='' size='5' ></td>";
	document_string +="</tr>";
	document_string +="<tr>";
	document_string +="<td>z</td> <td><input id='position_z' type='text' value='' size='5' ></td>";
	document_string +="</tr>";
	document_string +="</table > ";
	//document_string +="</div>	";

	return document_string;
}

function setNamespace(ns) {
	atomAnalyzerNamespace = ns;
	  console.log("[ATOM Analyzer] set namespace ", atomAnalyzerNamespace);
}

function fireSendStrucEvent(data) {

	console.log("[ATOM ANALYZER] fire send data in viewer ", data);
	setTimeout(
			function() {
				if ( atomAnalyzerNamespace ) {
					console.log("[ATOM EDITOR]Receive_Struc_from_Viewer:" , data);
					window.parent[atomAnalyzerNamespace+'fireDataChangedEvent']( data );

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