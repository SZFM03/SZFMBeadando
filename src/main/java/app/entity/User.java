package app.entity;

import javax.persistence.*;

@Entity
@Table(name = "jogosultak")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column(unique = true, nullable = false)
    private String nev;

    @Column(unique = true, nullable = false)
    private String jelszo;

    public User() {
    }

    public User(String nev, String jelszo) {
        this.nev = nev;
        this.jelszo = jelszo;
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

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + ID +
                ", name='" + nev + '\'' +
                ", password='" + jelszo + '\'' +
                '}';
    }
}
