/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tongh
 */
@Entity
@Table(name = "google_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GoogleAccount.findAll", query = "SELECT g FROM GoogleAccount g"),
    @NamedQuery(name = "GoogleAccount.findById", query = "SELECT g FROM GoogleAccount g WHERE g.id = :id"),
    @NamedQuery(name = "GoogleAccount.findByGoogleId", query = "SELECT g FROM GoogleAccount g WHERE g.googleId = :googleId"),
    @NamedQuery(name = "GoogleAccount.findByName", query = "SELECT g FROM GoogleAccount g WHERE g.name = :name"),
    @NamedQuery(name = "GoogleAccount.findByEmail", query = "SELECT g FROM GoogleAccount g WHERE g.email = :email"),
    @NamedQuery(name = "GoogleAccount.findByPictureUrl", query = "SELECT g FROM GoogleAccount g WHERE g.pictureUrl = :pictureUrl")})
public class GoogleAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "google_id")
    private String googleId;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "pictureUrl")
    private String pictureUrl;

    public GoogleAccount() {
    }

    public GoogleAccount(Integer id) {
        this.id = id;
    }

    public GoogleAccount(String googleId, String name, String email, String pictureUrl) {
        this.googleId = googleId;
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public GoogleAccount(Integer id, String email, String pictureUrl) {
        this.id = id;
        this.email = email;
        this.pictureUrl = pictureUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
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
        if (!(object instanceof GoogleAccount)) {
            return false;
        }
        GoogleAccount other = (GoogleAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tth.pojo.GoogleAccount[ id=" + id + " ]";
    }
    
}
