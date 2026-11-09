package com.yawolart.hibernateDemo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
//@Table(name = "@Table(name = "Student_hibernate_play")")
@Table(name = "students")
public class Student {

/*
    //this part of code use table: Student_hibernate_play
    //to play with this code uncomment @Table(name = "Student_hibernate_play")
    //and comment @Table(name = "students")

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto generated id
    private long id;

    //Column name in the name can be customized
    @Column(name = "student_name")
    private String name;

    //Constraint for the column can be added
    @Column(
            name = "student_email", //column name
            unique = true, //UNIQUE constraint
            nullable = false, //NOT NULL constraints
            length = 150 // Size of the varchar eg. email VARCHAR(150)
    )
    private String email;

    //decimal precision can be set
    @Column(
            //its like percentage DECIMAL(5,2)
            precision = 5,
            scale = 2
    )
    private BigDecimal percentage;

    //Enum can be represented on the database
    //@Enumerated(EnumType.ORDINAL) //To store the enum ordinal to database
    @Enumerated(EnumType.STRING) //to the in string form
    private StudentStatus studentStatus;

    //String large object can be store in the database
    //Lob -> large object
    @Lob
    private String studentDescription;

    //date and time can be store
    private LocalDateTime createdAt;

    //Attribute convertor
    //For example boolean true can be store as 'yes' and false as 'no'
    //in the database
    @Convert(converter = BooleanToStringConvertor.class)
    private Boolean isMonitor;

    //Composition relationship
    //For example store another class object to the database table
    //the other class object will also consider as part of the table
    //it will store on the same table
    @Embedded
    private Address address;

    //if there are two Address class object the attribute name on the
    //table will be repeated, the attribute name can be overridden.
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "street",
                    column = @Column(name = "permanent_street")
            ),
            @AttributeOverride(
                    name = "city",
                    column = @Column(name = "permanent_city")
            ),
            @AttributeOverride(
                    name = "state",
                    column = @Column(name = "permanent_state")
            )
    })
    private Address permanentAddress;

    //Storing collection type in the table
    //in this case, it is not possible to store the list or set or map
    //any collection in a single cell of the database
    //a new table is required to create and put the primary key of this
    //Student_hibernate_play table to the new table as foreign key
    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(
                    name = "student_id"

                    //this is optional
                    //referencedColumnName = "id"
            )
    )
    private List<String> skills;

    //If any field is not to be a column in the database table
    @Transient
    private String temporary;

*/
    //this part of code use table: students
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer age;




    public Student() {
    }

    public Student(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public Student setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }
}
