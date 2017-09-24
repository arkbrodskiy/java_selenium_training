package first.pack.addressbook;

public class ContactData {
    private final String firstName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private final String company;
    private final String homePhone;
    private final String mobilePhone;
    private final String officePhone;
    private final String email;

    public ContactData(String firstName, String lastName, String nickname, String title, String company, String homePhone, String mobilePhone, String officePhone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.officePhone = officePhone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public String getEmail() {
        return email;
    }
}
