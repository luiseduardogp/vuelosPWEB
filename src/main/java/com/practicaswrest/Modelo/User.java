

package com.practicaswrest.Modelo;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column
    private String fullname;

    @Column
    private String correo;

    @Column
    private String password;


    @OneToMany(mappedBy = "usuarios")
    private Set<Booking> bookingSet;

    public User() {
    }

    public User(String fullname, String correo, String password) {
        this.fullname = fullname;
        this.correo = correo;
        this.password = password;
    }



    public int getId() {
        return idUsuario;
    }

    public void setId(int id) {
        this.idUsuario = idUsuario;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
