package singin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class User implements Serializable {
    
  protected final int idUser;
  protected String nom;
  protected String prenom;
  protected String instrument;
  
  private static int nbInstances = 0;
  private static final HashMap<Integer, User> users = new HashMap<>();
  
  public User(String p, String n, String i){
    nom = n;
    prenom = p;
	instrument = i;
    idUser = addInstance();
  }
  
  private int addInstance(){
    nbInstances++;
    users.put(nbInstances, this);
    return nbInstances;
  }
  
  private void delete(){
	users.remove(idUser);
  }
  
  public static void initUsers(){
    User ua = new User("Bilel", "Hachaichi", "Saxo");
    User ub = new User("Othmane", "Bensassi", "Luth");
    User uc = new User("Julien", "Cuello", "Basse");
  }

  public static User getOneUserRandom(){
    Random random = new Random();
    int id = random.nextInt(nbInstances) + 1;
    return users.get(id);
  }
  
  /*******************/
  /***** GETTERS *****/
  
  public static HashMap<Integer, User> getUsers() {
    return users;
  }

  public String getNom() {
	return nom;
  }

  public String getPrenom() {
	return prenom;
  }

  public int getIdUser() {
	return idUser;
  }

  public String getInstrument() {
	return instrument;
  }
  
  public UserJList getUserJList(){
	return new UserJList(this);
  }
  

  /*********************/
  /***** AFFICHAGE *****/
  
  @Override
  public String toString() {
	return "User{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", instrument=" + instrument + '}';
  }
 
  public static void printUsers(){
    for(User user : users.values()){
      System.out.println(user.toString());
    }
  }
  
}
