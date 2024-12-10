package dev.gmathur.bookstore.orders.domain;

import dev.gmathur.bookstore.orders.domain.models.Address;
import dev.gmathur.bookstore.orders.domain.models.Customer;
import dev.gmathur.bookstore.orders.domain.models.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_generator")
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq")
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(name = "username", nullable = false)
    private String userName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private Set<OrderItemEntity> items;

    // Leverages the @Embedded annotation to embed the Customer object into the OrderEntity table. Helps us by
    // reducing the need to write code for individual columns in the table starting with customer_
    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "name", column = @Column(name = "customer_name")),
                    @AttributeOverride(name = "email", column = @Column(name = "customer_email")),
                    @AttributeOverride(name = "phone", column = @Column(name = "customer_phone"))
            })
    private Customer customer;

    // Leverages the @Embedded annotation to embed the Address object into the OrderEntity table. Helps us by
    // reducing the need to write code for individual columns in the table starting with delivery_address_
    @Embedded
    @AttributeOverrides(
            value = {
                    @AttributeOverride(name = "addressLine1", column = @Column(name = "delivery_address_line1")),
                    @AttributeOverride(name = "addressLine2", column = @Column(name = "delivery_address_line2")),
                    @AttributeOverride(name = "city", column = @Column(name = "delivery_address_city")),
                    @AttributeOverride(name = "state", column = @Column(name = "delivery_address_state")),
                    @AttributeOverride(name = "zipCode", column = @Column(name = "delivery_address_zip_code")),
                    @AttributeOverride(name = "country", column = @Column(name = "delivery_address_country")),
            })
    private Address deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String comments;

    // The updatable set to false ensures that the value of the createdAt field is not updated when the record is
    // updated. This is notable
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemEntity> items) {
        this.items = items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
