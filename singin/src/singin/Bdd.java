package singin;

import singin.mvc.CModel;
import java.util.HashMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 *
 * @author matt
 */
public class Bdd extends CModel {

  /**
   * *************************************************************************
   */
  /**
   * *** PROPRIETES **********************************************************
   */
  // Toutes les données permanentes.
  protected static HashMap<Integer, Projet> projets = new HashMap<>();
  protected static HashMap<Integer, Professeur> professeurs = new HashMap<>();
  protected static HashMap<Integer, Eleve> eleves = new HashMap<>();

  /**
   * *************************************************************************
   */
  /**
   * *** CONSTRUCTEURS *******************************************************
   */
  public Bdd() {
	super();
	initData();
  }

  private static void initData() {
	Professeur pf = new singin.Professeur("Bilel", "Hachaichi", "Saxo");
	professeurs.put(pf.getIdUser(), pf);

	Eleve ua = new Eleve("Othmane", "Bensassi", "Luth", pf);
	Eleve ub = new Eleve("Julien", "Cuello", "Basse", pf);
	Eleve uc = new Eleve("Mika", "Randria", "Guitare", pf);
	Eleve ud = new Eleve("Matthieu", "Gibeaud", "Piano", pf);
	Eleve ue = new Eleve("Mystère", "Bouledegom", "Guibarde", pf);
	eleves.put(ua.getIdUser(), ua);
	eleves.put(ub.getIdUser(), ub);
	eleves.put(uc.getIdUser(), uc);
	eleves.put(ud.getIdUser(), ud);
	eleves.put(ue.getIdUser(), ue);

	pf.addEleve(ua);
	pf.addEleve(ub);
	pf.addEleve(uc);
	pf.addEleve(ud);
	pf.addEleve(ue);

	Enregistrement ea = new Enregistrement("p1sample1.wav", pf);
	Enregistrement eb = new Enregistrement("p1sample2.wav", ua);
	Enregistrement ec = new Enregistrement("p1sample3.wav", ub);
	Enregistrement ed = new Enregistrement("p2sample1.wav", pf);
	Enregistrement ef = new Enregistrement("p2sample2.wav", ua);
	Enregistrement eg = new Enregistrement("p2sample3.wav", ub);
	Enregistrement eh = new Enregistrement("p2sample4.wav", uc);
	Enregistrement ei = new Enregistrement("p2sample5.wav", ud);
	Enregistrement ej = new Enregistrement("p2sample6.wav", ue);

	Projet p1 = new Projet("Travail vamp et turn around");
	p1.addEnregistrement(ea);
	p1.addEnregistrement(eb);
	p1.addEnregistrement(ec);
	projets.put(p1.getIdProjet(), p1);

	Projet p2 = new Projet("Audition fin de trimestre");
	p2.addEnregistrement(ed);
	p2.addEnregistrement(ef);
	p2.addEnregistrement(eg);
	p2.addEnregistrement(eh);
	p2.addEnregistrement(ei);
	p2.addEnregistrement(ej);
	projets.put(p2.getIdProjet(), p2);
  }

  /**
   * *************************************************************************
   */
  /**
   * *** METHODES Accès aux données ******************************************
   */
  public Projet getProjet(int idp) throws singin.DataNotFound {
	if (projets.containsKey(idp)) {
	  return projets.get(idp);
	} else {
	  throw new singin.DataNotFound("Bdd.getProjet(idp): projet introuvable.");
	}
  }

  public Professeur getProfesseurDuProjet(int idp) throws singin.DataNotFound {
	Projet p = getProjet(idp);
	if (p.getProfesseur() != null) {
	  return p.getProfesseur();
	} else {
	  throw new singin.DataNotFound(
			  "Bdd.getProfesseurDuProjet: projet sans professeur.");
	}
  }
  
  public Eleve getEleve(String nom) throws singin.DataNotFound {
        Eleve e = null;
	for(Eleve value : eleves.values()) {
            String n = value.getNom();
            System.out.println("Utilisateur NON retrouvé");
            if (n.equals(nom))
            {
                System.out.println("Utilisateur retrouvé");
                e = value;
                break;
            }             
    }
        return e;
  }

