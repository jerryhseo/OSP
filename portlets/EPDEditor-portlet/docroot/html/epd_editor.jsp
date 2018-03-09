<!DOCTYPE html>

<html>

<head>
    <title> ���ڱ� �������</title>
	
    
	<style>
			body{ background-color: rgb(255,255,255); }
			canvas{ background-color: white; float: left; }
						          
		</style>
		
 
<script type="text/javascript">
var namespace;

function setNamespace( ns ){
	namespace = ns;
}

function fireDataChangedEvent(){
	var data = getParameters();
	console.log('fireDataChangedEvent() ', data);
	/*
	setTimeout(
			function(){
				if( namespace ){
					var data = getParameters();
					window.parent[namespace+'fireDataChangedEvent']( data );
				}
				else{
					fireDataChangedEvent();
				}
			},
			10
	);
	*/
}

var z_scal=9.400000e-05;
var um = 1e-6;

var ti=0;

var ti_dummy=0;
//var x_scale=2, y_scale=0.5, z_scale=3;


var zz=0;

var Is_obj = {};

var button_obj = new Array();

var inputs = new Array();

// From --- middle -----
 var Nx, Ny, Nz, x_scal, y_scal, z_scal, N_nod, N_t;
 var Norm_dx, Norm_dy, Norm_dz;
 var xmx, ymx, zmx;
 var xmn, ymn, zmn;
 var  dx,  dy,  dz;
 
 

//---------------------------------------------
function main () 
{ 
	document.getElementById("button_obj0").style="color:#aaaaaa"; 
	document.getElementById("button_obj1").style="color:#aaaaaa"; 
	document.getElementById("button_obj2").style="color:#aaaaaa"; 
	document.getElementById("button_obj3").style="color:#aaaaaa"; 
};


//------------  ��ü �߰��ϱ� -----------------------------------------------------------------------------
function Addition_object(obj_i,input1,input2,input3,input4) 
{

     var Px = document.getElementById(input1).value*um/x_scal;
     var Py = document.getElementById(input2).value*um/y_scal;
     var Pz = document.getElementById(input3).value*um/z_scal;      
     
     switch( obj_i ){
    	 case 0:
    		 if( Is_obj.pointType1 ){
	    		 delete Is_obj.pointType1;
	    		 document.getElementById('button_obj0').style="color:#00ff00; background-color:#eeeeee;"
    		 }
    		 else{
    			 Is_obj.pointType1 = true;
    		 	document.getElementById('button_obj0').style="color:#000000; background-color:#eeeeee;";
    		 }
    		 break;
    	 case 1:
    		 if( Is_obj.pointType2 ){
	    		 delete Is_obj.pointType2;
    		 }
    		 else{
	    		 Is_obj.pointType2 = true;
    		 	document.getElementById('button_obj1').style="color:#aaaaaa; background-color:#eeeeee;";
    		 }
    		 break;
    	 case 2:
    		 if( Is_obj.sphereType1 ){
	    		 delete Is_obj.sphereType1;
    		 }
    		 else{
    		 	Is_obj.sphereType1 = true;
    		 	document.getElementById('button_obj2').style="color:#aaaaaa; background-color:#eeeeee;";
    		 }
    		 break;
    	 case 3:
    		 if( Is_obj.cubeType1 ){
	    		 delete Is_obj.cubeType1;
    		 }
    		 else{
    		 	Is_obj.cubeType1 = true;
    		 	document.getElementById('button_obj3').style="color:#aaaaaa; background-color:#eeeeee;";
    		 }
    		 break;
    	default:
    		alert('Un-recognized object: '+obj_i);
     }
     
     fireDataChangedEvent();
}
//------------  ��ü ��ġ�̵� -----------------------------------------------------------------------------
function obj_position(target, source) 
{
	target.value = source.value;    // This needs modification
	
	fireDataChangedEvent();
}



//------------  ��ü ���� -----------------------------------------------------------------------------
function obj_tranp(obj_i,input_1) 
{
	fireDataChangedEvent();
}

</script>

<script type="text/javascript">

