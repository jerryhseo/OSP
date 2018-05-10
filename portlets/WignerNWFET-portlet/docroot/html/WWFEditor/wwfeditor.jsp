<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
var namespace;

var modelViewer;
var IV_mod=0;

function setNamespace( ns ){
	console.log( '[WWFEditor]'+'[wwfeditor.jsp] Set Namespace: '+ns);
	namespace = ns;
}

function set_IV_mod() {
	var obj = document.getElementsByName('I_V_mode');	
	
		if(obj[0].checked) IV_mod=0; else if(obj[1].checked) IV_mod=1;	
		
}
//-----------------------------------------------------
function printValue(input_1, input_2,i) {
		
        var x = document.getElementById(input_1);
        var y = document.getElementById(input_2);        
      
        if(i==6 && x.value==9) y.value = 'Circle';
        else                   y.value = x.value ; 
        
        Read_Parameters_for_Draw_Device();
        return false;          
}
  
function Read_Parameters_for_Draw_Device(){
	
	var data = {
			input1 : $('#input1').val(),
			input2 : $('#input2').val(),
			input3 : $('#input3').val(),
			input4 : $('#input4').val(),
			input5 : $('#input5').val(),
			input6 : $('#input6').val(),										
			input7 : $('#input7').val(),
			input8 : $('#input8').val(),
			input9 : $('#input9').val(),			
			Text1  : $('#Text1').val(),
			Text2  : $('#Text2').val(),
			Text3  : $('#Text3').val(),
			Text4  : $('#Text4').val()
		};	

	console.log('[WWFEditor]'+ namespace+'fireLocalWignerFETEvent' );	
	window.parent[namespace+'fireLocalWignerFETEvent'](data);
} 

function initialize(){
	$('#input1').val(3.4);
	$('#range1').val(3.4);
	$('#input2').val(1);
	$('#range2').val(1);
	$('#input3').val(15);
	$('#range3').val(15);
	$('#input4').val(15);
	$('#range4').val(15);
	$('#input5').val(10);
	$('#range5').val(10);
	$('#input6').val(4);
	$('#range6').val(4);
	$('#input7').val('1.5e10');
	$('#input8').val('5.0e19');
	$('#range8').val('5.0e19');
	$('#input9').val(1350);
	$('#range9').val(1350);
	$('#I_Vg').attr('checked', 'checked');
	$('#Text1').val(0.4);
	$('#Text2').val(-0.2);
	$('#Text3').val(0.3);
	$('#Text4').val(0.02);
	
	fireOnChangeEvent();
	Read_Parameters_for_Draw_Device();
}

function loadParametersDrawDevice( data ){
	// evaluate string data
	// set values to input constrols
	data = data.replace(/[\n ]/g, '');
	var params = data.split(';');
	
	for( var index in params ){
	    var param = params[index];
	    if( param === '' )	continue;
	    var keyVal = param.split( '=');
	    var key = keyVal[0].trim();
	    var value = keyVal[1].trim();
		switch( key ){
		case 'D_Si':
		    $('#input1').val( value );
		    break;
		case 'D_Oxi':
		    $('#input2').val( value );
		    break;
		case 'L_source':
		    $('#input3').val( value );
		    break;
		case 'L_drain':
		    $('#input4').val( value );
		    break;
		case 'L_channel':
		    $('#input5').val( value );
		    break;
		case 'S_crossec':
		    $('#input6').val( value );
		    break;
		case 'ni':
		    $('#input7').val( value );
		    break;
		case 'Nd':
		    $('#input8').val( value );
		    break;
		case 'mu_n':
		    $('#input9').val( value );
		    break;
		case 'IV_mod':
		    IV_mod = value;
		    break;
		case 'V_fix':
		    $('#Text1').val( value );
		    break;
		case 'V_ini':
		    $('#Text2').val( value );
		    break;
		case 'V_fin':
		    $('#Text3').val( value );
		    break;
		case 'V_step':
		    $('#Text4').val( value );
		    break;
		default:
		    console.log('[WWFEditor]'+'Un-recognizable parameter: '+key);
			return;
		}
	}
	
	Read_Parameters_for_Draw_Device();
}

function getParametersFromDrawDevice(){
	var params = '';
	params += 'D_Si = '      + $('#input1').val() + ' ;\n';
	params += 'D_Oxi = '     + $('#input2').val() + ' ;\n';
	params += 'L_source = '  + $('#input3').val() + ' ;\n';
	params += 'L_drain = '   + $('#input4').val() + ' ;\n';
	params += 'L_channel = ' + $('#input5').val() + ' ;\n';
	params += 'S_crossec = ' + $('#input6').val() + ' ;\n';
	params += 'ni = '        + $('#input7').val() + ' ;\n';
	params += 'Nd = '        + $('#input8').val() + ' ;\n';
	params += 'mu_n = '      + $('#input9').val() + ' ;\n';
	params += 'IV_mod = '    + IV_mod             + ' ;\n';
	params += 'V_fix = '     + $('#Text1').val()  + ' ;\n';
	params += 'V_ini = '     + $('#Text2').val()  + ' ;\n';
	params += 'V_fin = '     + $('#Text3').val()  + ' ;\n';
	params += 'V_step = '    + $('#Text4').val()  + ' ;\n';
	
	return params;
}


$(document).ready( function(){
	$('input').change(function(){
	    fireOnChangeEvent();
	});
});

