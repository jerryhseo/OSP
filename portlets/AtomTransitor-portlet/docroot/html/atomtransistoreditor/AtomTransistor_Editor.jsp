<!DOCTYPE html>
<!-- bootstrap 10.16 -->
<html>

<head>
<!-- JQuery -->
<script src="<%= request.getContextPath() %>/js/jquery/jquery-2.2.3.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery/jquery-ui.min.js"></script>
<script src="<%= request.getContextPath() %>/js/jquery/jquery.blockUI.js"></script>
<link href="<%= request.getContextPath() %>/css/osp-editor.css" rel="stylesheet" type="text/css" />

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
	.inner-canvas {margin: 0;padding: 0;width:100%;	height:100%;overflow:none;	}
</style>
</head>

<body style="width:100%; height:100%">
<button onclick="Set_Ini_Parameters()">Parameter setting completed</button><br>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA FDF file </font> </b> <br>
</a><DIV style="display:none">
<textarea id="fdf_text" cols="100" rows="20" onchange="fireDataChangedEvent()">
</textarea>

</div>

<!-- 

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA include1 </font></b><br>
</a><DIV style="display:none">
<textarea id="include1" cols="100" rows="20" onchange="fireDataChangedEvent()">
</textarea>

</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA include2 </font> </b> <br>
</a><DIV style="display:none">
<textarea id="include2" cols="100" rows="20" onchange="fireDataChangedEvent()">
</textarea>

</div>
 -->


<script>
//today 20190722
var fdf_text="";

var namespace;

var z_scal=9.400000e-05;
var um = 1e-6;

var ti=0;

var ti_dummy=0;

var N_struc_param=11;

var dev_struc=0;

// From --- middle -----
var Nx, Ny, Nz, x_scal, y_scal, z_scal, N_nod, N_t;
var Norm_dx, Norm_dy, Norm_dz;
var xmx=-1e1000, ymx=-1e1000, zmx=-1e1000;
var xmn= 1e1000, ymn= 1e1000, zmn= 1e1000;
var dx, dy, dz;

var Dxx, Dyy, Dzz;

var LatticeVector = new Array(3);
var Extend = new Array(3);
Extend[0]=0; Extend[1]=0; Extend[2]=0;
var N_Atoms=0;
var scale_fac=1.0 ;
var R_Bohr = 0.53;
var Lattice_C ;

var target_x, target_y, target_z;
var camera_x, camera_y, camera_z;

function setNamespace(ns) {
namespace = ns;
}

	
function disableControls(flag){
	// disable all controls if flag is true otherwise enable all controls.
	
	
	document.getElementById('fdf_text').disabled =flag ;
}


function fireDataChangedEvent() {
	
		setTimeout(
				function() {
					if ( namespace ) {
						var data = getParameters();
						
					//	disableControls(true);

						window.parent[namespace+'fireDataChangedEvent']( data );
					}
					else {
						fireDataChangedEvent();
					}
				},
				10
		);
	}


