package timicasto.quantumbase.utils.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface OverrideDefaultItemBlock {
    @NotNull String clazz();
    String fieldName() default "itemBlock";
}
