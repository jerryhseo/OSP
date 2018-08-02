<!DOCTYPE html>

<html>

<head>
	<style>
			body{ background-color: rgb(255,255,255); }
			canvas{ background-color: white; float: left; }
		</style>
</head>

		
<body>
<script src="https://code.jquery.com/jquery-2.2.3.min.js" ></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js" ></script>
<link type="text/css" href="https://code.jquery.com/ui/1.11.4/themes/south-street/jquery-ui.css" rel="stylesheet" />


<textarea id='fdf_file' rows="60" cols="80" style="font-family:Courier New;font-weight: bold; font-size: 14px;" ></textarea>



<script>
//DotumChe
//oninput="fireTextChangedEvent()"
//oninput="myFunction()"


var fdf_text="";

//$("body").on('change', '#fdf_file', function(){    // 2nd (B)
//	alert( 'textarea changed');
//});


/*
$('#fdf_file').on('change',
		function(){			alert( 'textarea changed');	fireTextChangedEvent();	}
		);
*/
//document.getElementById('fdf_file').value=fdf_text;
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

function fireDataChangedEvent(){
		setTimeout(
				function(){
					if( namespace ){
						var data = getParameters();
						console.log('fireDataChangedEvent() ', data);
						window.parent[namespace+'fireDataChangedEvent']( data );
					}
					else{
						fireDataChangedEvent();
					}
				},
				10
		);
	}

	function fireTextChangedEvent(){
		setTimeout(
				function(){
					if( namespace ){
						
					
						
						var data = getParameters();
						console.log('fireTextChangedEvent() ', data);
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
	   	
		
	    return document.getElementById('fdf_file').value;

	}
	  
	  function get_CC_Struc()
	  {	              
	   	
		
	    return document.getElementById('CC_Struc').checked;

	}
	  
	  
	
	  


</script>

</body>
</html>