<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script type="text/javascript">

function dialogAjax(json){
	$.pdialog.reloadDialog("visitList");
}
function closeTriggerList(){
	$.pdialog.close("visitList");
}
</script>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="trigger/addShow" target="navTab" rel="addTrigger"><span>添加</span></a></li>
			<li><a class="delete"  href="<%=request.getContextPath()%>/trigger/delete" callback="dialogAjax" target="ajaxTodo" title="确定要删除吗?" fresh="true"><span>删除</span></a></li>
		</ul>
	</div>
	<input type="hidden" name="jobId" value=""/>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">编号</th>
				<th width="160">方法名</th>
				<th width="160">访问次数</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="visitList" id="visit">
			<tr target="sid_user" rel="${visit.id }">
				<td align="middle">${visit.id }</td>
				<td align="middle">${visit.mothodName }</td>
				<td align="middle">${visit.number}</td>
				<td><s:date name="#visit.modifyTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>
