package seedu.cakecollate.model.order;

import static seedu.cakecollate.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.cakecollate.model.tag.Tag;

/**
 * Represents an Order in the cakecollate.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order implements Comparable<Order> {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<OrderDescription, Integer> orderDescriptions = new HashMap<>();
    private final DeliveryDate deliveryDate;
    private final DeliveryStatus deliveryStatus;
    private final Request request;

    // with default status
    /**
     * Every field must be present and not null.
     */

    public Order(Name name, Phone phone, Email email, Address address,
                 HashMap<OrderDescription, Integer> orderDescriptions,
                 Set<Tag> tags, DeliveryDate deliveryDate, Request request) {
        requireAllNonNull(name, phone, email, address, orderDescriptions, tags, deliveryDate, request);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.orderDescriptions.putAll(orderDescriptions);
        this.tags.addAll(tags);
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = new DeliveryStatus();
        this.request = request;
    }

    // with status
    /**
     * Initialises an order.
     * @param name Name of the customer.
     * @param phone Phone number of the customer.
     * @param email Email of the customer.
     * @param address Address of the customer.
     * @param orderDescriptions Order descriptions of the orders made by the customer.
     * @param tags Tags for the order.
     * @param deliveryDate Delivery date of the order.
     * @param deliveryStatus Delivery status of the order.
     * @param request Request of the order.
     */
    public Order(Name name, Phone phone, Email email, Address address, Map<OrderDescription, Integer> orderDescriptions,
                 Set<Tag> tags, DeliveryDate deliveryDate, DeliveryStatus deliveryStatus, Request request) {
        requireAllNonNull(name, phone, email, address, orderDescriptions, tags, deliveryDate, request);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.orderDescriptions.putAll(orderDescriptions);
        this.tags.addAll(tags);
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.request = request;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable order description set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Map<OrderDescription, Integer> getOrderDescriptions() {
        return Collections.unmodifiableMap(orderDescriptions);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public DeliveryDate getDeliveryDate() {
        return deliveryDate;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public Request getRequest() {
        return request;
    }

    /**
     * Returns true if both orders have the same name, address, order description and delivery date.
     * This defines a weaker notion of equality between two orders.
     */
    public boolean isSameOrder(Order otherOrder) {
        if (otherOrder == this) {
            return true;
        }

        return otherOrder != null
                && otherOrder.getName().equals(getName())
                && otherOrder.getAddress().equals(getAddress())
                && otherOrder.getOrderDescriptions().equals(getOrderDescriptions())
                && otherOrder.getDeliveryDate().equals(getDeliveryDate());
    }

    /**
     * Returns true if both orders have the same identity and data fields.
     * This defines a stronger notion of equality between two orders.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getName().equals(getName())
                && otherOrder.getPhone().equals(getPhone())
                && otherOrder.getEmail().equals(getEmail())
                && otherOrder.getAddress().equals(getAddress())
                && otherOrder.getOrderDescriptions().equals(getOrderDescriptions())
                && otherOrder.getTags().equals(getTags())
                && otherOrder.getDeliveryDate().equals(getDeliveryDate())
                && otherOrder.getDeliveryStatus().equals(getDeliveryStatus())
                && otherOrder.getRequest().equals(getRequest());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, deliveryDate, deliveryStatus, request);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Map<OrderDescription, Integer> orderDescriptions = getOrderDescriptions();
        if (!orderDescriptions.isEmpty()) {
            builder.append("; Order Descriptions:");
            orderDescriptions.forEach((obj, quantity) ->
                    builder.append(" ").append(quantity).append(" x ").append(obj).append(","));
            builder.setLength(builder.length() - 1);
        }


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; DeliveryDate: ")
                .append(getDeliveryDate())
                .append("; DeliveryStatus: ")
                .append(getDeliveryStatus())
                .append("; Request: ")
                .append(getRequest());

        return builder.toString();
    }

    @Override
    public int compareTo(Order o) {
        int statusCompareTo = this.getDeliveryStatus().compareTo(o.getDeliveryStatus());

        if (statusCompareTo == 0) {
            return (this.getDeliveryDate().compareTo(o.getDeliveryDate()));
        } else {
            return statusCompareTo;
        }
    }
}
