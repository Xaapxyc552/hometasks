package ua.task3.model.entities;

public class Note {

    private String firstName;
    private String nickName;
    private String phoneNumber;

    public Note(String firstName, String nickName, String phoneNumber) {
        this.firstName = firstName;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "\tNote{\n" +
                "\t\tFirst name = " + firstName + '\n' +
                "\t\tNickname = " + nickName + '\n' +
                "\t\tPhone number= " + phoneNumber + '}' +
                '\n';
    }
}