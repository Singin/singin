package singin;

public class Simple {

  public static void init() {
      
    initData();
    printData();
    
    
    
    
    
  }
  
  public static void initData(){
    User.initUsers();
    Enregistrement.initEnregistrements();
    Projet.initProjets();
  }
  
  public static void printData(){
    User.printUsers();
    Enregistrement.printEnregistrements();
    Projet.printProjets();
  }
  
}
