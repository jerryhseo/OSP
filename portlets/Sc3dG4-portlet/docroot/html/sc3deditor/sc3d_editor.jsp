<!DOCTYPE html>

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
<font color=black size='3' > <u>Please, click on the items below to set parameters</u></font> <br>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA SiestaHam </font> </b> <br>
</a><DIV style="display:none">

<table>
<tr><td colspan="2"><hr></td></tr>
<tr><td>SIESTA_SYSTEM_LABEL</td> <td><input id='ham_label' type='text' style = "text-align:center;" value='Graphyne' size='12' ></td></tr>
<tr><td>Make_xyz.r(on,off) </td> <td><select id="sel_Make_xyz">
<option value="on">on</option>
<option value="off">off</option>
</select></td></tr>
<tr><td colspan="2">Information_xyz.r </td> </tr>
<tr><td>Ux </td> <td><input id='text_Ux' type='text' style = "text-align:center;" value='6.94751' size='12' ></td></tr>
<tr><td>Uy </td> <td><input id='text_Uy' type='text' style = "text-align:center;" value='12.03344' size='12' ></td></tr>
<tr><td>Uz </td> <td><input id='text_Uz' type='text' style = "text-align:center;" value='1.0' size='12' ></td></tr>
<tr><td>Natoms_in_Ux*Uy*Uz </td> <td><input id='text_Natoms_in_Uxyz' type='text' style = "text-align:center;" value='24' size='12' ></td></tr>
<!-- <tr><td>Eshift(eV) </td> <td><input id='text_Eshift' type='text' style = 'text-align:center;' value='-4.9282' size='12' ></td></tr> -->
<tr><td>BandCalculation(on,off) </td> <td>
<select id="sel_BandCalculation">
<option value="off">off</option>
<option value="on">on</option>
</select>
</td></tr>
<tr><td>BandLineScale </td> <td><input id='text_BandLineScale' type='text' style = "text-align:center;" value='ReciprocalVector' size='12' ></td></tr>
<tr><td>NumberOfBands </td> <td><input id='text_NumberOfBands' type='text' style = "text-align:center;" value='2' size='12' ></td></tr>
</table>
Band path<br>
<textarea cols="30" id="Band_path" rows="5">
1 0.0000 0.0000 0.0000 G
50 0.5000 0.0000 0.0000 R
50 0.5000 0.5000 0.0000 A
50 0.0000 0.0000 0.0000 G
</textarea>

<br>
Band_Range(eV)
<input id="Band_Range_i" type="text" style = "text-align:center;" value='-15' size='5' > to
<input id="Band_Range_f" type="text" style = "text-align:center;" value='15' size='5' >
<hr>
</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA EM </font></b><br>
</a><DIV style="display:none">
<table>
<tr><td colspan="3"><hr></td></tr>
<tr><td>Complex_U </td> <td><select id="sel_Complex_U">
<option value="no">no</option>
<option value="yes">yes</option>
</select> </td></tr>
<tr><td>Nz </td> <td><input id='text_Nz' type='text' style = "text-align:right;" value='4' size='4' > </td></tr>
<tr><td>q_values </td> <td><input id='text_q_values' type='text' style = "text-align:right;" value='10' size='4' > </td></tr>
<tr><td>Max_Num_Mode </td> <td><input id='text_Max_Num_Mode' type='text' style = "text-align:right;" value='500' size='4' > </td></tr>
<tr><td>Max_Iteration </td> <td><input id='text_Max_Iteration' type='text' style = "text-align:right;" value='300' size='4' > </td></tr>
<tr><td>Energy_Window </td> <td><input id='text_Energy_Window' type='text' style = "text-align:right;" value='CBE-1.0' size='7' > <input id='text_Energy_Window_1' type='text' style = "text-align:right;" value='CBE+1.0' size='7' ></td></tr>
<tr><td>UTB_Ewindowmin </td> <td><input id='text_UTB_Ewindowmin' type='text' style = "text-align:right;" value='0.1' size='4' > </td></tr>
<tr><td>E_Extension_Gamma </td> <td><input id='text_E_Extension_Gamma' type='text' style = "text-align:right;" value='0.0' size='4' > </td></tr>
<tr><td>E_Extension_Plot </td> <td><input id='text_E_Extension_Plot' type='text' style = "text-align:right;" value='0.0' size='4' > </td></tr>
<tr><td>Max_No_Subbands </td> <td><input id='text_Max_No_Subbands' type='text' style = "text-align:right;" value='100' size='4' > </td></tr>
<tr><td>Selection_by_GEVP </td> <td><input id='text_Selection_by_GEVP' type='text' style = "text-align:right;" value='0' size='4' > </td></tr>
<tr><td>Selection_by_kEVP </td> <td><input id='text_Selection_by_kEVP' type='text' style = "text-align:right;" value='10' size='4' > </td></tr>
<tr><td>SVE_tol_U </td> <td><input id='text_SVE_tol_U' type='text' style = "text-align:right;" value='0.002' size='4' > </td></tr>
<tr><td>SVE_tol_Z </td> <td><input id='text_SVE_tol_Z' type='text' style = "text-align:right;" value='0.02' size='4' > </td></tr>
<tr><td>E_tol_UPB_check </td> <td><input id='text_E_tol_UPB_check' type='text' style = "text-align:right;" value='0.025' size='4' > </td></tr>
<tr><td>Fraction_Gray_Area </td> <td><input id='text_Fraction_Gray_Area' type='text' style = "text-align:right;" value='0.15' size='4' > </td></tr>
<tr><td>Minimum_Gray_Area </td> <td><input id='text_Minimum_Gray_Area' type='text' style = "text-align:right;" value='0.05' size='4' > </td></tr>
<tr><td>Inspect_Mode_Wavefn </td> <td><select id="sel_Inspect_Mode_Wavefn">
<option value="no">no</option>
</select> </td></tr>
<tr><td>Tol_Stuck_Modes </td> <td><input id='text_Tol_Stuck_Modes' type='text' style = "text-align:right;" value='2' size='4' > </td></tr>
<!-- <tr><td>Read_Umode </td> <td><select select id="sel_Read_Umode">
<option value="no">no</option>
<option value="yes">yes</option>
</select> </td></tr> -->
<tr><td> Write_Umode </td> <td><select id="sel_Write_Umode">
<option value="last">last</option>
<option value="no">no</option>
</select> </td></tr>
<tr><td>Print_for_Debugging </td> <td><select id="sel_Print_for_Debugging">
<option value="level_0">level_0</option>
<option value="level_1">level_1</option>
<option value="level_2">level_2</option>
<option value="level_3">level_3</option>
</select> </td></tr>
<tr><td>Runtime_display </td> <td><select id="sel_Runtime_display">
<option value="no">no</option>
<option value="yes">yes</option>
</select> </td></tr>
<tr>
<td colspan="2"><hr>Minimization<hr> </td>
</tr>
<tr><td>method </td> <td><select id="sel_method">
<option value="CG-descent">CG-descent</option>
<option value="Frprmn">Frprmn</option>
</select> </td></tr>
<tr><td>Max_Iteration </td> <td><input id='text_Mini_Max_Iteration' type='text' style = "text-align:right;" value='0' size='4' > </td></tr>
<tr><td>Tolerance </td> <td><input id='text_Tolerance' type='text' style = "text-align:right;" value='1.0e-06' size='4' > </td></tr>
<tr><td>Use_Default_Stop_Rule </td> <td><select id="sel_Use_Default_Stop_Rule">
<option value="no">no</option>
<option value="yes">yes</option>
</select> </td></tr>
</table>

</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA Device </font> </b> <br>
</a><DIV style="display:none">

<table>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="1">DEV_Opt </td>
<td>structure </td>
<!-- <td><select onchange="alert(this[selectedIndex].text)"> -->
<td><select id="sel_dev" onchange="Sel_ini_parameters(selectedIndex);">
<option value="ML">ML</option>
<option value="UTB">UTB</option>
<option value="Nanowire">Nanowire</option>
<option value="Bulk">Bulk</option>
</select> </td>
</tr>

<tr><td colspan="3"><hr></td></tr>


