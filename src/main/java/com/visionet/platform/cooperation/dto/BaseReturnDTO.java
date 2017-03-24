package com.visionet.platform.cooperation.dto;

public class BaseReturnDTO {
	private String returnCode;
	private String returnMessage;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	@Override
	public String toString() {
		return "BaseReturnDto [returnCode=" + returnCode + ", returnMessage=" + returnMessage + "]";
	}

}
