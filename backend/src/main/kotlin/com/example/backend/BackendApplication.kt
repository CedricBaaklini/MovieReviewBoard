import org.springframework.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.web.bind.annotation.*
import org.springframework.web.cors.*
import org.springframework.web.filter.CorsFilter
import java.util.*
import javax.servlet.Filter

@RestController
class HelloWorldController {
	@GetMapping("/helloworld")
	fun greet(): String {
		return "helloworld!"
	}

	@Bean
	fun simpleCorsFilter(): FilterRegistration<*> {
		val source = UrlBasedCorsConfigurationSource()
		val config = CorsConfiguration()
		config.allowCredentials = true
		config.allowedOrigins = Collections.singletonList("http://localhost:8080")
		config.allowedMethods = Collections.singletonList("*")
		config.allowedHeaders = Collections.singletonList("*")
		source.registerCorsConfiguration("/**", config)
		val bean = FilterRegistrationBean<Filter>(CorsFilter(source))
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
		return bean
	}
}