function fireOnChangeEvent(){
	setTimeout(
		   	    function(){
		   	    	if( namespace ){
		   	    		var inputData = {
		   	    		                 type_: 'fileContent',
		   	    		                 context_: getParametersFromDrawDevice()
		   	    		};
		   	    		console.log( '[WWFEditor]'+'fireOnChangeEvent', inputData );
		   	    		
		   	    		window.parent[namespace+'fireOnChangeEvent'](inputData);
		   	    		
		   	    		Read_Parameters_for_Draw_Device();
		   	    	}
		   	    	else{
		   	    		fireOnChangeEvent();
		   	    	}
		   	    },
		   	    10
		);
}
  
</script>
	</head>
	<body>
<div>
<!-- 
<input class="span12" type="button" value='   Run   ' onchange="fireOnChangeEvent()" autocomplete='off'>
 -->
<table style="border:2px solid;" class="table">
		    
	    <tr>
	        <td> Width(or diameter) of Si (nm)</td>
	        <td> <input class="span12" id='input1' type="text" value='3.4' size='4' height='148' oninput="printValue('input1','range1',1)" autocomplete='off'> </td>
	        <td> <input class="span12" id='range1' type="range" value="3.4" min="0.5" max="10" step="0.1" oninput="printValue('range1','input1',1)" autocomplete='off'/>   </td>
	    </tr>   
	    
	     <tr>
	        <td> Thickness of the oxide (nm)</td>
	        <td> <input class="span12" id='input2' type="text" value='1' size='4'  oninput="printValue('input2','range2',2)" autocomplete='off'>  </td>
	        <td> <input class="span12" id='range2' type="range" value="1" min="1" max="5" step="0.1" oninput="printValue('range2','input2',2)" autocomplete='off'/>   </td>
	    </tr>
	    
	    <tr>
	        <td> Length of the source (nm) </td>
	        <td> <input class="span12" id='input3' type="text" value='15' size='4' oninput="printValue('input3','range3',3)" autocomplete='off'>  </td>
	        <td> <input class="span12" id='range3' type="range" value="15" min="5" max="20" step="0.1" oninput="printValue('range3','input3',3)" autocomplete='off'/>   </td>
	        
	    </tr>    
	    <tr>
	        <td> Length of the drain (nm)</td>
	        <td> <input class="span12" id='input4' type="text" value="15" size='4' oninput="printValue('input4','range4',4)" autocomplete='off'> </td>
	        <td> <input class="span12" id='range4' type="range" value="15" min="5" max="20" step="0.1" oninput="printValue('range4','input4',4)" autocomplete='off'/>   </td>
	    </tr>    
	    <tr>
	        <td> Channel length (nm)</td>
	        <td> <input class="span12" id='input5' type="text"  value='10' size='4' oninput="printValue('input5','range5',5)" autocomplete='off'> </td>
	        <td> <input class="span12" id='range5' type="range" value="10" min="3" max="20" step="0.1" oninput="printValue('range5','input5',5)" autocomplete='off'/>   </td>
	    </tr>   
	    <tr>
	        <td>  Cross section shape </td>
	        <td> <input class="span12" id='input6' type="text"  value='4' size='4' oninput="printValue('input6','range6',6)" autocomplete='off' > </td>
	        <td> <input class="span12" id='range6' type="range" value="4" min="3" max="9" step="1" oninput="printValue('range6','input6',6)" autocomplete='off' >   </td>
	    </tr>
	    <tr>
	        <td> Intrinsic doping (cm-3)</td>
	        <td> <input class="span12" id='input7' type="text" value='1.5e10' size='4' oninput="" autocomplete='off' disabled='disabled'> </td>	        
	        <td>  </td>
	        
	    </tr>
	    <tr>
	        <td> Doping density in the source/drain (cm-3)</td>
	        <td> <input class="span12" id='input8' type="text"  value='5.0e19' size='4' oninput="printValue('input8','range8',8)" autocomplete='off'> </td>
	        <td> <input class="span12" id='range8' type="range" value="5.0e19" min="1.0e18" max="1.0e20" step="1.0e18" oninput="printValue('range8','input8',8)" autocomplete='off'/>   </td>	        
	    </tr>    
	    <tr>
	        <td> Mobility (cm^2/V-s)</td>
	        <td> <input class="span12" id='input9' type="text"  value='1350' size='4' oninput="printValue('input9','range9',9)" autocomplete='off'> </td>
	        <td> <input class="span12" id='range9' type="range" value="1350" min="50" max="2500" step="50" oninput="printValue('range9','input9',9)" autocomplete='off'/>   </td>	        
	    </tr>    
	    
	       
	             
	</table>
	
	<table style="border:2px solid; text-align: 'right'" class="table">
	<tr>
	        <td align="center"> I-V mode</td>
	        <td> <input type="radio" id='I_Vg' name="I_V_mode" value="0" checked="checked" onclick="set_IV_mod()"> I-Vg   </td>
	        <td> <input type="radio" id='I_Vd' name="I_V_mode" value="1" onclick="set_IV_mod()"> I-Vd   </td>	        
  	        <td>    </td>
    </tr>
	<tr>
	        <td style="text-align: 'right'"> V_fix=      <input class="span12" id='Text1' type="text" value='0.4' style="width:40%" > V </td>
	        <td> V_inc=      <input class="span12" id='Text2' type="text" value='-0.2'style="width:40%; text-align: 'right'"> V </td>
	        <td> ~           <input class="span12" id='Text3' type="text" value='0.3' style="width:40%" halign="right">  </td>
	        <td> V_step=     <input class="span12" id='Text4' type="text" value='0.02'style="width:40%"> V  </td>
	               
	    </tr>
	</table>
	</div>
	</body>
</html>
