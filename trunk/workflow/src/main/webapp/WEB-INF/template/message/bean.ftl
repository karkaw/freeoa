package com.damuzee.core.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * auto generator
 */
 
@Entity
@Table(name = ${tableName} , uniqueConstraints = @UniqueConstraint(columnNames = ${primaryKey}))
public class ${beanName} implements java.io.Serializable {
 

}