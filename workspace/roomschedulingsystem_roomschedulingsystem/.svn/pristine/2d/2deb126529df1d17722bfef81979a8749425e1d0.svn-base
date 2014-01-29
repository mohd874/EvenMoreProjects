/*
 * To change (la la la) this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ae.hct.admc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Saeed
 */
@Entity
@Table(name="room")
public class Room extends BaseEntity {

    @Column(name="description")
    private String description;
    
    @Column(name="name")
    private String name;
    
    @Column(name="capacity")
    private Integer capacity;
    
    @JoinColumn(name = "room_type", referencedColumnName = "id", nullable = true)
    @ManyToOne
    private RoomType type;
    
    @ManyToOne
    private Block block;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    
    
}