<tr>
<td rowspan="6"> Device dimension </td>
<td>Source length </td> <td><input id='Source_length_text' type='text' style = "text-align:right;" value='10.0' size='4' oninput="change_struc(0, 0, 0)"  > nm<input id='Source_length_range' type='range' style='width:90%;' value='10'  min='1' max='20' step='0.1' oninput="change_struc(0, 0, 1)"  autocomplete='off'/> </td>
</tr>
<tr>
<td>Drain length </td> <td><input id='Drain_length_text' type='text' style = "text-align:right;" value='10.0' size='4' oninput="change_struc(1, 0, 0)"> nm<input id='Drain_length_range' type='range' style='width:90%;' value='10'  min='1' max='20' step='0.1' oninput="change_struc(1, 0, 1)"  autocomplete='off'/> </td>
</tr>
<td>Channel length</td> <td><input id='Channel_length_text' type='text' style = "text-align:right;" value='10.0' size='4' oninput="change_struc(2, 0, 0)"> nm <input id='Channel_length_range' type='range' style='width:90%;' value='10'  min='1' max='20' step='0.1' oninput="change_struc(2, 0, 1)"  autocomplete='off'/> </td>
</tr>
<tr>
<td>Gate length</td> <td><input id='Gate_length_text' type='text' style = "text-align:right;" value='10.0' size='4' oninput="change_struc(3, 0, 0)"  > nm<input id='Gate_length_range' type='range' style='width:90%;' value='10'  min='1' max='20' step='0.1' oninput="change_struc(3, 0, 1)"  autocomplete='off'/> </td>
</tr>
<tr>
<td>Channel thickness </td> <td><input id='Channel_thickness_text' type='text' style = "text-align:right;" value='0.1' size='4'  oninput="change_struc(4, 0, 0)"> nm<input id='Channel_thickness_range' type='range' style='width:90%;' value='3'  min='0.1' max='5' step='0.1' oninput="change_struc(4, 0, 1)"  autocomplete='off'/> </td>
</tr>
<tr>
<td>Channel width </td> <td><input id='Channel_width_text' type='text' style = "text-align:right;" value='0.1' size='4' oninput="change_struc(5, 0, 0)"> nm<input id='Channel_width_range' type='range' style='width:90%;' value='1'  min='0.1' max='20' step='0.1' oninput="change_struc(5, 0, 1)"  autocomplete='off'/> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
</table>
<table style = "text-align:center;">
<tr>
<td rowspan="1">Oxide thickness(nm) </td>
<td>Top<br><input id="Oxide_thickness0_text" type="text" style = "text-align:right;" value='0.5' size='1' oninput="change_struc(6, 0, 0)"> <input id='Oxide_thickness0_range' type='range' style='width:90%;' value='1'  min='0.1' max='5' step='0.1' oninput="change_struc(6, 0, 1)"  autocomplete='off'/> </td>
<td>Bottom<br><input id="Oxide_thickness1_text" type="text" style = "text-align:right;" value='0.5' size='1' oninput="change_struc(6, 1, 0)"> <input id='Oxide_thickness1_range' type='range' style='width:90%;' value='1'  min='0.1' max='5' step='0.1' oninput="change_struc(6, 1, 1)"  autocomplete='off'/> </td>
<td>Left<br><input id="Oxide_thickness2_text" type="text" style = "text-align:right;" value='0.5' size='1' oninput="change_struc(6, 2, 0)"> <input id='Oxide_thickness2_range' type='range' style='width:90%;' value='1'  min='0.1' max='5' step='0.1' oninput="change_struc(6, 2, 1)"  autocomplete='off'/> </td>
<td>Right<br><input id="Oxide_thickness3_text" type="text" style = "text-align:right;" value='0.5' size='1' oninput="change_struc(6, 3, 0)"> <input id='Oxide_thickness3_range' type='range' style='width:90%;' value='1'  min='0.1' max='5' step='0.1' oninput="change_struc(6, 3, 1)"  autocomplete='off'/> </td>
</tr>
<tr><td colspan="5"><hr></td></tr>
</table>
<table>
<tr>
<td>Gate type </td> <td><select id='sel_gate_type' onchange="draw_struc(selectedIndex);">
<option value="Double">Double</option>
<option value="Tri">Tri</option>
<option value="Pi">Pi</option>
<option value="Omega">Omega</option>
<option value="GAA">GAA</option>
<option value="Wgate">Wgate</option>
</select> </td>
</tr>

<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="3">Doping density </td>
<td>Source <br><select id='dop_type_Source' onchange="draw_struc();">
<option value="n">n</option>
<option value="p">p</option>
<option value="i">i</option>
</select></td>
<td>
<input id="Source_dop_text" type="text" style = "text-align:right;" value='1.0e+20' size='4' oninput="change_struc(7, 0, 0)">
<input autocomplete="off" id="Source_dop_range" max="25" min="5"  oninput="change_struc(7, 0, 1)" step="1" style="width:90%;" type="range"  value="20" />
</td>
</tr>
<tr>
<td>Drain <br><select id='dop_type_Drain' onchange="draw_struc();">
<option value="n">n</option>
<option value="p">p</option>
<option value="i">i</option>
</select></td>
<td>
<input id="Drain_dop_text" type="text" style = "text-align:right;" value='1.0e+20' size='4' oninput="change_struc(8, 0, 0)">
<input autocomplete="off" id="Drain_dop_range" max="25" min="5"  oninput="change_struc(8, 0, 1)" step="1" style="width:90%;" type="range"  value="20" />
</td>
</tr>
<tr>
<td>Channel <br><select id='dop_type_Channel' onchange="draw_struc();">
<option value="i">i</option>
<option value="n">n</option>
<option value="p">p</option>
</select></td>
<td>
<input id="Channel_dop_text" type="text" style = "text-align:right;" value='0' size='4' oninput="change_struc(9, 0, 0)">
<input autocomplete="off" id="Channel_dop_range" max="25" min="0"  oninput="change_struc(9, 0, 1)" step="1" style="width:90%;" type="range"  value="0" />
</td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">Dielectric constant </td>
<td>channel </td> <td><input id='chn_dielec_const' type='text' style = "text-align:right;" value='1.12' size='4' > </td>
</tr>
<tr>
<td>oxide </td> <td><input id='ox_dielec_const' type='text' style = "text-align:right;" value='3.9' size='4' > </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="1">STRAIN </td>
<td rowspan="1">exx </td>
<td> <input id='exx_0' type='text' style = "text-align:right;" value='bi' size='1' > <input id='exx_1' type='text' style = "text-align:right;" value='0.00' size='1' > </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
</table>
</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA Method </font> </b> <br>
</a><DIV style="display:none">
<table>
<tr>
<td>
<table>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">HAMIL </td>
<td>method </td> <td>TightBinding </td>
</tr>
<tr>
<td>import </td> <td><select id="sel_import">
<option value="overlap">overlap</option>
<option value="none">none</option>
<option value="ortho">ortho</option>
</select> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="1">TB_Param </td>
<td>SO </td> <td><select id="sel_SO">
<option value="off">off</option>
<option value="on">on</option>
</select> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">VOLT </td>
<td>Vd </td> <td><input id='VOLT_Vd' type='text' style = "text-align:right;" value='0.5' size='4' > </td>
</tr>
<tr>
<td>Vg </td>
<td>
<table>
<tr>
<td>Initial</td><td>Final</td><td>Step</td>
</tr>
<tr>
<td><input id="VOLT_Vg_0" type="text" style = "text-align:right;" value='-0.2' size='1' ></td>
<td><input id="VOLT_Vg_1" type="text" style = "text-align:right;" value='1.0' size='1' ></td>
<td><input id="VOLT_Vg_2" type="text" style = "text-align:right;" value='0.1' size='1' ></td>
</tr>
</table>

</td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">CONTROL </td>
<td>sc_conv_eps </td> <td><input id='sc_conv_eps' type='text' style = "text-align:right;" value='1.0e-4' size='4' > </td>
</tr>
<tr>
<td>sc_max_iter </td> <td><input id='sc_max_iter_0' type='text' style = "text-align:right;" value='10' size='1' > <input id='sc_max_iter_1' type='text' style = "text-align:right;" value='6' size='1' > </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">UTB_Opt </td>
<td>num_ky </td> <td><input id='num_ky' type='text' style = "text-align:right;" value='20' size='4' > </td>
</tr>
<tr>
<td>E_cut </td> <td><input id='E_cut' type='text' style = "text-align:right;" value='0.02' size='4' > </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
</table>
</td>
<td valign="top">

</td>
</tr>
</table>

</div>

<a onclick=this.nextSibling.style.display=(this.nextSibling.style.display=='none')?'block':'none'; style="text-decoration:none">
<b> <font color=black size='4' style='cursor:pointer'> &#x25BA Advanced </font> </b><br>
</a><DIV style="display:none">
<table>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="1">DEV_Opt </td>
<td>adj_to_near_flag </td> <td><select id='adj_to_near_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>

