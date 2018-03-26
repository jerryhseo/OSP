AUI().ready(
	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/
		'event','anim',
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
			
			
			A.on(
				'scroll',
				function() {
					A.throttle(displayBanner(), 250);
				}
			);
			var WIN = A.getWin();
			var BODY = A.getBody();
			var banner = A.one('#banner') === null ? 
			    {removeClass:function(){}, addClass:function(){} } : 
			        A.one('#banner');
			var triggerPos = 200;
			var lastScrollPos = 0;
			var savedScrollPos = 0;
			
			var displayBanner = function() {
				
				var scrollPos = WIN.get('docScrollY');

				if (scrollPos > lastScrollPos) {
					savedScrollPos = scrollPos;
				}

				lastScrollPos = scrollPos;
				
				if (scrollPos < (savedScrollPos - 100) && scrollPos>100) {
					savedScrollPos = scrollPos + 100;
					BODY.removeClass('hide-banner');
					banner.addClass('navbar-fixed-top');
				}
				else if (scrollPos > triggerPos) {
					BODY.addClass('hide-banner');
					banner.removeClass('navbar-fixed-top');
				}
				else {
					BODY.removeClass('hide-banner');
					banner.removeClass('navbar-fixed-top');
				}
			}
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