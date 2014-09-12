package one;

import javax.persistence.*;

import java.util.*;

public class MediaService {
	 private EntityManager manager;
         /*Constructor*/
	 public MediaService(EntityManager manager) {
            this.manager = manager;
	 }

    // method to create a new record
     public Media createMedia(String type, String filePath) {
    	Media media = new Media();
 	    media.setType(type);
 	    media.setFile_path(filePath);
 	    manager.persist(media);
 	    return media;
     }

    // method to read a record
     public Media readMedia(int media_id) {
        Media media = manager.find(Media.class, media_id);
    	return media;
     }

     // method to read all records
     public List<Media> readAll() {
    	 TypedQuery<Media> query = manager.createQuery("SELECT e FROM MEDIA e", Media.class);
    	 List<Media> result =  query.getResultList();
    	 return result;
     }

    // method to update a record
     public Media updateMedia(int id, String type, String fpath) {
    	 Media media = manager.find(Media.class, id);
    	 if (media != null) {
    		 media.setType(type);
    		 media.setFile_path(fpath);
    	 }
    	 return media;
     }
     // method to delete a record
    public void deleteMedia(int id) {
    	 Media media = manager.find(Media.class, id);
    	 if (media != null) {
    		 manager.remove(media);
    	 }
    }
    
}
