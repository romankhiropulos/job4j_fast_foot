package ru.job4j.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.StringJoiner;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "kitchen_job")
public class KitchenJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Override
    public String toString() {
        return new StringJoiner(
                ", ", KitchenJob.class.getSimpleName() + "[", "]"
        )
                .add("id=" + id)
                .add("description='" + description + "'")
                .add("order=" + order)
                .toString();
    }
}
