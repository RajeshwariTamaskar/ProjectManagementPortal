package one;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import one.ProjectList;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-12T21:18:42")
@StaticMetamodel(Media.class)
public class Media_ { 

    public static volatile SingularAttribute<Media, String> file_path;
    public static volatile SingularAttribute<Media, Integer> media_id;
    public static volatile SingularAttribute<Media, String> type;
    public static volatile SetAttribute<Media, ProjectList> projectSet;

}