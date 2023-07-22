package com.ossbar.modules.evgl.activity.domain;

import com.alibaba.fastjson.JSONObject;
import com.ossbar.core.baseclass.annotation.validator.MaxLength;
import com.ossbar.core.baseclass.annotation.validator.NotNull;
import com.ossbar.core.baseclass.domain.BaseDomain;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public class TevglActivityTaskGroup extends BaseDomain<TevglActivityTaskGroup>{
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "TevglActivityTaskGroup";
	public static final String ALIAS_GROUP_ID = "小组id";
	public static final String ALIAS_CT_ID = "关联课堂id";
	public static final String ALIAS_GROUP_NAME = "小组名称";
	public static final String ALIAS_GROUP_NUM = "小组人数";
	public static final String ALIAS_CONTENT = "作答内容";
	public static final String ALIAS_FINAL_SCORE = "最终得分";
	public static final String ALIAS_STATE = "状态(Y有效N无效)";
	

    /**
     * 小组id       db_column: group_id 
     */	
 	@NotNull(msg="小组id不能为空")
 	@MaxLength(value=32, msg="字段[小组id]超出最大长度[32]")
	private java.lang.String groupId;
    /**
     * 关联课堂id       db_column: ct_id 
     */	
 	@NotNull(msg="关联课堂id不能为空")
 	@MaxLength(value=32, msg="字段[关联课堂id]超出最大长度[32]")
	private java.lang.String ctId;
    /**
     * 小组名称       db_column: group_name 
     */	
 	@NotNull(msg="小组名称不能为空")
 	@MaxLength(value=50, msg="字段[小组名称]超出最大长度[50]")
	private java.lang.String groupName;
    /**
     * 小组人数       db_column: group_num 
     */	
 	@NotNull(msg="小组人数不能为空")
 	@MaxLength(value=10, msg="字段[小组人数]超出最大长度[10]")
	private java.lang.Integer groupNum;
    /**
     * 作答内容       db_column: content 
     */	
 	@NotNull(msg="作答内容不能为空")
 	@MaxLength(value=65535, msg="字段[作答内容]超出最大长度[65535]")
	private java.lang.String content;
    /**
     * 最终得分       db_column: final_score 
     */	
 	@NotNull(msg="最终得分不能为空")
 	@MaxLength(value=5, msg="字段[最终得分]超出最大长度[5]")
	private java.math.BigDecimal finalScore;
    /**
     * 状态(Y有效N无效)       db_column: state 
     */	
 	@NotNull(msg="状态(Y有效N无效)不能为空")
 	@MaxLength(value=2, msg="字段[状态(Y有效N无效)]超出最大长度[2]")
	private java.lang.String state;
	//columns END

	public TevglActivityTaskGroup(){
	}

	public TevglActivityTaskGroup(
		java.lang.String groupId
	){
		this.groupId = groupId;
	}

	public void setGroupId(java.lang.String value) {
		this.groupId = value;
	}
	public java.lang.String getGroupId() {
		return this.groupId;
	}
	public void setCtId(java.lang.String value) {
		this.ctId = value;
	}
	public java.lang.String getCtId() {
		return this.ctId;
	}
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	public void setGroupNum(java.lang.Integer value) {
		this.groupNum = value;
	}
	public java.lang.Integer getGroupNum() {
		return this.groupNum;
	}
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	public void setFinalScore(java.math.BigDecimal value) {
		this.finalScore = value;
	}
	public java.math.BigDecimal getFinalScore() {
		return this.finalScore;
	}
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

