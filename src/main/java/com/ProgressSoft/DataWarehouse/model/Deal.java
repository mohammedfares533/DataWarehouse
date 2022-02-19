package com.ProgressSoft.DataWarehouse.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CUS_DEAL")
public class Deal {

    @GenericGenerator(
            name = "CUS_DEAL_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "SEQ", value = "SEQUENCE"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Id
    @GeneratedValue(generator = "CUS_DEAL_SEQ")
    @Column(name = "Id")
    private Long id;

    @Column(name = "Deal_Id", unique = true, nullable = false)
    private Long dealId;

    @Column(name = "Order_Currency", nullable = false)
    private String orderCurrency;

    @Column(name = "To_Currency", nullable = false)
    private String toCurrency;

    @Column(name = "Deal_Amount", nullable = false)
    private BigDecimal dealAmount;

    @CreatedDate
    @Column(name = "Deal_Time", updatable = false)
    private LocalDateTime createDate;

}
