package com.ossbar.modules.sys.controller;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.core.common.cbsecurity.log.SysLog;
import com.ossbar.modules.sys.api.ScheduleJobLogService;

/**
 * <p>定时任务日志控制类</p>
 * <p>Title: ScheduleJobLogController.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年5月30日
 */
@RestController
@RequestMapping("/api/sys/jobLog")
public class ScheduleJobLogController {

	@Reference(version = "1.0.0")
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * <p>查询列表</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param params
	 * @return
	 */
	@GetMapping("/query")
	@SysLog("查询定时任务日志列表")
	public R query(@RequestParam Map<String, Object> params) {
		return scheduleJobLogService.query(params);
	}
	
}
