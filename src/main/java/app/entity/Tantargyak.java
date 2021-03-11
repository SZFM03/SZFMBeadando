package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "tantargyak")

public class Tantargyak {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column(unique = true, nullable = false)
    private String nev;

    @Column(unique = true, nullable = false)
    private String kod;

    @Column(unique = true, nullable = false)
    private String kreditszam;

    public Tantargyak() {
    }

    public Tantargyak(String nev, String kod, String kreditszam) {
        this.nev = nev;
        this.kod = kod;
        this.kreditszam = kreditszam;
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

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKreditszam() {
        return kreditszam;
    }

    public void setKreditszam(String kreditszam) {
        this.kreditszam = kreditszam;
    }

    @Override
    public String toString() {
        return "Tantargyak{" +
                "ID=" + ID +
                ", nev='" + nev + '\'' +
                ", kod='" + kod + '\'' +
                ", kreditszam='" + kreditszam + '\'' +
                '}';
    }
}
