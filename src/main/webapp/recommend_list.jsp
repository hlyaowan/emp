<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<script type="text/javascript">

function dialogAjax(json){
	$.pdialog.reloadDialog("recommendList");
}
function closeTriggerList(){
	$.pdialog.close("recommendList");
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
				<th width="80">方法名</th>
				<th width="80">内容类型</th>
				<th width="80">推荐类型</th>
				<th width="80">时间维度</th>
				<th width="80">起始记录</th>
				<th width="80">结束记录</th>
				<th width="80">分类编号</th>
				<th width="80">渠道类型</th>
				<th>建立时间</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="recommendList" id="recommendinfo">
			<tr target="sid_user" rel="${recommendinfo.id }">
				<td align="middle">${recommendinfo.id }</td>
				<td align="middle">${recommendinfo.methodName }</td>
				<td align="middle">${recommendinfo.contentType}</td>
				<td align="middle">${recommendinfo.recommendType}</td>
				<td align="middle">${recommendinfo.timeType }</td>
				<td align="middle">${recommendinfo.start }</td>
				<td align="middle">${recommendinfo.count }</td>
				<td align="middle">${recommendinfo.catalogId}</td>
				<td align="middle">${recommendinfo.channelType}</td>
				<td><s:date name="#recommendinfo.createTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
				<td><s:date name="#recommendinfo.modifyTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</div>
