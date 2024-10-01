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
@Table(name = "districts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Districts.findAll", query = "SELECT d FROM Districts d"),
    @NamedQuery(name = "Districts.findByCode", query = "SELECT d FROM Districts d WHERE d.code = :code"),
    @NamedQuery(name = "Districts.findByFullName", query = "SELECT d FROM Districts d WHERE d.fullName = :fullName"),
    @NamedQuery(name = "Districts.findByProvinceCode", query = "SELECT d FROM Districts d WHERE d.provinceCode = :provinceCode")})
public class Districts implements Serializable {

    @OneToMany(mappedBy = "districtCode")
    @JsonIgnore
    private Set<ShippingAddress> shippingAddressSet;

    @JoinColumn(name = "province_code", referencedColumnName = "code")
    @ManyToOne
    @JsonIgnore
    private Provinces provinceCode;
    @OneToMany(mappedBy = "districtCode")
    @JsonIgnore
    private Set<Wards> wardsSet;

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
    @JoinColumn(name = "administrative_unit_id", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private AdministrativeUnits administrativeUnitId;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "districtId")
    @JsonIgnore
    private Set<Admin> adminSet;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "districtId")
    @JsonIgnore
    private Set<Branch> branchSet;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "districtId")
    @JsonIgnore
    private Set<Customer> customerSet;

    public Districts() {
    }

    public Districts(String code) {
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

    public AdministrativeUnits getAdministrativeUnitId() {
        return administrativeUnitId;
    }

    public void setAdministrativeUnitId(AdministrativeUnits administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
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
        if (!(object instanceof Districts)) {
            return false;
        }
        Districts other = (Districts) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.Districts[ code=" + code + " ]";
    }

    public Provinces getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Provinces provinceCode) {
        this.provinceCode = provinceCode;
    }

    @XmlTransient
    public Set<Wards> getWardsSet() {
        return wardsSet;
    }

    public void setWardsSet(Set<Wards> wardsSet) {
        this.wardsSet = wardsSet;
    }

    @XmlTransient
    public Set<ShippingAddress> getShippingAddressSet() {
        return shippingAddressSet;
    }

    public void setShippingAddressSet(Set<ShippingAddress> shippingAddressSet) {
        this.shippingAddressSet = shippingAddressSet;
    }

}
