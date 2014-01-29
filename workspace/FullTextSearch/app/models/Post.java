/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Model;
import play.modules.search.Field;
import play.modules.search.Indexed;

/**
 *
 * @author m.alhabshi
 */
@Indexed
@Entity
public class Post extends Model{

    @Field
    public String book;
    @Field
    public String author;

    public Post() {
    }

    public Post(String text, String author) {
        this.book = text;
        this.author = author;
    }


}
