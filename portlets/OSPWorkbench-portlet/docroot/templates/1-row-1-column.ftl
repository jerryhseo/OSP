<#if isStepWorkbench == true>
<div class="col-md-12 col" id="${namespace}${layoutKey}column-3" style="width: 100%;height: 100%;">
</div>
<#else>
<div class="hold-transition skin-black-light sidebar-mini" id="body-div">
	<header class="main-header" id="${namespace}${layoutKey}column-2">
		
	</header>
	
	<div class="wrapper">
		<aside class="main-sidebar">
			<section class="sidebar" id="${namespace}${layoutKey}column-1" section-type="system">
				
				
				
				
			</section>
		</aside>
		<div class="content-wrapper">
			<div class="menu-panel">
				<div class="row" id="${namespace}menu-panel-box"></div>
			</div>
			<section class="content text-center" id="no-job-layout-area">
				<span>
				</span>
			</section>
			
			<section class="content" id="workbench-layout-area" style="display:none;">
				<div class="container-fluid">
					<div class="row" style="height: 100%;">
						<div class="col-md-12 col" id="${namespace}${layoutKey}column-3" style="width: 100%;height: 100%;">
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</div>
</#if>

