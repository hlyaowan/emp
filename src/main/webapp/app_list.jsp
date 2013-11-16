<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script type="text/javascript">

function dialogAjax(json){
	$.pdialog.reloadDialog("appList");
}
function closeTriggerList(){
	$.pdialog.close("appList");
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
				<th width="80">app编号</th>
				<th width="160">appId</th>
				<th width="330">accessToken</th>
				<th width="130">创建时间</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="appList" id="appinfo">
			<tr target="sid_user" rel="${appinfo.id }">
				<td align="middle">${appinfo.id }</td>
				<td align="middle">${appinfo.appId }</td>
				<td align="middle">${appinfo.accessToken }</td>
				<td><s:date name="#appinfo.createTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="#appinfo.modifyTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>
