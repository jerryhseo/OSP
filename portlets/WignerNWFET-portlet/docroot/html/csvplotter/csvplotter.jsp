<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%; overflow:hidden;">
	<head>
	<script src="<%=request.getContextPath()%>/js/jquery-1.10.2.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/c3.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/d3.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/c3.css">    
    	
	<%
//String path = request.getContextPath()+"/data/energy.csv";
%>


           <script>
           var namespace;
           var chart;

           $(window).resize = function(){
        	  // setTimeout(function () { chart.resize();	}, 3000);
        	   
        	//   alert("ffff");
        	              
           }
                                
           function setNamespace( ns ){
           	console.log( ' Set Namespace: '+ns);
           	namespace = ns;
           }
           
           function drawGraph(url, width, height ) 
           {
            	//d3.select("#rightdown") .append("div") .attr("id", "chart");
             	chart = c3.generate(
             			{ 
             			    bindto:"#chart",
             			    size: {
             					height: $('#rightdown').height(),
             					width: $('#rightdown').width()
             			    },
             			    data: { 
             						url: url, 
             						x:'z(nm)', 
             						type: 'line' 
             				},
             				axis: {
             					x: {
             						label: {
             							text: 'z(nm)',
             							position: 'outer-center'
             						},
             						tick: {
             						// format: d3.format("$,") // ADD
             						}
             					},
             					y: {
             						label: {
             							text: 'Es(eV)',
             							position: 'outer-middle'
             						},
             						tick: {
             							//format: d3.format("$,") // ADD
             						}
             					}
             				}
             			}
             	);

           }
           
           function chartResize() 
           {        	   
       		    chart.resize();   
           }
           
           $(document).ready( function(){
        	});
        </script>	
	
	</head>
	
<body style="height:100%;">
      <div id="wrapper" class="wrapper"  >
        
        <div style="width:100%;height:85%" class="rightdown" id="rightdown">
            <div class="embossed">Subband energy</div>
            <!-- 
             -->
            <div id="chart" ></div>
        </div>
    </div>  
</body>

</html>