/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tongh
 */
@Entity
@Table(name = "administrative_regions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministrativeRegions.findAll", query = "SELECT a FROM AdministrativeRegions a"),
    @NamedQuery(name = "AdministrativeRegions.findById", query = "SELECT a FROM AdministrativeRegions a WHERE a.id = :id"),
    @NamedQuery(name = "AdministrativeRegions.findByName", query = "SELECT a FROM AdministrativeRegions a WHERE a.name = :name"),
    @NamedQuery(name = "AdministrativeRegions.findByNameEn", query = "SELECT a FROM AdministrativeRegions a WHERE a.nameEn = :nameEn"),
    @NamedQuery(name = "AdministrativeRegions.findByCodeName", query = "SELECT a FROM AdministrativeRegions a WHERE a.codeName = :codeName"),
    @NamedQuery(name = "AdministrativeRegions.findByCodeNameEn", query = "SELECT a FROM AdministrativeRegions a WHERE a.codeNameEn = :codeNameEn")})
public class AdministrativeRegions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name_en")
    private String nameEn;
    @Size(max = 255)
    @Column(name = "code_name")
    private String codeName;
    @Size(max = 255)
    @Column(name = "code_name_en")
    private String codeNameEn;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "administrativeRegionId")
    private Set<Provinces> provincesSet;

    public AdministrativeRegions() {
    }

    public AdministrativeRegions(Integer id) {
        this.id = id;
    }

    public AdministrativeRegions(Integer id, String name, String nameEn) {
        this.id = id;
        this.name = name;
        this.nameEn = nameEn;
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeNameEn() {
        return codeNameEn;
    }

    public void setCodeNameEn(String codeNameEn) {
        this.codeNameEn = codeNameEn;
    }

    @XmlTransient
    public Set<Provinces> getProvincesSet() {
        return provincesSet;
    }

    public void setProvincesSet(Set<Provinces> provincesSet) {
        this.provincesSet = provincesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministrativeRegions)) {
            return false;
        }
        AdministrativeRegions other = (AdministrativeRegions) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.AdministrativeRegions[ id=" + id + " ]";
    }
    
}
