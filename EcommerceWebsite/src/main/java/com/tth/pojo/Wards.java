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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "wards")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wards.findAll", query = "SELECT w FROM Wards w"),
    @NamedQuery(name = "Wards.findByCode", query = "SELECT w FROM Wards w WHERE w.code = :code"),
    @NamedQuery(name = "Wards.findByFullName", query = "SELECT w FROM Wards w WHERE w.fullName = :fullName"),
    @NamedQuery(name = "Wards.findByDistrictCode", query = "SELECT w FROM Wards w WHERE w.districtCode = :districtCode"),
    @NamedQuery(name = "Wards.findByAdministrativeUnitId", query = "SELECT w FROM Wards w WHERE w.administrativeUnitId = :administrativeUnitId")})
public class Wards implements Serializable {

    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private AdministrativeUnits administrativeUnitId;
    @JoinColumn(name = "district_code", referencedColumnName = "code")
    @JsonIgnore
    @ManyToOne
    private Districts districtCode;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "wardId")
    private Set<Admin> adminSet;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "wardId")
    @JsonIgnore
    private Set<Branch> branchSet;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "wardId")
    @JsonIgnore
    private Set<Customer> customerSet;

    public Wards() {
    }

    public Wards(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlTransient
    public Set<Admin> getAdminSet() {
        return adminSet;
    }

    public void setAdminSet(Set<Admin> adminSet) {
        this.adminSet = adminSet;
    }

    @XmlTransient
    public Set<Branch> getBranchSet() {
        return branchSet;
    }

    public void setBranchSet(Set<Branch> branchSet) {
        this.branchSet = branchSet;
    }

    @XmlTransient
    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wards)) {
            return false;
        }
        Wards other = (Wards) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.Wards[ code=" + code + " ]";
    }

    public AdministrativeUnits getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(AdministrativeUnits administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
    }

    public Districts getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Districts districtCode) {
        this.districtCode = districtCode;
    }

}
