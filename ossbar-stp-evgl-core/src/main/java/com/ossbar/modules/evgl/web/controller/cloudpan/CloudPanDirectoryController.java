package com.ossbar.modules.evgl.web.controller.cloudpan;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.common.DictService;
import com.ossbar.modules.evgl.cloudpan.api.TcloudPanDirectoryService;
import com.ossbar.modules.evgl.cloudpan.api.TcloudPanFileService;
import com.ossbar.modules.evgl.common.CheckSession;
import com.ossbar.modules.evgl.common.EvglGlobal;
import com.ossbar.modules.evgl.common.LoginUtils;
import com.ossbar.modules.evgl.trainee.api.TevglTraineeInfoService;
import com.ossbar.modules.evgl.trainee.domain.TevglTraineeInfo;
import com.ossbar.utils.constants.Constant;
import com.ossbar.utils.tool.StrUtils;

/**
 * 云盘目录
 * @author huj
 *
 */
@RestController
@RequestMapping("/cloud-api/directory")
public class CloudPanDirectoryController {

	@Reference(version = "1.0.0")
	private DictService dictService;
	@Reference(version = "1.0.0")
	private TevglTraineeInfoService tevglTraineeInfoService;
	@Reference(version = "1.0.0")
	private TcloudPanDirectoryService tcloudPanDirectoryService;
	@Reference(version = "1.0.0")
	private TcloudPanFileService tcloudPanFileService;
	
	/**
     * 云盘目录（新版）
     * @return
     */
    @GetMapping("/queryDirListNew")
    @CheckSession
    public R queryDirListNew(HttpServletRequest request, @RequestParam Map<String, Object> params) {
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if (traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        return tcloudPanDirectoryService.queryDirListNew(params, traineeInfo.getTraineeId(), false);
    }
	
	/**
	 * 创建目录
	 * @param request
	 * @param pkgId 必传参数
	 * @param dirId
	 * @param name
	 * @return
	 */
	@PostMapping("createDir")
	@CheckSession
	public R createDir(HttpServletRequest request, String pkgId, String dirId, String name, String icon) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
		if(traineeInfo == null) {
			return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
		}
		return tcloudPanDirectoryService.createDir(pkgId, dirId, name, icon, traineeInfo.getTraineeId());
	}
	
	@RequestMapping("/viewDetail")
    @CheckSession
    public R viewDetail(HttpServletRequest request, String ctId, String pkgId, String dirId, String name){
        TevglTraineeInfo tevglTraineeInfo = LoginUtils.getLoginUser(request);
        if(tevglTraineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        if (StrUtils.isNotEmpty(ctId)) {
            return tcloudPanDirectoryService.viewDetailForRoomPage(ctId, pkgId, dirId, name, tevglTraineeInfo.getTraineeId());
        } else {
            return tcloudPanDirectoryService.viewDetailForPkgPage(pkgId, dirId, name, tevglTraineeInfo.getTraineeId());
        }
        //return tcloudPanDirectoryService.viewDetailNew(ctId, pkgId, dirId, name, traineeInfo.getTraineeId());
    }
	
	/**
	 * 重命名目录
	 * @param request
	 * @param dirId
	 * @return
	 */
	@PostMapping("/rename")
	@CheckSession
    public R rename(HttpServletRequest request, String id, String name, String type, String pkgId, String icon){
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if(traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        if (StrUtils.isEmpty(type)) {
        	return R.error("必传参数为空");
        }
        // 重命名文件夹
        if ("1".equals(type)) {
        	return tcloudPanDirectoryService.rename(id, name, icon, traineeInfo.getTraineeId()); 
        }
        // 重命名文件
        if ("2".equals(type)) {
        	return tcloudPanFileService.rename(id, name, traineeInfo.getTraineeId(), pkgId);
        }
        return R.ok();
    }
	
	/**
	 * 删除目录和文件
	 * @param request
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/deletes")
	@CheckSession
    public R deletes(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if(traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        //return tcloudPanDirectoryService.deletes(jsonObject, traineeInfo.getTraineeId());
        return tcloudPanDirectoryService.deletesNew(jsonObject, traineeInfo.getTraineeId());
    }
	
	/**
	 * 获取目录树
	 * @param request
	 * @return
	 */
	@PostMapping("/getDirectoryTree")
	@CheckSession
    public R getDirectoryTree(HttpServletRequest request, String pkgId){
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if(traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        List<Map<String,Object>> list = tcloudPanDirectoryService.getDirectoryTree2(pkgId, traineeInfo.getTraineeId());
        return R.ok().put(Constant.R_DATA, list);
    }
	
	/**
	 * 移动目录或文件
	 * @param request
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/move")
	@CheckSession
	public R move(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if(traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
		return tcloudPanDirectoryService.move(jsonObject, traineeInfo.getTraineeId());
	}
	
	/**
	 * 复制目录或文件
	 * @param request
	 * @param jsonObject
	 * @return
	 */
	@PostMapping("/copy")
	@CheckSession
	public R copy(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
		TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if(traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
		return tcloudPanDirectoryService.copy(jsonObject, traineeInfo.getTraineeId());
	}
	
	/**
     * 目录文件树，发布教学包页面中专用
     * @param request
     * @param pkgId
     * @param name
     * @return
     */
    @PostMapping("/getTree")
    @CheckSession
    public R getTree(HttpServletRequest request, String ctId, String pkgId, String name) {
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if(traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        //return tcloudPanDirectoryService.search(ctId, pkgId, name, traineeInfo.getTraineeId());
        return tcloudPanDirectoryService.getDirectoryTree(pkgId, traineeInfo.getTraineeId());
    }
    
    /**
     * 文件夹上传
     * @param request
     * @param pkgId
     * @param dirId
     * @return
     */
    @PostMapping("/uploadFolder")
    @CheckSession
    public R uploadFolder(HttpServletRequest request, String pkgId, String dirId){
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if (traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        String traineeId = traineeInfo.getTraineeId();
        return tcloudPanDirectoryService.uploadFolder(request, pkgId, dirId, traineeId);
    }
}
