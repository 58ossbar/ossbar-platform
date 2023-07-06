package com.ossbar.modules.mgr.site.controller;

import com.ossbar.common.cbsecurity.logs.annotation.SysLog;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.common.DictService;
import com.ossbar.modules.evgl.site.api.TevglSiteUpdateLogService;
import com.ossbar.modules.evgl.site.domain.TevglSiteUpdateLog;
import com.ossbar.utils.constants.Constant;
import com.ossbar.utils.tool.StrUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

@RestController
@RequestMapping("/api/site/tevglsiteupdatelog")
public class TevglSiteUpdateLogController {
	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(TevglSiteUpdateLogController.class);
	@Reference(version = "1.0.0")
	private TevglSiteUpdateLogService tevglSiteUpdateLogService;
	@Reference(version = "1.0.0")
	private DictService dictService;

	/**
	 * 查询列表(返回List<Bean>)
	 * @param params
	 * @return R
	 */
	@GetMapping("/query")
	@PreAuthorize("hasAuthority('site:tevglsiteupdatelog:query')")
	@SysLog("查询列表(返回List<Bean>)")
	public R query(@RequestParam Map<String, Object> params) {
		return tevglSiteUpdateLogService.query(params);
	}
	/**
	 * 查询列表(返回List<Map<String, Object>)
	 * @param params
	 * @return R
	 */
	@GetMapping("/queryForMap")
	@PreAuthorize("hasAuthority('site:tevglsiteupdatelog:query')")
	@SysLog("查询列表(返回List<Map<String, Object>)")
	public R queryForMap(@RequestParam Map<String, Object> params) {
		return tevglSiteUpdateLogService.queryForMap(params);
	}

	/**
	 * 查看明细
	 */
	@GetMapping("/view/{id}")
	@PreAuthorize("hasAuthority('site:tevglsiteupdatelog:view')")
	@SysLog("查看明细")
	public R view(@PathVariable("id") String id) {
		return tevglSiteUpdateLogService.view(id);
	}
	
	/**
	 * 执行数据新增
	 * 
	 */
	@PostMapping("/saveorupdate")
	@PreAuthorize("hasAuthority('site:tevglsiteupdatelog:add') or hasAuthority('site:tevglsiteupdatelog:edit')")
	@SysLog("执行数据新增")
	public R saveOrUpdate(@RequestBody(required = false) TevglSiteUpdateLog tevglSiteUpdateLog) {
		if(StrUtils.isEmpty(tevglSiteUpdateLog.getLogId())) { //新增
			return tevglSiteUpdateLogService.save(tevglSiteUpdateLog);
		} else {
			return tevglSiteUpdateLogService.update(tevglSiteUpdateLog);
		}
	}
	
	/**
	 * 单条删除
	 */
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('site:tevglsiteupdatelog:delete')")
	@SysLog("单条删除")
	public R delete(@PathVariable("id") String id) {
		return tevglSiteUpdateLogService.delete(id);
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping("/deletes")
	@PreAuthorize("hasAuthority('site:tevglsiteupdatelog:delete')")
	@SysLog("批量删除")
	public R deleteBatch(@RequestBody(required = true) String[] ids) {
		return tevglSiteUpdateLogService.deleteBatch(ids);
	}
	
	/**
	 * 大类型
	 * @return
	 */
	@GetMapping("/getBigType")
	public R getBigType() {
		List<Map<String,Object>> list = dictService.getDictList("feedbackBigType");
		return R.ok().put(Constant.R_DATA, list);
	}
	
}
