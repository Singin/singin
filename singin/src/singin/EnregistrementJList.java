package singin;

/**
 *
 * @author matt
 */
public class EnregistrementJList {
  
  private Enregistrement enregistrement;

  public EnregistrementJList(Enregistrement e) {
	this.enregistrement = e;
  }

  public Enregistrement getEnregistrement() {
	return enregistrement;
  }

  @Override
  public String toString() {
	String res = enregistrement.getUser().getPrenom() + " - "
			+ enregistrement.getUser().getInstrument();
	if(enregistrement.getUser().getClass() == Professeur.class){
	  res += " - Professeur";
	}
	return res;
  }
  
  
  
}
