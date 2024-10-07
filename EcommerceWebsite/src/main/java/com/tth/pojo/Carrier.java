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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tongh
 */
@Entity
@Table(name = "carrier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrier.findAll", query = "SELECT c FROM Carrier c"),
    @NamedQuery(name = "Carrier.findById", query = "SELECT c FROM Carrier c WHERE c.id = :id"),
    @NamedQuery(name = "Carrier.findByName", query = "SELECT c FROM Carrier c WHERE c.name = :name")})
public class Carrier implements Serializable {

    @OneToMany(mappedBy = "carrierId")
    private Set<SaleOrder> saleOrderSet;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;

    public Carrier() {
    }

    public Carrier(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Carrier)) {
            return false;
        }
        Carrier other = (Carrier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.Carrier[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<SaleOrder> getSaleOrderSet() {
        return saleOrderSet;
    }

    public void setSaleOrderSet(Set<SaleOrder> saleOrderSet) {
        this.saleOrderSet = saleOrderSet;
    }
    
}
