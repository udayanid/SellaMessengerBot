package it.sella.smb.chat.dto;

import java.util.List;

public class PollResponse extends BaseResponse{

	private String status;
	private List<Error> errors = null;
	private List<Result> results = null;
	private Object errorMessageCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public Object getErrorMessageCode() {
		return errorMessageCode;
	}

	public void setErrorMessageCode(Object errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}

	@Override
	public String toString() {
		return "PollResponse [status=" + status + ", errors=" + errors + ", results=" + results + ", errorMessageCode="
				+ errorMessageCode + "]";
	}
	

}
