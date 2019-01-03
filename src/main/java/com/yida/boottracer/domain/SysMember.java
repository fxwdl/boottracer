package com.yida.boottracer.domain;
// Generated 2018-12-2 22:45:07 by Hibernate Tools 5.2.10.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.minidev.json.annotate.JsonIgnore;

/**
 * SysMember generated by hbm2java
 */
@Entity
@Table(name = "sys_member")
@NamedEntityGraphs(value = {
	    @NamedEntityGraph(name = "SysMember.dictCompanyType-dictIndustry-dictRegion-dictMemberType", attributeNodes = {
	        @NamedAttributeNode("dictCompanyType"),@NamedAttributeNode("dictIndustry"),@NamedAttributeNode("dictRegion"),@NamedAttributeNode("dictMemberType")
	    })
	})
public class SysMember extends AuditModel implements java.io.Serializable
{

	private long id;
	private DictCommon dictCompanyType;
	private DictCommon dictIndustry;
	private DictRegion dictRegion;
	private DictMemberType dictMemberType;
	private String name;
	private String nameEn;
	private String shortName;
	private String socialCreditCode;
	private String legalPerson;
	private String website;
	private int requirement;
	private String regAddress;
	private String expressAddress;
	private String postcode;
	private String linkman;
	private String tel;
	private String mobile;
	private String fax;
	private String email;
	private String qq;
	private String webChat;
	private Date fromDate;
	private Date endDate;
	private int status;
	private String comment;
	private long barcodeQty;
	private Long version;
	private Set<SysUser> sysUsers = new HashSet<>();
	private Set<EntDictCategory> entDictCategories = new HashSet<EntDictCategory>();

	public SysMember()
	{
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public long getId()
	{
		return this.id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CompanyType_ID", nullable = false)
	public DictCommon getDictCompanyType()
	{
		return this.dictCompanyType;
	}

	public void setDictCompanyType(DictCommon dictCompanyType)
	{
		this.dictCompanyType = dictCompanyType;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.REFRESH})
	@JoinColumn(name = "Industry_ID", nullable = false)
	public DictCommon getDictIndustry()
	{
		return this.dictIndustry;
	}

	public void setDictIndustry(DictCommon dictIndustry)
	{
		this.dictIndustry = dictIndustry;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.REFRESH})
	@JoinColumn(name = "Region_ID", nullable = false)
	public DictRegion getDictRegion()
	{
		return this.dictRegion;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.REFRESH})
	@JoinColumn(name = "MemberTypeId", nullable = false)
	public DictMemberType getDictMemberType()
	{
		return this.dictMemberType;
	}

	public void setDictMemberType(DictMemberType dictMemberType)
	{
		this.dictMemberType = dictMemberType;
	}
	
	public void setDictRegion(DictRegion dictRegion)
	{
		this.dictRegion = dictRegion;
	}

	@NotBlank(message = "名称不能为空")
	@Column(name = "Name", nullable = false, length = 256)
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "Name_En", length = 256)
	public String getNameEn()
	{
		return this.nameEn;
	}

	public void setNameEn(String nameEn)
	{
		this.nameEn = nameEn;
	}

	@Column(name = "ShortName", length = 256)
	public String getShortName()
	{
		return this.shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	@NotBlank(message = "社会信用代码不能为空")
	@Column(name = "SocialCreditCode", nullable = false, length = 256)
	public String getSocialCreditCode()
	{
		return this.socialCreditCode;
	}

	public void setSocialCreditCode(String socialCreditCode)
	{
		this.socialCreditCode = socialCreditCode;
	}

	@NotBlank(message = "法人不能为空")
	@Column(name = "LegalPerson", nullable = false, length = 256)
	public String getLegalPerson()
	{
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson)
	{
		this.legalPerson = legalPerson;
	}

	
	@Column(name = "Website", length = 256)
	public String getWebsite()
	{
		return this.website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	@Column(name = "Requirement", nullable = false)
	public int getRequirement()
	{
		return this.requirement;
	}

	public void setRequirement(int requirement)
	{
		this.requirement = requirement;
	}

	@NotBlank(message = "注册地址不能为空")
	@Column(name = "RegAddress", nullable = false, length = 500)
	public String getRegAddress()
	{
		return this.regAddress;
	}

	public void setRegAddress(String regAddress)
	{
		this.regAddress = regAddress;
	}

	@NotBlank(message = "通信地址不能为空")
	@Column(name = "ExpressAddress", nullable = false, length = 500)
	public String getExpressAddress()
	{
		return this.expressAddress;
	}

	public void setExpressAddress(String expressAddress)
	{
		this.expressAddress = expressAddress;
	}

	@NotBlank(message = "邮编不能为空")
	@Column(name = "Postcode", nullable = false, length = 45)
	public String getPostcode()
	{
		return this.postcode;
	}

	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}

	@NotBlank(message = "联系人不能为空")
	@Column(name = "Linkman", nullable = false, length = 45)
	public String getLinkman()
	{
		return this.linkman;
	}

	public void setLinkman(String linkman)
	{
		this.linkman = linkman;
	}

	@NotBlank(message = "办公电话不能为空")
	@Column(name = "Tel", nullable = false, length = 45)
	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	@NotBlank(message = "手机不能为空")
	@Column(name = "Mobile", nullable = false, length = 45)
	public String getMobile()
	{
		return this.mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	@Column(name = "Fax", length = 45)
	public String getFax()
	{
		return this.fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	@NotBlank(message = "电子邮件不能为空")
	@Column(name = "Email", nullable = false, length = 256)
	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "QQ", length = 45)
	public String getQq()
	{
		return this.qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	@Column(name = "WebChat", length = 45)
	public String getWebChat()
	{
		return this.webChat;
	}

	public void setWebChat(String webChat)
	{
		this.webChat = webChat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FromDate", length = 26)
	public Date getFromDate()
	{
		return this.fromDate;
	}

	public void setFromDate(Date fromDate)
	{
		this.fromDate = fromDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate", length = 26)
	public Date getEndDate()
	{
		return this.endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Column(name = "Status", nullable = false)
	public int getStatus()
	{
		return this.status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	@Column(name = "Comment", length = 1000)
	public String getComment()
	{
		return this.comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysMember",cascade= {CascadeType.ALL})
	@JsonManagedReference
	public Set<SysUser> getSysUsers()
	{
		return this.sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers)
	{
		this.sysUsers = sysUsers;
	}

	@Column(name = "BarcodeQty", nullable = false)
	public Long getBarcodeQty()
	{
		return this.barcodeQty;
	}

	public void setBarcodeQty(Long barcodeQty)
	{
		this.barcodeQty = barcodeQty;
	}

	@Version
	@Column(name = "Version", nullable = false)
	public Long getVersion()
	{
		return this.version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysMember")
	@JsonManagedReference(value="EntDictCategory-SysMember")
	public Set<EntDictCategory> getEntDictCategories()
	{
		return this.entDictCategories;
	}

	public void setEntDictCategories(Set<EntDictCategory> entDictCategories)
	{
		this.entDictCategories = entDictCategories;
	}
	
	@Transient
	public String getDisplayId() 
	{
		return StringUtils.leftPad(Long.toString(this.getId()), 5, "0");
	}
	
	protected void setDisplayId(String v)
	{
		//do nothing,for hibernate
	}
}
