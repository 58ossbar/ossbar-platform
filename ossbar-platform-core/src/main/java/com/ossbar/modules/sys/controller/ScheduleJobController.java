package com.ossbar.modules.sys.controller;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.core.common.cbsecurity.log.SysLog;
import com.ossbar.modules.core.common.utils.LoginUtils;
import com.ossbar.modules.sys.api.ScheduleJobService;
import com.ossbar.modules.sys.domain.ScheduleJobEntity;
import com.ossbar.modules.sys.domain.TsysUserinfo;

/**
 * <p>定时任务控制类</p>
 * <p>Title: ScheduleJobController.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年5月30日
 */
@RestController
@RequestMapping("/api/sys/job")
public class ScheduleJobController {

	@Reference(version = "1.0.0")
	private ScheduleJobService scheduleJobService;
	@Autowired
	private LoginUtils loginUtils;
	
	/**
	 * <p>查询列表</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param params
	 * @return
	 */
	@GetMapping("/query")
	@PreAuthorize("hasAuthority('sys:job:query')")
	@SysLog("查询定时任务列表")
	public R query(@RequestParam Map<String, Object> params) {
		return scheduleJobService.query(params);
	}
	
	/**
	 * <p>查询明细</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param id
	 * @return
	 */
	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('sys:job:view')")
	@SysLog("查询定时任务明细")
	public R view(@PathVariable("id") String id) {
		return scheduleJobService.selectObjectByJobId(id);
	}
	
	/**
	 * <p>执行数据新增和修改</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param scheduleJob
	 * @return
	 */
	@PostMapping("/save")
	@PreAuthorize("hasAuthority('sys:job:add') or hasAuthority('sys:job:edit')")
	@SysLog("执行定时任务数据新增或修改")
	public R save(@RequestBody(required = false) ScheduleJobEntity scheduleJob) {
		if (scheduleJob.getJobId() == null || "".equals(scheduleJob.getJobId())) { // 新增
			TsysUserinfo obj = loginUtils.getLoginUser();
			if(obj != null){
				scheduleJob.setCreateUserId(obj.getCreateUserId()); // 创建人
			}
		} else {
			TsysUserinfo obj = loginUtils.getLoginUser();
			if(obj != null){
				scheduleJob.setUpdateUserId(obj.getCreateUserId()); // 修改人
			}
		}
		return scheduleJobService.saveOrUpdate(scheduleJob);
	}
	
	/**
	 * <p>删除</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param ids
	 * @return
	 */
	@PostMapping("/deletes")
	@PreAuthorize("hasAuthority('sys:job:remove')")
	@SysLog("删除定时任务")
	public R deletes(@RequestBody(required = false) String[] ids) {
		return scheduleJobService.deleteBatch(ids);
	}
	
	/**
	 * <p>暂停</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param ids
	 * @return
	 */
	@PostMapping("/pause")
	@PreAuthorize("hasAuthority('sys:job:pause')")
	@SysLog("暂停定时任务")
	public R pause(@RequestBody(required = false) String[] ids) {
		return scheduleJobService.pause(ids);
	}
	
	/**
	 * <p>恢复</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param ids
	 * @return
	 */
	@PostMapping("/resume")
	@PreAuthorize("hasAuthority('sys:job:resume')")
	@SysLog("恢复定时任务")
	public R resume(@RequestBody(required = false) String[] ids) {
		return scheduleJobService.resume(ids);
	}
	
	/**
	 * <p>立即执行</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param ids
	 * @return
	 */
	@PostMapping("/runTask")
	@PreAuthorize("hasAuthority('sys:job:run')")
	@SysLog("立即定时任务")
	public R runTask(@RequestBody(required = false) String[] ids) {
		return scheduleJobService.runTask(ids);
	}
	
}
