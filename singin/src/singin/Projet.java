package singin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.beadsproject.beads.data.Sample;

public class Projet implements Serializable{

  /**
   * *************************************************************************
   */
  /**
   * *** PROPRIETES **********************************************************
   */
  private int idProjet;
  private String objet;
  private String consignes;
  private Professeur professeur;
  private ArrayList<Eleve> eleves;
  private ArrayList<Enregistrement> enregistrements;

  /* Gestion des instances */
  private static int nbInstances = 0;
  private static final HashMap<Integer, Projet> projets = new HashMap<>();

  

  /**
   * *************************************************************************
   */
  /**
   * *** CONSTRUCTEURS *******************************************************
   */
  
  public Projet() {
	idProjet = addInstance();
	objet = null;
	consignes = null;
	professeur = null;
	eleves = new ArrayList<>();
	enregistrements = new ArrayList<>();
  }
  
  public Projet(String o) {
	this();
	objet = o;
  }
  
  public Projet(String o, String c) {
	this(o);
	consignes = c;
  }


  /**
   * *************************************************************************
   */
  /**
   * *** GESTION DES INSTANCES ***********************************************
   */
  private int addInstance() {
	nbInstances++;
	projets.put(nbInstances, this);
	return nbInstances;
  }

  /**
   * *************************************************************************
   */
  /**
   * *** METHODES ************************************************************
   */
  
  public void addProfesseur(Professeur p){
	professeur = p;
  }
  
  public void addEleve(Eleve e){
	eleves.add(e);
  }
  
  public void addEnregistrement(Enregistrement e) {
	enregistrements.add(e);
	
	if(e.getUser().getClass() == Eleve.class){
	  addEleve((Eleve) e.getUser());
	}
	else{
	  addProfesseur((Professeur) e.getUser());
	}
  }

  public void addEnregistrements(Collection<Enregistrement> enreg) {
	for (Enregistrement e : enreg) {
	  addEnregistrement(e);
	}
  }

  public List<Sample> getSamples() {
	ArrayList<Sample> res = new ArrayList<Sample>();
	for (Enregistrement e : enregistrements) {
	  res.add(e.getSample());
	}
	return res;
  }

  public static void initProjets() {
	Collection<Enregistrement> enreg = Enregistrement.getEnregistrements().values();
	Projet p = new Projet("ancien");
	p.addEnregistrements(enreg);
  }

  public static Projet getOneProjetRandom() {
	Random random = new Random();
	int id = random.nextInt(nbInstances) + 1;
	return projets.get(id);
  }

  public EnregSelectionnable[] getSampleSelectionnable() {
	EnregSelectionnable[] res = new EnregSelectionnable[getNbEnregistrement()];
	int i = 0;
	for (Enregistrement e : enregistrements) {
	  User u = e.getUser();
	  res[i] = new EnregSelectionnable(e.getIdEnregistrement(), u.getPrenom(), u.getNom());
	  i++;
	}
	return res;
  }

  public int getNbEnregistrement() {
	return enregistrements.size();
  }
  
  public void addConsigne(String c){
	consignes = c;
  }

  /**
   * *************************************************************************
   */
  /**
   * *** GETTERS *************************************************************
   */
  
  public static HashMap<Integer, Projet> getProjets() {
	return projets;
  }

  public int getIdProjet() {
	return idProjet;
  }

  public Professeur getProfesseur() {
	return professeur;
  }

  public String getObjet() {
	return objet;
  }

  public ArrayList<Eleve> getEleves() {
	return eleves;
  }

  public ArrayList<Enregistrement> getEnregistrements() {
	return enregistrements;
  }

  public String getConsignes() {
	return consignes;
  }

  /**
   * *************************************************************************
   */
  /**
   * *** SETTERS *************************************************************
   */
  
  public void setConsignes(String consignes) {
	this.consignes = consignes;
  }
  
  public ProjetJComboBox getProjetComboBox(){
	return new ProjetJComboBox(this);
  }

  /**
   * *************************************************************************
   */
  /**
   * *** AFFICHAGE ***********************************************************
   */
  @Override
  public String toString() {
	return "Projet{" + "idProjet=" + idProjet + ", objet=" + objet + ", professeurs=" + professeur + ", eleves=" + eleves + ", enregistrements=" + enregistrements + '}';
  }

  /**
   * Affiche toutes les instances de la classe sur sortie standard .
   */
  public static void printProjets() {
	for (Projet p : projets.values()) {
	  System.out.println(p.toString());
	}
  }

}
