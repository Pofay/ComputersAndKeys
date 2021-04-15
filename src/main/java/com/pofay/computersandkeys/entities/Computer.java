package com.pofay.computersandkeys.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.Getter;

@Entity
@Table(name = "computers")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Computer {

    @Id
    @Getter
    private String modelNumber;

    @Getter
    private String maker;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "computer_type")
    @Type(type = "pgsql_enum")
    @Getter
    private ComputerTypes type;

    @Getter
    private String language;

    @Type(type = "list-array")
    @Column(columnDefinition = "text[]")
    @Getter
    private List<String> colors;

    public Computer() {
    }

    public Computer(String modelNumber, String maker, ComputerTypes type, String language, List<String> colors) {
        this.modelNumber = modelNumber;
        this.maker = maker;
        this.type = type;
        this.language = language;
        this.colors = colors;
    }
}
