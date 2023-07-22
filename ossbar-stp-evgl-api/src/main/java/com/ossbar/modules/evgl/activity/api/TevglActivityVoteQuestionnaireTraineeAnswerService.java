package com.ossbar.modules.evgl.activity.api;

import com.alibaba.fastjson.JSONObject;
import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.activity.domain.TevglActivityVoteQuestionnaireTraineeAnswer;

/**
 * <p> Title: 活动之投票/问卷---学员作答信息表</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TevglActivityVoteQuestionnaireTraineeAnswerService extends IBaseService<TevglActivityVoteQuestionnaireTraineeAnswer>{
	
	/**
	 * 保存学员提交投票/问卷的作答内容
	 * @param jsonObject
	 * @param loginUserId
	 * @return
	 */
	R saveTraineeCommitAnswerContent(JSONObject jsonObject, String loginUserId);
	
}