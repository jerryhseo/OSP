<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>

<div class="panel panel-primary" id="port-remote" style="left: 1300px;top: 0px">
	<div class="panel-heading" id="mydivheader">Port Selector</div>
	<div class="panel-body">
		<a data-toggle="collapse" data-parent="#accordion" href="#collapse_1">
			입력
		</a>
		<div id="collapse_1" class="collapse">
			<ul class="list-group">
				<li class="list-group-item list-group-item-default">
					-inp1
				</li>
				<li class="list-group-item list-group-item-default">
					-inp2
				</li>
				<li class="list-group-item list-group-item-default">
					-inp3
				</li>
			</ul>
		</div>
		<a data-toggle="collapse" data-parent="#accordion" href="#collapse_2">
			로그
		</a>
		<div id="collapse_2" class="collapse">
			<ul class="list-group">
				<li class="list-group-item list-group-item-default">
					-log1
				</li>
				<li class="list-group-item list-group-item-default">
					-log2
				</li>
				<li class="list-group-item list-group-item-default">
					-log3
				</li>
			</ul>
		</div>
		<a data-toggle="collapse" data-parent="#accordion" href="#collapse_3">
			출력
		</a>
		<div id="collapse_3" class=" collapse">
			<ul class="list-group">
				<li class="list-group-item list-group-item-default">
					-out1
				</li>
				<li class="list-group-item list-group-item-default">
					-out2
				</li>
				<li class="list-group-item list-group-item-default">
					-out3
				</li>
			</ul>
		</div>
	</div>
	<div class="panel-footer">
		프로비넌스 엔진 <liferay-ui:icon-help message="edison-science-appstore-toolkit-descriptive-message"/>
		<input type="checkbox" data-toggle="toggle" data-onstyle="success" data-offstyle="danger" data-on="Enabled" data-off="Disabled">
		<button type="submit" class="btn btn-primary btn-flat ">제출</button>
	</div>
</div>