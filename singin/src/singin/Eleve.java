package singin;

/**
 *
 * @author matt
 */
public class Eleve extends singin.User{
  
  private Professeur professeur;

  public Eleve(String p, String n, String i, Professeur professeur) {
    super(p, n, i);
	this.professeur = professeur;
  }

  public Professeur getProfesseur() {
	return professeur;
  }
  
  
  @Override
  public String toString() {
	return "Eleve{" + "idUser=" + idUser + ", nom=" + nom + ", prenom=" + prenom + ", instrument=" + instrument + ", professeur=" + professeur + '}';
  }
  
}
