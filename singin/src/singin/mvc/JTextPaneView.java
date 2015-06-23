package singin.mvc;

import singin.ConsoleBdd;
import javax.swing.JTextPane;
import singin.Bdd;

/**
 *
 * @author matt
 */
public class JTextPaneView extends JTextPane implements IModelView{

  private CModel bdd;
  
  public JTextPaneView() {
	super();
	setEditable(false);
	
	bdd = (Bdd) ConsoleBdd.getModel();
  }
  
  
  
  @Override
  public void notify(CModel m) {
	Bdd model = (Bdd) ConsoleBdd.getModel();
	
	String fullBdd = "";
	
	fullBdd += model.getPrintableEleves();
	fullBdd += "\n";
	fullBdd += model.getPrintableProfesseurs();
	fullBdd += "\n";
	fullBdd += model.getPrintableProjets();
	
	this.setText(fullBdd);
	this.setEditable(false);
  }
  
}
