<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
  <%@include file="../init.jsp"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/2dmesh/bc.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/2dmesh/wb.css"/>
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.2/css/bulma.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/2dmesh/bulma-tooltip.min.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/2dmesh/bulma-pageloader.min.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/2dmesh/all.css"/>
  <style>
    body{
      margin :0;
    }
    .lineTooltip {
      position: absolute;
      text-align: left;
      width: auto;
      height: auto;
      padding: 8px;
      margin-top: 10px;
      margin-left: 10px;
      font: 15px sans-serif;
      background: #fff;
      pointer-events: none;
    }
    .dropdown-menu {
      min-width: 6rem;
    }
  </style>
</head>
<body>
  <div class="wb-appFrame">
    <div class="wb-appFrame-header">
      <nav class="level">
        <div class="level-left">
          <p class="level-item" id="meshName">Name</p>
          <!--
          <div class="level-item">
            <input class="input" type="text" placeholder="Text input">
          </div>
          -->
        </div>
        <div class="level-left">
          <div class="level-item">
            <p class="control">
              <a class="button is-link tooltip is-tooltip-bottom"  data-tooltip="Mesh View" id='meshButton'>
                <span class="icon">
                  <i class="fas fa-th-large"></i>
                </span>
              </a>
            </p>
            <p class="control">
              <a class="button is-primary tooltip is-tooltip-bottom"  data-tooltip="BC(Boundary Condition) View" id='bcButton'>
                <span class="icon">
                  <i class="fas fa-indent"></i>
                </span>
              </a>
            </p>
            <p class="control">
              <a class="button tooltip is-tooltip-bottom"  data-tooltip="BC Title View" id='bcTitleButton'>
                <span class="icon">
                  <i class="fas fa-font"></i>
                </span>
              </a>
            </p>
          </div>
          <div class="level-item">
            <p class="control">
              <a class="button tooltip is-tooltip-bottom"  data-tooltip="Zoom reset" id='resetButton'>
                <span class="icon">
                  <i class="fas fa-home"></i>
                </span>
              </a>
            </p>
            <p class="control">
              <a class="button zoomed tooltip is-tooltip-bottom"  data-tooltip="Zoom in" id='zoom_in'>
                <span class="icon">
                  <i class="fas fa-search-plus"></i>
                </span>
              </a>
            </p>
            <p class="control">
              <a class="button zoomed tooltip is-tooltip-bottom"  data-tooltip="Zoom out" id='zoom_out'>
                <span class="icon">
                  <i class="fas fa-search-minus"></i>
                </span>
              </a>
            </p>
          </div>
          <div class="level-item">
            <p class="control">
              <a class="button is-info tooltip is-tooltip-bottom"  data-tooltip="Save" id='mesh_save'>
                <span class="icon">
                  <i class="fas fa-save"></i>
                </span>
                <span>Save</span>
              </a>
            </p>
          </div>
          <div class="level-item">
            <div class="dropdown is-right">

              <div class="dropdown-trigger">
              <button class="button dropdown-trigger" aria-haspopup="true" aria-controls="dropdown-menu">
                <span class="icon">
                  <i class="fas fa-bars"></i>
                </span>
              </button>
              <div class="dropdown-menu" id="dropdown-menu" role="menu">
                <div class="dropdown-content">
                  <div class="dropdown-item" id="">

                    <p><strong>Load</strong></p>
                  </div>
                  <a class="dropdown-item" id="sampleLoadButton">
                    <span>Sample File</span>
                  </a>
                  <a class="dropdown-item" id="serverLoadButton">
                    <span>Server File</span>
                  </a>
                  <a class="dropdown-item">
                    <input class="file-input" type="file" name="resume" accept=".msh" id='localLoadButton'>
                      <span>Local File</span>
                    </input>
                  </a>
                  <hr class="dropdown-divider">
                  <a class="dropdown-item" id='exportButton'>
                    <span>Export</span>
                  </a>
                  <a class="dropdown-item" id='mesh_saveAs'>
                    <span>Save As</span>
                  </a>
                </div>
              </div>
            </div>
            </div>
          </div>
        </div>

      </nav>
    </div>
    <div class='wb-appFrame-ViewerColumn' id='canvas'>
      <div class="box settingPanel displayOff" id='editBCPanel'>
        <article class="message is-small">
          <div class="message-header">
            <p>Edit Boundary Conditons</p>
            <button class="delete is-small" aria-label="delete" id="editBCPanelDelete"></button>
          </div>
          <div class="message-body">

            <div class="message-line-margin">
              <label class="label is-medium" id="BCsettingTilte"><h1>Grid4 > jMax > 0 orders</h1></label>
              <label class="label is-medium" id="BCsettingTilteSub"><h1>Grid4 > jMax > 0 orders</h1></label>
            </div>

            <div class="message-line-margin">
              <label class="label is-small">Boundary conditions</label>
              <div class="select is-fullwidth">
                <select id="BCsettingSelect">
                </select>
              </div>
            </div>
            <div class="message-line-margin displayOff" id="additionalValueView1">
              <label class="label is-small" id="additionalValueTitle1">Title1</label>
              <p class="control">
                <input class="input is-small" id="additionalValueInput1"   onClick="this.select();" type="number" step="1.0" value=0>
              </p>
            </div>
            <div class="message-line-margin displayOff" id="additionalValueView2">
              <label class="label is-small" id="additionalValueTitle2">Title2</label>
              <p class="control">
                <input class="input is-small" id="additionalValueInput2"  onClick="this.select();" type="number" step="any" value=0>
              </p>
            </div>
            <div class="message-line-margin displayOff" id="additionalValueView3">
              <label class="label is-small" id="additionalValueTitle3">Title3</label>
              <p class="control">
                <input class="input is-small" id="additionalValueInput3"   onClick="this.select();" type="number" step="any" value=0>
              </p>
            </div>
            <div class="buttons has-addons is-right">
              <a class="button is-success is-small" id="saveBC">
                <span class="icon">
                  <i class="fas fa-check"></i>
                </span>
                <span>Save</span>
              </a>
            </div>
          </div>
        </article>
      </div>
    </div>
    <div class="pageloader" id="pageloader"><span class="title"> Loading Save As ...</span>
    </div>
  </div>

  <script src="<%=request.getContextPath()%>/js/2dmesh/d3.min.js"></script>
  <script src="<%=request.getContextPath()%>/js/2dmesh/app.js"></script>
  <script src="<%=request.getContextPath()%>/js/2dmesh/loadsgv.js"></script>
  <script>

    const BCdict = {
      0: "0. Automatic Setting",
      1: "1. Viscous Adiabatic Wall",
      2: "2. Viscous Isothermal Wall",
      3: "3. Inviscid Wall",
      4: "4. Moving Wall",
      5: "5. Boundary Layer Specified",
      6: "6. Symmetric BC",
      7: "7. Subsonic Inlet",
      8: "8. Prescribed Inflow Velocity",
      9: "9. Subsonic Outlet",
      10: "10. Convective Condition",
      11: "11. Supersonic Inlet",
      12: "12. Supersonic Outlet",
      13: "13. Periodic BC",
      14: "14. Far-Field BC",
      15: "15. Mixed BC-INVALID",
      16: "16. Multi BC-INVALID",
      17: "17. Pressure Outlet",
      18: "18. Mass Flow Outlet",
      19: "19. Overset BC",
      20: "20. Singular Line",
      21: "21. Wedge",
      22: "22. Fixed Value",
      23: "23. Zero Gradient",
      24: "24. Synthetic Jet BC",
      31: "31. Subsonic Inflow",
      32: "32. Mass Flow In",
      33: "33. Pressure Outflow",
      34: "34. Pressure Outflow (Subsonic)",
      35: "35. Mass Flow Out",
      36: "36. Viscous Wall BC (Advanced)",
      99: "99. Block Communication"
    };
    const BCAddValue = {
      2: ['Wall Temperature'],
      4: ['Moving Velocity(x)', 'Moving Velocity(y)'],
      17: ['Static Pressure'],
      18: ['Mass Flow Rate'],
      24: ['Frequency', 'Max Jet Velocity', 'Jet Angle'],
      31: ['Total Pressure', 'Total Temperature'],
      32: ['Mass Flow Rate', 'Total Temperature'],
      33: ['Static Pressure'],
      34: ['Static Pressure'],
      35: ['Mass Flow Rate'],
      36: ['Moving Velocity(x)', 'Moving Velocity(y)', 'Wall Temperature'],
    }
  	var namespace;
  	function setNamespace( ns ){
  		namespace = ns;
  	}

    var meshData = {};
    var BCArrayList = [];
    var currentFileStatus = {
      type : "",
      name : ""
    };
    generateSelectOption();
