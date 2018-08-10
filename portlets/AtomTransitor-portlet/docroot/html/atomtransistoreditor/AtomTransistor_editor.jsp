<!DOCTYPE html>

<html>

<head>
<!-- JQuery -->
<script src="<%=request.getContextPath()%>/js/jquery/jquery-2.2.3.min.js" ></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery-ui.min.js" ></script>
<script src="<%=request.getContextPath()%>/js/jquery/jquery.blockUI.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/osp-editor.css"/>


<link type="text/css" href="<%=request.getContextPath()%>/js/jquery/jquery-ui.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css">
<link href="<%=request.getContextPath()%>/js/jquery/bootstrap-toggle.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery/bootstrap-toggle.min.js"></script>

<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/js/jquery/bootstrap.min.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery/bootstrap.min.js"></script>
	
<style>
	body{ background-color: rgb(255,255,255); }
	canvas{ background-color: white; float: left; }
</style>
</head>

		
<body style="width:100%; height:100%">


<textarea id='fdf_file' style="width:100%; height:100%"  rows="60" cols="80" style="font-family:Courier New;font-weight: bold; font-size: 14px;" oninput="javascript:fireTextChangedEvent()" ></textarea>

<script>
var fdf_text="";

var namespace;

var z_scal=9.400000e-05;
var um = 1e-6;

var ti=0;

var ti_dummy=0;


var inputs = new Array();

// From --- middle -----
 var Nx, Ny, Nz, x_scal, y_scal, z_scal, N_nod, N_t;
 var Norm_dx, Norm_dy, Norm_dz;
 var xmx, ymx, zmx;
 var xmn, ymn, zmn;
 var dx,  dy,  dz;
 

function setNamespace(ns) {
  namespace = ns;
}
function fireTextChangedEvent(){
	console.log('[ATOM EDITOR] fireTextChangedEvent() ', namespace);
	setTimeout(
		function(){
			if( namespace ){
				var data = getParameters();
				console.log('[ATOM EDITOR] fireTextChangedEvent() ', data);
				window.parent[namespace+'fireTextChangedEvent']( data );
			}
			else{
				fireTextChangedEvent();
			}
		},
		10
	);
}

function load_FDF_S( data ){
	document.getElementById('fdf_file').value=data;
}
	
function load_struc_from_P( data ){
	var replace_string = new Array();
	var replace_i=0;
	var N_atoms, N_Species;
	var lines = data.split('\n');
     	
     	for( var index=0; index<lines.length; index++ )
     	{
 			var line = lines[index].trim();				
 		 			
 			line=line.replace(/ +/g, " "); 			
 			var keyVal = line.split(' '); 			
 			if(keyVal[0] === 'NumberOfAtoms'  ) { replace_i=0; N_atoms   = Number(keyVal[1]); replace_string[replace_i]=lines[index]+"\n"; }			
			if(keyVal[0] === 'NumberOfSpecies') { replace_i=1; N_Species = Number(keyVal[1]); replace_string[replace_i]=lines[index]+"\n"; } 			
 		}
     	
     	for( var index=0; index<lines.length; index++ ){
 			var line = lines[index].trim();				
 			if( !line )	continue;
 			
 			line=line.replace(/ +/g, " "); 			
 			var keyVal = line.split(' '); 			
			
 			if(keyVal[0] === '%block' && keyVal[1] === 'ChemicalSpeciesLabel') 
 			{ 	
 				replace_i=2; replace_string[replace_i]=""; 			
 			    for(var i=0; i<N_Species+2 ;i++) {replace_string[replace_i]+=lines[index+i]+"\n"; }
 			    
 			 
 			} 						
 			if(keyVal[0] === '%block' && keyVal[1] === 'LatticeVectors')
 			{
 				replace_i=3; 
 				replace_string[replace_i]=""; 			
 				for(var i=0; i<5 ;i++)           {replace_string[replace_i]+=lines[index+i]+"\n"; }
 			
 			}						
 			if(keyVal[0] === '%block' && keyVal[1] === 'AtomicCoordinatesAndAtomicSpecies')
 			{
 				replace_i=4; 				
 				replace_string[replace_i]="";
 				for(var i=0; i<N_atoms+2 ;i++)   {replace_string[replace_i]+=lines[index+i]+"\n"; }
 				
 			
 			}			
 			if(keyVal[0] === '%block' && keyVal[1] === 'SuperCell')
 			{
 				replace_i=5;
 				replace_string[replace_i]="";
 				for(var i=0; i<5 ;i++)           {replace_string[replace_i]+=lines[index+i]+"\n"; } 			
 			}
 			
 		}
     	
     	     	
     	var N_atoms_old, N_Species_old;
     	
     	var fdf=document.getElementById('fdf_file').value;
     	
     	var modified_fdf="" ;
    	
    	lines = fdf.split('\n');   
    	
    	for( var index=0; index<lines.length; index++ ){
			var line = lines[index].trim();				
			//if( !line )	continue;
			line=line.replace(/ +/g, " ");
			
			var keyVal = line.split(' ');
			
			     if(keyVal[0] === 'NumberOfAtoms'  ) { N_atoms_old   = Number(keyVal[1]);}			
			else if(keyVal[0] === 'NumberOfSpecies') { N_Species_old = Number(keyVal[1]);}
		}
    	
    	    	
    	for( var index=0; index<lines.length; index++ ){
			var line = lines[index].trim();				
			//if( !line )	continue;
			line=line.replace(/ +/g, " ");
			
			var keyVal = line.split(' ');
			
			     if(keyVal[0] === 'NumberOfAtoms'  ) { modified_fdf += replace_string[0]; continue;}			
			else if(keyVal[0] === 'NumberOfSpecies') { modified_fdf += replace_string[1]; continue;}
			else if(keyVal[0] === '%block' && keyVal[1] === 'ChemicalSpeciesLabel') 
			{
				modified_fdf +=replace_string[2];				
				index +=N_Species_old+1;
			}					
			else if(keyVal[0] === '%block' && keyVal[1] === 'LatticeVectors')
			{
				modified_fdf +=replace_string[3];				
				index +=4;								
			}						
			else if(keyVal[0] === '%block' && keyVal[1] === 'AtomicCoordinatesAndAtomicSpecies')
			{
				modified_fdf +=replace_string[4];				
				index += N_atoms_old+1; 
			}			
			else if(keyVal[0] === '%block' && keyVal[1] === 'SuperCell')
			{
				modified_fdf +=replace_string[5];				
				index +=4;				
			}
			else
			{
				modified_fdf += lines[index]+"\n";
			}
		}
    	
    	
       
    	document.getElementById('fdf_file').value=modified_fdf;		
	}
		
	
function getParameters()
{	              
	console.log("[ATOM EDITOR] get parameter function :", document.getElementById('fdf_file').value);
	return document.getElementById('fdf_file').value;
}
	  
function get_CC_Struc()
{	              
	return document.getElementById('CC_Struc').checked;
}

</script>
</body>
</html>