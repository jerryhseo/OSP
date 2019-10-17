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
</style>
</head>

<body style="width:100%; height:100%">
<button onclick="fireDataChangedEvent()">Parameter setting completed</button><br>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA SiestaHam </font> </b> <br>
</a><DIV style="display:none">
<textarea id="SiestaHam_input" cols="100" rows="10" >
System_Label	Hetero
SparseOut	Off
NewformatOut	On
Ham_Divide	On
Num_Block	10
EM_select 3
</textarea>

</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA EM </font></b><br>
</a><DIV style="display:none">
<textarea id="EM_input" cols="100" rows="32" >
{EM_METHOD}
 Complex_U		= yes
 Nz 			= 4
 q_values 		= 10	# Nq or {q1, q2, etc}
 Max_Num_Mode 		= 500
 Max_Iteration 		= 300
 Energy_Window 	    	= (VBE-0.5,CBE+0.4)	# (VBE-value1, CBE+value2) or (value1, value2) 
 UTB_Ewindowmin		= 0.10	
 E_Extension_Gamma 	= 0.0 			# Extension of E-window at Gamma
 E_Extension_Plot	= 1.0			# Extension of E-window for E-k band plot
 Max_No_Subbands  	= 500			# Rough number of subbands in each of CB and VB
 Selection_by_GEVP  	= 0			# N, (N_vb, N_cb), (E1, E2, etc). 0 for non-selection
 Selection_by_kEVP  	= 20			# 'G_only', Number of ks, or (below{E1}, above{E2})
 SVE_tol_U		= 1.0e-07		# Singluar Value Eliminiation (SVE) tolerance for Umode
 SVE_tol_Z		= 1.0e-03		# Singluar Value Eliminiation (SVE) tolerance for Zeta
 E_tol_UPB_check	= 0.025			# Energy Tolerance for UnPhyiscal Branch Checking
 Fraction_Gray_Area	= 0.10			# Ratio of 'Gray area' to E-window for F measurement
 Minimum_Gray_Area	= 0.05			# Minimum Absolute value of Gray area (eV)
 Inspect_Mode_Wavefn	= no			# Inspect wavefunctions of initial Bloch states
 Tol_Stuck_Modes	= 2			# Min. num of stuck modes for removal. 'no' = 0
 Retry			= no
 Write_Umode	        = last			# 'none' for no, 'last' for the last one, 'all' for all
 Print_for_Debugging	= level_0		# none(level_0), level_1, level_2, or level_3
 Runtime_display	= no
{Minimization}
 Method    		= CG-descent		# Frprmn, CG-descent 
 Max_Iteration  	= 0			# 0 for automatic 		
 Tolerance 		= 1.0e-06
 Use_Default_Stop_Rule	= no			# For CG-descent ONLY
</textarea>

