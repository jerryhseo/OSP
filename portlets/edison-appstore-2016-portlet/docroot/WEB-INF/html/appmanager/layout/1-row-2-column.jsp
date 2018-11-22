<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Size가 변경될 Layout은 row 일 경우 row-columnId,col 일 경우 col-columnId 로 규칙 정의 -->
<div class="panel-body sortableLayout gridLayoutArea">
	<div class="row" style="height: 100%;">
		<div class="col-md-12 col" id="col-column-3" style="width: 50%;height: 100%;">
			<ul class="sortable-list ui-sortable list-group" id="column-3" data-init-area="input">
			</ul>
		</div>
		<div class="devider vertical" id="devider-1" data-equal-id="col-column-3" data-remainder-id="col-column-4" set-equal-ids="column-3" set-remainder-ids="column-4"></div>
		<div class="col-md-12 col" id="col-column-4" style="width: 50%;height: 100%;">
			<ul class="sortable-list ui-sortable list-group" id="column-4" data-init-area="log|output">
			</ul>
		</div>
	</div>
</div>