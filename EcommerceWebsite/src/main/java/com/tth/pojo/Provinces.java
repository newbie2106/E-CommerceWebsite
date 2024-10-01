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
@Table(name = "provinces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provinces.findAll", query = "SELECT p FROM Provinces p"),
    @NamedQuery(name = "Provinces.findByCode", query = "SELECT p FROM Provinces p WHERE p.code = :code"),
    @NamedQuery(name = "Provinces.findByFullName", query = "SELECT p FROM Provinces p WHERE p.fullName = :fullName"),
    @NamedQuery(name = "Provinces.findByAdministrativeUnitId", query = "SELECT p FROM Provinces p WHERE p.administrativeUnitId = :administrativeUnitId")})
public class Provinces implements Serializable {

    @OneToMany(mappedBy = "provinceCode")
    @JsonIgnore
    private Set<ShippingAddress> shippingAddressSet;

    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private AdministrativeUnits administrativeUnitId;
    @JsonIgnore
    @OneToMany(mappedBy = "provinceCode")
    private Set<Districts> districtsSet;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "full_name")
    private String fullName;
    @JoinColumn(name = "administrative_region_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private AdministrativeRegions administrativeRegionId;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "provinceId")
    @JsonIgnore
    private Set<Admin> adminSet;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "provinceId")
    @JsonIgnore
    private Set<Branch> branchSet;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "provinceId")
    @JsonIgnore
    private Set<Customer> customerSet;

    public Provinces() {
    }

    public Provinces(String code) {
        this.code = code;
    }

    public Provinces(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
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


    public AdministrativeRegions getAdministrativeRegionId() {
        return administrativeRegionId;
    }

    public void setAdministrativeRegionId(AdministrativeRegions administrativeRegionId) {
        this.administrativeRegionId = administrativeRegionId;
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
        if (!(object instanceof Provinces)) {
            return false;
        }
        Provinces other = (Provinces) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.Provinces[ code=" + code + " ]";
    }

    public AdministrativeUnits getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(AdministrativeUnits administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
    }

    @XmlTransient
    public Set<Districts> getDistrictsSet() {
        return districtsSet;
    }

    public void setDistrictsSet(Set<Districts> districtsSet) {
        this.districtsSet = districtsSet;
    }

    @XmlTransient
    public Set<ShippingAddress> getShippingAddressSet() {
        return shippingAddressSet;
    }

    public void setShippingAddressSet(Set<ShippingAddress> shippingAddressSet) {
        this.shippingAddressSet = shippingAddressSet;
    }
    
}
