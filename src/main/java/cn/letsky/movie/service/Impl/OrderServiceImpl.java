package cn.letsky.movie.service.Impl;

import cn.letsky.movie.entity.Order;
import cn.letsky.movie.entity.Scene;
import cn.letsky.movie.exception.EntityNotFoundException;
import cn.letsky.movie.repository.OrderRepository;
import cn.letsky.movie.service.OrderService;
import cn.letsky.movie.service.SceneService;
import cn.letsky.movie.util.CommonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SceneService sceneService;

    public OrderServiceImpl(OrderRepository orderRepository, SceneService sceneService) {
        this.orderRepository = orderRepository;
        this.sceneService = sceneService;
    }

    @Override
    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public synchronized void pay(Order order) {
        Integer sceneId = order.getSceneId();
        Integer ticketNum = order.getTicketNum();
        Scene scene = sceneService.getScene(sceneId);
        int remainTicket = scene.getSeatNum() - ticketNum;
        if (remainTicket > 0) {
            scene.setSeatNum(remainTicket);
            sceneService.update(scene);
            int insert = orderRepository.insert(order);
            CommonUtils.checkInsert(insert);
        }
    }
}
