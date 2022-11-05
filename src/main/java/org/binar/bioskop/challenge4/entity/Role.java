package org.binar.bioskop.challenge4.entity;

import lombok.*;
import org.binar.bioskop.challenge4.respon.ERole;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Enumerated(STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {
    }

    public Role(ERole name){
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }


}
