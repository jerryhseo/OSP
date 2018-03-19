<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/init.jsp" %>

<style type="text/css">
	.edison-table thead{
		background-color: #ffffff !important;
	}
	
	.blue .edison-table thead{
		background-color: #f4f9ed !important;
	}
	
	.brown .edison-table thead{
		background-color: #f7f1e4 !important;
	}
	
	.emerald .edison-table thead{
		background-color: #e9f7f5 !important;
	}
	
	.green .edison-table thead{
		background-color: #e8f6fd !important;
	}
	
	.navy .edison-table thead{
		background-color: #e9eff7 !important;
	}
	
	.purple .edison-table thead{
		background-color: #f1ecf8 !important;
	}
	
	.red .edison-table thead{
		background-color: #fef1f3 !important;
	}
	
	.yellow .edison-table thead{
		background-color: #fcfaeb !important;
	}
	
	
</style>
<script type="text/javascript">
	$(function () {
		$('.filterable .btn-filter').click(function(){
	        var $panel = $(this).parents('#<portlet:namespace/>filterTable'),
	        $filters = $panel.find('.filters .filter')
	        if ($filters.prop('disabled') == true) {
	            $filters.prop('disabled', false);
	            $filters.first().focus();
	        } else {
	        	$filters.val('').prop('disabled', true);
	        }
	    });
	});
</script>
<h3>Note</h3>
<h5>1200px 및 가운데 고정은 liferay-portlet.xml에서 css-class-wrapper 태그에 container  추가</h5>
<h3>-->Tables</h3>
<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			Default - #ffffff
		</h3>
		<div class="dropdown pull-right">
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				Paging  <span class="caret"></span>
 				</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
				<li><a href="#">10</a></li>
				<li><a href="#">50</a></li>
				<li><a href="#">100</a></li>
				<li role="separator" class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
	</div>
	<table class = "table table-bordered table-hover edison-table">
		<thead>
			<tr>
				<th>Name</th>
				<th>City</th>
				<th>Pincode</th>
			</tr>
		</thead>
			
		<tbody>
			<tr>
				<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
			<tr>
				<td class="center">Tanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
			<tr>
				<td class="center">Tanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<li><a href="#">&laquo;</a></li>
			<li class="active"><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</div>
</div>













<div class="red">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				전산열유체 - #fef1f3
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="green">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				나노물리 - #e8f6fd
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 			</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="yellow">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				계산화학 - #fcfaeb
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="blue">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				구조동역학 - #f4f9ed
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="purple">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				전산설계 - #f1ecf8
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="emerald">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				전산의학 - #e9f7f5
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="brown">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				도시환경 - #f7f1e4
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<div class="navy">
	<div class="table-responsive panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				전파위성 - #e9eff7
			</h3>
			<div class="dropdown pull-right">
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
					<li role="separator" class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>













<br/>
<br/>
<br/>
<br/>
<br/>


<div class="table-responsive panel edison-panel">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			Search
		</h3>
		
		<div class="input-group">
			<input type="text" class="form-control" placeholder="Search term...">
			<div class="input-group-btn">
				<button class="btn btn-default" type="button"><i class="icon-search"></i></button>
			 	<button class="btn btn-default">Clear</button>
				<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					Paging  <span class="caret"></span>
	 			</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
				</ul>
			</div>
		</div>
	</div>
	<table class = "table table-bordered table-hover edison-table">
		<thead>
			<tr>
				<th>Name</th>
				<th>City</th>
				<th>Pincode</th>
			</tr>
		</thead>
			
		<tbody>
			<tr>
				<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
			<tr>
				<td class="center">Tanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
			<tr>
				<td class="center">Tanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<li><a href="#">&laquo;</a></li>
			<li class="active"><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</div>
</div>
















<div class="table-responsive panel filterable edison-panel" id="<portlet:namespace/>filterTable">
	<div class="panel-heading clearfix">
		<h3 class="panel-title pull-left">
			<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
			Filter Search
		</h3>
		<div class="btn-group pull-right">
		 	<button class="btn btn-default btn-filter"><i class="icon-filter"></i>Filter</button>
			<button class="btn btn-default dropdown-toggle " type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				Paging  <span class="caret"></span>
 			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
				<li><a href="#">10</a></li>
				<li><a href="#">50</a></li>
				<li><a href="#">100</a></li>
			</ul>
		</div>
	</div>
	<table class = "table table-bordered table-hover edison-table">
		<thead>
			<tr class="filters">
				<th>
					<select class="form-control filter" disabled>
						<option value="">name</option>
						<option>Mustard</option>
						<option>Ketchup</option>
						<option>Relish</option>
					</select>
				</th>
				<th>
					<input type="text" class="form-control filter" placeholder="City" disabled>
				</th>
				<th>Pincode</th>
			</tr>
		</thead>
			
		<tbody>
			<tr>
				<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
			<tr>
				<td class="center">Tanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
			<tr>
				<td class="center">Tanmay</td>
				<td>Bangalore</td>
				<td>560001</td>
			</tr>
		</tbody>
	</table>
	<div class="text-center">
		<ul class="pagination">
			<li><a href="#">&laquo;</a></li>
			<li class="active"><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#">&raquo;</a></li>
		</ul>
	</div>
</div>


<h3>Note</h3>
<h5>780px 이하 테이블은 dropdown 사용</h5>
<div style="width: 780px;">
	<div class="panel edison-panel">
		<div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">
				<img src="${pageContext.request.contextPath}/images/title_virtual.png" width="18" height="18" class="title-img"/>
				780이하
			</h3>
			<div class="btn-group pull-right">
				<button class="btn btn-default">Clear</button>
			 	<button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					<i class="icon-search"></i>
					<span class="caret"></span>
	 			</button>
				<ul class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu3">
					<li>
						<input type="text" class="form-control" placeholder="Search term...">
					</li>
					<li>
						<button class="btn btn-primary">Search</button>
					</li>
					<li role="separator" class="divider">10</li>
					<li><a href="#">10</a></li>
					<li><a href="#">50</a></li>
					<li><a href="#">100</a></li>
				</ul>
			</div>
		</div>
		<table class = "table table-bordered table-hover edison-table">
			<thead>
				<tr>
					<th>Name</th>
					<th>City</th>
					<th>Pincode</th>
				</tr>
			</thead>
				
			<tbody>
				<tr>
					<td class="center">TanmayTanmayTanmayTanmayTanmayTanmay</td>
					<td>Bangalore</td>
					<td>560001</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>