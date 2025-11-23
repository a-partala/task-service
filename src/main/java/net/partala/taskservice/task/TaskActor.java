package net.partala.taskservice.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.partala.taskservice.auth.SecurityUser;
import net.partala.taskservice.user.UserRole;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
public class TaskActor {

    private final Long id;
    private final Set<UserRole> roles;

    public TaskActor(SecurityUser securityUser) {
        id = securityUser.getId();

        roles = securityUser.getRoles();
    }

    public static TaskActor of(SecurityUser securityUser) {
        return new TaskActor(securityUser);
    }

    public boolean isAdmin() {
        return roles.contains(UserRole.ADMIN);
    }

    public boolean isCreatorOf(TaskEntity task) {
        if(task.getCreatorId() == null) {
            return false;
        }
        return task.getCreatorId().equals(id);
    }

    public boolean isAssignedTo(TaskEntity task) {
        return task.getAssignedUserId() != null &&
                task.getAssignedUserId().equals(id);
    }

    public boolean canComplete(TaskEntity task) {

        if(task.getAssignedUserId() == null) {
            return false;
        }

        return isAssignedTo(task) || isCreatorOf(task) || isAdmin();
    }

    public boolean canStartTask(TaskEntity task) {

        return isAssignedTo(task) || isCreatorOf(task) || isAdmin();
    }

    public boolean canUpdate(TaskEntity task) {

        return isCreatorOf(task) || isAdmin();
    }

    public boolean canDelete(TaskEntity task) {

        return isCreatorOf(task) || isAdmin();
    }
}
