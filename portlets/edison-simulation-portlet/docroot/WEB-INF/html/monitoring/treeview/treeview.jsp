<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp"%>
<liferay-portlet:resourceURL var="getSimulationsUrl" id="getSimulations" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="getJobsUrl" id="getJobs" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="jobInfoUrl" id="jobInfo" copyCurrentRenderParameters="false"/>
<liferay-portlet:resourceURL var="jobListUrl" id="jobList" copyCurrentRenderParameters="false"/>
<style>
.mflefttree{padding-top: 15px; width: 300px;}
.rightcontent{width: 640px;}
.simulation-tree{overflow-y: auto; height: 570px;}
.tree-view-monitoring-portlet .jstree-clicked {color: white !important;}
.tree-view-monitoring-portlet .searchbox{
    margin-left: 8px;
    padding-left: 8px;
    width: 232px;
    height: 30px;
    background: url(${contextPath}/images/monitoring/search_bg02.png) left bottom no-repeat;
    float: none;
}
.tree-view-monitoring-portlet .searchbox input {width: 173px;}
.tree-view-monitoring-portlet .simulation-tree > ul {margin-left: 10px; margin-top: 5px;}
.tree-view-monitoring-portlet .simulation-tree > ul > li > a{text-overflow: ellipsis; white-space: nowrap; word-wrap: normal; overflow: hidden; width: 85%;}
</style>
<script type="text/javascript" src="${contextPath}/js/jstree.min.js"></script>

<div class="h10"></div>
<div class="myfilebox">
    <div class="mflefttree">
        <div class="searchbox">
            <input id="<portlet:namespace/>simulation-search-keyword" name="simulation-search-keyword" type="text" value="" autocomplete="off" placeholder="Search"/>
            <input type="button" id="<portlet:namespace/>simulation-search"/>
          </div>
        <div id="<portlet:namespace/>simulation-tree" class="simulation-tree"></div>
    </div>
    <div class="rightcontent" id="<portlet:namespace/>job-info-wrapper">
        <div style="width: 100%; height: 550px; text-align: center; ">
            <p style="top: 40%; position: relative; font-size: 2.5em !important;"><i class="icon-file"></i> Select a simulation from the tree.</p>
        </div>
    </div>
</div>

