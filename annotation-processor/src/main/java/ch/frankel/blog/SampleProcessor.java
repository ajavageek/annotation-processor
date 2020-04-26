package ch.frankel.blog;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("ch.frankel.blog.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SampleProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment env) {
        annotations.forEach(annotation -> {
                    Set<? extends Element> elements = env.getElementsAnnotatedWith(annotation);
                    elements.stream()
                            .filter(TypeElement.class::isInstance)
                            .map(TypeElement.class::cast)
                            .map(TypeElement::getQualifiedName)
                            .map(name -> "Class " + name + " is annotated with " + annotation.getQualifiedName())
                            .forEach(System.out::println);
                }
        );
        return true;
    }
}
