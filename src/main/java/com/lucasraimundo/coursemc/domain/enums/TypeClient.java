package com.lucasraimundo.coursemc.domain.enums;

public enum TypeClient {

	PESSOAFISICA(1, "Pessoa fisica"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int cod;
	private String desc;
	
	private TypeClient(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static TypeClient toEnum(Integer cod) {
		if (cod== null) {
			return null;
		}
		
		for (TypeClient x : TypeClient.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalid");
	}
}
