package com.texas.team1.pharmacy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "district", uniqueConstraints =
@UniqueConstraint(name = "uk_district_name", columnNames = "name"))
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "name_np", length = 50)
    private String nameNepali;

    private Integer code;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Province.class)
    @JoinColumn(name = "province_id", foreignKey = @ForeignKey(name = "fk_province_id"))
    private Province province;
}
