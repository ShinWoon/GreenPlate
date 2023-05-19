package com.ssafy.greenplate.model.dto;

public class Product {
	private Integer id;
	private String name;
	private String englishName;
	private String type;
	private String discription;
	private Integer price;
	private String img;

	public Product(Integer id, String name, String englishName, String type, String discription, Integer price,
			String img) {
		this.id = id;
		this.name = name;
		this.englishName = englishName;
		this.type = type;
		this.discription = discription;
		this.price = price;
		this.img = img;
	}

	public Product(String name, String englishName, String type, String discription, Integer price, String img) {
		this.name = name;
		this.englishName = englishName;
		this.type = type;
		this.discription = discription;
		this.price = price;
		this.img = img;
	}

	public Product() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", englishName=" + englishName + ", type=" + type
				+ ", discription=" + discription + ", price="
				+ price + ", img=" + img + "]";
	}

}
