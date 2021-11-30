package config.setup;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:envDev.properties"})
public interface BrowserStackConfig extends Config {
        String userName();
        String mobileKey();
        String appURL();
}

