package org.springframework.act;

public class Mojo {
    private String mojoName = "mojo'name";

	public String getMojoName() {
		return mojoName;
	}

	public void setMojoName(String mojoName) {
		this.mojoName = mojoName;
	}

	@Override
	public String toString() {
		return "Mojo(mojoName：" + mojoName + ")";
	}
}
