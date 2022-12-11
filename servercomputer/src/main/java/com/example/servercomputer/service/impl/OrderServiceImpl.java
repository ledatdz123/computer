package com.example.servercomputer.service.impl;

import com.example.servercomputer.dto.ErrorCode;
import com.example.servercomputer.dto.OrderDTO;
import com.example.servercomputer.entity.DetailOrder;
import com.example.servercomputer.entity.Order;
import com.example.servercomputer.entity.Product;
import com.example.servercomputer.entity.User;
import com.example.servercomputer.exception.*;
import com.example.servercomputer.repository.DetailOrderRepository;
import com.example.servercomputer.repository.OrderRepository;
import com.example.servercomputer.repository.ProductRepository;
import com.example.servercomputer.repository.UserRepository;
import com.example.servercomputer.response.MessageResponse;
import com.example.servercomputer.service.EmailService;
import com.example.servercomputer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DetailOrderRepository detailRepository;
    @Autowired
    private ProductRepository productrepository;

    @Override
    public List<OrderDTO> retrieveOrders() {
        List<Order> orders = orderRepository.findAll();

        return new OrderDTO().entityToDTO(orders);
    }

    @Override
    public Optional<OrderDTO> getOrder(Long orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found for this id: " + orderId));
        return Optional.of(new OrderDTO().entityToDTO(order));
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) throws ResourceNotFoundException {
        User user = userRepository.findById(orderDTO.getId_user()).orElseThrow(() ->
                new ResourceNotFoundException("user not found for this id: " + orderDTO.getId_user()));
        Order order = new OrderDTO().dtoToEntity(orderDTO);
        order.setUser(user);
        order.setNgaydat(java.time.LocalDate.now());
        return new OrderDTO().entityToDTO(orderRepository.save(order));
    }

    @Override
    public Boolean deleteOrder(Long orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found for this id: " + orderId));
        this.orderRepository.delete(order);
        return true;
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO order) throws ResourceNotFoundException {
        Order orderExist = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("order not found for this id: " + orderId));


        orderExist.setStatus(order.getStatus());
        orderExist.setTotal_price(order.getTotal_price());

        Order orderr = new Order();
        orderr = orderRepository.save(orderExist);
        String email = orderExist.getUser().getEmail();

        emailService.sendMailWithAttachment(email, "Hi", "<h1>Check attachment for image!</h1>"
                , "<h1>Check attachment for image!</h1>");
        return new OrderDTO().entityToDTO(orderr);
    }

    @Override
    public List<OrderDTO> findOrderByUser(Long userId) throws ResourceNotFoundException {
        Optional<User> userExist = userRepository.findById(userId);
        if (!userExist.isPresent()) {
            throw new ResourceNotFoundException("" + "No User");
        }
        User user = userExist.get();

        List<Order> list = null;
        list = orderRepository.getOrderByUser(user);

        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS = new OrderDTO().entityToDTO(list);
        return orderDTOS;
    }

    @Override
    public OrderDTO updateOrderStatus(Long orderId, OrderDTO order) throws ResourceNotFoundException {
        Order orderExist = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("order not found for this id: " + orderId));

        Order orderr = new Order();
        orderr = orderRepository.save(orderExist);
        return new OrderDTO().entityToDTO(orderr);
    }

    @Override
    public OrderDTO updateStatusOrder(Long orderId) throws ResourceNotFoundException {
        Order orderExist = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("order not found for this id: " + orderId));
        String status = orderExist.getStatus();
        System.out.println(status);
        if (status.equals("Pending")) {
            orderExist.setStatus("Shipping");
        } else if (status.equals("Shipping")) {
            orderExist.setStatus("Delivered");
        } else {
            orderExist.setStatus("Delivered");
        }

        //Product product = new Product();
        Order order = new Order();
        order = orderRepository.save(orderExist);
