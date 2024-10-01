/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tongh
 */
@Entity
@Table(name = "shipping_address")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShippingAddress.findAll", query = "SELECT s FROM ShippingAddress s"),
    @NamedQuery(name = "ShippingAddress.findById", query = "SELECT s FROM ShippingAddress s WHERE s.id = :id"),
    @NamedQuery(name = "ShippingAddress.findByFullName", query = "SELECT s FROM ShippingAddress s WHERE s.fullName = :fullName"),
    @NamedQuery(name = "ShippingAddress.findByPhone", query = "SELECT s FROM ShippingAddress s WHERE s.phone = :phone"),
    @NamedQuery(name = "ShippingAddress.findByAddress", query = "SELECT s FROM ShippingAddress s WHERE s.address = :address")})
public class ShippingAddress implements Serializable {

    @JoinColumn(name = "district_code", referencedColumnName = "code")
    @ManyToOne
    private Districts districtCode;
    @JoinColumn(name = "province_code", referencedColumnName = "code")
    @ManyToOne
    private Provinces provinceCode;
    @JoinColumn(name = "ward_code", referencedColumnName = "code")
    @ManyToOne
    private Wards wardCode;
    @OneToMany(mappedBy = "shippingAddress")
    @JsonIgnore
    private List<SaleOrder> saleOrders;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "full_name")
    private String fullName;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "phone_number")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "is_default")
    private Boolean isDefault;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Customer username;

    public ShippingAddress(Districts districtCode, Provinces provinceCode, Wards wardCode, String fullName, String phone, String address, Boolean isDefault, Date createdAt, Date updatedAt, Customer username) {
        this.districtCode = districtCode;
        this.provinceCode = provinceCode;
        this.wardCode = wardCode;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.isDefault = isDefault;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
    }

    
    
    public List<SaleOrder> getSaleOrders() {
        return saleOrders;
    }

    public void setSaleOrders(List<SaleOrder> saleOrders) {
        this.saleOrders = saleOrders;
    }

    public ShippingAddress() {
    }

    public ShippingAddress(Long id) {
        this.id = id;
    }

    public ShippingAddress(Long id, String fullName, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Customer getUsername() {
        return username;
    }

    public void setUsername(Customer username) {
        this.username = username;
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
        if (!(object instanceof ShippingAddress)) {
            return false;
        }
        ShippingAddress other = (ShippingAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.ShippingAddress[ id=" + id + " ]";
    }

    public Districts getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Districts districtCode) {
        this.districtCode = districtCode;
    }

    public Provinces getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Provinces provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Wards getWardCode() {
        return wardCode;
    }

    public void setWardCode(Wards wardCode) {
        this.wardCode = wardCode;
    }

}
