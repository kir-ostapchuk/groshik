package com.ostapchuk.app.groshik.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.mapping.FieldType.DECIMAL128;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;

    @Field(targetType = DECIMAL128)
    private BigDecimal amount;

    private MoneyFlowType moneyFlowType;

    private String description;

}
