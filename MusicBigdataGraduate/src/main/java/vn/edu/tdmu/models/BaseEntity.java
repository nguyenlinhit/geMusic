package vn.edu.tdmu.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author NguyenLinh
 */

 @MappedSuperclass
 @EntityListeners(AuditingEntityListener.class)
 public abstract class BaseEntity implements Serializable{
     protected static final long serialVersionUID = 1L;

     @Column(name = "created_by_user")
     @CreatedBy
     private String createdByUser;

     @Column(name = "modified_by_user")
     @LastModifiedBy
     private String modifiedByUser;

     @CreatedDate
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "creation_time")
     private Date creationTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_time")
    private Date modificationTime;

    @Version
     @Column(insertable = false, columnDefinition = "INT DEFAULT 0")
     protected Integer version;

     public String getCreateByUser(){
         return this.createdByUser;
     }

     public String getModifiedByUser(){
         return this.modifiedByUser;
     }

     public Date getCreateTime(){
         return this.creationTime;
     }

    BaseEntity() {
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }
}