  public ListModel<UserJList> getUsersJList() {
	DefaultListModel<UserJList> res = new DefaultListModel<>();
	for (Eleve e : eleves.values()) {
	  res.addElement(e.getUserJList());
	}
	return res;
  }

  public ComboBoxModel<ProjetJComboBox> getProjetsJComboBox() {
	DefaultComboBoxModel<ProjetJComboBox> res = new DefaultComboBoxModel<>();
	for (Projet p : projets.values()) {
	  res.addElement(p.getProjetComboBox());
	}
	return res;
  }

  public ListModel<EnregistrementJList> getEnregistrementsJList(int idp)
		  throws DataNotFound {
	DefaultListModel<EnregistrementJList> res = new DefaultListModel<>();
	for (Enregistrement e : getProjet(idp).getEnregistrements()) {
	  res.addElement(e.getEnregistrementJList());
	}
	return res;
  }

  public HashMap<Integer, Projet> getProjets() {
	return projets;
  }

  public HashMap<Integer, Professeur> getProfesseurs() {
	return professeurs;
  }

  public HashMap<Integer, Eleve> getEleves() {
	return eleves;
  }
  
  

  /**
   * *************************************************************************
   */
  /**
   * *** METHODES Mise à jour ************************************************
   */
  public int newProjet(String titre) {
	Projet p = new Projet(titre);
	projets.put(p.getIdProjet(), p);
	notifyViews();
	return p.getIdProjet();
  }
  
  public int newProjet(Projet p){
	projets.put(p.getIdProjet(), p);
	notifyViews();
	return p.getIdProjet();
  }

  public void addProfesseurToProject(int idp, Professeur prof)
		  throws DataNotFound {
	Projet proj = getProjet(idp);
	proj.addProfesseur(prof);
  }

  public void addEleveToProject(int idp, Eleve e) throws DataNotFound {
	Projet proj = getProjet(idp);
	proj.addEleve(e);
  }

  public void addEnregistrementToProjet(int idp, Enregistrement e)
		  throws DataNotFound {
	Projet proj = getProjet(idp);
	proj.addEnregistrement(e);
  }
  
  public void addConsigneToProjet(int idp, String consigne) throws DataNotFound{
	Projet proj = getProjet(idp);
	proj.addConsigne(consigne);
	notifyViews();
  }

  /**
   * *************************************************************************
   */
  /**
   * *** AFFICHAGE ***********************************************************
   */
  public static void printAllData() {
	for (Projet p : projets.values()) {
	  System.out.println(p);
	}

	for (Professeur p : professeurs.values()) {
	  System.out.println(p);
	}

	for (Eleve e : eleves.values()) {
	  System.out.println(e);
	}
  }

  public String getPrintableEleves() {
	String res = "Elèves :\n";
	for (Eleve e : eleves.values()) {
	  res += "  " + e.getPrenom() + " " + e.getNom();
	  res += ", " + e.getInstrument();
	  res += ". Prof : " + e.getProfesseur().getPrenom();
	  res += " " + e.getProfesseur().getNom();
	  res += "\n";
	}
	return res;
  }

  public String getPrintableProfesseurs() {
	String res = "Professeurs :\n";
	for (Professeur p : professeurs.values()) {
	  res += "  " + p.getPrenom() + " " + p.getNom();
	  res += ", " + p.getInstrument();
	  res += ". Eleves :";
	  for (Eleve e : p.getEleves()) {
		res += "  " + e.getPrenom();
	  }
	}
	return res;
  }

  public String getPrintableProjets() {
	String res = "";
	for (Projet p : projets.values()) {
	  res += "\n\nProjet " + p.getIdProjet() + " : " + p.getObjet();
	  res += " " + p.getProfesseur().getPrenom();
	  res += "\n  ++ Enregistrements :";
	  for (Enregistrement e : p.getEnregistrements()) {
		res += "\n    " + e.getIdEnregistrement();
		res += ", " + e.getUser().getInstrument();
		res += "\t" + e.getUser().getPrenom();
		res += "\t " + e.getPath();
	  }
	  res += "\n  ++ Eleves :";
	  for (Eleve e : p.getEleves()) {
		res += "  " + e.getPrenom();
	  }
	}
	return res;
  }

}