//-----------------------------------------------------
  //-------------------------------------
  function Run_button()
  {
  
      Disable_obj() ;
   
     
                	  
  }
 //------------------------------------------------ 
  function getParameters()
  {
              
   	var params = '@EP_input\n';
           	
    params += 'obj0 = ' + (Is_obj.pointType1?1:0) + ' / '  + 
    					document.getElementById('obj0_q').value + ' / ' + 
    					document.getElementById('obj0_x').value + ' / ' + 
    					document.getElementById('obj0_y').value+ ' / ' + 
    					document.getElementById('obj0_z').value + ' / 0 /\n' ;
    params += 'obj1 = ' + (Is_obj.pointType2?1:0) + ' / '  + 
    					document.getElementById('obj1_q').value + ' / ' + 
    					document.getElementById('obj1_x').value + ' / ' + 
    					document.getElementById('obj1_y').value+ ' / ' + 
    					document.getElementById('obj1_z').value + ' / 0 /\n' ;
    params += 'obj2 = ' + (Is_obj.sphereType1?1:0) + ' / '  + 0 + ' / ' + 
    					document.getElementById('obj2_x').value + ' / ' + 
						document.getElementById('obj2_y').value+ ' / ' + 
						document.getElementById('obj2_z').value + ' / ' + 
						document.getElementById('obj2_R').value + '/\n' ;
	params += 'obj3 = ' + (Is_obj.cubeType1?1:0) + ' / ' + 0 + ' / ' + 
						document.getElementById('obj3_x').value + ' / ' + 
						document.getElementById('obj3_y').value+ ' / ' + 
						document.getElementById('obj3_z').value + ' / ' + 
						document.getElementById('obj3_R').value + '/\n' ;
	params += 'obj0_tra = ' + document.getElementById('obj0_tra').value + '/\n' ;
	params += 'obj1_tra = ' + document.getElementById('obj1_tra').value + '/\n' ;
	params += 'obj2_tra = ' + document.getElementById('obj2_tra').value + '/\n' ;
	params += 'obj3_tra = ' + document.getElementById('obj3_tra').value + '/\n' ;
	
    return params;

}

// Display functions
//------------------------------------------------------------------------

function Get_Info( data )
{

    var lines =data.split('\n');

    var N_nod;

    for( var index in lines ) // for( var index=0; index < lines.length; index++ )
    { 
           var line = lines[index].trim();

           var keyVal = line.split('=');

           switch( keyVal[0] )
           {
 
                case 'Nx'     : Nx      = Number(keyVal[1]); break;
                case 'Ny'     : Ny      = Number(keyVal[1]); break;
                case 'Nz'     : Nz      = Number(keyVal[1]); break;
                case 'x_scal' : x_scal  = Number(keyVal[1]); break;
                case 'y_scal' : y_scal  = Number(keyVal[1]); break;
                case 'z_scal' : z_scal  = Number(keyVal[1]); break;
                case 'N_nod'  : N_nod   = Number(keyVal[1]); break;
                case 'N_t'    : N_t     = Number(keyVal[1]); break;
                case 'Norm_dx': Norm_dx = Number(keyVal[1]); break;
                case 'Norm_dy': Norm_dy = Number(keyVal[1]); break;
                case 'Norm_dz': Norm_dz = Number(keyVal[1]); break;
                case 'xmx'    : xmx     = Number(keyVal[1]); break;
                case 'ymx'    : ymx     = Number(keyVal[1]); break;
                case 'zmx'    : zmx     = Number(keyVal[1]); break;
                case 'xmn'    : xmn     = Number(keyVal[1]); break;
                case 'ymn'    : ymn     = Number(keyVal[1]); break;
                case 'zmn'    : zmn     = Number(keyVal[1]); break;
                case 'dx'     : dx      = Number(keyVal[1]); break;
                case 'dy'     : dy      = Number(keyVal[1]); break;
                case 'dz'     : dz      = Number(keyVal[1]); break;
                
                case 'EP_data': load_EP_data( index+1, N_nod, data ); break;

                case 'EF_data': load_EF_data( index+1, N_nod, data ); break;
                

           }

    }

   

}

function setParameters( data ){
	data = 'obj1/ 1 / 1e-20 / 17 / 25 / 24 / 0 /\nobj2/ 1 / 1e-20 / 20 / 20 / -20 / 0 /\nobj3/ 1 / 0     / 20 / -20 / 20/ 15 /\nobj4/ 1 / 0     / -20 / 20 / 20/ 15 /\n';
	var lines = data.split('\n');
	for( var index in lines ){
		var line = lines[index].trim();
		if( line ){
			var keyVal = line.split('/');
			console.log( keyVal );
			Is_obj[index] = keyVal[1].trim();
			document.getElementById( 'obj'+index+'_q' ).value = keyVal[2].trim();
			
			document.getElementById( 'obj'+index+'_x' ).value = keyVal[3].trim();
			setRange( 'cont_obj'+index+'_x', -47, 47, 1, keyVal[3].trim()); 
			document.getElementById( 'obj'+index+'_y' ).value = keyVal[4].trim();
			setRange( 'cont_obj'+index+'_y', -47, 47, 1, keyVal[4].trim());
			document.getElementById( 'obj'+index+'_z' ).value = keyVal[5].trim();
			setRange( 'cont_obj'+index+'_z', -47, 47, 1, keyVal[5].trim());
			if( document.getElementById( 'obj'+index+'_R' ) ){
				document.getElementById( 'obj'+index+'_R' ).value = keyVal[6].trim();
				setRange( 'cont_obj'+index+'_R', -47, 47, 1, keyVal[6].trim());
			}
		}
		else{
			continue;
		}
	}
}

function setRange( targetId, min, max, step, val ){
	console.log( 'targetId: '+targetId);
	var target = document.getElementById( targetId );
	target.setAttribute('min', min);
	target.setAttribute('max', max);
	target.setAttribute('step', step);
	target.value = val;
}