</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA Device </font> </b> <br>
</a><DIV style="display:none">
<textarea id="sc3d_input" cols="100" rows="50" onchange="changed_sc3d()">
DEV_Opt		structure		UTB			# Nanowire, UTB, Bulk, ML(monolayer)
DEV_Opt		opmode			Band			# Band, TR, LDOS, IV, Band-kys 
HAMIL           method                  import                  
HAMIL           RBT                     on     
HAMIL           overlap                 on                      
HAMIL           SO                      off
HAMIL		relax_check_numH	yes
HAMIL		hetero_band_method	unmix			# unmix(default)/mix
CRYSTAL		orient			100			# 100, 110, 111, 311, manual
DEV_Dimen	Lchn_Lgate		10.0			# or (Lchn, Lgate)
DEV_Dimen	Lsrc_Ldrn		(10.0, 20.0)		# or (Lsrc, Ldrn)
DEV_Dimen	Tchn_Wchn		3.0			# or (Wch, Tchn)
DEV_Dimen	ox_thickness		1.0			# or (Tox, Tbox, Wlox, Wrox)
DEV_Dimen	gate_type		Double			# GAA, Tri, Pi, (Omega, Wgate)
DEV_Mat		src_doping		(p, 1.0e+20)
DEV_Mat		drn_doping		(n, 2.0e+19)
DEV_Mat		chn_doping		(i, 0.0)
DEV_Mat		chn_dielec_const	15.0
DEV_Mat		ox_dielec_const		20.0
STRAIN		exx			(bi, 0.00)
VOLT		VD			0.3
VOLT		VG			(-0.2, 0.8, 0.1)
CONTROL		sc_conv_eps		1.0e-04
CONTROL		sc_max_iter		(10, 6)
BAND		ka			(-1.0,1.0)
BAND		num_ka			32
BAND		Energy			(-7.0, -3.0)
UTB_Opt		num_ky			20
UTB_Opt		E_cut			0.5
POI_Opt		guess_pot_raise		0.1
------------------------- ADVANCED --------------------------
DEV_Opt		adj_to_near_flag 	yes
VOLT		Temp			300.0
VOLT		Phig_offset		0.0		# gate voltage offset
NEGF		hamil_eta		1.0e-09
NEGF		self_energy_routine	LR_RH_v			# LR_RH_eps or LR_RH_v or LR_QR_v
EGRID		level_1                 ( 5.0, 0.0005)           # (alpha, delta)
EGRID		level_2                 (10.0, 0.001)
EGRID		level_3                 (20.0, 0.002)
EGRID		around_top              ( 6.0, 0.005)
EGRID		maxNe                   4096
PRINT		pot3d_flag	        yes
PRINT		Jx_flag			yes
PRINT		JE_flag			yes
PRINT		Ldos_each_x_flag	no
PRINT		channel_region_flag	no
PRINT		Ebe_iky_flag		yes
CONTROL		read_pot3d_flag		no
CONTROL		check_dimen_and_exit	no
</textarea>

</div>


<input type="textbox" id="Views" size="100" value="--Views--: 0 -60 0 0 0 0" style="display: none">

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
var xmx, ymx, zmx;
var xmn, ymn, zmn;
var dx, dy, dz;



function setNamespace(ns) {
namespace = ns;
}

	
function disableControls(flag){
	// disable all controls if flag is true otherwise enable all controls.
	
	
	document.getElementById('SiestaHam_input').disabled =flag ;
	document.getElementById('EM_input').disabled =flag ;
	document.getElementById('sc3d_input').disabled =flag ;
	
	  
}


