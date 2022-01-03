package com.spring.board.vo;

public class ComcodeVo {
/*CODE_TYPE VARCHAR2(10 BYTE) NOT NULL 
, CODE_ID VARCHAR2(10 BYTE) NOT NULL 
, CODE_NAME VARCHAR2(20 BYTE) 
, CREATOR VARCHAR2(8 BYTE) 
, CREATE_TIME VARCHAR2(14 BYTE) 
, MODIFIER VARCHAR2(8 BYTE) 
, MODIFIED_TIME VARCHAR2(14 BYTE) 
, CONSTRAINT COM_CODE_PK PRIMARY KEY */
	
	private String codeType;
	private String codeId;
	private String codeName;
	private String creator;
	private String modified;
	
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	@Override
	public String toString() {
		return "ComcodeVo [codeType=" + codeType + ", codeId=" + codeId + ", codeName=" + codeName + ", creator="
				+ creator + ", modified=" + modified + "]";
	}
	
	
}
