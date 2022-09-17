package com.ossbar.modules.evgl.web.controller.threefeetplatform.resource;

import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.common.GlobalRoomPermission;
import com.ossbar.modules.evgl.book.api.TevglBookSubjectService;
import com.ossbar.modules.evgl.common.CheckSession;
import com.ossbar.modules.evgl.common.EvglGlobal;
import com.ossbar.modules.evgl.common.LoginUtils;
import com.ossbar.modules.evgl.tch.api.TevglTchClassroomService;
import com.ossbar.modules.evgl.tch.domain.TevglTchClassroom;
import com.ossbar.modules.evgl.trainee.domain.TevglTraineeInfo;
import com.ossbar.utils.tool.StrUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huj
 * @create 2022-09-17 9:50
 * @email hujun@creatorblue.com
 * @company 创蓝科技 www.creatorblue.com
 */
@RestController
@RequestMapping("/resourceCenter-api")
public class ResourceController {

    private Logger log = LoggerFactory.getLogger(ResourceController.class);

    @Reference(version = "1.0.0")
    private TevglTchClassroomService tevglTchClassroomService;
    @Reference(version = "1.0.0")
    private TevglBookSubjectService tevglBookSubjectService;


    /**
     * 教材的树形数据（教学包对应课程的树形数据目前使用的这个）
     * @param subjectId
     * @return
     */
    @GetMapping("/getBookTreeDataNew")
    @CheckSession
    public R getBookTreeDataNew(HttpServletRequest request, String ctId, String pkgId, String subjectId, String chapterName) {
        TevglTraineeInfo traineeInfo = LoginUtils.getLoginUser(request);
        if (traineeInfo == null) {
            return R.error(EvglGlobal.UN_LOGIN_MESSAGE);
        }
        if (StrUtils.isNotEmpty(ctId)) {
            TevglTchClassroom tevglTchClassroom = tevglTchClassroomService.selectObjectById(ctId);
            if (tevglTchClassroom == null) {
                return R.error("课堂不存在了！");
            }
            boolean isCreator = StrUtils.isEmpty(tevglTchClassroom.getReceiverUserId()) && tevglTchClassroom.getCreateUserId().equals(traineeInfo.getTraineeId());
            boolean isTeachingAssistant = StrUtils.isNotEmpty(tevglTchClassroom.getTraineeId()) && traineeInfo.getTraineeId().equals(tevglTchClassroom.getTraineeId());
            boolean isReceiver = StrUtils.isNotEmpty(tevglTchClassroom.getReceiverUserId()) && tevglTchClassroom.getReceiverUserId().equals(traineeInfo.getTraineeId());
            // 如果是助教，校验下是否有设置学员是否可见的权限
            boolean hasSetVisiblePermission = false;
            if (isTeachingAssistant) {
                List<String> permissionList = new ArrayList<>(); // tevglTchClassroomRoleprivilegeService.queryPermissionListByCtId(ctId);
                if (permissionList != null && permissionList.size() > 0) {
                    hasSetVisiblePermission = permissionList.stream().anyMatch(a -> a.equals(GlobalRoomPermission.ROOM_PERM_SUBJECT_CHAPTERVISIBLE));
                }
            }
            String identity = (isCreator || hasSetVisiblePermission || isReceiver) ? "teacher" : "trainee";
            return tevglBookSubjectService.getBookForRoomPage(ctId, pkgId, subjectId, chapterName, traineeInfo.getTraineeId(), false, identity);
        } else {
            return tevglBookSubjectService.getBookForPkgPage(pkgId, subjectId, chapterName, traineeInfo.getTraineeId());
        }
    }
}