//        if(order.getStatus().equals("confirmed")){
//            order.getOrderDetails().forEach(detail -> {
//                if(detail.getProduct().getProduct_id() == product.getProduct_id()){
//                    product.setProductQty(product.getProductQty() - detail.getDetailQty());
//                    productrepository.save(product);
//                }
//            });
//        }
        return new OrderDTO().entityToDTO(order);
    }

//    @Override
//    public OrderDTO cancelStatusOrder(Long orderId) throws ResourceNotFoundException, UpdateDataFail {
//        Order orderExist = orderRepository.findById(orderId).orElseThrow(() ->
//                new ResourceNotFoundException("order not found for this id: " + orderId));
//        String status = orderExist.getStatus();
//        if (!status.equals("Pending")) {
//            throw new UpdateDataFail("" + ErrorCode.UPDATE_ORDER_ERROR);
//        }
//        orderExist.setStatus("Canceled");
//        Order order = new Order();
//        order = orderRepository.save(orderExist);
//
//
//
//
//        Optional<Order> orderExistNew = orderRepository.findById(orderId);
//        if (!orderExistNew.isPresent()) {
//            throw new ResourceNotFoundException("" + ErrorCode.FIND_ORDER_ERROR);
//        }
//        Order orderNew = orderExistNew.get();
//
//        List<DetailOrder> list = null;
//        list = detailRepository.findOrderDetailsByOrder(order);
//        for (DetailOrder orderItem:list
//        ) {
//            Product product = productrepository.findById(orderItem.getProduct().getId()).orElseThrow(() ->
//                    new ResourceNotFoundException("product not found for this id: " + orderItem.getProduct().getId()));
//            product.setQuantity(orderItem.getDetail_qty()+product.getQuantity());
//            productrepository.save(product);
//        }
//
//
//        return new OrderDTO().entityToDTO(order);
//    }

    @Override
    public ResponseEntity<MessageResponse> cancelStatusOrder(Long orderId) throws ResourceNotFoundException, UpdateDataFail {
        Order orderExist = orderRepository.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("order not found for this id: " + orderId));
        String status = orderExist.getStatus();
        if (!status.equals("Pending")) {
           throw new ResourceNotFoundException("order not found for this id: " + orderId);
        }
        else {
            orderExist.setStatus("Canceled");
            Order order = new Order();
            order = orderRepository.save(orderExist);

            Optional<Order> orderExistNew = orderRepository.findById(orderId);
            if (!orderExistNew.isPresent()) {
                throw new ResourceNotFoundException("" + ErrorCode.FIND_ORDER_ERROR);
            }
            Order orderNew = orderExistNew.get();

            List<DetailOrder> list = null;
            list = detailRepository.findOrderDetailsByOrder(order);
            for (DetailOrder orderItem : list
            ) {
                Product product = productrepository.findById(orderItem.getProduct().getId()).orElseThrow(() ->
                        new ResourceNotFoundException("product not found for this id: " + orderItem.getProduct().getId()));
                product.setQuantity(orderItem.getDetail_qty() + product.getQuantity());
                productrepository.save(product);
            }
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public String restoreCancelStatus(Long orderId) throws ResourceNotFoundException {
        Optional<Order> orderExist = orderRepository.findById(orderId);
        if (!orderExist.isPresent()) {
            throw new ResourceNotFoundException("" + ErrorCode.FIND_ORDER_ERROR);
        }
        Order order = orderExist.get();

        List<DetailOrder> list = null;
        list = detailRepository.findOrderDetailsByOrder(order);
        for (DetailOrder orderItem:list
        ) {
            Product product = productrepository.findById(orderItem.getProduct().getId()).orElseThrow(() ->
                    new ResourceNotFoundException("product not found for this id: " + orderItem.getProduct().getId()));
            product.setQuantity(orderItem.getDetail_qty()+product.getQuantity());
            productrepository.save(product);
        }
        return "";
    }
}

