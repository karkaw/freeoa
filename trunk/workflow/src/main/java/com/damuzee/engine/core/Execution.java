/* Copyright 2013-2015 www.WFflow.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.damuzee.engine.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.damuzee.engine.WFEngine;
import com.damuzee.engine.WFException;
import com.damuzee.engine.domain.Flow;
import com.damuzee.engine.domain.Order;
import com.damuzee.engine.domain.Task;
import com.damuzee.engine.model.FlowModel;

/**
 * 流程执行过程中所传递的执行对象，其中包含流程定义、流程模型、流程实例对象、执行参数、返回的任务列表
 * @author yuqs
 * @since 1.0
 */
public class Execution implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3730741790729624400L;
	/**
	 * WFEngine holder
	 */
	private WFEngine engine;
	/**
	 * 流程定义对象
	 */
	private Flow flow;
	/**
	 * 流程实例对象
	 */
	private Order order;
	/**
	 * 父流程实例
	 */
	private Order parentOrder;
	/**
	 * 父流程实例节点名称
	 */
	private String parentNodeName;
	/**
	 * 子流程实例节点名称
	 */
	private String childOrderId;
	/**
	 * 执行参数
	 */
	private Map<String, Object> args;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 任务
	 */
	private Task task;
	/**
	 * 返回的任务列表
	 */
	private List<Map> tasks = new ArrayList<Map>();
	/**
	 * 是否已合并
	 * 针对join节点的处理
	 */
	private boolean isMerged = false;
	
	/**
	 * 用于产生子流程执行对象使用
	 * @param execution
	 * @param flow
	 * @param parentNodeName
	 */
	Execution(Execution execution, Flow flow, String parentNodeName) {
		if(execution == null || flow == null || parentNodeName == null) {
			throw new WFException("构造Execution对象失败，请检查execution、process、parentNodeName是否为空");
		}
		this.engine = execution.getEngine();
		this.flow = flow;
		this.args = execution.getArgs();
		this.parentOrder = execution.getOrder();
		this.parentNodeName = parentNodeName;
		this.operator = execution.getOperator();
	}
	
	/**
	 * 构造函数，接收流程定义、流程实例对象、执行参数
	 * @param flow
	 * @param order
	 * @param args
	 */
	public Execution(WFEngine engine, Flow flow, Order order, Map<String, Object> args) {
		if(flow == null || order == null) {
			throw new WFException("构造Execution对象失败，请检查process、order是否为空");
		}
		this.engine = engine;
		this.flow = flow;
		this.order = order;
		this.args = args;
	}
	
	/**
	 * 根据当前执行对象execution、子流程定义process、当前节点名称产生子流程的执行对象
	 * @param execution
	 * @param flow
	 * @param parentNodeName
	 * @return
	 */
	public Execution createSubExecution(Execution execution, Flow flow, String parentNodeName) {
		return new Execution(execution, flow, parentNodeName);
	}
	
	/**
	 * 获取流程定义对象
	 * @return
	 */
	public Flow getFlow() {
		return flow;
	}
	
	/**
	 * 获取流程模型对象
	 * @return
	 */
	public FlowModel getModel() {
		return flow.getModel();
	}
	
	/**
	 * 获取流程实例对象
	 * @return
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * 获取执行参数
	 * @return
	 */
	public Map<String, Object> getArgs() {
		return args;
	}
	
	/**
	 * 返回任务结果集
	 * @return
	 */
	public List<Map> getTasks() {
		return tasks;
	}
	
	/**
	 * 添加任务集合
	 * @param tasks
	 */
	public void addTasks(List<Map> tasks) {
		this.tasks.addAll(tasks);
	}
	
	/**
	 * 添加任务
	 * @param task
	 */
	public void addTask(Map task) {
		this.tasks.add(task);
	}

	/**
	 * 返回当前操作人ID
	 * @return
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置当前操作人ID
	 * @param operator
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 返回任务
	 * @return
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * 设置任务
	 * @param task
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	/**
	 * 判断是否已经成功合并
	 * @return
	 */
	public boolean isMerged() {
		return isMerged;
	}

	/**
	 * 设置是否为已合并
	 * @param isMerged
	 */
	public void setMerged(boolean isMerged) {
		this.isMerged = isMerged;
	}

	/**
	 * 获取引擎
	 * @return
	 */
	public WFEngine getEngine() {
		return engine;
	}

	public Order getParentOrder() {
		return parentOrder;
	}

	public String getParentNodeName() {
		return parentNodeName;
	}

	public String getChildOrderId() {
		return childOrderId;
	}

	public void setChildOrderId(String childOrderId) {
		this.childOrderId = childOrderId;
	}
}
