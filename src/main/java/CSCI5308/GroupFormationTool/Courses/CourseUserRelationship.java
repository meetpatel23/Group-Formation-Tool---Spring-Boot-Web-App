package CSCI5308.GroupFormationTool.Courses;

import java.util.List;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.SystemConfig;

public class CourseUserRelationship implements ICourseUserRelationship
{
	public boolean userHasRoleInCourse(IUser user, Role role, ICourse course)
	{
		if (null == user || !user.isValidUser())
		{
			return false;
		}
		if (null == role)
		{
			return false;
		}
		if (null == course)
		{
			return false;
		}
		ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		if (null != roles && roles.contains(role))
		{
			return true;
		}
		return false;
	}

	public List<Role> loadAllRoluesForUserInCourse(IUser user, ICourse course)
	{
		ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
		List<Role> roles = userCourseRelationshipDB.loadUserRolesForCourse(course, user);
		return roles;
	}

	public boolean enrollUserInCourse(IUser user, ICourse course, Role role)
	{
		ICourseUserRelationshipPersistence userCourseRelationshipDB = SystemConfig.instance().getCourseUserRelationshipDB();
		return userCourseRelationshipDB.enrollUser(course, user, role);
	}
}
