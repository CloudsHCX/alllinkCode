package com.alllink.sellerapp.seller.entity;

import com.alllink.commons.utils.TimeUtil;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 *
 * @author xzz
 * @date 2017-12-08 19:46:48
 */
public class SellerAuthinfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //商家认证表id
    private int sauthinfoId;
    //商家id
    private int sellerId;
    //法人身份证号
    private String crCardId;
    //法人代表
    private String crRealName;
    //法人照片
    private String crRealPhoto;
    //法人身份证正面照
    private String crCardFrontPhoto;
    //法人身份证反面照
    private String crCardBackPhoto;
    //组织机构代码证
    private String organziationCodeCertificate;
    //营业执照
    private String businessLicence;
    //创建时间
    private Date createTime;
    //审核状态
    private int auditState;
    //审核时间
    private Date auditTime;
    //修改时间
    private Timestamp modifiedTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSauthinfoId() {
        return sauthinfoId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getCrCardId() {
        return crCardId;
    }

    public String getCrRealName() {
        return crRealName;
    }

    public String getCrRealPhoto() {
        return crRealPhoto;
    }

    public String getCrCardFrontPhoto() {
        return crCardFrontPhoto;
    }

    public String getCrCardBackPhoto() {
        return crCardBackPhoto;
    }

    public String getOrganziationCodeCertificate() {
        return organziationCodeCertificate;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getAuditState() {
        return auditState;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setSauthinfoId(int sauthinfoId) {
        this.sauthinfoId = sauthinfoId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public void setCrCardId(String crCardId) {
        this.crCardId = crCardId;
    }

    public void setCrRealName(String crRealName) {
        this.crRealName = crRealName;
    }

    public void setCrRealPhoto(String crRealPhoto) {
        this.crRealPhoto = crRealPhoto;
    }

    public void setCrCardFrontPhoto(String crCardFrontPhoto) {
        this.crCardFrontPhoto = crCardFrontPhoto;
    }

    public void setCrCardBackPhoto(String crCardBackPhoto) {
        this.crCardBackPhoto = crCardBackPhoto;
    }

    public void setOrganziationCodeCertificate(String organziationCodeCertificate) {
        this.organziationCodeCertificate = organziationCodeCertificate;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public String toString() {
        return "SellerAuthinfoEntity{" +
                "sauthinfoId=" + sauthinfoId +
                ", sellerId=" + sellerId +
                ", crCardId='" + crCardId + '\'' +
                ", crRealName='" + crRealName + '\'' +
                ", crRealPhoto='" + crRealPhoto + '\'' +
                ", crCardFrontPhoto='" + crCardFrontPhoto + '\'' +
                ", crCardBackPhoto='" + crCardBackPhoto + '\'' +
                ", organziationCodeCertificate='" + organziationCodeCertificate + '\'' +
                ", businessLicence='" + businessLicence + '\'' +
                ", createTime=" + createTime +
                ", auditState=" + auditState +
                ", auditTime=" + auditTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