//    setup();

    function init2DmeshEditor(){
      if (!(d3.select('#mainCanvas').empty())) {
        d3.select("#mainCanvas").remove();
      }
      setMeshName("Name");
      saveButtonOff();
    };

    function setMeshName(title){
      d3.select("#meshName").text(title);
    };

    function view2Dmesh(data){

      if (!(d3.select('#mainCanvas').empty())) {
        d3.select("#mainCanvas").remove();
      } else {

        if (!(d3.select('#editBCPanel').classed('displayOff'))) {
          d3.select('#editBCPanel').classed('displayOff', true);
        }

        if (!(d3.select("#bcButton").classed("is-primary"))) {
          d3.select("#bcButton").classed("is-primary", true);
        }
        if (!(d3.select("#meshButton").classed("is-link"))) {
          d3.select("#meshButton").classed("is-link", true);
        }
        if (d3.select("#bcTitleButton").classed("is-primary")) {
          d3.select("#bcTitleButton").classed("is-primary", false);
        }
      }


      meshData = readMeshData(data);
      makeSVG(meshData);
      saveButtonOff();
    };

    function getContent (){
      return saveMeshData();
    }
    function disable( flag ){
    
    }
	

    function saveMeshData() {
      var rank = meshData.rank;
      var lowRank = '';
      //    console.log(rank[0]);
      for (var i = 0; i < rank.length; i++) {
        lowRank = lowRank.concat('rank ' + i.toString(), '\n');
        for (var j = 0; j < rank[i].length; j++) {
          lowRank = lowRank.concat(rank[i][j].length, '\n');

          for (var k = 0; k < rank[i][j].length; k++) {
            var bcline = rank[i][j][k];
            lowRank = lowRank.concat(bcline.beginIndex, " ", bcline.endIndex, " ", bcline.BCIndex, " ");
            if (typeof bcline.additionalValue !== 'undefined') {
              for (var q = 0; q < bcline.additionalValue.length; q++) {
                lowRank = lowRank.concat(bcline.additionalValue[q], " ");
              }
              lowRank = lowRank.concat("\n");
            } else {
              lowRank = lowRank.concat("0.0 \n");
            }
          }
        }
      }
      return lowRank = meshData.lowDatas.concat(lowRank, meshData.lowBlockComm);
    };


    function generateSelectOption() {
      var selectList = document.getElementById("BCsettingSelect");
      for (var prop in BCdict) {
        var option = document.createElement("option");
        option.value = prop;
        option.text = BCdict[prop];
        selectList.appendChild(option);
      }
    };
    let dropdown = document.querySelector('.dropdown');
    dropdown.addEventListener('click', function(event) {
        event.stopPropagation();
        dropdown.classList.toggle('is-active');
    });
    document.addEventListener('click', function (event) {
      dropdown.classList.remove('is-active');
    });


    d3.select("#mesh_saveAs").on("click", function() {
      var function_name = namespace + 'saveAtServerAs';
      parent[function_name]( saveMeshData() );
    });

    d3.select("#exportButton").on("click", function() {
      var function_name = namespace + 'downloadCurrentFile';
      parent[function_name]();
    });


    d3.select("#mesh_save").on("click", function() {
      if (d3.select("#mesh_save").attr("disabled") === null ) {
        var function_name = namespace + 'saveAtServer';
        var mesh = saveMeshData();
        parent[function_name]( mesh );

        fireDataChangedEvent( mesh );
      }
    });


    d3.select("#sampleLoadButton").on("click", function() {
      var function_name = namespace + 'fireRequestSampleContentEvent';
//     currentFileStatus.type = "sample";
//     currentFileStatus.name = "sample.msh";
//     saveButtonOn();
//     setMeshName(currentFileStatus.name);
      parent[function_name]();

    });

    d3.select("#serverLoadButton").on("click", function() {
      var function_name = namespace + 'openServerFile';
      parent[function_name]();
    });

    d3.select("#localLoadButton").on("change", function() {
      if (!(d3.select('#editBCPanel').classed('displayOff'))) {
        d3.select('#editBCPanel').classed('displayOff', true);
      }
      d3.select("#mainCanvas").remove();

      if (!(d3.select("#bcButton").classed("is-primary"))) {
        d3.select("#bcButton").classed("is-primary", true);
      }
      if (!(d3.select("#meshButton").classed("is-link"))) {
        d3.select("#meshButton").classed("is-link", true);
      }
      if (d3.select("#bcTitleButton").classed("is-primary")) {
        d3.select("#bcTitleButton").classed("is-primary", false);
      }
      var function_name = namespace + 'saveAtServerAs';
      
      
      var file = d3.event.target.files[0];
      if (file) {
        var reader = new FileReader();
        reader.onloadend = function(evt) {
          var dataUrl = evt.target.result;
          d3.text(dataUrl).then(function(data) {
            meshData = readMeshData(data);
            makeSVG(meshData);
            currentFileStatus.type = "file";
            currentFileStatus.name = file.name.replace(/\s/g, '');
            console.log(currentFileStatus);

            setMeshName(currentFileStatus.name);
            saveButtonOn();
            parent[function_name](data);
          });
        };
        reader.readAsDataURL(file);
      }

    });

    function getLocalFile (){
      var file = currentFileStatus;
      return file;
    }


    function loadingPageOn (){
      var pageloader = document.getElementById('pageloader');
      pageloader.classList.add('is-active');
      console.log('loadingPageOn');
    }

    function loadingPageOff(){
      var pageloader = document.getElementById('pageloader');
      pageloader.classList.remove('is-active');
      console.log('loadingPageOff');
    }

    function saveButtonOn(){
      d3.select("#mesh_save").attr("disabled",null);
    };

    function saveButtonOff(){
      d3.select("#mesh_save").attr("disabled","");
    };

    saveButtonOff();

	
	function fireDataChangedEvent( content ){
		setTimeout(
				function(){
					if( namespace ){
						window.parent[namespace+'fireDataChangedEvent']( content );
					}
					else{
						fireDataChangedEvent( content );
					}
				},
				10
			);
	}



  </script>
</body>

</html>
