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
    private int jegy;

    public Leckekonyv() {
    }

    public Leckekonyv(Long hallgato_id, Long tantargy_id, int jegy) {
        this.hallgato_id = hallgato_id;
        this.tantargy_id = tantargy_id;
        this.jegy = jegy;
    }

    public Leckekonyv(Long id, Long hallgato_id, Long tantargy_id, int jegy) {
        this.id = id;
        this.hallgato_id = hallgato_id;
        this.tantargy_id = tantargy_id;
        this.jegy = jegy;
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

    public int getJegy() {
        return jegy;
    }

    public void setJegy(int jegy) {
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
