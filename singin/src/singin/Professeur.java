package singin;

import java.util.ArrayList;

/**
 *
 * @author matt
 */
public class Professeur extends singin.User {
  
  private ArrayList<Eleve> eleves;

  public Professeur(String p, String n, String i) {
	super(p, n, i);
	this.eleves = new ArrayList<>();
  }
  
  public void addEleve(Eleve e){
	eleves.add(e);
  }
  
  public void removeEleve(Eleve e){
	eleves.remove(e);
  }

  public ArrayList<Eleve> getEleves() {
	return eleves;
  }
  
  

  public String toString() {
	return "Professeur{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", instrument=" + instrument + '}';
  }
  
}
