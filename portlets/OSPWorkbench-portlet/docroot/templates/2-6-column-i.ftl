<div id="${namespace}outerLayout"  style="height:100%;">
	<div class="ui-layout-west" style="margin:0; padding:0;">
		<div class="ui-layout-content"  style="margin:0; padding:0;">
			<div class="ui-layout-north" style="margin:0; padding:0;" id="${namespace}column-1">
			</div>
			<div class="ui-layout-south" style="margin:0; padding:0;"  id="${namespace}column-3">
			</div>
			<div class="ui-layout-center" style="margin:0; padding:0;" id="${namespace}column-2">
			</div>
		</div>
	</div>
	<div class="ui-layout-center" style="margin:0; padding:0;">
		<div class="ui-layout-content" style="margin:0; padding:0;">
			<div class="ui-layout-north" style="margin:0; padding:0;" id="${namespace}column-4">
			</div>
			<div class="ui-layout-center" style="margin:0; padding:0;">
				<div class="ui-layout-content" style="margin:0; padding:0;">
					<div class="ui-layout-west" style="margin:0; padding:0;" id="${namespace}column-5">
					</div>
					<div class="ui-layout-center" style="margin:0; padding:0;" id="${namespace}column-6">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$('#${namespace}outerLayout').layout({
	defaults: {
		spacing_closed:14
	},
	west: {
		size: .20,
		spacing_closed: 8,
		togglerLength_closed: "100%",
		children:{
			north:{
				size: .30,
				spacing_open: 0
			},
			center:{
				onresize_end: function(){
					console.log( 'pane resized: '+$(this).height());
				}
			},
			south:{
				size: .40,
				spacing_open: 0
			}
		}
	},
	center: {
		spacing_closed: 8,
		togglerLength_closed:"100%",
		children:{
			north:{
				size: .10,
				spacing_open: 6,
				spacing_closed: 10
			},
			center:{
				onresize_end: function(){
					console.log( 'pane resized: '+$(this).height());
				},
				children:{
					west:{
						size: .40,
						spacing_open: 6,
						spacing_closed: 10
					},
					center:{
						onresize_end: function(){
							console.log( 'pane resized: '+$(this).height());
						}
					}
				}
			}
		}
	}
});

</script>
