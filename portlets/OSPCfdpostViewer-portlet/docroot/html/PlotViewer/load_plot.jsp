<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ionicons.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/AdminLTE.min.css">
	<!-- AdminLTE Skins. Choose a skin from the css/skins
	     folder instead of downloading all of them to reduce the load. -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/_all-skins.min.css">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">


</head>
<body class="hold-transition skin-blue  ">
	<!-- Site wrapper -->
	<div class="wrapper">
		  <!-- Content Wrapper. Contains page content -->
		  <div class="content-wrapper" style="margin-left: 0px">
		    <!-- Main content -->
		    <section class="content">
		      <div class="nav-tabs-custom" style="margin-bottom : 0px">
		          <ul class="nav nav-tabs">
		            <li class="dropdown pull-right">
		                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="true">
		                  <i class="fa fa-gear"></i>
		                </a>
		                <ul class="dropdown-menu" style="z-index : 1040" >
		                  <div class="content" style="min-height : 0">

		                  </div>
		                </ul>
		              </li>
		          </ul>
		          <div class="tab-content">
		          </div>
		          <!-- /.tab-content -->
		        </div>
		    </section>
		    <!-- /.content -->
		 </div>
		 <!-- /.content-wrapper -->

	 </div>
	 <!-- ./wrapper -->

	 <!-- jQuery 3 -->
	 <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
	 <!-- Bootstrap 3.3.7 -->
	 <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
	 <!-- SlimScroll -->
	 <script src="<%=request.getContextPath()%>/js/jquery.slimscroll.min.js"></script>
	 <!-- FastClick -->
	 <script src="<%=request.getContextPath()%>/js/fastclick.js"></script>
	 <!-- AdminLTE App -->
	 <script src="<%=request.getContextPath()%>/js/adminlte.min.js"></script>
	 <!-- Plotly libs -->
	 <script src="<%=request.getContextPath()%>/js/plotly-latest.min.js"></script>

  <script>

		var namespace;

		function setNamespace( ns ){
			namespace = ns;
		}

    $('div.content').on('change','select' ,function(){
//      console.log($(this).get(0).id);
//      console.log($(this).find(":selected").val());
//      console.log($(this).find(":selected").text());
      var selectOption =  $(this).find(":selected").text();

      var getAxis = $(this).get(0).id.split('_')[1];
      var getTabIDNum = $(this).get(0).id.split('_')[2];

      for (var i = 0; i < plotDataArray.length; i++) {
        if (plotDataArray[i].tabIDNum == getTabIDNum ) {
          if (plotDataArray[i].plotType == '2D') {
            if (getAxis == 'x') {
              plotDataArray[i].plotData[0].x = plotDataArray[i][selectOption];
              Plotly.update(plotDataArray[i].divClass, plotDataArray[i].plotData, plotDataArray[i].plotLayout );

            } else if (getAxis == 'y') {
                plotDataArray[i].plotData[0].y = plotDataArray[i][selectOption];
                Plotly.update(plotDataArray[i].divClass, plotDataArray[i].plotData, plotDataArray[i].plotLayout );
            }


          } else if (plotDataArray[i].plotType == '3D') {
            if (getAxis == 'x') {
              plotDataArray[i].plotData[1].x = plotDataArray[i][selectOption];
              Plotly.update(plotDataArray[i].divClass, plotDataArray[i].plotData, plotDataArray[i].plotLayout );

            } else if (getAxis == 'y') {
                plotDataArray[i].plotData[1].y = plotDataArray[i][selectOption];
                Plotly.update(plotDataArray[i].divClass, plotDataArray[i].plotData, plotDataArray[i].plotLayout );
            } else if (getAxis == 'z') {
                plotDataArray[i].plotData[0].z = plotDataArray[i][selectOption];
                Plotly.update(plotDataArray[i].divClass, plotDataArray[i].plotData, plotDataArray[i].plotLayout );
            }
          }
        }
      }
    });

    var plotMarginWidth = parseInt($('.content').css("padding-right"))
     + parseInt($('.content').css("padding-left"))
     + parseInt($('.tab-content').css("padding-right"))
     + parseInt($('.tab-content').css("padding-left"));


    var plotMarginHeight = parseInt($('.content').css("padding-top"))
      + parseInt($('.content').css("padding-bottom"))
      + parseInt($('.tab-content').css("padding-top"))
      + parseInt($('.tab-content').css("padding-bottom"));

    var d3 = Plotly.d3;
    var gd;

    var plotDataArray = [];
    var plotDataCount = 0;

    resizeLayout = function (clickTagNum){
      var plotDataFlag = false;

      for (var i = 0; i < plotDataArray.length; i++) {
        //plotDataArray[i]
        if (plotDataArray[i].tabIDNum == clickTagNum) {
          plotDataFlag = true;
        }
      }
      if (!plotDataFlag){ return -1; }

      var gd3 = d3.select('#tap_'+clickTagNum + '> div');
      gd = gd3.node();
      if (!gd) { return -1; }

      var h = $( window ).height();
      var w = $( window ).width();

      var update = {
        width: w - plotMarginWidth,
        height: h - plotMarginHeight - $('.nav-tabs').height()
      };
      Plotly.relayout(gd, update);
    }

    window.onresize = function() {
      var h = $( window ).height();
      var w = $( window ).width();

      var update = {
        width: w - plotMarginWidth,
        height: h - plotMarginHeight - $('.nav-tabs').height()
      };

      Plotly.relayout(gd, update);
    };

    var readFileList = {};

    subTapControll = function (clickTap){
      for (var i = 0; i < readFileList.length; i++) {
        $('#sub_tap_'+i).css("display", 'none' );
        if (clickTap == i) {
          $('#sub_tap_'+i).css("display", '' );
        }
      }
    };

    $(function() {
      for (var i = 1; i < readFileList.length; i++) {
        $(readFileList[i].tabID).one( "click", function() {
          var clickTagNum =this.id.slice(-1);
          getCFDData(readFileList[clickTagNum] );

        });
      }
      for (var i = 0; i < readFileList.length; i++) {
        $(readFileList[i].tabID).click( function() {
          var clickTagNum =this.id.slice(-1);
          subTapControll(clickTagNum);
          resizeLayout(clickTagNum);
        });


      }

    });

    var serveResourceURL = '';
		var jobParentPath ='';

    function drawPlot (data_json, parentPath, serveResourceURL2){

			if (!(serveResourceURL == '')) {
				return 0;
			}

			jobParentPath = parentPath;

    	serveResourceURL = serveResourceURL2;
    	data = JSON.parse(data_json);
      readFileList = JSON.parse(data_json);

			console.log("namespace ::", namespace);
			var filepath_name = namespace+"fileName"

			console.log("filepath_name ::", filepath_name);

      for (var i = 0; i < data.length; i++) {
        var tab_num = i;
        var tabString = '#nav_tap_' + tab_num;
        var tabContentID = 'tap_' + tab_num;

        readFileList[i].tabID = tabString;
        readFileList[i].tabContentID = tabContentID;

//				dataload[filepath_name] = data[i].text;

//				console.log(readFileList);

        if(i==0) {
          $('.nav-tabs').append($('<li />',{ 'class' : 'active'})
                        .append($('<a />',{ 'id': 'nav_tap_' + tab_num , 'href' : '#tap_' + tab_num, 'data-toggle' : 'tab' })
                        .html(data[i].text.split('.')[0].charAt(0).toUpperCase()
                               + data[i].text.split('.')[0].slice(1))));

          $('.tab-content').append($('<div />',
                                      { 'class' : 'tab-pane active', 'id' : tabContentID }));
        } else {
          $('.nav-tabs').append($('<li />')
                        .append($('<a />',{ 'id': 'nav_tap_' + tab_num , 'href' : '#tap_' + tab_num, 'data-toggle' : 'tab' })
                        .html(data[i].text.split('.')[0].charAt(0).toUpperCase()  //첫번째 대문자 나머지 소문자 변환
                               + data[i].text.split('.')[0].slice(1))));

          $('.tab-content').append($('<div />',{ 'class' : 'tab-pane', 'id' : tabContentID }));
        }
      }
      //console.log(readFileList);
      getCFDData(readFileList[0]);
    };

    select_option = function  (tab_index, div_class, selectOption ) {
      $("#sub_tap_" + tab_index +" > ." + div_class + " > select option:eq(" +selectOption+ ")")
        .attr("selected", "selected");
    }

    createSubTab = function (tab_num, plotType){
      $('.dropdown-menu > div').append($('<form />', { 'role' : 'form', 'id' : 'sub_tap_' + tab_num})
                              .append($('<div />', {'class' : 'form-group x_axis', })
                                .append($('<label />').html('Select x axis'))) );

      $('#sub_tap_'+tab_num ).append($('<div />', {'class' : 'form-group y_axis'})
                          .append($('<label />').html('Select y axis')));

      $('#sub_tap_'+tab_num +'> .x_axis')
            .append($('<select />', { 'class' : 'form-control', 'id':'select_x_' + tab_num}));

      $('#sub_tap_'+tab_num +'> .y_axis')
            .append($('<select />', {'class' : 'form-control', 'id':'select_y_' + tab_num}));

      if (plotType == '3D') {
        $('#sub_tap_'+tab_num ).append($('<div />', {'class' : 'form-group z_axis'})
                            .append($('<label />').html('Select z data')));
        $('#sub_tap_'+tab_num +'> .z_axis')
              .append($('<select />', {'class' : 'form-control', 'id':'select_z_' + tab_num}));
      }
    //  $('#sub_tap_'+tab_num +'> .x_axis')
    //        .append($('<select />', {'class' : 'form-control', 'id' : 'select_x_' + tab_num}));


    }

    readCFDData = function (data, class_path){
      var datas = data.trim().split(/[\s,="']+/);
      var mode = 0;

  //    console.log(datas);
      var colum_val =[];
      var zone = {};
      var zone_val = [];
      var zone_keys = [];
      var trace = {};
      var x_langth, y_langth;
      var r = 0;
      var plotType = '2D';

      if (!datas[0].toLowerCase().match(/^variables/)) {

        var lines = data.split('\n');

        for (var qq = 0; qq < lines.length; qq++) {
          var getId = '#tap_' + class_path.slice(-1);
          $(getId).append($('<p />').html(lines[qq]));
        }
//        return 0;
      } else { // plot3D type
        for(var i = 0; i < datas.length; i++) {
          if (datas[i].toLowerCase().match(/^variables/)) {
            var j = 0;
            while(!datas[i+1].toLowerCase().match(/^zone/)){
              i = i + 1;
              //console.log(datas[i])
              var replaced = datas[i].replace(/\W*/g,'').toLowerCase();
              //console.log(replaced)
              if ( replaced ) {
                colum_val[j++] = replaced;
              }
            }
          } else if (datas[i].toLowerCase().match('zone')){
            i=i+1;
            var j = 0;
            var key = "";
            var value = "";
            var i_flag = false, j_flag = false;

            //console.log(datas[i]);
            while ( datas[i].replace(/\W+/g,'').match(/^[a-zA-Z]/) ) {
              var key = datas[i].replace(/\W+/g,'').toLowerCase();
              var value = "";
              while ( ! value ) {
                value = datas[++i].replace(/\W+/g,'').toLowerCase();
              }
              if( key.match(/[i]/)){
                zone[key] = parseInt(value);
                i_flag = true;
              } else if( key.match(/[j]/)){
                zone[key] = parseInt(value);
                j_flag = true;

              } else if ( key.match(/[tf]/)) {
                zone[key] = value;
              }

              i++;
            }
            i--;

            if (i_flag && j_flag ) {
              plotType = '3D';
            } else {
              plotType = '2D';


              for (var p = 0; p < colum_val.length; p++) {
                trace[colum_val[p]] = new Array();
              }
            }
          } else {     //read data
            if (plotType == '2D'){
              for (var p = 0; p < colum_val.length; p++) {
                trace[colum_val[p]][r] = parseFloat(datas[i++]);
              }
              i--;
              r++;
            } else if (plotType == '3D') {
              trace['a'] = new Array();
              trace['b'] = new Array();

              var rr=0;

              for (var k = 0; k < zone.j; k++) {
                //console.log(k);
                for (var q = 0; q < zone.i; q++) {
                  for (var p = 0; p < colum_val.length; p++) {
                    if ((k==0) && (q==0) ){            // 객체 원소 생성
                      trace[colum_val[p]] = new Array();
                    }
                    if(p==0){
                      trace.a[rr] = q;
                    } else if(p==1) {
                      trace.b[rr] = k;
                    }
                    trace[colum_val[p]][rr] = parseFloat(datas[i++]);
                  }
                  rr++;
                }
              }
              trace.x_langth = zone.i;
              trace.y_langth = zone.j;

            } else {
              return -1;
            }
          }
        }
        trace.zone = zone;
        trace.varlist = colum_val;
        trace.plotType = plotType;

//        console.log(trace);

        if (trace.plotType.match('2D')) {
          var x_flag = true, y_flag = true;
          var tab_index = class_path.slice(-1);

          createSubTab(tab_index, trace.plotType);

          for (var kk = 0; kk < colum_val.length; kk++) {
            $('#sub_tap_'+ tab_index + ' select')
              .append($('<option />',{'value' : kk}).html(colum_val[kk]));
          };

          //init select_option x,y
          select_option(tab_index, 'x_axis', 0);
          select_option(tab_index, 'y_axis', 1);

          var trace1  = {
            type: 'scatter',
            x: trace[colum_val[0]],
            y: trace[colum_val[1]]
          };
          var data2 = [trace1];

          var h = $( window ).height();
          var w = $( window ).width();

          //      console.log(h);
          //      console.log(w);
          var layout = {
            width: w - plotMarginWidth,
            height: h - plotMarginHeight - $('.nav-tabs').height(),
            margin: {
              t: 20
            }
          };

          var gd3 = d3.select('#'+class_path).append('div');

          gd = gd3.node();


        } else if (trace.plotType.match('3D')) {
          var x_flag = true, y_flag = true;
          var tab_index = class_path.slice(-1);

          createSubTab(tab_index, trace.plotType);

          for (var kk = 0; kk < colum_val.length; kk++) {
            $('#sub_tap_'+ tab_index + ' select')
              .append($('<option />',{'value' : kk}).html(colum_val[kk]));

          };

          //init select_option x,y
          select_option(tab_index, 'x_axis', 0);
          select_option(tab_index, 'y_axis', 1);
          select_option(tab_index, 'z_axis', 4);


          var trace1  = {
            type: 'carpet',
            a: trace.a,
            b: trace.b,
            x: trace[colum_val[0]],
            y: trace[colum_val[1]],
            aaxis:{
              showticklabels: "none"
            },
            baxis:{

              showticklabels: "none"
            }
          }
          var trace2 = {

            autocolorscale: true,
            zmax: 1,
            name: "Pressure",
            colorscale: "Viridis",
            zmin: -8,
            line: {
              smoothing: 0
            },
            a: trace.a,
            b: trace.b,
            z: trace[colum_val[4]],
            type: "contourcarpet",
            autocontour: false,
            zauto: false

          }
          var data2 = [trace2, trace1];

          var h = $( window ).height();
          var w = $( window ).width();

          //      console.log(h);
          //      console.log(w);
          var layout = {
            width: w - plotMarginWidth,
            height: h - plotMarginHeight - $('.nav-tabs').height(),
            margin: {
              t: 20
            }
          };
          var gd3 = d3.select('#'+class_path).append('div');

          gd = gd3.node();

        } else {
          return -1;
        }

        trace.clickCount = plotDataCount;
        trace.divClass = gd;
        trace.tapID = class_path;
        trace.tabIDNum = class_path.slice(-1);
        trace.plotData = data2;
        trace.plotLayout = layout;

        Plotly.plot(trace.divClass, trace.plotData, trace.plotLayout);
        plotDataArray[plotDataCount++] = trace;

      }
    }

		//readFileList[i].dataload = dataload;

    getCFDData = function (inputDatalist){

			var data = {}
			data[namespace+'command'] = "READ_FILE";
			data[namespace+'pathType'] = "file";
			data[namespace+'repositoryType'] = "USER_JOBS";
			data[namespace+'parentPath'] = jobParentPath;
			data[namespace+'fileName'] = inputDatalist.text;
			data[namespace+'relative'] = true;

			console.log(inputDatalist);
      $.ajax({
				type: 'POST',
				url: serveResourceURL,
				data  : data,
				dataType : 'text',
        success: function (getData){
//					console.log("ajax Success!!");
//					console.log(getData);
          readCFDData(getData, inputDatalist.tabContentID);
        },error:function(getData,e){
					console.log('getCFDData ajax Error-->'+e);
				}
      });
    }

  </script>
</body>
</html>
