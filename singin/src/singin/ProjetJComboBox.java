package singin;

/**
 *
 * @author matt
 */
public class ProjetJComboBox {
  
  private Projet projet;
  
  public ProjetJComboBox(Projet p){
	projet = p;
  }

  public Projet getProjet() {
	return projet;
  }

  @Override
  public String toString() {
	return projet.getObjet();
  }
  
}