<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">VOLT </td>
<td>Temp(K) </td> <td><input id='VOLT_Temp' type='text' style = "text-align:right;" value='300.0' size='4' > </td>
</tr>
<tr>
<td>Phig_offset </td> <td><input id='VOLT_Phig_offset' type='text' style = "text-align:right;" value='0.0' size='4' > </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">NEGF </td>
<td>hamil_eta </td> <td><input id='NEGF_hamil_eta' type='text' style = "text-align:right;" value='1.0e-9' size='4' > </td>
</tr>
<tr>
<td>self_energy_routine </td> <td><select id='self_energy_routine'>
<option value="LR_RH_v">LR_RH_v</option>
<option value="LR_RH_eps">LR_RH_eps</option>
<option value="LR_QR_v">LR_QR_v</option>
</select> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">POI_Opt </td>
<td>solver </td> <td><select id='solver'>
<option value="FDM">FDM</option>
<option value="FEM">FEM</option>
</select> </td>
</tr>
<tr>
<td>n3d_alloc_model </td> <td><select id='n3d_alloc_model'>
<option value="vatom">vatom</option>
<option value="c">c</option>
<option value="d">d</option>
</select> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="5">EGRID </td>
<td>level_1 </td> <td><input id='EGRID_level_1_0' type='text' style = "text-align:right;" value='3.0' size='2' > <input id='EGRID_level_1_1' type='text' style = "text-align:right;" value='0.001' size='2' > </td>
</tr>
<tr>
<td>level_2 </td> <td><input id='EGRID_level_2_0' type='text' style = "text-align:right;" value='8.0' size='2' > <input id='EGRID_level_2_1' type='text' style = "text-align:right;" value='0.002' size='2' ></td>
</tr>
<tr>
<td>level_3 </td> <td><input id='EGRID_level_3_0' type='text' style = "text-align:right;" value='15.0' size='2' > <input id='EGRID_level_3_1' type='text' style = "text-align:right;" value='0.005' size='2' > </td>
</tr>
<tr>
<td>around_top </td> <td><input id='EGRID_around_top_0' type='text' style = "text-align:right;" value='6.0' size='2' > <input id='EGRID_around_top_1' type='text' style = "text-align:right;" value='0.005' size='2' > </td>
</tr>
<tr>
<td>maxNe </td> <td><input id='EGRID_maxNe' type='text' style = "text-align:right;" value='2048' size='4' > </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="6">PRINT </td>
<td>pot3d_flag </td> <td><select id='pot3d_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>
<tr>
<td>Charge density </td> <td><select id='nq3d_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>
<tr>
<td>Jx_flag </td> <td><select id='Jx_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>
<tr>
<td>JE_flag </td> <td><select id='JE_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>
<tr>
<td>Ebe_iky_flag </td> <td><select id='Ebe_iky_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>
<tr>
<td>JE_iky_flag </td> <td><select id='JE_iky_flag'>
<option value="yes">yes</option>
<option value="no">no</option>
</select> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
<tr>
<td rowspan="2">CONTROL </td>
<td>read_pot3d_flag </td> <td><select id='read_pot3d_flag'>
<option value="no">no</option>
<option value="yes">yes</option>
</select> </td>
</tr>
<tr>
<td>check_dimen_and_exit </td> <td><select id='check_dimen_and_exit'>
<option value="no">no</option>
<option value="yes">yes</option>
</select> </td>
</tr>
<tr><td colspan="3"><hr></td></tr>
</table>
</div>

<script>

var fdf_text="";

var namespace;

var z_scal=9.400000e-05;
var um = 1e-6;

var ti=0;

var ti_dummy=0;

var N_struc_param=11;


// From --- middle -----
var Nx, Ny, Nz, x_scal, y_scal, z_scal, N_nod, N_t;
var Norm_dx, Norm_dy, Norm_dz;
var xmx, ymx, zmx;
var xmn, ymn, zmn;
var dx, dy, dz;


//console.log(getParameters_sc3d());

var Control_TR = new Array(N_struc_param);

for (var i=0; i<N_struc_param ; i++){ Control_TR[i] = new Array(2); }

Control_TR[6][0] = new Array(4);
Control_TR[6][1] = new Array(4);

Control_TR[0][0]='Source_length_text';
Control_TR[1][0]='Drain_length_text';
Control_TR[2][0]='Channel_length_text';
Control_TR[3][0]='Gate_length_text';
Control_TR[4][0]='Channel_thickness_text';
Control_TR[5][0]='Channel_width_text';
Control_TR[6][0][0]='Oxide_thickness0_text'; Control_TR[6][0][1]='Oxide_thickness1_text';Control_TR[6][0][2]='Oxide_thickness2_text';Control_TR[6][0][3]='Oxide_thickness3_text';
Control_TR[7][0]='Source_dop_text';
Control_TR[8][0]='Drain_dop_text';
Control_TR[9][0]='Channel_dop_text';

Control_TR[0][1]='Source_length_range';
Control_TR[1][1]='Drain_length_range';
Control_TR[2][1]='Channel_length_range';
Control_TR[3][1]='Gate_length_range';
Control_TR[4][1]='Channel_thickness_range';
Control_TR[5][1]='Channel_width_range';
Control_TR[6][1][0]='Oxide_thickness0_range'; Control_TR[6][1][1]='Oxide_thickness1_range';Control_TR[6][1][2]='Oxide_thickness2_range';Control_TR[6][1][3]='Oxide_thickness3_range';
Control_TR[7][1]='Source_dop_range';
Control_TR[8][1]='Drain_dop_range';
Control_TR[9][1]='Channel_dop_range';

var Ini_parameters = new Array(4);

for (var i=0; i<4 ; i++){ Ini_parameters[i] = new Array(N_struc_param); }

Ini_parameters[0][0] = 10.0;
Ini_parameters[0][1] = 10.0;
Ini_parameters[0][2] = 10.0;
Ini_parameters[0][3] = 10.0;
Ini_parameters[0][4] = 3.0;
Ini_parameters[0][5] = 3.0;
Ini_parameters[0][6] = 1.0;
Ini_parameters[0][7] = "1.0e+20";
Ini_parameters[0][8] = "1.0e+20";
Ini_parameters[0][9] = "1.0e+15";
Ini_parameters[0][10] = "Double";

Ini_parameters[1][0] = 10.0;
Ini_parameters[1][1] = 10.0;
Ini_parameters[1][2] = 10.0;
Ini_parameters[1][3] = 10.0;
Ini_parameters[1][4] = 3.0;
Ini_parameters[1][5] = 20.0;
Ini_parameters[1][6] = 1.0;
Ini_parameters[1][7] = "1.0e+20";
Ini_parameters[1][8] = "1.0e+20";
Ini_parameters[1][9] = "1.0e+15";
Ini_parameters[1][10] = "Double";

for (var i=0; i<N_struc_param ; i++){ Ini_parameters[3][i] = Ini_parameters[0][i]; }
for (var i=0; i<N_struc_param ; i++){ Ini_parameters[2][i] = Ini_parameters[1][i]; }

function initParameters(){
	fdf_text="";

	 z_scal=9.400000e-05;
	 um = 1e-6;

	 ti=0;

	 ti_dummy=0;

	 N_struc_param=11;

	 Ini_parameters = new Array(4);

	for (var i=0; i<4 ; i++){ Ini_parameters[i] = new Array(N_struc_param); }

	Ini_parameters[0][0] = 10.0;
	Ini_parameters[0][1] = 10.0;
	Ini_parameters[0][2] = 10.0;
	Ini_parameters[0][3] = 10.0;
	Ini_parameters[0][4] = 3.0;
	Ini_parameters[0][5] = 3.0;
	Ini_parameters[0][6] = 1.0;
	Ini_parameters[0][7] = "1.0e+20";
	Ini_parameters[0][8] = "1.0e+20";
	Ini_parameters[0][9] = "1.0e+15";
	Ini_parameters[0][10] = "Double";

	Ini_parameters[1][0] = 10.0;
	Ini_parameters[1][1] = 10.0;
	Ini_parameters[1][2] = 10.0;
	Ini_parameters[1][3] = 10.0;
	Ini_parameters[1][4] = 3.0;
	Ini_parameters[1][5] = 20.0;
	Ini_parameters[1][6] = 1.0;
	Ini_parameters[1][7] = "1.0e+20";
	Ini_parameters[1][8] = "1.0e+20";
	Ini_parameters[1][9] = "1.0e+15";
	Ini_parameters[1][10] = "Double";

	for (var i=0; i<N_struc_param ; i++){ Ini_parameters[3][i] = Ini_parameters[0][i]; }
	for (var i=0; i<N_struc_param ; i++){ Ini_parameters[2][i] = Ini_parameters[1][i]; }

	setStructures(0);
}

function setNamespace(ns) {
namespace = ns;
}

