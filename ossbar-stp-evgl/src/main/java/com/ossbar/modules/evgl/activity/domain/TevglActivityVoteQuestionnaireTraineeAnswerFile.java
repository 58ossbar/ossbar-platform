package com.ossbar.modules.evgl.activity.domain;

import com.alibaba.fastjson.JSONObject;
import com.ossbar.core.baseclass.domain.BaseDomain;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2019 </p>
 * <p> Company:creatorblue.co.,ltd </p>
 *
 * @author zhuq
 * @version 1.0
 */

public class TevglActivityVoteQuestionnaireTraineeAnswerFile extends BaseDomain<TevglActivityVoteQuestionnaireTraineeAnswerFile> {
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "TevglActivityVoteQuestionnaireTraineeAnswerFile";
	public static final String ALIAS_FILE_ID = "主键id";
	public static final String ALIAS_ID = "投票/问卷作答表主键";
	public static final String ALIAS_TRAINEE_ID = "学员id";
	public static final String ALIAS_URL = "文件地址";
	public static final String ALIAS_FILE_SUFFIX = "文件后缀";
	public static final String ALIAS_FILE_TYPE = "文件类型";
	public static final String ALIAS_FILE_SIZE = "文件大小(单位字节)";
	public static final String ALIAS_SORT_NUM = "排序号";
	public static final String ALIAS_ORIGINAL_FILENAME = "源文件名";
	public static final String ALIAS_DURATION_TIME = "视频/音频的时长(单位秒)";
	public static final String ALIAS_FIRST_CAPTURE_ACCESS_URL = "视频第1帧图片访问路径";
	public static final String ALIAS_FIRST_CAPTURE_SAVE_PATH = "视频第1帧图片实际路径";
	public static final String ALIAS_STATE = "状态(Y有效N无效)";
	

    /**
     * 主键id       db_column: file_id 
     */	
 	//@NotNull(msg="主键id不能为空")
 	//@MaxLength(value=32, msg="字段[主键id]超出最大长度[32]")
	private String fileId;
    /**
     * 投票/问卷作答表主键       db_column: id 
     */	
 	////@NotNull(msg="投票/问卷作答表主键不能为空")
 	//@MaxLength(value=32, msg="字段[投票/问卷作答表主键]超出最大长度[32]")
	private String id;
    /**
     * 学员id       db_column: trainee_id 
     */	
 	////@NotNull(msg="学员id不能为空")
 	//@MaxLength(value=32, msg="字段[学员id]超出最大长度[32]")
	private String traineeId;
    /**
     * 文件地址       db_column: url 
     */	
 	////@NotNull(msg="文件地址不能为空")
 	//@MaxLength(value=100, msg="字段[文件地址]超出最大长度[100]")
	private String url;
    /**
     * 文件后缀       db_column: file_suffix 
     */	
 	////@NotNull(msg="文件后缀不能为空")
 	//@MaxLength(value=20, msg="字段[文件后缀]超出最大长度[20]")
	private String fileSuffix;
    /**
     * 文件类型       db_column: file_type 
     */	
 	////@NotNull(msg="文件类型不能为空")
 	//@MaxLength(value=255, msg="字段[文件类型]超出最大长度[255]")
	private String fileType;
    /**
     * 文件大小(单位字节)       db_column: file_size 
     */	
 	////@NotNull(msg="文件大小(单位字节)不能为空")
 	//@MaxLength(value=19, msg="字段[文件大小(单位字节)]超出最大长度[19]")
	private Long fileSize;
    /**
     * 排序号       db_column: sort_num 
     */	
 	////@NotNull(msg="排序号不能为空")
 	//@MaxLength(value=10, msg="字段[排序号]超出最大长度[10]")
	private Integer sortNum;
    /**
     * 源文件名       db_column: original_filename 
     */	
 	////@NotNull(msg="源文件名不能为空")
 	//@MaxLength(value=255, msg="字段[源文件名]超出最大长度[255]")
	private String originalFilename;
    /**
     * 视频/音频的时长(单位秒)       db_column: duration_time 
     */	
 	////@NotNull(msg="视频/音频的时长(单位秒)不能为空")
 	//@MaxLength(value=19, msg="字段[视频/音频的时长(单位秒)]超出最大长度[19]")
	private Long durationTime;
    /**
     * 视频第1帧图片访问路径       db_column: first_capture_access_url 
     */	
 	////@NotNull(msg="视频第1帧图片访问路径不能为空")
 	//@MaxLength(value=255, msg="字段[视频第1帧图片访问路径]超出最大长度[255]")
	private String firstCaptureAccessUrl;
    /**
     * 视频第1帧图片实际路径       db_column: first_capture_save_path 
     */	
 	////@NotNull(msg="视频第1帧图片实际路径不能为空")
 	//@MaxLength(value=255, msg="字段[视频第1帧图片实际路径]超出最大长度[255]")
	private String firstCaptureSavePath;
    /**
     * 状态(Y有效N无效)       db_column: state 
     */	
 	////@NotNull(msg="状态(Y有效N无效)不能为空")
 	//@MaxLength(value=2, msg="字段[状态(Y有效N无效)]超出最大长度[2]")
	private String state;
	//columns END

