package com.ues21

import com.ues21.utils.StringUtils

import com.ues21.enums.UserRoleEnum

class User {

	Person person
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	Set passwords = []
	Set roles = []

	static hasMany = [passwords: UserPassword, roles: Role]

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		roles lazy: false
		person lazy: false
		passwords lazy: false
	}

	public transient boolean isStudent() {
		return roles.collect { it.authority == UserRoleEnum.STUDENT.role()}.contains(true)
	}

	public transient boolean isSecretary() {
		return roles.collect { it.authority == UserRoleEnum.SECRETARY.role()}.contains(true)
	}

	public transient boolean isDirector() {
		return roles.collect { it.authority == UserRoleEnum.DIRECTOR.role()}.contains(true)
	}

	public transient boolean isTeacher() {
		return roles.collect { it.authority == UserRoleEnum.TEACHER.role()}.contains(true)
	}
}
