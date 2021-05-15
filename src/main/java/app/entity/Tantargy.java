package app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Tantargy")
@Table(name = "tantargy")

public class Tantargy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String nev;

    @Column(unique = true, nullable = false)
    private String kod;

    @Column(nullable = false)
    private Integer kreditszam;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tantargyak")
    private List<Hallgato> hallgatok = new ArrayList<>();

    public List<Hallgato> getHallgatok() {
        return hallgatok;
    }

    public void setHallgatok(List<Hallgato> hallgatok) {
        for (var hallgato : hallgatok){
            addHallgato(hallgato);
        }
    }

    public void addHallgato(Hallgato hallgato){
        hallgatok.add(hallgato);
        hallgato.getTantargyak().add(this);
    }


    public Tantargy() {
    }

    public Tantargy(String nev, String kod, Integer kreditszam) {
        this.nev = nev;
        this.kod = kod;
        this.kreditszam = kreditszam;
    }

    public Tantargy(Long id, String nev, String kod, Integer kreditszam, List<Hallgato> hallgatok) {
        this.id = id;
        this.nev = nev;
        this.kod = kod;
        this.kreditszam = kreditszam;
        this.hallgatok = hallgatok;
    }

    public Tantargy(Long id, String nev, String kod, Integer kreditszam) {
        this.id = id;
        this.nev = nev;
        this.kod = kod;
        this.kreditszam = kreditszam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tantargy that = (Tantargy) o;
        return Objects.equals(id, that.id) && Objects.equals(nev, that.nev) && Objects.equals(kod, that.kod) && Objects.equals(kreditszam, that.kreditszam) && Objects.equals(hallgatok, that.hallgatok);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, kod, kreditszam, hallgatok);
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

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Integer getKreditszam() {
        return kreditszam;
    }

    public void setKreditszam(Integer kreditszam) {
        this.kreditszam = kreditszam;
    }

    @Override
    public String toString() {
        return "Tantargyak{" +
                "ID=" + id +
                ", nev='" + nev + '\'' +
                ", kod='" + kod + '\'' +
                ", kreditszam='" + kreditszam + '\'' +
                '}';
    }
}
