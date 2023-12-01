package dev.jays.ecommerce.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    //Non-UUID autoincrement Integral Primary key
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */


    //Version 4 UUID

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Column(name= "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID uuid;


    //Version 2 UUID   (Naman Bhalla taught this)
    /*
    @Id
    @GeneratedValue(generator="uuidgenerator")
    @GenericGenerator(name="uuidgenerator", strategy= "uuid2")
    @Column(name="id" , columnDefinition ="binary(16)", nullable=false, updatable=false)
    private UUID id;
    */
}
