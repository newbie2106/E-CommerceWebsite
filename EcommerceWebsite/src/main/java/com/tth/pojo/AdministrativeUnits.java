/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "administrative_units")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministrativeUnits.findAll", query = "SELECT a FROM AdministrativeUnits a"),
    @NamedQuery(name = "AdministrativeUnits.findById", query = "SELECT a FROM AdministrativeUnits a WHERE a.id = :id"),
    @NamedQuery(name = "AdministrativeUnits.findByFullName", query = "SELECT a FROM AdministrativeUnits a WHERE a.fullName = :fullName"),
    @NamedQuery(name = "AdministrativeUnits.findByFullNameEn", query = "SELECT a FROM AdministrativeUnits a WHERE a.fullNameEn = :fullNameEn"),
    @NamedQuery(name = "AdministrativeUnits.findByShortName", query = "SELECT a FROM AdministrativeUnits a WHERE a.shortName = :shortName"),
    @NamedQuery(name = "AdministrativeUnits.findByShortNameEn", query = "SELECT a FROM AdministrativeUnits a WHERE a.shortNameEn = :shortNameEn"),
    @NamedQuery(name = "AdministrativeUnits.findByCodeName", query = "SELECT a FROM AdministrativeUnits a WHERE a.codeName = :codeName"),
    @NamedQuery(name = "AdministrativeUnits.findByCodeNameEn", query = "SELECT a FROM AdministrativeUnits a WHERE a.codeNameEn = :codeNameEn")})
public class AdministrativeUnits implements Serializable {

    @OneToMany(mappedBy = "administrativeUnitId")
    @JsonIgnore
    private Set<Provinces> provincesSet;
    @OneToMany(mappedBy = "administrativeUnitId")
    @JsonIgnore
    private Set<Wards> wardsSet;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;
    @Size(max = 255)
    @Column(name = "full_name_en")
    private String fullNameEn;
    @Size(max = 255)
    @Column(name = "short_name")
    private String shortName;
    @Size(max = 255)
    @Column(name = "short_name_en")
    private String shortNameEn;
    @Size(max = 255)
    @Column(name = "code_name")
    private String codeName;
    @Size(max = 255)
    @Column(name = "code_name_en")
    private String codeNameEn;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "administrativeUnitId")
    private Set<Districts> districtsSet;

    public AdministrativeUnits() {
    }

    public AdministrativeUnits(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullNameEn() {
        return fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortNameEn() {
        return shortNameEn;
    }

    public void setShortNameEn(String shortNameEn) {
        this.shortNameEn = shortNameEn;
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
    public Set<Districts> getDistrictsSet() {
        return districtsSet;
    }

    public void setDistrictsSet(Set<Districts> districtsSet) {
        this.districtsSet = districtsSet;
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
        if (!(object instanceof AdministrativeUnits)) {
            return false;
        }
        AdministrativeUnits other = (AdministrativeUnits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.AdministrativeUnits[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<Provinces> getProvincesSet() {
        return provincesSet;
    }

    public void setProvincesSet(Set<Provinces> provincesSet) {
        this.provincesSet = provincesSet;
    }

    @XmlTransient
    public Set<Wards> getWardsSet() {
        return wardsSet;
    }

    public void setWardsSet(Set<Wards> wardsSet) {
        this.wardsSet = wardsSet;
    }
    
}
