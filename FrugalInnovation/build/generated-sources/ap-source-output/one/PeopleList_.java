package one;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import one.ProjectList;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-06-12T21:18:42")
@StaticMetamodel(PeopleList.class)
public class PeopleList_ { 

    public static volatile SingularAttribute<PeopleList, String> first_name;
    public static volatile SingularAttribute<PeopleList, Integer> people_id;
    public static volatile SingularAttribute<PeopleList, String> company_name;
    public static volatile SingularAttribute<PeopleList, String> last_name;
    public static volatile SingularAttribute<PeopleList, String> email_address;
    public static volatile SetAttribute<PeopleList, ProjectList> projectSet;

}