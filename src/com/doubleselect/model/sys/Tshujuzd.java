package com.doubleselect.model.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tshujuzd entity. @author MyEclipse Persistence Tools
 */


/*2、@Table(name="",catalog="",schema="")

可选,通常和@Entity配合使用,只能标注在实体的class定义处,表示实体对应的数据库表的信息

name:可选,表示表的名称.默认地,表名和实体名称一致,只有在不一致的情况下才需要指定表名

catalog(目录):可选,表示Catalog名称,默认为Catalog("").

schema(概要):可选,表示Schema名称,默认为Schema("").*/

@Entity
@Table(name = "tshujuzd")
public class Tshujuzd implements java.io.Serializable {

	/**
	 * 唯一标志
	 */
	private Integer id;
	/**
	 * 字典类别
	 */
	private String zdlb;
	/**
	 * 字典代码
	 */
	private String zddm;
	/**
	 * 字典描述
	 */
	private String zdms;


	/** default constructor */
	public Tshujuzd() {
	}

	/** full constructor */
	public Tshujuzd(String zdlb, String zddm, String zdms) {
		this.zdlb = zdlb;
		this.zddm = zddm;
		this.zdms = zdms;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "zdlb", length = 20)
	public String getZdlb() {
		return this.zdlb;
	}

	public void setZdlb(String zdlb) {
		this.zdlb = zdlb;
	}

	@Column(name = "zddm", length = 20)
	public String getZddm() {
		return this.zddm;
	}

	public void setZddm(String zddm) {
		this.zddm = zddm;
	}

	@Column(name = "zdms", length = 30)
	public String getZdms() {
		return this.zdms;
	}

	public void setZdms(String zdms) {
		this.zdms = zdms;
	}
}