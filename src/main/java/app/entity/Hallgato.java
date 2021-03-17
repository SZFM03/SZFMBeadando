package app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Hallgato")
@Table(name = "hallgato")
public class Hallgato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nev;

    @Column(nullable = false)
    private String szuletesi_ev;

    @Column(unique = true, nullable = false)
    private String neptun_kod;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="hallgato_tantargy",
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private List<Tantargy> tantargyak = new ArrayList<>();

    public List<Tantargy> getTantargyak() {
        return tantargyak;
    }

    public void addTantargy(Tantargy tantargy){
        tantargyak.add(tantargy);
        tantargy.getHallgatok().add(this);
    }

    public void removeTantargy(Tantargy tantargy){
        tantargyak.remove(tantargy);
        tantargy.getHallgatok().remove(this);
    }

    public void setTantargyak(List<Tantargy> tantargyak) {
        for (var tantargy : tantargyak){
            addTantargy(tantargy);
        }
    }

    public void removeTantargyak(List<Tantargy> tantargyak){
        for(var tantargy : tantargyak){
            removeTantargy(tantargy);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hallgato hallgato = (Hallgato) o;
        return Objects.equals(id, hallgato.id) && Objects.equals(nev, hallgato.nev) && Objects.equals(szuletesi_ev, hallgato.szuletesi_ev) && Objects.equals(neptun_kod, hallgato.neptun_kod) && Objects.equals(tantargyak, hallgato.tantargyak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, szuletesi_ev, neptun_kod, tantargyak);
    }

    public Hallgato() {
    }

    public Hallgato(String nev, String szuletesi_ev, String neptun_kod) {
        this.nev = nev;
        this.szuletesi_ev = szuletesi_ev;
        this.neptun_kod = neptun_kod;
    }

    public Hallgato(Long id, String nev, String szuletesi_ev, String neptun_kod, List<Tantargy> tantargyak) {
        this.id = id;
        this.nev = nev;
        this.szuletesi_ev = szuletesi_ev;
        this.neptun_kod = neptun_kod;
        this.tantargyak = tantargyak;
    }

    public Hallgato(Long id, String nev, String szuletesi_ev, String neptun_kod) {
        this.id = id;
        this.nev = nev;
        this.szuletesi_ev = szuletesi_ev;
        this.neptun_kod = neptun_kod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
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
                "ID=" + id +
                ", nev='" + nev + '\'' +
                ", szuletesiev=" + szuletesi_ev +
                ", neptunkod='" + neptun_kod + '\'' +
                '}';
    }
}
