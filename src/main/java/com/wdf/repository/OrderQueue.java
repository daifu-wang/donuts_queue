package com.wdf.repository;

import com.wdf.entity.Order;
import com.wdf.service.MockPremiumClientService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class OrderQueue {

    final MockPremiumClientService premiumClientService;

    private final BlockingQueue<Order> normalOrderQueue;

    private final BlockingQueue<Order> premiumOrderQueue;

    private final Integer MAX_NORMAL_CUSTOMER_QUANTITY = 21000;
    private final Integer MAX_PREMIUM_CUSTOMER_QUANTITY = 1000;

    protected OrderQueue(MockPremiumClientService premiumClientService) {
        normalOrderQueue = new ArrayBlockingQueue<>(MAX_NORMAL_CUSTOMER_QUANTITY);
        premiumOrderQueue = new ArrayBlockingQueue<>(MAX_PREMIUM_CUSTOMER_QUANTITY);
        this.premiumClientService = premiumClientService;
    }

    public Boolean offerOrder(Order order) {

        Integer clientId = order.getClientId();

        if (isPremium(clientId)) {
            return premiumOrderQueue.offer(order);
        }
        return normalOrderQueue.offer(order);

    }

    private boolean isPremium(Integer clientId) {
        if (premiumClientService.isPremium(clientId)) {
            return true;
        }
        return false;
    }

    public Optional<Order> poll() {
        Optional<Order> order = pollFromQueue(premiumOrderQueue);
        if (order.isPresent()){
            return order;
        }else {
            return pollFromQueue(normalOrderQueue);
        }
    }

    private Optional<Order> pollFromQueue(BlockingQueue<Order> queue) {
        if (queue.size() > 0) {
            return Optional.ofNullable(queue.poll());
        }
        return Optional.empty();
    }

    public Optional<Order> peek() {
        Optional<Order> order = peekFromQueue(premiumOrderQueue);
        if (order.isPresent()){
            return order;
        }else {
            return peekFromQueue(normalOrderQueue);
        }
    }

    private Optional<Order> peekFromQueue(BlockingQueue<Order> queue) {
        if (queue.size() > 0) {
            return Optional.ofNullable(queue.peek());
        }
        return Optional.empty();
    }

    protected int premiumQueueSize(){
        return premiumOrderQueue.size();
    }

    protected int normalQueueSize(){
        return normalOrderQueue.size();
    }
}
