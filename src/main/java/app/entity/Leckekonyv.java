package app.entity;

import javax.persistence.*;

@Entity
@Table(name="leckekonyv")
public class Leckekonyv {

    @Id
    @Column
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long hallgato_id;

    @Column
    private Long tantargy_id;

    @Column
    private Integer jegy;

    @Column
    private String neptun_kod;

    @Column
    private String nev;

    public Leckekonyv() {
    }

    public Leckekonyv(Long hallgato_id, Long tantargy_id, Integer jegy) {
        this.hallgato_id = hallgato_id;
        this.tantargy_id = tantargy_id;
        this.jegy = jegy;
    }

    public Leckekonyv(Long id, Long hallgato_id, Long tantargy_id, Integer jegy) {
        this.id = id;
        this.hallgato_id = hallgato_id;
        this.tantargy_id = tantargy_id;
        this.jegy = jegy;
    }

    public Leckekonyv(String neptun_kod, String nev, Integer jegy) {
        this.neptun_kod = neptun_kod;
        this.nev = nev;
        this.jegy = jegy;

    }

    public String getNeptun_kod() {
        return neptun_kod;
    }

    public void setNeptun_kod(String neptun_kod) {
        this.neptun_kod = neptun_kod;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHallgato_id() {
        return hallgato_id;
    }

    public void setHallgato_id(Long hallgato_id) {
        this.hallgato_id = hallgato_id;
    }

    public Long getTantargy_id() {
        return tantargy_id;
    }

    public void setTantargy_id(Long tantargy_id) {
        this.tantargy_id = tantargy_id;
    }

    public Integer getJegy() {
        return jegy;
    }

    public void setJegy(Integer jegy) {
        this.jegy = jegy;
    }

    @Override
    public String toString() {
        return "Leckekonyv{" +
                "id=" + id +
                ", hallgato_id=" + hallgato_id +
                ", tantargy_id=" + tantargy_id +
                ", jegy=" + jegy +
                '}';
    }
}
