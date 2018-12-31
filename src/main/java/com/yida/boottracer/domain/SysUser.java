package com.yida.boottracer.domain;
// Generated 2018-11-25 20:43:13 by Hibernate Tools 5.2.10.Final

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * SysUser generated by hbm2java
 */
@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SysUser implements java.io.Serializable, UserDetails
{

	private String userId;
	private String userName;
	private String userNameCn;
	private String tel;
	private String email;
	private String qq;
	private String webChat;
	private boolean isAnonymous;
	private String password;
	private Integer passwordFormat;
	private String passwordSalt;
	private String passwordQuestion;
	private String passwordAnswer;
	private boolean isApproved;
	private boolean isLockedOut;
	private Date createDate;
	private Date lastLoginDate;
	private Date lastPasswordChangedDate;
	private Date lastLockoutDate;
	private int failedPasswordAttemptCount;
	private Date failedPasswordAttemptWindowStart;
	private int failedPasswordAnswerAttemptCount;
	private Date failedPasswordAnswerAttemptWindowStart;
	private String comment;
	private Set<SysRole> roles = new HashSet<>();
	private SysMember sysMember;
	private Boolean isMemberAdmin;

	private Collection<? extends GrantedAuthority> authorities = new HashSet<>();

	public SysUser()
	{
	}

	
	@Id
	@Column(name = "UserId", unique = true, nullable = false, length = 64)
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	@Column(name = "UserName", nullable = false, length = 256)
	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	@Column(name = "UserNameCN", nullable = false, length = 256)
	public String getUserNameCn()
	{
		return this.userNameCn;
	}

	public void setUserNameCn(String userNameCn)
	{
		this.userNameCn = userNameCn;
	}

	@Column(name = "Tel", length = 256)
	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	@Column(name = "Email", length = 256)
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "QQ", length = 256)
	public String getQq()
	{
		return this.qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	@Column(name = "WebChat", length = 256)
	public String getWebChat()
	{
		return this.webChat;
	}

	public void setWebChat(String webChat)
	{
		this.webChat = webChat;
	}

	@Column(name = "IsAnonymous", nullable = false)
	public boolean isIsAnonymous()
	{
		return this.isAnonymous;
	}

	public void setIsAnonymous(boolean isAnonymous)
	{
		this.isAnonymous = isAnonymous;
	}

	@Column(name = "Password", nullable = false, length = 128)
	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Column(name = "PasswordFormat")
	public Integer getPasswordFormat()
	{
		return this.passwordFormat;
	}

	public void setPasswordFormat(Integer passwordFormat)
	{
		this.passwordFormat = passwordFormat;
	}

	@Column(name = "PasswordSalt", length = 128)
	public String getPasswordSalt()
	{
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt)
	{
		this.passwordSalt = passwordSalt;
	}

	@Column(name = "PasswordQuestion", length = 256)
	public String getPasswordQuestion()
	{
		return this.passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion)
	{
		this.passwordQuestion = passwordQuestion;
	}

	@Column(name = "PasswordAnswer", length = 128)
	public String getPasswordAnswer()
	{
		return this.passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer)
	{
		this.passwordAnswer = passwordAnswer;
	}

	@Column(name = "IsApproved", nullable = false)
	public boolean isIsApproved()
	{
		return this.isApproved;
	}

	public void setIsApproved(boolean isApproved)
	{
		this.isApproved = isApproved;
	}

	@Column(name = "IsLockedOut", nullable = false)
	public boolean isIsLockedOut()
	{
		return this.isLockedOut;
	}

	public void setIsLockedOut(boolean isLockedOut)
	{
		this.isLockedOut = isLockedOut;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate", nullable = false, length = 26)
	public Date getCreateDate()
	{
		return this.createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastLoginDate", nullable = false, length = 26)
	public Date getLastLoginDate()
	{
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate)
	{
		this.lastLoginDate = lastLoginDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastPasswordChangedDate", nullable = false, length = 26)
	public Date getLastPasswordChangedDate()
	{
		return this.lastPasswordChangedDate;
	}

	public void setLastPasswordChangedDate(Date lastPasswordChangedDate)
	{
		this.lastPasswordChangedDate = lastPasswordChangedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastLockoutDate", nullable = false, length = 26)
	public Date getLastLockoutDate()
	{
		return this.lastLockoutDate;
	}

	public void setLastLockoutDate(Date lastLockoutDate)
	{
		this.lastLockoutDate = lastLockoutDate;
	}

	@Column(name = "FailedPasswordAttemptCount", nullable = false)
	public int getFailedPasswordAttemptCount()
	{
		return this.failedPasswordAttemptCount;
	}

	public void setFailedPasswordAttemptCount(int failedPasswordAttemptCount)
	{
		this.failedPasswordAttemptCount = failedPasswordAttemptCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FailedPasswordAttemptWindowStart", nullable = false, length = 26)
	public Date getFailedPasswordAttemptWindowStart()
	{
		return this.failedPasswordAttemptWindowStart;
	}

	public void setFailedPasswordAttemptWindowStart(Date failedPasswordAttemptWindowStart)
	{
		this.failedPasswordAttemptWindowStart = failedPasswordAttemptWindowStart;
	}

	@Column(name = "FailedPasswordAnswerAttemptCount", nullable = false)
	public int getFailedPasswordAnswerAttemptCount()
	{
		return this.failedPasswordAnswerAttemptCount;
	}

	public void setFailedPasswordAnswerAttemptCount(int failedPasswordAnswerAttemptCount)
	{
		this.failedPasswordAnswerAttemptCount = failedPasswordAnswerAttemptCount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FailedPasswordAnswerAttemptWindowStart", nullable = false, length = 26)
	public Date getFailedPasswordAnswerAttemptWindowStart()
	{
		return this.failedPasswordAnswerAttemptWindowStart;
	}

	public void setFailedPasswordAnswerAttemptWindowStart(Date failedPasswordAnswerAttemptWindowStart)
	{
		this.failedPasswordAnswerAttemptWindowStart = failedPasswordAnswerAttemptWindowStart;
	}

	@Column(name = "Comment")
	public String getComment()
	{
		return this.comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "sys_user_in_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<SysRole> getRoles()
	{
		return this.roles;
	}

	public void setRoles(Set<SysRole> roles)
	{
		this.roles = roles;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Member_ID")
	@JsonBackReference
	public SysMember getSysMember()
	{
		return this.sysMember;
	}

	public void setSysMember(SysMember sysMember)
	{
		this.sysMember = sysMember;
	}

	/**
	 * 是否为企业会员的管理员账户
	 * @return
	 */
	@Column(name = "IsMemberAdmin")
	public Boolean getIsMemberAdmin()
	{
		return this.isMemberAdmin;
	}

	public void setIsMemberAdmin(Boolean isMemberAdmin)
	{
		this.isMemberAdmin = isMemberAdmin;
	}

	/**
	 * 是否有企业会员
	 * @return
	 */
	@Transient
	public boolean IsEnterpriseMember()
	{
		return this.getSysMember()!=null;
	}
	
	/**
	 * 设置权限集合
	 * 
	 * @param authorities
	 */
	@Transient
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities)
	{
		this.authorities = authorities;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	@Override
	@Transient
	public String getUsername()
	{
		return this.userName;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return !this.isLockedOut;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled()
	{
		return this.isApproved;
	}

}
