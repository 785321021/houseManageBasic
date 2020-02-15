//package com.coins.entity;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Table(name = "house_info")
//public class House implements Serializable {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String house_id;
//	@Column(name = "number")//编号
//	private Integer number;
//	@Column(name = "room")//卧室
//	private Integer room;
//	@Column(name = "parlour")//客厅
//	private Integer parlour;
//	@Column(name = "toilet")//卫生间
//	private Integer toilet;
//	
//	@Column(name = "kitchen")//厨房
//	private Integer kitchen;
//	@Column(name = "balcony")//阳台
//	private Integer balcony;
//	@Column(name = "age")//房龄
//	private Integer age;
//	@Column(name = "orientation")//朝向
//	private String orientation;
//	@Column(name = "area")//面积
//	private Float area;
//	
//	@Column(name = "use_area")//使用面积
//	private Float use_area;
//	@Column(name = "quoted_price")//报价
//	private Float quoted_price;
//	@Column(name = "base_price")//底价
//	private Float base_price;
//	@Column(name = "unit_price")//单价
//	private Float unit_price;
//	@Column(name = "pay_method")//付款方式
//	private String pay_method;
//	
//	@Column(name = "decorate")//装修
//	private String decorate;
//	@Column(name = "current_status")//目前状态
//	private String current_status;
//	@Column(name = "refer_house_time")//交房时间
//	private Date refer_house_time;
//	@Column(name = "entrust_time")//委托时间
//	private Date entrust_time;
//	@Column(name = "papers")//证件文件
//	private String papers;
//	
//	@Column(name = "visit_house")//看房方式
//	private String visit_house;
//	@Column(name = "key_num")//钥匙编号
//	private String key_num;
//	@Column(name = "house_source")//房屋来源
//	private Date house_source;
//	@Column(name = "is_rent")//是否可以出租
//	private Date is_rent;
//	@Column(name = "agent")//经纪人
//	private String agent;
//	@Column(name = "state")//状态
//	private String state;
//
//	private Owner owner;//业主
////	private Building building;//楼盘
//	
//	
//	public String getHouse_id() {
//		return house_id;
//	}
//	public String getState() {
//		return state;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
//	public String getAgent() {
//		return agent;
//	}
//	public void setAgent(String agent) {
//		this.agent = agent;
//	}
//	public void setHouse_id(String house_id) {
//		this.house_id = house_id;
//	}
//	public Integer getNumber() {
//		return number;
//	}
//	public void setNumber(Integer number) {
//		this.number = number;
//	}
//	public Integer getRoom() {
//		return room;
//	}
//	public void setRoom(Integer room) {
//		this.room = room;
//	}
//	public Integer getParlour() {
//		return parlour;
//	}
//	public void setParlour(Integer parlour) {
//		this.parlour = parlour;
//	}
//	public Integer getToilet() {
//		return toilet;
//	}
//	public void setToilet(Integer toilet) {
//		this.toilet = toilet;
//	}
//	public Integer getKitchen() {
//		return kitchen;
//	}
//	public void setKitchen(Integer kitchen) {
//		this.kitchen = kitchen;
//	}
//	public Integer getBalcony() {
//		return balcony;
//	}
//	public void setBalcony(Integer balcony) {
//		this.balcony = balcony;
//	}
//	public Integer getAge() {
//		return age;
//	}
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//	public String getOrientation() {
//		return orientation;
//	}
//	public void setOrientation(String orientation) {
//		this.orientation = orientation;
//	}
//	public Float getArea() {
//		return area;
//	}
//	public void setArea(Float area) {
//		this.area = area;
//	}
//	public Float getUse_area() {
//		return use_area;
//	}
//	public void setUse_area(Float use_area) {
//		this.use_area = use_area;
//	}
//	public Float getQuoted_price() {
//		return quoted_price;
//	}
//	public void setQuoted_price(Float quoted_price) {
//		this.quoted_price = quoted_price;
//	}
//	public Float getBase_price() {
//		return base_price;
//	}
//	public void setBase_price(Float base_price) {
//		this.base_price = base_price;
//	}
//	public Float getUnit_price() {
//		return unit_price;
//	}
//	public void setUnit_price(Float unit_price) {
//		this.unit_price = unit_price;
//	}
//	public String getPay_method() {
//		return pay_method;
//	}
//	public void setPay_method(String pay_method) {
//		this.pay_method = pay_method;
//	}
//	public String getDecorate() {
//		return decorate;
//	}
//	public void setDecorate(String decorate) {
//		this.decorate = decorate;
//	}
//	public String getCurrent_status() {
//		return current_status;
//	}
//	public void setCurrent_status(String current_status) {
//		this.current_status = current_status;
//	}
//	public Date getRefer_house_time() {
//		return refer_house_time;
//	}
//	public void setRefer_house_time(Date refer_house_time) {
//		this.refer_house_time = refer_house_time;
//	}
//	public Date getEntrust_time() {
//		return entrust_time;
//	}
//	public void setEntrust_time(Date entrust_time) {
//		this.entrust_time = entrust_time;
//	}
//	public String getPapers() {
//		return papers;
//	}
//	public void setPapers(String papers) {
//		this.papers = papers;
//	}
//	public String getVisit_house() {
//		return visit_house;
//	}
//	public void setVisit_house(String visit_house) {
//		this.visit_house = visit_house;
//	}
//	public String getKey_num() {
//		return key_num;
//	}
//	public void setKey_num(String key_num) {
//		this.key_num = key_num;
//	}
//	public Date getHouse_source() {
//		return house_source;
//	}
//	public void setHouse_source(Date house_source) {
//		this.house_source = house_source;
//	}
//	public Date getIs_rent() {
//		return is_rent;
//	}
//	public void setIs_rent(Date is_rent) {
//		this.is_rent = is_rent;
//	}
//	public Owner getOwner() {
//		return owner;
//	}
//	public void setOwner(Owner owner) {
//		this.owner = owner;
//	}
////	public Building getBuilding() {
////		return building;
////	}
////	public void setBuilding(Building building) {
////		this.building = building;
////	}
//	
//	
//
//	
//
//}
