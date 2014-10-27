<#if returns??>
 <dl class="count">
        <dd>
             <span>任务：</span><input style="width: 75%" type="text"
			readonly="true" name="deftitle"
			value="${returns.title!''}" />
        </dd>
        <dd>
		<span>类型：</span><input style="width: 35%" type="text"
			readonly="true" name="objectname"
			value="${returns.defname!''}" />
	</dd>
	<dd>
		 <span ><input  id="stepNumber" class="stepNumber hide"  value="${returns.stepnumber}"></span>
	</dd>
	<dd>
		<span>创建人：</span><input style="width: 35%" type="text"
			readonly="true" name="username"
			value="${returns.lastname!''} ${returns.firstname!''}" />
	</dd>
	<dd>
		<span>日 期：</span><input style="width: 35%" type="text" 	readonly="" name="createdate"	
				value="${returns.createdate?string('yyyy-MM-dd HH:mm:ss')!''} " />
	</dd>
	<dd >
		<form id="objForm">

		</form>
	</dd>
	<dd>
		<span>审批历史：</span>
	</dd>
	<dd>
		<table cellspacing="0" cellpadding="0" width="94%"
			class="listTab0">
			<tr>
				<th width="13%">日期</th>
				<th width="13%">描述</th>
				<th width="20%">被分配人</th>
				<th width="14%">批准人</th>
				<th width="10%">操作</th>
				<th width="10%">备注</th>
				<th width="21%">修改历史</th>
			</tr>
			<#if returns["historysteps"] ??>
			<#list returns["historysteps"] as steps>
			<tr>
					<td> 
						<#if steps["approve_persons"] ??> 
							<#list steps["approve_persons"] as approves>
						 		 ${approves["approve_time"]?string("yyyy-MM-dd HH:mm:ss")!''}
						 		 <#if approves_has_next>,</#if>
						  	</#list>
						  </#if>
					</td>
					<td> 
						 		 ${steps["description"]!''}
					</td>
					<td> 
						<#if steps["stepAllotUsers"] ??> 
							<#list steps["stepAllotUsers"] as allotUsers>
						 		 ${allotUsers["lastname"]!''} ${allotUsers["firstname"]!''}
						 		 <#if allotUsers_has_next>,</#if>
						  	</#list>
						  </#if>
					</td>
					<td> 
						<#if steps["approve_persons"] ??> 
							<#list steps["approve_persons"] as approves>
								${approves["lastname"]!''} ${approves["firstname"]!''}
								<#if approves_has_next>,</#if>
						  	</#list>
						  </#if>
					</td>
					<td> 
						<#if steps["approve_persons"] ??> 
							<#list steps["approve_persons"] as approves>
							<#if (approves["action"] =='approved') && (steps["stepnumber"]=='SYS_START')> 
                                                           重新提交
							<#elseif approves["action"] =='approved'>
	                                                        同意
							<#elseif approves["action"] =='rejected'>
								拒绝
							<#elseif approves["action"] =='redirect'>
								转发
							</#if>
							<#if approves_has_next>,</#if>
						  	</#list>
						  </#if>
					</td>
					<td> 
						<#if steps["approve_persons"] ??> 
							<#list steps["approve_persons"] as approves>
								${approves["comment"]!''}
								<#if approves_has_next>,</#if>
						  	</#list>
						  </#if>
					</td>
					<td> 
						<#if steps["approve_persons"] ??> 
							<#list steps["approve_persons"] as approves>
                                                                 ${approves["operate"]!''} 								
						  	</#list>
						  </#if>
					</td>
					</tr>
			</#list>
			</#if>
		</table>
	</dd>
	<dd>
		审批意见:
		<textarea style="width: 80%" name="comment"></textarea>
         <input type="hidden" class="rejectId" value='${returns._id!''}'/>  
         <input type="hidden" class="rejectProjid" value='${returns.projid!''}'/>       
	</dd>
	<dd> 
		<input  type="button" onclick="approve('${returns._id!''}','approved','${returns.projid!''}')"
			class="but" value="同意" id="agree" />
			
		 <input  type="button" id="refuse"
				class="but" onclick="reject('${returns._id!''}','${returns.projid!''}')" value="拒绝" />
			 
		 <input type="button" id="intransit" onclick="showContent('${returns._id!''}','forward','${returns.objectname!''}')"
				class="but" value="转发" /> 
				
		 <input type="button" id="cancel" class="but hide" onclick="approve('${returns._id!''}','cancled','${returns.projid!''}')" value="取消" /> 		
		 		
	</dd>
</dl>
</#if>
	