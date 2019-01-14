package com.example.ravipavansne.brightcarrot;

public class BankDetails {

    private String accountnumber;
    private String ifsccode;
    private String bankname;
    private String bankbranch;
    private String flag;

    public BankDetails() { }

    public BankDetails(String accountnumber, String ifsccode, String bankname, String bankbranch) {
        this.accountnumber = accountnumber;
        this.ifsccode = ifsccode;
        this.bankname = bankname;
        this.bankbranch = bankbranch;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public void setBankbranch(String bankbranch) {
        this.bankbranch = bankbranch;
    }
}
