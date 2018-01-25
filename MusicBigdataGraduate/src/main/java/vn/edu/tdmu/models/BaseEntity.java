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

     @Column(name = "created_by_user", nullable = true)
     @CreatedBy
     private String createdByUser;

     @Column(name = "modified_by_user", nullable = true)
     @LastModifiedBy
     private String modifiedByUser;

     @CreatedDate
     @Temporal(TemporalType.TIMESTAMP)
     protected Date creationTime;

     @LastModifiedDate
     @Temporal(TemporalType.TIMESTAMP)
     @Column(name = "modification_time")
     protected Date modificationTime;

     @Version
     @Column(nullable = true, insertable = false, columnDefinition = "INT DEFAULT 0")
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

     public Date getModificationTime(){
         return this.getModificationTime();
     }
 }