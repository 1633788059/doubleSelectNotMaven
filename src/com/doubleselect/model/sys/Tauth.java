package com.doubleselect.model.sys;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Tauth")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="checkAuth") 
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tauth implements java.io.Serializable {

	private int id;
	private String url;
	private String auth_name;
	private Tauth tauth;
	
	private Set<Tauth> tauths = new HashSet<Tauth>(0);
	private Set<Trole> troles = new HashSet<Trole>(0);
	
	public Tauth() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "id",nullable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name = "url", length = 100)
	public String getUrl() {
		return url;
	}

	
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "auth_name", length = 20)
	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	
/*	
	@ManyToOne表示一个多对一的映射,该注解标注的属性通常是数据库表的外键

	optional:是否允许该字段为null,该属性应该根据数据库表的外键约束来确定,默认为true

	fetch:表示抓取策略,默认为FetchType.EAGER

	cascade:表示默认的级联操作策略,可以指定为ALL,PERSIST,MERGE,REFRESH和REMOVE中的若
	干组合,默认为无级联操作

	targetEntity:表示该属性关联的实体类型.该属性通常不必指定,ORM框架根据属性类型自动判
	断targetEntity.
	
	*   CRUD即create(创建)、Read/Retrieve(读取)、Update(更新)和Delete(删除) 
   Cascade用于设置CUD 
   Cascade．all在所有情况下级联。 
    Cascade.MERGE合并　save update 
   Cascade.PERSIST保存 
    Cascade.REFRESH刷新 
    Cascade.REMOVE删除 
    Fetch用于设置R 
    Fetch.EAGER主动的积极 
    Fetch.LAZY懒 

	*
	*/
	
	/*默认Fetch为EAGER 当查询的时候会把该user所在的小组也查询出来 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Tauth getTauth() {
		return tauth;
	}

	public void setTauth(Tauth tauth) {
		this.tauth = tauth;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trole_tauth", joinColumns = { @JoinColumn(name = "tauth_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "trole_id", nullable = false, updatable = false) })
	public Set<Trole> getTroles() {
		return troles;
	}

	public void setTroles(Set<Trole> troles) {
		this.troles = troles;
	}
	
	/*@OneToMany描述一个一对多的关联,该属性应该为集体类型,在数据库中并没有实际字段.

	fetch:表示抓取策略,默认为FetchType.LAZY,因为关联的多个对象通常不必从数据库预先读取
	到内存

	cascade:表示级联操作策略,对于OneToMany类型的关联非常重要,通常该实体更新或删除时,其
	关联的实体也应当被更新或删除
	# targetEntity属性表示默认关联的实体类型，默认为当前标注的实体类，为Tauth类；
	*/
	//OneToMany  默认fetch为LAZY  当查询小组时，不会从user表里查询出该小组的所有组员，
	//当group.getUsers().get(0).getName()此时ORM框架会向数据库发出查询语句  
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tauth")
	public Set<Tauth> getTauths() {
		return tauths;
	}

	public void setTauths(Set<Tauth> tauths) {
		this.tauths = tauths;
	}



}
