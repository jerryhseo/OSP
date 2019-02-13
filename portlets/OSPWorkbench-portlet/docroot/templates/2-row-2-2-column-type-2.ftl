<#if isStepWorkbench == true>
<div class="col-md-12 col" style="height: 100%;width: 50%;" id="col-${namespace}${layoutKey}column-3">
	<div class="row" style="height:50%"  id="row-${namespace}${layoutKey}column-3">
		<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-3" set-width="false" set-width-id="col-${namespace}${layoutKey}column-3">
			
		</div>
	</div>
	<div class="devider horizontal" id="${namespace}${layoutKey}devider-1" data-equal-id="row-${namespace}${layoutKey}column-3" data-remainder-id="row-${namespace}${layoutKey}column-4"></div>
	<div class="row" style="height:50%" id="row-${namespace}${layoutKey}column-4">
		<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-4"  set-width="false">
		
		</div>
	</div>
</div>
<div class="devider vertical" id="${namespace}${layoutKey}devider-2" data-equal-id="col-${namespace}${layoutKey}column-3" data-remainder-id="col-${namespace}${layoutKey}column-5"></div>
<div class="col-md-12 col" style="height: 100%;width: 50%;" id="col-${namespace}${layoutKey}column-5">
	<div class="row" style="height:50%" id="row-${namespace}${layoutKey}column-5">
		<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-5" set-width="false" set-width-id="col-${namespace}${layoutKey}column-5">
		
		</div>
	</div>
	<div class="devider horizontal" id="${namespace}${layoutKey}devider-3" data-equal-id="row-${namespace}${layoutKey}column-5" data-remainder-id="row-${namespace}${layoutKey}column-6"></div>
	<div class="row" style="height:50%" id="row-${namespace}${layoutKey}column-6">
		<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-6"  set-width="false">
		
		</div>
	</div>
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
				<div class="container-fluid" style="height: 100%;">
					<div class="row" style="height:100%">
						<div class="col-md-12 col" style="height: 100%;width: 50%;" id="col-${namespace}${layoutKey}column-3">
							<div class="row" style="height:50%"  id="row-${namespace}${layoutKey}column-3">
								<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-3" set-width="false" set-width-id="col-${namespace}${layoutKey}column-3">
									
								</div>
							</div>
							<div class="devider horizontal" id="${namespace}${layoutKey}devider-1" data-equal-id="row-${namespace}${layoutKey}column-3" data-remainder-id="row-${namespace}${layoutKey}column-4"></div>
							<div class="row" style="height:50%" id="row-${namespace}${layoutKey}column-4">
								<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-4"  set-width="false">
								
								</div>
							</div>
						</div>
						<div class="devider vertical" id="${namespace}${layoutKey}devider-2" data-equal-id="col-${namespace}${layoutKey}column-3" data-remainder-id="col-${namespace}${layoutKey}column-5"></div>
						<div class="col-md-12 col" style="height: 100%;width: 50%;" id="col-${namespace}${layoutKey}column-5">
							<div class="row" style="height:50%" id="row-${namespace}${layoutKey}column-5">
								<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-5" set-width="false" set-width-id="col-${namespace}${layoutKey}column-5">
								
								</div>
							</div>
							<div class="devider horizontal" id="${namespace}${layoutKey}devider-3" data-equal-id="row-${namespace}${layoutKey}column-5" data-remainder-id="row-${namespace}${layoutKey}column-6"></div>
							<div class="row" style="height:50%" id="row-${namespace}${layoutKey}column-6">
								<div class="col-md-12 col sub-col" style="height: 100%;width: 100%;" id="${namespace}${layoutKey}column-6"  set-width="false">
								
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</div>
</#if>