package com.icaviation

class User {

	transient springSecurityService
    def saltSource

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

    Date dateCreated

    static hasMany = [responses: Response]

	static constraints = {
		username blank: false, unique: true, email: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

    protected void encodePassword() {
        def salt = saltSource.systemWideSalt
        password = springSecurityService.encodePassword(password, salt)
    }
}
