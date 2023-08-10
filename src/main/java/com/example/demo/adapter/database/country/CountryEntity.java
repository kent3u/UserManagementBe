package com.example.demo.adapter.database.country;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "country")
class CountryEntity {

    @Id
    private String iso;
    @Column(name = "name")
    private String name;
}
