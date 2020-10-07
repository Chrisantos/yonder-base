package com.chriseze.yonder.utils.base;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 4512794130884392491L;

    private static final String SEQ_GENERATOR = "login_sequence_generator";

    @Id
    @SequenceGenerator(name = SEQ_GENERATOR, sequenceName = "login_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATOR)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATE_DATE")
    private LocalDate createDate;
}
