package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "hallgatok")
public class Hallgato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column(unique = true, nullable = false)
    private String nev;

    @Column(unique = true, nullable = false)
    private String szuletesi_ev;

    @Column(unique = true, nullable = false)
    private String neptun_kod;


    public Hallgato() {
    }

    public Hallgato(String nev, String szuletesi_ev, String neptun_kod) {
        this.nev = nev;
        this.szuletesi_ev = szuletesi_ev;
        this.neptun_kod = neptun_kod;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getSzuletesi_ev() {
        return szuletesi_ev;
    }

    public void setSzuletesi_ev(String szuletesiev) {
        this.szuletesi_ev = szuletesiev;
    }

    public String getNeptun_kod() {
        return neptun_kod;
    }

    public void setNeptun_kod(String neptunkod) {
        this.neptun_kod = neptunkod;
    }

    @Override
    public String toString() {
        return "Hallgato{" +
                "ID=" + ID +
                ", nev='" + nev + '\'' +
                ", szuletesiev=" + szuletesi_ev +
                ", neptunkod='" + neptun_kod + '\'' +
                '}';
    }
}
