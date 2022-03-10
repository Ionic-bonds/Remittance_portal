package csvfiles.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CorporateUser")
public class CorporateUser {
    @Id
    @Column(name = "UserID")
        private long UserID;
    @Column(name = "UserName")
        private String username;
    @Column(name = "UserEmail" )
        private String userEmail;
    public CorporateUser(){

    }
    public CorporateUser(long UserID, String username, String userEmail){
        this.UserID = UserID;
        this.username = username;
        this.userEmail = userEmail;
    }
    public long getUserID() {
        return UserID;
    }
    public void setUserID(long userID) {
        UserID = userID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    @Override
    public String toString() {
        return "CorporateUser [UserID=" + UserID + ", username=" + username + ", userEmail=" + userEmail + "]";
    }
}