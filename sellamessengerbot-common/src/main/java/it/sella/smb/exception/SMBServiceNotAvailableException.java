package it.sella.smb.exception;

import it.sella.smb.exception.utility.ErrorMessage;

@SuppressWarnings("serial")
public class SMBServiceNotAvailableException extends SMBBaseThrowable{


    private final String serviceName;
    private final String status;
    private final String checkedBy;

    public SMBServiceNotAvailableException(String serviceName, String status,String checkedBy) {
        super(ErrorMessage.SMB_SERVICE_STATUS_ERR);
        this.serviceName = serviceName;
        this.status = status;
        this.checkedBy = checkedBy;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getStatus() {
        return status;
    }

    public String getCheckedBy() {
        return checkedBy;
    }
}
