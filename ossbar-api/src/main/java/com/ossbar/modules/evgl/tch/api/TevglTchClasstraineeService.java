package com.ossbar.modules.evgl.tch.api;

import com.ossbar.common.exception.CreatorblueException;
import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.tch.domain.TevglTchClasstrainee;
import com.ossbar.modules.evgl.trainee.domain.TevglTraineeInfo;
import com.ossbar.modules.evgl.trainee.vo.TraineeInfoVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * <p> Title: 班级学员</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglTchClasstraineeService extends IBaseService<TevglTchClasstrainee> {
	
	/**
	 * <p>根据条件查询记录，返回List<Map<String, Object>></p>  
	 * @author huj
	 * @data 2019年12月16日	
	 * @param map
	 * @return
	 */
	R selectListMapForWeb(Map<String, Object> map);
	
	/**
	 * 根据条件查询班级成员
	 * @param params 说明：必传的参数key； classId，选传的key有: trianeeName
	 * @return
	 * @apiNote 查询并返回了如下字段：ctId班级学员表主键，classId班级id，className班级名称， traineeId学员id，
	 * traineeName学员名称（如没有值则查的微信昵称），traineeSex学员性别，traineePic学员头像（没有证件照则查微信头像）
	 */
	List<Map<String, Object>> listClassTraineeInfo(Map<String, Object> params);
	
	/**
	 * 根据条件查询班级成员，注意：只会查询未加入课堂的成员
	 * @param params 必传参数key：ctId课堂主键
	 * @return
	 */
	R listClassTraineeExcludeJoinedClassroom(Map<String, Object> params);
	
	/**
	 * 管理端修改班级成员信息（其实就是修改学员信息）
	 * @param tevglTraineeInfo
	 * @return
	 */
	R updateTraineeForMgr(TevglTraineeInfo tevglTraineeInfo);
	
	/**
	 * excel导入班级成员
	 * @param request
	 * @param classId
	 * @return
	 */
	R importExcel(HttpServletRequest request, String classId);

	/**
	 * 根据条件查询班级成员
	 * @param map
	 * @return
	 */
	List<TraineeInfoVO> selectClassTraineeListByMap(Map<String, Object> map);
	
	/**
	 * 批量新增班级成员
	 * @param classId
	 * @param traineeIdList
	 * @return
	 * @throws CreatorblueException
	 */
	R saveClassTraineeBatch(String classId, List<String> traineeIdList) throws CreatorblueException;
}