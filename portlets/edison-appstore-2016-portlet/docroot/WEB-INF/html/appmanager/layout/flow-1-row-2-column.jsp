<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel-body sortableLayout gridLayoutArea">
	<div class="row" style="display: none;">
		<div class="col-md-4 col">
			<ul class="sortable-list ui-sortable list-group" id="column-4" data-init-area="input">
			</ul>
		</div>
		<div class="col-md-8 col">
			<ul class="sortable-list ui-sortable list-group" id="column-5" data-init-area="log">
			</ul>
		</div>
	</div>
	<div class="row" style="height: 100%;">
		<div class="col-md-12 col" id="col-column-6" style="width: 50%;height: 100%;">
			<ul class="sortable-list ui-sortable list-group" id="column-6" >
			</ul>
		</div>
		<div class="devider vertical" id="devider-1" data-equal-id="col-column-6" data-remainder-id="col-column-7" set-equal-ids="column-6" set-remainder-ids="column-7"></div>
		<div class="col-md-12 col" id="col-column-7" style="width: 50%;height: 100%;">
			<ul class="sortable-list ui-sortable list-group" id="column-7" data-init-area="output">
			</ul>
		</div>
	</div>
</div>