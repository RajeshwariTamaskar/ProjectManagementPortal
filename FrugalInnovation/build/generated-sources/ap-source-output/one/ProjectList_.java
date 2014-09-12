package one;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import one.Media;
import one.PeopleList;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-12T21:18:42")
@StaticMetamodel(ProjectList.class)
public class ProjectList_ { 

    public static volatile SetAttribute<ProjectList, Media> mediaSet;
    public static volatile SingularAttribute<ProjectList, Date> startDate;
    public static volatile SingularAttribute<ProjectList, String> result;
    public static volatile SingularAttribute<ProjectList, Integer> projectID;
    public static volatile SingularAttribute<ProjectList, String> category;
    public static volatile SingularAttribute<ProjectList, String> status;
    public static volatile SingularAttribute<ProjectList, String> name;
    public static volatile SingularAttribute<ProjectList, Date> endDate;
    public static volatile SetAttribute<ProjectList, PeopleList> peopleSet;

}