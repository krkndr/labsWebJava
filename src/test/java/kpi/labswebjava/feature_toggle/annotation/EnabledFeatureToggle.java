package kpi.labswebjava.feature_toggle.annotation;

import kpi.labswebjava.feature_toggle.FeatureToggles;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface EnabledFeatureToggle {
    FeatureToggles value();
}