function Set_Ini_Parameters()
{		
		var data = document.getElementById("fdf_text").value;
		
		data=data.replace(/\t/g, " ");
		data=data.replace(/ +/g, " "); // several space -> one space
		
		var lines = data.split('\n');
		
		var fin_i=0;
		var AtomicCoordinatesFormat='' ;
		
		var Lp_x, Lp_y, Lp_z;
		

		 
			for ( var index=0; index<lines.length; index++ )
			{				
				var line = lines[index].trim();

				if ( !line ) continue;

			    var keyVal = line.split(' ');

				if ( keyVal[0] === 'NumberOfAtoms') {N_Atoms = Number(keyVal[1]); }								
				if ( keyVal[0] === 'AtomicCoordinatesFormat'){ AtomicCoordinatesFormat = keyVal[1];  }
			
				if ( keyVal[0] === 'LatticeConstant') { Lattice_C = Number(keyVal[1]); unit = keyVal[2]; }

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
			
			
			     if(AtomicCoordinatesFormat==='Bohr'        || AtomicCoordinatesFormat==='NotScaledCartesianBohr') scale_fac = R_Bohr;
			else if(AtomicCoordinatesFormat==='Ang'         || AtomicCoordinatesFormat==='NotScaledCartesianAng')  scale_fac = 1.0;
			else if(AtomicCoordinatesFormat==='ScaledCartesian')                                                   scale_fac = Lattice_C;
			else if(AtomicCoordinatesFormat==='Fractional'  || AtomicCoordinatesFormat==='ScaledByLatticeVectors') scale_fac = Lattice_C;
			else scale_fac=1.0;
			

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
	 	
		Lp_x=LatticeVector[0]*Lattice_C;
		Lp_y=LatticeVector[1]*Lattice_C; 
		Lp_z=LatticeVector[2]*Lattice_C;
		
		
		fireDataChangedEvent();
}
//----------------------------------------------------
function parseAtomCoor_input ( dataLines )
	{
		
	//	console.log(dataLines);
		
		var N_Atoms_u=dataLines.length;
		
		var isName;

		var ai=0;

		var Lp_x=Lattice_C*LatticeVector[0], Lp_y=Lattice_C*LatticeVector[1], Lp_z=Lattice_C*LatticeVector[2];
		
		N_Atoms=N_Atoms*Extend[0]*Extend[1]*Extend[2];
		
		Real_N_Atoms=N_Atoms;

		for ( var i=0; i<N_Atoms_u; i++ )
		{
			var avalue = dataLines[i].trim().split(' ');
			
			if (isNaN( Number(avalue[0])) ==false ) isName=0; else isName=1;
			
			var xi = Number(avalue[0+isName])*scale_fac;
			var yi = Number(avalue[1+isName])*scale_fac;
			var zi = Number(avalue[2+isName])*scale_fac;
			
			//console.log("positions --->>.",xi, yi, zi);
			
			if(xi > xmx) xmx=xi; if(xi < xmn) xmn=xi;  
			if(yi > ymx) ymx=yi; if(yi < ymn) ymn=yi;
			if(zi > zmx) zmx=zi; if(zi < zmn) zmn=zi;

		}
		
		//alert("editor "+scale_fac);
		
		//console.log("Extend --->>.", Extend[0], Extend[1], Extend[2]);
		
		var xmx_1=xmx, ymx_1=ymx, zmx_1=zmx;
		var xmn_1=xmn, ymn_1=zmn, zmn_1=zmn;

		for (var ix=0; ix<Extend[0] ; ix++)
		{
			for (var iy=0; iy<Extend[1] ; iy++)
			{
				for (var iz=0; iz<Extend[2] ; iz++)
				{
					if (ix==0 && iy==0 && iz==0) continue;
					
					for ( var i=0; i<N_Atoms_u; i++ )
					{
						var xi, yi, zi;
						
						xi = xmx_1 + Lp_x*ix; yi = ymx_1 + Lp_y*iy; zi = zmx_1 + Lp_z*iz;
						
					//	console.log(" xi max", xi, yi, zi);
						
						if(xi > xmx) xmx=xi; if(yi > ymx) ymx=yi; if(zi > zmx) zmx=zi; 
						
						xi = xmn_1 + Lp_x*ix; yi = ymn_1 + Lp_y*iy; zi = zmn_1 + Lp_z*iz;
						
					//	console.log(" xi min", xi, yi, zi);
						
						if(xi < xmn) xmn=xi; if(yi < ymn) ymn=yi; if(zi < zmn) zmn=zi; 

						
						
					}
				}
			}

		}
		
		Dxx = xmx-xmn; Dyy = ymx-ymn; Dzz = zmx-zmn;
		
		target_x = Dxx *0.5 ; target_y = Dyy *0.5 ; target_z = Dzz *0.5;
		
		camera_x = (Dyy+Dzz)+Dxx*0.5; 
		camera_y = (Dzz+Dxx)+Dyy*0.5; 
		camera_z = (Dxx+Dyy)+Dzz*0.5; 
		
	//	alert(xmx +" "+ ymx +" "+ zmx +" "+ xmn +" "+ ymn +" "+ zmn);
		
	//	alert(camera_x +" "+ camera_y+" "+ camera_z);
	//	alert(target_x +" "+ target_y+" "+ target_z);
	
	//	console.log("max  --->>", xmx, ymx, zmx, xmn, ymn, zmn );
		
		
		
		
	



	};
//----------------------------------------------------
function parseSuperCell ( dataLines ) {

	//dataLines.replace( /\t/gi, " ");

	for ( var i=0; i<3; i++ ){ Extend[i]=1; var avalue = dataLines[i].trim().split(' '); Extend[i]=avalue[i]; }

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
//----------------------------------------------------
	
	function setParameters(data, version)
	{
      	
		document.getElementById("fdf_text").value = data;
		
	}
	
	function getParameters()
	{
		//var start = Date.now();
		
		  var dummy;
		  var dummy0, dummy1, dummy2, dummy3;
		  var string="";
		  
		  string+= document.getElementById("fdf_text").value ;
		 
		
	    return string;
	    
	}
	

</script>
</body>
</html>