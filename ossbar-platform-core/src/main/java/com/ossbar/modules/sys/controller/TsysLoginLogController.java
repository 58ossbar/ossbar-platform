package com.ossbar.modules.sys.controller;

import java.util.Map;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.core.common.cbsecurity.log.SysLog;
import com.ossbar.modules.sys.api.TsysLoginLogService;

/**
 * <p>登陆日志</p>
 * <p>Title: TsysLoginLogController.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2019</p>
 * <p>Company: 湖南创蓝信息科技有限公司</p> 
 * @author huj
 * @date 2019年5月30日
 */
@RestController
@RequestMapping("/api/sys/loginlog")
public class TsysLoginLogController {

	@Reference(version = "1.0.0")
	private TsysLoginLogService tsysLoginLogService;
	
	/**
	 * <p>查询列表</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param params
	 * @return
	 */
	@GetMapping("/query")
	@PreAuthorize("hasAuthority('sys:tsysloginlog:query')")
	@SysLog("查询登陆日志列表")
	public R query(@RequestParam Map<String, Object> params) {
		return tsysLoginLogService.selectAllByMap(params);
	}
	
	/**
	 * <p>批量删除</p>
	 * @author huj
	 * @data 2019年5月30日
	 * @param ids
	 * @return
	 */
	@PostMapping("/deletes")
	@PreAuthorize("hasAuthority('sys:tsysloginlog:remove')")
	@SysLog("删除登陆日志信息")
	public R delete(@RequestBody String[] ids) {
		return tsysLoginLogService.delete(ids);
	}
	
}
