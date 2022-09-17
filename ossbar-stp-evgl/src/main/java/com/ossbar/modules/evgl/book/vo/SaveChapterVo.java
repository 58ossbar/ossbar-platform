package com.ossbar.modules.evgl.book.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author huj
 * @create 2022-06-11 16:13
 * @email hujun@creatorblue.com
 */
public class SaveChapterVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "必传参数不能为空")
	private String pkgId;
	
	@NotNull(message = "必传参数不能为空")
	private String parentId;
	
	private List<String> chapterNameList;

	public String getPkgId() {
		return pkgId;
	}

	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<String> getChapterNameList() {
		return chapterNameList;
	}

	public void setChapterNameList(List<String> chapterNameList) {
		this.chapterNameList = chapterNameList;
	}

	@Override
	public String toString() {
		return "SaveChapterVo [pkgId=" + pkgId + ", parentId=" + parentId + ", chapterNameList=" + chapterNameList
				+ "]";
	}
	
}
