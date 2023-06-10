package hello.proxy.common.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceInterfaceImpl implements ServiceInterface {
    @Override
    public void save() {
        log.info("save execute");
    }

    @Override
    public void find() {
        log.info("save execute");
    }
}
