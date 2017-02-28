package com.asiainfo.domain.entity.user;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by eason on 2017/2/21.
 */
@MappedSuperclass
public class AbstractDomainClass implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id=id;
    }

    @Version
    private Integer version;
    private Date dateCreated;
    private Date lastUpdated;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        lastUpdated = new Date();
        if (dateCreated==null) {
            dateCreated = new Date();
        }
    }

}
