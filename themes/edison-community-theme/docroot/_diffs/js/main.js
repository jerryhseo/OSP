AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	'event','anim','node','node-event-simulate',
	function(A) {
		A.all('.edison-use-dialog').on('click', function(event) {
			var url = event.currentTarget.get('href');
			var title = event.currentTarget.get('title');
			event.preventDefault();
			Liferay.Util.openWindow({
				dialog : {
					align : {
						points : [ 'tc', 'tc' ]
					},
					width : '95%'
				},
				title : title,
				uri : url,
				id:"edison-use-dialog"
			});
		});
		
		var triggerPos = 0;
		var dockbar_p = A.one('.portlet-dockbar');
		if(dockbar_p){
			triggerPos = 100;
		}else{
			triggerPos = 50;
		}
		var WIN = A.getWin();
		
		
		var sMenuEvent = new A.Anim(
			{
				duration: 0.5,
				easing: A.Easing.easeOutStrong 
			});
		
		var toggleClassOffClick = function(event) {
			var currentTargetNode = event.currentTarget;
			
			var smenuNode = currentTargetNode.one('.submenu');
			
			var active = false;
			if(smenuNode.getStyle('display')=='none'){
				smenuNode.setStyle('display','block');
				var toArrat = {width: 250};
				sMenuEvent.set("node",smenuNode);
				sMenuEvent.set("to",toArrat);
				sMenuEvent.run();
			}else{
				active = true;
			}
			
			smenuNode.on(
				'clickoutside',
				function(event) {
					if (!active) {
						active = true;
						return;
					}
					
					smenuNode.setStyle('display','none');
					smenuNode.setStyle('width','100px');
					smenuNode.detach('clickoutside');
				}
			);
		}
		
		A.all('.class-toggle-off-click').on('click', toggleClassOffClick);
		
		
		
		var sideMenuEvent = new A.Anim(
				{
					duration: 0.3,
					easing: 'easeBoth'
				});
		
		var sideMenuClick = function(event) {
			var sildeMenu = A.one('#nslidemenu');
			var active = false;
			if(sildeMenu.getStyle('display')=='none'){
				var dockbar = A.one('.portlet-dockbar');
				if(dockbar){
					var scrollPos = WIN.get('docScrollY');
					if (scrollPos < triggerPos) {
						sildeMenu.setStyle('top',dockbar.getStyle('height'));
					}else{
						sildeMenu.setStyle('top',0);
					}
					
					
				}
				
				sildeMenu.setStyle('display','block');
				var toArrat = {width: '325px'};
				sideMenuEvent.set("node",sildeMenu);
				sideMenuEvent.set("to",toArrat);
				sideMenuEvent.run();
				sildeMenu.detach('clickoutside');
			}else{
				sildeMenu.setStyle('display','none');
				sildeMenu.setStyle('width','10px');
				active = true;
			}
			
			sildeMenu.on(
				'clickoutside',
				function(event) {
					if (!active) {
						active = true;
						return;
					}
					
					sildeMenu.setStyle('display','none');
					sildeMenu.setStyle('width','10px');
					sildeMenu.detach('clickoutside');
				}
			);
		}
		
		A.all('.slidemenuicon').on('click', sideMenuClick);
		
		
		var displayBanner = function() {
			var lastScrollPos = 0;
			var savedScrollPos = 0;
			var banner = A.one('#banner');
			
			var scrollPos = WIN.get('docScrollY');

			if (scrollPos > lastScrollPos) {
				savedScrollPos = scrollPos;
			}
			
			lastScrollPos = scrollPos;
			if (scrollPos < (savedScrollPos - 100)) {
				savedScrollPos = scrollPos + 100;
				banner.removeClass('move-banner');
			}
			else if (scrollPos > triggerPos) {
				banner.addClass('move-banner');
			}
			else {
				banner.removeClass('move-banner');
			}
			
			var sildeMenu = A.one('#nslidemenu');
			if(sildeMenu.getStyle('display')!='none'){
				var dockbar = A.one('.portlet-dockbar');
				if(dockbar){
					if (scrollPos < triggerPos) {
						sildeMenu.setStyle('top',dockbar.getStyle('height'));
					}else{
						sildeMenu.setStyle('top',0);
					}
				}
			}
			
			
		}
		
		A.on(
			'scroll',
			function() {
				A.throttle(displayBanner());
			}
		);
	}
);

Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);