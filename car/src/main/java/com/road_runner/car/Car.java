package com.road_runner.car;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Car {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public long id;

    public String name;
    public String image;
  
    public String shortDescription;
    @Column(columnDefinition = "TEXT")
    public String longDesc;
    public int price;

}
