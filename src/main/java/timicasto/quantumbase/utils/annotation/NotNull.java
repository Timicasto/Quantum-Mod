package timicasto.quantumbase.utils.annotation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.TYPE_PARAMETER, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface NotNull {
}
