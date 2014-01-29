/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.hct.admc.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name = "block")
public class Block extends BaseEntity {

    @Column(name = "description")
    private String description;
    
    @Column(name = "name")
    private String name;
    
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "block", fetch = FetchType.LAZY)
    private Set<Room> rooms;

    public Block(){
        rooms = new HashSet<Room>();
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}
