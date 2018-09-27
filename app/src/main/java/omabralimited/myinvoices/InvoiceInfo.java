package omabralimited.myinvoices;

import java.io.Serializable;

public class InvoiceInfo implements Serializable {
  private String title;
  private String profilePic;
  private String id;
  private String type;
  private String location;
  private String shopName;
  private String date;
  private String comment;

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
  public String getProfilePic() {
    return profilePic;
  }

  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }
}
