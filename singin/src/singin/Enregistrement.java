package singin;

import java.beans.Transient;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.beadsproject.beads.data.Sample;

public class Enregistrement implements Serializable {

  /****************************************************************************/
  /***** PROPRIETES ***********************************************************/
  
  private final int idEnregistrement;
  private Sample sample;
  private String path;
  private User user;
  private String commentaire;
  
  /* Gestion des instances */
  private static int nbInstances = 0;
  private static final HashMap<Integer, Enregistrement> enregistrements = 
	  new HashMap<>();
  
  /**
   * *************************************************************************
   */
  /**
   * *** CONSTRUCTEURS *******************************************************
   */

  /**
   * 
   * @param p String : Chemin vers le fichier son (wav).
   * @param u User : user qui a enregistré le sample.
   */
  public Enregistrement(String p, User u){
    user = u;
    path = p;
    openSample();
    idEnregistrement = addInstance();
  }
  
  /****************************************************************************/
  /***** GESTION DES INSTANCES ************************************************/
  
  private int addInstance(){
    nbInstances++;
    enregistrements.put(nbInstances, this); 
    return nbInstances;
  }
  
  /****************************************************************************/
  /***** METHODES *************************************************************/
  
  /**
   * Tente de charger un fichier son à partir de la propriété
   * 'path'. La référence du 'Sample' créé est dans la propriété 'sample'.
   * 
   */
  private void openSample(){
    try{
      sample = new Sample(path);
    }
    catch (IOException ex) {
      Logger.getLogger(Enregistrement.class.getName()).log(Level.SEVERE, null, ex);
      ex.printStackTrace();
    }
  }
  
//  /**
//   * Crée des instances de 'Enregistrement'.
//   * POUR TEST.
//   */
  public static void initEnregistrements(){
    Enregistrement e1 = new Enregistrement("Sample1.wav", User.getOneUserRandom());
    Enregistrement e2 = new Enregistrement("Sample2.wav", User.getOneUserRandom());
    Enregistrement e3 = new Enregistrement("Sample3.wav", User.getOneUserRandom());
    Enregistrement e4 = new Enregistrement("Sample4.wav", User.getOneUserRandom());
  }
  
  
  /****************************************************************************/
  /***** GETTERS **************************************************************/
  
  public static HashMap<Integer, Enregistrement> getEnregistrements() {
    return enregistrements;
  }

  public Sample getSample() {
    return sample;
  }

  public User getUser() {
	return user;
  }

  public int getIdEnregistrement() {
	return idEnregistrement;
  }

  public String getPath() {
	return path;
  }
  
  public EnregistrementJList getEnregistrementJList(){
	return new EnregistrementJList(this);
  }

  public String getCommentaire() {
	return commentaire;
  }

  public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
  }
  
  
  
  /****************************************************************************/
  /***** AFFICHAGE ************************************************************/
  
  @Override
  public String toString() {
    return "Enregistrement{" + "idEnregistrement=" + idEnregistrement + ", path=" + path + ", user=" + user + '}';
  }
  
  /**
   * Affiche toutes les instances de la classe sur sortie standard .
   */
  public static void printEnregistrements(){
    for(Enregistrement e : enregistrements.values()){
      System.out.println(e.toString());
    }
  }
  
}
