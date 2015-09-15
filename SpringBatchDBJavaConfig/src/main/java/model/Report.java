package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
public class Report {
	private String name;
	private String sex;
	private String age;
	private String birthday;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {this.birthday = birthday;}
}