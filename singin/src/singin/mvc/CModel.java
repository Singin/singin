package singin.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matt
 */
public class CModel {

  protected List<IModelView> views;
  
  public CModel(){
	views = new ArrayList<>();
  }
  
  public void notifyViews(){
	for(IModelView view : views)
	  view.notify(this);
  }

  public void addView(IModelView v){
	views.add(v);
  }
  
  public void removeView(IModelView v){
	views.remove(v);
  }

}