function disableControls(flag){
	// disable all controls if flag is true otherwise enable all controls.
	//  
	
	  document.getElementById('ham_label').disabled =true ;
	  document.getElementById('sel_Make_xyz').disabled =true ;	  
	  document.getElementById('text_Ux').disabled =true ;
	  document.getElementById('text_Uy').disabled =true ;
	  document.getElementById('text_Uz').disabled =true ;
	  document.getElementById('text_Natoms_in_Uxyz').disabled =true ;	  
	  document.getElementById('sel_BandCalculation').disabled =true ;	  
	  document.getElementById('text_BandLineScale').disabled =true ;
	  document.getElementById('text_NumberOfBands').disabled =true ;
	  document.getElementById('Band_path').disabled =true ;
	  document.getElementById('Band_Range_i').disabled =true
	  document.getElementById('Band_Range_f').disabled =true ;

	  /////  --em-----

      document.getElementById('sel_Complex_U').disabled =true ;
	  document.getElementById('text_Nz').disabled =true ;
	  document.getElementById('text_q_values').disabled =true ;
	  document.getElementById('text_Max_Num_Mode').disabled =true ;
	  document.getElementById('text_Max_Iteration').disabled =true ;
	  document.getElementById('text_Energy_Window' ).disabled =true;
	  document.getElementById('text_Energy_Window_1').disabled =true;	  
	  document.getElementById('text_UTB_Ewindowmin').disabled =true ;
	  document.getElementById('text_E_Extension_Gamma').disabled =true ;
	  document.getElementById('text_E_Extension_Plot').disabled =true ;
	  document.getElementById('text_Max_No_Subbands').disabled =true ;
	  document.getElementById('text_Selection_by_GEVP').disabled =true ;
	  document.getElementById('text_Selection_by_kEVP').disabled =true ;
	  document.getElementById('text_SVE_tol_U').disabled =true ;
	  document.getElementById('text_SVE_tol_Z').disabled =true ;
	  document.getElementById('text_E_tol_UPB_check').disabled =true ;
  	  document.getElementById('text_Fraction_Gray_Area').disabled =true ;
	  document.getElementById('text_Minimum_Gray_Area').disabled =true ;
	  document.getElementById('sel_Inspect_Mode_Wavefn').disabled =true ;	  
	  document.getElementById('text_Tol_Stuck_Modes').disabled =true ;
	  document.getElementById('sel_Write_Umode').disabled =true ;	  
	  document.getElementById('sel_Print_for_Debugging').disabled =true ;	  
	  document.getElementById('sel_Runtime_display').disabled =true ;	  
	  document.getElementById('sel_method').disabled =true ;	  
	  document.getElementById('text_Mini_Max_Iteration').disabled =true ;
	  document.getElementById('text_Tolerance').disabled =true ;
	  document.getElementById('sel_Use_Default_Stop_Rule').disabled =true ;
	  
	  //------sc3d---------------

	  document.getElementById('sel_dev').disabled =true ;
	  document.getElementById('sel_SO').disabled =true ;
	  document.getElementById('sel_import').disabled =true ;
	  document.getElementById('Channel_length_text').disabled =true ;
	  document.getElementById('Gate_length_text').disabled =true ;	  
	  document.getElementById('Source_length_text').disabled =true ;
	  document.getElementById('Drain_length_text').disabled =true ;
	  document.getElementById('Channel_thickness_text').disabled =true ;
	  document.getElementById('Channel_width_text').disabled =true ;	  
	  document.getElementById('Oxide_thickness0_text').disabled =true ;
	  document.getElementById('Oxide_thickness1_text').disabled =true ;
	  document.getElementById('Oxide_thickness2_text').disabled =true ;
	  document.getElementById('Oxide_thickness3_text').disabled =true ;	  
	  document.getElementById('sel_gate_type').disabled =true ;	  
	  document.getElementById('dop_type_Source').disabled =true ;
	  document.getElementById('dop_type_Drain').disabled =true ;
	  document.getElementById('Source_dop_text').disabled =true ;
	  document.getElementById('Drain_dop_text').disabled =true ;
	  document.getElementById('dop_type_Channel').disabled =true ;
	  document.getElementById('Channel_dop_text').disabled =true ;
	  document.getElementById('chn_dielec_const').disabled =true;
	  document.getElementById('ox_dielec_const').disabled =true ;
	  document.getElementById('exx_0').disabled =true;
	  document.getElementById('exx_1').disabled =true;
	  document.getElementById('VOLT_Vd').disabled =true ;	  
	  document.getElementById('VOLT_Vg_0').disabled =true;
	  document.getElementById('VOLT_Vg_1').disabled =true;
	  document.getElementById('VOLT_Vg_2').disabled =true;	  
	  document.getElementById('sc_conv_eps').disabled =true ;
	  document.getElementById('sc_max_iter_0').disabled =true;
	  document.getElementById('sc_max_iter_1').disabled =true;	  
	  document.getElementById('num_ky').disabled =true ;
	  document.getElementById('E_cut').disabled =true ;
	  document.getElementById('adj_to_near_flag').disabled =true ;	  
	  document.getElementById('VOLT_Temp').disabled =true ;
	  document.getElementById('VOLT_Phig_offset').disabled =true ;
	  document.getElementById('NEGF_hamil_eta').disabled =true ;
	  document.getElementById('self_energy_routine').disabled =true ;	  
	  document.getElementById('solver').disabled =true ;	  
	  document.getElementById('n3d_alloc_model').disabled =true ;	  
	  document.getElementById('EGRID_level_1_0').disabled =true;
	  document.getElementById('EGRID_level_1_1').disabled =true;	  
	  document.getElementById('EGRID_level_2_0').disabled =true;
	  document.getElementById('EGRID_level_2_1').disabled =true;
  	  document.getElementById('EGRID_level_3_0').disabled =true;
  	  document.getElementById('EGRID_level_3_1').disabled =true;	  
	  document.getElementById('EGRID_around_top_0').disabled =true;
	  document.getElementById('EGRID_around_top_1').disabled =true;	  
	  document.getElementById('EGRID_maxNe').disabled =true ;
	  document.getElementById('pot3d_flag').disabled =true ;	  
	  document.getElementById('nq3d_flag').disabled =true ;
	  document.getElementById('Jx_flag').disabled =true ;	  
	  document.getElementById('JE_flag').disabled =true ;
	  document.getElementById('Ebe_iky_flag').disabled =true ;	  
	  document.getElementById('JE_iky_flag').disabled =true ;	  
	  document.getElementById('read_pot3d_flag').disabled =true ;	  
	  document.getElementById('check_dimen_and_exit').disabled =true ;	  
	  
	  document.getElementById('Source_length_range').disabled =true ;
	  document.getElementById('Drain_length_range').disabled =true ;
	  document.getElementById('Channel_length_range').disabled =true ;
	  document.getElementById('Gate_length_range').disabled =true ;
	  document.getElementById('Channel_thickness_range').disabled =true ;
	  document.getElementById('Channel_width_range').disabled =true ;
	  document.getElementById('Oxide_thickness0_range').disabled =true ;
	  document.getElementById('Oxide_thickness1_range').disabled =true ;
	  document.getElementById('Oxide_thickness2_range').disabled =true ;
	  document.getElementById('Oxide_thickness3_range').disabled =true ;
	  document.getElementById('Source_dop_range').disabled =true ;
	  document.getElementById('Drain_dop_range').disabled =true ;
	  document.getElementById('Channel_dop_range').disabled =true ;
	  
}


function fireDataChangedEvent() {
	// disableControls();
		setTimeout(
				function() {
					if ( namespace ) {
						var data = getParameters();

						//console.log('fireDataChangedEvent() ', data);
						window.parent[namespace+'fireDataChangedEvent']( data );
					}
					else {
						fireDataChangedEvent();
					}
				},
				10
		);
	}



