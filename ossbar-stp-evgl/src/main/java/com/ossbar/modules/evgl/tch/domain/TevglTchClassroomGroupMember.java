package com.ossbar.modules.evgl.tch.domain;

import com.alibaba.fastjson.JSONObject;
import com.ossbar.core.baseclass.domain.BaseDomain;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public class TevglTchClassroomGroupMember extends BaseDomain<TevglTchClassroomGroupMember> {
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "TevglTchClassroomGroupMember";
	public static final String ALIAS_GM_ID = "主键小组成员ID";
	public static final String ALIAS_GP_ID = "小组ID";
	public static final String ALIAS_TRAINEE_ID = "学员ID";
	public static final String ALIAS_SORT_NUM = "排序号";
	public static final String ALIAS_STATE = "状态(Y有效N无效)";
	public static final String ALIAS_IS_LEADER = "是否为小组组长(Y是N否)";
	public static final String ALIAS_DICT_CODE = "来源字典group_member_identity：1项目经理2技术总监3开发人员4测试人员";
	

    /**
     * 主键小组成员ID       db_column: gm_id 
     */	
 	//@NotNull(msg="主键小组成员ID不能为空")
 	//@MaxLength(value=32, msg="字段[主键小组成员ID]超出最大长度[32]")
	private String gmId;
    /**
     * 小组ID       db_column: gp_id 
     */	
 	//@NotNull(msg="小组ID不能为空")
 	//@MaxLength(value=32, msg="字段[小组ID]超出最大长度[32]")
	private String gpId;
    /**
     * 学员ID       db_column: trainee_id 
     */	
 	//@NotNull(msg="学员ID不能为空")
 	//@MaxLength(value=32, msg="字段[学员ID]超出最大长度[32]")
	private String traineeId;
    /**
     * 排序号       db_column: sort_num 
     */	
 	//@NotNull(msg="排序号不能为空")
 	//@MaxLength(value=10, msg="字段[排序号]超出最大长度[10]")
	private Integer sortNum;
    /**
     * 状态(Y有效N无效)       db_column: state 
     */	
 	//@NotNull(msg="状态(Y有效N无效)不能为空")
 	//@MaxLength(value=2, msg="字段[状态(Y有效N无效)]超出最大长度[2]")
	private String state;
    /**
     * 是否为小组组长(Y是N否)       db_column: is_leader 
     */	
 	//@NotNull(msg="是否为小组组长(Y是N否)不能为空")
 	//@MaxLength(value=2, msg="字段[是否为小组组长(Y是N否)]超出最大长度[2]")
	private String isLeader;
    /**
     * 来源字典group_member_identity：1项目经理2技术总监3开发人员4测试人员       db_column: dict_code 
     */	
 	//@NotNull(msg="来源字典group_member_identity：1项目经理2技术总监3开发人员4测试人员不能为空")
 	//@MaxLength(value=2, msg="字段[来源字典group_member_identity：1项目经理2技术总监3开发人员4测试人员]超出最大长度[2]")
	private String dictCode;
	//columns END

	public TevglTchClassroomGroupMember(){
	}

	public TevglTchClassroomGroupMember(
		String gmId
	){
		this.gmId = gmId;
	}

	public void setGmId(String value) {
		this.gmId = value;
	}
	public String getGmId() {
		return this.gmId;
	}
	public void setGpId(String value) {
		this.gpId = value;
	}
	public String getGpId() {
		return this.gpId;
	}
	public void setTraineeId(String value) {
		this.traineeId = value;
	}
	public String getTraineeId() {
		return this.traineeId;
	}
	public void setSortNum(Integer value) {
		this.sortNum = value;
	}
	public Integer getSortNum() {
		return this.sortNum;
	}
	public void setState(String value) {
		this.state = value;
	}
	public String getState() {
		return this.state;
	}
	public void setIsLeader(String value) {
		this.isLeader = value;
	}
	public String getIsLeader() {
		return this.isLeader;
	}
	public void setDictCode(String value) {
		this.dictCode = value;
	}
	public String getDictCode() {
		return this.dictCode;
	}
	
	private TevglTchClassroomGroup tevglTchClassroomGroup;
	
	public void setTevglTchClassroomGroup(TevglTchClassroomGroup tevglTchClassroomGroup){
		this.tevglTchClassroomGroup = tevglTchClassroomGroup;
	}
	
	public TevglTchClassroomGroup getTevglTchClassroomGroup() {
		return tevglTchClassroomGroup;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

