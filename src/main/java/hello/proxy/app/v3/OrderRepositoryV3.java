package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

import static hello.proxy.utils.ThreadUtils.sleep;

@Repository
public class OrderRepositoryV3 {
    public void save(String itemId) {
        // 저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("예외 발생!");
        }
        sleep(1000);
    }
}
