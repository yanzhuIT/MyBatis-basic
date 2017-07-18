package com.github.bean;

public enum EmpStatus {

	LOGIN(100, "login success"), LOGOUT(200, "logout success"), REMOVE(300, "remove success");

	private Integer code;
	private String msg;

	private EmpStatus(Integer code, String msg) {

		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public static EmpStatus getStatusByCode(Integer code) {
		switch (code) {
		case 100:
			return EmpStatus.LOGIN;
		case 200:
			return EmpStatus.LOGOUT;
		case 300:
			return EmpStatus.REMOVE;
		default:
			return EmpStatus.LOGOUT;
		}
	}

}
