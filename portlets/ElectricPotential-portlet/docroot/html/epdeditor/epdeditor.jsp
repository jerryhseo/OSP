<!DOCTYPE html>

<html>

<head>
	<style>
			body{ background-color: rgb(255,255,255); }
			canvas{ background-color: white; float: left; }
						          
		</style>
		
</head>

		
<body>	


<hr/>
<table style="width:100%;" border="0" >
	<tr> 
		<td width="36%"> 
			<input id="button_obj0" type="button" value="point1" onclick="displayObject(0)"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj0_x" type="text"  value="20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj0_x" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" 
						oninput="obj_position(document.getElementById('obj0_x'), this);" /> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN> 
			<input id="obj0_q" type="text" value="1e-20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> C/m-3 </SPAN>  
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> y = </SPAN> 
			<input id="obj0_y" type="text"  value="20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj0_y" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" 
				oninput="obj_position(document.getElementById('obj0_y'),this);"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj0_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" 
			oninput="obj_transp(0);"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj0_z" type="text"  value="20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj0_z" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" 
			oninput="obj_position(document.getElementById('obj0_z'),this);"/> 
		</td> 
	</tr>
</table>
<hr/>
<table style="width:100%;" border="0" >
	<tr> 
		<td width="36%"> 
			<input id="button_obj1" type="button" value="point2" onclick="displayObject(1)"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj1_x" type="text" value= "20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj1_x" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj1_x'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN> 
			<input id="obj1_q" type="text" value="1e-20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> C/m-3 </SPAN>   
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> y = </SPAN> 
			<input id="obj1_y" type="text" value= "20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj1_y" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj1_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj1_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="obj_transp(1)"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj1_z" type="text" value="-20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj1_z" type="range" style="width:90%;" value="-20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj1_z'),this)"/>
		</td> 
	</tr>
</table>
<hr/>
<table style="width:100%;" border="0" > 
	<tr> 
		<td width="36%"> 
			<input id="button_obj2" type="button" value="sphere" onclick="displayObject(2)"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj2_x" type="text" value=  "20" size="1" height="1" > 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_x" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" 
			oninput="obj_position(document.getElementById('obj2_x'),this);"/>
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN>
			<input id="obj2_q" type="text" value="0" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> C/m-3 </SPAN>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt">y = </SPAN> 
			<input id="obj2_y" type="text" value= "-20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_y" type="range" style="width:90%;" value="-20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj2_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj2_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="obj_transp(2)"/>            
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj2_z" type="text" value=  "20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_z" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj2_z'),this)"/>
		</td> 
	</tr> 
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> Thickness <br> =3 &microm </SPAN>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> R = </SPAN> 
			<input id="obj2_R"  type="text" value=  "15" size="1" height="1"/>
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj2_R" type="range" style="width:90%;" value="15" min="0" max="47" step="1" oninput="obj_volume(2)"/>
		</td> 
	</tr>
</table>
<hr/>
<table style="width:100%;" border="0" > 
	<tr> 
		<td width="36%"> 
			<input id="button_obj3" type="button" value="cube" onclick="displayObject(3)"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> x = </SPAN> 
			<input id="obj3_x" type="text" value= "-20" size="1" height="1" /> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_x" type="range" style="width:90%;" value="-20" min="-47" max="47" step="1" 
				oninput="obj_position(document.getElementById('obj3_x'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> &rho; = </SPAN> 
			<input id="obj3_q" type="text" value="0" size="1" height="1"/> 
			<SPAN style='font-size: 10pt'> C/m-3 </SPAN>   
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> y = </SPAN> 
			<input id="obj3_y" type="text" value=  "20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_y" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj3_y'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<input id="obj3_tra" type="range" style="width:90%;" value="1" min="0" max="1" step="0.1" oninput="obj_transp(3)"/>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> z = </SPAN> 
			<input id="obj3_z" type="text" value=  "20" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_z" type="range" style="width:90%;" value="20" min="-47" max="47" step="1" oninput="obj_position(document.getElementById('obj3_z'),this)"/> 
		</td> 
	</tr>
	<tr> 
		<td width="36%"> 
			<SPAN style="font-size: 10pt"> Thickness <br> =3 &microm </SPAN>
		</td> 
		<td width="30%"> 
			<SPAN style="font-size: 10pt"> d = </SPAN> 
			<input id="obj3_R"  type="text" value=  "15" size="1" height="1"/> 
			<SPAN style="font-size: 10pt"> &microm </SPAN>  
		</td> 
		<td width="34%"> 
			<input id="cont_obj3_R" type="range" style="width:90%;" value="15" min="0" max="47" step="1" oninput="obj_volume(3)"/>
		</td> 
	</tr>
