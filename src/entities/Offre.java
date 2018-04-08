/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;




/**
 *
 * @author asus
 */
public class Offre {
    
     private Integer id; 
    private String Titre_offre;
    private String Type_offre; 
    private String Date_alleer	; 
    private String Dateretour ; 
    
    public Offre() {
		// TODO Auto-generated constructor stub
	}

    public Offre(int id, String Titre_offre, String Type_offre, String Date_alleer, String Dateretour) {
        this.id = id;
        this.Titre_offre = Titre_offre;
        this.Type_offre = Type_offre;
        this.Date_alleer = Date_alleer;
        this.Dateretour = Dateretour;
    }

    public Offre(String Titre_offre, String Type_offre, String Date_alleer, String Dateretour) {
        this.Titre_offre = Titre_offre;
        this.Type_offre = Type_offre;
        this.Date_alleer = Date_alleer;
        this.Dateretour = Dateretour;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre_offre() {
        return Titre_offre;
    }

    public void setTitre_offre(String Titre_offre) {
        this.Titre_offre = Titre_offre;
    }

    public String getType_offre() {
        return Type_offre;
    }

    public void setType_offre(String Type_offre) {
        this.Type_offre = Type_offre;
    }

    public String getDate_alleer() {
        return Date_alleer;
    }

    public void setDate_alleer(String Date_alleer) {
        this.Date_alleer = Date_alleer;
    }

    public String getDateretour() {
        return Dateretour;
    }

    public void setDateretour(String Dateretour) {
        this.Dateretour = Dateretour;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", Titre_offre=" + Titre_offre + ", Type_offre=" + Type_offre + ", Date_alleer=" + Date_alleer + ", Dateretour=" + Dateretour + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj==null || !(obj instanceof Offre) || ((Offre)obj).getId()==null || this.getId()==null)
    		return false;
    	
    	return getId().equals( ((Offre)obj).getId());
    }
    
   
}
