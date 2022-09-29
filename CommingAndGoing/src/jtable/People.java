package jtable;

import java.time.LocalDateTime;

public class People {
	
	private LocalDateTime visitDate;
	private boolean agree;
	private String address;
	private String tel;
	private String sign;
	
	public People(boolean agree, String address, String tel) {
		this.agree = agree;
		this.address = address;
		this.tel = tel;
	}
	

	public People(boolean agree, String address, String sign, String tel) {
		this.agree = agree;
		this.address = address;
		this.sign = sign;
		this.tel = tel;
	}
	
	
	

	public People(LocalDateTime visitDate, boolean agree, String address, String tel, String sign) {
		this.visitDate = visitDate;
		this.agree = agree;
		this.address = address;
		this.tel = tel;
		this.sign = sign;
	}



	public LocalDateTime getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDateTime visitDate) {
		this.visitDate = visitDate;
	}


	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
	
	
}
