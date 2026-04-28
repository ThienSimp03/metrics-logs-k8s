package nobrain.logs_loki.controller;

import lombok.extern.slf4j.Slf4j;
import nobrain.logs_loki.model.Order;
import nobrain.logs_loki.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/demo")
@Slf4j
public class LogDemoController {

    @GetMapping("/logs")
    public String generateLogs() {
        // 1. INFO logs
        log.info("Bắt đầu xử lý request lấy thông tin demo logs.");

        // 2. INFO log với object
        User user = new User("U001", "admin_user", "admin@example.com");
        log.info("Thông tin user đang thao tác: {}", user);

        // 3. DEBUG log với list object
        List<Order> orders = Arrays.asList(
                new Order("ORD-1", 150.50, Arrays.asList("Item A", "Item B")),
                new Order("ORD-2", 300.00, Arrays.asList("Item C"))
        );
        log.debug("Chi tiết các order của user {}: {}", user.getUsername(), orders);

        // 4. ERROR log với StackTrace
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            log.error("Đã xảy ra lỗi hệ thống khi tính toán. Stack trace sẽ được gom thành 1 chuỗi JSON:", e);
        }

        log.info("Kết thúc xử lý request.");
        return "Log generated successfully! Check your console/Loki.";
    }
}