	public TevglActivityVoteQuestionnaireTraineeAnswerFile(){
	}

	public TevglActivityVoteQuestionnaireTraineeAnswerFile(
		String fileId
	){
		this.fileId = fileId;
	}

	public void setFileId(String value) {
		this.fileId = value;
	}
	public String getFileId() {
		return this.fileId;
	}
	public void setId(String value) {
		this.id = value;
	}
	public String getId() {
		return this.id;
	}
	public void setTraineeId(String value) {
		this.traineeId = value;
	}
	public String getTraineeId() {
		return this.traineeId;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	public String getUrl() {
		return this.url;
	}
	public void setFileSuffix(String value) {
		this.fileSuffix = value;
	}
	public String getFileSuffix() {
		return this.fileSuffix;
	}
	public void setFileType(String value) {
		this.fileType = value;
	}
	public String getFileType() {
		return this.fileType;
	}
	public void setFileSize(Long value) {
		this.fileSize = value;
	}
	public Long getFileSize() {
		return this.fileSize;
	}
	public void setSortNum(Integer value) {
		this.sortNum = value;
	}
	public Integer getSortNum() {
		return this.sortNum;
	}
	public void setOriginalFilename(String value) {
		this.originalFilename = value;
	}
	public String getOriginalFilename() {
		return this.originalFilename;
	}
	public void setDurationTime(Long value) {
		this.durationTime = value;
	}
	public Long getDurationTime() {
		return this.durationTime;
	}
	public void setFirstCaptureAccessUrl(String value) {
		this.firstCaptureAccessUrl = value;
	}
	public String getFirstCaptureAccessUrl() {
		return this.firstCaptureAccessUrl;
	}
	public void setFirstCaptureSavePath(String value) {
		this.firstCaptureSavePath = value;
	}
	public String getFirstCaptureSavePath() {
		return this.firstCaptureSavePath;
	}
	public void setState(String value) {
		this.state = value;
	}
	public String getState() {
		return this.state;
	}
	
	private TevglActivityVoteQuestionnaireTraineeAnswer tevglActivityVoteQuestionnaireTraineeAnswer;
	
	public void setTevglActivityVoteQuestionnaireTraineeAnswer(TevglActivityVoteQuestionnaireTraineeAnswer tevglActivityVoteQuestionnaireTraineeAnswer){
		this.tevglActivityVoteQuestionnaireTraineeAnswer = tevglActivityVoteQuestionnaireTraineeAnswer;
	}
	
	public TevglActivityVoteQuestionnaireTraineeAnswer getTevglActivityVoteQuestionnaireTraineeAnswer() {
		return tevglActivityVoteQuestionnaireTraineeAnswer;
	}
	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}

