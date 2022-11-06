package com.taskmanagementsystem.core;

/**
 * This enum class helps provide the member state, which
 * can be either Inactive or Active. This approach allows us
 * to manage the members; if they are inactive,
 * the Admin won't be able to assign a task.
 */
public enum UserState {
    INACTIVE, ACTIVE
}
