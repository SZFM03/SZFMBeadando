package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "jegy")
public class Jegy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private long hallgato_id;

    @Column
    private Long tantargy_id;

    @Column
    private Integer jegy;

    public Jegy(long id, long hallgato_id, Long tantargy_id, Integer jegy) {
        this.id = id;
        this.hallgato_id = hallgato_id;
        this.tantargy_id = tantargy_id;
        this.jegy = jegy;
    }

    public Jegy() {
    }

    public Jegy(long hallgato_id, Long tantargy_id, Integer jegy) {
        this.hallgato_id = hallgato_id;
        this.tantargy_id = tantargy_id;
        this.jegy = jegy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHallgato_id() {
        return hallgato_id;
    }

    public void setHallgato_id(long hallgato_id) {
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
        return "Jegy{" +
                "id=" + id +
                ", Hallgato_id=" + hallgato_id +
                ", tantargy_id=" + tantargy_id +
                ", jegy=" + jegy +
                '}';
    }
}
