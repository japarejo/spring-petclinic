package org.springframework.samples.learning;




public class Trade {
    private long id = 0;
    private String name = "";
    private String desc = "";

    public Trade(long id) {
        this.id = id;
    }

	public Trade(String name, String desc) {
        this.name = name;
        this.desc = desc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	

}
