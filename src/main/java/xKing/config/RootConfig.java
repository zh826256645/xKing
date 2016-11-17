package xKing.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;


@Configuration
@ComponentScans(value={
		@ComponentScan("xKing.*.service")})
@ActiveProfiles("Test")
public class RootConfig {

}
