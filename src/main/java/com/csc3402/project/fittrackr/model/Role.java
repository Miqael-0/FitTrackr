package com.csc3402.project.fittrackr.model;


import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users= new ArrayList<>();
    public Role(String name)
    {
        this.name = name;
    }
    public Role(String name, List<User> users){
        this.name = name;
        this.users = users;
    }
    public Role() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List getUsers() {
        return users;
    }
    public void setUsers(List users) {
        this.users = users;
    }
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
        '}';
    }
}
