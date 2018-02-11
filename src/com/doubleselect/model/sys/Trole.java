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
@Table(name = "trole")

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="checkAuth") 
@DynamicInsert(true)
@DynamicUpdate(true)
public class Trole implements java.io.Serializable {

	private int id;
	private String name;
	
	private Set<Tauth> tauths = new HashSet<Tauth>(0);
	private Set<Tuser> tusers = new HashSet<Tuser>(0);

	public Trole() {
	}

	public Trole(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Trole(int id, String name,Set<Tauth> tauths, Set<Tuser> tusers) {
		this.id = id;
		this.name = name;
		this.tauths = tauths;
		this.tusers = tusers;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name = "id",nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "trole_tauth", joinColumns = { @JoinColumn(name = "trole_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tauth_id", nullable = false, updatable = false) })
	public Set<Tauth> getTauths() {
		return this.tauths;
	}

	public void setTauths(Set<Tauth> tauths) {
		this.tauths = tauths;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tuser_trole", joinColumns = { @JoinColumn(name = "trole_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tuser_id", nullable = false, updatable = false) })
	public Set<Tuser> getTusers() {
		return this.tusers;
	}

	public void setTusers(Set<Tuser> tusers) {
		this.tusers = tusers;
	}

}
