/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Saeed
 */

@MappedSuperclass
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BaseEntity implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date",nullable=false)
    private Date createDate;
    
    @JoinColumn(name = "create_user", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private User createUser;
    
    @Column(name="record_status")
    @Enumerated(EnumType.STRING)
    private RecordStatus recordStatus;

    public BaseEntity() {
        createDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
    
    public enum RecordStatus{
        Active,Deleted
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        BaseEntity other = (BaseEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }
    
}
