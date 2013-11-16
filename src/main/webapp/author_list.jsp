<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script type="text/javascript">

function dialogAjax(json){
	$.pdialog.reloadDialog("authorList");
}
function closeTriggerList(){
	$.pdialog.close("authorList");
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
				<th width="160">作者编号</th>
				<th width="130">起始位置</th>
				<th width="130">结束位置</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="authorList" id="authorinfo">
			<tr target="sid_user" rel="${authorinfo.id }">
				<td align="middle">${authorinfo.id }</td>
				<td align="middle">${authorinfo.authorId}</td>
				<td align="middle">${authorinfo.start }</td>
				<td align="middle">${authorinfo.count }</td>
				<td><s:date name="#authorinfo.createTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>
