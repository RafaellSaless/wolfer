package wolfer;

import wolfer.enums_profissoes.Classes;

public class Players {
	private String name;
	private Classes role;
	private int status;
	
	
	public Players(String name, Classes role, int status) {
		super();
		this.name = name;
		this.role = role;
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public Classes getRole() {
		return role;
	}

	public void setRole(Classes role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "name=" + name + ", role=" + role + ", status=" + status;
	}

	
	

}
