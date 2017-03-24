package com.visionet.platform.cooperation.dto;

public class banlanceAccountsDTO extends BaseReturnDTO {
	private BanlanceAccountsPayDTO data;

	public BanlanceAccountsPayDTO getData() {
		return data;
	}

	public void setData(BanlanceAccountsPayDTO data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "banlanceAccountsDTO [data=" + data + "]";
	}

}