</table>

<script>

var namespace;

var z_scal=9.400000e-05;
var um = 1e-6;

var ti=0;

var ti_dummy=0;

var Is_obj = [false, false, false, false];

var button_obj = [
	document.getElementById("button_obj0"), 
	document.getElementById("button_obj1"), 
	document.getElementById("button_obj2"), 
	document.getElementById("button_obj3") 
];

for( var i=0; i<button_obj.length; i++ ){

	button_obj[i].style="color:#aaaaaa; background-color:#eeeeee;";
	
}

var P_textBox = [['aaa','aaa','aaa','aaa','aaa'],['aaa','aaa','aaa','aaa','aaa'],['aaa','aaa','aaa','aaa','aaa'],['aaa','aaa','aaa','aaa','aaa']];

var inputs = new Array();

// From --- middle -----
 var Nx, Ny, Nz, x_scal, y_scal, z_scal, N_nod, N_t;
 var Norm_dx, Norm_dy, Norm_dz;
 var xmx, ymx, zmx;
 var xmn, ymn, zmn;
 var dx,  dy,  dz;
 
function initialize(){
	z_scal=9.400000e-05;
	um = 1e-6;
	
	button_obj = [
		document.getElementById("button_obj0"), 
		document.getElementById("button_obj1"), 
		document.getElementById("button_obj2"), 
		document.getElementById("button_obj3") 
	];
	
	for( var i=0; i<button_obj.length; i++ ) button_obj[i].style="color:#aaaaaa; background-color:#eeeeee;";
	
	document.getElementById('obj0_x').value= 20 ; document.getElementById("cont_obj0_x").value = 20 ;
	document.getElementById('obj0_y').value= 20 ; document.getElementById("cont_obj0_y").value = 20 ;
	document.getElementById('obj0_z').value= 20 ; document.getElementById("cont_obj0_z").value = 20 ;
	document.getElementById("obj0_q").value=1e-20;
	document.getElementById("obj0_tra").value=1;
	
	document.getElementById('obj1_x').value= 20 ; document.getElementById("cont_obj1_x").value = 20 ;
	document.getElementById('obj1_y').value= 20 ; document.getElementById("cont_obj1_y").value = 20 ;
	document.getElementById('obj1_z').value=-20 ; document.getElementById("cont_obj1_z").value =-20 ;
	document.getElementById("obj1_q").value=1e-20;
	document.getElementById("obj1_tra").value=1;
	
	document.getElementById('obj2_x').value= 20 ; document.getElementById("cont_obj2_x").value = 20 ;
	document.getElementById('obj2_y').value=-20 ; document.getElementById("cont_obj2_y").value =-20 ;
	document.getElementById('obj2_z').value= 20 ; document.getElementById("cont_obj2_z").value = 20 ;
	document.getElementById("obj2_R").value= 15 ; document.getElementById("cont_obj2_R").value = 15 ;
	document.getElementById("obj2_q").value=0;
	document.getElementById("obj2_tra").value=1;

	document.getElementById('obj3_x').value=-20 ; document.getElementById("cont_obj3_x").value =-20 ;
	document.getElementById('obj3_y').value= 20 ; document.getElementById("cont_obj3_y").value = 20 ;
	document.getElementById('obj3_z').value= 20 ; document.getElementById("cont_obj3_z").value = 20 ;
	document.getElementById("obj3_R").value= 15 ; document.getElementById("cont_obj3_R").value = 15 ;
	document.getElementById("obj3_q").value=0;
	document.getElementById("obj3_tra").value=1;
	
	for( var i=0; i<button_obj.length; i++ ){

		button_obj[i].style="color:#aaaaaa; background-color:#eeeeee;";
		
		Is_obj[i] = false;
		
	}
	

}