<script type="text/javascript">
    var SIMULATION_TYPE = "simulation";
    var JOB_TYPE = "job";
    var <portlet:namespace/>currentPage = 1;
    AUI().ready(function(){
        var currentSearchKeyword;        
        <portlet:namespace/>initJstree();
        $("#<portlet:namespace/>simulation-search").click(function(e){
            e.preventDefault();
            currentSearchKeyword = $("#<portlet:namespace/>simulation-search-keyword").val();
            <portlet:namespace/>refreshSimulations(currentSearchKeyword);
        });
        
        $("#<portlet:namespace/>simulation-search-keyword").keydown(function(key, e){
            if(key.keyCode == 13){
                currentSearchKeyword = $("#<portlet:namespace/>simulation-search-keyword").val();                
                <portlet:namespace/>refreshSimulations(currentSearchKeyword);
            }
        });
        
        $("#<portlet:namespace/>simulation-tree").scroll(function(e){
            var elem = $("#<portlet:namespace/>simulation-tree");
            if(elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight()){
                <portlet:namespace/>addSimulations(currentSearchKeyword);
            }
        });
        
    });

    function <portlet:namespace/>initJstree(){
        $("#<portlet:namespace/>simulation-tree").jstree({
            "core" : {
                "check_callback" : true,
                "data" : {
                    "url" : "${getSimulationsUrl}",
                    "data" : function (node) {
                        return { "id" : node.id };
                    } 
                },
                "themes" : {
                    "name" : "proton",
                    "responsive" : true
                }
            },
            "types" : {
                "simulation" : {
                },
                "job" : {
                    "icon": "icon-file"
                }
            },
            "progressive_render" : true,
            "plugins" : [
                "types"
            ]
        }).bind("loaded.jstree", function(event, data){
        }).bind("load_node.jstree", function(event, data){
        }).bind("select_node.jstree", function(event, data){
            var nodeId = data.node.id;
            var node = data.node;
            if(node.type === SIMULATION_TYPE){
              console.log(node);
              <portlet:namespace/>displayJobList(node.id);
              if(!$("#" + nodeId).hasClass("jstree-open")){
                  <portlet:namespace/>openJstreeNode(nodeId, node);
              }else{
                $("#<portlet:namespace/>simulation-tree").jstree("close_node", node);
              }
            }else if(node.type === JOB_TYPE){
                <portlet:namespace/>displayJob(nodeId);
            }
        }).bind("hover_node.jstree", function(event, data){
        });

    }
    
    function <portlet:namespace/>displayJob(jobUuid, scienceAppId){
        $("#<portlet:namespace/>job-info-wrapper").load("${jobInfoUrl}", {
            <portlet:namespace/>jobUuid : jobUuid,
            <portlet:namespace/>scienceAppId : scienceAppId
        }, function(){
        });
    }
    
    function <portlet:namespace/>displayJobList(simulationUuid){
        $("#<portlet:namespace/>job-info-wrapper").load("${jobListUrl}", {
            <portlet:namespace/>simulationUuid : simulationUuid
        }, function(){
        });
    }

    function <portlet:namespace/>openJstreeNode(nodeId, node){
        if($("#" + nodeId).hasClass("is-loaded")){
            $("#<portlet:namespace/>simulation-tree").jstree("open_node", node);
        }else{
            <portlet:namespace/>addJobs(nodeId);
        }
    }

    function <portlet:namespace/>addJobs(simulationUuid){
        var callback = function(jobs, parentSimulationUuid){
            if(jobs){
                $.each(jobs, function(i){
                    var job = this;
                    <portlet:namespace/>createJobNodes(job, parentSimulationUuid);
                });
                var parentNode = $('#<portlet:namespace/>simulation-tree').jstree(true).get_node(parentSimulationUuid);
                $("#<portlet:namespace/>simulation-tree").jstree("open_node", parentNode);
                $("#" + parentSimulationUuid).addClass("is-loaded");
            }
        };
        <portlet:namespace/>loadJobs(simulationUuid, callback);
    }

    function <portlet:namespace/>createJobNodes(job, parentSimulationUuid){
        if(!$('#<portlet:namespace/>simulation-tree').jstree(true).get_node(job.id)){
            $('#<portlet:namespace/>simulation-tree').jstree(true).create_node(parentSimulationUuid, job, 'last');
        }
    }

    function <portlet:namespace/>loadJobs(simulationUuid, callback){
        $.ajax({
            url : '${getJobsUrl}',
            type : 'POST',
            dataType : 'json',
            data : {
                <portlet:namespace/>simulationUuid : simulationUuid
            },
            success : function(jobs){
                console.log(jobs);
                callback(jobs, simulationUuid);
            },
            error : function(err){
                if(console){
                    console.log('[ERROR] AJAX FAILED during loadJobs', err);
                }
            }
        });
    }

    function <portlet:namespace/>addSimulations(searchKeyword){
        var currentPage = <portlet:namespace/>currentPage + 1;
        var postData = {
            <portlet:namespace/>currentPage : currentPage
        };
        if(searchKeyword){
            postData["<portlet:namespace/>searchKeyword"] = searchKeyword;
        }
        $.ajax({
            url : '${getSimulationsUrl}',
            type : 'POST',
            dataType : 'json',
            data : postData,
            success : function(simulations){
                if(simulations && simulations.length > 0){
                    $.each(simulations, function(i){
                        var simulation = this;
                        <portlet:namespace/>createJobNodes(simulation, "#");
                    });
                    <portlet:namespace/>currentPage++;
                }
            },
            error : function(err){
                if(console){
                    console.log('[ERROR] AJAX FAILED during addSimulations', err);
                }
            }
        });
    }
    
    function <portlet:namespace/>refreshSimulations(searchKeyword){
        $.ajax({
            url : '${getSimulationsUrl}',
            type : 'POST',
            dataType : 'json',
            data : {
                <portlet:namespace/>searchKeyword : searchKeyword
            },
            success : function(simulations){
                <portlet:namespace/>currentPage = 1;
                $("#<portlet:namespace/>simulation-tree").jstree(true).settings.core.data = simulations;
                $("#<portlet:namespace/>simulation-tree").jstree(true).refresh();
            },
            error : function(err){
                if(console){
                    console.log('[ERROR] AJAX FAILED during loadSimulations', err);
                }
            }
        });
    }
</script>
