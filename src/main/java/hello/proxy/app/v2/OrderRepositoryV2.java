package hello.proxy.app.v2;

import static hello.proxy.utils.ThreadUtils.sleep;

public class OrderRepositoryV2 {
    public void save(String itemId) {
        // 저장 로직
        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("예외 발생!");
        }
        sleep(1000);
    }
}