//--------------
function change_struc(i,j, option ) {

		console.log(i,j,option);
	if (i>6 && i<10)
	{
		 if (option==0) document.getElementById(Control_TR[i][1]).value=Math.log10(Number(document.getElementById(Control_TR[i][0]).value));
	else if (option==1) document.getElementById(Control_TR[i][0]).value="1.0e+"+document.getElementById(Control_TR[i][1]).value;
	}
	else if (i==6)
	{
		 if (option==0) document.getElementById(Control_TR[i][1][j]).value=document.getElementById(Control_TR[i][0][j]).value;
	else if (option==1) document.getElementById(Control_TR[i][0][j]).value=document.getElementById(Control_TR[i][1][j]).value;
	}
	else
	{
		 if (option==0) document.getElementById(Control_TR[i][1]).value=document.getElementById(Control_TR[i][0]).value;
	else if (option==1) document.getElementById(Control_TR[i][0]).value=document.getElementById(Control_TR[i][1]).value;
	}

		 //fireStrucChangedEvent();

		 fireDataChangedEvent();
}
function draw_struc(g_type ) {


	//fireStrucChangedEvent();
	fireDataChangedEvent();

}

	function load_FDF_S( data ) {
		document.getElementById('fdf_file').value=data;
	}

	function getStructure()
	{
		var dummy ;

		var string="";
		string += document.getElementById('Channel_length_text' ).value+"\n";
		string += document.getElementById('Gate_length_text' ).value+"\n";
		string += document.getElementById('Source_length_text' ).value+"\n";
		string += document.getElementById('Drain_length_text' ).value+"\n";
		string += document.getElementById('Channel_thickness_text').value+"\n";
		string += document.getElementById('Channel_width_text' ).value+"\n";
		string += document.getElementById('Oxide_thickness0_text' ).value+"\n";
		string += document.getElementById('Oxide_thickness1_text' ).value+"\n";
		string += document.getElementById('Oxide_thickness2_text' ).value+"\n";
		string += document.getElementById('Oxide_thickness3_text' ).value+"\n";

		string += document.getElementById('Source_dop_text').value+"\n";
		string += document.getElementById('Drain_dop_text').value+"\n";
		string += document.getElementById('Channel_dop_text').value+"\n";

		dummy = document.getElementById('sel_gate_type'); string += dummy.options[dummy.selectedIndex].value+"\n";

		dummy = document.getElementById('dop_type_Source'); string += dummy.options[dummy.selectedIndex].value+"\n";
		dummy = document.getElementById('dop_type_Drain'); string += dummy.options[dummy.selectedIndex].value+"\n";
		dummy = document.getElementById('dop_type_Channel'); string += dummy.options[dummy.selectedIndex].value+"\n";

		//var ddd=document.getElementById('sel_gate_type')

	    return string;

	}


	function setStructures(si)
	{
		//console.log("setParameters: ", data);
		
		var dummy ;
		
		//si=0;
		
		document.getElementById('Channel_length_text' ).value   = Ini_parameters[si][0] ;
		document.getElementById('Gate_length_text' ).value      = Ini_parameters[si][1] ;
		document.getElementById('Source_length_text' ).value    = Ini_parameters[si][2] ;
		document.getElementById('Drain_length_text' ).value     = Ini_parameters[si][3] ;
		document.getElementById('Channel_thickness_text').value = Ini_parameters[si][4] ;
		document.getElementById('Channel_width_text' ).value    = Ini_parameters[si][5] ;
		document.getElementById('Oxide_thickness0_text' ).value = Ini_parameters[si][6] ;
		document.getElementById('Source_dop_text').value        = Ini_parameters[si][7] ;
		document.getElementById('Drain_dop_text').value         = Ini_parameters[si][8] ;
		document.getElementById('Channel_dop_text').value       = Ini_parameters[si][9] ;
		//Ini_parameters[si][10] = "Double";

			
		document.getElementById('Oxide_thickness1_text' ).value = document.getElementById('Oxide_thickness0_text' ).value;
		document.getElementById('Oxide_thickness2_text' ).value = document.getElementById('Oxide_thickness0_text' ).value;
		document.getElementById('Oxide_thickness3_text' ).value = document.getElementById('Oxide_thickness0_text' ).value;
				
		//dummy = document.getElementById('sel_gate_type'); string += dummy.options[dummy.selectedIndex].value+"\n";

		dummy = document.getElementById('sel_gate_type');
		
		dummy.options[dummy.selectedIndex].value=0; 
		
		
	}
	
	function setParameters(data)
	{
		//console.log("setParameters: ", data);
		
	
	// <----------------------------
//		var keys = {
	//			SIESTA_SYSTEM_LABEL: false,
		//Make_xyz.r(on,off):false,
	//};
	
	
	var N_key= 46;
	var keyws = new Array();
	
	for(var i=0; i<=9; i++) keyws[i] = new Array(1);	
	
	keyws[0][0] ='SIESTA_SYSTEM_LABEL';
	keyws[1][0] ='Make_xyz.r(on,off)';
	keyws[2][0] ='Ux';
	keyws[3][0] ='Uy';
	keyws[4][0] ='Uz';
	keyws[5][0] ='Natoms_in_Ux*Uy*Uz';	
	keyws[6][0] ='BandCalculation(on,off)';
	keyws[7][0] ='BandLineScale';
	keyws[8][0] ='NumberOfBands';
	keyws[9][0] ='Band_Range(eV)';
	
	//----------EM -------------------------------------
	
	for(var i=10; i<=34; i++) keyws[i] = new Array(1);
	
	keyws[10][0] ='Complex_U';             
	keyws[11][0]='Nz';                     
	keyws[12][0]='q_values';              
	keyws[13][0]='Max_Num_Mode';          
	keyws[14][0]='Max_Iteration';         
	keyws[15][0]='UTB_Ewindowmin';        
	keyws[16][0]='E_Extension_Gamma';     
	keyws[17][0]='E_Extension_Plot';      
	keyws[18][0]='Max_No_Subbands';       
	keyws[19][0]='Selection_by_GEVP';     
	keyws[20][0]='Selection_by_kEVP';     
	keyws[21][0]='SVE_tol_U';             
	keyws[22][0]='SVE_tol_Z';              
	keyws[23][0]='E_tol_UPB_check';       
	keyws[24][0]='Fraction_Gray_Area';    
	keyws[25][0]='Minimum_Gray_Area';     
	keyws[26][0]='Inspect_Mode_Wavefn';   
	keyws[27][0]='Tol_Stuck_Modes';       
	keyws[28][0]='Write_Umode';           	
	keyws[29][0]='Print_for_Debugging';   
	keyws[30][0]='Runtime_display';
	
	////-------------Minimization----------------------------
	
	keyws[31][0]='Method';	              
	keyws[32][0]='Max_Iteration';         
	keyws[33][0]='Tolerance';             
	keyws[34][0]='Use_Default_Stop_Rule'; 
	
	//-------------sc3d -------------------------------
	
	keyws[35] = new Array(4);	
	keyws[35][0]='DEV_Opt';               keyws[35][1]='structure';         keyws[35][2]='opmode';              keyws[35][3]='RBT'; 
	
	keyws[36] = new Array(2);	
	keyws[36][0]='TB_Param SO';		      keyws[36][1]='SO';
	
	keyws[37] = new Array(6);	
	keyws[37][0]='DEV_Dimen';             keyws[37][1]='Lchn_Lgate';        keyws[37][2]='Lsrc_Ldrn';           keyws[37][3]='Tchn_Wchn';        keyws[37][4]='ox_thickness';         keyws[37][5]='gate_type';
	
	keyws[38] = new Array(5);
	keyws[38][0]='DEV_Mat';	              keyws[38][1]='src_drn_doping';    keyws[38][2]='chn_doping';          keyws[38][3]='chn_dielec_const'; keyws[38][4]='ox_dielec_const';
	
	keyws[39] = new Array(2);
	keyws[39][0]='STRAIN';                keyws[39][1]='exx';
	
	keyws[40] = new Array(5);
	keyws[40][0]='VOLT';                  keyws[40][1]='VD';                keyws[40][2]='VG';                  keyws[40][3]='Temp';             keyws[40][4]='Phig_offset'; 
		
	keyws[41] = new Array(7);
	keyws[41][0]='CONTROL';	              keyws[41][1]='sc_conv_eps';       keyws[41][2]='sc_max_iter';         keyws[41][3]='read_pot3d_flag';  keyws[41][4]='check_dimen_and_exit'; keyws[41][5]='read_pot3d_flag'; keyws[41][6]='check_dimen_and_exit'; 
	
	keyws[42] = new Array(3);
	keyws[42][0]='UTB_Opt';	              keyws[42][1]='num_ky';            keyws[42][2]='E_cut'; 
	
	keyws[43] = new Array(2);
	keyws[43][0]='DEV_Opt';	              keyws[43][1]='adj_to_near_flag';
	
	keyws[44] = new Array(2);
	keyws[44][0]='VOLT';	              keyws[44][1]='Temp';   keyws[44][2]='Phig_offset'; 
	
	
	keyws[45] = new Array(3);
	keyws[45][0]='NEGF';                  keyws[45][1]='hamil_eta';         keyws[45][2]='self_energy_routine';
			
	keyws[46] = new Array(3);
	keyws[46][0]='POI_Opt';                  keyws[46][1]='solver';         keyws[46][2]='n3d_alloc_model';

	
	keyws[47] = new Array(6);
	keyws[47][0]='EGRID';	              keyws[47][1]='level_1';           keyws[47][2]='level_2';             keyws[47][3]='level_3';          keyws[47][4]='around_top';           keyws[47][5]='maxNe';
	
	keyws[48] = new Array(7);
	keyws[48][0]='PRINT';                 keyws[48][1]='pot3d_flag';        keyws[48][2]='nq3d_flag';           keyws[48][3]='Jx_flag';          keyws[48][4]='JE_flag';              keyws[48][5]='Ebe_iky_flag';    keyws[48][6]='JE_iky_flag';
		
	keyws[49] = new Array(2);
	keyws[49][0]='CONTROL';               keyws[49][1]='read_pot3d_flag';        keyws[49][2]='check_dimen_and_exit';       

	
	var keyName = new Array();
	
	for (var i=0; i<=8; i++) keyName[i]= new Array(1);
	
	keyName[0][0]='ham_label';
	keyName[1][0]='sel_Make_xyz';
	keyName[2][0]='text_Ux';
	keyName[3][0]='text_Uy';
	keyName[4][0]='text_Uz';
	keyName[5][0]='text_Natoms_in_Uxyz';
	keyName[6][0]='sel_BandCalculation';
	keyName[7][0]='text_BandLineScale';
	keyName[8][0]='text_NumberOfBands';	
	
	keyName[9]= new Array(3);
	keyName[9][0]='Band_path';
	keyName[9][1]='Band_Range_i';
	keyName[9][2]='Band_Range_f';
	
	for (var i=10; i<=36; i++) keyName[i]= new Array(1);	
	keyName[10][0]='sel_Complex_U';
	keyName[11][0]='text_Nz';
	keyName[12][0]='text_q_values';
	keyName[13][0]='text_Max_Num_Mode';
	keyName[14][0]='text_Max_Iteration';
	keyName[15][0]='text_UTB_Ewindowmin';
	keyName[16][0]='text_E_Extension_Gamma';	
	keyName[17][0]='text_E_Extension_Plot';
	keyName[18][0]='text_Max_No_Subbands';
	keyName[19][0]='text_Selection_by_GEVP';
	keyName[20][0]='text_Selection_by_kEVP';
	keyName[21][0]='text_SVE_tol_U';
	keyName[22][0]='text_SVE_tol_Z';
	keyName[23][0]='text_E_tol_UPB_check';
	keyName[24][0]='text_Fraction_Gray_Area';
	keyName[25][0]='text_Minimum_Gray_Area';
	keyName[26][0]='sel_Inspect_Mode_Wavefn';
	keyName[27][0]='text_Tol_Stuck_Modes';
	keyName[28][0]='sel_Write_Umode';	
	keyName[29][0]='sel_Print_for_Debugging';
	keyName[30][0]='sel_Runtime_display';
	keyName[31][0]='sel_method';
	keyName[32][0]='text_Mini_Max_Iteration';
	keyName[33][0]='text_Tolerance';
	keyName[34][0]='sel_Use_Default_Stop_Rule';
	keyName[35][0]='sel_dev';
	keyName[36][0]='sel_SO';
	
	keyName[37]= new Array(10);
	keyName[37][0]='Channel_length_text';
	keyName[37][1]='Gate_length_text';
	keyName[37][2]='Source_length_text';
	keyName[37][3]='Drain_length_text';
	keyName[37][4]='Channel_thickness_text';
	keyName[37][5]='Channel_width_text';	
	keyName[37][6]='Oxide_thickness0_text';
	keyName[37][7]='Oxide_thickness1_text';
	keyName[37][8]='Oxide_thickness2_text';
	keyName[37][9]='Oxide_thickness3_text';
	
	keyName[38]= new Array(7);
	keyName[38][0]='sel_gate_type';
	keyName[38][1]='dop_type_Source';
	keyName[38][2]='Source_dop_text';
	keyName[38][3]='dop_type_Drain';
	keyName[38][4]='Drain_dop_text';	
	keyName[38][5]='dop_type_Channel';
	keyName[38][6]='Channel_dop_text';
	keyName[38][7]='chn_dielec_const';
	keyName[38][8]='ox_dielec_const';
	
	keyName[39]= new Array(2);
	keyName[39][0]='exx_0';
	keyName[39][1]='exx_1';
	
	keyName[40]= new Array(4);
	keyName[40][0]='VOLT_Vd';
	keyName[40][1]='VOLT_Vg_0';
	keyName[40][2]='VOLT_Vg_1';
	keyName[40][3]='VOLT_Vg_2';
	
	keyName[41]= new Array(3);
	keyName[41][0]='sc_conv_eps';
	keyName[41][1]='sc_max_iter_0';
	keyName[41][2]='sc_max_iter_1';
	
	keyName[42]= new Array(2);
	keyName[42][0]='num_ky';
	keyName[42][1]='E_cut';
	
	keyName[43]= new Array(1);
	keyName[43][0]='adj_to_near_flag';
	
	keyName[44]= new Array(2);
	keyName[44][0]='VOLT_Temp';
	keyName[44][1]='VOLT_Phig_offset';
		
	keyName[45]= new Array(2);
	keyName[45][0]='NEGF_hamil_eta';	
	keyName[45][1]='self_energy_routine';	
	
	keyName[46]= new Array(2);
	keyName[46][0]='solver';
	keyName[46][1]='n3d_alloc_model';
	
	keyName[47]= new Array(10);
	keyName[47][0]='EGRID_level_1_0';
	keyName[47][1]='EGRID_level_1_1';
	keyName[47][2]='EGRID_level_2_0';
	keyName[47][3]='EGRID_level_2_1';
	keyName[47][4]='EGRID_level_3_0';
	keyName[47][5]='EGRID_level_3_1';
	keyName[47][6]='EGRID_around_top_0';
	keyName[47][7]='EGRID_around_top_1';
	keyName[47][8]='EGRID_maxNe';
	
	keyName[48]= new Array(6);
	keyName[48][0]='pot3d_flag';
	keyName[48][1]='nq3d_flag';
	keyName[48][2]='Jx_flag';
	keyName[48][3]='JE_flag';
	keyName[48][4]='Ebe_iky_flag';
	keyName[48][5]='JE_iky_flag';
	
	keyName[49]= new Array(2);
	keyName[49][0]='read_pot3d_flag';
	keyName[49][1]='check_dimen_and_exit';

	
	///////////	
		  	
	    data = data.replace(/	/gi, ' ');
	    data = data.replace(/\t/gi,  ' ');
	    data = data.replace(/=/gi,   ' ');
	    data = data.replace(/\(/gi,   ' ');
	    data = data.replace(/\)/gi,   ' ');
	    data = data.replace(/,/gi,   ' ');
	    
	    data = data.replace(/ +/gi,  " ");
		   
	       	    
		var lines = data.split('\n');
		
		var ham_i, EM_i, Minimization_i, sc3d_i, Band_i, Band_f;
		
		for( var i in lines )
		{
			var line = lines[i].trim();
			var dummy = line.split(' ');
			
			if(dummy[0]=="*ham*")           ham_i=i;
			if(dummy[0]=="NumberOfBands")   Band_i=parseInt(i);			
			if(dummy[0]=="Band_Range(eV)" ) Band_f=parseInt(i);						
			if(dummy[0]=="*EM*" )           EM_i=parseInt(i);
			if(dummy[0]=="{Minimization}" ) Minimization_i=parseInt(i);
			if(dummy[0]=="*sc3d*" )         sc3d_i=parseInt(i);
						
		}
				
		//-- read from ham----------------------------------
		
		for( var i=0; i<EM_i; i++ )
		{
			var line = lines[i].trim();			
			var dummy = line.split(' ');				
						
			for(var j=0; j<=8 ;j++)
			{				
				for(var k=0; k<=keyName[j].length-1; k++)
				{
					if(dummy[0]==keyws[j][0]) document.getElementById(keyName[j][k]).value = dummy[2*k+1];
				}
			}			
			
			var j=9;
			if(dummy[0]==keyws[j][0]) 
			{			
			   document.getElementById(keyName[j][0]).value="";			
			   for(var k=Band_i+1 ;k<= Band_f-1; k++)
			   {
				   document.getElementById(keyName[j][0]).value += lines[k].trim()+"\n";   
			   }		
				
				document.getElementById(keyName[j][1]).value = dummy[1];
				document.getElementById(keyName[j][2]).value = dummy[3];				
			}								
		}
		//--------------
		
		
		for( var i=EM_i+1; i<Minimization_i; i++ )
		{
			var line = lines[i].trim();			
			var dummy = line.split(' ');				
						
			for(var j=10; j<=30 ;j++)
			{				
				for(var k=0; k<=keyName[j].length-1; k++)
				{
					if(dummy[0]==keyws[j][0]) document.getElementById(keyName[j][k]).value = dummy[k+1];
				}
			}	
											
		}
		//---------------------  Minimization --------------
		for( var i=Minimization_i+1; i<sc3d_i; i++ )
		{
			var line = lines[i].trim();			
			var dummy = line.split(' ');				
						
			for(var j=31; j<=34 ;j++)
			{				
				for(var k=0; k<=keyName[j].length-1; k++)
				{
					if(dummy[0]==keyws[j][0]) document.getElementById(keyName[j][k]).value = dummy[k+1];
				}
			}	
											
		}
		
		//-----------------------------------
		for( var i=sc3d_i+1; i<lines.length; i++ )
		{
			//alert(i+" "+lines[i].trim());
		
			var line = lines[i].trim();			
			var dummy = line.split(' ');		
			
						
			//for(var j=35; j<=49 ;j++)
			//{			
			var j;
			j=35;	if(dummy[0]=="DEV_Opt"   && dummy[1]=="structure"          ) { document.getElementById(keyName[j][0]).value = dummy[2]; }
			j=37;	if(dummy[0]=="DEV_Dimen" && dummy[1]=="Lchn_Lgate"         ) { document.getElementById(keyName[j][0]).value = dummy[2]; document.getElementById(keyName[j][1]).value = dummy[3]; }
			j=37;	if(dummy[0]=="DEV_Dimen" && dummy[1]=="Lsrc_Ldrn"          ) { document.getElementById(keyName[j][2]).value = dummy[2]; document.getElementById(keyName[j][3]).value = dummy[3]; }
			j=37;	if(dummy[0]=="DEV_Dimen" && dummy[1]=="Tchn_Wchn"          ) { document.getElementById(keyName[j][4]).value = dummy[2]; document.getElementById(keyName[j][5]).value = dummy[3]; }
			j=37;	if(dummy[0]=="DEV_Dimen" && dummy[1]=="ox_thickness"       ) { document.getElementById(keyName[j][6]).value = dummy[2]; document.getElementById(keyName[j][7]).value = dummy[3]; document.getElementById(keyName[j][8]).value = dummy[4];document.getElementById(keyName[j][9]).value = dummy[5]; }
			
			j=38;	if(dummy[0]=="DEV_Dimen" && dummy[1]=="gate_type"          ) { document.getElementById(keyName[j][0]).value = dummy[2]; }
			
			j=38;	if(dummy[0]=="DEV_Mat"   && dummy[1]=="src_drn_doping"     ) { document.getElementById(keyName[j][1]).value = dummy[2]; document.getElementById(keyName[j][2]).value = dummy[3]; document.getElementById(keyName[j][3]).value = dummy[2]; document.getElementById(keyName[j][4]).value = dummy[3];}
			j=38;	if(dummy[0]=="DEV_Mat"   && dummy[1]=="chn_doping"         ) { document.getElementById(keyName[j][5]).value = dummy[2]; document.getElementById(keyName[j][6]).value = dummy[3]; }
			j=38;	if(dummy[0]=="DEV_Mat"   && dummy[1]=="chn_dielec_const"   ) { document.getElementById(keyName[j][7]).value = dummy[2];  }
			j=38;	if(dummy[0]=="DEV_Mat"   && dummy[1]=="ox_dielec_const"    ) { document.getElementById(keyName[j][8]).value = dummy[2];  }
			
			j=39;	if(dummy[0]=="STRAIN"    && dummy[1]=="exx"                ) { document.getElementById(keyName[j][0]).value = dummy[2]; document.getElementById(keyName[j][1]).value = dummy[3]; }
			
			j=40;	if(dummy[0]=="VOLT"      && dummy[1]=="VD"                 ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=40;	if(dummy[0]=="VOLT"      && dummy[1]=="VG"                 ) { document.getElementById(keyName[j][1]).value = dummy[2]; document.getElementById(keyName[j][2]).value = dummy[3]; document.getElementById(keyName[j][3]).value = dummy[4]; }

			j=41;	if(dummy[0]=="CONTROL"   && dummy[1]=="sc_conv_eps"        ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=41;	if(dummy[0]=="CONTROL"   && dummy[1]=="sc_max_iter"        ) { document.getElementById(keyName[j][1]).value = dummy[2];  document.getElementById(keyName[j][2]).value = dummy[3];}

			j=42;	if(dummy[0]=="UTB_Opt"   && dummy[1]=="num_ky"             ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=42;	if(dummy[0]=="UTB_Opt"   && dummy[1]=="E_cut"              ) { document.getElementById(keyName[j][1]).value = dummy[2];  }
			
			j=43;	if(dummy[0]=="DEV_Opt"   && dummy[1]=="adj_to_near_flag"   ) { document.getElementById(keyName[j][0]).value = dummy[2];  }

			j=44;	if(dummy[0]=="VOLT"      && dummy[1]=="Temp"               ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=44;	if(dummy[0]=="VOLT"      && dummy[1]=="Phig_offset"        ) { document.getElementById(keyName[j][1]).value = dummy[2];  }

			j=45;	if(dummy[0]=="NEGF"      && dummy[1]=="hamil_eta"          ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=45;	if(dummy[0]=="NEGF"      && dummy[1]=="self_energy_routine") { document.getElementById(keyName[j][1]).value = dummy[2];  }

			j=46;	if(dummy[0]=="#POI_Opt"  && dummy[1]=="solver"             ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=46;	if(dummy[0]=="#POI_Opt"  && dummy[1]=="n3d_alloc_model"    ) { document.getElementById(keyName[j][1]).value = dummy[2];  }

			j=47;	if(dummy[0]=="EGRID"     && dummy[1]=="level_1"            ) { document.getElementById(keyName[j][0]).value = dummy[2]; document.getElementById(keyName[j][1]).value = dummy[3]; }
			j=47;	if(dummy[0]=="EGRID"     && dummy[1]=="level_2"            ) { document.getElementById(keyName[j][2]).value = dummy[2]; document.getElementById(keyName[j][3]).value = dummy[3]; }
			j=47;	if(dummy[0]=="EGRID"     && dummy[1]=="level_3"            ) { document.getElementById(keyName[j][4]).value = dummy[2]; document.getElementById(keyName[j][5]).value = dummy[3]; }
			j=47;	if(dummy[0]=="EGRID"     && dummy[1]=="around_top"         ) { document.getElementById(keyName[j][6]).value = dummy[2]; document.getElementById(keyName[j][7]).value = dummy[3]; }
			j=47;	if(dummy[0]=="EGRID"     && dummy[1]=="maxNe"              ) { document.getElementById(keyName[j][8]).value = dummy[2];  }

			
			j=48;	if(dummy[0]=="PRINT"     && dummy[1]=="pot3d_flag"         ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=48;	if(dummy[0]=="PRINT"     && dummy[1]=="nq3d_flag"          ) { document.getElementById(keyName[j][1]).value = dummy[2];  }
			j=48;	if(dummy[0]=="PRINT"     && dummy[1]=="Jx_flag"            ) { document.getElementById(keyName[j][2]).value = dummy[2];  }
			j=48;	if(dummy[0]=="PRINT"     && dummy[1]=="JE_flag"            ) { document.getElementById(keyName[j][3]).value = dummy[2];  }
			j=48;	if(dummy[0]=="PRINT"     && dummy[1]=="Ebe_iky_flag"       ) { document.getElementById(keyName[j][4]).value = dummy[2];  }
			j=48;	if(dummy[0]=="PRINT"     && dummy[1]=="JE_iky_flag"        ) { document.getElementById(keyName[j][5]).value = dummy[2];  }

			j=49;	if(dummy[0]=="CONTROL"   && dummy[1]=="read_pot3d_flag"      ) { document.getElementById(keyName[j][0]).value = dummy[2];  }
			j=49;	if(dummy[0]=="CONTROL"   && dummy[1]=="check_dimen_and_exit" ) { document.getElementById(keyName[j][1]).value = dummy[2];  }
											
		}

		
		
		var line_seg;
	
	    var dummy; 
	    
	    var i0, i1;


	}
	
	function getParameters()
	{
		var start = Date.now();
		//console.log('getParameters():'+start);
		  var dummy;
		  var dummy0, dummy1, dummy2, dummy3;
		  var string="";

		  ////////////---input.d -----------------
		  string+="*ham*\n";

		  string+="SIESTA_SYSTEM_LABEL "+document.getElementById('ham_label').value +"\n";

		  dummy = document.getElementById('sel_Make_xyz');
		  string+="Make_xyz.r(on,off) "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="Information_xyz.r\n";

		  string+="Ux "+document.getElementById('text_Ux').value +"\n";
		  string+="Uy "+document.getElementById('text_Uy').value +"\n";
		  string+="Uz "+document.getElementById('text_Uz').value +"\n";

		  string+="Natoms_in_Ux*Uy*Uz "+document.getElementById('text_Natoms_in_Uxyz').value +"\n";
		  string+="Eshift(eV) 0.00 \n";

		  dummy = document.getElementById('sel_BandCalculation');
		  string+="BandCalculation(on,off) "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="BandLineScale "+document.getElementById('text_BandLineScale').value +"\n";
		  string+="NumberOfBands "+document.getElementById('text_NumberOfBands').value +"\n";
		  string+= document.getElementById('Band_path').value +"\n";
		  string+="Band_Range(eV) "+document.getElementById('Band_Range_i').value+" to "+document.getElementById('Band_Range_f').value +"\n";


		  /////  --em-----

		  string+="*EM*\n";

		  string+="{EM_METHOD}\n";

		  dummy = document.getElementById('sel_Complex_U');

		  string+=" Complex_U = "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+=" Nz = " +document.getElementById('text_Nz').value +"\n";
		  string+=" q_values = " +document.getElementById('text_q_values').value +"\n";

		  string+=" Max_Num_Mode = " +document.getElementById('text_Max_Num_Mode').value +"\n";
		  string+=" Max_Iteration = " +document.getElementById('text_Max_Iteration').value +"\n";

		  dummy0=document.getElementById('text_Energy_Window' ).value;
		  dummy1=document.getElementById('text_Energy_Window_1').value;

		  string+=" Energy_Window = ("+dummy0+","+dummy1+")\n";
		  string+=" UTB_Ewindowmin = " +document.getElementById('text_UTB_Ewindowmin').value +"\n";
		  string+=" E_Extension_Gamma = " +document.getElementById('text_E_Extension_Gamma').value +"\n";
		  string+=" E_Extension_Plot = " +document.getElementById('text_E_Extension_Plot').value +"\n";
		  string+=" Max_No_Subbands = " +document.getElementById('text_Max_No_Subbands').value +"\n";

		  string+=" Selection_by_GEVP = " +document.getElementById('text_Selection_by_GEVP').value +"\n";
		  string+=" Selection_by_kEVP = " +document.getElementById('text_Selection_by_kEVP').value +"\n";

		  string+=" SVE_tol_U = " +document.getElementById('text_SVE_tol_U').value +"\n";
		  string+=" SVE_tol_Z = " +document.getElementById('text_SVE_tol_Z').value +"\n";
		  string+=" E_tol_UPB_check = " +document.getElementById('text_E_tol_UPB_check').value +"\n";
	  	  string+=" Fraction_Gray_Area = " +document.getElementById('text_Fraction_Gray_Area').value +"\n";
		  string+=" Minimum_Gray_Area = " +document.getElementById('text_Minimum_Gray_Area').value +"\n";

		  dummy = document.getElementById('sel_Inspect_Mode_Wavefn');
		  string+="Inspect_Mode_Wavefn = "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="Tol_Stuck_Modes = " +document.getElementById('text_Tol_Stuck_Modes').value +"\n";

		//  dummy = document.getElementById('sel_Read_Umode');
		  string+="Read_Umode = no\n";

		  dummy = document.getElementById('sel_Write_Umode');
		  string+="Write_Umode = "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('sel_Print_for_Debugging');
		  string+="Print_for_Debugging = "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('sel_Runtime_display');
		  string+="Runtime_display = "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="{Minimization}\n";

		  dummy = document.getElementById('sel_method');
		  string+="Method = "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="Max_Iteration = " +document.getElementById('text_Mini_Max_Iteration').value +"\n";
		  string+="Tolerance = " +document.getElementById('text_Tolerance').value +"\n";

		  dummy = document.getElementById('sel_Use_Default_Stop_Rule');
		  string+="Use_Default_Stop_Rule = "+dummy.options[dummy.selectedIndex].value+"\n";

		  //------sc3d---------------

		  string+="*sc3d*\n";

		  dummy = document.getElementById('sel_dev');
		  string+="DEV_Opt structure "+dummy.options[dummy.selectedIndex].value+"\n";
		  string+="DEV_Opt opmode Band\n";
		  string+="DEV_Opt RBT EM\n";

		  string+="HAMIL method TB\n";
		  string+="HAMIL import overlap\n";
		  string+="HAMIL DFT_HLIB ./hamil\n";

		  dummy = document.getElementById('sel_SO');
		  string+="TB_Param SO "+dummy.options[dummy.selectedIndex].value+"\n";

		  var L_channel = document.getElementById('Channel_length_text').value ;
		  var L_gate = document.getElementById('Gate_length_text').value ;

		  string+="DEV_Dimen Lchn_Lgate (" +L_channel+", "+ L_gate+") \n";

		  var L_source = document.getElementById('Source_length_text').value ;
		  var L_drain = document.getElementById('Drain_length_text').value ;

		  string+="DEV_Dimen Lsrc_Ldrn (" +L_source+", "+ L_drain+") \n";

		  var T_channel = document.getElementById('Channel_thickness_text').value ;
		  var W_channel = document.getElementById('Channel_width_text').value ;

		  string+="DEV_Dimen Tchn_Wchn (" +T_channel+", "+ W_channel+") \n";

		  dummy0 = document.getElementById('Oxide_thickness0_text').value ;
		  dummy1 = document.getElementById('Oxide_thickness1_text').value ;
		  dummy2 = document.getElementById('Oxide_thickness2_text').value ;
		  dummy3 = document.getElementById('Oxide_thickness3_text').value ;

		  string+="DEV_Dimen ox_thickness ("+dummy0+", "+dummy1+", "+dummy2+", "+dummy3+")\n";

		  dummy = document.getElementById('sel_gate_type');

		  string+="DEV_Dimen gate_type "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('dop_type_Source');

		  string+="DEV_Mat src_drn_doping (" +dummy.options[dummy.selectedIndex].value+", "+ document.getElementById('Source_dop_text').value+") \n";

		  dummy = document.getElementById('dop_type_Channel');

		  string+="DEV_Mat chn_doping (" +dummy.options[dummy.selectedIndex].value+", "+ document.getElementById('Channel_dop_text').value+") \n";
		  string+="DEV_Mat chn_dielec_const "+document.getElementById('chn_dielec_const').value+"\n";
		  string+="DEV_Mat ox_dielec_const " +document.getElementById('ox_dielec_const').value +"\n";

		  string+="STRAIN exx ("+document.getElementById('exx_0').value+", "+ document.getElementById('exx_1').value+") \n";

		  string+="VOLT VD " +document.getElementById('VOLT_Vd').value +"\n";
		  var Vg_0,Vg_1,Vg_2;
		  Vg_0 = document.getElementById('VOLT_Vg_0').value;
		  Vg_1 = document.getElementById('VOLT_Vg_1').value;
		  Vg_2 = document.getElementById('VOLT_Vg_2').value;

		  string+="VOLT VG (" +Vg_0 +", "+ Vg_1 +", "+ Vg_2 +")\n";

		  string+="CONTROL sc_conv_eps " +document.getElementById('sc_conv_eps').value +"\n";

		  var sc_max_iter_0, sc_max_iter_1;
		  sc_max_iter_0 = document.getElementById('sc_max_iter_0').value;
		  sc_max_iter_1 = document.getElementById('sc_max_iter_1').value;

		  string+="CONTROL sc_max_iter (" +sc_max_iter_0+", "+ sc_max_iter_1+") \n";

		  string+="UTB_Opt num_ky " +document.getElementById('num_ky').value +"\n";
		  string+="UTB_Opt E_cut " +document.getElementById('E_cut').value +"\n";

		  string+="------ADVANCED ----------------\n";

		  dummy = document.getElementById('adj_to_near_flag');
		  string+="DEV_Opt adj_to_near_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="VOLT Temp " +document.getElementById('VOLT_Temp').value +"\n";
		  string+="VOLT Phig_offset " +document.getElementById('VOLT_Phig_offset').value +"\n";
		  string+="NEGF hamil_eta " +document.getElementById('NEGF_hamil_eta').value +"\n";

		  dummy = document.getElementById('self_energy_routine');
		  string+="NEGF self_energy_routine "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('solver');
		  string+="#POI_Opt solver "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('n3d_alloc_model');
		  string+="#POI_Opt n3d_alloc_model "+dummy.options[dummy.selectedIndex].value+"\n";

		  var EGRID_level_0 = document.getElementById('EGRID_level_1_0').value, EGRID_level_1 = document.getElementById('EGRID_level_1_1').value;
		   string+="EGRID level_1 (" +EGRID_level_0+", "+ EGRID_level_1+") \n";

		  EGRID_level_0 = document.getElementById('EGRID_level_2_0').value, EGRID_level_1 = document.getElementById('EGRID_level_2_1').value;
	  	  string+="EGRID level_2 (" +EGRID_level_0+", "+ EGRID_level_1 +")\n";

		  EGRID_level_0 = document.getElementById('EGRID_level_3_0').value, EGRID_level_1 = document.getElementById('EGRID_level_3_1').value;
		  string+="EGRID level_3 (" +EGRID_level_0+", "+ EGRID_level_1 +")\n";

		  var EGRID_around_top_0 = document.getElementById('EGRID_around_top_0').value, EGRID_around_top_1 = document.getElementById('EGRID_around_top_1').value;
		  string+="EGRID around_top (" +EGRID_around_top_0+", "+ EGRID_around_top_1 +")\n";

		  string+="EGRID maxNe " +document.getElementById('EGRID_maxNe').value +"\n";

		  dummy = document.getElementById('pot3d_flag');
		  string+="PRINT pot3d_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('nq3d_flag');
		  string+="PRINT nq3d_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('Jx_flag');
		  string+="PRINT Jx_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('JE_flag');
		  string+="PRINT JE_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('Ebe_iky_flag');
		  string+="PRINT Ebe_iky_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('JE_iky_flag');
		  string+="PRINT JE_iky_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('read_pot3d_flag');
		  string+="CONTROL read_pot3d_flag "+dummy.options[dummy.selectedIndex].value+"\n";

		  dummy = document.getElementById('check_dimen_and_exit');
		  string+="CONTROL check_dimen_and_exit "+dummy.options[dummy.selectedIndex].value+"\n";

		  string+="\n";
		  
		var end = Date.now();
		console.log('Spent: '+end+'-'+start+'='+(end-start));

	    return string;
	    
	    ///
	    
	}
	
	
	

	  function Sel_ini_parameters(struc)
	  {

		  for (var j=0; j<N_struc_param-1; j++)
		  {

			  if (j==6)
			  {
				  for (k=0; k<4;k++)
				  {
					  document.getElementById(Control_TR[j][0][k]).value = Ini_parameters[struc][j];
					  document.getElementById(Control_TR[j][1][k]).value = Ini_parameters[struc][j];
				  }
			  }
			  else
			  {
				  document.getElementById(Control_TR[j][0]).value = Ini_parameters[struc][j];
				  document.getElementById(Control_TR[j][1]).value = Ini_parameters[struc][j];
			  }

		  }

		  if (struc==1 || struc==2)
		  {
			  document.getElementById(Control_TR[5][0]).disabled = true;
			  document.getElementById(Control_TR[5][1]).disabled = true;
			  document.getElementById("sel_gate_type").value = "Double";
			  document.getElementById("sel_gate_type").disabled = true;
		  }
		  else
		  {
			  document.getElementById(Control_TR[5][0]).disabled = false;
			  document.getElementById(Control_TR[5][1]).disabled = false;
			  document.getElementById("sel_gate_type").disabled = false;
		  }

		  draw_struc();


		//  dummy = document.getElementById('sel_gate_type');          string += dummy.options[dummy.selectedIndex].value+"\n";

	    return ;

	}




</script>
</body>
</html>