//------------------------------------------------------------------------ 
function load_EP_data( startLine, size, EP_Data )
{

    for( var index=startLine; index< startLine+size; index++)
    {

         var line = EP_Data[index];

         var values = line.split(',');

         if( values.length !== 7 ) return;

         for(var j=0 ; j<=6; j++)
         {

           Vertices[0][index-startLine][j]  = parseFloat(values[j]);

         }

    }

}

//------------------------------------------------------------------------ 
function load_EF_data( startLine, size, EF_Data )
{

    for( var index=startLine; index< startLine+size; index++)
    {

         var line = EF_Data[index];

         var values = line.split(',');

         if( values.length !== 3 ) return;

         for(var j=0 ; j<=2; j++)
         {

           Grad[index-startLine][j] = parseFloat(values[j]);

         }

    }

}

  
</script>  

</head>

		
<body onLoad="main();">	


<input id="Run_button" type="button" value="Run" onclick="Run_button()">
<hr/>
<table width="270" border="0" >
	<tr> 
		<td width="36%"> 
			<input id="button_obj0" type="button" value="point1" onclick="Addition_object(0,'obj0_x','obj0_y','obj0_z', '0')">
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj0_x" type="text"  value="20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj0_x" type="range" style="width:90%;" value="0" min="-47" max="47" step="1" 
						oninput="obj_position(document.getElementById('obj0_x'),this)" /> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN> 
			<input id="obj0_q" type="text" value="1e-20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> C/m-3 </SPAN>  
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> y = </SPAN> 
			<input id="obj0_y" type="text"  value="20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj0_y" type="range" style="width:90%;" value="0" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj0_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj0_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="fireDataChangedEvent()"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj0_z" type="text"  value="20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj0_z" type="range" style="width:90%;" value="0" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj0_z'),this)"/> 
		</td> 
	</tr>
</table>
<hr/>
<table width="270" border="0" >
	<tr> 
		<td width="36%"> 
			<input id="button_obj1" type="button" value="point2" onclick="Addition_object(1,'obj1_x','obj1_y','obj1_z', '0')">
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj1_x" type="text" value= "20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj1_x" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj1_x'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN> 
			<input id="obj1_q" type="text" value="1e-20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> C/m-3 </SPAN>   
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> y = </SPAN> 
			<input id="obj1_y" type="text" value= "20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj1_y" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj1_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj1_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="fireDataChangedEvent()"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj1_z" type="text" value="-20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj1_z" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj1_z'),this)"/>
		</td> 
	</tr>
</table>
<hr/>
<table width="270" border="0" > 
	<tr> 
		<td width="36%"> 
			<input id="button_obj2" type="button" value="sphere" onclick="Addition_object(2,'obj2_x','obj2_y','obj2_z','obj2_R')">
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj2_x" type="text" value=  "20" size="1" height="1" > 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_x" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj2_x'),this)"/>
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN>
			<input id="obj2_q" type="text" value="0" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> C/m-3 </SPAN>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt">y = </SPAN> 
			<input id="obj2_y" type="text" value= "-20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_y" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj2_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj2_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="fireDataChangedEvent()"/>            
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj2_z" type="text" value=  "20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_z" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj2_z'),this)"/>
		</td> 
	</tr> 
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> Thickness <br> =3 &microm </SPAN>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> R = </SPAN> 
			<input id="obj2_R"  type="text" value=  "15" size="1" height="1">
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_R" type="range" style="width:90%;" value="15" min="0" max="47" step="1" oninput="obj_position(document.getElementById('obj2_R'),this)"/>
		</td> 
	</tr>
</table>
<hr/>
<table width="270" border="0" > 
	<tr> 
		<td width="36%"> 
			<input id="button_obj3" type="button" value="cube" onclick="Addition_object(3,'obj3_x','obj3_y','obj3_z','obj3_R')">
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj3_x" type="text" value= "-20" size="1" height="1" > 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_x" type="range" style="width:90%;" value="1" min="-47"' max="47" step="1" oninput="obj_position(document.getElementById('obj3_x'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN> 
			<input id="obj3_q" type="text" value="0" size="1" height="1"> 
			<SPAN style='font-size: 10pt'> C/m-3 </SPAN>   
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> y = </SPAN> 
			<input id="obj3_y" type="text" value=  "20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_y" type="range" style="width:90%;" value="1" min="-47"' max="47" step="1" oninput="obj_position(document.getElementById('obj3_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj3_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="fireDataChangedEvent()"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj3_z" type="text" value=  "20" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_z" type="range" style="width:90%;" value="1" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj3_z'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> Thickness <br> =3 &microm </SPAN>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> d = </SPAN> 
			<input id="obj3_R"  type="text" value=  "15" size="1" height="1"> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_R" type="range" style="width:90%;" value="15" min="0" max="47" step="1" oninput="obj_position(document.getElementById('obj3_R'),this)"/>
		</td> 
	</tr>
</table>

<script>
setParameters();
</script>

</body>
</html>