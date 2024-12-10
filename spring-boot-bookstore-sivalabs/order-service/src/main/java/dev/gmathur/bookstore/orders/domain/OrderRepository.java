package dev.gmathur.bookstore.orders.domain;

import dev.gmathur.bookstore.orders.domain.models.OrderStatus;
import dev.gmathur.bookstore.orders.domain.models.OrderSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// We create repository for aggregate roots. Order is the aggregate root in this case. There is not distinct
// repository for OrderItem or any other entity in the order aggregate. All order related operations are done
// through OrderRepository.
interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByStatus(OrderStatus status);

    Optional<OrderEntity> findByOrderNumber(String orderNumber);

    default void updateOrderStatus(String orderNumber, OrderStatus status) {
        OrderEntity order = this.findByOrderNumber(orderNumber).orElseThrow();
        order.setStatus(status);
        this.save(order);
    }

    @Query(
            """
                    select new dev.gmathur.bookstore.orders.domain.models.OrderSummary(o.orderNumber, o.status)
                    from OrderEntity o
                    where o.userName = :userName
                    """)
    List<OrderSummary> findByUserName(String userName);

    @Query(
            """
                    select distinct o
                    from OrderEntity o left join fetch o.items
                    where o.userName = :userName and o.orderNumber = :orderNumber
                    """)
    Optional<OrderEntity> findByUserNameAndOrderNumber(String userName, String orderNumber);
}
