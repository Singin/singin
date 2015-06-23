package singin;

/**
 *
 * @author matt
 */
public class UserJList {

  private User user;

    public User getUser() {
        return user;
    }

  public UserJList(User user) {
	this.user = user;
  }

  @Override
  public String toString() {
	return user.getPrenom() + " - " + user.getInstrument();
  }
  
}
