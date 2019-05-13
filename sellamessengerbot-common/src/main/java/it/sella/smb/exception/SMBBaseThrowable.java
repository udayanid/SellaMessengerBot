package it.sella.smb.exception;

import it.sella.smb.exception.utility.ErrorMessage;

@SuppressWarnings("serial")
public abstract class SMBBaseThrowable extends RuntimeException
{
    ///PLEASE PUT HERE ONLY GENERIC EXCEPTION AND NOT SPECIFIC ERROR!
	 /*  public static final String MB_ERR_9999 = "ERR-9999";
    public static final String MB_DBA_001 = "DB_ERROR";
    public static final String MB_GEN_001 = "TECH_ERROR";
    public static final String MB_GEN_002 = "FUNCTION NAME UNAVAILABLE";
    
    
    public static final String MB_GEN_VALIDATION_CODE = "-999998";
    public static final String MB_GEN_VALIDATION_MSG = "Servizio momentaneamente non disponibile";
    public static final String MB_XML_001 = "XML_PARSE_ERROR";
    public static final String MB_SOA_UNCONTROLLED_ERROR = "SOA_UNCONTROLLED_ERROR"; //NOT USE THIS CODE!!!!!!!!
    public static final String MB_SOA_002 = "XML_SOA_EXCEPTION";
   public static final String HB_DBA_002 = "DB_WRONG_PARAMETER";
    public static final String HB_DBA_003 = "DB_NO_DATA_FOUND";
    public static final String HB_WDG_DB_001 = "WDT_TECH_ERR";
    public static final String HB_AUTH_ERROR = "HB_AUTH_ERR";
    public static final String HB_SOA_001 = "XML_SOA_ERROR";
    public static final String HB_SOA_003 = "SOA_WRONG_INPUT";
    public static final String HB_SOA_004 = "XML_SOA_EMPTY_OUTPUT";
    public static final String HB_SOA_005 = "HB_SOA_ERROR_MESSAGE";
    public static final String HB_TYPE_EXP = "HB_TYPE_EXCEPTION";
    public static final String HB_JSON_001 = "XML_JSON_ERROR";
    public static final String HB_PRODUCT_NOT_FOUND = "NO_PRODUCT_FOUND";
    public static final String HB_SERVICE_IN_EXECUTION = "SERVICE_IN_EXECUTION";
    public static final String HB_SERVICE_NOT_STARTED = "SERVICE_NOT_STARTED";
    public static final String HB_HTTP_CLIENT_EXP="HB_HTTP_CLIENT_EXP";
    public static final String HB_VALIDATION_EXP="HB_VALIDATION_EXP";
    public static final String DATE_PARSE_ERROR = "DATE_PARSE_ERROR";
    public static final String REQUEST_JSON_PARSE_ERROR = "REQUEST_JSON_PARSE_ERROR";
    public static final String HB_NUMBER_FORMAT_ERROR = "HB_NUMBER_FORMAT_ERROR";*/
    ///PLEASE PUT HERE ONLY GENERIC EXCEPTION AND NOT SPECIFIC ERROR!


	public static final String HB_XML_001 = "XML_PARSE_ERROR";
    private String code = ErrorMessage.SMB_ERR_9999.getCode();
    private String[] params;

    public SMBBaseThrowable(ErrorMessage error)
    {
        super(error.getMsg());
        code = error.getCode() !=null ? error.getCode() : ErrorMessage.SMB_ERR_9999.getCode();
    }
    
    public SMBBaseThrowable(ErrorMessage error, Throwable t) {
    	 super(error.getMsg(), t);
         code = error.getCode() !=null ? error.getCode() : ErrorMessage.SMB_ERR_9999.getCode();
	}
    
    public SMBBaseThrowable(final String msg)
    {
        super(msg);
        code = code !=null? code :ErrorMessage.SMB_ERR_9999.getCode();
    }
    
    public SMBBaseThrowable(final String msg, final String code)
    {
        super(msg);
        this.code = code!=null?code:ErrorMessage.SMB_ERR_9999.getCode();
    }

    public SMBBaseThrowable(final String msg, final String code, final Throwable t)
    {
        super(msg,t);
        this.code = code!=null?code:ErrorMessage.SMB_ERR_9999.getCode();
    }

    public SMBBaseThrowable(final String msg, final String code, final String[] params)
    {
        super(msg);
        this.code = code!=null?code:ErrorMessage.SMB_ERR_9999.getCode();
        this.params = params;
    }

    public SMBBaseThrowable(final String msg, final String code, final String[] params, final Throwable t)
    {
        super(msg,t);
        this.code = code!=null?code:ErrorMessage.SMB_ERR_9999.getCode();
        this.params = params;
    }


	public String getCode() {
        return code;
    }

    public String[] getParams() {
        return params;
    }
}
