var Designer = (function(namespace, $) {
    'use strict';
    var panelData = {
        "apps": {
            "col": 4/* ,
            "body": "tpl-menu-panel-apps" */
        }, "new": {
            "col": 6,
            "body": "tpl-menu-panel-new",
            "form":{
                "submitname": "Create"    
            },
            "func": {
                "create" : newDesigner
            }
        }, "open": {
            "col": 10, 
            "body": "tpl-menu-panel-load",
            "footer": "tpl-menu-panel-pagination"
        }, "import": {
            "col": 10, 
            "body": "tpl-menu-panel-load",
            "footer": "tpl-menu-panel-pagination"
        }, "save-as": {
            "col": 6,
            "body": "tpl-menu-panel-new",
            "form":{
                "submitname": "Save"
            }
        }, "setting": {
            "col": 6,
            "body": "tpl-menu-panel-setting"
        }
      };
    var jqPortletBoundaryId = "#p_p_id" + namespace;
    
    $(jqPortletBoundaryId + " .sidebar-btn").click(function(e) {
        e.preventDefault();
        var btnType = $(this).attr("data-btn-type");
        var templateData = panelData[btnType];
        
        if(templateData){
            activateLi(this);
            templateData.boxtitle = $(this).text();
            if (btnType === "apps") {
                if(!$("#" + namespace + "menu-panel-box-app").hasClass("loaded")){
                }
                $("#" + namespace + "menu-panel-box-app").show();
                $("#" + namespace + "menu-panel-box").hide();
            }else{
                $("#" + namespace + "menu-panel-box-app").hide();
                $("#" + namespace + "menu-panel-box").show();
                $("#" + namespace + "menu-panel-box").empty().mustache('tpl-menu-panel-box', templateData);
                $("#" + namespace + "menu-panel-box .box-body").mustache(templateData.body, templateData);
                if(templateData.footer){
                    $("#" + namespace + "menu-panel-box .box").append($.Mustache.render(templateData.footer, templateData));
                }
            }
            $(".menu-panel .menu-panel-close").click(function(e){
                e.preventDefault();
                closePanel();
            });
            $("#" + namespace + "menu-panel-box .data-binded").change(function(e){
                var thisValue = $(this).val();
                var thisName = $(this).attr("name");
                templateData.form[thisName] = thisValue;
            });
            $("#" + namespace + "menu-panel-box .func").each(function(_){
                var thisName = $(this).attr("name");
                if(templateData.func && templateData.func[thisName]){
                  $(this).click(function(e){
                      templateData.func[thisName](btnType, this, e);
                  });
                }
            });
            $(".menu-panel").show('slide', {direction:'left'}, 500);
        }
    });

    function newDesigner(panelDataType, that, event){
      if(isValidate()){
          setTitle(panelData[panelDataType].form.title);
          panelData.setting.form = $.extend({}, panelData[panelDataType].form);
          panelData[panelDataType].form.title = "";
          panelData[panelDataType].form.description = "";
          closePanel();
      }
    }

    function setTitle(titleText){
      $("#" + namespace + "workflow-title").text(titleText);
    }

    function isValidate(){
      $("#" + namespace + "menu-panel-box form").validator('validate');
      return $("#" + namespace + "menu-panel-box form").find(".has-error").length === 0;
    }
    
    function closePanel(){
        $(".menu-panel").hide('slide', {direction:'left'}, 500);
        $(jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
    }

    function activateLi(jqLink){
        $(jqPortletBoundaryId + " .sidebar > .sidebar-menu > li.active").removeClass("active");
        $(jqLink).parent("li").addClass("active");
    }
});