function fireDataChangedEvent() {
	//initParameters();
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



function changed_sc3d() {
	
	fireDataChangedEvent();

}


	
	function initParameters()
	{		
		  	
		var SiestaHam_input=`System_Label	Hetero
SparseOut	Off
NewformatOut	On
Ham_Divide	On
Num_Block	10
EM_select 3`;
		
		document.getElementById("SiestaHam_input").value = SiestaHam_input;			
		 
		var EM_input=`{EM_METHOD}
Complex_U		= yes
Nz 			= 4
q_values 		= 10	# Nq or {q1, q2, etc}
Max_Num_Mode 		= 500
Max_Iteration 		= 300
Energy_Window 	    	= (VBE-0.5,CBE+0.4)	# (VBE-value1, CBE+value2) or (value1, value2) 
UTB_Ewindowmin		= 0.10	
E_Extension_Gamma 	= 0.0 			# Extension of E-window at Gamma
E_Extension_Plot	= 1.0			# Extension of E-window for E-k band plot
Max_No_Subbands  	= 500			# Rough number of subbands in each of CB and VB
Selection_by_GEVP  	= 0			# N, (N_vb, N_cb), (E1, E2, etc). 0 for non-selection
Selection_by_kEVP  	= 20			# 'G_only', Number of ks, or (below{E1}, above{E2})
SVE_tol_U		= 1.0e-07		# Singluar Value Eliminiation (SVE) tolerance for Umode
SVE_tol_Z		= 1.0e-03		# Singluar Value Eliminiation (SVE) tolerance for Zeta
E_tol_UPB_check	= 0.025			# Energy Tolerance for UnPhyiscal Branch Checking
Fraction_Gray_Area	= 0.10			# Ratio of 'Gray area' to E-window for F measurement
Minimum_Gray_Area	= 0.05			# Minimum Absolute value of Gray area (eV)
Inspect_Mode_Wavefn	= no			# Inspect wavefunctions of initial Bloch states
Tol_Stuck_Modes	= 2			# Min. num of stuck modes for removal. 'no' = 0
Retry			= no
Write_Umode	        = last			# 'none' for no, 'last' for the last one, 'all' for all
Print_for_Debugging	= level_0		# none(level_0), level_1, level_2, or level_3
Runtime_display	= no
{Minimization}
Method    		= CG-descent		# Frprmn, CG-descent 
Max_Iteration  	= 0			# 0 for automatic 		
Tolerance 		= 1.0e-06
Use_Default_Stop_Rule	= no			# For CG-descent ONLY`;
		
		document.getElementById("EM_input").value = EM_input;
		 
        var sc3d_input=`DEV_Opt		structure		UTB			# Nanowire, UTB, Bulk, ML(monolayer)
DEV_Opt		opmode			Band			# Band, TR, LDOS, IV, Band-kys 
HAMIL           method                  import                  # tb, import (kp, pemt to be added)
HAMIL           RBT                     on      # on/off
HAMIL           overlap                 on                      # none, ortho, overlap
HAMIL           SO                      off
HAMIL		relax_check_numH	yes
HAMIL		hetero_band_method	unmix			# unmix(default)/mix
CRYSTAL		orient			100			# 100, 110, 111, 311, manual
DEV_Dimen	Lchn_Lgate		10.0			# or (Lchn, Lgate)
DEV_Dimen	Lsrc_Ldrn		(10.0, 20.0)		# or (Lsrc, Ldrn)
DEV_Dimen	Tchn_Wchn		3.0			# or (Wch, Tchn)
DEV_Dimen	ox_thickness		1.0			# or (Tox, Tbox, Wlox, Wrox)
DEV_Dimen	gate_type		Double			# GAA, Tri, Pi, (Omega, Wgate)
DEV_Mat		src_doping		(p, 1.0e+20)
DEV_Mat		drn_doping		(n, 2.0e+19)
DEV_Mat		chn_doping		(i, 0.0)
DEV_Mat		chn_dielec_const	15.0
DEV_Mat		ox_dielec_const		20.0
STRAIN		exx			(bi, 0.00)
VOLT		VD			0.3
VOLT		VG			(-0.2, 0.8, 0.1)
CONTROL		sc_conv_eps		1.0e-04
CONTROL		sc_max_iter		(10, 6)
BAND		ka			(-1.0,1.0)
BAND		num_ka			32
BAND		Energy			(-7.0, -3.0)
UTB_Opt		num_ky			20
UTB_Opt		E_cut			0.5
POI_Opt		guess_pot_raise		0.1
------------------------- ADVANCED --------------------------
DEV_Opt		adj_to_near_flag 	yes
VOLT		Temp			300.0
VOLT		Phig_offset		0.0		# gate voltage offset
NEGF		hamil_eta		1.0e-09
NEGF		self_energy_routine	LR_RH_v			# LR_RH_eps or LR_RH_v or LR_QR_v
EGRID		level_1                 ( 5.0, 0.0005)           # (alpha, delta)
EGRID		level_2                 (10.0, 0.001)
EGRID		level_3                 (20.0, 0.002)
EGRID		around_top              ( 6.0, 0.005)
EGRID		maxNe                   4096
PRINT		pot3d_flag	        yes
PRINT		Jx_flag			yes
PRINT		JE_flag			yes
PRINT		Ldos_each_x_flag	no
PRINT		channel_region_flag	no
PRINT		Ebe_iky_flag		yes
CONTROL		read_pot3d_flag		no
CONTROL		check_dimen_and_exit	no`;
		
		document.getElementById("sc3d_input").value = sc3d_input;

		var Views_input="--Views--: 0 -60 0 0 0 0";
			
			document.getElementById("Views").value = Views_input;

	}
	
	
	function setParameters(data, version)
	{
		
		version = '1.0.0';
		
		
		
		var R_data  = data;		
		var R_lines = R_data.split('\n');
		
	
		if( version === '1.0.0'){
			//function call for old version parameters
		  	
	    data = data.replace(/	/gi, ' ');
	    data = data.replace(/\t/gi,  ' ');
	    data = data.replace(/=/gi,   ' ');
	    data = data.replace(/\(/gi,   ' ');
	    data = data.replace(/\)/gi,   ' ');
	    data = data.replace(/,/gi,   ' ');
	    
	    data = data.replace(/ +/gi,  " ");
		   
	       	    
		var lines = data.split('\n');
		
		var ham_i, EM_i, sc3d_i, Views_i ;
		
		var F_Views=0;
		
		for( var i in lines )
		{
			var line = lines[i].trim();
			var dummy = line.split(' ');
			
			if(dummy[0]=="*ham*")           ham_i=i;			
			if(dummy[0]=="*EM*" )           EM_i=parseInt(i);			
			if(dummy[0]=="*sc3d*" )         sc3d_i=parseInt(i);
			if(dummy[0]=="--*Views*--" )    {Views_i=parseInt(i); F_Views=1;  break;}
		}
		
		data="";
				
		//-- read from ham----------------------------------
		
				
		var SiestaHam_input="";
		
		for( var i=1; i<EM_i; i++ )		{			var line = R_lines[i].trim();	SiestaHam_input += line+"\n";		}
		document.getElementById("SiestaHam_input").value = SiestaHam_input;
		 
		var EM_input="";
		
		for( var i=EM_i+1; i<sc3d_i; i++ )		{			var line = R_lines[i].trim();	EM_input += line+"\n";	}
		document.getElementById("EM_input").value = EM_input;
		 
		//-----------------------------------
			
        var sc3d_input="";
		
        if(F_Views==0)  Views_i=lines.length;
		
		for( var i=sc3d_i+1; i<Views_i; i++ )		{	var line = R_lines[i].trim(); sc3d_input += line+"\n";	}
		document.getElementById("sc3d_input").value = sc3d_input;

		if(F_Views!=0)
		{
			
			var Views_input="";
			
			for( var i=Views_i+1; i<lines.length; i++ )		{	var line = R_lines[i].trim(); Views_input += line+"\n";	}
			
			
			document.getElementById("Views").value = Views_input;

		}
        

		}
		else if( version === '2.0.0' ){
			//function call for new version parameters
					

		}
		else{
			console.log('ERROR: DataType Version Mismatch - '+version);
		}
		
	}
	
	function getParameters()
	{
		//var start = Date.now();
		
		  var dummy;
		  var dummy0, dummy1, dummy2, dummy3;
		  var string="";
		  
		  string+= "*ham*\n";
		  
		  string+= document.getElementById("SiestaHam_input").value ;
		  
		  string+="\n*EM*\n";
		  
		  string+= document.getElementById("EM_input").value ;
		  
		  string+="\n*sc3d*\n";
			
		  string+= document.getElementById("sc3d_input").value ;
		  
		  string+="\n--*Views*--\n";
			
		  string+= document.getElementById("Views").value ;

		 
		
		  
		//var end = Date.now();
	
		
		//string = "*ham*\n" + document.getElementById('ham_input').value +"\n*EM*\n"+ document.getElementById('EM_input').value +"\n*sc3d*\n"+ document.getElementById('sc3d_input').value ;

	    return string;
	    
	    ///
	    
	}
	

	







</script>
</body>
</html>