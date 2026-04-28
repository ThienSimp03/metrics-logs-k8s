package nobrain.logs_loki.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TraceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Lấy traceId và spanId từ MDC (Micrometer Tracing/Sleuth tự động điền vào MDC)
        String traceId = MDC.get("traceId");
        String spanId = MDC.get("spanId");

        // Gán vào Request Attributes để Tomcat Access Log Valve có thể lấy bằng %{traceId}r
        if (traceId != null) {
            request.setAttribute("traceId", traceId);
        }
        if (spanId != null) {
            request.setAttribute("spanId", spanId);
        }

        chain.doFilter(request, response);
    }
}
