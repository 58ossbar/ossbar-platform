package com.ossbar.modules.evgl.eao.api;

import com.ossbar.core.baseclass.api.IBaseService;
import com.ossbar.core.baseclass.domain.R;
import com.ossbar.modules.evgl.eao.domain.TeaoTraineeExamine;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:ossbar.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public interface TeaoTraineeExamineService extends IBaseService<TeaoTraineeExamine>{
	
	/**
	 * 定时自动保存考核成绩
	 * @param ctId
	 * @param erId
	 * @param traineeIds
	 * @param regularIds
	 * @param regularSjnums
	 * @param loginUserId
	 * @return
	 */
	R preExam(String ctId, String erId, String traineeIds, String regularIds, String regularSjnums, String loginUserId, String traineeName);
	
	/**
	 * 最终提交考核成绩
	 * @param ctId
	 * @param erId
	 * @param traineeIds
	 * @param regularIds
	 * @param regularSjnums
	 * @param loginUserId
	 * @return
	 */
	R commit(String ctId, String erId, String traineeIds, String regularIds, String regularSjnums, String loginUserId, String traineeName);
	
}