package singin;

public class EnregSelectionnable {
  
  private boolean selected;
  private int idEnregistrement;
  private String text;
  
  public EnregSelectionnable(int id, String prenom, String nom){
	idEnregistrement = id;
	text = prenom + " " + nom;
	selected = false;
  }

  public boolean isSelected() {
	return selected;
  }
  
  public void setSelected(boolean selected) {
	this.selected = selected;
  }

  
  
  
  
  
  
  @Override
  public String toString() {
	return "SS{" + "s=" + selected + ", t=" + text + '}';
  }
  
  
  
  
}
