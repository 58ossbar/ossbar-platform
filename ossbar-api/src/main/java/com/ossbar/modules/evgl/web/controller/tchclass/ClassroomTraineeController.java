package com.ossbar.modules.evgl.web.controller.tchclass;

import com.alibaba.fastjson.JSONObject;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.common.CheckSession;
import com.ossbar.modules.evgl.common.EvglGlobal;
import com.ossbar.modules.evgl.common.LoginUtils;
import com.ossbar.modules.evgl.tch.api.TevglTchClassroomService;
import com.ossbar.modules.evgl.tch.api.TevglTchClassroomTraineeService;
import com.ossbar.modules.evgl.trainee.api.TevglTraineeInfoService;
import com.ossbar.modules.evgl.trainee.domain.TevglTraineeInfo;
import com.ossbar.utils.constants.Constant;
import com.ossbar.utils.tool.StrUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("teachingCenter-api/classroom-trainee-api")
public class ClassroomTraineeController {
	
	// 文件上传路径
	@Value("${com.creatorblue.file-upload-path}")
    private String uploadPath;

	@Reference(version = "1.0.0")
	private TevglTchClassroomService tevglTchClassroomService;
	@Reference(version = "1.0.0")
	private TevglTchClassroomTraineeService tevglTchClassroomTraineeService;
	@Reference(version = "1.0.0")
	private TevglTraineeInfoService tevglTraineeInfoService;
	
	/**
	 * 课堂成员
	 * @param request
	 * @param ctId 课堂主键
	 * @param traineeName 学员名称
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/listClassroomTrainee")
	@CheckSession
	public R listClassroomTrainee(HttpServletRequest request, String ctId, String traineeName, Integer pageNum, Integer pageSize, String mobile) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		Map<String, Object> params = new HashMap<>();
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		if (StrUtils.isNotEmpty(ctId)) {
			params.put("ctId", ctId);
		}
		if (StrUtils.isNotEmpty(traineeName)) {
			params.put("traineeName", traineeName);
		}
		params.put("mobile", mobile);
		params.put("pageNum", pageNum);
		params.put("pageSize", pageSize);
		params.put("queryPermission", "Y");
		return tevglTchClassroomTraineeService.selectListMapForWeb(params, traineeInfo.getTraineeId());
	}
	
	/**
	 * 班级成员列表，只会查询未加入课堂的
	 * @param ctId 课堂主键
	 * @param classId 班级主键
	 * @param traineeName 学员名称
	 * @param pageNum 当前页
	 * @param pageSize 每页显示数
	 * @return
	 */
	@GetMapping("listClassTrainee")
	@CheckSession
	public R listClassTrainee(HttpServletRequest request, String ctId, String classId, String traineeName, 
			Integer pageNum, Integer pageSize) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 10 : pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ctId", ctId);
		params.put("classId", classId);
		params.put("pageNum", pageNum);
		params.put("pageSize", pageSize);
		if (StrUtils.isNotEmpty(traineeName)) {
			params.put("traineeName", traineeName);
		}
		return R.ok();
	}
	
	/**
	 * 删除课堂成员
	 * @param request
	 * @param id 课堂成员表主键
	 * @param ctId 课堂主键
	 * @param traineeId 被删除的人 
	 * @return
	 */
	@PostMapping("/deleteClassTrainee")
	@CheckSession
	public R deleteClassroomTrainee(HttpServletRequest request, String id, String ctId, String traineeId) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		params.put("ctId", ctId);
		params.put("traineeId", traineeId);
		params.put("loginUserId", traineeInfo.getTraineeId());
		return tevglTchClassroomTraineeService.deleteClassroomTrainee(params);
	}
	
	/**
	 * 批量删除课堂成员
	 * @param request
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/deleteClassroomTraineeBatch")
	@CheckSession
	public R deleteClassroomTraineeBatch(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		return tevglTchClassroomTraineeService.deleteClassroomTraineeBatch(jsonObject, traineeInfo.getTraineeId());
	}

	/**
	 * 查看课堂成员基本信息
	 * @param ctId
	 * @param traineeId
	 * @return
	 */
	@GetMapping("/viewTraineeBaseInfo")
	public R viewTraineeBaseInfo(String ctId, String traineeId) {
		return tevglTchClassroomTraineeService.viewTraineeBaseInfoForWeb(ctId, traineeId);
	}
	
	/**
	 * 图形信息
	 * @param ctId
	 * @param traineeId
	 * @return
	 */
	@GetMapping("/viewTraineeDetailInfo")
	public R viewTraineeDetailInfo(String ctId, String traineeId) {
		return tevglTchClassroomTraineeService.viewTraineeDetailInfoForWeb(ctId, traineeId);
	}
	
	/**
	 * 设为助教
	 * @param request
	 * @param  {ctId:'课堂主键',  traineeId:'学员主键' }
	 * @return
	 */
	@PostMapping("/setTraineeToTeachingAssistant")
	@CheckSession
	public R setTraineeToTeachingAssistant(HttpServletRequest request, String ctId, String traineeId) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("ctId", ctId);
		params.put("traineeId", traineeId);
		params.put("loginUserId", traineeInfo.getTraineeId());
		return tevglTchClassroomService.setTraineeToTeachingAssistant(params);
	}
	
	/**
	 * 取消助教
	 * @param request
	 * @param ctId
	 * @return
	 */
	@PostMapping("/cancelTeachingAssistant")
	@CheckSession
	public R cancelTeachingAssistant(HttpServletRequest request, String ctId, String traineeId) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		return tevglTchClassroomService.cancelTeachingAssistant(ctId, traineeId, traineeInfo.getTraineeId());
	}
	
	/**
	 * 查看经验值明细
	 * @param ctId
	 * @param traineeId
	 * @return
	 */
	@GetMapping("/viewEmpiricalValueLogs")
	@CheckSession
	public R viewEmpiricalValueLogs(HttpServletRequest request, String ctId, String traineeId, Integer pageNum, Integer pageSize) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		return R.ok().put(Constant.R_DATA, null);
	}
	
	/**
	 * 无分页
	 * @param request
	 * @param ctId
	 * @return
	 */
	@PostMapping("queryAllTrainee")
	@CheckSession
	public R queryAllTrainee(HttpServletRequest request, String ctId, String traineeName) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginUserId", traineeInfo.getTraineeId());
		params.put("ctId", ctId);
		params.put("traineeName", traineeName);
		List<Map<String,Object>> list = tevglTchClassroomTraineeService.listClassroomTrainee(params);
		return R.ok().put(Constant.R_DATA, list);
	}
	
	/**
	 * 教师修改学员信息
	 * @param request
	 * @param ctId
	 * @param traineeId
	 * @param traineeName
	 * @param traineeSex
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("/updateTraineeInfo")
	@CheckSession
	public R updateTraineeInfo(HttpServletRequest request, String ctId, String traineeId, String traineePic, String traineeName, String nickName, String traineeSex) throws Exception {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if (traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		return tevglTchClassroomTraineeService.updateTraineeInfo(request, ctId, traineeId, traineePic, traineeName, nickName, traineeSex, traineeInfo.getTraineeId());
	}
	
}
