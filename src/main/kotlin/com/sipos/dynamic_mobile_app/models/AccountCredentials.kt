package com.sipos.dynamic_mobile_app.models

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Table(name = "account_credentials", uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("email"))])
class AccountCredentials : ParentModelObject(), UserDetails {
    private var password: String? = null
    private var email: String? = null

    @OneToOne
    @JoinColumn(name = "userDetails")
    var details: AccountDetails? = null

    fun getUUID(): UUID? {
        return this.uuid
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf()
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return email!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    @Override
    fun setPassword(password: String){
        this.password = password
    }
    @Override
    fun setUsername(email: String){
        this.email = email
    }
}