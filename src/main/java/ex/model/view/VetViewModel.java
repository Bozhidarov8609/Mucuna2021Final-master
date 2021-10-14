package ex.model.view;

public class VetViewModel {
    private String vetName;
    private String picture;
    private String service;
    private String address;
    private String phoneNumber;

    public VetViewModel() {
    }

    public String getVetName() {
        return vetName;
    }

    public VetViewModel setVetName(String vetName) {
        this.vetName = vetName;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public VetViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getService() {
        return service;
    }

    public VetViewModel setService(String service) {
        this.service = service;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public VetViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public VetViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
