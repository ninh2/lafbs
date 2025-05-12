package sio.leo.weblafbs.modele;

import java.sql.Time;
import java.util.List;

public class Seance {
    private int id;
    private Time horaire;
    private String jourSemaine;
    private String prestation;
    private String salle;
    private String prestationCode;
    private String salleId;
    private int nbPlaces;
    private List<String> abonnementsRequis; // Nouvelle propriété

    // Constructeur vide
    public Seance() {
    }

    // Constructeur complet (mis à jour)
    public Seance(int id, Time horaire, String jourSemaine, String prestation, String salle, 
                  String prestationCode, String salleId, int nbPlaces, List<String> abonnementsRequis) {
        this.id = id;
        this.horaire = horaire;
        this.jourSemaine = jourSemaine;
        this.prestation = prestation;
        this.salle = salle;
        this.prestationCode = prestationCode;
        this.salleId = salleId;
        this.nbPlaces = nbPlaces;
        this.abonnementsRequis = abonnementsRequis;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Time getHoraire() { return horaire; }
    public void setHoraire(Time horaire) { this.horaire = horaire; }
    public String getJourSemaine() { return jourSemaine; }
    public void setJourSemaine(String jourSemaine) { this.jourSemaine = jourSemaine; }
    public String getPrestation() { return prestation; }
    public void setPrestation(String prestation) { this.prestation = prestation; }
    public String getSalle() { return salle; }
    public void setSalle(String salle) { this.salle = salle; }
    public String getPrestationCode() { return prestationCode; }
    public void setPrestationCode(String prestationCode) { this.prestationCode = prestationCode; }
    public String getSalleId() { return salleId; }
    public void setSalleId(String salleId) { this.salleId = salleId; }
    public int getNbPlaces() { return nbPlaces; }
    public void setNbPlaces(int nbPlaces) { this.nbPlaces = nbPlaces; }
    
    // Nouveau getter et setter pour abonnementsRequis
    public List<String> getAbonnementsRequis() { return abonnementsRequis; }
    public void setAbonnementsRequis(List<String> abonnementsRequis) { this.abonnementsRequis = abonnementsRequis; }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", horaire=" + horaire +
                ", jourSemaine='" + jourSemaine + '\'' +
                ", prestation='" + prestation + '\'' +
                ", salle='" + salle + '\'' +
                ", prestationCode='" + prestationCode + '\'' +
                ", salleId='" + salleId + '\'' +
                ", nbPlaces=" + nbPlaces +
                ", abonnementsRequis=" + abonnementsRequis +
                '}';
    }
}