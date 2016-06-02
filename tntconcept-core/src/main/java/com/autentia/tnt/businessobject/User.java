/**
 * TNTConcept Easy Enterprise Management by Autentia Real Bussiness Solution S.L. Copyright (C) 2007 Autentia Real Bussiness
 * Solution S.L. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation, either version 3 of the License. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.autentia.tnt.businessobject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.autentia.tnt.util.ConfigurationUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.autentia.tnt.dao.ITransferObject;

/**
 * Transfer object to store Users
 * 
 * @author stajanov code generator
 */
public class User implements Serializable, ITransferObject {

    /** Serial version field */
    private static final long serialVersionUID = -1L;

    /** Logger */
    private static final Log log = LogFactory.getLog(User.class);

    /* user - generated by stajanov (do not edit/delete) */

    // Fields

    private Integer id;

    private boolean active;

    private String login;

    private String password;

    private String name;

    private Date startDate;

    private String nif;

    private UserGenre genre;

    private String socialSecurityNumber;

    private boolean workingInClient;

    private Date birthDate;

    private boolean married;

    private int childrenNumber;

    private String travelAvailability;

    private String academicQualification;

    private String email;

    private String phone;

    private String mobile;

    private String street;

    private String postalCode;

    private String city;

    private String bank;

    private String account;

    private BigDecimal salary;

    private BigDecimal salaryExtras;

    private String drivenLicenseType;

    private String vehicleType;

    private String licensePlate;

    private String securityCard;

    private String healthInsurance;

    private String notes;

    private String photo;

    private Date endTestPeriodDate;

    private Date endContractDate;

    private int dayDuration;

    private String contractObservations;

    private Date insertDate;

    private Date updateDate;

    private Role role;

    private UserCategory category;

    private Province province;

    private DocumentCategory documentCategory;

    private Department department;

    private ContractType contractType;

    private WorkingAgreement agreement;

    private Integer agreementYearDuration;

    private Date passwordExpireDate;

    private Boolean expiredPassword;

    private String ldapPassword;

    // Setters and getters

    protected void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public UserGenre getGenre() {
        return genre;
    }

    public void setGenre(UserGenre genre) {
        this.genre = genre;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public boolean isWorkingInClient() {
        return workingInClient;
    }

    public void setWorkingInClient(boolean workingInClient) {
        this.workingInClient = workingInClient;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public String getTravelAvailability() {
        return travelAvailability;
    }

    public void setTravelAvailability(String travelAvailability) {
        this.travelAvailability = travelAvailability;
    }

    public String getAcademicQualification() {
        return academicQualification;
    }

    public void setAcademicQualification(String academicQualification) {
        this.academicQualification = academicQualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getSalary() {
        if (salary == null) {
            return new BigDecimal(0);
        } else {
            return salary;
        }
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getSalaryExtras() {
        if (salaryExtras == null) {
            return new BigDecimal(0);
        } else {
            return salaryExtras;
        }

    }

    public void setSalaryExtras(BigDecimal salaryExtras) {
        this.salaryExtras = salaryExtras;
    }

    public String getDrivenLicenseType() {
        return drivenLicenseType;
    }

    public void setDrivenLicenseType(String drivenLicenseType) {
        this.drivenLicenseType = drivenLicenseType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getSecurityCard() {
        return securityCard;
    }

    public void setSecurityCard(String securityCard) {
        this.securityCard = securityCard;
    }

    public String getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(String healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getEndTestPeriodDate() {
        return endTestPeriodDate;
    }

    public void setEndTestPeriodDate(Date endTestPeriodDate) {
        this.endTestPeriodDate = endTestPeriodDate;
    }

    public Date getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(Date endContractDate) {
        this.endContractDate = endContractDate;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public String getContractObservations() {
        return contractObservations;
    }

    public void setContractObservations(String contractObservations) {
        this.contractObservations = contractObservations;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserCategory getCategory() {
        return category;
    }

    public void setCategory(UserCategory category) {
        this.category = category;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public DocumentCategory getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(DocumentCategory documentCategory) {
        this.documentCategory = documentCategory;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public WorkingAgreement getAgreement() {
        return agreement;
    }

    public void setAgreement(WorkingAgreement agreement) {
        this.agreement = agreement;
    }

    public Integer getOwnerId() {
        return id;
    }

    public Integer getDepartmentId() {
        return department.getId();
    }

    public void setOwnerId(Integer ownerId) {

    }

    public void setDepartmentId(Integer departmentId) {

    }

    /**
     * @return the agreementHoursByYear
     */
    public Integer getAgreementYearDuration() {
        return agreementYearDuration;
    }

    /**
     * @param agreementYearDuration the agreementHoursByYear to set
     */
    public void setAgreementYearDuration(Integer agreementYearDuration) {
        this.agreementYearDuration = agreementYearDuration;
    }

    public double getSalaryPerHour() {

        /*
         * Si el usuario tiene rellenop el campo de horas anuales, se usa ese dato para el calculo del salario por hora Si
         * no, se usa el numero de horas incluido en el convenio
         */
        double userYearHours = this.getAgreementYearDuration() != null ? (this.getAgreementYearDuration() / 60.0)
                : (this.getAgreement().getYearDuration() / 60.0);
        double costPerHour = this.getSalary().doubleValue() / userYearHours;

        return costPerHour;
    }

    /* se estima el coste por hora, en el salario de cada usuario */
    public Double getCostPerHour() {

        double bruto = this.getSalary().doubleValue();
        double coste = (bruto * 2) / 11 / 20 / 8; // se divide por 11 meses, 20 dias al mes laborables y 8 horas al día

        return coste;

    }

    public Date getPasswordExpireDate() {
        return passwordExpireDate;
    }

    public void setPasswordExpireDate(Date passwordExpireDate) {
        this.passwordExpireDate = passwordExpireDate;
    }

    /**
     * @return Return if the user password expired
     */
    public boolean isPasswordExpired() {
        if (!isLdapAuthentication()) {
            this.expiredPassword = checkDatabaseExpiredPassword();
        }
        if (this.expiredPassword && log.isInfoEnabled()) {
            log.info("Password expired for user " + this.getLogin());
        }
        return this.expiredPassword;
    }

    private Boolean checkDatabaseExpiredPassword() {
        Date nowDate = new Date();
        Date expireDate = this.getPasswordExpireDate();
        return (expireDate == null) || nowDate.after(expireDate);
    }

    public void setExpiredPassword(Boolean expiredPassword) {
        this.expiredPassword = expiredPassword;
    }

    @Override
    public boolean equals(Object that) {
        try {
            if (that == null)
                return false;
            else
                return this.getId().equals(((User)that).getId());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (this.getId() == null)
            return super.hashCode();
        else
            return this.getId().intValue();
    }

    public List<Integer> getOwnersId() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getLdapPassword() {
        return ldapPassword;
    }

    public void setLdapPassword(String ldapPassword) {
        this.ldapPassword = ldapPassword;
    }

    /* user - generated by stajanov (do not edit/delete) */

    public Boolean isLdapAuthentication() {
        return ldapPassword != null && !"".equals(ldapPassword);
    }
}
