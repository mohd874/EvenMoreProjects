package controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Post;
import play.modules.search.Search;
import play.modules.search.Search.Query;
import play.mvc.*;

public class Application extends Controller {

    public static void index() {
        render();
    }

    public static void search(String search) {
    	Query query;
    	String highlight;
        
    	if (search.contains(":")) {
    		query = Search.search(search, Post.class);
    		highlight = search.split(":", 2)[1];
    	} else {
    		query = Search.search("book:"+search+"*", Post.class);
    		highlight = search;
    	}
    	List results = query.fetch();
    	render(results, search, highlight);
    }

    public static void show(long id,String highlight) {
        System.out.println("ID: "+id);
        Post t = Post.findById(id);
        if(t==null)t=new Post("null", "null");
        render(t,highlight);
    }
}