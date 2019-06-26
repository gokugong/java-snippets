package context;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@ConditionalOnWebApplication
@ConditionalOnProperty(value = "args.match.enabled", matchIfMissing = true)
@Configuration
public class ContextArgumentResolverConfig extends WebMvcConfigurerAdapter
{

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(0, new ContextArgumentResolver());
    }

    public class ContextArgumentResolver implements HandlerMethodArgumentResolver
    {
        @Override
        public boolean supportsParameter(MethodParameter parameter)
        {
            return parameter.getParameterType().equals(Context.class);
        }

        @Override
        public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception
        {
            return Context.getContext();
        }
    }
}