function setNamespace(ns) {
  namespace = ns;
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

	function fireDrawObjectEvent( obj){
		setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'fireDrawObjectEvent']( obj );
					}
					else{
						fireDrawObjectEvent( obj );
					}
				},
				10
		);
	}

	function fireMoveObjectPositionEvent( obj ){
		console.log('fireMoveObjectPositionEvent() ', obj);
		setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'fireMoveObjectPositionEvent']( obj );
					}
					else{
						fireMoveObjectPositionEvent( obj );
					}
				},
				10
		);
	}

	function fireDrawObjectVolumeEvent( obj ){
		console.log('fireDrawObjectVolumeEvent() ', obj );
		setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'fireDrawObjectVolumeEvent']( obj );
					}
					else{
						fireDrawObjectVolumeEvent( obj );
					}
				},
				10
		);
	}

	function fireChangeTransparencyEvent( shape, o ){
		console.log('fireChangeTransparencyEvent() ', shape, o);
		setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'fireChangeTransparencyEvent']( shape, o );
					}
					else{
						fireChangeTransparencyEvent( shape, o );
					}
				},
				10
		);
	}

	function desableInputControls(){
		var inputs = document.querySelectorAll('input');
		for( var i=0; i<inputs.length; i++ ){
			inputs[i].disabled = true;
		}
	}

	function displayObject(obj_i) 
	{
		fireDataChangedEvent();
		
		var obj;
		switch( obj_i){
			case 0:
				obj = getPoint_1_Data();
				break;
			case 1:
				obj = getPoint_2_Data();
				break;
			case 2:
				obj = getSphereData();
				break;
			case 3:
				obj = getCubeData();
				break;
			default:
				return;
		}
		
		
		
		console.log('obj_i: '+Is_obj[obj_i]);
		fireDrawObjectEvent( obj );
		
		Is_obj[obj_i] = Is_obj[obj_i] ? false : true ;
		
		if( Is_obj[obj_i] )	button_obj[obj_i].style="color:#ffffff ; background-color:#333333;";		
		else				button_obj[obj_i].style="color:#aaaaaa ; background-color:#eeeeee;" ;
			
		
		
	}
	
	function displayEPDEditor( data ){
		if( data ){
								
				var lines = data.split('\n');
				
				//for( var index=0; index<lines.length; index++ ){
                  for( var index=0; index<=8 ; index++ ){
					var line = lines[index].trim();
					if( !line )	continue;
					
					
					if( line[0] === '@'){
						var line = line.replace('@', '');
						var keyVal = line.split(' ');
						var start = index+1, end = start + Number(keyVal[1]);
						index = end-1;						
						parseEPInput( lines.slice(start, end));
						
					}
				}
				
		}
		else{
			// initialize
			
			initialize();
			
			
		

		}
	 }
	//--------------------------------
	function parseEPInput( dataLines ){
				for( var i=0; i<dataLines.length; i++ ){
					var line = dataLines[i];
					
					var param = line.split('=');
					var values = param[1].trim().split('/');
					
					switch( param[0].trim()){
						case 'obj1' :							
							document.getElementById("obj0_q").value= Number(values[1]) ;
							document.getElementById('obj0_x').value= Number(values[2]) ; document.getElementById("cont_obj0_x").value = Number(values[2]) ;
							document.getElementById('obj0_y').value= Number(values[3]) ; document.getElementById("cont_obj0_y").value = Number(values[3]) ;
							document.getElementById('obj0_z').value= Number(values[4]) ; document.getElementById("cont_obj0_z").value = Number(values[4]) ;	
							if( Number(values[0]) )	button_obj[0].style="color:#ffffff ; background-color:#333333;";		
							else				    button_obj[0].style="color:#aaaaaa; background-color:#eeeeee;" ;

							break;							
						case 'obj2' :
							document.getElementById("obj1_q").value= Number(values[1]) ;
							document.getElementById('obj1_x').value= Number(values[2]) ; document.getElementById("cont_obj1_x").value = Number(values[2]) ;
							document.getElementById('obj1_y').value= Number(values[3]) ; document.getElementById("cont_obj1_y").value = Number(values[3]) ;
							document.getElementById('obj1_z').value= Number(values[4]) ; document.getElementById("cont_obj1_z").value = Number(values[4]) ;	
							if( Number(values[0]) )	button_obj[1].style="color:#ffffff ; background-color:#333333;";		
							else				    button_obj[1].style="color:#aaaaaa; background-color:#eeeeee;" ;
							
							break;
						case 'obj3' :
							document.getElementById("obj2_q").value= Number(values[1]) ;
							document.getElementById('obj2_x').value= Number(values[2]) ; document.getElementById("cont_obj2_x").value = Number(values[2]) ;
							document.getElementById('obj2_y').value= Number(values[3]) ; document.getElementById("cont_obj2_y").value = Number(values[3]) ;
							document.getElementById('obj2_z').value= Number(values[4]) ; document.getElementById("cont_obj2_z").value = Number(values[4]) ;
							document.getElementById("obj2_R").value= Number(values[5]) ; document.getElementById("cont_obj2_R").value = Number(values[5]) ;
							if( Number(values[0]) )	button_obj[2].style="color:#ffffff ; background-color:#333333;";		
							else				    button_obj[2].style="color:#aaaaaa; background-color:#eeeeee;" ;
							
							break;
						case 'obj4' :														
							document.getElementById("obj3_q").value= Number(values[1]) ;						
							document.getElementById('obj3_x').value= Number(values[2]) ; document.getElementById("cont_obj3_x").value = Number(values[2]) ;
							document.getElementById('obj3_y').value= Number(values[3]) ; document.getElementById("cont_obj3_y").value = Number(values[3]) ;
							document.getElementById('obj3_z').value= Number(values[4]) ; document.getElementById("cont_obj3_z").value = Number(values[4]) ;
							document.getElementById("obj3_R").value= Number(values[5]) ; document.getElementById("cont_obj3_R").value = Number(values[5]) ;
							if( Number(values[0]) )	button_obj[3].style="color:#ffffff ; background-color:#333333;";		
							else				    button_obj[3].style="color:#aaaaaa; background-color:#eeeeee;" ;
							break;
						case 'obj0_tra' : document.getElementById("obj0_tra").value=Number(values[0]); break;
						case 'obj1_tra' : document.getElementById("obj1_tra").value=Number(values[0]); break;
						case 'obj2_tra' : document.getElementById("obj2_tra").value=Number(values[0]); break;
						case 'obj3_tra' : document.getElementById("obj3_tra").value=Number(values[0]); break;
						default: alert('Un-recognizable parameter: '+param[0].trim());	return;
					}
				}
			};
	
	function obj_position(target, source) 
	{
		target.value = source.value;
		
		console.log('Target: ', target );
		var obj = {};
		switch ( target.id ){
			case 'obj0_x':
			case 'obj0_y':
			case 'obj0_z':
				obj = getPoint_1_Data();
				break;
			case 'obj1_x':
			case 'obj1_y':
			case 'obj1_z':
				obj = getPoint_2_Data();
				break;
			case 'obj2_x':
			case 'obj2_y':
			case 'obj2_z':			
				obj = getSphereData();
				break;
			case 'obj3_x':
			case 'obj3_y':
			case 'obj3_z':			
				obj = getCubeData();
				break;
		}
		
		fireMoveObjectPositionEvent( obj );
	}


	function obj_volume(obj_i) 
	{
		var obj;
		switch( obj_i){
			case 2:
				document.getElementById("obj2_R").value = document.getElementById("cont_obj2_R").value;
				if( Is_obj[obj_i] ){
					obj = getSphereData();
					
				}
				else{
					
					console.log('Obj is not visible: '+obj_i);
					return;
				}
				break;
			case 3:
				document.getElementById("obj3_R").value = document.getElementById("cont_obj3_R").value;
				if( Is_obj[obj_i] ){
					obj = getCubeData();
				
				}
				else{
					console.log('Obj is not visible: '+obj_i);
					return;
					
				}
				break;
			default:
				return;
		}
		
		fireDrawObjectVolumeEvent( obj );
	}


	function obj_transp(obj_i) 
	{
		var obj;
		switch( obj_i){
			case 0:
				obj = getPoint_1_Data();
				break;
			case 1:
				obj = getPoint_2_Data();
				break;
			case 2:
				obj = getSphereData();
				break;
			case 3:
				obj = getCubeData();
				break;
		}
		
		if( Is_obj[obj_i]==false ){
			console.log('Obj is not visible: '+obj_i);
			return;
		}
		//alert(obj.o);
		fireChangeTransparencyEvent( obj.shape, obj.o );
	}

	function getPoint_1_Data(){
		var obj = {};
		obj.x = document.getElementById("obj0_x").value;
		obj.y = document.getElementById("obj0_y").value;
		obj.z = document.getElementById("obj0_z").value;
		obj.r = 0;
		obj.q = document.getElementById("obj0_q").value;
		obj.o = document.getElementById("obj0_tra").value;
		obj.shape = 'point_1';
		
		return obj;
	}

	function getPoint_2_Data(){
		var obj = {};
		obj.x = document.getElementById("obj1_x").value;
		obj.y = document.getElementById("obj1_y").value;
		obj.z = document.getElementById("obj1_z").value;
		obj.r = 0;
		obj.q = document.getElementById("obj1_q").value;
		obj.o = document.getElementById("obj1_tra").value;
		obj.shape = 'point_2';
		return obj;
	}

	function getSphereData(){
		var obj = {};
		obj.x = document.getElementById("obj2_x").value;
		obj.y = document.getElementById("obj2_y").value;
		obj.z = document.getElementById("obj2_z").value;
		obj.r = document.getElementById("obj2_R").value;
		obj.q = document.getElementById("obj2_q").value;
		obj.o = document.getElementById("obj2_tra").value;
		
		obj.shape = 'sphere';
		return obj;
	}

	function getCubeData(){
		var obj = {};
		obj.x = document.getElementById("obj3_x").value;
		obj.y = document.getElementById("obj3_y").value;
		obj.z = document.getElementById("obj3_z").value;
		obj.r = document.getElementById("obj3_R").value;
		obj.q = document.getElementById("obj3_q").value;
		obj.o = document.getElementById("obj3_tra").value;
		obj.shape = 'cube';
		return obj;
	}

	  function getParameters()
	  {
	              
	   	var params = '@EP_input 8\n';
	           	
   //   params += 'obj1 = ' + (Is_obj.pointType1?1:0) + ' / '  + 
	    params += 'obj1 = ' + (Is_obj[0]? 1:0) + ' / '  + 
	    					document.getElementById('obj0_q').value + ' / ' + 
	    					document.getElementById('obj0_x').value + ' / ' + 
	    					document.getElementById('obj0_y').value+ ' / ' + 
	    					document.getElementById('obj0_z').value + ' / 0 /\n' ;
   
//	    params += 'obj2 = ' + (Is_obj.pointType2?1:0) + ' / '  + 
	    params += 'obj2 = ' + (Is_obj[1]? 1:0) + ' / '  + 
	    					document.getElementById('obj1_q').value + ' / ' + 
	    					document.getElementById('obj1_x').value + ' / ' + 
	    					document.getElementById('obj1_y').value+ ' / ' + 
	    					document.getElementById('obj1_z').value + ' / 0 /\n' ;
	    					
//	    params += 'obj3 = ' + (Is_obj.sphereType1?1:0) + ' / '  + 0 + ' / ' + 
	    params += 'obj3 = ' + (Is_obj[2]? 1:0) + ' / '  + 0 + ' / ' + 
	                        document.getElementById('obj2_x').value + ' / ' + 
							document.getElementById('obj2_y').value+ ' / ' + 
							document.getElementById('obj2_z').value + ' / ' + 
							document.getElementById('obj2_R').value + '/\n' ;
							
//		params += 'obj4 = ' + (Is_obj.cubeType1?1:0) + ' / ' + 0 + ' / ' + 
		params += 'obj4 = ' + (Is_obj[3]? 1:0) + ' / ' + 0 + ' / ' + 
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


</script>

</body>
</html>