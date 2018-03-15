<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;">
	<head>
		<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/three.js"></script>
		<script src="<%=request.getContextPath()%>/js/ThreeBSP.js"></script>
		<script src="<%=request.getContextPath()%>/js/TrackballControls.js"></script>
		<script type="text/javascript">
var namespace;
var csvEditor;

var myDiv;
var scene;
var camera;
////var camera  = new THREE.OrthographicCamera(window.innerWidth / - 150, window.innerWidth / 150, window.innerHeight / 150, window.innerHeight / - 150,-20, 20);
var renderer;

// 0: source, 1: drain, 2: channel, 3: gate, 4: oxide
 

var Compo       = new Array();
var Color       = new Array();
var Opacity     = new Array();
var Transparent = new Array();
var Mater       = new Array();

function setNamespace( ns ){
	console.log( '[DeviceModelViewer.jsp] Set Namespace: '+ns);
	namespace = ns;
}

$(document).ready(function(){
	initialize();
	//---------------------------------------------------------------------
});
	
function initialize() {    
	myDiv = document.getElementById('WebGL-output');
	scene   = new THREE.Scene();
	
	camera  = new THREE.PerspectiveCamera(45, myDiv.offsetWidth/myDiv.offsetHeight, 0.1, 1000);
	////var camera  = new THREE.OrthographicCamera(window.innerWidth / - 150, window.innerWidth / 150, window.innerHeight / 150, window.innerHeight / - 150,-20, 20);
	renderer = new THREE.WebGLRenderer();
	
	camera.aspect = myDiv.offsetWidth/myDiv.offsetHeight;
	
	
	
	// initialize renderer
	renderer.setClearColor(new THREE.Color(0xffffff, 1.0));
	renderer.setSize(myDiv.offsetWidth, myDiv.offsetHeight);
	renderer.shadowMapEnabled = true;
	renderer.sortObjects=false;

	//initialize camera

	camera.position.set(30,10,30);
	camera.updateProjectionMatrix();
	camera.rotation.set(50,10,0); 

	//initialize lights

	var ambientLight = new THREE.AmbientLight(0x666666); scene.add(ambientLight);
	var light1 = new THREE.DirectionalLight(0xffffff, 1); light1.position.set( 10,  10,  10); scene.add(light1);
	var light2 = new THREE.DirectionalLight(0xffffff, 1); light2.position.set( 10, -10, -10); scene.add(light2);

	Color[0]=0xfd4444;
	Color[1]=0xfd4444;
	Color[2]=0x997777;
	Color[3]=0x5255fb;
	Color[4]=0x995599;

	Opacity[0]=1.0;
	Opacity[1]=1.0;
	Opacity[2]=1.0;
	Opacity[3]=0.5;
	Opacity[4]=0.5;

	Transparent[0]=false;
	Transparent[1]=false;
	Transparent[2]=false;
	Transparent[3]=true;
	Transparent[4]=true;

	// Mater Definition
	for(var i=0;i<=4;i++)
	{
		Mater[i] = new THREE.MeshLambertMaterial({transparent:Transparent[i], opacity:Opacity[i] , color: Color[i] });	
	}
	    
	document.getElementById("WebGL-output").appendChild(renderer.domElement);

	//initialize trackballControls

	trackballControls = new THREE.TrackballControls(camera,renderer.domElement);
	trackballControls.rotateSpeed = 0.3;
	trackballControls.zoomSpeed   = 0.3;
	trackballControls.panSpeed    = 0.3;

	//draw the first device 

	
	//alert("initialize");
	
	window.addEventListener( 'resize', onWindowResize, false );
	
	renderer.render(scene, camera);  

	//activate animation 

	renderScene();
	 
	}      
	//-------------------------------------------------------------------------------------------------
	function renderScene() 
	{  
	    trackballControls.update();    
	    requestAnimationFrame(renderScene);  
	    renderer.render(scene, camera);  
	}   
	//----------------------------------------------------------------------------------------------------------------    
	function Draw_Device(data) 
	{
		console.log( 'Draw_Device', data );
			
		var ReR=1;   
		
		var z_T=-0;
		var x_T=0 ;
		var y_T=0 ;	
		
		var D_Si     =parseFloat(data.input1)*ReR; 
	    var D_Oxi    =parseFloat(data.input2)*ReR; 
	    var L_source =parseFloat(data.input3)*ReR; 
	    var L_drain  =parseFloat(data.input4)*ReR; 
	    var L_channel=parseFloat(data.input5)*ReR;
	    var L_Oxi    = L_channel+L_source+L_drain;
	    
	    
	    
	    var Geo       = new Array();
	    
	    var N_seg;
	    
	    var Doping_density;       
	    
	 
	    
	    if (data.input6=='Circle') {N_seg = 20; } 
	    else                        N_seg = parseInt(data.input6);
	    
	    Doping_density =parseFloat(data.input8);
	    
	    In_id    =parseInt(data.in_id);
	      
	    //--------source  --------------------------------------
	       Geo[0] = new THREE.CylinderGeometry(D_Si*0.5, D_Si*0.5, L_source, N_seg,1,false);
	      
	      Color_doped=0x060000+ (1-(Doping_density-1e18)/(1e20-1e18))*(0x00ff00)+ ((Doping_density-1e18)/(1e20-1e18))*(0x0000ff) ;
	     
	      Mater[0] = new THREE.MeshLambertMaterial({opacity:Opacity[0], color: Color_doped, transparent:false});
	      
	      scene.remove(Compo[0]);       
	      Compo[0] = new THREE.Mesh(Geo[0], Mater[0]);      
	      Compo[0].position.set(x_T,y_T, -L_channel*0.5 - 0.5*L_source+z_T);       
	      Compo[0].rotation.x=Math.PI/2;
	      scene.add(Compo[0]);
	      
	    //--------drain --------------------------------------
	      Geo[1] = new THREE.CylinderGeometry(D_Si*0.5, D_Si*0.5, L_drain, N_seg,1,false);          
	      scene.remove(Compo[1]);  
	      Compo[1] = new THREE.Mesh(Geo[1], Mater[0]);      
	      Compo[1].position.set(x_T, y_T, L_channel*0.5 + 0.5*L_drain+z_T);            
	      Compo[1].rotation.x=Math.PI/2;
	      scene.add(Compo[1]); 
	      
	      //--------channel--------------------------------------
	      Geo[2] = new THREE.CylinderGeometry(D_Si*0.5, D_Si*0.5, L_channel, N_seg,1,false);
	      scene.remove(Compo[2]);      
	      Compo[2] = new THREE.Mesh(Geo[2], Mater[2]);
	      Compo[2].position.set(x_T,y_T,z_T);        
	      Compo[2].rotation.x=Math.PI/2;
	      scene.add(Compo[2]);
	      
	      //--------gate   --------------------------------------
	      
	      Geo[3] = new THREE.CylinderGeometry((D_Si*0.5+D_Oxi)+0.5, (D_Si*0.5+D_Oxi)+0.5, L_channel, N_seg);
	     var gate_BSP = new THREE.Mesh(Geo[3], Mater[3]);     

	      Geo[4] = new THREE.CylinderGeometry((D_Si*0.5+D_Oxi), (D_Si*0.5+D_Oxi), L_channel, N_seg);  
	     var Oxi_BSP = new THREE.Mesh(Geo[4], Mater[4]);       
	         Oxi_BSP = new ThreeBSP(Oxi_BSP);
	         
	         scene.remove(Compo[3]); 
	     
	     gate_BSP = new ThreeBSP(gate_BSP).subtract(Oxi_BSP);     
	     
	     Compo[3]     = gate_BSP.toMesh(Mater[3]);    
	     Compo[3].position.set(x_T,y_T,z_T);    
	     Compo[3].rotation.x=Math.PI/2;
	     
	     scene.add(Compo[3]);
	     
	   //--------oxide   --------------------------------------
	    Geo[4]      = new THREE.CylinderGeometry((D_Si*0.5+D_Oxi), (D_Si*0.5+D_Oxi), L_Oxi, N_seg);  
	   var Oxi_hole_Geo = new THREE.CylinderGeometry(D_Si*0.5, D_Si*0.5, L_Oxi, N_seg);    
	   var Oxi_hole     = new THREE.Mesh(Oxi_hole_Geo, Mater[4]);  
	   var Oxi_hole_BSP = new ThreeBSP(Oxi_hole);        
	      
	   var OxiBSP  = new THREE.Mesh(Geo[4], Mater[4]);               
	       OxiBSP  = new ThreeBSP(OxiBSP ).subtract(Oxi_hole_BSP);    
	       
	       scene.remove( Compo[4]);       
	       Compo[4]=OxiBSP.toMesh( Mater[4]);      
	       Compo[4].position.set(x_T,y_T, (L_drain-L_source)*0.5+z_T);         
	       Compo[4].rotation.x=Math.PI/2;            
	       scene.add(Compo[4]);      
	}
	
	function onWindowResize() {

		camera.aspect = myDiv.offsetWidth / myDiv.offsetHeight;
		camera.updateProjectionMatrix();		
		renderer.setSize(myDiv.offsetWidth, myDiv.offsetHeight);
		renderer.render(scene, camera);

	}
	
</script>
		
	</head>
	<body style="height:100%;">
		<div class="wrapper" >
		<b>Device model</b>
		</div>
		
		<div style="width:100%;height:100%" id='WebGL-output'>
		</div>
	</body>

</html>
