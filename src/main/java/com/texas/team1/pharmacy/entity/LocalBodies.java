package com.texas.team1.pharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "local_bodies", uniqueConstraints =
@UniqueConstraint(name = "uk_lb_name", columnNames = {"name", "district_id"}))
@NoArgsConstructor
@AllArgsConstructor
public class LocalBodies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "name_np", length = 50)
    private String nameNepali;

    private Integer code;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = District.class)
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "fk_district_id"))
    private District district;
}
