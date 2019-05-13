package it.sella.smb.exception.utility;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public enum ErrorMessage {

	MSG_101("SB-101","Error Completing Request"),
	SMB_ERR_9999("ERR-9999"), 
	SMB_SERVICE_STATUS_ERR("Service Not Available"),
	SQL_ERR
	;
	

	private String msg;
	private String code;
	private static Map<String,ErrorMessage> sqlErrorCodeMap = new HashMap<String, ErrorMessage>();

	
	ErrorMessage(){
		//Empty Constructor
	}
	
	ErrorMessage(final String code) {
		this.code = code;
		this.msg = code;
	}

	ErrorMessage(final String msg, final String code) {
		this.code = code;
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public String getConcatErrMsg(){
		final StringBuilder builder = new StringBuilder();
		builder.append(this.code).append(" : ").append(this.msg);
		return builder.toString();
	}
	
	/**
	 * Method getSQLErrorMsg.
	 * @param sqlEx SQLException
	 * @return BTError
	 */
	public ErrorMessage getSQLErrorMsg(final SQLException sqlEx){
		final String sqlErrorCode = String.valueOf(sqlEx.getErrorCode());
		final String sqlErrorMessage = sqlEx.getMessage();
		ErrorMessage  errMsg = MSG_101;
		if(isCustomSQLErrorCode(sqlErrorCode)){
			errMsg = getSQLErrorMsgMap().get(sqlErrorCode);
		}else if(sqlErrorMessage!=null && !sqlErrorMessage.trim().equals("")){
			this.code = "";
			this.msg = sqlErrorMessage.trim().toUpperCase();
			errMsg = SQL_ERR;
		}

		return errMsg;
	}

	private Map<String,ErrorMessage> getSQLErrorMsgMap(){
		//	if(sqlErrorCodeMap.isEmpty()){
		//sqlErrorCodeMap.put("20002", MSG_102);
		//	}
		return sqlErrorCodeMap;
	}



	private boolean isCustomSQLErrorCode(final String sqlErrorCode){

		return getSQLErrorMsgMap().containsKey(sqlErrorCode);
	}
}
